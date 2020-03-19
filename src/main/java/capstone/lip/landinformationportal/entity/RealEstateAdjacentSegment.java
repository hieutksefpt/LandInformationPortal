/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="RealEstateAdjacentSegment")
public class RealEstateAdjacentSegment extends AuditAbstract implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RealEstateAdjacentSegmentId id;
    
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "SegmentID")
    @MapsId(value="SegmentID")
    private SegmentOfStreet segmentOfStreet;
    
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "RealEstateID")
    @MapsId(value="RealEstateID")
    private RealEstate realEstate;

    public RealEstateAdjacentSegment() {
    }

    public SegmentOfStreet getSegmentOfStreet() {
        return segmentOfStreet;
    }

    public RealEstateAdjacentSegment setSegmentOfStreet(SegmentOfStreet segmentOfStreet) {
        this.segmentOfStreet = segmentOfStreet;
        return this;
    }

    public RealEstate getRealEstate() {
        return realEstate;
    }

    public RealEstateAdjacentSegment setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;
        return this;
    }

	public RealEstateAdjacentSegmentId getId() {
		return id;
	}

	public RealEstateAdjacentSegment setId(RealEstateAdjacentSegmentId id) {
		this.id = id;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((realEstate == null) ? 0 : realEstate.hashCode());
		result = prime * result + ((segmentOfStreet == null) ? 0 : segmentOfStreet.hashCode());
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
		RealEstateAdjacentSegment other = (RealEstateAdjacentSegment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (realEstate == null) {
			if (other.realEstate != null)
				return false;
		} else if (!realEstate.equals(other.realEstate))
			return false;
		if (segmentOfStreet == null) {
			if (other.segmentOfStreet != null)
				return false;
		} else if (!segmentOfStreet.equals(other.segmentOfStreet))
			return false;
		return true;
	}
    
}
