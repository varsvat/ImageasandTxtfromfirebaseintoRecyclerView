package com.example.celebrare;

public class Model {

    private String name , College;
    private String imageurl;


    public Model(){}

    public Model(String name, String College , String imageurl) {
        this.name = name;
        this.College = College;
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCollege() {
        return College;
    }

    public void setCollege(String College) {
        this.College = College;
    }

    public String getimageurl() {
        return imageurl;
    }

    public void setimageurl(String imageurl) {
        imageurl = imageurl;
    }
}
