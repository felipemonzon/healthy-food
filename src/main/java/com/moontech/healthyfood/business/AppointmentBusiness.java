package com.moontech.healthyfood.business;

import com.moontech.healthyfood.entities.AppointmentEntity;
import com.moontech.healthyfood.enums.Status;
import com.moontech.healthyfood.models.responses.AppointmentResponse;
import com.moontech.healthyfood.repositories.AppointmentRepository;
import com.moontech.healthyfood.services.AppointmentService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de las reglas de negocio para citas.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 26 abr., 2023
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentBusiness implements AppointmentService {
  /** Repositorio para citas. */
  private final AppointmentRepository appointmentRepository;

  /** {@inheritDoc}. */
  @Override
  @Transactional(readOnly = true)
  public List<AppointmentResponse> retrieve() {
    log.info("Consulta todas las citas");
    return this.appointmentRepository.findAll().stream()
        .map(this::mapping)
        .collect(Collectors.toList());
  }

  /** {@inheritDoc}. */
  @Override
  @Transactional(readOnly = true)
  public List<AppointmentResponse> findByCustomerAndStatus(
      final Long customerId, final Status status) {
    log.info("Consulta todas las citas activas del cliente {} con status {}", customerId, status);
    return this.appointmentRepository.findByCustomerIdAndStatus(customerId, status).stream()
        .map(this::mapping)
        .collect(Collectors.toList());
  }

  /**
   * Convierte una entidad {@code AppointmentEntity} a uno de tipo {@code AppointmentResponse}
   *
   * @param entity objeto de tipo {@link AppointmentEntity}
   * @return objeto de salida de la api de citas
   */
  private AppointmentResponse mapping(AppointmentEntity entity) {
    return new ModelMapper().map(entity, AppointmentResponse.class);
  }
}
