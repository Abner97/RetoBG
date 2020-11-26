package com.abraham.app.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.abraham.app.security.entity.Editor;

@Repository
public interface EditorRepository extends JpaRepository<Editor, Long> {
	 Optional<Editor> findByUsername(String username);
	 boolean existsByUsername(String username);
	 boolean existsByEmail(String email);
}
