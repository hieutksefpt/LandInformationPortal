package capstone.lip.landinformationportal.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
@Table(name="SegmentOfStreet")
public class SegmentOfStreet extends AuditAbstract implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="SegmentID")
	private Long segmentId;
	@Column(name="SegmentName")
	private String segmentName;
	@Column(name="SegmentLat")
	private Double segmentLat;
	@Column(name="SegmentLng")
	private Double segmentLng;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name ="DistrictID", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private District district;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name ="StreetID", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Street street;
	

}
