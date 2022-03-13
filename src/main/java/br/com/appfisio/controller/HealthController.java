package br.com.appfisio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@RestController
@RequestMapping("/health")
public class HealthController {

	@ApiOperation(httpMethod = "GET", value = "Valida se a aplicação está online ou não")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Status UP - Aplicação está online") })
	@GetMapping
	public String health() {
		return "Status UP";
	}

}
