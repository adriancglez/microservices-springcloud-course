package com.smoothiemx.web.app.services;

import com.smoothiemx.web.app.models.Producto;
import com.smoothiemx.web.app.repositories.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService {

    private final IProductoRepository repository;

    @Autowired
    public ProductoServiceImpl(IProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return (List<Producto>) this.repository.findAll();
    }

    @Override
    public Producto findById(long id) {
        return this.repository.findById(id).orElse(null);
    }
}