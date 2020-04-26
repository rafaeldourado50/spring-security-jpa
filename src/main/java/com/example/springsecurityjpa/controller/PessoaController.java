package com.example.springsecurityjpa.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.springsecurityjpa.model.DominioSexo;
import com.example.springsecurityjpa.model.Pessoa;
import com.example.springsecurityjpa.service.PessoaService;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.initDirectFieldAccess();

	    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@GetMapping(value = "/pessoas")
	@PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView obterTodos() {
        ModelAndView mv = new ModelAndView("pessoas/pessoaList");
        mv.addObject("pessoas", pessoaService.findAll());
        return mv;
    }

    @GetMapping("/pessoas/create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView adicionar(Pessoa pessoa) {
        if (pessoa == null) {
            pessoa = new Pessoa();
        }
        ModelAndView mv = new ModelAndView("pessoas/pessoa");
        mv.addObject("pessoa", pessoa);
        mv.addObject("sexos", DominioSexo.values());
        return mv;
    }

    @GetMapping("/pessoas/{id}/update")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView editar(@PathVariable("id") Integer id) {
        return adicionar(pessoaService.findById(id));
    }

    @GetMapping("/pessoas/{id}/delete")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView deletar(@PathVariable("id") Integer id) {
    	pessoaService.deleteById(id);
        return obterTodos();
    }

    @PostMapping("/pessoas")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView salvar(@Valid Pessoa pessoa, BindingResult result) {
        pessoaService.validarCamposFormulario(pessoa, result);
        if (result.hasErrors()) {
            return adicionar(pessoa);
        }
        pessoaService.save(pessoa);
        return obterTodos();
    }
}
