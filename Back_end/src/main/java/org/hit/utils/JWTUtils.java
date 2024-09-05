package org.hit.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Calendar;
import java.util.Map;

@Schema(description = "JWT工具类")
public class JWTUtils {
    //定义自己的密钥
    private static String TOKEN = "token!DASD(#$dsad%$#.";

    /**
     * 生成token
     * @param map 传入的有效负荷
     * @return
     */
    public static String genToken(Map<String, String> map){
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k, v);
        });
        Calendar instance = Calendar.getInstance();
        //定义过期时间
        instance.add(Calendar.DATE, 7);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(TOKEN)).toString();
    }

    /**
     * 验证获取token中的有效负载，验证失败返回null
     * @param token
     * @return
     */
    public static DecodedJWT getToken(String token){
        if (token == null){
            throw new RuntimeException("token为空");
        }
        return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }
}
