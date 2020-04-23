/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.common;

import java.sql.Timestamp;

/**
 *
 * @author Phong
 */
public interface TimePack {
    final Timestamp TIME_CURRENT = new Timestamp(System.currentTimeMillis());
    final Timestamp TIME_PAST = 
            new Timestamp(TIME_CURRENT.getTime() - (24 * 60 * 60 * 1000)*1 );
    final Timestamp TIME_FUTURE = 
            new Timestamp(TIME_CURRENT.getTime() + (24 * 60 * 60 * 1000)*1 );
}
