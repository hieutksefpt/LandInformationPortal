/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service;

import capstone.lip.landinformationportal.entity.HousesDetail;
import capstone.lip.landinformationportal.repository.HousesDetailRepository;
import capstone.lip.landinformationportal.service.Interface.IHousesDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class HousesDetailService implements IHousesDetailService {

    @Autowired
    private HousesDetailRepository housesDetailRepository;

    @Override
    public List<HousesDetail> findAll() {
        return housesDetailRepository.findAll();
    }

    @Override
    public HousesDetail save(HousesDetail housesDetail) {
        return housesDetailRepository.save(housesDetail);
    }

    @Override
    public void delete(List<HousesDetail> listHousesDetail) {
        housesDetailRepository.deleteInBatch(listHousesDetail);
    }

	@Override
	public void delete(HousesDetail housesDetail) {
		housesDetailRepository.delete(housesDetail);
	}

}
