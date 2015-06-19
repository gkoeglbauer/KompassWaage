package com.example.gkoeglbauer.kompasswaage;

/**
 * Created by gkoeglbauer on 19.06.2015.
 */
public class Class_Position{
    private String name;
    private int lgrad,bgrad;

    public Class_Position(String name, int lgrad, int bgrad) {
        this.name = name;
        this.lgrad = lgrad;
        this.bgrad = bgrad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLgrad() {
        return lgrad;
    }

    public void setLgrad(int lgrad) {
        this.lgrad = lgrad;
    }

    public int getBgrad() {
        return bgrad;
    }

    public void setBgrad(int bgrad) {
        this.bgrad = bgrad;
    }
}
