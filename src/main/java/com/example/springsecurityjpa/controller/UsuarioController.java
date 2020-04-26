package com.example.springsecurityjpa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.springsecurityjpa.model.DominioSimNao;
import com.example.springsecurityjpa.model.Usuario;
import com.example.springsecurityjpa.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping(value = "/usuarios")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView obterTodos() {
        ModelAndView mv = new ModelAndView("usuarios/usuarioList");
        mv.addObject("usuarios", usuarioService.findAll());
        return mv;
    }

    @GetMapping("/usuarios/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView adicionar(Usuario usuario) {
        if (usuario == null) {
            usuario = new Usuario();
        }
        ModelAndView mv = new ModelAndView("usuarios/usuario");
        mv.addObject("usuario", usuario);
        mv.addObject("ativos", DominioSimNao.values());
        return mv;
    }

    @GetMapping("/usuarios/{id}/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView editar(@PathVariable("id") Integer id) {
        return adicionar(usuarioService.findById(id));
    }

    @GetMapping("/usuarios/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView deletar(@PathVariable("id") Integer id) {
    	usuarioService.deleteById(id);
        return obterTodos();
    }

    @PostMapping("/usuarios")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView salvar(@Valid Usuario usuario, BindingResult result) {
        validarCamposFormulario(usuario, result);
        if (result.hasErrors()) {
            return adicionar(usuario);
        }
        usuarioService.save(usuario);
        return obterTodos();
    }

    private void validarCamposFormulario(Usuario usuario, BindingResult result) {
        if (StringUtils.isEmpty(usuario.getLogin())) {
            result.rejectValue("login", "mensagem.campo.obrigatorio");
        }
        if (StringUtils.isEmpty(usuario.getSenha())) {
        	result.rejectValue("senha", "mensagem.campo.obrigatorio");
        }
        if (StringUtils.isEmpty(usuario.getRoles())) {
            result.rejectValue("roles", "mensagem.campo.obrigatorio");
        }
    }
}
