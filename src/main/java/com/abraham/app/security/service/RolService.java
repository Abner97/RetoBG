package com.abraham.app.security.service;

import java.util.Optional;

import com.abraham.app.security.entity.Rol;
import com.abraham.app.security.enums.RolName;

public interface RolService {
   public Optional<Rol> getByRolName(RolName rolNombre);
   public void save(Rol rol);
}
