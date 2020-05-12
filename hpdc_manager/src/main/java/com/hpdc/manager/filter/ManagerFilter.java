package com.hpdc.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@Component
public class ManagerFilter extends ZuulFilter {
    @Autowired
    private JwtUtil jwtUtil;

    //在请求前(pre)或后(post)
    @Override
    public String filterType() {
        return "pre";
    }

    //执行顺序（数字越小越先执行）
    @Override
    public int filterOrder() {
        return 0;
    }

    //过滤器是否开启
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器执行的操作（return 任何object的值都表示继续执行）
     * setsendzullResponse(false)表示不再继续执行
     * 经过网关后请求头信息丢失，需要进行转发操作
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("经过后台zuul过滤器");

        // 得到request上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        // 得到request域
        HttpServletRequest request = ctx.getRequest();
        // 每个经过网关的请求都有两次转发，第一次是zuul内部分发的请求来找到具体访问路径，这个请求固定带OPTIONS，必须放行。
        if (request.getMethod().equals("OPTIONS")) {
            return null;
        }
        // 如果是登录的请求就直接放行，不应该要验证token.
        String url = request.getRequestURL().toString();
        if (url.indexOf("/admin/login") > 0) {
            System.out.println("登陆页面" + url);
            return null;
        }
        // 从request域中得到头信息
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null) {
                if ("admin".equals(claims.get("roles"))) {
                    ctx.addZuulRequestHeader("Authorization", authHeader);
                    return null;
                }
            }
        }
        ctx.setSendZuulResponse(false);//终止运行
        ctx.setResponseStatusCode(403);//设置状态码
        ctx.setResponseBody("sorry,您无权访问此资源。");
        ctx.getResponse().setContentType("text/html; charset=utf-8");
        return null;
    }
}
