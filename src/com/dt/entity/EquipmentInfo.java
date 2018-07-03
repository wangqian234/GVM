package com.dt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class EquipmentInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer Equipment_Num;//排序序号
	private Integer Equipment_Id;//设备id，主键
	private String Equipment_No;//设备编号
	private String Equipment_Name;//设备名字
	private String Equipment_State;//探测器设备状态？
	private String Equipment_Type;//探测器设备类型
	private String Equipment_Project;//小区
	private String Equipment_Room;//探测器设备房
	private String Equipment_Order;//设备排序
	private String Equipment_Version;//探测设备视图
	private String Equipment_Price;//探测设备价格
	private String Equipment_TP;//设备参数
	private String Equipment_Manufacturers;//生产厂家
	private String Equipment_ProductionDate;//生产日期
	private String Equipment_UseDate;//使用日期
	private String Equipment_MaintenancePeriod;//保养周期（天）
	private String Equipment_RepairsDate;//保修截止日期
	private String Equipment_Tel;//厂家电话
	private String Equipment_Mobile;//厂家手机
	private String Equipment_FactoryNo;//出厂编号
	private String Equipment_Lifetime;//设备寿命
	private String Equipment_Director;//设备负责人
	private String Equipment_UserID;//录入人员ID
	private String Equipment_AddDate;//添加时间
	private String Equipment_GUID;//
	private String Equipment_Memo;//备注
	private Float  Equipment_Fault;//故障率
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getEquipment_Id() {
		return Equipment_Id;
	}
	public void setEquipment_Id(Integer equipment_Id) {
		Equipment_Id = equipment_Id;
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
	public String getEquipment_State() {
		return Equipment_State;
	}
	public void setEquipment_State(String equipment_State) {
		Equipment_State = equipment_State;
	}
	public String getEquipment_Type() {
		return Equipment_Type;
	}
	public void setEquipment_Type(String equipment_Type) {
		Equipment_Type = equipment_Type;
	}
	public String getEquipment_Room() {
		return Equipment_Room;
	}
	public void setEquipment_Room(String equipment_Room) {
		Equipment_Room = equipment_Room;
	}
	public String getEquipment_Order() {
		return Equipment_Order;
	}
	public void setEquipment_Order(String equipment_Order) {
		Equipment_Order = equipment_Order;
	}
	public String getEquipment_Version() {
		return Equipment_Version;
	}
	public void setEquipment_Version(String equipment_Version) {
		Equipment_Version = equipment_Version;
	}
	public String getEquipment_Price() {
		return Equipment_Price;
	}
	public void setEquipment_Price(String equipment_Price) {
		Equipment_Price = equipment_Price;
	}
	public String getEquipment_TP() {
		return Equipment_TP;
	}
	public void setEquipment_TP(String equipment_TP) {
		Equipment_TP = equipment_TP;
	}
	public String getEquipment_Manufacturers() {
		return Equipment_Manufacturers;
	}
	public void setEquipment_Manufacturers(String equipment_Manufacturers) {
		Equipment_Manufacturers = equipment_Manufacturers;
	}
	public String getEquipment_ProductionDate() {
		return Equipment_ProductionDate;
	}
	public void setEquipment_ProductionDate(String equipment_ProductionDate) {
		Equipment_ProductionDate = equipment_ProductionDate;
	}
	public String getEquipment_UseDate() {
		return Equipment_UseDate;
	}
	public void setEquipment_UseDate(String equipment_UseDate) {
		Equipment_UseDate = equipment_UseDate;
	}
	public String getEquipment_MaintenancePeriod() {
		return Equipment_MaintenancePeriod;
	}
	public void setEquipment_MaintenancePeriod(String equipment_MaintenancePeriod) {
		Equipment_MaintenancePeriod = equipment_MaintenancePeriod;
	}
	public String getEquipment_RepairsDate() {
		return Equipment_RepairsDate;
	}
	public void setEquipment_RepairsDate(String equipment_RepairsDate) {
		Equipment_RepairsDate = equipment_RepairsDate;
	}
	public String getEquipment_Tel() {
		return Equipment_Tel;
	}
	public void setEquipment_Tel(String equipment_Tel) {
		Equipment_Tel = equipment_Tel;
	}
	public String getEquipment_Mobile() {
		return Equipment_Mobile;
	}
	public void setEquipment_Mobile(String equipment_Mobile) {
		Equipment_Mobile = equipment_Mobile;
	}
	public String getEquipment_FactoryNo() {
		return Equipment_FactoryNo;
	}
	public void setEquipment_FactoryNo(String equipment_FactoryNo) {
		Equipment_FactoryNo = equipment_FactoryNo;
	}
	public String getEquipment_Lifetime() {
		return Equipment_Lifetime;
	}
	public void setEquipment_Lifetime(String equipment_Lifetime) {
		Equipment_Lifetime = equipment_Lifetime;
	}
	public String getEquipment_Director() {
		return Equipment_Director;
	}
	public void setEquipment_Director(String equipment_Director) {
		Equipment_Director = equipment_Director;
	}
	public String getEquipment_UserID() {
		return Equipment_UserID;
	}
	public void setEquipment_UserID(String equipment_UserID) {
		Equipment_UserID = equipment_UserID;
	}
	public String getEquipment_AddDate() {
		return Equipment_AddDate;
	}
	public void setEquipment_AddDate(String equipment_AddDate) {
		Equipment_AddDate = equipment_AddDate;
	}
	public String getEquipment_GUID() {
		return Equipment_GUID;
	}
	public void setEquipment_GUID(String equipment_GUID) {
		Equipment_GUID = equipment_GUID;
	}
	public String getEquipment_Memo() {
		return Equipment_Memo;
	}
	public void setEquipment_Memo(String equipment_Memo) {
		Equipment_Memo = equipment_Memo;
	}
	public Float getEquipment_Fault() {
		return Equipment_Fault;
	}
	public void setEquipment_Fault(Float equipment_Fault) {
		Equipment_Fault = equipment_Fault;
	}
	public Integer getEquipment_Num() {
		return Equipment_Num;
	}
	public void setEquipment_Num(Integer equipment_Num) {
		Equipment_Num = equipment_Num;
	}
	public String getEquipment_Project() {
		return Equipment_Project;
	}
	public void setEquipment_Project(String equipment_Project) {
		Equipment_Project = equipment_Project;
	}
	
}
