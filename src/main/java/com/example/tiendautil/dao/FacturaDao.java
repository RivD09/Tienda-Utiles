package com.example.tiendautil.dao;

import com.example.tiendautil.domain.Articulos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaDao extends JpaRepository<Articulos, Integer> {
}
