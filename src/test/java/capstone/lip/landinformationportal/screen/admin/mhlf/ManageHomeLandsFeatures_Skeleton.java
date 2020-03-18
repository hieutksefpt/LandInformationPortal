/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.screen.admin.mhlf;

/**
 *
 * @author Phong
 */
public interface ManageHomeLandsFeatures_Skeleton {
    final String BTN_LAND_FEATURE_TAB = "//input[contains(@id,'btn-LandFeatureTab')]";
    final String BTN_HOME_FEATURE_TAB = "//input[contains(@id,'btn-HomeFeatureTab')]";
    
    final String BTN_ADD_NEW_FEATURE = "//input[contains(@id,'btn-AddNewFeature')]";
    final String BTN_EDIT_FEATURE = "//input[contains(@id,'btn-EditFeature')]";
    final String BTN_DELETE_FEATURE = "//input[contains(@id,'btn-DeleteFeature')]";
    
    //THIẾU POPUP
    //...
    final String TXT_NAME_FEATURE = "//input[contains(@id,'txtinput-NameFeature')]";
    final String TXT_UNIT_FEATURE = "//input[contains(@id,'txtInput-UnitFeature')]";
    final String BTN_ADD_FEATURE = "//input[contains(@id,'btn-AddFeature')]";
    final String BTN_SAVE = "//input[contains(@id,'btn-Save')]";
    final String BTN_CANCEL = "//input[contains(@id,'btn-Cancel')]";
    
    final String LBL_TITLE = "//input[contains(@id,'lbl-Title')]";
    final String LBL_FEATURE = "//input[contains(@id,'lbl-Feature')]";
    final String LBL_UNIT = "//input[contains(@id,'lbl-Unit')]";
    final String LBL_TASK = "//input[contains(@id,'lbl-Task')]";
}
