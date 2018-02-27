package com.project;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;



@SessionScoped
@ManagedBean(name="userController")
public class UserController {

		
		String file;
		String file_uzantisi;
	
		private int veriID;
		
		public int getVeriID() {
			return veriID;
		}
	
		public void setVeriID(int veriID) {
			this.veriID = veriID;
		}	
		
	
    public String download() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();

            Connection dbConnection = null;
			Statement statement = null;
	
			String selectTableSQL = "SELECT FILE_UZANTI, FILES FROM BILGI where islemid = "+"'"+ veriID +"'"; 
	
			try {
		
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
			    String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			    dbConnection = DriverManager.getConnection(url,"hr","oguzhan");
	            
				statement = dbConnection.createStatement();
	
			
			
				
				ResultSet rs = statement.executeQuery(selectTableSQL);
	
				while (rs.next()) {
					file_uzantisi = rs.getString("FILE_UZANTI");
		
					file= "C:\\Users\\otuke_000\\Music\\" + "(" + veriID  + ").ihbar ." + file_uzantisi+ "";
					
					
					
					
					  File image = new File(file);
				      FileOutputStream fos = new FileOutputStream(image);

				      byte[] buffer = new byte[1];
				      InputStream is = rs.getBinaryStream("FILES");
				      while (is.read(buffer) > 0) {
				        fos.write(buffer);
				      }
				      fos.close();
			    	   
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


            context.responseComplete();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        
        
        return 	"admin.xhtml?faces-redirect=true";
    }



	
}