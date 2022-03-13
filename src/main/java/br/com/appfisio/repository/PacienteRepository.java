package br.com.appfisio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appfisio.entity.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	List<Paciente> findByOrderByNomeAsc();

	List<Paciente> findAllByStatusTrueOrderByNomeAsc();

	List<Paciente> findAllByStatusFalseOrderByNomeAsc();

}
