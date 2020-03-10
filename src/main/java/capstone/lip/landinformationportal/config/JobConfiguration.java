package capstone.lip.landinformationportal.config;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import capstone.lip.landinformationportal.bean.ManageCrawlRealEstateBean;

@Configuration
public class JobConfiguration {
	@Bean
	public JobDetail printRandomJobDetail() {
	    return JobBuilder
	            .newJob(ManageCrawlRealEstateBean.class)
	            .withIdentity(JobKey.jobKey("printRandom"))
	            .storeDurably()
	            .build();
	}
	
	@Bean
	public Trigger printRandomJobTrigger() {
	    return TriggerBuilder
	            .newTrigger()
	            .forJob(printRandomJobDetail())
	            .withIdentity(TriggerKey.triggerKey("printRandom"))
	            .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
	            .build();
	}
}
