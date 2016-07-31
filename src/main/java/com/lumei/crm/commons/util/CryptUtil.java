package com.lumei.crm.commons.util;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author dave
 *
 */
public class CryptUtil {

  protected enum Algorithm {
    RSA, DES, AES
  }

  /**
   * RAS加密
   * 
   * @author dave
   *
   */
  public static class Rsa {
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * 用公钥加密
     * 
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] publicKey) throws Exception {
      X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);
      KeyFactory keyFactory = KeyFactory.getInstance(Algorithm.RSA.name());
      RSAPublicKey pubKey = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
      Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
      cipher.init(Cipher.ENCRYPT_MODE, pubKey);

      /*
       * 分片加密
       */
      int inputLen = data.length;
      int maxEncryptLen = pubKey.getModulus().bitLength() / 8 - 11;
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      int offSet = 0;
      byte[] cache;
      int i = 0;
      while (inputLen - offSet > 0) {
        if (inputLen - offSet > maxEncryptLen) {
          cache = cipher.doFinal(data, offSet, maxEncryptLen);
        } else {
          cache = cipher.doFinal(data, offSet, inputLen - offSet);
        }
        out.write(cache, 0, cache.length);
        i++;
        offSet = i * maxEncryptLen;
      }
      byte[] encryptedData = out.toByteArray();
      out.close();

      return encryptedData;
    }

    /**
     * 用私钥解密
     * 
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] privateKey) throws Exception {
      PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
      KeyFactory keyFactory = KeyFactory.getInstance(Algorithm.RSA.name());
      RSAPrivateKey priKey = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
      Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
      cipher.init(Cipher.DECRYPT_MODE, priKey);

      /*
       * 分片解密
       */
      int inputLen = data.length;
      int maxDecryptLen = priKey.getModulus().bitLength() / 8;
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      int offSet = 0;
      byte[] cache;
      int i = 0;
      while (inputLen - offSet > 0) {
        if (inputLen - offSet > maxDecryptLen) {
          cache = cipher.doFinal(data, offSet, maxDecryptLen);
        } else {
          cache = cipher.doFinal(data, offSet, inputLen - offSet);
        }
        out.write(cache, 0, cache.length);
        i++;
        offSet = i * maxDecryptLen;
      }
      byte[] decryptedData = out.toByteArray();
      out.close();

      return decryptedData;
    }

    /**
     * 用私钥加密
     * 
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] privateKey) throws Exception {
      PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
      KeyFactory keyFactory = KeyFactory.getInstance(Algorithm.RSA.name());
      RSAPrivateKey priKey = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
      Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
      cipher.init(Cipher.ENCRYPT_MODE, priKey);
      /*
       * 分片加密
       */
      int inputLen = data.length;
      int maxEncryptLen = priKey.getModulus().bitLength() / 8 - 11;
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      int offSet = 0;
      byte[] cache;
      int i = 0;
      while (inputLen - offSet > 0) {
        if (inputLen - offSet > maxEncryptLen) {
          cache = cipher.doFinal(data, offSet, maxEncryptLen);
        } else {
          cache = cipher.doFinal(data, offSet, inputLen - offSet);
        }
        out.write(cache, 0, cache.length);
        i++;
        offSet = i * maxEncryptLen;
      }
      byte[] encryptedData = out.toByteArray();
      out.close();

      return encryptedData;
    }

    /**
     * 用公钥解密
     * 
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] publicKey) throws Exception {
      X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);
      KeyFactory keyFactory = KeyFactory.getInstance(Algorithm.RSA.name());
      RSAPublicKey pubKey = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
      Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
      cipher.init(Cipher.DECRYPT_MODE, pubKey);

      /*
       * 分片解密
       */
      int inputLen = data.length;
      int maxDecryptLen = pubKey.getModulus().bitLength() / 8;
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      int offSet = 0;
      byte[] cache;
      int i = 0;
      while (inputLen - offSet > 0) {
        if (inputLen - offSet > maxDecryptLen) {
          cache = cipher.doFinal(data, offSet, maxDecryptLen);
        } else {
          cache = cipher.doFinal(data, offSet, inputLen - offSet);
        }
        out.write(cache, 0, cache.length);
        i++;
        offSet = i * maxDecryptLen;
      }
      byte[] decryptedData = out.toByteArray();
      out.close();

      return decryptedData;
    }

    /**
     * 用私钥加签
     * 
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] sign(byte[] data, byte[] privateKey) throws Exception {
      PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
      KeyFactory keyFactory = KeyFactory.getInstance(Algorithm.RSA.name());
      PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
      Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
      signature.initSign(priKey);
      signature.update(data);
      return signature.sign();
    }

    /**
     * 用公钥验签
     * 
     * @param data
     * @param publicKey
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] data, byte[] publicKey, byte[] sign) throws Exception {
      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
      KeyFactory keyFactory = KeyFactory.getInstance(Algorithm.RSA.name());
      PublicKey pubKey = keyFactory.generatePublic(keySpec);
      Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
      signature.initVerify(pubKey);
      signature.update(data);
      return signature.verify(sign);
    }

    /**
     * 生成公钥私钥对 默认1024bit
     * 
     * @return
     * @throws Exception
     */
    public static RsaKeyPair genKey() throws Exception {
      return genKey(1024);
    }

    /**
     * 生成公钥私钥对
     * 
     * @param bit
     * @return
     * @throws Exception
     */
    public static RsaKeyPair genKey(int bit) throws Exception {
      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(Algorithm.RSA.name());
      keyPairGen.initialize(bit, new SecureRandom());
      KeyPair keyPair = keyPairGen.generateKeyPair();
      RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
      RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
      return new RsaKeyPair(publicKey.getEncoded(), privateKey.getEncoded());
    }

    public static class RsaKeyPair {
      private byte[] publicKey;
      private byte[] privateKey;

      RsaKeyPair(byte[] publicKey, byte[] privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
      }

      public byte[] getPublicKey() {
        return publicKey;
      }

      public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
      }

      public byte[] getPrivateKey() {
        return privateKey;
      }

      public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
      }

    }
  }

  /**
   * 对称加密算法
   * 
   * @author dave
   *
   */
  protected static abstract class SymmetricEncryptionAlgorithm {
    protected Algorithm algorithm;

    protected SymmetricEncryptionAlgorithm(Algorithm algorithm) {
      this.algorithm = algorithm;
    }

    /**
     * 生成密钥
     * 
     * @return
     * @throws Exception
     */
    public byte[] genKey() throws Exception {
      return genKey(null);
    }

    /**
     * 生成密钥
     * 
     * @param seed
     * @return
     * @throws Exception
     */
    public byte[] genKey(byte[] seed) throws Exception {
      SecureRandom secureRandom = null;
      if (seed != null) {
        secureRandom = new SecureRandom(seed);
      } else {
        secureRandom = new SecureRandom();
      }
      javax.crypto.KeyGenerator kg = javax.crypto.KeyGenerator.getInstance(algorithm.name());
      kg.init(secureRandom);
      SecretKey secretKey = kg.generateKey();
      return secretKey.getEncoded();
    }

    protected Key toKey(byte[] key) throws Exception {
      SecretKey secretKey = null;
      if (Algorithm.DES.equals(algorithm)) {
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm.name());
        secretKey = keyFactory.generateSecret(dks);
      } else {
        secretKey = new SecretKeySpec(key, algorithm.name());
      }

      return secretKey;
    }

    /**
     * 解密
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public byte[] decrypt(byte[] data, byte[] key) throws Exception {
      Cipher cipher = Cipher.getInstance(algorithm.name());
      cipher.init(Cipher.DECRYPT_MODE, toKey(key));
      return cipher.doFinal(data);
    }

    /**
     * 加密
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public byte[] encrypt(byte[] data, byte[] key) throws Exception {
      Cipher cipher = Cipher.getInstance(algorithm.name());
      cipher.init(Cipher.ENCRYPT_MODE, toKey(key));
      return cipher.doFinal(data);
    }

  }

  /**
   * DES加密
   * 
   * @author dave
   *
   */
  public static class Des extends SymmetricEncryptionAlgorithm {

    protected Des(Algorithm algorithm) {
      super(algorithm);
    }

    public static Des newInstance() {
      return new Des(Algorithm.DES);
    }
  }

  /**
   * AES加密
   * 
   * @author dave
   *
   */
  public static class Aes extends SymmetricEncryptionAlgorithm {

    protected Aes(Algorithm algorithm) {
      super(algorithm);
    }

    public static Aes newInstance() {
      return new Aes(Algorithm.AES);
    }
  }


}
