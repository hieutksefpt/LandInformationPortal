/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.screen.admin.mig;

/**
 *
 * @author Phong
 */
public interface ManageGeoInfo_Skeleton {
    final String TXT_NAME = "//input[contains(@id,'txtInput-Name')]";
    final String LBL_COORDINATE = "//input[contains(@id,'lbl-Coordinate')]";
    final String TXT_LAT = "//input[contains(@id,'txtInput-Latitude')]";
    final String TXT_LNG = "//input[contains(@id,'txtInput-Longitude')]";

    final String BTN_ADD_NEW = "//button[contains(@id,'btn-AddNew')]";
    final String BTN_SAVE = "//button[contains(@id,'btn-Save')]";
    final String BTN_DELETE = "//button[contains(@id,'btn-Delete')]";
    final String BTN_CLEAR = "//button[contains(@id,'btn-Clear')]";

    final String TXT_SEGMENT = "//button[contains(@id,'txtInput-SegmentName')]";
    final String LBL_FORMED_COORDINATE = "//input[contains(@id,'lbl-FormedCoordinate')]";
    final String TXT_FORMED_LAT = "//button[contains(@id,'txtInput-FormedLatitude')]";
    final String TXT_FORMED_LNG = "//button[contains(@id,'txtInput-FormedLongitude')]";
    final String BTN_SAVE_SEGMENT = "//button[contains(@id,'btn-SaveSegmentStreet')]";
    final String BTN_CLEAR_SEGMENT = "//button[contains(@id,'btn-ClearSegment')]";
}
