package com.springBoot.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.ecommerce.model.DetalleOrden;
import com.springBoot.ecommerce.repository.IDetalleOrdenRepository;


@Service
public class DetalleOrdenImplement implements IDetalleOrdenService {
	 @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;
	 
	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		
		return detalleOrdenRepository.save(detalleOrden);
	}

}
