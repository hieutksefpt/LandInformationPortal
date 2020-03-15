/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.repository.HouseRepository;
import capstone.lip.landinformationportal.repository.RealEstateRepository;
import capstone.lip.landinformationportal.service.Interface.IHouseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class HouseService implements IHouseService{

    @Autowired
    private HouseRepository houseRepository;
    
    @Autowired
    private RealEstateRepository realEstateRepository;

    @Override
    public List<House> findAll() {
        return houseRepository.findAll();
    }

    @Override
    public House save(House house) {
        return houseRepository.save(house);
    }

    @Override
    public void delete(List<House> listHouse) {
        houseRepository.deleteInBatch(listHouse);
    }

    @Override
    public void deleteById(Long houseId) {
        houseRepository.deleteById(houseId);
    }

    @Override
    public List<House> findByRealEstateId(long realEstateId) {
        RealEstate realEstate = realEstateRepository.findById(realEstateId).get();
        return realEstate.getListHouse();
    }

    
}
