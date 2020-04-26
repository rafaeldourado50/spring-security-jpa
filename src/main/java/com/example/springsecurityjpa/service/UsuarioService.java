package com.example.springsecurityjpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springsecurityjpa.model.Usuario;
import com.example.springsecurityjpa.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Usuario findById(Integer id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) { 
			return usuario.get();
		}
		return null;
	}

	public void deleteById(Integer id) {
		usuarioRepository.deleteById(id);
	}

	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
}
