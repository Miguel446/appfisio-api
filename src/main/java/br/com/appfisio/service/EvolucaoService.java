package br.com.appfisio.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.appfisio.entity.Evolucao;
import br.com.appfisio.repository.EvolucaoRepository;

@Service
public class EvolucaoService {

	@Autowired
	private EvolucaoRepository repo;

	public List<Evolucao> listarTodos() {
		return repo.findAllByStatusTrueOrderByDataAtualizacaoDesc();
	}

	public List<Evolucao> listarPorPacienteId(Long id) {
		return repo.findAllByStatusTrueAndPaciente_IdEqualsOrderByDataAtualizacaoDesc(id);
	}

	public void salvar(Evolucao avaliacao) {
		repo.save(avaliacao);
	}

	public Evolucao buscar(Long id) {
		return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Evolução não encontrada"));
	}

	public void excluir(Long id) {
		Evolucao a = buscar(id);
		a.setStatus(false);
		salvar(a);
	}

}
