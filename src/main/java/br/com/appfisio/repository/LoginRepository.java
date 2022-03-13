package br.com.appfisio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appfisio.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

	Login findFirstByUsuarioEqualsAndSenhaEquals(String usuario, String senha);

}
