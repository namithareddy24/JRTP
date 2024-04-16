package in.ashokit.service;

import java.util.Map;

import in.ashokit.dto.LoginDto;
import in.ashokit.dto.RegisterDto;
import in.ashokit.dto.ResetPwdDto;
import in.ashokit.dto.UsersDto;

public interface UsersService {
	public Map<Integer,String> getCountries();

	public Map<Integer,String> getStates(Integer countryId);

	public Map<Integer,String> getCities(Integer stateId);

	public UsersDto getUsers(String email);

	public boolean registerUser(RegisterDto regDto);

	public UsersDto getUser(LoginDto loginDto);

	public boolean resetPwd(ResetPwdDto pwdDto);

	public String getQuote();
	

}
