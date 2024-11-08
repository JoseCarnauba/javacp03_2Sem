package br.com.fiap.cp03.controller;

import br.com.fiap.cp03.model.Curso;
import br.com.fiap.cp03.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // Listar todos os cursos
    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoService.listarTodos());
        return "admin/cursos/lista"; // A view "admin/cursos/lista" exibirá os cursos
    }

    // Formulário para criar um novo curso
    @GetMapping("/novo")
    public String novoCursoForm(Model model) {
        model.addAttribute("curso", new Curso()); // Atributo para o formulário
        return "admin/cursos/form"; // A view "admin/cursos/form" será o formulário de cadastro
    }

    // Salvar um novo curso
    @PostMapping
    public String salvarCurso(@Valid @ModelAttribute Curso curso, BindingResult result) {
        if (result.hasErrors()) { // Se houver erro de validação
            return "admin/cursos/form"; // Retorna para o formulário de cadastro com os erros
        }
        cursoService.salvar(curso); // Salva o curso usando o serviço
        return "redirect:/admin/cursos"; // Redireciona para a lista de cursos
    }

    // Formulário para editar um curso existente
    @GetMapping("/{id}/editar")
    public String editarCursoForm(@PathVariable Long id, Model model) {
        Curso curso = cursoService.buscarPorId(id); // Busca o curso pelo ID
        model.addAttribute("curso", curso); // Adiciona o curso ao modelo
        return "admin/cursos/form"; // Retorna para o formulário de edição
    }

    // Atualizar um curso existente
    @PostMapping("/{id}/editar")
    public String atualizarCurso(@PathVariable Long id, @Valid @ModelAttribute Curso curso, BindingResult result) {
        if (result.hasErrors()) { // Se houver erro de validação
            return "admin/cursos/form"; // Retorna para o formulário com os erros
        }
        curso.setId(id); // Define o ID do curso para garantir que ele seja atualizado
        cursoService.salvar(curso); // Atualiza o curso no banco
        return "redirect:/admin/cursos"; // Redireciona para a lista de cursos
    }

    // Excluir um curso
    @GetMapping("/{id}/excluir")
    public String excluirCurso(@PathVariable Long id) {
        cursoService.deletar(id); // Deleta o curso pelo ID
        return "redirect:/admin/cursos"; // Redireciona para a lista de cursos
    }
}
