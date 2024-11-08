package br.com.fiap.cp03.controller;

import br.com.fiap.cp03.model.Matricula;
import br.com.fiap.cp03.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    // Endpoint para listar todas as matrículas
    @GetMapping
    public String listarMatriculas(Model model) {
        model.addAttribute("matriculas", matriculaService.listarTodas());
        return "admin/matriculas/lista"; // Retorna a página com a lista de matrículas
    }

    // Endpoint para exibir o formulário para criar uma nova matrícula
    @GetMapping("/novo")
    public String novoMatriculaForm(Model model) {
        model.addAttribute("matricula", new Matricula());
        return "admin/matriculas/form"; // Retorna o formulário de criação de matrícula
    }

    // Endpoint para salvar uma nova matrícula
    @PostMapping
    public String salvarMatricula(@ModelAttribute Matricula matricula) {
        matriculaService.salvar(matricula);
        return "redirect:/admin/matriculas"; // Redireciona para a lista de matrículas após salvar
    }

    // Endpoint para editar uma matrícula existente
    @GetMapping("/editar/{id}")
    public String editarMatriculaForm(@PathVariable Long id, Model model) {
        Matricula matricula = matriculaService.buscarPorId(id);
        model.addAttribute("matricula", matricula);
        return "admin/matriculas/form"; // Retorna o formulário de edição com os dados da matrícula
    }

    // Endpoint para excluir uma matrícula
    @DeleteMapping("/{id}")
    public String deletarMatricula(@PathVariable Long id) {
        matriculaService.deletar(id);
        return "redirect:/admin/matriculas"; // Redireciona para a lista de matrículas após excluir
    }
}
