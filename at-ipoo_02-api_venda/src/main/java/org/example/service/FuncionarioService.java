package org.example.service;

import org.example.dto.FuncionarioResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class FuncionarioService {

    private final RestTemplate restTemplate;

    public FuncionarioService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FuncionarioResponseDTO buscarFuncionarioPorId(int id) {
        String url = "http://localhost:8081/funcionarios/" + id;
        try {
            FuncionarioResponseDTO funcionario = restTemplate.getForObject(url, FuncionarioResponseDTO.class);
            if (funcionario == null) {
                throw new RuntimeException("Funcionário não encontrado com ID: " + id);
            }
            return funcionario;
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("Funcionário não encontrado com ID: " + id);
        } catch (ResourceAccessException e) {
            throw new RuntimeException("API de Funcionários indisponível.");
        }
    }
}
