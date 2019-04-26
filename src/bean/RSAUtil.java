package bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class RSAUtil {

    public static final String DEFAULT_PUBLIC_KEY =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLD2WxWXQyDgarrUdRY8FN9vbb\n" +
                    "dfcv4KFdENwMC/lpdFkhVEvm8coosXC3aqIUfpOuDcm5GUUdEzvKTuCEakqX6OtO\n" +
                    "cGhgLEpAGmQBysMJvTe1gqWPNSrhAxgkIZjY9fCaNKxOFGEzthBexUhaZuULfoi6\n" +
                    "xA6skuZC/KtMNFchBwIDAQAB";

    public static final String DEFAULT_PRIVATE_KEY =
            "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMsPZbFZdDIOBqut\n" +
                    "R1FjwU329tt19y/goV0Q3AwL+Wl0WSFUS+bxyiixcLdqohR+k64NybkZRR0TO8pO\n" +
                    "4IRqSpfo605waGAsSkAaZAHKwwm9N7WCpY81KuEDGCQhmNj18Jo0rE4UYTO2EF7F\n" +
                    "SFpm5Qt+iLrEDqyS5kL8q0w0VyEHAgMBAAECgYAELgP025eFtD406HMwXLJM7Yl1\n" +
                    "SP9Wj/QiW0d8Q/7VSdzPz9FkJpP3tHdSfzJo45YcnhIJ6lg+2feAGZGLiDiS0H5R\n" +
                    "p65Y16waErQUJ2l1H1M6VIL/LaYnW0KNSnyYZLMC1ELlyQMCuyvELmrdcllrkgGS\n" +
                    "/scwSxPcMdrmyo8xAQJBAPoqCYTe+/ZIiz/l9jGV6E4r+5eoS3iUo5pwU+uRoZfE\n" +
                    "IHi8u3l8hS33K8ssaBSIvysLTbL03o5Ugz4X6T14X4ECQQDPzA7V5wngbk2ny3+l\n" +
                    "BAnxio7jt0xX8hZtasrj4RhhIiCuImwZUaidQh9lUg6Oiv8y0nFdWoC7GWINT+U/\n" +
                    "vsSHAkEA5Tsh79w4O4xr9Qhu2S8JaKBK8cQoK/zgEYN+kqymyx/PtXMTwxXEH8OP\n" +
                    "IKS3VqGbswCvmR1i1j9A5+QXONmVgQJAe6QMU8Q0AUFAnY2dNr+vdvQdz2raVx3p\n" +
                    "sFMxw+PEkM8wqMw8i9glvuSAL9ZiaPhVnpTO9WALZshUql0WqBZHcwJBALd/8lU7\n" +
                    "7fiPqIxhRfchFtpFLNPxi5ecxk1r3+vlGI4KamHHQXhieOBHPWG4umo2uObD0Dk0\n" +
                    "L/iVM1cFYsYaIjM=";

    public static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 加载公钥
     * @param in
     * @throws Exception
     */
    public void loadPublicKey(InputStream in) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            loadPublicKey(sb.toString());
        } catch (IOException e) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥输入流为空");
        }
    }


    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr 公钥数据字符串
     * @throws Exception 加载公钥时产生的异常
     */
    public static RSAPublicKey loadPublicKey(String publicKeyStr) throws Exception {
        try {
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] buffer = base64Decoder.decodeBuffer(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (IOException e) {
            throw new Exception("公钥数据内容读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 从文件中加载私钥
     * @return 是否成功
     * @throws Exception
     */
    public void loadPrivateKey(InputStream in) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            loadPrivateKey(sb.toString());
        } catch (IOException e) {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥输入流为空");
        }
    }

    public static RSAPrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
        try {
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] buffer = base64Decoder.decodeBuffer(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (IOException e) {
            throw new Exception("私钥数据内容读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 签名
     * @param privateKey 私钥
     * @return
     * @throws SignatureException
     */
    public static String rsaSign(String privateKey, String signStr) throws SignatureException {
        try {
            RSAPrivateKey rsaPrivateKey=loadPrivateKey(privateKey);
            if (privateKey == null) {
                throw new Exception("加密公钥为空, 请设置");
            }
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(rsaPrivateKey);
            signature.update(signStr.getBytes());

            byte[] signed = signature.sign();
            String output = new BASE64Encoder().encode(signed);
            return output;
        } catch (Exception e) {
            throw new SignatureException("RSAcontent = " + signStr);
        }
    }

    /**
     * 验签
     */
    public static boolean rsaVerify(String publicKey, String sign,String signStr)
    {
        try
        {
            RSAPublicKey rsaPublicKey=loadPublicKey(publicKey);
            if (publicKey == null) {
                throw new Exception("解密私钥为空, 请设置");
            }
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(rsaPublicKey);
            signature.update(signStr.getBytes());

            //把签名反解析，并验证
            byte[] decodeSign=new BASE64Decoder().decodeBuffer(sign);
            return signature.verify(decodeSign);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //LogMan.log("[NeteaseSignUtil][verifySHA1withRSASigature]"+e);
            return false;
        }
    }


    public static final byte[] hexStrToBytes(String s)  {
        byte[] bytes;
        bytes = new byte[s.length() / 2];
        for (int i = 0; i < bytes.length; i++){
            bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    /**
     * 加密过程
     *
     * @param privateKey     公钥
     * @param encryptStr 明文数据
     * @return
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String privateKey, String encryptStr) throws Exception {
        byte[] plainTextData=encryptStr.getBytes();
        RSAPrivateKey rsaPrivateKey=loadPrivateKey(privateKey);

        if (privateKey == null) {
            throw new Exception("加密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey);
            byte[] crypted = cipher.doFinal(plainTextData);

            String output = new BASE64Encoder().encode(crypted);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }

    /**
     * 解密过程
     *
     * @param publicKey 私钥
     * @param data 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String publicKey, String data) throws Exception {
        RSAPublicKey rsaPublicKey=loadPublicKey(publicKey);
        if (publicKey == null) {
            throw new Exception("解密私钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);
            byte[] cipherData=new BASE64Decoder().decodeBuffer(data);
            byte[] output = cipher.doFinal(cipherData);
            return new String(output);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }

}
