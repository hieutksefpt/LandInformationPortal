package capstone.lip.landinformationportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.entity.Report;
import capstone.lip.landinformationportal.repository.RealEstateRepository;
import capstone.lip.landinformationportal.repository.ReportRepository;
import capstone.lip.landinformationportal.repository.UserRepository;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.service.Interface.IReportService;
import capstone.lip.landinformationportal.service.Interface.IUserService;
import capstone.lip.landinformationportal.validation.ReportValidate;

@Service
public class ReportService implements IReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private IUserService userService;
    
    @Autowired
    private IRealEstateService realEstateService;
    
    @Override
    public Report save(Report report) {
        try {
        	ReportValidate validate = new ReportValidate();
        	String error = validate.isValidReport(report);
        	if (!error.isEmpty()) {
        		throw new Exception(error);
        	}
        	if (userService.findById(report.getId().getUserId()) == null) {
        		throw new Exception("User not found");
        	};
        	if (realEstateService.findById(report.getId().getRealestateId()) == null){
        		throw new Exception("Realestate not found");
        	}
        	
        	
            return reportRepository.save(report);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Report findById(Long userId, Long realEstateId) {
        try {
            return reportRepository.findByIdUserIdAndIdRealEstateId(userId, realEstateId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Report report) {
        try {
        	ReportValidate validate = new ReportValidate();
        	String error = validate.isValidReport(report);
        	if (!error.isEmpty()) {
        		throw new Exception(error);
        	}
        	if (findById(report.getId().getRealestateId(), report.getId().getUserId())==null) {
        		throw new Exception("Id not found");
        	}
            reportRepository.delete(report);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
