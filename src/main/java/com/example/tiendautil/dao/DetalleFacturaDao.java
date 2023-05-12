package com.example.tiendautil.dao;

import com.example.tiendautil.domain.DetalleFactura;
import com.example.tiendautil.domain.PkDetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleFacturaDao extends JpaRepository<DetalleFactura, PkDetalleFactura> {

}
