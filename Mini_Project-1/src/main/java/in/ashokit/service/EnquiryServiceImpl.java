package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.dto.Dashboard;
import in.ashokit.entity.Counsellor;
import in.ashokit.entity.Enquiry;
import in.ashokit.repo.CounsellorRepo;
import in.ashokit.repo.EnquiryRepo;

@Service
public class EnquiryServiceImpl implements EnquiryService{
	@Autowired
	private CounsellorRepo counRepo;
	@Autowired
	private EnquiryRepo enqRepo;

	@Override
	public Dashboard getDashboard(Integer id) {
		Long totalEnquiries = enqRepo.getEnquiries(id);
		Long openEnquiries = enqRepo.getEnquiries(id, "new");
		Long enrolledEnquiries = enqRepo.getEnquiries(id, "enroll");
		Long lostEnquiries = enqRepo.getEnquiries(id, "lost");
		
		Dashboard d=new Dashboard();
		d.setTotalEnquiries(totalEnquiries);
		d.setOpenEnquiries(openEnquiries);
		d.setEnrolledEnquiries(enrolledEnquiries);
		d.setLostEnquiries(lostEnquiries);
		return d;
	}

	@Override
	public boolean addEnquiry(Enquiry enquiry, Integer id) {
		
		Counsellor counsellor = counRepo.findById(id).orElseThrow();
		enquiry.setCounsellor(counsellor);
		Enquiry save = enqRepo.save(enquiry);
		return save.getEid()!=null;
	}

	@Override
	public List<Enquiry> getEnquiry(Enquiry enquiry, Integer id) {
//		Counsellor counsellor = counRepo.findById(id).orElseThrow();
		Counsellor counsellor=new Counsellor();
		counsellor.setCid(id);
		
		Enquiry searchCriteria=new Enquiry();
		searchCriteria.setCounsellor(counsellor);
		
		if(null!= enquiry.getCourse() && !"".equals(enquiry.getCourse())) {
			searchCriteria.setCourse(enquiry.getCourse());
		}
		if(null!= enquiry.getMode() && !"".equals(enquiry.getMode())) {
			searchCriteria.setMode(enquiry.getMode());
		}
		if(null!=enquiry.getStatus() && !"".equals(enquiry.getStatus())) {
			searchCriteria.setStatus(enquiry.getStatus());
		}
		
		
		Example<Enquiry> of = Example.of(searchCriteria);
		return enqRepo.findAll(of);
	}

	@Override
	public Enquiry updateEnquiry(Integer eid) {
		Optional<Enquiry> id = enqRepo.findById(eid);
		Enquiry enquiry=null;
		if(id.isPresent()) {
			enquiry= id.get();
		}
		return enquiry;
	}
	

}
