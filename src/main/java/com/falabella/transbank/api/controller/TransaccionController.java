package com.falabella.transbank.api.controller;

import com.falabella.transbank.api.domain.*;
import com.falabella.transbank.api.services.ITransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransaccionController {

	@Autowired
	private ITransaccionService transaccionService;

	@PostMapping("/transbank/transaccion/crear")
	public RespuestaCrearTransaccion crearTransaccion(@RequestBody Transaccion transaccion) {
		return transaccionService.crearTransaccion(transaccion);
	}

	@PostMapping("/transbank/transaccion/consultarcuotas")
	public RespuestaCuota consultarCuota(@RequestBody ConsultaCuota consultaCuota) {
		return transaccionService.consultarCuota(consultaCuota);
	}

	@PutMapping("/transbank/transaccion/confirmar") 
	public RespuestaTransaccion confirmarTransaccion(@RequestBody DataConfirmacion dataConfirmacion) {
		return transaccionService.confirmarTransaccion(dataConfirmacion);
	}

	@GetMapping("/transbank/transaccion/consultarestado/{token}") 
	public RespuestaTransaccion consultarEstado(@PathVariable("token") String token) {
		//System.out.println("TOKEN"+ token);
		return transaccionService.consultarEstado(token);
	}
	
	@PostMapping("/transbank/transaccion/reversarpago")
	public RespuestaReversa reversarTransaccion(@RequestBody Reversa reversa) {
		return transaccionService.reversarTransaccion(reversa);
	}
}