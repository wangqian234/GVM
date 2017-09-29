package com.dt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Detector_TriggerLog")
public class DetectorTriggerLog implements Serializable{
	private Integer Detector_TriggerLog_Id;
	private String Detector_Trigger;
	private String Detector_Sensor_Id;
	private String Detector_Trigger_AlarmMode;
	private String Detector_Trigger_AlarmUser;
	private String CRM_List_Id;
	private String Detector_TriggerLog_Time;
	private String Detector_TriggerLog_Memo;
	private String Detector_TriggerLog_RemoveTime;
	private String Detector_TriggerLog_State;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Detector_TriggerLog_Id",unique = true, nullable = false, length = 11)
	public Integer getDetector_TriggerLog_Id() {
		return Detector_TriggerLog_Id;
	}
	public void setDetector_TriggerLog_Id(Integer detector_TriggerLog_Id) {
		Detector_TriggerLog_Id = detector_TriggerLog_Id;
	}
	public String getDetector_Trigger() {
		return Detector_Trigger;
	}
	public void setDetector_Trigger(String detector_Trigger) {
		Detector_Trigger = detector_Trigger;
	}
	public String getDetector_Sensor_Id() {
		return Detector_Sensor_Id;
	}
	public void setDetector_Sensor_Id(String detector_Sensor_Id) {
		Detector_Sensor_Id = detector_Sensor_Id;
	}
	public String getDetector_Trigger_AlarmMode() {
		return Detector_Trigger_AlarmMode;
	}
	public void setDetector_Trigger_AlarmMode(String detector_Trigger_AlarmMode) {
		Detector_Trigger_AlarmMode = detector_Trigger_AlarmMode;
	}
	public String getDetector_Trigger_AlarmUser() {
		return Detector_Trigger_AlarmUser;
	}
	public void setDetector_Trigger_AlarmUser(String detector_Trigger_AlarmUser) {
		Detector_Trigger_AlarmUser = detector_Trigger_AlarmUser;
	}
	public String getCRM_List_Id() {
		return CRM_List_Id;
	}
	public void setCRM_List_Id(String cRM_List_Id) {
		CRM_List_Id = cRM_List_Id;
	}
	public String getDetector_TriggerLog_Time() {
		return Detector_TriggerLog_Time;
	}
	public void setDetector_TriggerLog_Time(String detector_TriggerLog_Time) {
		Detector_TriggerLog_Time = detector_TriggerLog_Time;
	}
	public String getDetector_TriggerLog_Memo() {
		return Detector_TriggerLog_Memo;
	}
	public void setDetector_TriggerLog_Memo(String detector_TriggerLog_Memo) {
		Detector_TriggerLog_Memo = detector_TriggerLog_Memo;
	}
	public String getDetector_TriggerLog_RemoveTime() {
		return Detector_TriggerLog_RemoveTime;
	}
	public void setDetector_TriggerLog_RemoveTime(String detector_TriggerLog_RemoveTime) {
		Detector_TriggerLog_RemoveTime = detector_TriggerLog_RemoveTime;
	}
	public String getDetector_TriggerLog_State() {
		return Detector_TriggerLog_State;
	}
	public void setDetector_TriggerLog_State(String detector_TriggerLog_State) {
		Detector_TriggerLog_State = detector_TriggerLog_State;
	}

}
