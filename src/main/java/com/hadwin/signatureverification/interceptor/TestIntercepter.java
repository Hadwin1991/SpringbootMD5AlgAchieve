package com.hadwin.signatureverification.interceptor;

import com.hadwin.signatureverification.utils.ParamSignUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @desc 自定义拦截器，使用 @Component 让 Spring 管理其生命周期
 * @Author Hadwin1991
 * @date 2018/10/16
 * @return
 */
@Component
public class TestIntercepter  implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("------preHandle-----");
        HashMap map = ParamSignUtils.getRequestParamMap(request);
        String secret = "";
        String sign = "";
        String timeStamp = "";
        Long timeStampnum;
        if(!map.containsKey("key"))
        {
            return false;
        }
        else
        {
            map.remove("key");
        }

        if(!map.containsKey("sign"))
        {
            return false;
        }
        else
        {
            sign = (String)map.get("sign");
            map.remove("sign");
        }

        if(!map.containsKey("secret"))
        {
            return false;
        }
        else
        {
            secret = (String)map.get("secret");
        }

        if(!map.containsKey("timeStamp"))
        {
            return false;
        }
        else
        {
            timeStamp = (String)map.get("timeStamp");

            timeStampnum = Long.parseLong(timeStamp);
            long t2 = System.currentTimeMillis();
            if((t2 - timeStampnum) > 5*60*1000)
            {
                return false;
            }
            map.remove("timeStamp");
        }

        String Sign1 = ParamSignUtils.sign(map, secret);
        if (Sign1.equals(sign)) {
            return true;
        }

        return false;// 只有返回true才会继续向下执行，返回false取消当前请求
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("------postHandle-----");
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("------afterCompletion-----");
    }
}
