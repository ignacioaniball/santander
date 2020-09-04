package com.meetups.model.DAO;

import org.springframework.data.repository.CrudRepository;

import com.meetups.model.entity.Cliente;


public interface IClientesDao extends CrudRepository<Cliente, Long>{
	
	
}
