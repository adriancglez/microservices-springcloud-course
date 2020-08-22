package com.smoothiemx.web.app.services;

import com.smoothiemx.web.app.models.Producto;

import java.util.List;

public interface IProductoService {

    public List<Producto> findAll();

    public Producto findById(long id);
}