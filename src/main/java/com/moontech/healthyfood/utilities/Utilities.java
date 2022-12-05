package com.moontech.healthyfood.utilities;

import com.moontech.healthyfood.constants.ApiConstant;
import com.moontech.healthyfood.models.requests.UserRequest;
import com.moontech.healthyfood.notifications.Email;
import java.util.HashMap;
import java.util.Map;
import org.springframework.data.domain.Pageable;

/**
 * Clase de utilerías.
 *
 * @author Felipe Monzón
 * @since Dec 17, 2021
 */
public abstract class Utilities {
  /** Formato de salida de la respuesta de error. */
  public static final String ERROR_DATE_PATTER = "yyyy-MM-dd HH:mm:ss";
  /** Espacio en blanco. */
  public static final String WHITE_SPACE = " ";

  /**
   * Obtiene la pagina actual.
   *
   * @param pageable datos de paginación
   * @return pagina actual
   */
  public static int getCurrentPage(Pageable pageable) {
    int page = pageable.getPageNumber();
    if (pageable.getPageNumber() != ApiConstant.INT_ZERO_VALUE) {
      page -= ApiConstant.INT_ONE_VALUE;
    }
    return page;
  }

  /**
   * Obtiene los datos para enviar por correo.
   *
   * @param userData {@link UserRequest}
   * @param password contraseña a enviar
   * @return {@link Email}
   */
  public static Email getEmailData(UserRequest userData, final String password, String office) {
    Email email = new Email();
    final String displayName =
        userData.getFirstName() + ApiConstant.WHITE_SPACE + userData.getLastName();
    Map<String, String> model = new HashMap<>();
    model.put(ApiConstant.MAIL_NAME_PROPERTY, displayName);
    model.put(ApiConstant.MAIL_PHONE_PROPERTY, userData.getCel());
    model.put(ApiConstant.MAIL_USER_PROPERTY, userData.getEmail());
    model.put(ApiConstant.MAIL_PASSWORD_PROPERTY, password);
    model.put(ApiConstant.MAIL_OFFICE_PROPERTY, office);

    email.setTo(userData.getEmail());
    email.setModel(model);

    return email;
  }

  /** Constructor privado. */
  private Utilities() {}
}
