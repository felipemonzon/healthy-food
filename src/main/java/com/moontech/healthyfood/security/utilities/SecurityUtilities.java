package com.moontech.healthyfood.security.utilities;

import com.moontech.healthyfood.models.responses.SecurityResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utilería para seguridad.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 25 nov., 2022
 */
@Slf4j
public abstract class SecurityUtilities {
  /** Caracteres permitidos en la generación de la contraseña. */
  private static final String ALLOWED_SPL_CHARACTERS = "!@#$%^&*()_+";
  /** Error al generar la contraseña. */
  private static final String ERROR_CODE = "Error en caracteres especiales";
  /** Mascará para la tarjeta. */
  private static final String MASK_CARD = "XXXX XXXX XXXX ";
  /** Perfil de cliente. */
  public static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";

  /**
   * Encripta la contraseña.
   *
   * @param password contraseña a encriptar
   * @return contraseña encriptada
   */
  public static String passwordEncoder(final String password) {
    return new BCryptPasswordEncoder().encode(password);
  }

  /**
   * Genera una contraseña segura.
   *
   * @return contraseña generada
   */
  public static String generateSecurePassword() {
    PasswordGenerator gen = new PasswordGenerator();
    CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
    CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
    lowerCaseRule.setNumberOfCharacters(3);

    CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
    CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
    upperCaseRule.setNumberOfCharacters(3);

    CharacterData digitChars = EnglishCharacterData.Digit;
    CharacterRule digitRule = new CharacterRule(digitChars);
    digitRule.setNumberOfCharacters(3);
    CharacterData specialChars =
        new CharacterData() {
          public String getErrorCode() {
            return ERROR_CODE;
          }

          public String getCharacters() {
            return ALLOWED_SPL_CHARACTERS;
          }
        };
    CharacterRule splCharRule = new CharacterRule(specialChars);
    splCharRule.setNumberOfCharacters(3);

    return gen.generatePassword(12, splCharRule, lowerCaseRule, upperCaseRule, digitRule);
  }

  /**
   * Obtiene el genero del usuario.
   *
   * @param userCredentials credenciales de usuario
   * @return datos del usuario
   */
  public static SecurityResponse getUserData(final Authentication userCredentials) {
    return new ModelMapper().map(userCredentials.getPrincipal(), SecurityResponse.class);
  }

  /**
   * Obtiene le número de cliente.
   *
   * @param userCredentials credenciales de usuario
   * @return identificador de usuario
   */
  public static Long getUserId(final Authentication userCredentials) {
    SecurityResponse userCredential = (SecurityResponse) userCredentials.getPrincipal();
    return userCredential.getId();
  }

  /**
   * Agrega la mascara al número de tarjeta.
   *
   * @param cardNumber número de tarjeta
   * @return tarjeta enmascarada
   */
  public static String maskCard(final String cardNumber) {
    return MASK_CARD + cardNumber;
  }

  /** Constructor */
  private SecurityUtilities() {}
}
