/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="RealEstateAdjacentSegment")
public class RealEstateAdjacentSegment extends AuditAbstract implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "SegmentID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SegmentOfStreet segmentOfStreet;
    
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "RealEstateID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RealEstate realEstate;

    public RealEstateAdjacentSegment() {
    }

    public SegmentOfStreet getSegmentOfStreet() {
        return segmentOfStreet;
    }

    public void setSegmentOfStreet(SegmentOfStreet segmentOfStreet) {
        this.segmentOfStreet = segmentOfStreet;
    }

    public RealEstate getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;
    }

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
    
    
}
