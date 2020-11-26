package com.abraham.app.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import com.abraham.app.security.enums.RolName;


@Entity
public class Rol {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

@Column(nullable=false,length = 50)
@Enumerated(EnumType.STRING)
 private RolName rolName;

public Rol( RolName rolName) {
	this.rolName = rolName;
}

public Rol() {

}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public RolName getRolName() {
	return rolName;
}

public void setRolName( RolName rolName) {
	this.rolName = rolName;
}






}
