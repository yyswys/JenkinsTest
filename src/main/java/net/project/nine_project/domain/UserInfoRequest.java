package net.project.nine_project.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfoRequest {
    private String name;
    private String sex;
    private int age;
    private String job;
    @JsonProperty("password_old")
    private String passwordOld;
    @JsonProperty("password_new")
    private String passwordNew;

    @Override
    public String toString() {
        return "UserInfoRequest{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                ", passwordOld='" + passwordOld + '\'' +
                ", passwordNew='" + passwordNew + '\'' +
                '}';
    }

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

}
