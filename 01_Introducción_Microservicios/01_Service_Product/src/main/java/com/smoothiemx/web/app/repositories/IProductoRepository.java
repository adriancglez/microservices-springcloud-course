package com.smoothiemx.web.app.repositories;

import com.smoothiemx.web.app.models.Producto;
import org.springframework.data.repository.CrudRepository;

public interface IProductoRepository extends CrudRepository<Producto, Long> {
}