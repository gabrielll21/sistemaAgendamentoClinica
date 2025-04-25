package com.karise_estetica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Email(message = "Informe um e-mail válido")
    @NotBlank(message = "O e-mail é obrigatório")
    @Column(unique = true)
    private String email;

// @NotBlank(message = "O CPF é obrigatório")
// @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos")
// @Column(unique = true)
// private String cpf;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;
}