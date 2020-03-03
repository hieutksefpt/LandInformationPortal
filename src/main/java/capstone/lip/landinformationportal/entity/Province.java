package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Province")
//@NamedEntityGraph(name="graph.province.district",
//		attributeNodes= @NamedAttributeNode(value="district", subgraph="district"))
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
	
	@OneToMany(mappedBy="province",cascade = CascadeType.ALL,orphanRemoval = true, fetch=FetchType.LAZY)
	private List<District> listDistrict;
	
	public List<District> getListDistrict() {
		return listDistrict;
	}
	public void setListDistrict(List<District> listDistrict) {
		this.listDistrict = listDistrict;
	}
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
