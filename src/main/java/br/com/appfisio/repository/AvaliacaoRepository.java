package br.com.appfisio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appfisio.entity.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

	List<Avaliacao> findAllByStatusTrueOrderByDataAtualizacaoDesc();

	List<Avaliacao> findAllByStatusTrueAndPaciente_IdEqualsOrderByDataAtualizacaoDesc(Long id);

}
