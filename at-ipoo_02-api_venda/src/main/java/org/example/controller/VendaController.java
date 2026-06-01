package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.dto.VendaRequestDTO;
import org.example.model.Venda;
import org.example.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendas")
@Tag(name = "Vendas", description = "Endpoints para cadastro e gerenciamento de vendas")
public class VendaController {
    @Autowired
    private VendaService vendaService;

    @GetMapping
    @Operation(summary = "Listar vendas", description = "Retorna todas as vendas cadastradas")
    public List<Venda> findAll() {
        return vendaService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar venda por ID", description = "Retorna uma venda pelo ID")
    public Optional<Venda> findById(@PathVariable("id") int id){
        return vendaService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Cadastrar venda", description = "Cadastra uma nova venda e busca os dados do funcionário responsável na API de Funcionários")
    public ResponseEntity<?> cadastrar(@RequestBody VendaRequestDTO dto){
        try {
            Venda venda = vendaService.cadastrar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(venda);
        } catch (RuntimeException e) {
            String msg = e.getMessage();
            if (msg != null && msg.contains("indisponível")) {
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("{\"erro\": \"" + msg + "\"}");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"erro\": \"" + msg + "\"}");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar venda", description = "Atualiza os dados de uma venda existente pelo ID")
    public Venda update(@PathVariable("id") int id, @RequestBody Venda venda){
        return vendaService.update(id, venda);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar venda", description = "Remove uma venda pelo ID")
    public void delete(@PathVariable("id") int id){
        vendaService.delete(id);
    }
}
