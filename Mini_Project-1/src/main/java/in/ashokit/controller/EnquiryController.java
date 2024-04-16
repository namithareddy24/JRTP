package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.entity.Enquiry;
import in.ashokit.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Controller
public class EnquiryController {
	@Autowired
	private EnquiryService enqService;
	
	
	@GetMapping("/form")
	public String saveEnquiry(Model model) {
		model.addAttribute("enq", new Enquiry());
		return "Enquiry";
	}
	@PostMapping("/enquiry")
	public String handleEnquiry(Enquiry enq, HttpServletRequest req,Model model) {
		HttpSession session = req.getSession(false);
		Integer cid = (Integer)session.getAttribute("cid");
		boolean status = enqService.addEnquiry(enq, cid);
		 if(status) {
			 model.addAttribute("enq", new Enquiry());
			 model.addAttribute("smsg","Enquiry saved");
		 }else {
			 model.addAttribute("enq", new Enquiry());
			 model.addAttribute("emsg", "Enquiry failed");
		 }
		return "Enquiry";
	}
	@GetMapping("/Enquiries")
	public String viewEnquiry(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		Integer cid = (Integer)session.getAttribute("cid");
		
		List<Enquiry> enquiry = enqService.getEnquiry(new Enquiry(), cid);
		model.addAttribute("enqs",enquiry);
		model.addAttribute("enq", new Enquiry());
		return "display";
	}
	@PostMapping("/Enquiries")
	public String viewEnquiry(@ModelAttribute("enq") Enquiry enq,HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		Integer cid = (Integer)session.getAttribute("cid");
		List<Enquiry> enquiry = enqService.getEnquiry(enq, cid);
		model.addAttribute("enqs",enquiry);
		return "display";
	}
	@GetMapping("/update")
	public String UpdateEnquiry(@RequestParam("id")Integer eid,Model model) {
		Enquiry enquiry = enqService.updateEnquiry(eid);
		model.addAttribute("enq",enquiry);
		return "Enquiry";
		}
	}
