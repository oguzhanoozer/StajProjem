package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="guncelleOnay")
@SessionScoped
public class GuncelleOnay {

	
	private int islemId;
	private String durum;
	private String aciklama;
	
	int iptal_id=2;
	int birinciUyariId=3;
	int ikinciUyariId=4;
	int DisiplinId=5;
	int bilgilendirmeId=6;
	int devamId=7;
	
	
	
	public int getIslemId() {
		return islemId;
	}
	public void setIslemId(int islemId) {
		this.islemId = islemId;
	}
	
	
	public String getDurum() {
		return durum;
	}
	public void setDurum(String durum) {
		this.durum = durum;
	}
	
	
	public String getAciklama() {
		return aciklama;
	}
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	
			
		public String guncelleOnaylama() {
		
				PreparedStatement ps=null;
		    	Connection con=null;
			
		    
			try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
			 
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		         con = DriverManager.getConnection(url,"hr","oguzhan");	
		     
			    ps = con.prepareStatement("UPDATE BILGI SET DURUM=? , ACIKLAMA = ? , DURUM_ID = ? where islemid = ?");
			    

			    
			    if(getDurum().equalsIgnoreCase("iptal **"))
			    {
			    	ps.setInt(3, iptal_id);
			    }
			    
			    else if(getDurum().equalsIgnoreCase("1.Uyarı Cezası **"))
			    {
			    	ps.setInt(3, birinciUyariId);
			    }
			    
			    else  if(getDurum().equalsIgnoreCase("2.Uyarı Cezası **"))
			    {
			    	ps.setInt(3, ikinciUyariId);
			    }
			    
			    else  if(getDurum().equalsIgnoreCase("Disipline Sevk Edildi **"))
			    {
			    	ps.setInt(3, DisiplinId);
			    }
			    
			    else   if(getDurum().equalsIgnoreCase("Bilgilendirme yapıldı **"))
			    {
			    	ps.setInt(3, bilgilendirmeId);
			    }
			    
			    else    if(getDurum().equalsIgnoreCase("Devam Ediyor **"))
			    {
			    	ps.setInt(3, devamId);
			    }
			    
			    
			    
			    ps.setString(1, getDurum());
			    ps.setString(2, getAciklama());
			    
			    ps.setInt(4, getIslemId());
			    
			   ps.executeUpdate();
   
		        
				}
		
			    catch(ClassNotFoundException | SQLException exception)
		        {
		            System.out.println(exception);	                 
		        }
		        finally 
		        {
		            try {
		                if(con!=null){
		                    con.close();
		                }
		                if(ps!=null){
		                    ps.close();
		                }
		            }
		            catch(SQLException sqlException)
		            {
		                System.out.println(sqlException);
		            }
		        	}
			    	
			return "admin.xhtml?faces-redirect=true";
			
		   }

	}
	
	

