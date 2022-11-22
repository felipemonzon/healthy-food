package com.moontech.healthyfood.business;

import com.moontech.healthyfood.constants.ErrorConstant;
import com.moontech.healthyfood.entities.OfficeEntity;
import com.moontech.healthyfood.exceptions.custom.BusinessException;
import com.moontech.healthyfood.exceptions.custom.NotDataFoundException;
import com.moontech.healthyfood.models.requests.OfficeRequest;
import com.moontech.healthyfood.models.responses.OfficeResponse;
import com.moontech.healthyfood.repositories.OfficeRepository;
import com.moontech.healthyfood.services.OfficeService;
import com.moontech.healthyfood.utilities.Utilities;
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
 * Implementación de las reglas de negocio de las apis de sucursales.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 17 jul., 2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OfficeBusiness implements OfficeService {
  /** Repositorio de sucursales. */
  private final OfficeRepository officeRepository;

  /** {@inheritDoc}. */
  @Override
  @Transactional(readOnly = true)
  public List<OfficeResponse> retrieve() {
    log.info("Consulta todas las sucursales.");
    return this.officeRepository.findAll().stream().map(this::mapped).collect(Collectors.toList());
  }

  /** {@inheritDoc}. */
  @Override
  @Transactional
  public void create(OfficeRequest request) {
    log.debug("Datos de la sucursal a guardar {}", request);
    List<OfficeEntity> offices = this.officeRepository.findBy(request.getName());
    if (ObjectUtils.isEmpty(offices)) {
      this.officeRepository.save(this.mapped(request));
    } else {
      throw new NotDataFoundException(ErrorConstant.RECORD_NOT_FOUND_MESSAGE);
    }
  }

  /** {@inheritDoc}. */
  @Override
  @Transactional
  public void update(Long id, OfficeRequest request) {
    log.info("Actualiza los datos de la sucursal {}", request);
    Optional<OfficeEntity> entity = this.validateOffice(id);
    if (entity.isPresent()) {
      request.setId(id);
      this.officeRepository.save(this.mapped(request));
    } else {
      throw new BusinessException(
          ErrorConstant.RECORD_NOT_FOUND_CODE, ErrorConstant.OFFICE_NOT_FOUND_MESSAGE);
    }
  }

  /** {@inheritDoc}. */
  @Override
  public List<OfficeResponse> findBy(final String search) {
    log.debug("Consulta la sucursal por {}", search);
    return this.officeRepository.findBy(search).stream()
        .map(this::mapped)
        .collect(Collectors.toList());
  }

  /**
   * Valida si la sucursal existe.
   *
   * @param id identificador de la sucursal
   * @return entidad de sucursal {@link OfficeEntity}
   */
  private Optional<OfficeEntity> validateOffice(Long id) {
    return this.officeRepository.findById(id);
  }

  /**
   * Convierte un entity de tipo {@link OfficeEntity} a un objeto {@link OfficeResponse}
   *
   * @param entity entidad de sucursal
   * @return {@link OfficeResponse} respuesta de sucursal
   */
  private OfficeResponse mapped(OfficeEntity entity) {
    OfficeResponse response = new ModelMapper().map(entity, OfficeResponse.class);
    response.setManager(
        entity.getManager().getFirstName()
            + Utilities.WHITE_SPACE
            + entity.getManager().getLastName());
    return response;
  }

  /**
   * Convierte un objeto {@link OfficeRequest} a un entity de tipo {@link OfficeEntity}
   *
   * @param request petición con los datos de la sucursal
   * @return {@link OfficeEntity} entidad de sucursal
   */
  private OfficeEntity mapped(OfficeRequest request) {
    return new ModelMapper().map(request, OfficeEntity.class);
  }
}
