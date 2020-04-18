/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.entity.LandsDetail;
import capstone.lip.landinformationportal.repository.LandsDetailRepository;
import capstone.lip.landinformationportal.service.Interface.ILandsDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class LandsDetailService implements ILandsDetailService {

    @Autowired
    private LandsDetailRepository landsDetailRepository;

    @Override
    public LandsDetail save(LandsDetail landsDetail) {
        try {
            return landsDetailRepository.save(landsDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(List<LandsDetail> listLandsDetail) {
    	try {
    		if (listLandsDetail == null) throw new Exception("null");
    		if (listLandsDetail.isEmpty()) throw new Exception("empty");
    		
    		landsDetailRepository.deleteAll(listLandsDetail);
    		return true;
    	}catch(Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }

}
