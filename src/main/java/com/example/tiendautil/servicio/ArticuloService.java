package com.example.tiendautil.servicio;

import com.example.tiendautil.domain.Articulo;

import java.util.List;


public interface ArticuloService {

    public List<Articulo> listarArticulos();
    public void guardar(Articulo articulo);
    public void eliminar(Articulo articulo);
    public Articulo encontrarArticulo(Articulo articulo);

    public List<Articulo> buscarArticulo(String nombre);

}
