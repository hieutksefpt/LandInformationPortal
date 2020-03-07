/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.Interface;

import capstone.lip.landinformationportal.entity.District;
import capstone.lip.landinformationportal.entity.LandsFeature;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ILandsFeatureService {

    public List<LandsFeature> findAll();

    public LandsFeature save(LandsFeature landsfeature);
    
    public void delete(Long landsfeatureID);
}
