package com.unicomer.example.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.unicomer.example.model.Client;
import com.unicomer.example.service.DemoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/REST/1.0/clients")
@Api(value = "Controlador de Clientes")
@RestController
public class DemoController {

	private static final String MSG_GET_CLIENTS_API_OP = "Obtendra el listado de los clientes";
	private static final String MSG_GET_CLIENTS_NOTE = "Este método buscará todos los clientes";
	private static final String MSG_INGRESO_CLIENTE_NOTE = "Este método persistira el registro de cliente";
	private static final String MSG_INGRESO_CLIENTE_API_OP = "Persiste un registro de cliente";
	private static final String MSG_ACTUALIZA_RESULTADO_API_OP = "Actualiza un registro de un cliente";
	private static final String MSG_ACTUALIZA_RESULTADO_NOTE = "Este método actualiza el registro de un cliente previamente ingresado";

	private static Logger logger = LogManager.getLogger(DemoController.class);

	@Autowired
	private DemoService demoService;

	List<Client> clients = new ArrayList<>();

	{
		clients.add(new Client(1, "Francisco", "Graf", new Date(), "", 123, 123, "Direccion 123", "Arquitecto", 150));
		clients.add(new Client(2, "User2", "User2", new Date(), "", 123, 123, "Direccion 123", "Ingeniero", 300));
		clients.add(new Client(3, "User3", "User3", new Date(), "", 123, 123, "Direccion 123", "Doctor", 100));
		clients.add(new Client(4, "User4", "User4", new Date(), "", 123, 123, "Direccion 123", "Profesor", 200));
	}

	@ApiOperation(value = MSG_GET_CLIENTS_API_OP, notes = MSG_GET_CLIENTS_NOTE)
	@GetMapping(value = "/getClients", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Client> getClients() {
		return clients;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE }, path = { "/" })
	@ApiOperation(value = MSG_INGRESO_CLIENTE_API_OP, notes = MSG_INGRESO_CLIENTE_NOTE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Client> ingresoCliente(@Valid @RequestBody Client cliente) {

		try {
			Client resultadoEntity = this.demoService.save(cliente);
			return new ResponseEntity<>(resultadoEntity, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE }, path = { "/id/{idCliente}" })
	@ApiOperation(value = MSG_ACTUALIZA_RESULTADO_API_OP, notes = MSG_ACTUALIZA_RESULTADO_NOTE)
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Client> actualizaResultado(@PathVariable("idCliente") int idCliente,
			@Valid @RequestBody Client cliente) {

		logger.info(cliente);
		try {
			Client resultadoEntity = this.demoService.update(cliente, idCliente);
			return new ResponseEntity<>(resultadoEntity, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
