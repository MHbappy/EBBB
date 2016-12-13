package com.blood.bappy.ebbb;

/**
 * Created by bappy on 12/13/16.
 */

public class EmergencyBlood {

    private String image;
    private String bloodGroup;
    private String name;
    private String phone;
    private String Location;

    public EmergencyBlood(){

    }

    public EmergencyBlood(String image, String bloodGroup, String name, String phone, String location) {
        this.image = image;
        this.bloodGroup = bloodGroup;
        this.name = name;
        this.phone = phone;
        Location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
