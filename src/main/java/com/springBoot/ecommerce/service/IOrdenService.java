package com.springBoot.ecommerce.service;

import java.util.List;
import java.util.Optional;
import com.springBoot.ecommerce.model.Orden;
import com.springBoot.ecommerce.model.Usuario;


public interface IOrdenService {
	List<Orden> findAll();
    Optional<Orden> findById(Integer id);
    Orden save (Orden orden);
    String generarNumeroOrden(Orden orden);
    List<Orden> findByUsuario (Usuario usuario);
}
