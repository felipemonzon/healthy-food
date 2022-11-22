package com.moontech.healthyfood.security.cipher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Configuración para cifrado.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 16 nov., 2022
 */
@Slf4j
@Component
public class CipherUtility {
  /** Propiedad para el cifrado RSA. */
  private static final String RSA_PROPERTY = "RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING";

  private KeyPairGenerator keyPairGenerator;

  private final SecureRandom random = new SecureRandom();

  /**
   * Genera para de llaves para RSA.
   *
   * @return {@code KeyPair}
   */
  public KeyPair getKeyPair() {
    return keyPairGenerator.genKeyPair();
  }

  public String encrypt(String content, Key pubKey)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
          IllegalBlockSizeException, BadPaddingException {
    byte[] contentBytes = content.getBytes();
    Cipher cipher = Cipher.getInstance(RSA_PROPERTY);
    cipher.init(Cipher.ENCRYPT_MODE, pubKey);
    byte[] cipherContent = cipher.doFinal(contentBytes);
    return Base64.getEncoder().encodeToString(cipherContent);
  }

  public String decrypt(String cipherContent, Key privateKey)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
          IllegalBlockSizeException, BadPaddingException {
    Cipher cipher = Cipher.getInstance(RSA_PROPERTY);
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] cipherContentBytes = Base64.getDecoder().decode(cipherContent.getBytes());
    byte[] decryptedContent = cipher.doFinal(cipherContentBytes);
    return new String(decryptedContent);
  }

  public String encodeKey(Key key) {
    return Base64.getEncoder().encodeToString(key.getEncoded());
  }

  public PublicKey decodePublicKey(String keyStr)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    byte[] keyBytes = Base64.getDecoder().decode(keyStr);
    X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(RSA_PROPERTY);
    return keyFactory.generatePublic(spec);
  }

  public PrivateKey decodePrivateKey(String keyStr)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    byte[] keyBytes = Base64.getDecoder().decode(keyStr);
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(RSA_PROPERTY);
    return keyFactory.generatePrivate(keySpec);
  }

  @PostConstruct
  private void init() {
    try {
      if (this.keyPairGenerator == null) {
        this.keyPairGenerator = KeyPairGenerator.getInstance(RSA_PROPERTY);
      }
      this.keyPairGenerator.initialize(2048, random);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }
}
