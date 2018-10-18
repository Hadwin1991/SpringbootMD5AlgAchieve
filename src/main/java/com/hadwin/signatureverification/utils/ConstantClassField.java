package com.hadwin.signatureverification.utils;

public class ConstantClassField {
    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * 获取公钥的key
     */
    public static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    public static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * RSA最大加密明文大小117
     */
    public static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小128
     */
    public static final int MAX_DECRYPT_BLOCK = 128;

    public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCw1o4Nsumc0zFqZ3AuovTeiHMC" +
            "VplgTkl1g7xWWsvvOr5WW/xYQYs6eUkwq8FNRNOC3daIwU1HW+Euvnw5AcxFiiwV" +
            "8CMevvophdy6AYVC6YPjxPAhQa0OOureFfGRfcXX9ORVsIiVgzUn2bKoT0ZIAJXZ" +
            "7e8a9qBW3eQuexg9swIDAQAB";
    public static final String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALDWjg2y6ZzTMWpn" +
            "cC6i9N6IcwJWmWBOSXWDvFZay+86vlZb/FhBizp5STCrwU1E04Ld1ojBTUdb4S6+" +
            "fDkBzEWKLBXwIx6++imF3LoBhULpg+PE8CFBrQ466t4V8ZF9xdf05FWwiJWDNSfZ" +
            "sqhPRkgAldnt7xr2oFbd5C57GD2zAgMBAAECgYA8IlngEq0aZT7yngYp9cQrQF9L" +
            "tkMl2rlkOs3615lt2p4LI/Cl2nQMCF55LI7pRbRfViy0CHfNAlOK37/CavgZREEY" +
            "d6gv6q5Q9RFrTapes6TDn397VQp2ocIC9bBYGM3rdkGPd8aMQC8Wb+2mCE8R6a11" +
            "p3ie+Zo2PptY/XwnoQJBAOq/R/KeU+uyeZD+XpOJjfmurpbG7Fq6s8s3o/dDq/h5" +
            "05nUAPowfpVi04QSe4KvQJuzyzSLdmzXJOs0G73AGPkCQQDA2R57l8YVKr6BlJVJ" +
            "8NAXj6aTyRi63J+htQ6EvPJxICIsjXNpy1mjI/jc8SLUNGYPxXZlO2j2wuEGIOdJ" +
            "PkMLAkEA3FvS3h1iCc7v7vvZLAXkEI25ti/ljwXT4N/KO0J7HDTAPZaVlEYRjYnH" +
            "pCL1bie1LnQIPLrt4B3VujSgFe9NgQJAaNFaHSkoU/bwpvPAlPjb0gKVMrxZwa8K" +
            "4NTNOF6Ok+g4SujzMyyA4wg5SrWSDa8RTj8wk+NPsNfJgRA/RfMecwJBAOMgkjfr" +
            "EYEmqsqscioo/Awd3F5z0fYFqxm98Z8wdKnir3s4tIR+TBdazcv7nndrU/S8KhMg" +
            "b1SVEv2Q4R46WGg=";

    public static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
}
