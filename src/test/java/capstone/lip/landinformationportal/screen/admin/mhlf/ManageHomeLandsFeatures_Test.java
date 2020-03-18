/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.screen.admin.mhlf;

import capstone.lip.landinformationportal.common.AdminUITest;
import capstone.lip.landinformationportal.common.SitePath;

/**
 *
 * @author Phong
 */
public abstract class ManageHomeLandsFeatures_Test extends AdminUITest 
    implements ManageHomeLandsFeatures_Skeleton {
    
    @Override
    protected void openSite() throws InterruptedException {
        driver.get(SitePath.MHLF);
        Thread.currentThread().sleep(200);
    }
    
    protected void testDefaultVisibleItem() {
        testOnlyOneExisted(BTN_LAND_FEATURE_TAB);
        testOnlyOneExisted(BTN_HOME_FEATURE_TAB);
        
        testOnlyOneExisted(BTN_ADD_NEW_FEATURE);
        
        testOnlyOneExisted(LBL_TITLE);
        testOnlyOneExisted(LBL_FEATURE);
        testOnlyOneExisted(LBL_UNIT);
        testOnlyOneExisted(LBL_TASK);
    }
}
