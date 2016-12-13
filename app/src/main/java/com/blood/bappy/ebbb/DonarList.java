package com.blood.bappy.ebbb;

/**
 * Created by bappy on 12/12/16.
 */

public class DonarList {

    private String bloodGroup;
    private String name;
    private String address;
    private String sex;
    private String occupation;
    private String date;
    private String email;
    private String phone;
    private String nationalIdNo;
    private String age;

    public DonarList( ){

    }

    public DonarList(String bloodGroup, String name, String address, String sex, String occupation, String phone, String nationalIdNo, String email, String date, String age) {
        this.bloodGroup = bloodGroup;
        this.name = name;
        this.address = address;
        this.sex = sex;
        this.occupation = occupation;
        this.phone = phone;
        this.nationalIdNo = nationalIdNo;
        this.email = email;
        this.date = date;
        this.age = age;
    }



    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNationalIdNo() {
        return nationalIdNo;
    }

    public void setNationalIdNo(String nationalIdNo) {
        this.nationalIdNo = nationalIdNo;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
