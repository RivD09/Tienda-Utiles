package com.example.tiendautil.servicio;

import com.example.tiendautil.domain.Cliente;
import com.example.tiendautil.domain.DetalleFactura;
import com.example.tiendautil.domain.Factura;

import java.util.List;

public interface FacturaService {

    public void guardarFactura(Factura factura);
    public float calcularTotal(Factura factura);
    public List<Factura> listarFacturas();
    public void eliminar(Factura factura);
}
