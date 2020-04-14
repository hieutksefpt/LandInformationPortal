package capstone.lip.landinformationportal;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import capstone.lip.landinformationportal.dto.GroupByDateMaxMinCreate;
import capstone.lip.landinformationportal.dto.MaxMinAvg;
import capstone.lip.landinformationportal.entity.CrawledNews;
import capstone.lip.landinformationportal.entity.HousesFeature;
import capstone.lip.landinformationportal.entity.LandsFeature;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.Report;
import capstone.lip.landinformationportal.entity.ReportId;
import capstone.lip.landinformationportal.repository.HousesFeatureRepository;
import capstone.lip.landinformationportal.repository.LandsFeatureRepository;
import capstone.lip.landinformationportal.repository.RealEstateRepository;
import capstone.lip.landinformationportal.service.Interface.ICrawledNewsService;
import capstone.lip.landinformationportal.service.Interface.IReportService;
import capstone.lip.landinformationportal.specification.RealEstateSpecifications;
import capstone.lip.landinformationportal.specification.SearchCriteria;
import capstone.lip.landinformationportal.utils.EmailSender;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan
public class Application{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }
//    
//    
//    @Autowired
//	private ICrawledNewsService service;
//    
//    @Override
//    public void run(String... args) {
//    	CrawledNews news = new CrawledNews().setCrawledNewsImageUrl("tuan").setCrawledNewsLink("tuan").setCrawledNewsShortDescription("tuan").setCrawledNewsStatus("tua")
//    			.setCrawledNewsTitle("tuan").setCrawledNewsWebsite("tuan");
//    	
//    	news = service.findById(270l);
//    	news.setCrawledNewsTitle("123321");
//
//    	service.save(news);
//    }
}
