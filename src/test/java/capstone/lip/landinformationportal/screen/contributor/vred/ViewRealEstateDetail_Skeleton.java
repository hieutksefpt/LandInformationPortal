/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.screen.contributor.vred;

/**
 *
 * @author Phong
 */
public interface ViewRealEstateDetail_Skeleton {
    final String CONTENT_RIGHT = "//div[contains(@id,'content-RightMap')]";
    final String LBL_DETAIL_REAL_ESTATE = "//*[contains(@id,'lbl-DetailRealEstate')]";
    
    final String LBL_USERS_NAME = "//*[contains(@id,'lbl-UsersNameLabel')]";
    final String TXT_USERS_NAME = "//input[contains(@id,'txt-UsersName')]";
    
    final String LBL_REAL_ESTATE_NAME = "//input[contains(@id,'lbl-RealEstateNameLabel')]";
    final String TXT_REAL_ESTATE_NAME = "//input[contains(@id,'txt-RealEstateName')]";
    
    final String LBL_REAL_ESTATE_ADDRESS = "//input[contains(@id,'lbl-RealEstateAddressLabel')]";
    final String TXT_REAL_ESTATE_ADDRESS = "//input[contains(@id,'txt-RealEstateAddress')]";
    
    final String LBL_REAL_ESTATE_PRICE = "//input[contains(@id,'lbl-RealEstatePriceLabel')]";
    final String TXT_REAL_ESTATE_PRICE = "//input[contains(@id,'txt-RealEstatePrice')]";
    
    final String LBL_LANDS_DETAIL_FEATURE_NAME = "//input[contains(@id,'lbl-LandsDetailFeatureName')]";
    final String LBL_LANDS_DETAIL_VALUE_UNIT = "//input[contains(@id,'lbl-LandsDetailValueUnit')]";
    final String LBL_HOUSE_DETAIL_FEATURE_NAME = "//input[contains(@id,'lbl-HousesDetailFeatureName')]";
    final String LBL_HOUSE_DETAIL_VALUE_UNIT = "//input[contains(@id,'lbl-HousesDetailValueUnit')]";
}
