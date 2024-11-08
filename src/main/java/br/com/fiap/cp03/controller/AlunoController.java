package br.com.fiap.cp03.controller;

import br.com.fiap.cp03.model.Aluno;
import br.com.fiap.cp03.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    // Endpoint para salvar um novo aluno
    //Criar: POST /alunos
    @PostMapping ("/alunos")
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno salvarAluno(@RequestParam String nome, @RequestParam String email, @RequestParam Set<Long> cursosId) {
        return alunoService.salvarAluno(nome, email, cursosId);
    }

    // Endpoint para atualizar os dados de um aluno
    @PutMapping("/{alunoId}")
    public Aluno atualizarAluno(@PathVariable Long alunoId,
                                @RequestParam(required = false) String nome,
                                @RequestParam(required = false) String email,
                                @RequestParam Set<Long> cursosId) {
        return alunoService.atualizarAluno(alunoId, nome, email, cursosId);
    }

    // Endpoint para excluir um aluno
    @DeleteMapping("/{alunoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAluno(@PathVariable Long alunoId) {
        alunoService.deletarAluno(alunoId);
    }

    // Endpoint para buscar um aluno por ID
    @GetMapping("/{alunoId}")
    public Aluno buscarAlunoPorId(@PathVariable Long alunoId) {
        return alunoService.buscarAlunoPorId(alunoId);
    }

    // Endpoint para listar todos os alunos
    @GetMapping
    public Set<Aluno> listarAlunos() {
        return alunoService.listarTodosAlunos();
    }
}
