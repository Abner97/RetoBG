package com.abraham.app.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abraham.app.security.entity.Editor;
import com.abraham.app.security.repository.EditorRepository;

@Service
@Transactional
public class EditorServiceImplementation implements EditorService {
	@Autowired
	EditorRepository editorRepository;

	@Override
	public Optional<Editor> getByEditorName(String username) {
		return editorRepository.findByUsername(username);
	}

	@Override
	public Boolean existsByUsername(String username) {
		return editorRepository.existsByUsername(username);
	}

	@Override
	public Boolean existsByEditorEmail(String email) {
		return editorRepository.existsByEmail(email);
	}

	@Override
	public void save(Editor editor) {
		editorRepository.save(editor);

	}
}
