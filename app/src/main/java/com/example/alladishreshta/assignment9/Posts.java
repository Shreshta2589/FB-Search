package com.example.alladishreshta.assignment9;

public class Posts {
    public String name,date,message,image;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Posts(String name,String date,String image,String message){

        this.setName(name);
        this.setImage(image);
        this.setDate(date);
        this.setMessage(message);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
