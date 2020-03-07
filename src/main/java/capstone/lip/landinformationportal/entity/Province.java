package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ProvinceID")
	private Long provinceId;
	@Column(name = "ProvinceName")
	private String provinceName;
	@Column(name = "ProvinceLat")
	private Double provinceLat;
	@Column(name = "ProvinceLng")
	private Double provinceLng;
	
	@Basic(fetch = FetchType.LAZY)
	@OneToMany(mappedBy="province")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<District> listDistrict;
	
	public List<District> getListDistrict() {
		return listDistrict;
	}
	public Province setListDistrict(List<District> listDistrict) {
		this.listDistrict = listDistrict;
		return this;
	}
	public Long getProvinceId() {
		return provinceId;
	}
	public Province setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
		return this;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public Province setProvinceName(String provinceName) {
		this.provinceName = provinceName;
		return this;
	}
	public Double getProvinceLat() {
		return provinceLat;
	}
	public Province setProvinceLat(Double provinceLat) {
		this.provinceLat = provinceLat;
		return this;
	}
	public Double getProvinceLng() {
		return provinceLng;
	}
	public Province setProvinceLng(Double provinceLng) {
		this.provinceLng = provinceLng;
		return this;
	}
}
