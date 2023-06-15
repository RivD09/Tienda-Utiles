package com.example.tiendautil.web;

import com.example.tiendautil.domain.Articulo;
import com.example.tiendautil.domain.Cliente;
import com.example.tiendautil.domain.Factura;
import com.example.tiendautil.servicio.ArticuloService;
import com.example.tiendautil.servicio.ClienteService;
import com.example.tiendautil.servicio.FacturaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorVistas {

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FacturaService facturaService;


    @GetMapping("/")
    public String inicio(){
        return "index";
    }

    @GetMapping("/cliente")
    public String listarCliente(Model model, Cliente cliente){
        var clientes = clienteService.listarClientes();
        log.info("*****Listando clientes");
        model.addAttribute("clientes",clientes);
        return "/test/clientes";
    }

    @GetMapping("/articulo")
    public String listarArticulo(Model model, Articulo articulo){
        var articulos = articuloService.listarArticulos();

        model.addAttribute("articulos",articulos);
        log.info("*****Listando articulos");
        return "/test/articulos";
    }

    @GetMapping("/compra")
    public String compra(){
        return "/compraUtiles";
    }

    @PostMapping("/guardarCliente")
    public String guardarCliente(@Valid Cliente cliente, Errors errores){
        log.info("*****Guardando cliente");
        if (errores.hasErrors()){
            return "redirect:/cliente";
        }
        clienteService.guardar(cliente);
        log.info("*****Se guardo cliente");
        return "redirect:/cliente";
    }

    @GetMapping("/eliminarCliente/{cod_cliente}")
    public String eliminarCliente(@Valid Cliente cliente, Errors errores){
        log.info("*****Borrando cliente");
        clienteService.eliminar(cliente);
        log.info("*****Se elimino cliente" + cliente.getNombre());
        return "redirect:/cliente";
    }

    @PostMapping("/guardarArticulo")
    public String guardarArticulo(@Valid Articulo articulo, Errors errores){
        log.info("*****Guardando articulo");
        if (errores.hasErrors()){
            return "redirect:/articulo";
        }
        articuloService.guardar(articulo);
        log.info("*****Se guardo articulo");
        return "redirect:/articulo";
    }

    @GetMapping("/eliminarArticulo/{cod_articulo}")
    public String eliminarArticulo(@Valid Articulo articulo, Errors errores){
        log.info("*****Borrando articulo");
        articuloService.eliminar(articulo);
        log.info("*****Se elimino articulo" + articulo.getNombre());
        return "redirect:/articulo";
    }

    @PostMapping("buscarArticulo")
    public void buscarArticulo(Model model){
        model.getAttribute("nombreArticulo");
    }

    @PostMapping("/factura")
    public String generarFactura(@ModelAttribute Factura factura){
        log.info("*****Generando factura");

        facturaService.guardarFactura(factura);

        return "/factura";
    }

}
