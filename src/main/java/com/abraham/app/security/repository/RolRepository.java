package com.abraham.app.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abraham.app.security.entity.Rol;
import com.abraham.app.security.enums.RolName;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
	Optional<Rol> findByRolName(RolName rolNombre);
}
