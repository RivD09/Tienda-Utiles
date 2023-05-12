package com.example.tiendautil.servicio;

import com.example.tiendautil.domain.Articulos;

import java.util.List;


public interface ArticulosService {

    public List<Articulos> listarArticulos();
    public void guardar(Articulos articulo);
    public void eliminar(Articulos articulo);
    public Articulos encontrarArticulo(Articulos articulos);

}
