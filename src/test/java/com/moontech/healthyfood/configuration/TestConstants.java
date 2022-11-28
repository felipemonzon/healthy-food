package com.moontech.healthyfood.configuration;

import com.moontech.healthyfood.enums.Genre;
import com.moontech.healthyfood.models.responses.AuthorityResponse;
import com.moontech.healthyfood.models.responses.SecurityResponse;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Constantes para pruebas.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 21 nov., 2022
 */
public abstract class TestConstants {
  /** UUID header. */
  public static final String UUID_HEADER = "uuid";
  /** Perfil de administrador. */
  public static final String ROLE_ADMIN = "ADMIN";
  /** Perfil de otros. */
  public static final String ROLE_OTHER = "OTHER";
  /** Log running. */
  public static final String TEST_RUNNING = "Running {}";

  /**
   * Crea un usuario.
   *
   * @return datos del usuario generado
   */
  public static SecurityResponse TEST_USER(String username) {
    SecurityResponse response = new SecurityResponse();
    response.setId(1L);
    response.setCel("6671568899");
    response.setEmail("felipe.moznon@gmail.com");
    response.setGenre(Genre.MALE);
    response.setFirstName("felipe");
    response.setLastName("monzón");
    response.setBranchOfficeId(1L);
    response.setBranchOfficeName("barrancos");
    response.setDisplayName("Felipe monzón");
    response.setUsername(username);
    response.setPassword("password");
    AuthorityResponse role = new AuthorityResponse();
    role.setId(1L);
    role.setName(ROLE_ADMIN);
    role.setValue(ROLE_ADMIN);
    response.setProfiles(Collections.singleton(role));
    response.setAuthorities(
        Arrays.asList(
            new SimpleGrantedAuthority(role.getName()), new SimpleGrantedAuthority("ROLE_ADMIN")));
    return response;
  }

  private TestConstants() {}
}
