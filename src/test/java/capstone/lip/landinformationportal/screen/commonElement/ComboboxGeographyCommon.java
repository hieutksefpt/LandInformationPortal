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
public interface ComboboxGeographyCommon {
    final String SEARCHBOX_ADDRESS = "//input[contains(@id,'searchbox-Address')]";
    final String CBB_PROVINCE = "//select[contains(@id,'cbb-Province')]";
    final String CBB_DISTRICT = "//select[contains(@id,'cbb-District')]";
    final String CBB_STREET = "//select[contains(@id,'cbb-Street')]";
    final String CBB_SEGMENT = "//select[contains(@id,'cbb-SegmentOfStreet')]";
    
    void testComboboxGeographyCommon();
}
