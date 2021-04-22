package com.company.student.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateForm {

    @Expose
    @SerializedName("name")
    @JsonProperty("name")
    private String name;

    @Expose
    @SerializedName("address")
    @JsonProperty("address")
    private String address;

    @Expose
    @SerializedName("score")
    @JsonProperty("score")
    private double score;

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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}