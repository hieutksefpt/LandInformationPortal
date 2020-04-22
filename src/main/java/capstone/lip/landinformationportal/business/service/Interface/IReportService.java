package capstone.lip.landinformationportal.business.service.Interface;

import capstone.lip.landinformationportal.common.entity.Report;

public interface IReportService {
    Report save(Report report);

    Report findById(Long userId, Long realEstateId);

    boolean delete(Report report);
}
