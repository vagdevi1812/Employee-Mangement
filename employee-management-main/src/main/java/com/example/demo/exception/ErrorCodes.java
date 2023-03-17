package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ErrorCodes {

    SYSTEM_EXCEPTION("DE001", 500, "system.exception"),
    BAD_REQUEST("DE002", 400, "bad.request"),
    INVALID_TYPE("DE003", 400 ,"invalid.type"),
    MEDIA_TYPE_NOT_SUPPORTED("DE004", 415, "media.not.supported"),
    ACCESS_DENIED("DE005", 403, "invalid.access"),
    REQUESTED_METHOD_NOT_ALLOWED("DE006", 405,  "method.not.allowed");

    private final String errorCode;
    private final int httpStatus;
    private final String errorDescription;

}