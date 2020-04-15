package capstone.lip.landinformationportal.service.Interface;

import capstone.lip.landinformationportal.entity.Report;
import capstone.lip.landinformationportal.entity.ReportId;

public interface IReportService {
    Report save(Report report);

    Report findById(Long userId, Long realEstateId);

    boolean delete(Report report);
}
