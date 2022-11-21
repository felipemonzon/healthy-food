package com.moontech.library.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moontech.library.constants.DatabaseConstant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/**
 * Entidad para sucursales.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 17 jul., 2022
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = DatabaseConstant.TABLE_OFFICES)
public class OfficeEntity extends AuditableEntity {
  /** Serial. */
  private static final long serialVersionUID = -8089684905670011173L;
  /** Identificador de la sucursal. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  /** Nombre de la sucursal. */
  @Column(name = DatabaseConstant.PROPERTY_OFFICE_NAME)
  private String name;
  /** Dirección de la sucursal. */
  @Column(name = DatabaseConstant.PROPERTY_ADDRESS)
  private String address;
  /** Teléfono de la sucursal. */
  @Column(name = DatabaseConstant.PROPERTY_PHONE)
  private String phone;
  /** Status de la sucursal. */
  @Column(name = DatabaseConstant.PROPERTY_OFFICE_STATUS)
  private Boolean active;
  /** Encargado de la sucursal. */
  @ToString.Exclude
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = DatabaseConstant.PROPERTY_OFFICE_MANAGER)
  private UserEntity manager;
  /** Usuarios pertenecientes a la sucursal. */
  @JsonIgnore
  @OneToMany(mappedBy = DatabaseConstant.RELATION_OFFICE_USERS_NAME, cascade = CascadeType.ALL)
  @ToString.Exclude
  private Set<UserEntity> users;
}
