package com.falabella.transbank.api.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaccion {

	private String ordenCompra;
	private String idSession;
	private Double monto;
	private Short cvv;
	private String numeroTarjeta;
	private String fechaExpiracionTarjeta;
	
}
