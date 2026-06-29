package com.SpringSecurityJWT.models;


import jakarta.persistence.*;
import jakarta.transaction.UserTransaction;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    @Size(max = 80)
    private String email;


    @NotBlank
    @Size(max = 30)
    private String username;


    @NotBlank
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    /*
     * Relación muchos a muchos entre usuarios y roles.
     *
     * fetch = FetchType.EAGER:
     * Carga los roles inmediatamente junto con el usuario.
     * Es útil en seguridad porque Spring necesita saber los roles al autenticar.
     *
     * targetEntity = RoleEntity.class:
     * Indica que la relación es con la entidad RoleEntity.
     * En este caso también se puede inferir por el tipo Set<RoleEntity>.
     *
     * cascade = CascadeType.PERSIST:
     * Si se guarda un usuario con roles nuevos, también intenta guardar esos roles.
     * No elimina ni actualiza roles existentes automáticamente.
     *
     * @JoinTable:
     * Crea la tabla intermedia user_roles, donde se relacionan usuarios y roles.
     */

}
