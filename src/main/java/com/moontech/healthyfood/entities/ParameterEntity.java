package com.moontech.healthyfood.entities;

import com.moontech.healthyfood.constants.DatabaseConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad para la tabla "parameters".
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 23 jul., 2022
 */
@Entity
@Getter
@Setter
@Table(name = DatabaseConstant.TABLE_PARAMETERS)
public class ParameterEntity extends AuditableEntity {
  /** Identificador del parámetro. */
  @Id
  @Column(name = DatabaseConstant.PROPERTY_PARAMETER_ID, nullable = false, length = 50)
  private String id;
  /** Descripción del parámetro. */
  @Column(
      name = DatabaseConstant.PROPERTY_PARAMETER_NAME,
      nullable = false,
      unique = true,
      length = 100)
  private String description;
  /** Valor del parámetro. */
  @Column(name = DatabaseConstant.PROPERTY_PARAMETER_VALUE, nullable = false, length = 300)
  private String value;
  /** Status del parámetro. */
  @Column(name = DatabaseConstant.PROPERTY_STATUS, nullable = false)
  private boolean status;
}
