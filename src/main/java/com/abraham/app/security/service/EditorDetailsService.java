package com.abraham.app.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abraham.app.security.entity.Editor;
import com.abraham.app.security.entity.EditorPrincipal;

@Service
@Transactional
public class EditorDetailsService implements UserDetailsService{

	@Autowired
	EditorServiceImplementation editorService;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Optional<Editor> editor = editorService.getByEditorName(name);
		
		return EditorPrincipal.build(editor.get());
	}

}
