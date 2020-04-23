/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.business.validation;

import capstone.lip.landinformationportal.common.dto.RealEstateObjectCrawl;
import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class CrawlRealEstateValidation extends StringValidation {

    public boolean isValidCrawlRealEstate(RealEstateObjectCrawl reoCrawl) {
        if (reoCrawl == null) {
            return false;
        }
        if (reoCrawl.getTitle().equals("")) {
            return false;
        } else if (Double.compare(reoCrawl.getLatitude(), 0) <= 0 && Double.compare(reoCrawl.getLongitude(), 0) <= 0) {
            return false;
        } else if (reoCrawl.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        } else if (reoCrawl.getSource().toString().equals("CONTRIBUTOR")) {
            return false;
        }
        return true;
    }
}
