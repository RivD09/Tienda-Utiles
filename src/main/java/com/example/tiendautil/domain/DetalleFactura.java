package com.example.tiendautil.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "detalle_factura")
public class DetalleFactura implements Serializable {
    public static final long serialVersionUID = 1L;

    @EmbeddedId
    private PkDetalleFactura pkDetalleFactura;

    @ManyToOne
    @JoinColumn(name ="cod_articulo")
    private Articulo articulo;

    private int cantidad;

    private float precio;
}
