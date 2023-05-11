package com.example.tiendautil.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "factura")
public class Factura implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num_factura;

    @ManyToOne()
    @JoinColumn(name ="cod_cliente")
    private Cliente cliente;

    private Date fecha;

    //Posible error
    @OneToMany()
    @JoinColumn(name = "num_factura")
    private List<DetalleFactura> detalleFacturas;

    @OneToOne
    @JoinColumn(name = "num_cuenta")
    private CuentaCobrar cuentaCobrar;
}
