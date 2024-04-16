package in.ashokit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ashokit.dto.LoginDto;
import in.ashokit.dto.RegisterDto;
import in.ashokit.dto.ResetPwdDto;
import in.ashokit.dto.UsersDto;
import in.ashokit.service.UsersService;
@Controller
public class UsersController {
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("registerDto",new RegisterDto());
		model.addAttribute("countries",usersService.getCountries());
		return "registerView";
	}
	@GetMapping("/states/{countryId}")
	@ResponseBody
	public Map<Integer, String> getStates(@PathVariable("countryId") Integer countryId) {
		return usersService.getStates(countryId);
	}
	@GetMapping("/cities/{stateId}")
	@ResponseBody
	public Map<Integer, String> getCities(@PathVariable("stateId") Integer stateId) {
		return usersService.getCities(stateId);
	}
	@PostMapping("/register")
	public String register(RegisterDto regDto, Model model) {
		UsersDto user = usersService.getUsers(regDto.getEmail());
		if(user!=null) {
			model.addAttribute("emsg","Duplicate Email");
			return "registerView";
		}
		
		boolean registerUser=usersService.registerUser(regDto);
		if(registerUser) {
			model.addAttribute("smsg","user Registered");
		}else {
			model.addAttribute("emsg","Registration Failed");
		}

		model.addAttribute("countries",usersService.getCountries());
		return "registerView";
	}
	@GetMapping("/")
	public String loginPage(Model model) {
		model.addAttribute("loginDto",new LoginDto());
		return "index";
	}
	@PostMapping("/login")
	public String login(LoginDto loginDto, Model model) {
		UsersDto user=usersService.getUser(loginDto);
		if(user==null) {
			model.addAttribute("emsg","Invalid Credentials");
			return "index";
		}
		
		if("YES".equals(user.getUpdatePwd())) {
			return "redirect:dashboard";
		}else {
			ResetPwdDto resetPwdDto = new ResetPwdDto();
			resetPwdDto.setEmail(user.getEmail());
			model.addAttribute("resetPwdDto",resetPwdDto);
			return "resetPwdView";
		}
	}
	@PostMapping("/resetPwd")
	public String resetPwd(ResetPwdDto pwdDto, Model model) {
		UsersDto user = usersService.getUsers(pwdDto.getEmail());
		if(user.getPwd().equals(pwdDto.getOldPassword())) {
			boolean resetPwd=usersService.resetPwd(pwdDto);
			if(resetPwd) {
				return "redirect:/dashboard";
			}else {
				model.addAttribute("emsg","Pwd update failed");
				return "resetPwdView";
			}
		}else {
			model.addAttribute("emsg","Given old pwd is wrong");
			return "resetPwdView";
		}
		
	}
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		String quote = usersService.getQuote();
		model.addAttribute("quote",quote);
		return "dashboard";
	}
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}

}
