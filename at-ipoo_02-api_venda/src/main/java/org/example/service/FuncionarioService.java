package org.example.service;

import org.example.dto.FuncionarioResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FuncionarioService {

    private final RestTemplate restTemplate;

    public FuncionarioService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FuncionarioResponseDTO buscarFuncionarioPorId(int id) {
        String url = "http://localhost:8081/funcionarios/" + id;

        FuncionarioResponseDTO funcionario = restTemplate.getForObject(url, FuncionarioResponseDTO.class);

        if (funcionario == null) {
            throw new RuntimeException("Funcionário não encontrado com ID: " + id);
        }

        return funcionario;
    }
}
