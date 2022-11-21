package com.moontech.library.business;

import com.moontech.library.constants.LogConstant;
import com.moontech.library.entities.RoleEntity;
import com.moontech.library.entities.UserEntity;
import com.moontech.library.models.responses.AuthorityResponse;
import com.moontech.library.models.responses.InitialUserResponse;
import com.moontech.library.models.responses.UserResponse;
import com.moontech.library.repositories.UserRepository;
import com.moontech.library.services.OfficeService;
import com.moontech.library.services.RoleService;
import com.moontech.library.services.UserService;
import com.moontech.library.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de las reglas de negocio.
 *
 * @author Felipe Monzón
 * @since 31/12/21
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserBusiness implements UserService {
  /** Repositorio de usuarios. */
  private final UserRepository userRepository;
  /** Servicio para sucursales. */
  private final OfficeService officeService;
  /** Servicio para perfiles. */
  private final RoleService roleService;

  /** {@inheritDoc}. */
  @Override
  @Transactional(readOnly = true)
  public List<UserResponse> retrieve(Pageable pageable) {
    int currentPage = Utilities.getCurrentPage(pageable);
    log.info(LogConstant.USERS_RETRIEVE, currentPage);
    return this.userRepository.findAll().stream().map(this::mapping).collect(Collectors.toList());
  }

  /** {@inheritDoc}. */
  @Override
  @Transactional(readOnly = true)
  public InitialUserResponse initialUser() {
    log.info("Consulta los datos para iniciar la página de usuarios");
    InitialUserResponse response = new InitialUserResponse();
    response.setOffices(this.officeService.retrieve());
    response.setAuthorities(this.roleService.retrieve());
    return response;
  }

  /**
   * Convierte una entidad {@code UserEntity} a uno de tipo {@code User}
   *
   * @param entity objeto de tipo {@link UserEntity}
   * @return objeto de salida de la api de usuarios
   */
  private UserResponse mapping(UserEntity entity) {
    UserResponse response = new ModelMapper().map(entity, UserResponse.class);
    response.setAuthorities(
        entity.getRoles().stream().map(this::mapping).collect(Collectors.toSet()));
    return response;
  }

  /**
   * Convierte una entidad {@code RoleEntity} a uno de tipo {@code AuthorityResponse}
   *
   * @param entity objeto de tipo {@link RoleEntity}
   * @return objeto de salida de la api de perfiles
   */
  private AuthorityResponse mapping(RoleEntity entity) {
    return new ModelMapper().map(entity, AuthorityResponse.class);
  }
}
