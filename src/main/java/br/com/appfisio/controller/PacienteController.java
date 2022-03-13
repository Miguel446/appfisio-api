package br.com.appfisio.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.appfisio.entity.Paciente;
import br.com.appfisio.service.PacienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

	@Autowired
	private PacienteService service;

	@ApiOperation(httpMethod = "POST", value = "Cria ou Atualiza um Paciente na base de dados. Para atualizar, basta adicionar o Id do paciente no Body da requisição")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna o Paciente salvo no banco de dados"),
			@ApiResponse(code = 500, message = "Retorna mensagem de erro ao salvar Paciente") })
	@PostMapping
	public ResponseEntity<Object> post(@RequestBody Paciente p) {
		try {
			Paciente paciente = service.salvar(p);
			return ResponseEntity.ok(paciente);
		} catch (RuntimeException e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@ApiOperation(httpMethod = "GET", value = "Consulta os Pacientes salvos na base de dados. Utilize o parametro tipo para escolher quais tipos de pacientes consultar \n"
			+ "Podem ser 'todos', 'ativos' ou 'inativos'. Ex: https://appfisio-api.herokuapp.com/api/pacientes?tipo=inativos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna lista de Pacientes conforme o tipo escolhido"),
			@ApiResponse(code = 400, message = "Retorna mensagem de erro ao passar um valor invalido no parametro tipo"),
			@ApiResponse(code = 500, message = "Retorna mensagem de erro ao consultar Pacientes") })
	@GetMapping
	public ResponseEntity<Object> listar(
			@RequestParam(name = "tipo", required = false, defaultValue = "todos") String tipo) {

		try {
			switch (tipo) {
			case "todos":
				return ResponseEntity.ok(service.listar());
			case "ativos":
				return ResponseEntity.ok(service.listarAtivos());
			case "inativos":
				return ResponseEntity.ok(service.listarInativos());
			default:
				return ResponseEntity.status(400)
						.body("Valor do parametro 'tipo' invalido. Utilize 'todos' ou 'ativos' ou 'inativos'.");
			}

		} catch (RuntimeException e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@ApiOperation(httpMethod = "GET", value = "Consulta um Paciente por id na base de dados, se um Paciente tiver um id 1, ele será consultado da seguinte forma\n"
			+ "Ex: https://appfisio-api.herokuapp.com/api/pacientes/1")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna Paciente com ID enviado na requisição"),
			@ApiResponse(code = 404, message = "Retorna mensagem de erro quando o paciente não for encontrado"),
			@ApiResponse(code = 500, message = "Retorna mensagem de erro ao buscar Paciente") })
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscar(@PathVariable Long id) {

		try {
			return ResponseEntity.ok(service.buscar(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(404).body(e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@ApiOperation(httpMethod = "DELETE", value = "Desativa um Paciente por id na base de dados, se um Paciente tiver um id 1, ele será desativado da seguinte forma\n"
			+ "Ex: https://appfisio-api.herokuapp.com/api/pacientes/1")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna mensagem informando que o Paciente foi desativado com sucesso"),
			@ApiResponse(code = 404, message = "Retorna mensagem de erro quando o paciente não for encontrado"),
			@ApiResponse(code = 500, message = "Retorna mensagem de erro ao desativar Paciente") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> desativar(@PathVariable Long id) {
		try {
			service.desativar(id);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(404).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
		return ResponseEntity.ok("Paciente desativado com sucesso");
	}

}
