package com.meetups.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.meetups.model.DAO.IClientesDao;
import com.meetups.model.entity.Cliente;

@RestController
@SessionAttributes("user")
public class ClientesController {

	@Autowired
	private IClientesDao clienteDao;
	
	@GetMapping(value = {"/listar", "/"})
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de User");
		model.addAttribute("users", clienteDao.findAll());
		return "listar";
	}
	
	@GetMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("titulo", "Formulario de User");
		model.put("clientes", cliente);
		return "form";
	}
	
	@PostMapping("/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Optional<Cliente> user = null;
		
		if (id > 0) {
			user = clienteDao.findById(id);
		} else {
			return "redirect:/list";
		}
		model.put("user", user);
		model.put("titulo", "Editar user");
		return "form";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid @ModelAttribute("user") Cliente user, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de user");
			return "form";
		}
		
		clienteDao.save(user);
		status.setComplete();
		return "redirect:listar";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		
		if(id > 0) {
			clienteDao.deleteById(id);
		}
		return "redirect:/listar";
	}
}
