package com.code.ExtractTableToCSV;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

 class JDBCCopyFullTable
{
private  String url;
private  String uname;
private  String pass;
private  String forName;


public void setUrl(String url) {
	this.url = url;
}

public void setUname(String uname) {
	this.uname = uname;
}

public void setforName(String forName) {
	this.forName = forName;
}

public void setPass(String pass) {
	this.pass = pass;
}

public	  void  getTable(String tableName, String filepath) throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
		try
		{
			
			String query="select * from "+tableName; 
			Class.forName(forName);
			
			Connection con= DriverManager.getConnection(url,uname,pass);
			Statement st= con.createStatement();
			ResultSet rs=st.executeQuery(query);			
			for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
			{
				if(i<rs.getMetaData().getColumnCount()) 
				{
					bw.write("\""+rs.getMetaData().getColumnName(i)+"\""+",");
				}
				else
				{
					bw.write("\""+rs.getMetaData().getColumnName(i)+"\""+"\n");
				}
			}	
			while(rs.next())
			{
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
				{
					if(i<rs.getMetaData().getColumnCount()) 
					{
						bw.write("\""+rs.getString(i)+"\""+",");
					}
					else
					{
						bw.write("\""+rs.getString(i)+"\""+"\n");				
					}
				}
			}
			bw.close();
			con.close();
			System.out.println("Table is copied to csv file successfully!");
		}
		catch(Exception e) 
		{
			System.out.println("Copy Table Activity Failed:\n"+e);
		}
	}
}  	  

public class ExtractTableToCSV {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
	
		JDBCCopyFullTable copytable = new JDBCCopyFullTable();
		
		copytable.setforName(SupportFunctions.getProperties().getProperty("forName"));
		copytable.setUname(SupportFunctions.getProperties().getProperty("username"));
		copytable.setPass(SupportFunctions.getProperties().getProperty("password"));
		copytable.setUrl(SupportFunctions.getProperties().getProperty("url"));
		copytable.getTable(SupportFunctions.getProperties().getProperty("tablename"), SupportFunctions.getProperties().getProperty("filepath"));
		
	}



}
