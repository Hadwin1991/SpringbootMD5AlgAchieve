package com.hadwin.signatureverification.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.*;

public class ParamSignUtils {
    public static void main(String[] args)
    {
        HashMap<String, String> signMap = new HashMap<String, String>();
        signMap.put("a","01");
        signMap.put("c","02");
        signMap.put("b","03");
        String secret="test";
        //HashMap SignHashMap=ParamSignUtils.sign(signMap, secret);
        //System.out.println("SignHashMap:"+SignHashMap);
        List<String> ignoreParamNames=new ArrayList<String>();
        ignoreParamNames.add("a");
        //HashMap SignHashMap2=ParamSignUtils.sign(signMap,ignoreParamNames, secret);
        //System.out.println("SignHashMap2:"+SignHashMap2);
    }

    public static String sign(Map<String, String> paramValues,
                                               String secret) {
        return sign(paramValues, null, secret);
    }

    /**
     * @param paramValues
     * @param ignoreParamNames
     * @param secret
     * @return
     */

    public static String sign(Map<String, String> paramValues,
                                               List<String> ignoreParamNames, String secret) {
        try
        {
            HashMap<String, String> signMap = new HashMap<String, String>();
            StringBuilder sb = new StringBuilder();
            List<String> paramNames = new ArrayList<String>(paramValues.size());
            paramNames.addAll(paramValues.keySet());
            if (ignoreParamNames != null && ignoreParamNames.size() > 0)
            {
                for (String ignoreParamName : ignoreParamNames)
                {
                    paramNames.remove(ignoreParamName);
                }
            }
            Collections.sort(paramNames);
            sb.append(secret);
            for (String paramName : paramNames)
            {
                sb.append(paramName).append(paramValues.get(paramName));
            }
            sb.append(secret);
            byte[] md5Digest = getMD5Digest(sb.toString());
            String sign = byte2hex(md5Digest);
            signMap.put("appParam", sb.toString());
            signMap.put("appSign", sign);
            return sign;
        } catch (IOException e) {
            throw new RuntimeException("加密签名计算错误", e);
        }

    }

    public static String utf8Encoding(String value, String sourceCharsetName) {
        try {
            return new String(value.getBytes(sourceCharsetName), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static byte[] getSHA1Digest(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            bytes = md.digest(data.getBytes("UTF-8"));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse);
        }
        return bytes;
    }

    private static byte[] getMD5Digest(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data.getBytes("UTF-8"));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse);
        }
        return bytes;
    }


    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    public static HashMap<String, String> getRequestParamMap(HttpServletRequest request) {
        HashMap<String, String> map = new HashMap();
        //得到枚举类型的参数名称，参数名称若有重复的只能得到第一个
        Enumeration enums = request.getParameterNames();
        while (enums.hasMoreElements()) {
            String paramName = (String) enums.nextElement();
            String paramValue = request.getParameter(paramName);

            //形成键值对应的map
            map.put(paramName, paramValue);
        }
        return map;

    }
}
