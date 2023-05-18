package com.example.tiendautil.servicio;

import com.example.tiendautil.dao.ArticuloDao;
import com.example.tiendautil.domain.Articulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    @Autowired
    private ArticuloDao articuloDao;

    @Override
    @Transactional(readOnly = true)
    public List<Articulo> listarArticulos() {
        return (List<Articulo>) articuloDao.findAll() ;
    }

    @Override
    @Transactional
    public void guardar(Articulo articulo) {
        articuloDao.save(articulo);
    }

    @Override
    @Transactional
    public void eliminar(Articulo articulo) {
        articuloDao.delete(articulo);
    }

    @Override
    @Transactional(readOnly = true)
    public Articulo encontrarArticulo(Articulo articulo) {
        return articuloDao.findById(articulo.getCod_articulo()).orElse(null);
    }
}
