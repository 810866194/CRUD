package com.woniu.util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import com.mchange.v2.c3p0.ComboPooledDataSource;
public class JdbcUtils {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql:///user1";
	private static String user = "root";
	private static String pwd = "liukang951121";
	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	public static Connection start() throws SQLException{
		return DriverManager.getConnection(url, user,pwd);
	}
	public static void close(ResultSet rs,PreparedStatement ps,Connection c){
			try {
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(c!=null){
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
	public static String tableNames() throws DocumentException{
		String str = null;
		SAXReader reader = new SAXReader();
		Document document = reader.read("src/c3p0-config.xml");
		Element root = document.getRootElement();
		Iterator<Element> i = root.elementIterator();
		while(i.hasNext()){
			Element element = i.next();
			Iterator<Element> i2 = element.elementIterator();
			while(i2.hasNext()){
				str = i2.next().getStringValue();		
			}	
		}
		System.out.println(str);
		return str;
	}
	public static void createVO() throws DocumentException{
		String tables = tableNames();
		String[] tableArr = tables.split(":");
		String pack = "com.woniu.bean";
		String packDir = pack.replace(".", "\\");
		File dir = new File("src",packDir);
		if(!dir.exists()){
			dir.mkdirs();
		}
		for(int i=0;i<tableArr.length;i++){
			String tableName = tableArr[i];
			File targetFile = new File(dir,turnFirstChar(tableName)+".java");
			StringBuffer strB = new StringBuffer();
			strB.append("package "+pack+";\n");
			strB.append("import java.util.Date;\n");
			strB.append("public class "+turnFirstChar(tableName)+"{\n");
			try {
				Connection c = start();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery("select * from "+tableName);
				ResultSetMetaData rsmd = rs.getMetaData();
				StringBuffer strB2 = new StringBuffer();
				StringBuffer strB3 = new StringBuffer();
				for(int j=1;j<=rsmd.getColumnCount();j++){
					String colName = rsmd.getColumnName(j).toLowerCase();
					String colType = rsmd.getColumnTypeName(j);
					String javaType = "";
					switch(colType){
						case "INT":
							javaType = "Integer";
							break;
						case "CHAR":
							javaType = "String";
							break;	
						case "VARCHAR":
							javaType = "String";
							break;
						case "TEXT":
							javaType = "String";
							break;
						case "TIMESTAMP":
							javaType = "Date";
							break;
						case "DATETIME":
							javaType = "Date";
							break;
						case "DECIMAL":
							javaType = "double";
							break;
					}
					strB.append("private "+javaType+" "+colName+";\n");
					strB.append("public "+javaType+" get"+turnFirstChar(colName)+"(){\n");
					strB.append("\t return this."+colName+";\n");
					strB.append("}\n");
					strB.append("public void set"+turnFirstChar(colName)+"("+javaType+" "+colName+"){\n");
					strB.append("\t this."+colName+"="+colName+";\n");
					strB.append("}\n");	
					strB2.append(javaType+" "+colName+",");
					strB3.append("\t this."+colName+"="+colName+";\n");
				}	
				strB.append("public "+tableName+"(){\n");
				strB.append("\t super();\n");
				strB.append("}\n");
				String str = strB2.toString().substring(0,strB2.lastIndexOf(","));
				strB.append("public "+tableName+"("+str+"){\n");
				strB.append("\t super();\n");
				strB.append(strB3.toString());
				strB.append("}\n");	
				strB.append("}");	
				FileWriter fw = new FileWriter(targetFile);
				fw.write(strB.toString());
				fw.flush();
				fw.close();		
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}	
	}
	public static String turnFirstChar(String tableName){
		char[] cs = tableName.toCharArray();
		if(cs[0]>=97){
			cs[0] -= 32;
		}
		return new String(cs);
	}
	
	public static void main (String[] args) throws DocumentException{
		createVO();	
	}
	
}
