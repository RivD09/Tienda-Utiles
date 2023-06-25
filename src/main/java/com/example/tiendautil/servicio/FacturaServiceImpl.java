package com.example.tiendautil.servicio;

import com.example.tiendautil.dao.ClienteDao;
import com.example.tiendautil.dao.DetalleFacturaDao;
import com.example.tiendautil.dao.FacturaDao;
import com.example.tiendautil.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class FacturaServiceImpl implements FacturaService{

    @Autowired
    private FacturaDao facturaDao;

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private DetalleFacturaDao detalleFacturaDao;

    @Autowired
    private ClienteService clienteService;

    @Override
    @Transactional
    public void guardarFactura(Factura factura) {  //Ya tengo añadido cod_cliente

        //Guardar cliente en la bd y añadirlo al objeto factura
        Cliente cliente = factura.getCliente();
        cliente = clienteService.guardarYDevolver(cliente);
        factura.setCliente(cliente);

        //Insertar fecha en factura
        LocalDate fechaActual = LocalDate.now();

        factura.setFecha(Date.valueOf(fechaActual));

        log.warn("*Guardando factura*");

        //Obteniendo factura con su PK
        log.warn("ANTES DE GUARDAR FACTURA");

        Factura facturaGuardada = facturaDao.saveAndFlush(factura);

        log.warn("ID FACTURA  " + facturaGuardada.getNum_factura());
        //Insertando lista de DetalleFactura
        List<DetalleFactura> detalleFacturasGuardada= facturaGuardada.getDetalleFacturas();

        for (int i=0; i<detalleFacturasGuardada.size() ; i++){
            detalleFacturasGuardada.get(i).setFactura(facturaGuardada);
        }
        facturaGuardada.setDetalleFacturas(detalleFacturasGuardada);

        //Insertando CuentaPorCobrar
        CuentaCobrar cuentaCobrar = new CuentaCobrar();
        cuentaCobrar.setEstado("Pendiente");
        cuentaCobrar.setFecha_cobro(Date.valueOf(fechaActual.plusDays(7)));

        facturaGuardada.setCuentaCobrar(cuentaCobrar);
        facturaDao.save(facturaGuardada);
    }

    public float calcularTotal(Factura factura){
        float total = 0;
        List<DetalleFactura> detalleFacturasGuardada= factura.getDetalleFacturas();

        for (int i=0; i<factura.getDetalleFacturas().size() ; i++){
            total = total + detalleFacturasGuardada.get(i).getCantidad()*detalleFacturasGuardada.get(i).getPrecio();
        }
        return total;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Factura> listarFacturas() {
        return (List<Factura>) facturaDao.findAll();
    }

    @Override
    @Transactional
    public void eliminar(Factura factura) {
        factura = facturaDao.findById(factura.getNum_factura()).orElse(null);
        facturaDao.delete(factura);
    }

}
