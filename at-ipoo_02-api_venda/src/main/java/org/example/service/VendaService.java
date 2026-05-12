package org.example.service;

import org.example.model.Venda;
import org.example.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    public List<Venda> findAll() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> findById(int id){
        return vendaRepository.findById(id);
    }

    public Venda cadastrar(Venda venda){
        venda.setValorTotalVenda(venda.getValorProduto() * venda.getQtd());
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
