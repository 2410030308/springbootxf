package com.yuan.common;

import lombok.Data;

/**
 * @author yuan
 */



@Data
    public class Result<T> {
        private long code;
        private String message;
        private T data;
}
