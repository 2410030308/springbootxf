package com.yuan.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuan
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
        private long code;
        private String message;
        private Object data;
        public static Result success(String message){
                return new Result(200,message,null);
        }

        public static Result success(String message, Object obj){
                return new Result(200,message,obj);
        }
        public static Result success( Object obj){
                return new Result(200,"成功！",obj);
        }

        public static Result error(String message){
                return new Result(500,message,null);
        }

        public static Result error(String message, Object obj){
                return new Result(500,message,obj);
        }
        public static Result error(Object obj){
                return new Result(500,"失败！",null);
        }
}
