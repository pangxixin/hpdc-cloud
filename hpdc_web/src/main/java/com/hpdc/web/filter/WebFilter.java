package com.hpdc.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class WebFilter extends ZuulFilter {

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
        System.out.println("经过前台zuul过滤器");
        //得到上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        //得到request域
        HttpServletRequest request = ctx.getRequest();
        //得到头信息
        String authHeader = request.getHeader("Authorization");
        //判断是否有头信息
        if (authHeader != null && !"".equals(authHeader)) {
            //把头信息继续向下传
            ctx.addZuulRequestHeader("Authorization", authHeader);
        }
        return null;
    }
}
