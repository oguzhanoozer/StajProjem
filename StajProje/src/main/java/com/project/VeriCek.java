package com.project;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="veriCek")
@SessionScoped
public class VeriCek {

	private String durumTipi;
	private String sql = "SELECT ISLEMID,AD,OLAY,OLAYSAAT,FILES,FILE_UZANTI,DURUM,ACIKLAMA FROM BILGI  order by islemid ";
	private String ihbarSql ;
	public String ihbarSqlAta;

	int ihbarSayisi;
	
	PreparedStatement ps=null;
	PreparedStatement psSqlIhbar=null;
	Connection con=null;
	
	private Veriler secilen = null;
	
	public VeriCek() throws SQLException {
		super();
		setIhbarSql("SELECT COUNT(*) AS total from BILGI");
		ihbarVeriSayisi();

	}
	
	public int getIhbarSayisi() {
		return ihbarSayisi;
	}

	public void setIhbarSayisi(int ihbarSayisi) {
		this.ihbarSayisi = ihbarSayisi;
	}
	
	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public String getIhbarSql() {
		return ihbarSql;
	}

	public void setIhbarSql(String ihbarSql) {
		this.ihbarSql = ihbarSql;
	}

	
	
	public String getDurumTipi() {
		return durumTipi;
	}

	public void setDurumTipi(String durumTipi) {
		this.durumTipi = durumTipi;
	}
	
	
		
	public Veriler getSecilen() {
		return secilen;
	}

	public void setSecilen(Veriler secilen) {
		this.secilen = secilen;
	}
	
	public String sayfa() {
		return "admin.xhtml?faces-redirect=true";
	}



	public void veritabaniBaglantisi() throws SQLException {
		 String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	        con = DriverManager.getConnection(url,"hr","oguzhan");		
			
			if(con==null)
				throw new SQLException("Can't get database connection");
	}

	public void ihbarSay() throws SQLException {
		
		  if( getDurumTipi().equalsIgnoreCase("Tüm İhbarlar"))
		    {
				 setSql("SELECT  COUNT(*) AS total,ISLEMID,AD,OLAY,OLAYSAAT,FILES,FILE_UZANTI,DURUM,ACIKLAMA FROM BILGI  order by islemid ");
				 setIhbarSql("SELECT COUNT(*) AS total from BILGI");
				
		    } 


		  else if( getDurumTipi().equalsIgnoreCase("Belirtilmemis"))
		 {
			  	setSql("SELECT ISLEMID,AD,OLAY,OLAYSAAT,FILES,FILE_UZANTI,DURUM,ACIKLAMA FROM BILGI WHERE DURUM_ID = 1 order by islemid ");
			  	setIhbarSql("SELECT COUNT(*) AS total from BILGI WHERE DURUM_ID = 1");
			 
		 } 
	
		 else if( getDurumTipi().equalsIgnoreCase("iptal"))
	    {
			 setSql("SELECT ISLEMID,AD,OLAY,OLAYSAAT,FILES,FILE_UZANTI,DURUM,ACIKLAMA FROM BILGI WHERE DURUM_ID = 2 order by islemid ");
			 setIhbarSql("SELECT COUNT(*) AS total from BILGI WHERE DURUM_ID = 2");
	    } 
	    
	    else if( getDurumTipi().equalsIgnoreCase("1.Uyarı Cezası"))
	    {
	    	setSql("SELECT ISLEMID,AD,OLAY,OLAYSAAT,FILES,FILE_UZANTI,DURUM,ACIKLAMA FROM BILGI WHERE DURUM_ID = 3 order by islemid ");
	    	setIhbarSql("SELECT COUNT(*) AS total from BILGI WHERE DURUM_ID = 3");
	    }
	    
	    else  if( getDurumTipi().equalsIgnoreCase("2.Uyarı Cezası"))
	    {
	    		setSql("SELECT ISLEMID,AD,OLAY,OLAYSAAT,FILES,FILE_UZANTI,DURUM,ACIKLAMA FROM BILGI WHERE DURUM_ID = 4 order by islemid ");
	    		setIhbarSql("SELECT COUNT(*) AS total from BILGI WHERE DURUM_ID = 4");
	    }
	    
	    else  if( getDurumTipi().equalsIgnoreCase("Disipline Sevk Edildi"))
	    {	
	    		setSql("SELECT ISLEMID,AD,OLAY,OLAYSAAT,FILES,FILE_UZANTI,DURUM,ACIKLAMA FROM BILGI WHERE DURUM_ID = 5 order by islemid ");
	    		setIhbarSql("SELECT COUNT(*) AS total from BILGI WHERE DURUM_ID = 5");
	    }
	    
	    else   if( getDurumTipi().equalsIgnoreCase("Bilgilendirme yapıldı"))
	    {
	    	setSql("SELECT ISLEMID,AD,OLAY,OLAYSAAT,FILES,FILE_UZANTI,DURUM,ACIKLAMA FROM BILGI WHERE DURUM_ID = 6 order by islemid ");
	    	setIhbarSql("SELECT COUNT(*) AS total from BILGI WHERE DURUM_ID = 6");
	    }
	    
	    else    if( getDurumTipi().equalsIgnoreCase("Devam Ediyor"))
	    {
	    	setSql("SELECT ISLEMID,AD,OLAY,OLAYSAAT,FILES,FILE_UZANTI,DURUM,ACIKLAMA FROM BILGI WHERE DURUM_ID = 7 order by islemid ");
	    	setIhbarSql("SELECT COUNT(*) AS total from BILGI WHERE DURUM_ID = 7");
	    
	    }
		
		  ihbarVeriSayisi();
		  
	}

	public void ihbarVeriSayisi() throws SQLException {

		veritabaniBaglantisi();

		psSqlIhbar= con.prepareStatement(getIhbarSql());
		
		ResultSet resulSqhIhbar = psSqlIhbar.executeQuery();
	
		while(resulSqhIhbar.next()){
		
			ihbarSayisi= resulSqhIhbar.getInt("total");
		  
		}
		
		setIhbarSayisi(ihbarSayisi);
		
	}
	
	public List<Veriler> getVeriList() throws SQLException{
		
		veritabaniBaglantisi();
		
	
		ps= con.prepareStatement(getSql());
		
		ResultSet result =  ps.executeQuery();

		List<Veriler> list = new ArrayList<Veriler>();
	
		secilen = new Veriler();
		
		while(result.next()){
			Veriler veri = new Veriler();
			
			veri.setAd(result.getString("AD"));
			veri.setOlay(result.getString("OLAY"));
			veri.setOlaySaat(result.getString("OLAYSAAT"));
			veri.setFiles((oracle.sql.BLOB)result.getBlob("FILES"));
			veri.setDosyaUzantisi(result.getString("FILE_UZANTI"));
			veri.setIslemId(result.getInt("ISLEMID"));
			veri.setDurum(result.getString("DURUM"));
			veri.setAciklama(result.getString("ACIKLAMA"));
			
			//store all data into a List
			list.add(veri);
		}

		return list;
	
	}
	
	public void select(Veriler e) {
		secilen = e;
	}

	
}