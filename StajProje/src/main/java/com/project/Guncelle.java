package com.project;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="guncelle")
@SessionScoped
public class Guncelle {
	
	
	
	private int veriID;
	
	private Veriler veri =new Veriler();

	public Veriler getVeri() {
		return veri;
	}

	public void setVeri(Veriler veri) {
		this.veri = veri;
	}
	
	public int getVeriID() {
		return veriID;
	}

	public void setVeriID(int veriID) {
		this.veriID = veriID;
	}

		
		
		@SuppressWarnings("unused")
		public String  veriGuncelle() throws SQLException {
	
			Connection dbConnection = null;
			Statement statement = null;
	
			String selectTableSQL = "SELECT AD,OLAY,OLAYSAAT,DURUM,ACIKLAMA FROM BILGI where islemid = "+"'"+ veriID +"'"; 
	
			try {
				dbConnection = getDBConnection();
				statement = dbConnection.createStatement();
	
				ResultSet rs = statement.executeQuery(selectTableSQL);
	
				while (rs.next()) {
	
			
			    	    
			    		veri.setAciklama(rs.getString("ACIKLAMA"));
			    		veri.setIslemId(veriID);
			    		veri.setAd(rs.getString("AD"));
			    		veri.setOlay(rs.getString("OLAY"));
			    		veri.setOlaySaat(rs.getString("OLAYSAAT"));
			    		veri.setDurum(rs.getString("DURUM"));
	
				}
	
			} catch (SQLException e) {
	
				System.out.println(e.getMessage());
	
			} finally {
	
				if (statement != null) {
					statement.close();
				}
	
				if (dbConnection != null) {
					dbConnection.close();
				}
	
			}
			return "guncelle.xhtml?faces-redirect=true";
	
		}
		
		
		
		
		private static Connection getDBConnection() {
	
			Connection dbConnection = null;
	
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				
			} catch (ClassNotFoundException e) {
	
				System.out.println(e.getMessage());
	
			}
	
			try {
	
			
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
				dbConnection = DriverManager.getConnection(url,"hr","oguzhan");	
				

				return dbConnection;
	
			} catch (SQLException e) {
	
				System.out.println(e.getMessage());
	
			}
	
			return dbConnection;
	
		}
		
		
		
		}
	
	
	
