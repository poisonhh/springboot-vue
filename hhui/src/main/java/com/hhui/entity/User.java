package com.hhui.entity;

public class User {

    private Long Id;
    private String name;
    private Integer aga;
    private String info;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAga() {
        return aga;
    }

    public void setAga(Integer aga) {
        this.aga = aga;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
