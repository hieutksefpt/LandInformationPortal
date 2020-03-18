/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.common;

import capstone.lip.landinformationportal.screen.commonElement.ManageTabCommon;

/**
 *
 * @author Phong
 */
public abstract class AdminUITest extends GUITest implements ManageTabCommon {
    
    protected void testManageTabCommon() {
        testOnlyOneExisted(BTN_LOGO);
        testOnlyOneExisted(BTN_MANAGE_USER);
        testOnlyOneExisted(BTN_MANAGE_WEB_CRAWLED);
        testOnlyOneExisted(BTN_MANAGE_NEWS_CRAWLED);
        testOnlyOneExisted(BTN_MANAGE_NEWS);
        testOnlyOneExisted(BTN_MANAGE_IPG);
        testOnlyOneExisted(BTN_MANAGE_LAWS);
        testOnlyOneExisted(BTN_CHECK_REP_CONTRIBUTE);
        testOnlyOneExisted(BTN_UPDATE_PROFILE);
    }
}
