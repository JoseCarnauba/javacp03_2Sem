package br.com.fiap.cp03.service;

import br.com.fiap.cp03.model.Matricula;
import br.com.fiap.cp03.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {
    @Autowired
    private MatriculaRepository matriculaRepository;

    // Listar todas as matrículas
    public List<Matricula> listarTodas() {
        return matriculaRepository.findAll(); // Retorna todas as matrículas
    }

    // Salvar ou atualizar uma matrícula
    public void salvar(Matricula matricula) {
        matriculaRepository.save(matricula); // Salva ou atualiza a matrícula
    }

    // Buscar uma matrícula por ID
    public Matricula buscarPorId(Long id) {
        Optional<Matricula> matricula = matriculaRepository.findById(id); // Procura a matrícula pelo ID
        return matricula.orElse(null); // Retorna a matrícula ou null se não encontrada
    }

    // Deletar uma matrícula por ID
    public void deletar(Long id) {
        matriculaRepository.deleteById(id); // Deleta a matrícula com o ID fornecido
    }
}
