/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.screen;

import capstone.lip.landinformationportal.common.GUITest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
/**
 *
 * @author Phong
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageGeoInfoTest extends GUITest {
    
    private void openSite() throws InterruptedException {
        driver.get("http://localhost:8080/admin/managegeoinfo.xhtml");
        Thread.currentThread().sleep(200);
    }
    
    private void checkCommonItem() {
        checkAdminLeftBarItem();
        
        checkExistItem("//input[contains(@id,'searchbox-Address')]");
        checkExistItem("//select[contains(@id,'cbb-Province')]");
        checkExistItem("//select[contains(@id,'cbb-District')]");
        checkExistItem("//select[contains(@id,'cbb-Street')]");
        checkExistItem("//select[contains(@id,'cbb-SegmentOfStreet')]");
        checkExistItem("//select[contains(@id,'cbb-IpgType')]");
        checkExistItem("//input[contains(@id,'txtInput-Name')]");
        checkExistItem("//input[contains(@id,'txtInput-Coordinate')]");
        checkExistItem("//button[contains(@id,'btn-AddNew')]");
        checkExistItem("//button[contains(@id,'btn-Save')]");
        checkExistItem("//button[contains(@id,'btn-Delete')]");
        checkExistItem("//button[contains(@id,'btn-Clear')]");
        checkExistItem("//input[contains(@id,'txtInput-CoordinateContribute')]");
        checkExistItem("//button[contains(@id,'btn-ClearSegment')]");
    }
    
    private void checkSegmentItem() {
        checkExistItem("//input[contains(@id,'txtInput-SegmentName')]");
        checkExistItem("//input[contains(@id,'txtInput-CoordinateContribute')]");
        checkExistItem("//button[contains(@id,'btn-SaveSegmentStreet')]");
    }
    
    /*
    * UC001: List province
    */
    @Test
    public void GUI_UC001() throws Exception {
        openSite();
        checkCommonItem();
    }
    
    /*
    * UC002: Add new province
    */
    @Test
    public void GUI_UC002() throws Exception {
        openSite();
        checkCommonItem();
    }
    
    /*
    * UC003: Delete province
    */
    @Test
    public void GUI_UC003() throws Exception {
        openSite();
        checkCommonItem();
    }
    
    /*
    * UC004: Update province
    */
    @Test
    public void GUI_UC004() throws Exception {
        openSite();
        checkCommonItem();
    }
    
    /*
    * UC005: List district by province
    */
    @Test
    public void GUI_UC005() throws Exception {
        openSite();
        checkCommonItem();
    }
    
    /*
    * UC006: Add new district
    */
    @Test
    public void GUI_UC006() throws Exception {
        openSite();
        checkCommonItem();
    }
    
    /*
    * UC007: Delete district
    */
    @Test
    public void GUI_UC007() throws Exception {
        openSite();
        checkCommonItem();
    }
    
    /*
    * UC008: Update district
    */
    @Test
    public void GUI_UC008() throws Exception {
        openSite();
        checkCommonItem();
    }
    
    /*
    * UC009: List street by district
    */
    @Test
    public void GUI_UC009() throws Exception {
        openSite();
        checkCommonItem();
    }
    
    /*
    * UC010: Add new street
    */
    @Test
    public void GUI_UC010() throws Exception {
        openSite();
        checkCommonItem();
    }
    
    /*
    * UC011: Delete street
    */
    @Test
    public void GUI_UC011() throws Exception {
        openSite();
        checkCommonItem();
    }
    
    /*
    * UC012: Update street
    */
    @Test
    public void GUI_UC012() throws Exception {
        openSite();
        checkCommonItem();
    }
    
    /*
    * UC013: List segment by street
    */
    @Test
    public void GUI_UC013() throws Exception {
        openSite();
        checkCommonItem();
    }
    
    /*
    * UC014: Add new segment
    */
    @Test
    public void GUI_UC014() throws Exception {
        openSite();
        checkCommonItem();
    }
    
    /*
    * UC015: Delete segment
    */
    @Test
    public void GUI_UC015() throws Exception {
        openSite();
        checkCommonItem();
    }
    
    /*
    * UC016: Update segment
    */
    @Test
    public void GUI_UC016() throws Exception {
        openSite();
        checkCommonItem();
    }
}
