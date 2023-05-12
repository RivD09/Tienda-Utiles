package com.example.tiendautil.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cod_cliente;

    @NotEmpty
    private String nombre;

    @NotNull
    private int ruc;

    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas;
}
