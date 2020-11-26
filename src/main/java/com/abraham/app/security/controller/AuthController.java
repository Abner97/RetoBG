package com.abraham.app.security.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abraham.app.dto.Message;
import com.abraham.app.security.dto.JwtDto;
import com.abraham.app.security.dto.LoginEditor;
import com.abraham.app.security.dto.NewEditor;
import com.abraham.app.security.entity.Editor;
import com.abraham.app.security.entity.Rol;
import com.abraham.app.security.enums.RolName;
import com.abraham.app.security.jwt.JwtProvider;
import com.abraham.app.security.service.EditorServiceImplementation;
import com.abraham.app.security.service.RolServiceImplementation;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	EditorServiceImplementation editorService;

	@Autowired
	RolServiceImplementation rolService;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/new")
	public ResponseEntity<?> newEditor(@Valid @RequestBody NewEditor newEditor, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<Object>(new Message("Bad Fields or invalid email"), HttpStatus.BAD_REQUEST);

		}
		if (editorService.existsByUsername((newEditor.getUsername()))) {
			return new ResponseEntity<Object>(new Message("That username already exist"), HttpStatus.BAD_REQUEST);
		}
		if (editorService.existsByEditorEmail(newEditor.getEmail())) {
			return new ResponseEntity<Object>(new Message("That email already exist"), HttpStatus.BAD_REQUEST);
		}

		Editor editor = new Editor(newEditor.getName(), newEditor.getUsername(), newEditor.getEmail(),
				passwordEncoder.encode(newEditor.getPassword()));

		Set<Rol> roles = new HashSet<>();
		if (newEditor.getRoles().contains("admin")) {
			roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
			
		}
		roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
		editor.setRoles(roles);
		editorService.save(editor);

		return new ResponseEntity<Object>(new Message("User created"), HttpStatus.CREATED);

	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginEditor loginEditor, BindingResult bindingResult) {
		

		//Se que no es la mejor solución pero el tiempo no me daba para más :c
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginEditor.getUsername(), loginEditor.getPassword()));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtProvider.generateToken(authentication);
			UserDetails editorDetails = (UserDetails) authentication.getPrincipal();
			JwtDto jwtDto = new JwtDto(jwt, editorDetails.getUsername(), editorDetails.getAuthorities());
			return new ResponseEntity<>(jwtDto, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity(new Message("Invalid username or password"), HttpStatus.BAD_REQUEST);
		}
		
	
	}

}
