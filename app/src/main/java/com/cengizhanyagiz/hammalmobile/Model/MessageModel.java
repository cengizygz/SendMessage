package com.cengizhanyagiz.hammalmobile.Model;

public class MessageModel {

    String userId,otherId,type,time,text,from;
    Boolean seen;

    public MessageModel(){

    }

    public MessageModel(String from, Boolean seen, String text, String time, String type) {

        this.type = type;
        this.time = time;
        this.text = text;
        this.seen = seen;
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }


    public String getType() {
        return type;
    }

    public void setType(String textType) {
        this.type = textType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String date) {
        this.time = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }
}
