package com.hadwin.signatureverification.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.*;

public class ParamSignUtils {
    /**
     * @desc 基于java8 封装base64加密函数
     * @Author Hadwin1991
     * @date 2018/10/16
     * @return
     */
    public static String encodeBASE64(String data)
    {
        Base64.Encoder encoder = Base64.getEncoder();
        String strEncodeBASE64Text = "";
        try
        {
            byte b[] = data.getBytes("UTF-8");
            strEncodeBASE64Text = encoder.encodeToString(b);

        }catch ( Exception e)
        {
            System.out.println("Error in data encryption\n");
        }

        return strEncodeBASE64Text;
    }

    /**
     * @desc 基于java8 封装base64解密函数
     * @Author Hadwin1991
     * @date 2018/10/16
     * @return
     */
    public static String decodeBASE64(String data)
    {
        Base64.Decoder decoder = Base64.getDecoder();
        String strDecodeBASE64Text = "";
        try
        {
            strDecodeBASE64Text = new String(decoder.decode(data), "UTF-8");

        }catch ( Exception e)
        {
            System.out.println("Error in data encryption\n");
        }

        return strDecodeBASE64Text;
    }

    /**
     * @desc 将字符串分割然后存到一个
     * 二维数组中[{A,80},{B,60},{C,70}]
     * @Author Hadwin1991
     * @date 2018/10/16
     * @return 返回值为一个二维数组
     */
    public static String[][] splitStringToArray(String data)
    {
        String[] temp = data.split("&"); // 通过逗号将字符串拆分成一维数组{"A:80","B:60","C:70"}
        String[][] arr = new String[temp.length][];// 初始化一个二维字符串数组，只指定了行数
        for (int i = 0; i < temp.length; i++) {
            String[] tempAgain = temp[i].split("="); //继续分割并存到另一个一临时的一维数组当中
            arr[i] = new String[tempAgain.length]; //根据tempAgain中的数组长度，为二维数组的列赋值
            for (int j = 0; j < tempAgain.length; j++) {
                arr[i][j] = tempAgain[j];  //为二维数组赋值
            }
        }
        return arr;

    }

    /**
     * @desc 将字符串分割然后以键值对存入HashMap
     * @Author Hadwin1991
     * @date 2018/10/16
     * @return 返回为HashMap
     */
    public static Map<String,String> splitStringToMap(String data)
    {
        HashMap<String, String> map = new LinkedHashMap<String, String>();
        String[] temp = data.split("&"); //通过逗号进行分割
        for (int i = 0; i < temp.length; i++) {
            String[] arr = temp[i].split("="); //通过冒号继续分割
            String[] tempAagin = new String[arr.length]; //再开辟一个数组用来接收分割后的数据
            for (int j = 0; j < arr.length; j++) {
                tempAagin[j] = arr[j];
            }
            map.put(arr[0], arr[1]);
        }
        return map;
    }

    /**
     * @desc base64解码，数据分割到hashmap中
     * @Author Hadwin1991
     * @date 2018/10/16
     * @return 返回为HashMap
     */
    public static Map<String,String> decodeBase64TextToMap(String data)
    {
        String strdecodeBase64Text = decodeBASE64(data);
        Map<String, String> map = splitStringToMap(strdecodeBase64Text);
        return map;
    }

    /**
     * @desc MD5签名验证
     * @Author Hadwin1991
     * @date 2018/10/16
     * @return 返回为HashMap
     */
    public static String sign(Map<String, String> paramValues,
                              String secret,
                              String timeStamp) {
        return sign(paramValues, null, secret, timeStamp);
    }

    /**
     * @desc MD5签名验证
     * @Author Hadwin1991
     * @date 2018/10/16
     * @return 返回为HashMap
     */
    public static String sign(Map<String, String> paramValues,
                                               List<String> ignoreParamNames,
                                               String secret,
                                               String timeStampnum) {
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
            sb.append(timeStampnum);
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

    public static String utf8Encoding(String value,
                                      String sourceCharsetName) {
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
