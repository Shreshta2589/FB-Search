package com.example.alladishreshta.assignment9;

import java.io.Serializable;

public class Entries  implements Serializable {
    private String name;
    private String image;
    private String id;
    private String type;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public Entries(String name){
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOn(Boolean on) { on=false;}

    public void getOn(){return;}
}
