package capstone.lip.landinformationportal.config;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.domain.Auditable;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import capstone.lip.landinformationportal.entity.CrawledNews;

@Configurable
public class CustomAuditConfig {

	private @Nullable ObjectFactory<AuditingHandler> handler;

	/**
	 * Configures the {@link AuditingHandler} to be used to set the current auditor on the domain types touched.
	 *
	 * @param auditingHandler must not be {@literal null}.
	 */
	public void setAuditingHandler(ObjectFactory<AuditingHandler> auditingHandler) {

		Assert.notNull(auditingHandler, "AuditingHandler must not be null!");
		this.handler = auditingHandler;
	}

	/**
	 * Sets modification and creation date and auditor on the target object in case it implements {@link Auditable} on
	 * persist events.
	 *
	 * @param target
	 */
	@PrePersist
	public void touchForCreate(Object target) {

		Assert.notNull(target, "Entity must not be null!");

		if (handler != null) {
			
			AuditingHandler object = handler.getObject();
			
			if (target instanceof CrawledNews) {
				CrawledNews temp = (CrawledNews)target;
				if (temp.getCreatedDate() != null && temp.getModifiedDate() != null && temp.getCreatedDate().equals(temp.getModifiedDate())) {
					Date date = new Date();
					temp.setModifiedDate(new Timestamp(date.getTime()));
					return;
				}
			}
			
			if (object != null) {
				object.markCreated(target);
			}
		}
	}

	/**
	 * Sets modification and creation date and auditor on the target object in case it implements {@link Auditable} on
	 * update events.
	 *
	 * @param target
	 */
	@PreUpdate
	public void touchForUpdate(Object target) {

		Assert.notNull(target, "Entity must not be null!");

		if (handler != null) {

			AuditingHandler object = handler.getObject();
			if (object != null) {
				object.markModified(target);
			}
		}
	}
}
