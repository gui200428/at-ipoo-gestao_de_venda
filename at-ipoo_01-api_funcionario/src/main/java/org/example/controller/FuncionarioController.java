package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.model.Funcionario;
import org.example.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
@Tag(name = "Funcionários", description = "Endpoints para cadastro e gerenciamento de funcionários")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    @Operation(summary = "Listar funcionários", description = "Retorna todos os funcionários cadastrados")
    public List<Funcionario> findAll() {
        return funcionarioService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar funcionário por ID", description = "Retorna um funcionário pelo ID")
    public Optional<Funcionario> findById(@PathVariable("id") int id){
        return funcionarioService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Cadastrar funcionário", description = "Cadastra um novo funcionário")
    public Funcionario cadastrar(@RequestBody Funcionario funcionario){
        return funcionarioService.cadastrar(funcionario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar funcionário", description = "Atualiza os dados de um funcionário existente pelo ID")
    public Funcionario update(@PathVariable("id") int id, @RequestBody Funcionario funcionario){
        return funcionarioService.update(id, funcionario);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar funcionário", description = "Remove um funcionário pelo ID")
    public void delete(@PathVariable("id") int id){
        funcionarioService.delete(id);
    }
}
