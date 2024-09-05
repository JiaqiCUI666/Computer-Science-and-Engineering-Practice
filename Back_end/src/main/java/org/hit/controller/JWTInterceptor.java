package org.hit.controller;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Intercepts;
import org.hit.utils.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Component //当前拦截器对象由Spring创建和管理
@Slf4j
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> map = new HashMap<>();
        //获取请求头中令牌
        String token = request.getHeader("token");
        try {
            JWTUtils.getToken(token);//验证令牌
            return true;//放行请求
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("message","无效签名!");
        }catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("message","token过期!");
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("message","token算法不一致!");
        }catch (Exception e){
            e.printStackTrace();
            map.put("message","token无效!!");
        }
        map.put("status",200);//设置状态
        //将map 专为json  jackson
        map.put("success",0);
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }

}


