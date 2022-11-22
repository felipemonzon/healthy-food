package com.moontech.healthyfood.business;

import com.moontech.healthyfood.constants.ErrorConstant;
import com.moontech.healthyfood.entities.ParameterEntity;
import com.moontech.healthyfood.exceptions.custom.BusinessException;
import com.moontech.healthyfood.exceptions.custom.NotDataFoundException;
import com.moontech.healthyfood.models.requests.ParameterRequest;
import com.moontech.healthyfood.models.responses.ParameterResponse;
import com.moontech.healthyfood.repositories.ParameterRepository;
import com.moontech.healthyfood.services.ParameterService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Implementación de las reglas de negocio del módulo de parámetros.
 *
 * @author Felipe Monzón
 * @enterprise Sano Pak
 * @since 23 jul., 2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ParameterBusiness implements ParameterService {
  /** Repositorio de parámetros. */
  private final ParameterRepository parameterRepository;

  /** {@inheritDoc}. */
  @Override
  public List<ParameterResponse> retrieve() {
    log.info("Consulta todos los parámetros");
    return this.parameterRepository.findAll().stream()
        .map(this::mapping)
        .collect(Collectors.toList());
  }

  /** {@inheritDoc}. */
  @Override
  public List<ParameterResponse> findBy(String search) {
    log.info("Consulta parámetros por {}", search);
    return this.parameterRepository.findBy(search).stream()
        .map(this::mapping)
        .collect(Collectors.toList());
  }

  /** {@inheritDoc}. */
  @Override
  public void save(ParameterRequest request) {
    log.debug("Guarda parámetro {}", request);
    Optional<ParameterEntity> entity = this.parameterRepository.findById(request.getId());
    if (!entity.isPresent()) {
      this.parameterRepository.save(this.mapping(request));
    } else {
      throw new BusinessException(
          ErrorConstant.REGISTER_DATA_EXIST_CODE, ErrorConstant.PARAMETER_REGISTER_MESSAGE);
    }
  }

  /** {@inheritDoc}. */
  @Override
  public void update(String id, ParameterRequest request) {
    this.validateParameter(id);
    request.setId(id);
    log.debug("Actualiza los datos de un parámetro {}", request);
    this.parameterRepository.save(this.mapping(request));
  }

  /** {@inheritDoc}. */
  @Override
  public void delete(String id) {
    log.debug("Elimina un parámetro por su identificador {}", id);
    ParameterEntity entity = this.validateParameter(id);
    entity.setStatus(false);
    this.parameterRepository.save(entity);
  }

  /**
   * Valida si el parámetro existe.
   *
   * @param id identificador del parámetro
   * @return entidad de parámetro {@link ParameterEntity}
   */
  private ParameterEntity validateParameter(String id) {
    return this.parameterRepository
        .findById(id)
        .<NotDataFoundException>orElseThrow(
            () -> {
              throw new NotDataFoundException(ErrorConstant.PARAMETER_NOT_FOUND_MESSAGE);
            });
  }

  /**
   * Convierte un entity de tipo {@link ParameterEntity} a un objeto {@link ParameterResponse}
   *
   * @param entity entidad de parámetros
   * @return {@link ParameterResponse} respuesta de la API de parámetros
   */
  private ParameterResponse mapping(ParameterEntity entity) {
    return new ModelMapper().map(entity, ParameterResponse.class);
  }

  /**
   * Convierte un objeto {@link ParameterRequest} a un entity de tipo {@link ParameterEntity}
   *
   * @param request petición con los datos del parámetro
   * @return {@link ParameterEntity} entidad de parameters
   */
  private ParameterEntity mapping(ParameterRequest request) {
    return new ModelMapper().map(request, ParameterEntity.class);
  }
}
