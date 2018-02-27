package com.project;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

@ManagedBean(name="user")
@SessionScoped

public class UserBean {
	
	public  String ad;
	public  Date olaySaati ;
	
	public Date getOlaySaati() {
		return olaySaati;
	}

	public void setOlaySaati(Date olaySaati) {
		this.olaySaati = olaySaati;
	}	

	public  String olay;
	private Part file;
	public String yuklemeDurumu ;

	public String ext = null;
	public boolean upload ;
	
	
	public boolean isUpload() {
		return upload;
	}

	public void setUpload(boolean upload) {
		this.upload = upload;
	}
	

	public String getYuklemeDurumu() {
		return yuklemeDurumu;
	}

	public void setYuklemeDurumu(String yuklemeDurumu) {
		this.yuklemeDurumu = yuklemeDurumu;
	}




	
	int i=0;
	 
	 private byte[] dataArray;

		public void setDataArray(String filePath) {
			  Path path = Paths.get(filePath);	 
			try {
				this.dataArray = Files.readAllBytes(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		public byte[] getDataArray() {return this.dataArray;}
				
		public  String getAd() {
			return ad;
		}
		
		public void setAd(String ad) {
			this.ad = ad;
		}
	
		
		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}

		
		
		public  String getOlay() {
			return olay;
		}
		public void setOlay(String olay) {
			this.olay = olay;
		}
		

	    public Part getFile() {
	        return file;
	    }

	    public void setFile(Part file) {
	        this.file = file;
	    }
	    
	    
	    public  String uzantiBul() {
			
			String s = getFileName(file);
			int i = s.lastIndexOf('.');
	 
			if (i > 0 && i < s.length() - 1) {
				ext = s.substring(i + 1).toLowerCase();
			}
			return ext;
			
	    }
	    
	   
	    public String getFileName(Part part)
	    {
	   
	    	
	        for(String cd:part.getHeader("content-disposition").split(";"))
	            if(cd.trim().startsWith("filename")){
	                String filename=cd.substring(cd.indexOf('=')+1).trim().replace("\"", "");
	                return filename;
	            }
	        return "";
	                
	    }
	    
	    public String reset() {
	    	setAd("");
	    	setOlay("");
	    	setOlaySaati(null);
	    	setYuklemeDurumu("");
	    	return "index.xhtml?faces-redirect=true";
	    	
	    }
	    
	    public String outcome() {
	    
	    	return 	"mesaj.xhtml?faces-redirect=true";
	    	
	    }
	
    	public void yuklemeOK() {
	    	if(isUpload()==true) {
	    		yuklemeDurumu = "Dosyaniz Yuklenmistir !";
	    	}else {
	    		yuklemeDurumu = "Dosyaniz Yuklenememistir !";
	    	}
		}
	 
	 
	    public void upload()
	    {
	        try{
	     
	        
	        file.write("W:\\"+getFileName(file));	

	       
	        
	        upload = true;
	        	
	        setDataArray("W:\\"+getFileName(file));
	        //  setDataArray(getFileName(file));
	        }
	        catch(Exception ex)
	        {
	            System.err.print(ex);
	        }
	        
	   
	        
	        yuklemeOK();
	        
	    }
	    
	    

	    public void servisleriCagir(ActionEvent e) throws Exception{    	
	    	veritabani(e);
	    
	       }
	    		
	   
	   
	    
	    public void veritabani(ActionEvent e) throws Exception{		    	
	    	Veritabani v= new Veritabani(this);	
	    	v.veritabani_islemleri(e);	  
	    	}
	    
	    
	
		
	
	
}
