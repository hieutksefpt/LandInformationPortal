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
    public List<LandsDetail> findAll() {
        try {
            return landsDetailRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

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
            landsDetailRepository.deleteInBatch(listLandsDetail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    // not work with composite key
    @Override
    public boolean deleteById(Long landsDetailId) {
        try {
            landsDetailRepository.deleteById(landsDetailId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

}
