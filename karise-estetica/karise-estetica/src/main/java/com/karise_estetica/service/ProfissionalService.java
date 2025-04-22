package com.karise_estetica.service;


import com.karise_estetica.model.Profissional;
import com.karise_estetica.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

    @Service
    public class ProfissionalService {

        @Autowired
        private ProfissionalRepository profissionalRepository;

        public Profissional salvar(Profissional profissional) {
            return profissionalRepository.save(profissional);
        }

        public List<Profissional> listarTodos() {
            return profissionalRepository.findAll();
        }

        public Optional<Profissional> buscarPorId(Long id) {
            return profissionalRepository.findById(id);
        }

        public void deletar(Long id) {
            profissionalRepository.deleteById(id);
        }
    }
