package com.example.tiendautil.dao;

import com.example.tiendautil.domain.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticuloDao extends JpaRepository<Articulo, Integer> {

}
