/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.screen.commonElement;

/**
 *
 * @author Phong
 */
public interface HeaderContributorCommon {
    final String BTN_LOGO = "//button[contains(@id,'btn-Logo')]";
    final String BTN_ABOUT_US = "//button[contains(@id,'btn-AboutUs')]";
    final String BTN_LAWS_LIBRARY = "//button[contains(@id,'btn-LawsLibrary')]";
    final String BTN_CONTRIBUTED_REAL_ESTATE = "//button[contains(@id,'btn-ContributedRealEstate')]";
    final String BTN_USER_PROFILE = "//button[contains(@id,'btn-UserProfile')]";
    final String BTN_LOG_OUT = "//button[contains(@id,'btn-LogOut')]";
    
    void testExistedHCC();
}
