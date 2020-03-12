/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.repository.HouseRepository;
import capstone.lip.landinformationportal.repository.RealEstateRepository;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class RealEstateService implements IRealEstateService {

    @Autowired
    private RealEstateRepository realEstateRepository;

    @Override
    public List<RealEstate> findAll() {
        return realEstateRepository.findAll();
    }

    @Override
    public RealEstate save(RealEstate realEstate) {
        return realEstateRepository.save(realEstate);
    }

    @Override
    public void delete(List<RealEstate> listRealEstate) {
        realEstateRepository.deleteInBatch(listRealEstate);
    }

    // Hàm này get List Land theo ID của Real Estate
    @Override
    public List<Land> getListLand(Long realEstateId) {
        RealEstate realEstate = realEstateRepository.findById(realEstateId).get();
        List<Land> listLand = realEstate.getListLand();
        return listLand;
    }

    // Hàm này get List House theo ID của Real Estate
    @Override
    public List<House> getListHouse(Long realEstateId) {
        RealEstate realEstate = realEstateRepository.findById(realEstateId).get();
        List<House> listHouse = realEstate.getListHouse();
        return listHouse;
    }

    @Override
    public void delete(RealEstate realEstate) {
        realEstateRepository.delete(realEstate);
    }

}