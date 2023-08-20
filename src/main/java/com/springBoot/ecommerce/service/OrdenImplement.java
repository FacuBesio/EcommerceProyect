package com.springBoot.ecommerce.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.ecommerce.model.Orden;
import com.springBoot.ecommerce.model.Usuario;
import com.springBoot.ecommerce.repository.IOrdenRepository;

@Service
public class OrdenImplement implements IOrdenService {
	@Autowired
	IOrdenRepository ordenRepository;
	private List<Orden> listaOrdenUsuario;
	
	@Override
	public List<Orden> findAll() {
		return ordenRepository.findAll();
	}

	@Override
	public Optional<Orden> findById(Integer id) {
		return ordenRepository.findById(id);
	}

	@Override
	public Orden save(Orden orden) {
		ordenRepository.save(orden);
		return null;
	}

	@Override
	public String generarNumeroOrden(Orden orden) {
		int ordenId = orden.getId();
		DecimalFormat formatoOrden = new DecimalFormat("00000000000");
		return formatoOrden.format(ordenId);
	}

	@Override
	public List<Orden> findByUsuario(Usuario usuario) {
		List<Orden> listaOrden = ordenRepository.findAll();
		listaOrdenUsuario = null;
		for(Orden o : listaOrden) {
			if (o.getUsuario().equals(usuario)) {
				listaOrdenUsuario.add(o);
			}
		}
		return listaOrdenUsuario;
	}

}
