package com.annotations.demo.util;

import lombok.Data;

@Data
public class HttpResponse<T> {
    String status;
    String message;
    T object;
}
