/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.predictPrice;

import capstone.lip.landinformationportal.business.service.PredictPriceService;
import capstone.lip.landinformationportal.common.CRUDTest;
import capstone.lip.landinformationportal.common.RealEstatePack;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Phong
 */
public abstract class AbstractPredictPriceServiceTest extends CRUDTest 
    implements RealEstatePack {
    
    @Autowired
    protected PredictPriceService instance;
    
}
