package br.com.appfisio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appfisio.entity.Evolucao;

@Repository
public interface EvolucaoRepository extends JpaRepository<Evolucao, Long> {

	List<Evolucao> findAllByStatusTrueOrderByDataAtualizacaoDesc();

	List<Evolucao> findAllByStatusTrueAndPaciente_IdEqualsOrderByDataAtualizacaoDesc(Long id);

}