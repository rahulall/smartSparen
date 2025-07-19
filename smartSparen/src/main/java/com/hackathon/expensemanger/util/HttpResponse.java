package com.hackathon.expensemanger.util;

import lombok.Data;

@Data
public class HttpResponse<T> {
    String status;
    String message;
    T object;
}
