package br.com.appfisio.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.appfisio.entity.Avaliacao;
import br.com.appfisio.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

	@Autowired
	private AvaliacaoRepository repo;

	public List<Avaliacao> listarTodos() {
		return repo.findAllByStatusTrueOrderByDataAtualizacaoDesc();
	}

	public List<Avaliacao> listarPorPacienteId(Long id) {
		return repo.findAllByStatusTrueAndPaciente_IdEqualsOrderByDataAtualizacaoDesc(id);
	}

	public void salvar(Avaliacao avaliacao) {
		repo.save(avaliacao);
	}

	public Avaliacao buscar(Long id) {
		return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Avaliação não encontrada"));
	}

	public void excluir(Long id) {
		Avaliacao a = buscar(id);
		a.setStatus(false);
		salvar(a);
	}

}
