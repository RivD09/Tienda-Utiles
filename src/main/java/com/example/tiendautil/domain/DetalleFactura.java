package com.example.tiendautil.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "detalle_factura")
public class DetalleFactura implements Serializable {
    public static final long serialVersionUID = 1L;

    @EmbeddedId
    private PkDetalleFactura pkDetalleFactura;

    @ManyToOne
    @JoinColumn(name ="cod_articulo")
    private Articulos articulos;

    private int cantidad;

    private float precio;
}
