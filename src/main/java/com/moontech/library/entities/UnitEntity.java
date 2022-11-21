package com.moontech.library.entities;

import com.moontech.library.constants.DatabaseConstant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * Entidad para la tabla units.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 23 jul., 2022
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = DatabaseConstant.TABLE_UNITS)
public class UnitEntity extends AuditableEntity {
  /** Identificador de la unidad. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  /** Nombre de la unidad. */
  @NaturalId(mutable = true)
  @Column(name = DatabaseConstant.PROPERTY_UNIT_NAME, nullable = false, length = 50, unique = true)
  private String name;
  /** Abreviación de la unidad. */
  @Column(name = DatabaseConstant.PROPERTY_UNIT_ABBREVIATION, nullable = false, length = 50)
  private String abbreviation;
  /** Status de la unidad. */
  @Column(name = DatabaseConstant.PROPERTY_STATUS, nullable = false)
  private boolean status;
}
