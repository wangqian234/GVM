package com.dt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Detector_Equipment")
public class DetectorEquipment implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer Detector_Equipment_Id;//探测器设备id，主键
	private String Detector_Equipment_No;
	private String Detector_Equipment_Name;//探测器设备名字
	private Integer Detector_Equipment_State;//探测器设备状态    0   1 ？
	private Integer Detector_Equipment_Order;//
	private String Detector_EquipmentType;//FK 探测器设备类型
	private String Detector_EquipmentRoom;//FK 探测器设备房间
	private String Detector_Equipment_Version;//探测设备视图
	private Float Detector_Equipment_Price;//探测设备价格
	private String Detector_Equipment_TP;
	private String Detector_Equipment_Manufacturers;
	private Date Detector_Equipment_ProductionDate;
	private Date Detector_Equipment_UseDate;
	private Integer Detector_Equipment_MaintenancePeriod;
	private Date Detector_Equipment_RepairsDate;
	private Date Detector_Equipment_TroublesDate;
	private String Detector_Equipment_Tel;
	private String Detector_Equipment_Mobile;
	private String Detector_Equipment_FactoryNo;
	private Date Detector_Equipment_Lifetime;
	private String Detector_Equipment_Director;
	private String Detector_Equipment_UserID;
	private Date Detector_Equipment_AddDate;
	private String Detector_Equipment_GUID;
	private String Detector_Equipment_Memo;//维护备注

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Detector_Equipment_Id",unique = true, nullable = false, length = 11)
	public Integer getDetector_Equipment_Id() {
		return Detector_Equipment_Id;
	}
	public void setDetector_Equipment_Id(Integer detector_Equipment_Id) {
		Detector_Equipment_Id = detector_Equipment_Id;
	}
	public String getDetector_Equipment_No() {
		return Detector_Equipment_No;
	}
	public void setDetector_Equipment_No(String detector_Equipment_No) {
		Detector_Equipment_No = detector_Equipment_No;
	}
	public String getDetector_Equipment_Name() {
		return Detector_Equipment_Name;
	}
	public void setDetector_Equipment_Name(String detector_Equipment_Name) {
		Detector_Equipment_Name = detector_Equipment_Name;
	}
	public Integer getDetector_Equipment_State() {
		return Detector_Equipment_State;
	}
	public void setDetector_Equipment_State(Integer detector_Equipment_State) {
		Detector_Equipment_State = detector_Equipment_State;
	}
	public Integer getDetector_Equipment_Order() {
		return Detector_Equipment_Order;
	}
	public void setDetector_Equipment_Order(Integer detector_Equipment_Order) {
		Detector_Equipment_Order = detector_Equipment_Order;
	}

	public String getDetector_EquipmentType() {
		return Detector_EquipmentType;
	}
	public void setDetector_EquipmentType(String detector_EquipmentType) {
		Detector_EquipmentType = detector_EquipmentType;
	}
	public String getDetector_EquipmentRoom() {
		return Detector_EquipmentRoom;
	}
	public void setDetector_EquipmentRoom(String detector_EquipmentRoom) {
		Detector_EquipmentRoom = detector_EquipmentRoom;
	}
	public String getDetector_Equipment_Version() {
		return Detector_Equipment_Version;
	}
	public void setDetector_Equipment_Version(String detector_Equipment_Version) {
		Detector_Equipment_Version = detector_Equipment_Version;
	}

	public Float getDetector_Equipment_Price() {
		return Detector_Equipment_Price;
	}
	public void setDetector_Equipment_Price(Float detector_Equipment_Price) {
		Detector_Equipment_Price = detector_Equipment_Price;
	}
	public String getDetector_Equipment_TP() {
		return Detector_Equipment_TP;
	}
	public void setDetector_Equipment_TP(String detector_Equipment_TP) {
		Detector_Equipment_TP = detector_Equipment_TP;
	}
	public String getDetector_Equipment_Manufacturers() {
		return Detector_Equipment_Manufacturers;
	}
	public void setDetector_Equipment_Manufacturers(String detector_Equipment_Manufacturers) {
		Detector_Equipment_Manufacturers = detector_Equipment_Manufacturers;
	}
	public Date getDetector_Equipment_ProductionDate() {
		return Detector_Equipment_ProductionDate;
	}
	public void setDetector_Equipment_ProductionDate(Date detector_Equipment_ProductionDate) {
		Detector_Equipment_ProductionDate = detector_Equipment_ProductionDate;
	}
	public Date getDetector_Equipment_UseDate() {
		return Detector_Equipment_UseDate;
	}
	public void setDetector_Equipment_UseDate(Date detector_Equipment_UseDate) {
		Detector_Equipment_UseDate = detector_Equipment_UseDate;
	}
	public Integer getDetector_Equipment_MaintenancePeriod() {
		return Detector_Equipment_MaintenancePeriod;
	}
	public void setDetector_Equipment_MaintenancePeriod(Integer detector_Equipment_MaintenancePeriod) {
		Detector_Equipment_MaintenancePeriod = detector_Equipment_MaintenancePeriod;
	}
	public Date getDetector_Equipment_RepairsDate() {
		return Detector_Equipment_RepairsDate;
	}
	public void setDetector_Equipment_RepairsDate(Date detector_Equipment_RepairsDate) {
		Detector_Equipment_RepairsDate = detector_Equipment_RepairsDate;
	}
	public String getDetector_Equipment_Tel() {
		return Detector_Equipment_Tel;
	}
	public void setDetector_Equipment_Tel(String detector_Equipment_Tel) {
		Detector_Equipment_Tel = detector_Equipment_Tel;
	}
	public String getDetector_Equipment_Mobile() {
		return Detector_Equipment_Mobile;
	}
	public void setDetector_Equipment_Mobile(String detector_Equipment_Mobile) {
		Detector_Equipment_Mobile = detector_Equipment_Mobile;
	}
	public String getDetector_Equipment_FactoryNo() {
		return Detector_Equipment_FactoryNo;
	}
	public void setDetector_Equipment_FactoryNo(String detector_Equipment_FactoryNo) {
		Detector_Equipment_FactoryNo = detector_Equipment_FactoryNo;
	}
	public Date getDetector_Equipment_Lifetime() {
		return Detector_Equipment_Lifetime;
	}
	public void setDetector_Equipment_Lifetime(Date detector_Equipment_Lifetime) {
		Detector_Equipment_Lifetime = detector_Equipment_Lifetime;
	}
	public String getDetector_Equipment_Director() {
		return Detector_Equipment_Director;
	}
	public void setDetector_Equipment_Director(String detector_Equipment_Director) {
		Detector_Equipment_Director = detector_Equipment_Director;
	}
	public String getDetector_Equipment_UserID() {
		return Detector_Equipment_UserID;
	}
	public void setDetector_Equipment_UserID(String detector_Equipment_UserID) {
		Detector_Equipment_UserID = detector_Equipment_UserID;
	}
	public Date getDetector_Equipment_AddDate() {
		return Detector_Equipment_AddDate;
	}
	public void setDetector_Equipment_AddDate(Date detector_Equipment_AddDate) {
		Detector_Equipment_AddDate = detector_Equipment_AddDate;
	}
	public String getDetector_Equipment_GUID() {
		return Detector_Equipment_GUID;
	}
	public void setDetector_Equipment_GUID(String detector_Equipment_GUID) {
		Detector_Equipment_GUID = detector_Equipment_GUID;
	}
	public String getDetector_Equipment_Memo() {
		return Detector_Equipment_Memo;
	}
	public void setDetector_Equipment_Memo(String detector_Equipment_Memo) {
		Detector_Equipment_Memo = detector_Equipment_Memo;
	}
	public Date getDetector_Equipment_TroublesDate() {
		return Detector_Equipment_TroublesDate;
	}
	public void setDetector_Equipment_TroublesDate(Date detector_Equipment_TroublesDate) {
		Detector_Equipment_TroublesDate = detector_Equipment_TroublesDate;
	}
}
