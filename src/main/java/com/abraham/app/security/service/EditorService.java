package com.abraham.app.security.service;

import java.util.Optional;

import com.abraham.app.security.entity.Editor;


public interface EditorService {
	public Optional<Editor> getByEditorName(String name);
	public Boolean existsByUsername(String username);
	public Boolean existsByEditorEmail(String email);
	public void save(Editor editor);
}
