package com.abraham.app.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abraham.app.security.entity.Rol;
import com.abraham.app.security.enums.RolName;
import com.abraham.app.security.repository.RolRepository;

@Service
@Transactional
public class RolServiceImplementation implements RolService {
	
	@Autowired
	RolRepository rolRepository;

	@Override
	public Optional<Rol> getByRolName(RolName rolNombre) {
		return rolRepository.findByRolName(rolNombre);
	}

	@Override
	public void save(Rol rol) {
		rolRepository.save(rol);
		
	}
}
