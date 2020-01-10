/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.rep.realestateportal.entity;

import java.util.ArrayList;

/**
 *
 * @author Anh Hao
 */
public class Layer {
    private int layerId;
    private String layerName;
    private String layerType;
    private int layerTypeId;
    private ArrayList<Coordinate> listCoordinate;

    public Layer() {
    }

    public int getLayerId() {
        return layerId;
    }

    public Layer setLayerId(int layerId) {
        this.layerId = layerId;
        return this;
    }

    public String getLayerName() {
        return layerName;
    }

    public Layer setLayerName(String layerName) {
        this.layerName = layerName;
        return this;
    }

    public String getLayerType() {
        return layerType;
    }

    public Layer setLayerType(String layerType) {
        this.layerType = layerType;
        return this;
    }

    public ArrayList<Coordinate> getListCoordinate() {
        return listCoordinate;
    }

    public Layer setListCoordinate(ArrayList<Coordinate> listCoordinate) {
        this.listCoordinate = listCoordinate;
        return this;
    }

	public int getLayerTypeId() {
		return layerTypeId;
	}

	public Layer setLayerTypeId(int layerTypeId) {
		this.layerTypeId = layerTypeId;
		return this;
	}
    
}
