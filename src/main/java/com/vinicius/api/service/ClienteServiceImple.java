package com.vinicius.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinicius.api.model.Cliente;
import com.vinicius.api.model.Endereco;
import com.vinicius.api.repository.ClienteRepository;
import com.vinicius.api.repository.EnderecoRepository;

@Service
public class ClienteServiceImple implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        criarCliente(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente novoCliente) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            criarCliente(cliente.get());
        } 
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void criarCliente(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente); 
    }
}
