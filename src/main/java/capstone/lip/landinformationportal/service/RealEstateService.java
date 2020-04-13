/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.dto.GroupByDateMaxMinCreate;
import capstone.lip.landinformationportal.dto.MaxMinAvg;
import capstone.lip.landinformationportal.entity.House;
import capstone.lip.landinformationportal.entity.HousesDetail;
import capstone.lip.landinformationportal.entity.Land;
import capstone.lip.landinformationportal.entity.LandsDetail;
import capstone.lip.landinformationportal.entity.RealEstate;
import capstone.lip.landinformationportal.entity.RealEstateAdjacentSegment;
import capstone.lip.landinformationportal.entity.User;
import capstone.lip.landinformationportal.repository.RealEstateRepository;
import capstone.lip.landinformationportal.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.specification.RealEstateSpecifications;
import capstone.lip.landinformationportal.specification.SearchCriteria;
import capstone.lip.landinformationportal.validation.RealEstateValidation;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public RealEstate validateInfor(String realEstateName, Double realEstateLat, Double realEstateLng, String realEstateAddress, BigDecimal realEstatePrice,String realEstateStatus, User tempUser) {
        RealEstateValidation rev = new RealEstateValidation();
        RealEstate newUploadRealEstate = new RealEstate().setRealEstateName(realEstateName)
                .setRealEstateLat(realEstateLat).setRealEstateLng(realEstateLng)
                .setRealEstateAddress(realEstateAddress);
        newUploadRealEstate.setRealEstatePrice(realEstatePrice);
        newUploadRealEstate.setRealEstateStatus(realEstateStatus).setRealEstateSource("CONTRIBUTOR").setUser(tempUser);
        if(rev.checkRealEstateValidation(newUploadRealEstate).equals("AcceptRealEstate")){
//            return realEstateRepository.save(newUploadRealEstate);
              return newUploadRealEstate;
        }
        else return null;
    }

    @Override
    public void delete(List<RealEstate> listRealEstate) {
        realEstateRepository.deleteInBatch(listRealEstate);
    }

    // Hàm này get List Land theo ID của Real Estate
    @Override
    public Land getLand(Long realEstateId) {
        RealEstate realEstate = realEstateRepository.findById(realEstateId).get();
        Land land = realEstate.getLand();
        return land;
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

    @Override
    public RealEstate findById(long realEstateId) {
        Optional<RealEstate> re = realEstateRepository.findById(realEstateId);
        if (re.isPresent()) {
            return realEstateRepository.findById(realEstateId).get();
        }
        return null;
    }

    @Override
    public List<RealEstate> findByRealEstateStatus(String status) {
        return realEstateRepository.findByRealEstateStatus(status);
    }

    @Override
    public List<String> listRealEstateSource() {
        return realEstateRepository.listRealEstateSource();
    }

    @Override
    public Page<RealEstate> findByRealEstateStatus(String status, Pageable page) {
        return realEstateRepository.findByRealEstateStatus(status, page);
    }

    @Override
    public long count() {
        return realEstateRepository.count();
    }

    @Override
    public List<RealEstate> listFilterRealEstate(String realEstateName, String realEstateSource, String realEstateStatus) {
        List<RealEstateSpecifications> listSpec = new ArrayList<>();
        if (realEstateName != null) {
            listSpec.add(new RealEstateSpecifications(new SearchCriteria("realEstateName", ":", realEstateName)));
        }
        if (realEstateSource != null) {
            listSpec.add(new RealEstateSpecifications(new SearchCriteria("realEstateSource", ":=", realEstateSource)));
        }
        if (realEstateStatus != null) {
            listSpec.add(new RealEstateSpecifications(new SearchCriteria("realEstateStatus", ":=", realEstateStatus)));
        }
        switch (listSpec.size()) {
            case 1:
                return realEstateRepository.findAll(Specification.where(listSpec.get(0)));
            case 2:
                return realEstateRepository.findAll(Specification.where(listSpec.get(0).and(listSpec.get(1))));
            case 3:
                return realEstateRepository.findAll(Specification.where(listSpec.get(0).and(listSpec.get(1).and(listSpec.get(2)))));
            default:
                return realEstateRepository.findAll();
        }
    }

    @Override
    public long countByRealEstateStatus(String status) {
        return realEstateRepository.countByRealEstateStatus(status);
    }

    @Override
    public Page<RealEstate> listFilterRealEstateByAddress(String realEstateAddress, Pageable page) {
        RealEstateSpecifications spec1 = new RealEstateSpecifications(new SearchCriteria("realEstateName", ":", realEstateAddress));
        RealEstateSpecifications spec2 = new RealEstateSpecifications(new SearchCriteria("realEstateAddress", ":", realEstateAddress));
        return realEstateRepository.findAll(Specification.where(spec1).or(spec2), page);
    }

    @Override
    public long countByRealEstateSource(String realEstateAddress, String realEstateSource) {
        RealEstateSpecifications spec1 = new RealEstateSpecifications(new SearchCriteria("realEstateName", ":", realEstateAddress));
        RealEstateSpecifications spec2 = new RealEstateSpecifications(new SearchCriteria("realEstateAddress", ":", realEstateAddress));
        RealEstateSpecifications spec3 = new RealEstateSpecifications(new SearchCriteria("realEstateSource", ":=", realEstateSource));
        return realEstateRepository.count(Specification.where(Specification.where(spec1).or(spec2)).and(spec3));
    }

    @Override
    public Page<RealEstate> findByRealEstateSource(String source, Pageable page) {
        return realEstateRepository.findByRealEstateSource(source, page);
    }

    @Override
    public Page<RealEstate> findByRealEstateSourceNot(String source, Pageable page) {
        return realEstateRepository.findByRealEstateSourceNot(source, page);
    }

    @Override
    public Page<RealEstate> findAll(Pageable page) {
        return realEstateRepository.findAll(page);
    }

    @Override
    public long countByRealEstateSourceNot(String realEstateAddress, String realEstateSource) {
        RealEstateSpecifications spec1 = new RealEstateSpecifications(new SearchCriteria("realEstateName", ":", realEstateAddress));
        RealEstateSpecifications spec2 = new RealEstateSpecifications(new SearchCriteria("realEstateAddress", ":", realEstateAddress));
        RealEstateSpecifications spec3 = new RealEstateSpecifications(new SearchCriteria("realEstateSource", "!=", realEstateSource));
        return realEstateRepository.count(Specification.where(Specification.where(spec1).or(spec2)).and(spec3));
    }

    @Override
    public Page<RealEstate> listFilterRealEstateByAddressAndSource(String realEstateAddress, String realEstateSource, Pageable page) {
        RealEstateSpecifications spec1 = new RealEstateSpecifications(new SearchCriteria("realEstateName", ":", realEstateAddress));
        RealEstateSpecifications spec2 = new RealEstateSpecifications(new SearchCriteria("realEstateAddress", ":", realEstateAddress));
        RealEstateSpecifications spec3 = new RealEstateSpecifications(new SearchCriteria("realEstateSource", ":=", realEstateSource));
        return realEstateRepository.findAll(Specification.where(Specification.where(spec1).or(spec2)).and(spec3), page);
    }

    @Override
    public Page<RealEstate> listFilterRealEstateByAddressAndSourceNot(String realEstateAddress, String realEstateSource, Pageable page) {
        RealEstateSpecifications spec1 = new RealEstateSpecifications(new SearchCriteria("realEstateName", ":", realEstateAddress));
        RealEstateSpecifications spec2 = new RealEstateSpecifications(new SearchCriteria("realEstateAddress", ":", realEstateAddress));
        RealEstateSpecifications spec3 = new RealEstateSpecifications(new SearchCriteria("realEstateSource", "!=", realEstateSource));
        return realEstateRepository.findAll(Specification.where(Specification.where(spec1).or(spec2)).and(spec3), page);
    }

    @Override
    public long countByRealEstateAddress(String address) {
        RealEstateSpecifications spec1 = new RealEstateSpecifications(new SearchCriteria("realEstateName", ":", address));
        RealEstateSpecifications spec2 = new RealEstateSpecifications(new SearchCriteria("realEstateAddress", ":", address));
        return realEstateRepository.count(Specification.where(spec1).or(spec2));
    }

    @Override
    public MaxMinAvg listMaxMinAvg(String address) {
        return realEstateRepository.getMaxMinAvg(address);
    }

    @Override
    public List<GroupByDateMaxMinCreate> listGroupByDateAndValue(String address) {
        return realEstateRepository.getGroupTimeAndPrice(address);
    }

    @Autowired
    LandsDetailService landsDetailService;

    @Autowired
    LandService landService;

    @Autowired
    HousesDetailService housesDetailService;

    @Autowired
    HouseService houseService;

    @Autowired
    RealEstateAdjacentSegmentService realEstateAdjacentSegmentService;

    @Override
    public void delete(long realEstateId) {
        RealEstate realEstate = realEstateRepository.findById(realEstateId).get();
        Land land = realEstate.getLand();
        if (land != null) {
            List<LandsDetail> listLandDetail = realEstate.getLand().getListLandsDetail();
            if (listLandDetail != null) {
                landsDetailService.delete(listLandDetail);
            }
            landService.delete(land);
        }
        List<House> listHouse = realEstate.getListHouse();
        if (listHouse != null) {
            for (House house : listHouse) {
                List<HousesDetail> listHousesDetails = house.getListHousesDetail();
                if (listHousesDetails != null) {
                    housesDetailService.delete(listHousesDetails);
                }
                houseService.delete(listHouse);
            }
        }
        List<RealEstateAdjacentSegment> listAdjacentSegment = realEstate.getListRealEstateAdjacentSegment();
        if (listAdjacentSegment != null) {
            realEstateAdjacentSegmentService.delete(listAdjacentSegment);
        }
        realEstateRepository.deleteById(realEstateId);
        int i=1;
        i++;
    }
}
