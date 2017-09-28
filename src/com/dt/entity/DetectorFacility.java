package com.dt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class DetectorFacility implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer Detector_Facility_Id;
	private String Detector_Facility_Name;
	private String Detector_Facility_Alias;
	private Integer Detector_Facility_State;
	private Integer  Detector_Facility_Order;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Detector_Facility_Id",unique = true, nullable = false, length = 11)
	public Integer getDetector_Facility_Id() {
		return Detector_Facility_Id;
	}
	public void setDetector_Facility_Id(Integer detector_Facility_Id) {
		Detector_Facility_Id = detector_Facility_Id;
	}
	public String getDetector_Facility_Name() {
		return Detector_Facility_Name;
	}
	public void setDetector_Facility_Name(String detector_Facility_Name) {
		Detector_Facility_Name = detector_Facility_Name;
	}
	public String getDetector_Facility_Alias() {
		return Detector_Facility_Alias;
	}
	public void setDetector_Facility_Alias(String detector_Facility_Alias) {
		Detector_Facility_Alias = detector_Facility_Alias;
	}
	public Integer getDetector_Facility_State() {
		return Detector_Facility_State;
	}
	public void setDetector_Facility_State(Integer detector_Facility_State) {
		Detector_Facility_State = detector_Facility_State;
	}
	public Integer getDetector_Facility_Order() {
		return Detector_Facility_Order;
	}
	public void setDetector_Facility_Order(Integer detector_Facility_Order) {
		Detector_Facility_Order = detector_Facility_Order;
	}



	

}
