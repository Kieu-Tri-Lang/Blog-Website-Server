package com.kieutrilang.blogwebsite.exception;

import java.util.Map;

import lombok.Builder;

@Builder
public class ApiException {

    Map<String, String> errors;
}
