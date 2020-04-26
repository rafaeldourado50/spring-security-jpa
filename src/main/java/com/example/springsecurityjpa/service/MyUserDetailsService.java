package com.example.springsecurityjpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springsecurityjpa.model.MyUserDetails;
import com.example.springsecurityjpa.model.Usuario;
import com.example.springsecurityjpa.repository.UsuarioRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
		usuario.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos!"));
		return usuario.map(MyUserDetails::new).get();
	}

	public String obterLoginUsuarioLogado() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		} else {
			return principal.toString();
		}
	}

	public Usuario obterUsuarioLogado() {
		Optional<Usuario> usuario = usuarioRepository.findByLogin(obterLoginUsuarioLogado());
		if (usuario.isPresent()) {
			return usuario.get();
		}
		return null;
	}
}
