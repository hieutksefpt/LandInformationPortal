/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.screen.contributor.lore;

import capstone.lip.landinformationportal.common.GUITest;
import capstone.lip.landinformationportal.common.SitePath;
import capstone.lip.landinformationportal.screen.commonElement.HeaderContributorCommon;

/**
 *
 * @author Phong
 */
public abstract class ListOwnRealEstate_Test extends GUITest
        implements ListOwnRealEstate_Skeleton, HeaderContributorCommon {
    
    @Override
    protected void openSite() throws InterruptedException {
        driver.get(SitePath.LORE);
        Thread.currentThread().sleep(200);
    }

    @Override
    public void testExistedHCC() {
        testOnlyOneExisted(BTN_LOGO);
        testOnlyOneExisted(BTN_ABOUT_US);
        testOnlyOneExisted(BTN_LAWS_LIBRARY);
        testOnlyOneExisted(BTN_CONTRIBUTED_REAL_ESTATE);
        testOnlyOneExisted(BTN_USER_PROFILE);
        testOnlyOneExisted(BTN_LOG_OUT);
    }
    
    protected void testDefaultExistedItem() {
        testOnlyOneExisted(MAP);
        testOnlyOneExisted(CONTENT_RIGHT);
        testOnlyOneExisted(LINK_ADD_NEW_REAL_ESTATE);
    }
    
    protected void testFormItem() {
        testOnlyOneExisted(FORM_VIEW_LIST);
        testOnlyOneExisted(LBL_REAL_ESTATE_ADDRESS);
        testOnlyOneExisted(BTN_VIEW);
    }
    
}
