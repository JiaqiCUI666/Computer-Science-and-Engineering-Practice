package org.hit.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Schema(description = "响应数据")
public class ResponseData<T> {
    @Schema(description = "状态码，200为成功")
    private int status;
    @Schema(description = "消息")
    private String message;
    @Schema(description = "数据，是json格式描述的类")
    private T data;
    @Schema(description = "token")
    private String token;
    @Schema(description = "是否成功，1为成功")
    private int success;

    public ResponseData(Builder<T> builder){
        this.status = builder.status;
        this.message = builder.message;
        this.data = builder.data;
        this.token = builder.token;
        this.success = builder.success;

    }

    public static <T> Builder<T> builder(){
        return new Builder<T>();
    }

    public static class Builder<T>{
        private int status;
        private String message;
        private T data;
        private String token;
        private int success = 1;

        public Builder<T> status(int status){
            this.status = status;
            return this;
        }

        public Builder<T> successs(){
            this.success = 1;
            return this;
        }

        public Builder<T> message(String message){
            this.message = message;
            return this;
        }

        public Builder<T> token(String token){
            this.token = token;
            return this;
        }

        public Builder<T> data(T data){
            this.data = data;
            return this;
        }

        public Builder<T> success(int success){
            this.success = success;
            return this;
        }

        public ResponseData<T> build(){
            return new ResponseData<>(this);
        }
    }




}



