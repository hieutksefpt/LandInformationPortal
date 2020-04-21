package capstone.lip.landinformationportal.validation;

import capstone.lip.landinformationportal.entity.Report;

public class ReportValidation {
	public String isValidReport(Report report) {
		if (report == null) {
			return "Report is null";
		}
		if (report.getId()==null) {
			return "Id is null";
		}
		if (report.getId().getRealestateId()==null) {
			return "Id realestate is null";
		}
		if (report.getId().getUserId()==null) {
			return "Id user is null";
		}
		return "";
	}
}
