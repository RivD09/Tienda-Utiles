package com.example.tiendautil.dao;

import com.example.tiendautil.domain.Articulos;
import com.example.tiendautil.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaDao extends JpaRepository<Factura, Integer> {
}
