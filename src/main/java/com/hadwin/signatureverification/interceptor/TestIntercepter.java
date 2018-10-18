package com.hadwin.signatureverification.interceptor;

import com.hadwin.signatureverification.utils.ConstantClassField;
import com.hadwin.signatureverification.utils.ParamSignUtils;
import com.hadwin.signatureverification.utils.RSAUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @desc 自定义拦截器，完成API接口签名验证功能
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

        String secret = "";
        String strSign4Request = "";
        String timeStamp = "";
        String strRSAValue = "";
        String strBase64Value = "";

        /*****************************  1、三次公钥解密  ***********************************/

        //获取request中的RSA加密后的信息
        HashMap rsavaluemap = ParamSignUtils.getRequestParamMap(request);

        if(!rsavaluemap.containsKey("RSAvalue"))
        {
            return false;
        }
        else
        {
            strRSAValue = (String)rsavaluemap.get("RSAvalue");
        }

        //使用规定好的publickey进行RSA公钥解密
        byte[] bRSAValue = RSAUtils.toBytes(strRSAValue);
        byte[] decodedData = RSAUtils.decryptByPublicKey(bRSAValue, ConstantClassField.publicKey);
        strBase64Value = new String(decodedData);

        /*****************************  2、二次base64解密  ***********************************/

        //对数据进行base64二次解密
        Map<String, String> map = ParamSignUtils.decodeBase64TextToMap(strBase64Value);

        //对数据参数的有效性进行判断处理
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
            strSign4Request = (String)map.get("sign");
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
            map.remove("timeStamp");
        }

        /*****************************  3、一次MD5签名校验  ***********************************/

        //对数据进行一次MD5签名校验
        String strSign4calc = ParamSignUtils.sign(map, secret, timeStamp);
        if (strSign4calc.equals(strSign4Request)) {
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
