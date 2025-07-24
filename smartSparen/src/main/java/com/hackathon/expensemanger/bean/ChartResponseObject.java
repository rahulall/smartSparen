package com.hackathon.expensemanger.bean;

import lombok.Data;

import java.util.List;

@Data
public class ChartResponseObject {
    List<CategoryResponse> barResponseList;
    List<CategoryResponse> pieResponseList;
}
