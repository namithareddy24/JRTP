package in.ashokit.service;

import java.util.List;

import in.ashokit.dto.Dashboard;
import in.ashokit.entity.Enquiry;

public interface EnquiryService {
	
	public Dashboard getDashboard(Integer id);
	
	public boolean addEnquiry(Enquiry enquiry, Integer id);
	
	public List<Enquiry> getEnquiry(Enquiry enquiry, Integer id);
	
	public Enquiry updateEnquiry(Integer eid);

	

}
