package com.moontech.healthyfood.configs;

import com.moontech.healthyfood.constants.ApiConstant;
import com.moontech.healthyfood.models.responses.SecurityResponse;
import java.util.Objects;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Configuración de la clase auditora.
 *
 * @author Felipe Monzón
 * @since 21/12/21
 */
@Component
public class Auditor implements AuditorAware<String> {
  /**
   * Consulta el usuario auditor.
   *
   * @return usuario encontrado
   */
  @Override
  public Optional<String> getCurrentAuditor() {
    String user = ApiConstant.USER_SYSTEM;
    if (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
      SecurityResponse login =
          new ModelMapper()
              .map(
                  SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                  SecurityResponse.class);
      user = login.getUsername();
    }
    return Optional.of(user);
  }
}
