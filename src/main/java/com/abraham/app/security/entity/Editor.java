package com.abraham.app.security.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Editor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3448790359848132084L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	private String name;
	@Column(unique = true, length = 50, nullable = false)
	private String username;
	@Column(length = 50, nullable = false)
	private String email;
	private String password;
	
	@Column(nullable=false)
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="editor_rol",joinColumns=@JoinColumn(name="editor_id"),
	inverseJoinColumns = @JoinColumn(name="rol_id"))
	
	private Set<Rol> roles = new HashSet<>();

	public Editor() {
		
	}

	public Editor( String name, String username, String email, String password) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	
	
	
	
	
	
	
}