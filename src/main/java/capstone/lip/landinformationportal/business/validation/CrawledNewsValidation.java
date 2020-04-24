package capstone.lip.landinformationportal.business.validation;

import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Pattern;

import capstone.lip.landinformationportal.common.constant.StatusCrawledNewsConstant;
import capstone.lip.landinformationportal.common.entity.CrawledNews;

public class CrawledNewsValidation {

	public String isValidCrawledNews(List<CrawledNews> listNews) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		if(listNews == null || (listNews != null && listNews.isEmpty()) ) {
			return "List news can not be null or empty";
		}
		for (CrawledNews news : listNews) {
			if (time.getTime() < news.getCrawledNewsTime().getTime()) {
				return "Can't insert future time";
			}
			if (news.getCrawledNewsLink() == null
					|| (news.getCrawledNewsLink() != null && news.getCrawledNewsLink().trim().isEmpty())) {
				return "Link of news can not be null or empty";
			}
			if (news.getCrawledNewsWebsite() == null
					|| (news.getCrawledNewsWebsite() != null && news.getCrawledNewsWebsite().trim().isEmpty())) {
				return "Website of crawled news can not be null or empty";
			}
			if (news.getCrawledNewsStatus().equals(StatusCrawledNewsConstant.DISPLAY)
					|| news.getCrawledNewsStatus().equals(StatusCrawledNewsConstant.NON_DISPLAY)) {

			} else {
				return "News status muse be display or non display";
			}
		}
		return "";
	}

	public String isValidCrawledNewsOne(CrawledNews news) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		if(news.getCrawledNewsID() <= 0) {
			return "News ID can not be negative or equal zero";
		}
		if (time.getTime() < news.getCrawledNewsTime().getTime()) {
			return "Can't insert future time";
		}
		if (news.getCrawledNewsLink() == null
				|| (news.getCrawledNewsLink() != null && news.getCrawledNewsLink().trim().isEmpty())) {
			return "Link of news can not be null or empty";
		}
		if (news.getCrawledNewsWebsite() == null
				|| (news.getCrawledNewsWebsite() != null && news.getCrawledNewsWebsite().trim().isEmpty())) {
			return "Website of crawled news can not be null or empty";
		}
		if (news.getCrawledNewsStatus().equals(StatusCrawledNewsConstant.DISPLAY)
				|| news.getCrawledNewsStatus().equals(StatusCrawledNewsConstant.NON_DISPLAY)) {

		} else {
			return "News status muse be display or non display";
		}
		return "";
	}
}
