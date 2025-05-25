package com.karise_estetica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.karise_estetica.validation.CPF;

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

    @CPF(message = "CPF inválido")
    @NotBlank(message = "O CPF é obrigatório")
    @Column(unique = true)
    private String cpf;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;
}
