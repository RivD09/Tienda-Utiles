package com.example.tiendautil.servicio;

import com.example.tiendautil.dao.ArticulosDao;
import com.example.tiendautil.domain.Articulos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticulosServiceImpl implements ArticulosService{

    @Autowired
    private ArticulosDao articulosDao;

    @Override
    @Transactional(readOnly = true)
    public List<Articulos> listarArticulos() {
        return (List<Articulos>) articulosDao.findAll() ;
    }

    @Override
    @Transactional
    public void guardar(Articulos articulo) {
        articulosDao.save(articulo);
    }

    @Override
    @Transactional
    public void eliminar(Articulos articulo) {
        articulosDao.delete(articulo);
    }

    @Override
    @Transactional(readOnly = true)
    public Articulos encontrarArticulo(Articulos articulos) {
        return articulosDao.findById(articulos.getCod_articulo()).orElse(null);
    }
}
