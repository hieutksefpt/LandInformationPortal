package capstone.lip.landinformationportal.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Province")
public class Province extends AuditAbstract implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7177630011940526932L;

	@Id
	@Column(name = "ProvinceID")
	private Long provinceId;
	@Column(name = "ProvinceName")
	private String provinceName;
	@Column(name = "ProvinceLat")
	private Double provinceLat;
	@Column(name = "ProvinceLng")
	private Double provinceLng;
	public Long getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public Double getProvinceLat() {
		return provinceLat;
	}
	public void setProvinceLat(Double provinceLat) {
		this.provinceLat = provinceLat;
	}
	public Double getProvinceLng() {
		return provinceLng;
	}
	public void setProvinceLng(Double provinceLng) {
		this.provinceLng = provinceLng;
	}
}
