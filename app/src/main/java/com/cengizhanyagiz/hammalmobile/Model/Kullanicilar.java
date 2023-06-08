package com.cengizhanyagiz.hammalmobile.Model;


public class Kullanicilar {
    private String  resim,isim,yas,meslek,email;
    private Object state;

    public Kullanicilar(){

    }

    public Kullanicilar(String resim, String isim, String yas, String meslek, String email,Object state) {
        this.resim = resim;
        this.isim = isim;
        this.yas = yas;
        this.meslek = meslek;
        this.email = email;
        this.state= state;
    }

    public String getResim() {return resim;}

    public void setResim(String resim) {
        this.resim = resim;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getYas() {
        return yas;
    }

    public void setYas(String yas) {
        this.yas = yas;
    }

    public String getMeslek() {
        return meslek;
    }

    public void setMeslek(String meslek) {
        this.meslek = meslek;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getState() {return state;}

    public void setState(Object state) {this.state = state;}
}
