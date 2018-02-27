package com.project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name="veri")
@SessionScoped
public class Veritabani {
	
	private UserBean userBean = new UserBean();
	
	String date;
	int durum_id = 1;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	private String fileExt ;
	
	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	
	public Veritabani(UserBean userBean) {
		super();
		this.userBean = userBean;
	}
	
	

	public Veritabani() {
		super();
	}

	public void veritabani_islemleri(ActionEvent e) throws Exception{
		//userBean = new UserBean();
		
		PreparedStatement ps=null;
    	Connection con=null;
	
	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		    String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            con = DriverManager.getConnection(url,"hr","oguzhan");
						
		
            
		    ps = con.prepareStatement("INSERT INTO BILGI(ISLEMID,AD,OLAY,OLAYSAAT,FILES,FILE_UZANTI,DURUM,ACIKLAMA,DURUM_ID) VALUES(ISLEMID_AUTO_INCR.NEXTVAL,?,?,?,?,?,?,?,?)");
		  
		
		    ps.setString(1, userBean.getAd());
		    ps.setString(2, userBean.getOlay());
		    
		    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			 date = fmt.format(userBean.getOlaySaati());
			java.sql.Date dt = java.sql.Date.valueOf(new String(date));
		    
		
		    ps.setDate(3,dt);
		    ps.setBytes(4,  userBean.getDataArray());
		    ps.setString(5, userBean.ext);
		    ps.setString(6, "Belirtilmemis");
		    ps.setString(7, "Belirtilmemis");
		    ps.setInt(8, durum_id);
		    
		    userBean.setI(ps.executeUpdate());
		 
		 
		   con.close();
            
	}

		    catch(ClassNotFoundException | SQLException exception)
			{
		    	
	            System.out.println(exception);
	          
	          
	        }
	        finally½m 
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
		      
	
		    }
    
		
		
			
				
	
}
