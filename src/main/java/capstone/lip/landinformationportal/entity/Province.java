package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import capstone.lip.landinformationportal.entity.audit.AuditAbstract;

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
	
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@OneToMany(mappedBy="province", fetch = FetchType.LAZY)
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((listDistrict == null) ? 0 : listDistrict.hashCode());
		result = prime * result + ((provinceId == null) ? 0 : provinceId.hashCode());
		result = prime * result + ((provinceLat == null) ? 0 : provinceLat.hashCode());
		result = prime * result + ((provinceLng == null) ? 0 : provinceLng.hashCode());
		result = prime * result + ((provinceName == null) ? 0 : provinceName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Province other = (Province) obj;
		if (listDistrict == null) {
			if (other.listDistrict != null)
				return false;
		} else if (!listDistrict.equals(other.listDistrict))
			return false;
		if (provinceId == null) {
			if (other.provinceId != null)
				return false;
		} else if (!provinceId.equals(other.provinceId))
			return false;
		if (provinceLat == null) {
			if (other.provinceLat != null)
				return false;
		} else if (!provinceLat.equals(other.provinceLat))
			return false;
		if (provinceLng == null) {
			if (other.provinceLng != null)
				return false;
		} else if (!provinceLng.equals(other.provinceLng))
			return false;
		if (provinceName == null) {
			if (other.provinceName != null)
				return false;
		} else if (!provinceName.equals(other.provinceName))
			return false;
		return true;
	}
	
}
