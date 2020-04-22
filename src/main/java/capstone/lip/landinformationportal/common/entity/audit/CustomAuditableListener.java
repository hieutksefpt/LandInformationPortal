package capstone.lip.landinformationportal.common.entity.audit;

import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class CustomAuditableListener {
	@PrePersist
	void preCreate(CustomAuditModify auditable) {
		Timestamp now = Timestamp.from(Instant.now());
		if (auditable.getCreatedDate() == null) {
			auditable.setCreateDate(now);
		}
		auditable.setModifiedDate(now);
	}

	@PreUpdate
	void preUpdate(CustomAuditModify auditable) {
		Timestamp now = Timestamp.from(Instant.now());
		auditable.setModifiedDate(now);
	}
}
