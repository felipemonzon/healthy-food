package com.moontech.healthyfood.business;

import com.moontech.healthyfood.constants.ErrorConstant;
import com.moontech.healthyfood.entities.RoleEntity;
import com.moontech.healthyfood.exceptions.custom.BusinessException;
import com.moontech.healthyfood.models.requests.ProfileRequest;
import com.moontech.healthyfood.models.responses.AuthorityResponse;
import com.moontech.healthyfood.repositories.RoleRepository;
import com.moontech.healthyfood.security.constants.SecurityConstants;
import com.moontech.healthyfood.services.RoleService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * Implementación de las reglas de negocio para perfiles.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 17 nov., 2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleBusiness implements RoleService {
  /** Repositorio de perfiles. */
  private final RoleRepository roleRepository;

  /** {@inheritDoc}. */
  @Override
  @Transactional(readOnly = true)
  public List<AuthorityResponse> retrieve() {
    log.info("consulta todos los perfiles");
    return this.roleRepository.findAll().stream().map(this::mapping).collect(Collectors.toList());
  }

  /** {@inheritDoc}. */
  @Override
  @Transactional
  public void save(ProfileRequest request) {
    log.info("Guarda los datos de perfil {}", request);
    List<RoleEntity> roles = this.roleRepository.findBy(request.getName());
    if (ObjectUtils.isEmpty(roles)) {
      request.setName(SecurityConstants.ROLE_PREFIX + request.getName().toUpperCase());
      request.setValue(request.getValue().toUpperCase());
      this.roleRepository.save(this.mapping((request)));
    } else {
      throw new BusinessException(
          ErrorConstant.DATA_EXIST_CODE, ErrorConstant.PROFILE_REGISTER_MESSAGE);
    }
  }

  /** {@inheritDoc}. */
  @Override
  @Transactional
  public void update(Long id, ProfileRequest request) {
    log.info("Actualiza los datos de un perfil {}", request);
    Optional<RoleEntity> entity = this.validateProfile(id);
    if (entity.isPresent()) {
      request.setId(id);
      this.roleRepository.save(this.mapping(request));
    } else {
      throw new BusinessException(
          ErrorConstant.RECORD_NOT_FOUND_CODE, ErrorConstant.PROFILE_NOT_FOUND_MESSAGE);
    }
  }

  /** {@inheritDoc}. */
  @Override
  public List<AuthorityResponse> findBy(final String search) {
    log.debug("Consulta el perfile por {}", search);
    return this.roleRepository.findBy(search).stream()
        .map(this::mapping)
        .collect(Collectors.toList());
  }

  /**
   * Valida si la sucursal existe.
   *
   * @param id identificador del perfil
   * @return entidad de perfil {@link RoleEntity}
   */
  private Optional<RoleEntity> validateProfile(Long id) {
    return this.roleRepository.findById(id);
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

  /**
   * Convierte una entidad {@code ProfileRequest} a uno de tipo {@code RoleEntity}
   *
   * @param request objeto de tipo {@link ProfileRequest}
   * @return entidad para perfiles
   */
  private RoleEntity mapping(ProfileRequest request) {
    return new ModelMapper().map(request, RoleEntity.class);
  }
}
