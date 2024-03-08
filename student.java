package com.example.myapplication;

public class student {
    private int id;
    private String firstname;
    private String lastname;
    private boolean male;
    private boolean female;
    private String department;
    private String pass1;
    private String pass2;
    //create a constructor


    public student(int id, String firstname, String lastname, String male, String female, String department, String pass1, String pass2) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.male = Boolean.parseBoolean(male);
        this.female = Boolean.parseBoolean(female);
        this.department = department;
        this.pass1 = pass1;
        this.pass2 = pass2;
    }



    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", male=" + male +
                ", female=" + female +
                ", department='" + department + '\'' +
                ", pass1='" + pass1 + '\'' +
                ", pass2='" + pass2 + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public boolean isFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }
}
