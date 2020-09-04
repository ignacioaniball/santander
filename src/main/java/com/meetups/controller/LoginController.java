package com.meetups.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.meetups.model.entity.Cliente;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {

//	@GetMapping("/login")
//	public String login(@RequestParam(value="error", required=false) String error,
//			@RequestParam(value="logout", required = false) String logout,
//			Model model, Principal principal, RedirectAttributes flash) {
//		
//		if(principal != null) {
//			flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
//			return "redirect:/";
//		}
//		
//		if(error != null) {
//			model.addAttribute("error", "Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
//		}
//		
//		if(logout != null) {
//			model.addAttribute("success", "Ha cerrado sesión con éxito!");
//		}
//		
//		return "login";
//	}
	
	@PostMapping("/cliente")
	public Cliente loginCliente(@RequestParam("user") String username, @RequestParam("password") String password) {
		
		String tokenString = getJWTToken(username);
		Cliente cliente = new Cliente();
		cliente.setNombre(username);
		cliente.setToken(tokenString);
		
		return cliente;
	}
	
	private String getJWTToken(String username) {
		String secretKey ="mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String tokenString = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities", grantedAuthorities.stream()
						.map(GrantedAuthority :: getAuthority)
						.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		
		return "Bearer " + tokenString;
				
	}
}