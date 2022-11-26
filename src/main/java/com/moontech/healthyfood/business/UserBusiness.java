package com.moontech.healthyfood.business;

import com.moontech.healthyfood.constants.ApiConstant;
import com.moontech.healthyfood.constants.ErrorConstant;
import com.moontech.healthyfood.constants.LogConstant;
import com.moontech.healthyfood.entities.RoleEntity;
import com.moontech.healthyfood.entities.UserEntity;
import com.moontech.healthyfood.exceptions.custom.BusinessException;
import com.moontech.healthyfood.exceptions.custom.NotDataFoundException;
import com.moontech.healthyfood.models.requests.UserRequest;
import com.moontech.healthyfood.models.responses.AuthorityResponse;
import com.moontech.healthyfood.models.responses.InitialUserResponse;
import com.moontech.healthyfood.models.responses.SecurityResponse;
import com.moontech.healthyfood.models.responses.UserResponse;
import com.moontech.healthyfood.repositories.UserRepository;
import com.moontech.healthyfood.security.SecurityUtilities;
import com.moontech.healthyfood.services.OfficeService;
import com.moontech.healthyfood.services.RoleService;
import com.moontech.healthyfood.services.UserService;
import com.moontech.healthyfood.utilities.Utilities;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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
   * {@inheritDoc}.
   *
   * @return
   */
  @Override
  @Transactional
  public UserResponse updateUserProfile(Authentication auth, UserRequest request) {
    log.info("Actualiza los datos del perfil del usuario {}", request.getUsername());
    SecurityResponse userData = SecurityUtilities.getUserData(auth);
    UserEntity entity = this.validateUserById(userData.getId());
    if (entity.getUsername().equals(request.getUsername())) {
      entity.setCel(request.getCel());
      entity.setEmail(request.getEmail());
      entity.setGenre(request.getGenre());
      entity.setFirstName(request.getFirstName());
      entity.setLastName(request.getLastName());
      final String password =
          ObjectUtils.isEmpty(request.getPassword())
              ? entity.getPassword()
              : SecurityUtilities.passwordEncoder(request.getPassword());
      entity.setPassword(password);
      return this.mapping(this.userRepository.save(entity));
    } else {
      throw new BusinessException(
          ErrorConstant.RECORD_NOT_FOUND_CODE,
          String.format(ErrorConstant.CANNOT_USER_PROFILE_UPDATE, request.getUsername()));
    }
  }

  /**
   * Valida si un usuario existe.
   *
   * @param id identificador del usuario
   * @return {@link UserEntity} entidad de usuario
   */
  private UserEntity validateUserById(Long id) {
    return this.userRepository
        .findById(id)
        .<NotDataFoundException>orElseThrow(
            () -> {
              throw new NotDataFoundException(ErrorConstant.USER_NOT_FOUND_MESSAGE);
            });
  }

  /**
   * Convierte una entidad {@code UserEntity} a uno de tipo {@code User}
   *
   * @param entity objeto de tipo {@link UserEntity}
   * @return objeto de salida de la api de usuarios
   */
  private UserResponse mapping(UserEntity entity) {
    UserResponse response = new ModelMapper().map(entity, UserResponse.class);
    response.setProfiles(entity.getRoles().stream().map(this::mapping).collect(Collectors.toSet()));
    response.setDisplayName(entity.getFirstName() + ApiConstant.WHITE_SPACE + entity.getLastName());
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
