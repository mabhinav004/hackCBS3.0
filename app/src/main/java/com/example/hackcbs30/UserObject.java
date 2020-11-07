package com.example.hackcbs30;

public class UserObject {
    String name;
    String age;
    String number;
    String BloodGroup;
    String Gender;

    public UserObject(String name, String age, String number, String bloodGroup, String gender) {
        this.name = name;
        this.age = age;
        this.number = number;
        BloodGroup = bloodGroup;
        Gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public UserObject(String s, String name, String phone) {
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getNumber() {
        return number;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public String getGender() {
        return Gender;
    }
}
