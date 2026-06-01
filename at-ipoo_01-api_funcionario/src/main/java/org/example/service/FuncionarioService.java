package org.example.service;

import org.example.model.Funcionario;
import org.example.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> findById(int id){
        return funcionarioRepository.findById(id);
    }

    public Funcionario cadastrar(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario update(int id, Funcionario funcionarioAtualizado){
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + id));
        funcionario.setNome(funcionarioAtualizado.getNome());
        funcionario.setTelefone(funcionarioAtualizado.getTelefone());
        funcionario.setEmail(funcionarioAtualizado.getEmail());
        funcionario.setEndereco(funcionarioAtualizado.getEndereco());
        funcionario.setCidade(funcionarioAtualizado.getCidade());
        funcionario.setSalario(funcionarioAtualizado.getSalario());
        funcionario.setDataNasc(funcionarioAtualizado.getDataNasc());
        funcionario.setDataCadas(funcionarioAtualizado.getDataCadas());
        return funcionarioRepository.save(funcionario);
    }
    public void delete(int id){
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado com ID: " + id);
        }
        funcionarioRepository.deleteById(id);
    }
}
