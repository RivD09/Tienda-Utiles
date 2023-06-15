package com.example.tiendautil.servicio;

import com.example.tiendautil.dao.ClienteDao;
import com.example.tiendautil.domain.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteDao clienteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarClientes() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Cliente cliente) {
        clienteDao.save(cliente);
    }

    public Cliente guardarYDevolver(Cliente cliente){
        cliente = clienteDao.saveAndFlush(cliente);
        log.warn("***" + cliente.getCod_cliente() + " " + cliente.getNombre() + " " + cliente.getRuc());
        return cliente;
    }

    @Override
    @Transactional
    public void eliminar(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente encontrarCliente(Cliente cliente) {
        return clienteDao.findById(cliente.getCod_cliente()).orElse(null);
    }
}
