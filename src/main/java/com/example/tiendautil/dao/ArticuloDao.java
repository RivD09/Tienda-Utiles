package com.example.tiendautil.dao;

import com.example.tiendautil.domain.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticuloDao extends JpaRepository<Articulo, Integer> {
    List<Articulo> findByNombreContaining(String nombre);
}
