package capstone.lip.landinformationportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.lip.landinformationportal.entity.Report;
import capstone.lip.landinformationportal.repository.ReportRepository;
import capstone.lip.landinformationportal.service.Interface.IReportService;

@Service
public class ReportService implements IReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Report save(Report report) {
        try {
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
            reportRepository.delete(report);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
