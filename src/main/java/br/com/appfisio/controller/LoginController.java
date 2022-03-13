package br.com.appfisio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.appfisio.entity.Login;
import br.com.appfisio.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService service;

	@ApiOperation(httpMethod = "POST", value = "Valida um usuario e senha")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Usuario autenticado com sucesso"),
			@ApiResponse(code = 400, message = "Usuario ou senha nulos"),
			@ApiResponse(code = 401, message = "Usuário ou Senha invalidos"),
			@ApiResponse(code = 500, message = "Erro ao realizar login") })
	@PostMapping
	public ResponseEntity<Object> logar(@RequestBody Login login) {
		if (login.getUsuario() == null) {
			return ResponseEntity.status(400).body("Usuario não pode ser nulo");
		}
		if (login.getSenha() == null) {
			return ResponseEntity.status(400).body("Senha não pode ser nula");
		}

		try {
			if (!service.logar(login).isPresent()) {
				return ResponseEntity.status(401).body("Usuário/Senha invalidos");
			}

			return ResponseEntity.ok("Usuário autenticado com sucesso!");
		} catch (Exception e) {
			return ResponseEntity.status(400).body("Erro ao autorizar usuário");
		}

	}

}
