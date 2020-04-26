package com.example.springsecurityjpa.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import com.example.springsecurityjpa.model.Pessoa;
import com.example.springsecurityjpa.repository.PessoaRepository;
import com.example.springsecurityjpa.util.CpfUtil;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

	public Pessoa findById(Integer id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		if (pessoa.isPresent()) { 
			return pessoa.get();
		}
		return null;
	}

	public void deleteById(Integer id) {
		pessoaRepository.deleteById(id);
	}

	public void save(Pessoa pessoa) {
		if (pessoa.getCpf() != null) {
			pessoa.setCpf(pessoa.getCpf().trim().replace(".", "").replace("-", ""));
		}
		if (pessoa.getId() == null) {
			pessoa.setCriadoEm(new Date());
			pessoa.setCriadoPor(myUserDetailsService.obterUsuarioLogado());
		} else {
			Pessoa original = findById(pessoa.getId());
			pessoa.setCriadoEm(original.getCriadoEm());
			pessoa.setCriadoPor(original.getCriadoPor());
			pessoa.setAlteradoEm(new Date());
			pessoa.setAlteradoPor(myUserDetailsService.obterUsuarioLogado());
		}
		pessoaRepository.save(pessoa);
	}

	public void validarCamposFormulario(Pessoa pessoa, BindingResult result) {
        if (StringUtils.isEmpty(pessoa.getNome())) {
            result.rejectValue("nome", "mensagem.campo.obrigatorio");
        }
        if (pessoa.getDataNascimento() == null) {
            result.rejectValue("dataNascimento", "mensagem.campo.obrigatorio");
        }
        if (StringUtils.isEmpty(pessoa.getCpf())) {
            result.rejectValue("cpf", "mensagem.campo.obrigatorio");
        } else {
        	if (!CpfUtil.isValidCPF(pessoa.getCpf())) {
        		result.rejectValue("cpf", "mensagem.cpf.invalido");
        	}
        }
    }
}
