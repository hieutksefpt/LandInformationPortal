/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.repository;

import capstone.lip.landinformationportal.entity.RealEstateAdjacentSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface RealEstateAdjacentSegmentRepository extends JpaRepository<RealEstateAdjacentSegment, Long>{
    RealEstateAdjacentSegment findByIdSegmentIdAndRealEstateId (Long segmentId, Long RealEstateId);
}
