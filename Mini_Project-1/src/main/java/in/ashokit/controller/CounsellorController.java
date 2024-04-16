package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.dto.Dashboard;
import in.ashokit.entity.Counsellor;
import in.ashokit.service.CounsellorService;
import in.ashokit.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	@Autowired
	private CounsellorService counsellorService;
	
	@Autowired
	private EnquiryService enqService;
		
	@GetMapping("/logout")
	public String logOut(HttpServletRequest req,Model model) {
		HttpSession session=req.getSession(false);
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/register")
	public String form(Model model) {
		model.addAttribute("cobj",new Counsellor());
		return "register";
	}
	
	@PostMapping("/register")
	public String handleRegister(Counsellor c,Model model) {
		 boolean status = counsellorService.saveCounsellor(c);
		 if(status) {
			 
			 model.addAttribute("smsg","Counsellor saved");
			 model.addAttribute("cobj",new Counsellor());
		 }else {
			 model.addAttribute("cobj",new Counsellor());
			 model.addAttribute("emsg","failed to save");
			 
		 }
		 return "register";
	}
	
	
	@GetMapping("/")
	public String LoadForm(Model model) {
		model.addAttribute("counsellor",new Counsellor() );
		return "login";
	}
	@PostMapping("/login")
	public String handlelogin(Counsellor counsellor, HttpServletRequest req,Model model) {
		Counsellor c = counsellorService.getCounsellor(counsellor.getEmail(), counsellor.getPwd());
		if(c==null) {
			model.addAttribute("counsellor",new Counsellor() );
			model.addAttribute("msg", "Invalid Credentials");
			return "login";
		}else {
			
			HttpSession session=req.getSession(true);
			session.setAttribute("cid",c.getCid());
			Dashboard db= enqService.getDashboard(c.getCid());
			model.addAttribute("enq", db);
			return "dashboard";
		}
	}
	@GetMapping("/dashboard")
	public String BuildDashboard(HttpServletRequest req,Model model) {
		HttpSession session = req.getSession(false);
		Integer cid = (Integer)session.getAttribute("cid");
		Dashboard db= enqService.getDashboard(cid);
		model.addAttribute("enq", db);
		return "dashboard";
		
	}
	
}
