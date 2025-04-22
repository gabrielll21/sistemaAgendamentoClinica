package com.karise_estetica.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do serviço é obrigatório")
    private String nome;

    @NotBlank(message = "A descrição do serviço é obrigatória")
    private String descricao;

    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero")
    private BigDecimal preco;

    @NotNull(message = "A duração é obrigatória")
    @Min(value = 1, message = "A duração deve ser no mínimo 1 minuto")
    private Integer duracaoEmMinutos;

    private boolean promocaoAtiva;

    @DecimalMin(value = "0.0", message = "O desconto deve ser positivo")
    private BigDecimal desconto;
}