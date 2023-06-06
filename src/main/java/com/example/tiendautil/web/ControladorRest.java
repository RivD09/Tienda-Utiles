package com.example.tiendautil.web;

import com.example.tiendautil.domain.Articulo;
import com.example.tiendautil.servicio.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lista")
public class ControladorRest {

    @Autowired
    private ArticuloService articuloService;

    @GetMapping("/a")
    public List<Articulo> listaArticulos(){
        var articulos = articuloService.listarArticulos();
        return articulos;
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/buscar/{text}")
    public List<Articulo> buscarArticulo(@PathVariable("text") String nombre){
        var articulos = articuloService.buscarArticulo(nombre);
        return articulos;
    }

}
