package com.dt.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PreInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer Pre_Id;
	private Integer Detector_Equipment_Id;
	private Integer EquipmentType_Id;
	
	private String Equipment_No;
	private String Equipment_Name;
	private String Equipment_UseDate;
	private String Equipment_Lifetime;
	private Integer Equipment_State;
	
	private Integer MaintenanceLog_Type;
	private String MaintenanceLog_StartDate;
	private String MaintenanceLog_EndDate;
	
	private String preDate;
	private String PreLog_Content;
	private String PreLog_Tools;
	private String PreLog_Workers;
	private String Project;
	
	/*private String preDate;
	private String preContent;
	private String preTools;
	private String preWorkers;
	*/
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	public Integer getDetector_Equipment_Id() {
		return Detector_Equipment_Id;
	}
	public void setDetector_Equipment_Id(Integer detector_Equipment_Id) {
		Detector_Equipment_Id = detector_Equipment_Id;
	}
	public String getEquipment_No() {
		return Equipment_No;
	}
	public void setEquipment_No(String equipment_No) {
		Equipment_No = equipment_No;
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
	public String getEquipment_Lifetime() {
		return Equipment_Lifetime;
	}
	public void setEquipment_Lifetime(String equipment_Lifetime) {
		Equipment_Lifetime = equipment_Lifetime;
	}
	public Integer getMaintenanceLog_Type() {
		return MaintenanceLog_Type;
	}
	public void setMaintenanceLog_Type(Integer maintenanceLog_Type) {
		MaintenanceLog_Type = maintenanceLog_Type;
	}
	public String getMaintenanceLog_EndDate() {
		return MaintenanceLog_EndDate;
	}
	public void setMaintenanceLog_EndDate(String maintenanceLog_EndDate) {
		MaintenanceLog_EndDate = maintenanceLog_EndDate;
	}
	public String getMaintenanceLog_StartDate() {
		return MaintenanceLog_StartDate;
	}
	public void setMaintenanceLog_StartDate(String maintenanceLog_StartDate) {
		MaintenanceLog_StartDate = maintenanceLog_StartDate;
	}
//	public String getPreDate() {
//		return preDate;
//	}
//	public void setPreDate(String preDate) {
//		this.preDate = preDate;
//	}
//	public String getPreContent() {
//		return preContent;
//	}
//	public void setPreContent(String preContent) {
//		this.preContent = preContent;
//	}
//	public String getPreTools() {
//		return preTools;
//	}
//	public void setPreTools(String preTools) {
//		this.preTools = preTools;
//	}
//	public String getPreWorkers() {
//		return preWorkers;
//	}
//	public void setPreWorkers(String preWorkers) {
//		this.preWorkers = preWorkers;
//	}
	public Integer getPre_Id() {
		return Pre_Id;
	}
	public void setPre_Id(Integer pre_Id) {
		Pre_Id = pre_Id;
	}
	public Integer getEquipment_State() {
		return Equipment_State;
	}
	public void setEquipment_State(Integer equipment_State) {
		Equipment_State = equipment_State;
	}
	public String getPreLog_Content() {
		return PreLog_Content;
	}
	public void setPreLog_Content(String preLog_Content) {
		PreLog_Content = preLog_Content;
	}
	public String getPreLog_Tools() {
		return PreLog_Tools;
	}
	public void setPreLog_Tools(String preLog_Tools) {
		PreLog_Tools = preLog_Tools;
	}
	public String getPreLog_Workers() {
		return PreLog_Workers;
	}
	public void setPreLog_Workers(String preLog_Workers) {
		PreLog_Workers = preLog_Workers;
	}
	public Integer getEquipmentType_Id() {
		return EquipmentType_Id;
	}
	public void setEquipmentType_Id(Integer equipmentType_Id) {
		EquipmentType_Id = equipmentType_Id;
	}
	public String getPreDate() {
		return preDate;
	}
	public void setPreDate(String preDate) {
		this.preDate = preDate;
	}
	public String getProject() {
		return Project;
	}
	public void setProject(String project) {
		Project = project;
	}
}
