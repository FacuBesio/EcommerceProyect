package com.springBoot.ecommerce.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springBoot.ecommerce.model.Producto;
import com.springBoot.ecommerce.service.IDetalleOrdenService;
import com.springBoot.ecommerce.service.IOrdenService;
import com.springBoot.ecommerce.service.IProductoService;
import com.springBoot.ecommerce.model.Usuario;
import com.springBoot.ecommerce.model.DetalleOrden;
import com.springBoot.ecommerce.model.Orden;
//import com.nec2solutions.ecommercespringbootetapas.service.DetalleOrdenService;
//import com.nec2solutions.ecommercespringbootetapas.service.OrdenService;
//import com.nec2solutions.ecommercespringbootetapas.service.UsuarioService;




@Controller
@RequestMapping("/")
public class IndexController {
	
	private Logger LOGGER= LoggerFactory.getLogger(IndexController.class);
	@Autowired
    private IProductoService productoService;
	@Autowired
	private IOrdenService ordenService;
	@Autowired
	private IDetalleOrdenService detalleOrdenService;

    List<DetalleOrden> detalles = new ArrayList<>();
    Orden orden = new Orden();
   
	
	@GetMapping("")
	public String home(Model model) {
		List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
		return "index";
	}
	
	
	@GetMapping("/producto/{id}")
    public String productoDetalle(@PathVariable Integer id, Model model) {
		Producto producto = new Producto();
    	Optional <Producto> optionalProducto = productoService.get(id);
    	producto = optionalProducto.get();
    	LOGGER.info("Producto: {}" + producto);
    	model.addAttribute("producto", producto);
        return "pages/product_detail";
    }
	
	@GetMapping("/delete/cart/{id}")
    public String deleteProductoCart(@PathVariable Integer id, Model model) {
        List<DetalleOrden> ordenesNueva = new ArrayList<>();
        for (DetalleOrden detalleOrden : detalles) {
            if (!Objects.equals(detalleOrden.getProducto().getId(), id)) {
                ordenesNueva.add(detalleOrden);
            }
        }
        detalles = ordenesNueva;
        double sumaTotal;
        sumaTotal = detalles.stream().mapToDouble(DetalleOrden::getTotal).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "redirect:/carrito";
    }
	
	
	
	@GetMapping("/orden")
    public String orderDetail(Model model) {
		Usuario usuario = new Usuario(10003, "Nahuel", "Nahu1979", "nec2@solutions.com.ar", "Sala 444 4B", "12345678", "ADMIN", "123456");
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);
        return "pages/order_detail";
    }
	
	@GetMapping("/guardarorden")
    public String saveOrder(Model model) {
//		ordenService.save(orden);
		
	    List<Producto> productos = productoService.findAll();
	    model.addAttribute("productos", productos);
//	    model.addAttribute("orden", orden);
        return "pages/confirmacionCompra";
    }
	
	@GetMapping("/carrito")
    public String getCart(Model model) {
		model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "pages/cart";
    }
	
	@PostMapping("/carrito")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto;
        double sumaTotal;
        Optional<Producto> optionalProducto = productoService.get(id);
        LOGGER.info("Producto a�adido: {}", optionalProducto.get());
        LOGGER.info("Cantidad: {}", cantidad);
        producto = optionalProducto.get();
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        detalleOrden.setProducto(producto);
        Integer idProducto=producto.getId();
        boolean ingresado=detalles.stream().anyMatch(p -> Objects.equals(p.getProducto().getId(), idProducto));
        if (!ingresado) {
            detalles.add(detalleOrden);
        }else if(ingresado){
    		for(DetalleOrden d : detalles) {
    			if(d.getProducto().getId().equals(idProducto)) {
    				d.setCantidad(d.getCantidad() + cantidad);
    				d.setTotal(producto.getPrecio() * d.getCantidad());
    			}
    		}
    	}
        sumaTotal = detalles.stream().mapToDouble(DetalleOrden::getTotal).sum();
        orden.setTotal(sumaTotal);
        Usuario usuario = new Usuario(10003, "Nahuel", "Nahu1979", "nec2@solutions.com.ar", "Sala 444 4B", "12345678", "ADMIN", "123456");
        orden.setUsuario(usuario);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "pages/cart";
    }
	
	 @PostMapping("/search")
	    public String searchProduct(@RequestParam String nombre, Model model) {
		 	LOGGER.info("Nombre enviado como parámetro {}", nombre);
	        List<Producto> productos = productoService.findAll().stream().filter(p -> p.getNombre().contains(nombre)).collect(Collectors.toList());
	        model.addAttribute("productos", productos);
	        return "usuario/home";
	    }
}