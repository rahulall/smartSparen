package com.hackathon.expensemanger.bean;

import lombok.Data;

@Data
public class CategoryResponse {
    public int id;
    public String name;
    public String date;
    public double amount;
    public String insightData;
}
