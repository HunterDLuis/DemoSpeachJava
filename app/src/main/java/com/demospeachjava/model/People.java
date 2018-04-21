package com.demospeachjava.model;

import android.graphics.drawable.Drawable;

public class People {
    private long id;
    private long user_id;
    private String avatar;
    private String firsname;
    private String lastname;
    private String email;
    private String telephone_private;
    private String cell_phone;
    private String address;
    private String state;
    private String fecha;
    private String ci;
    private String type_register;
    private int is_admin;
    private int is_priority;
    private String gender;

    public People() {
    }

    public People(String firsname, String lastname, String email, String telephone_private, String cell_phone, String address, String state, String fecha, String ci, String type_register, int is_priority, String gender) {
        this.firsname = firsname;
        this.lastname = lastname;
        this.email = email;
        this.telephone_private = telephone_private;
        this.cell_phone = cell_phone;
        this.address = address;
        this.state = state;
        this.fecha = fecha;
        this.ci = ci;
        this.type_register = type_register;
        this.is_priority = is_priority;
        this.gender = gender;
    }

    public People(String avatar, String firsname, String lastname, String email, String telephone_private, String cell_phone, String address, String state, String fecha, String ci, String type_register, int is_priority, String gender) {
        this.firsname = firsname;
        this.lastname = lastname;
        this.email = email;
        this.telephone_private = telephone_private;
        this.cell_phone = cell_phone;
        this.address = address;
        this.state = state;
        this.fecha = fecha;
        this.ci = ci;
        this.type_register = type_register;
        this.is_priority = is_priority;
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFirsname() {
        return firsname;
    }

    public void setFirsname(String firsname) {
        this.firsname = firsname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone_private() {
        return telephone_private;
    }

    public void setTelephone_private(String telephone_private) {
        this.telephone_private = telephone_private;
    }

    public String getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getType_register() {
        return type_register;
    }

    public void setType_register(String type_register) {
        this.type_register = type_register;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }

    public int getIs_priority() {
        return is_priority;
    }

    public void setIs_priority(int is_priority) {
        this.is_priority = is_priority;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", avatar='" + avatar + '\'' +
                ", firsname='" + firsname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", telephone_private='" + telephone_private + '\'' +
                ", cell_phone='" + cell_phone + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", fecha='" + fecha + '\'' +
                ", ci='" + ci + '\'' +
                ", type_register='" + type_register + '\'' +
                ", is_admin=" + is_admin +
                ", is_priority=" + is_priority +
                ", gender='" + gender + '\'' +
                '}';
    }
}
