package br.com.appfisio.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.appfisio.entity.Login;
import br.com.appfisio.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository repo;

	public Optional<Login> logar(Login login) {

		return Optional.ofNullable(repo.findFirstByUsuarioEqualsAndSenhaEquals(login.getUsuario(), login.getSenha()));
	}

}
