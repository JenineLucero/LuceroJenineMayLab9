package com.lucero.jeninemay;

public class FireBase {
    private String Firstname;
    private String Lastname;
    private Integer ave;

    public FireBase(){

    }

    public FireBase(String lastname, String firstname, Integer ave){
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.ave = ave;
    }

    public Integer getAve() {
        return ave;
    }

    public void setAve(Integer ave) {
        this.ave = ave;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    public String getFirstname() {

        return Firstname;
    }

    public void setFirstname(String Firstname) {

        this.Firstname = Firstname;
    }

}
