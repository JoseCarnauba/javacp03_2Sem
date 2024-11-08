package br.com.fiap.cp03.service;

import br.com.fiap.cp03.model.Curso;
import br.com.fiap.cp03.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    // Método para listar todos os cursos
    public List<Curso> listarTodos() {
        return cursoRepository.findAll(); // Retorna todos os cursos do banco
    }

    // Método para salvar um curso (criar ou atualizar)
    public void salvar(Curso curso) {
        cursoRepository.save(curso); // Salva ou atualiza o curso no banco
    }

    // Método para buscar um curso pelo ID
    public Curso buscarPorId(Long id) {
        Optional<Curso> curso = cursoRepository.findById(id); // Busca o curso pelo ID
        return curso.orElse(null); // Retorna o curso se encontrado ou null caso contrário
    }

    // Método para deletar um curso pelo ID
    public void deletar(Long id) {
        cursoRepository.deleteById(id); // Deleta o curso com o ID fornecido
    }
}
