package in.ashokit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ashokit.dto.LoginDto;
import in.ashokit.dto.QuotesDto;
import in.ashokit.dto.RegisterDto;
import in.ashokit.dto.ResetPwdDto;
import in.ashokit.dto.UsersDto;
import in.ashokit.entity.City;
import in.ashokit.entity.Country;
import in.ashokit.entity.State;
import in.ashokit.entity.Users;
import in.ashokit.repo.CityRepo;
import in.ashokit.repo.CountryRepo;
import in.ashokit.repo.StateRepo;
import in.ashokit.repo.UsersRepo;
import in.ashokit.utils.EmailUtils;
@Service
public class UsersServiceImpl implements UsersService{
	@Autowired
	private CountryRepo countryRepo;

	@Autowired
	private StateRepo stateRepo;
	
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private UsersRepo usersRepo;
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public Map<Integer, String> getCountries() {
		Map<Integer, String> countrieslist=new HashMap<>();
		List<Country> findAll=countryRepo.findAll();
		findAll.forEach(c->{
			countrieslist.put(c.getCountryId(), c.getCountryName());
		});
		return countrieslist;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		Map<Integer, String> list=new HashMap<>();
		List<State> states=stateRepo.findByCountry_CountryId(countryId);
		states.forEach(s->{
			list.put(s.getStateId(), s.getStateName());
		});
		return list;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		Map<Integer, String> cities=new HashMap<>();
		List<City> list=cityRepo.findByState_StateId(stateId);
		list.forEach(c->{
			cities.put(c.getCityId(), c.getCityName());
		});
	return cities;
	}
	@Override
	public UsersDto getUsers(String email) {
		Users user=usersRepo.findByEmail(email);
		if(user==null) {
			return null;
		}
		ModelMapper mapper = new ModelMapper();
		UsersDto usersDto = mapper.map(user, UsersDto.class);
		
		return usersDto;
	}

	@Override
	public boolean registerUser(RegisterDto regDto) {
		ModelMapper mapper = new ModelMapper();
		Users user = mapper.map(regDto, Users.class);
		Country country = countryRepo.findById(regDto.getCountryId()).orElseThrow();
		State state = stateRepo.findById(regDto.getStateId()).orElseThrow();
		City city = cityRepo.findById(regDto.getCityId()).orElseThrow();
		user.setCountry(country);
		user.setState(state);
		user.setCity(city);
		user.setPwd(generateRandom());
		user.setUpdatePwd("No");
		Users users = usersRepo.save(user);
		String subject="User Registration";
		String body="Your temporary Pwd is"+user.getPwd();
		emailUtils.sendEmail(regDto.getEmail(),subject, body);
		return users.getUserId()!=null;
	}

	@Override
	public UsersDto getUser(LoginDto loginDto) {
		Users user=usersRepo.findByEmailAndPwd(loginDto.getEmail(),loginDto.getPwd());
		if(user==null) {
			return null;
		}
		ModelMapper mapper = new ModelMapper();
		return mapper.map(user,UsersDto.class);
	}

	@Override
	public boolean resetPwd(ResetPwdDto pwdDto) {
	 Users user = usersRepo.findByEmail(pwdDto.getEmail());
		if(user!=null) {
			
			user.setPwd(pwdDto.getNewPassword());
			user.setUpdatePwd("YES");
			usersRepo.save(user);
			return true;
		}
		return false;
	}

	@Override
	public String getQuote() {
		QuotesDto[] quotations=null;
		String url="https://type.fit/api/quotes";
		RestTemplate rt=new RestTemplate();
		ResponseEntity<String> forEntity=rt.getForEntity(url, String.class);
		String responseBody=forEntity.getBody();
		ObjectMapper mapper=new ObjectMapper();
		try {
			quotations=mapper.readValue(responseBody, QuotesDto[].class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Random r= new Random();
		int index=r.nextInt(quotations.length-1);
		return quotations[index].getText();
	}
private static String generateRandom() {
	String aToZ="ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
	Random rand=new Random();
	StringBuilder res=new StringBuilder();
	for(int i=0;i<6;i++) {
		int randIndex=rand.nextInt(aToZ.length());
		res.append(aToZ.charAt(randIndex));
	}
	return res.toString();
}


}
