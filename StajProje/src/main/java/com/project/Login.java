package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="login")
@SessionScoped
public class Login {

	public String email;
	public String sifre;
	public String gecerlilik;
	public String sayfa;
	public boolean loginValid;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	
	public String getGecerlilik() {
		return gecerlilik;
	}

	public void setGecerlilik(String gecerlilik) {
		this.gecerlilik = gecerlilik;
	}


	public String getSayfa() {
		return sayfa;
	}

	public void setSayfa(String sayfa) {
		this.sayfa = sayfa;
	}
		
	public void veritabani_islemleri(){
			
			PreparedStatement ps=null;
	    	Connection con=null;
		
		try {
			
				Class.forName("oracle.jdbc.driver.OracleDriver");
		
	
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	            con = DriverManager.getConnection(url,"hr","oguzhan");
	            
				ps = con.prepareStatement("SELECT EMAIL,SIFRE FROM BILGIADMIN where EMAIL=? AND SIFRE=?");
		    ps.setString(1, email);
		    ps.setString(2, sifre);
		    
		    ResultSet rs = ps.executeQuery();
		    
		    while(rs.next()) {	
		    	loginValid = true;		    	
		    }
			
		    
		   con.close();
            
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
		    	
		    }

	


	public String giris()  {
		
		veritabani_islemleri();
		
		if(loginValid==true) 
		{
			
			sayfa="admin.xhtml?faces-redirect=true";
		}
		
		else {
			gecerlilik = "** Kullanýcý adý veya sifre yanlýs";
		}
		return sayfa;
			
		
	}
	

}

			
	
