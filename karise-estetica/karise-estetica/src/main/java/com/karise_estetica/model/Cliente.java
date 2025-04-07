package com.karise_estetica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Profissional profissional;

    @ManyToOne
    private Servico servico;
}
