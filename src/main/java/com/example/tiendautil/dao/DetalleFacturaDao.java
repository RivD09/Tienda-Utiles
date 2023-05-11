package com.example.tiendautil.dao;

import com.example.tiendautil.domain.Articulos;
import com.example.tiendautil.domain.PkDetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleFacturaDao extends JpaRepository<Articulos, PkDetalleFactura> {

}
