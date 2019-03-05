package com.isagiongo.escola.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.isagiongo.escola.models.Aluno;
import com.isagiongo.escola.models.Nota;
import com.isagiongo.escola.repositories.AlunoRepository;

@Controller
public class NotaController {

	@Autowired
	private AlunoRepository alunoRepository;

	@PostMapping("/nota/salvar/{id}")
	public String salvar(@PathVariable String id, @ModelAttribute Nota nota) {
		Aluno aluno = alunoRepository.obterAlunoPor(id);
		alunoRepository.salvar(aluno.adiciona(aluno, nota));
		return "redirect:/aluno/listar";
	}

	@GetMapping("/nota/pesquisar")
	public String pesquisarPor(@RequestParam("classificacao") String classificacao,
			@RequestParam("notacorte") String notaCorte, Model model) {
		List<Aluno> alunos = alunoRepository.pesquisarPor(classificacao, Double.parseDouble(notaCorte));
		model.addAttribute("alunos", alunos);
		return "nota/pesquisar";
	}
}
