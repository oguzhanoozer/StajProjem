package com.project;
import java.io.File;
import java.sql.Blob;



public class Veriler {


	private int islemId;
	private String ad;
	private String olay;
	private String olaySaat;
	private String durum;
	private String aciklama;
	private Blob files;
	private String dosyaUzantisi;
	
	
	
	
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
	
	public int getIslemId() {
		return islemId;
	}
	public void setIslemId(int islemId) {
		this.islemId = islemId;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getOlay() {
		return olay;
	}
	public void setOlay(String olay) {
		this.olay = olay;
	}
	public String getOlaySaat() {
		return olaySaat;
	}
	public void setOlaySaat(String olaySaat) {
		this.olaySaat = olaySaat;
	}
	public Blob getFiles() {
		return files;
	}
	public void setFiles(Blob blob) {
		this.files = blob;
	}
	public String getDosyaUzantisi() {
		return dosyaUzantisi;
	}
	public void setDosyaUzantisi(String dosyaUzantisi) {
		this.dosyaUzantisi = dosyaUzantisi;
	}
	
	
	
	
}
