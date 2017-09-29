package com.dt.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PreInfo implements Serializable{
	static final long serialVersionUID = 1L;
	private Integer Equipment_Id;//设备id，主键
	private String Equipment_Name;//设备名字
	private String Equipment_UseDate;//使用日期
	private String MaintenanceLog_Content;//维护内容
	private String Equipment_Memo;//备注
	private String Equipment_Lifetime;//设备寿命
	private String MaintenanceLog_EndDate;//设备维护日期
	private String TriggerLog_Time;//故障日期
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getEquipment_Id() {
		return Equipment_Id;
	}
	public void setEquipment_Id(Integer equipment_Id) {
		Equipment_Id = equipment_Id;
	}
	public String getEquipment_Name() {
		return Equipment_Name;
	}
	public void setEquipment_Name(String equipment_Name) {
		Equipment_Name = equipment_Name;
	}
	public String getEquipment_UseDate() {
		return Equipment_UseDate;
	}
	public void setEquipment_UseDate(String equipment_UseDate) {
		Equipment_UseDate = equipment_UseDate;
	}
	public String getEquipment_Memo() {
		return Equipment_Memo;
	}
	public void setEquipment_Memo(String equipment_Memo) {
		Equipment_Memo = equipment_Memo;
	}
	public String getEquipment_Lifetime() {
		return Equipment_Lifetime;
	}
	public void setEquipment_Lifetime(String equipment_Lifetime) {
		Equipment_Lifetime = equipment_Lifetime;
	}
	public String getMaintenanceLog_EndDate() {
		return MaintenanceLog_EndDate;
	}
	public void setMaintenanceLog_EndDate(String maintenanceLog_EndDate) {
		MaintenanceLog_EndDate = maintenanceLog_EndDate;
	}
	public String getTriggerLog_Time() {
		return TriggerLog_Time;
	}
	public void setTriggerLog_Time(String triggerLog_Time) {
		TriggerLog_Time = triggerLog_Time;
	}
	public String getMaintenanceLog_Content() {
		return MaintenanceLog_Content;
	}
	public void setMaintenanceLog_Content(String maintenanceLog_Content) {
		MaintenanceLog_Content = maintenanceLog_Content;
	}
	
}
