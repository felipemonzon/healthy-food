package com.moontech.healthyfood.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moontech.healthyfood.constants.DatabaseConstant;
import com.moontech.healthyfood.enums.Genre;
import java.util.Set;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidad para cliente.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 25 abr., 2023
 */
@Getter
@Setter
@ToString
@Entity(name = DatabaseConstant.TABLE_CUSTOMER)
@Table(name = DatabaseConstant.TABLE_CUSTOMER)
public class CustomerEntity extends AuditableEntity {
  /** Identificador del usuario. */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  /** Propiedad primer nombre. */
  @Column(name = DatabaseConstant.FIRST_NAME_PROPERTY, nullable = false)
  private String firstName;
  /** Propiedad para el apellido. */
  @Column(name = DatabaseConstant.LAST_NAME_PROPERTY, nullable = false)
  private String lastName;
  /** Propiedad para el correo. */
  @Column(name = DatabaseConstant.EMAIL_PROPERTY, nullable = false)
  private String email;
  /** Propiedad para el celular. */
  @Column(name = DatabaseConstant.CELLPHONE_PROPERTY, nullable = false)
  private String cel;
  /** Propiedad para el género. */
  @Enumerated(EnumType.ORDINAL)
  private Genre genre;
  /** Citas del cliente. */
  @JsonIgnore
  @OneToMany(
      mappedBy = DatabaseConstant.RELATION_APPOINTMENT_CUSTOMER_NAME,
      cascade = CascadeType.ALL)
  @ToString.Exclude
  private Set<AppointmentEntity> appointments;
}
