package com.example.tiendautil.dao;

import com.example.tiendautil.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDao extends JpaRepository<Cliente, Integer> {
}
