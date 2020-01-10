/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.common;

/**
 *
 * @author Phong
 */
public class ColorDrawer {
    public static String IdentifyColor(double currentPrice) {
        if (currentPrice < 50){
            return "blue";
        }
        if (currentPrice < 70){
            return "orange";
        }
        return "red";
    }
    public static String IdentifyColorLayer(int typeLayer) {
        if (typeLayer == 1) {
        	return "red";
        }
        if (typeLayer == 2) {
        	return "orange";
        }
        if (typeLayer == 3) {
        	return "blue";
        }
        return "pink";
    }
}
