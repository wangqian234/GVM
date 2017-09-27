package com.dt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Detector_Sensor")
public class DetectorSensor implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer Detector_Sensor_Id;
	private String Detector_Equipment;
	private String Detector_SensorSort;
	private String Detector_Sensor_Name;
	private String Detector_Sensor_SequenceNum;
	private Integer Detector_Sensor_Mode;
	private Integer Detector_Sensor_Type;
	private String Detector_Sensor_Unit;
	private String Detector_Sensor_Duration;
	private Integer Detector_Sensor_AlarmSwitch;
	private Integer Detector_Sensor_AlarmValueMin;
	private Integer Detector_Sensor_AlarmValueMax;
	private String Detector_Sensor_IconName;
	private Integer Detector_Sensor_State;
	private Integer Detector_Sensor_Order;
	private Integer Detector_Sensor_Tags;
	private String Detector_Sensor_Memo;
	private Integer Detector_Sensor_SensorId;//不知道

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Detector_Sensor_Id",unique = true, nullable = false, length = 11)
	public Integer getDetector_Sensor_Id() {
		return Detector_Sensor_Id;
	}
	public void setDetector_Sensor_Id(Integer detector_Sensor_Id) {
		Detector_Sensor_Id = detector_Sensor_Id;
	}
	public String getDetector_Equipment() {
		return Detector_Equipment;
	}
	public void setDetector_Equipment(String detector_Equipment) {
		Detector_Equipment = detector_Equipment;
	}
	public String getDetector_SensorSort() {
		return Detector_SensorSort;
	}
	public void setDetector_SensorSort(String detector_SensorSort) {
		Detector_SensorSort = detector_SensorSort;
	}
	public String getDetector_Sensor_Name() {
		return Detector_Sensor_Name;
	}
	public void setDetector_Sensor_Name(String detector_Sensor_Name) {
		Detector_Sensor_Name = detector_Sensor_Name;
	}
	public String getDetector_Sensor_SequenceNum() {
		return Detector_Sensor_SequenceNum;
	}
	public void setDetector_Sensor_SequenceNum(String detector_Sensor_SequenceNum) {
		Detector_Sensor_SequenceNum = detector_Sensor_SequenceNum;
	}
	public Integer getDetector_Sensor_Mode() {
		return Detector_Sensor_Mode;
	}
	public void setDetector_Sensor_Mode(Integer detector_Sensor_Mode) {
		Detector_Sensor_Mode = detector_Sensor_Mode;
	}
	public Integer getDetector_Sensor_Type() {
		return Detector_Sensor_Type;
	}
	public void setDetector_Sensor_Type(Integer detector_Sensor_Type) {
		Detector_Sensor_Type = detector_Sensor_Type;
	}
	public String getDetector_Sensor_Unit() {
		return Detector_Sensor_Unit;
	}
	public void setDetector_Sensor_Unit(String detector_Sensor_Unit) {
		Detector_Sensor_Unit = detector_Sensor_Unit;
	}
	public String getDetector_Sensor_Duration() {
		return Detector_Sensor_Duration;
	}
	public void setDetector_Sensor_Duration(String detector_Sensor_Duration) {
		Detector_Sensor_Duration = detector_Sensor_Duration;
	}
	public Integer getDetector_Sensor_AlarmSwitch() {
		return Detector_Sensor_AlarmSwitch;
	}
	public void setDetector_Sensor_AlarmSwitch(Integer detector_Sensor_AlarmSwitch) {
		Detector_Sensor_AlarmSwitch = detector_Sensor_AlarmSwitch;
	}
	public Integer getDetector_Sensor_AlarmValueMin() {
		return Detector_Sensor_AlarmValueMin;
	}
	public void setDetector_Sensor_AlarmValueMin(Integer detector_Sensor_AlarmValueMin) {
		Detector_Sensor_AlarmValueMin = detector_Sensor_AlarmValueMin;
	}
	public Integer getDetector_Sensor_AlarmValueMax() {
		return Detector_Sensor_AlarmValueMax;
	}
	public void setDetector_Sensor_AlarmValueMax(Integer detector_Sensor_AlarmValueMax) {
		Detector_Sensor_AlarmValueMax = detector_Sensor_AlarmValueMax;
	}
	public String getDetector_Sensor_IconName() {
		return Detector_Sensor_IconName;
	}
	public void setDetector_Sensor_IconName(String detector_Sensor_IconName) {
		Detector_Sensor_IconName = detector_Sensor_IconName;
	}
	public Integer getDetector_Sensor_State() {
		return Detector_Sensor_State;
	}
	public void setDetector_Sensor_State(Integer detector_Sensor_State) {
		Detector_Sensor_State = detector_Sensor_State;
	}
	public Integer getDetector_Sensor_Order() {
		return Detector_Sensor_Order;
	}
	public void setDetector_Sensor_Order(Integer detector_Sensor_Order) {
		Detector_Sensor_Order = detector_Sensor_Order;
	}
	public Integer getDetector_Sensor_Tags() {
		return Detector_Sensor_Tags;
	}
	public void setDetector_Sensor_Tags(Integer detector_Sensor_Tags) {
		Detector_Sensor_Tags = detector_Sensor_Tags;
	}
	public String getDetector_Sensor_Memo() {
		return Detector_Sensor_Memo;
	}
	public void setDetector_Sensor_Memo(String detector_Sensor_Memo) {
		Detector_Sensor_Memo = detector_Sensor_Memo;
	}
	public Integer getDetector_Sensor_SensorId() {
		return Detector_Sensor_SensorId;
	}
	public void setDetector_Sensor_SensorId(Integer detector_Sensor_SensorId) {
		Detector_Sensor_SensorId = detector_Sensor_SensorId;
	}
}
 
