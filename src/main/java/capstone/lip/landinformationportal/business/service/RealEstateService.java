/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.service;

import capstone.lip.landinformationportal.business.repository.RealEstateRepository;
import capstone.lip.landinformationportal.business.service.Interface.IHouseService;
import capstone.lip.landinformationportal.business.service.Interface.ILandService;
import capstone.lip.landinformationportal.business.service.Interface.IRealEstateAdjacentSegmentService;
import capstone.lip.landinformationportal.business.service.Interface.IRealEstateService;
import capstone.lip.landinformationportal.business.service.Interface.IReportService;
import capstone.lip.landinformationportal.business.specification.RealEstateSpecifications;
import capstone.lip.landinformationportal.business.specification.SearchCriteria;
import capstone.lip.landinformationportal.business.validation.RealEstateValidation;
import capstone.lip.landinformationportal.common.dto.GroupByDateMaxMin;
import capstone.lip.landinformationportal.common.dto.MaxMinAvg;
import capstone.lip.landinformationportal.common.entity.House;
import capstone.lip.landinformationportal.common.entity.Land;
import capstone.lip.landinformationportal.common.entity.RealEstate;
import capstone.lip.landinformationportal.common.entity.Report;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    public RealEstate save(RealEstate realEstate) {
        try {
            RealEstateValidation validate = new RealEstateValidation();
            String error = validate.isRealEstateValid(realEstate);
            if (!error.isEmpty()) {
                throw new Exception(error);
            }
            return realEstateRepository.save(realEstate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // Hàm này get List Land theo ID của Real Estate
    @Override
    public Land getLand(Long realEstateId) {
        try {
            RealEstate realEstate = realEstateRepository.findById(realEstateId).get();
            Land land = realEstate.getLand();
            return land;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    // Hàm này get List House theo ID của Real Estate
    @Override
    public List<House> getListHouse(Long realEstateId) {
        try {
            RealEstate realEstate = realEstateRepository.findById(realEstateId).get();
            List<House> listHouse = realEstate.getListHouse();
            return listHouse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public RealEstate findById(long realEstateId) {
        try {
            Optional<RealEstate> re = realEstateRepository.findById(realEstateId);
            if (re.isPresent()) {
                return realEstateRepository.findById(realEstateId).get();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<String> listRealEstateSource() {
        try {
            return realEstateRepository.listRealEstateSource();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Page<RealEstate> findByRealEstateStatus(String status, Pageable page) {
        try {
            Page<RealEstate> tempPage = realEstateRepository.findByRealEstateStatus(status, page);
            List<RealEstate> temp = new ArrayList<>();
            temp = tempPage.stream().map(x -> x).collect(Collectors.toList());
            if (temp.isEmpty()) {
                throw new Exception();
            } else {
                return tempPage;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public long count() {
        try {
            return realEstateRepository.count();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public List<RealEstate> listFilterRealEstate(String realEstateName, String realEstateSource, String realEstateStatus) {
        try {
            List<RealEstateSpecifications> listSpec = new ArrayList<>();
            if (realEstateName != null && !realEstateName.isEmpty()) {
                listSpec.add(new RealEstateSpecifications(new SearchCriteria("realEstateName", ":", realEstateName)));
            } else if (realEstateName.isEmpty() || realEstateName == null) {
                throw new Exception();
            }
            if (realEstateSource != null && !realEstateSource.isEmpty()) {
                listSpec.add(new RealEstateSpecifications(new SearchCriteria("realEstateSource", ":=", realEstateSource)));
            } else if (realEstateSource.isEmpty() || realEstateSource == null) {
                throw new Exception();
            }
            if (realEstateStatus != null && !realEstateStatus.isEmpty()) {
                listSpec.add(new RealEstateSpecifications(new SearchCriteria("realEstateStatus", ":=", realEstateStatus)));
            } else if (realEstateStatus.isEmpty() || realEstateStatus == null) {
                throw new Exception();
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public long countByRealEstateStatus(String status) {
        try {
            return realEstateRepository.countByRealEstateStatus(status);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public Page<RealEstate> listFilterRealEstateByAddress(String realEstateAddress, Pageable page) {
        try {
            if(realEstateAddress.isEmpty() || realEstateAddress == null){
                throw new Exception("List data is empty");
            }
            RealEstateSpecifications spec1 = new RealEstateSpecifications(new SearchCriteria("realEstateName", ":", realEstateAddress));
            RealEstateSpecifications spec2 = new RealEstateSpecifications(new SearchCriteria("realEstateAddress", ":", realEstateAddress));
            Page<RealEstate> tempPage = realEstateRepository.findAll(Specification.where(spec1).or(spec2), page);
            List<RealEstate> listTemp = tempPage.stream().map(x -> x).collect(Collectors.toList());
            if(listTemp.isEmpty() || listTemp == null){
               throw new Exception("List data is empty");
            }
            else 
                return tempPage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public long countByRealEstateSource(String realEstateAddress, String realEstateSource) {
        try {
            if (realEstateSource.isEmpty() || realEstateAddress.isEmpty()) {
                throw new Exception("List data is empty");
            }
            RealEstateSpecifications spec1 = new RealEstateSpecifications(new SearchCriteria("realEstateName", ":", realEstateAddress));
            RealEstateSpecifications spec2 = new RealEstateSpecifications(new SearchCriteria("realEstateAddress", ":", realEstateAddress));
            RealEstateSpecifications spec3 = new RealEstateSpecifications(new SearchCriteria("realEstateSource", ":=", realEstateSource));
            return realEstateRepository.count(Specification.where(Specification.where(spec1).or(spec2)).and(spec3));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public Page<RealEstate> findAll(Pageable page) {
        try {
            Page<RealEstate> tempPage = realEstateRepository.findAll(page);
            List<RealEstate> listTemp = tempPage.stream().map(x -> x).collect(Collectors.toList());
            if(listTemp.isEmpty() || listTemp == null){
               throw new Exception("List data is empty");
            }
            else 
                return tempPage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public long countByRealEstateSourceNot(String realEstateAddress, String realEstateSource) {
        try {
            RealEstateSpecifications spec1 = new RealEstateSpecifications(new SearchCriteria("realEstateName", ":", realEstateAddress));
            RealEstateSpecifications spec2 = new RealEstateSpecifications(new SearchCriteria("realEstateAddress", ":", realEstateAddress));
            RealEstateSpecifications spec3 = new RealEstateSpecifications(new SearchCriteria("realEstateSource", "!=", realEstateSource));
            return realEstateRepository.count(Specification.where(Specification.where(spec1).or(spec2)).and(spec3));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public Page<RealEstate> listFilterRealEstateByAddressAndSource(String realEstateAddress, String realEstateSource, Pageable page) {
        try {
            if(realEstateAddress.isEmpty() || realEstateAddress == null){
                throw new Exception();
            }
            if(realEstateSource.isEmpty() || realEstateAddress == null){
                throw new Exception();
            }
            RealEstateSpecifications spec1 = new RealEstateSpecifications(new SearchCriteria("realEstateName", ":", realEstateAddress));
            RealEstateSpecifications spec2 = new RealEstateSpecifications(new SearchCriteria("realEstateAddress", ":", realEstateAddress));
            RealEstateSpecifications spec3 = new RealEstateSpecifications(new SearchCriteria("realEstateSource", ":=", realEstateSource));
            Page<RealEstate> tempPage = realEstateRepository.findAll(Specification.where(Specification.where(spec1).or(spec2)).and(spec3), page);
            List<RealEstate> listTemp = tempPage.stream().map(x -> x).collect(Collectors.toList());
            if(listTemp.isEmpty() || listTemp == null){
               throw new Exception("list data is empty");
            }
            else 
                return tempPage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Page<RealEstate> listFilterRealEstateByAddressAndSourceNot(String realEstateAddress, String realEstateSource, Pageable page) {
        try {
            if(realEstateAddress.isEmpty()){
                throw new Exception();
            }
            if(realEstateSource.isEmpty()){
                throw new Exception();
            }
            RealEstateSpecifications spec1 = new RealEstateSpecifications(new SearchCriteria("realEstateName", ":", realEstateAddress));
            RealEstateSpecifications spec2 = new RealEstateSpecifications(new SearchCriteria("realEstateAddress", ":", realEstateAddress));
            RealEstateSpecifications spec3 = new RealEstateSpecifications(new SearchCriteria("realEstateSource", "!=", realEstateSource));
            Page<RealEstate> tempPage = realEstateRepository.findAll(Specification.where(Specification.where(spec1).or(spec2)).and(spec3), page);
            List<RealEstate> listTemp = tempPage.stream().map(x -> x).collect(Collectors.toList());
            if(listTemp.isEmpty() || listTemp == null){
               throw new Exception();
            }
            else 
                return tempPage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public long countByRealEstateAddress(String address) {
        try {
            if (address.trim().isEmpty() || address.trim() == null) {
                throw new Exception();
            }
            RealEstateSpecifications spec1 = new RealEstateSpecifications(new SearchCriteria("realEstateName", ":", address));
            RealEstateSpecifications spec2 = new RealEstateSpecifications(new SearchCriteria("realEstateAddress", ":", address));
            return realEstateRepository.count(Specification.where(spec1).or(spec2));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

//    @Cacheable(value="listMaxMinAvg", key= "{#location}", cacheManager="cacheManager1Hour")
    @Override
    public MaxMinAvg listMaxMinAvg(String location) {
        try {
            if(location.isEmpty() || location == null){
                throw new Exception();
            }
            
            return realEstateRepository.getMaxMinAvg(location);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

//    @Cacheable(value="listGroupByDateAndValue", key= "{#location}", cacheManager="cacheManager1Hour")
    @Override
    public List<GroupByDateMaxMin> listGroupByDateAndValue(String location) {
        try {
            if (location.isEmpty() || location == null) {
                throw new Exception();
            }
            return realEstateRepository.getGroupTimeAndPrice(location);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Autowired
    private ILandService landService;

    @Autowired
    private IHouseService houseService;

    @Autowired
    private IRealEstateAdjacentSegmentService realEstateAdjacentSegmentService;

    @Autowired
    private IReportService reportService;

    @Override
    public boolean delete(long realEstateId) {
        try {
            RealEstate realEstate = realEstateRepository.findById(realEstateId).get();
//            Land land = realEstate.getLand();
//            if (land != null) {
//                List<LandsDetail> listLandDetail = realEstate.getLand().getListLandsDetail();
//                if (listLandDetail != null) {
//                    landsDetailService.delete(listLandDetail);
//                }
//                landService.delete(land);
//            }
//            List<House> listHouse = realEstate.getListHouse();
//            if (listHouse != null) {
//                for (House house : listHouse) {
//                    List<HousesDetail> listHousesDetails = house.getListHousesDetail();
//                    if (listHousesDetails != null) {
//                        housesDetailService.delete(listHousesDetails);
//                    }
//                    houseService.delete(listHouse);
//                }
//            }
//            List<RealEstateAdjacentSegment> listAdjacentSegment = realEstate.getListRealEstateAdjacentSegment();
//            if (listAdjacentSegment != null) {
//                realEstateAdjacentSegmentService.delete(listAdjacentSegment);
//            }
            houseService.delete(realEstate.getListHouse());
            landService.delete(realEstate.getLand());
            realEstateAdjacentSegmentService.delete(realEstate.getListRealEstateAdjacentSegment());

            List<Report> listReport = realEstate.getListReport();
            for (Report element : listReport) {
                reportService.delete(element);
            }
            realEstateRepository.deleteById(realEstateId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<RealEstate> findByRealEstateLatAndRealEstateLng(Double realEstateLat, Double realEstateLng) {
        try {
            return realEstateRepository.findByRealEstateLatAndRealEstateLng(realEstateLat, realEstateLng);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	@Override
	public Page<RealEstate> findAllBySourceAndStatus(String source, String status, Pageable page) {
		try {
			List<RealEstateSpecifications> listSpec = new ArrayList();
			if (source!= null) {
				RealEstateSpecifications spec = new RealEstateSpecifications(new SearchCriteria("realEstateSource", ":=", source));
				listSpec.add(spec);
			}
			if (status != null) {
				RealEstateSpecifications spec = new RealEstateSpecifications(new SearchCriteria("realEstateStatus", ":=", status));
				listSpec.add(spec);
			}
            if (listSpec.size() == 1) {
            	return realEstateRepository.findAll(Specification.where(listSpec.get(0)), page);
            }
            if (listSpec.size() == 2) {
            	return realEstateRepository.findAll(Specification.where(listSpec.get(0)).and(listSpec.get(1)), page); 
            }
			return realEstateRepository.findAll(page);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long countBySourceAndStatus(String source, String status) {
		try {
			List<RealEstateSpecifications> listSpec = new ArrayList();
			if (source!= null) {
				RealEstateSpecifications spec = new RealEstateSpecifications(new SearchCriteria("realEstateSource", ":=", source));
				listSpec.add(spec);
			}
			if (status != null) {
				RealEstateSpecifications spec = new RealEstateSpecifications(new SearchCriteria("realEstateStatus", ":=", status));
				listSpec.add(spec);
			}
            if (listSpec.size() == 1) {
            	return realEstateRepository.count(Specification.where(listSpec.get(0)));
            }
            if (listSpec.size() == 2) {
            	return realEstateRepository.count(Specification.where(listSpec.get(0)).and(listSpec.get(1))); 
            }
			return realEstateRepository.count();
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Page<RealEstate> findAllByAttribute(Map<String, Object> listAttribute, Pageable page) {
		try {
			List<RealEstateSpecifications> listSpec = new ArrayList();
			if (listAttribute != null) {
				for (Map.Entry meta : listAttribute.entrySet()) {
	    			String key = (String) meta.getKey();
	    			String value = (String) meta.getValue();
	    			if (key.equals("realEstateName")) {
	    				listSpec.add(new RealEstateSpecifications(new SearchCriteria(key, ":", value)));
	    			}else {
	    				listSpec.add(new RealEstateSpecifications(new SearchCriteria(key, ":=", value)));
	    			}
	    			
	    		}
			}
			if (!listSpec.isEmpty()) {
				return realEstateRepository.findAll(createSpecification(listSpec), page);
			}
			return realEstateRepository.findAll(page);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long countByAttribute(Map<String, Object> listAttribute) {
		try {
			List<RealEstateSpecifications> listSpec = new ArrayList();
			if (listAttribute != null) {
				for (Map.Entry meta : listAttribute.entrySet()) {
	    			String key = (String) meta.getKey();
	    			String value = (String) meta.getValue();
	    			if (key.equals("realEstateName")) {
	    				listSpec.add(new RealEstateSpecifications(new SearchCriteria(key, ":", value)));
	    			}else {
	    				listSpec.add(new RealEstateSpecifications(new SearchCriteria(key, ":=", value)));
	    			}
	    		}
			}
			if (!listSpec.isEmpty()) {
				return realEstateRepository.count(createSpecification(listSpec));
			}
			return realEstateRepository.count();
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	private Specification<RealEstate> createSpecification(List<RealEstateSpecifications> listSpec) {
		if (listSpec == null) return null;
		Specification<RealEstate> spec = Specification.where(listSpec.get(0));
		for (int i=1;i<listSpec.size();i++) {
			spec = spec.and(listSpec.get(i));
		}
		return spec;
	}
	
}
