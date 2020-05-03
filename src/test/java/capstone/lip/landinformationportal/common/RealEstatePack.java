/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.common;

import capstone.lip.landinformationportal.common.entity.RealEstate;
import capstone.lip.landinformationportal.common.entity.User;
import java.math.BigDecimal;

/**
 *
 * @author Phong
 */
public interface RealEstatePack {

    final String STATUS_CONFUSED = "CONFUSED";
    final String STATUS_NOT_VERIFIED = "NOT_VERIFIED";
    final String STATUS_VERIFIED = "VERIFIED";

    final String EXISTED_SOURCE = "CONTRIBUTOR";
    final String NON_EXISTED_SOURCE = "ADMIN";
    final String EXISTED_ADDRESS = "Hola";
    final String NON_EXISTED_ADDRESS = "123";

    final String EXISTED_NAME = "Real Estate 1";

    RealEstate sampleRealEstate = new RealEstate()
            .setRealEstateId(99L)
            .setRealEstateName("SAMPLE REAL ESTATE")
            .setRealEstatePrice(BigDecimal.valueOf(999999))
            .setRealEstateAddress(EXISTED_ADDRESS)
            .setRealEstateSource(EXISTED_SOURCE)
            .setRealEstateLat(99.0).setRealEstateLng(99.0)
            .setRealEstateStatus(STATUS_VERIFIED)
            .setUser(new User()
                    .setUserId(1L));

}
