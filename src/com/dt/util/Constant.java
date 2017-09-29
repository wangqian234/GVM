package com.dt.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Constant {
	public static int getFenyeNum(){
		return new Constant().preGetFenyeNum();
	}
	public static int getMonth3(){
		return Integer.parseInt(new Constant().getDataSource("dataSource.3month"));
	}
	public static int getMonth6(){
		return Integer.parseInt(new Constant().getDataSource("dataSource.6month"));
	}
	public static int getMonth9(){
		return Integer.parseInt(new Constant().getDataSource("dataSource.9month"));
	}
	public static int getYear1(){
		return Integer.parseInt(new Constant().getDataSource("dataSource.1year"));
	}
	public static int getYear2(){
		return Integer.parseInt(new Constant().getDataSource("dataSource.2year"));
	}
	private String getDataSource(String str){
		Properties prop=new Properties();
		InputStream inStream=getClass().getResourceAsStream("/hibernate.properties");
		try {
			prop.load(inStream);
			String num=prop.getProperty(str);
			return num;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "0";
		}
	}
	private Integer preGetFenyeNum(){
		Properties prop=new Properties();
		InputStream inStream=getClass().getResourceAsStream("/hibernate.properties");
		try {
			prop.load(inStream);
			String num=prop.getProperty("dataSource.FenyeNum");
			int i=Integer.parseInt(num);
			return i;	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 10;
		}
	}
}
