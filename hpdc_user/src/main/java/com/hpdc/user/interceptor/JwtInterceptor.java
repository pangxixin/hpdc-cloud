package com.hpdc.user.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");
        //基本规则是全放行，具体能否执行在具体的操作中去判断。
        //拦截器只是负责把请求头中包含token的令牌进行解析验证。
        final String authHeader = request.getHeader("Authorization");
        //如果token不为空，并且以"Bearer "开头
        if (!StringUtils.isEmpty(authHeader) && authHeader.startsWith("Bearer ")) {
            //得到token
            String token = authHeader.substring(7);
            //对token进行验证
            try {
                //解析令牌，得到其中的roles
                Claims claims = jwtUtil.parseJWT(token);
                String roles = (String) claims.get("roles");
                //如果是管理员
                if (!StringUtils.isEmpty(roles) && roles.equals("admin")) {
                    //将token信息放到request域中，用于需要admin角色的操作(具体能否执行还是在业务中判断)
                    request.setAttribute("claims_admin", token);
                }
                //如果是普通用户
                if (!StringUtils.isEmpty(roles) && roles.equals("user")) {
                    //将token信息放到request域中，用于需要admin角色的操作(具体能否执行还是在业务中判断)
                    request.setAttribute("claims_user", token);
                }
            } catch (Exception e) {
                throw new RuntimeException("令牌无效！");
            }

        }
        return true;
    }
}
