/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.service.Interface;

import capstone.lip.landinformationportal.entity.LandsDetail;
import java.util.List;
/**
 *
 * @author Admin
 */
public interface ILandsDetailService {

    LandsDetail save(LandsDetail landsDetail);

    boolean delete(List<LandsDetail> listLandsDetail);

}
