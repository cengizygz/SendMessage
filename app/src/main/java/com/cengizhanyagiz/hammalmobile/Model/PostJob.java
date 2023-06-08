package com.cengizhanyagiz.hammalmobile.Model;

public class PostJob {
    public String adres ;
    public String imageUrl ;
    public String isim ;
    public String meslek;
    public String deneyim ;
    public String iletisim ;
    public String ogretim ;
    public String tanit ;
    public String yas ;
    public String calismasaat ;

    public PostJob(String adres, String imageUrl, String isim, String meslek, String deneyim, String iletisim, String ogretim, String tanit, String yas, String calismasaat) {
        this.adres = adres;
        this.imageUrl = imageUrl;
        this.isim = isim;
        this.meslek = meslek;
        this.deneyim = deneyim;
        this.iletisim = iletisim;
        this.ogretim = ogretim;
        this.tanit = tanit;
        this.yas = yas;
        this.calismasaat = calismasaat;
    }
}
