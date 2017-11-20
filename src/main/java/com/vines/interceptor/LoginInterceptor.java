package com.vines.interceptor;

import com.vines.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 恩言 on 2017/11/16.
 */
public class LoginInterceptor implements HandlerInterceptor {
    private static final String[] LOING_URI={"/login","/loginIn"};
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isNotInterceptor=false;
        String servletPath=request.getServletPath();
        for(String str:LOING_URI ){
            if(servletPath.contains(str)){
                //不拦截
                isNotInterceptor=true;
            }
        }
        if(!isNotInterceptor){
            User user = (User) request.getSession().getAttribute("user");
            if(user==null){
                request.getRequestDispatcher("/login").forward(request, response);
            }else{
                System.out.println("拦截请求:用户已经登录，可以成功浏览网页");
                isNotInterceptor = true;
            }

        }
        return isNotInterceptor;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
