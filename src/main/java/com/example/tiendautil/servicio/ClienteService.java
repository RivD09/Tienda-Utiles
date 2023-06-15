package com.example.tiendautil.servicio;

import com.example.tiendautil.domain.Cliente;

import java.util.List;

public interface ClienteService {

    public List<Cliente> listarClientes();
    public void guardar(Cliente cliente);

    public Cliente guardarYDevolver(Cliente cliente);

    public void eliminar(Cliente cliente);
    public Cliente encontrarCliente(Cliente cliente);

}
