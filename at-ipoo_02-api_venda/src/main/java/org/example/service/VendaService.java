package org.example.service;

import org.example.dto.FuncionarioResponseDTO;
import org.example.dto.VendaRequestDTO;
import org.example.model.Venda;
import org.example.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final FuncionarioService funcionarioService;

    public VendaService(VendaRepository vendaRepository, FuncionarioService funcionarioService) {
        this.vendaRepository = vendaRepository;
        this.funcionarioService = funcionarioService;
    }

    public List<Venda> findAll() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> findById(int id){
        return vendaRepository.findById(id);
    }

    public Venda cadastrar(VendaRequestDTO dto){
        FuncionarioResponseDTO funcionario = funcionarioService.buscarFuncionarioPorId(dto.getFuncionarioId());

        Venda venda = new Venda();
        venda.setDescricao(dto.getDescricao());
        venda.setDataVenda(dto.getDataVenda());
        venda.setNomeProduto(dto.getNomeProduto());
        venda.setValorProduto(dto.getValorProduto());
        venda.setQtd(dto.getQtd());
        venda.setValorTotalVenda(dto.getValorProduto() * dto.getQtd());

        venda.setFuncionarioId(funcionario.getId());
        venda.setFuncionarioNome(funcionario.getNome());
        venda.setFuncionarioEmail(funcionario.getEmail());
        venda.setFuncionarioTelefone(funcionario.getTelefone());

        return vendaRepository.save(venda);
    }

    public Venda update(int id, Venda vendaAtualizada){
        Optional<Venda> vendaExistente = vendaRepository.findById(id);
        if (vendaExistente.isPresent()){
            Venda venda = vendaExistente.get();
            venda.setDescricao(vendaAtualizada.getDescricao());
            venda.setDataVenda(vendaAtualizada.getDataVenda());
            venda.setNomeProduto(vendaAtualizada.getNomeProduto());
            venda.setValorProduto(vendaAtualizada.getValorProduto());
            venda.setQtd(vendaAtualizada.getQtd());
            venda.setValorTotalVenda(venda.getValorProduto() * venda.getQtd());
            return vendaRepository.save(venda);
        }
        return null;
    }
    public void delete(int id){
        vendaRepository.deleteById(id);
    }
}
