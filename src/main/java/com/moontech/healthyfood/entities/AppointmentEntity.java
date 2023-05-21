package com.moontech.healthyfood.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moontech.healthyfood.constants.DatabaseConstant;
import com.moontech.healthyfood.enums.Status;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidad para citas.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 25 abr., 2023
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = DatabaseConstant.TABLE_APPOINTMENT)
public class AppointmentEntity extends AuditableEntity {
  /** Identificador de la cita. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  /** Título de la cita */
  @Column(name = DatabaseConstant.PROPERTY_APPOINTMENT_NAME, nullable = false, length = 50)
  private String title;
  /** Fecha inicio. */
  @Column(name = DatabaseConstant.PROPERTY_APPOINTMENT_START_DATE, nullable = false)
  private LocalDateTime start;
  /** Fecha final. */
  @Column(name = DatabaseConstant.PROPERTY_APPOINTMENT_END_DATE, nullable = false)
  private LocalDateTime end;
  /** Propiedad para el status de la cita. */
  @Enumerated(EnumType.ORDINAL)
  private Status status;
  /** Citas pertenecientes al cliente. */
  @JsonIgnore
  @ManyToOne
  @JoinColumn(
      name = DatabaseConstant.PROPERTY_CUSTOMER_ID,
      referencedColumnName = DatabaseConstant.PROPERTY_APPOINTMENT)
  private CustomerEntity customer;
}
