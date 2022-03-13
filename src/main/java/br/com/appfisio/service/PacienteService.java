package br.com.appfisio.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.appfisio.entity.Paciente;
import br.com.appfisio.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repo;

	public List<Paciente> listar() {
		return repo.findByOrderByNomeAsc();
	}

	public List<Paciente> listarAtivos() {
		return repo.findAllByStatusTrueOrderByNomeAsc();
	}

	public List<Paciente> listarInativos() {
		return repo.findAllByStatusFalseOrderByNomeAsc();
	}

	public Paciente salvar(Paciente paciente) {
		return repo.save(paciente);
	}

	public Paciente buscar(Long id) {
		return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Paciente não encontrado"));
	}

	public void desativar(Long id) {
		Paciente p = buscar(id);
		p.setStatus(false);
		salvar(p);
	}

}
