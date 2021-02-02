package com.falabella.transbank.api.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataConfirmacion{
	
	private String token;
	private Long identificadorCuota;
	private Integer cantidadPeriodo;
	private Boolean periodoGracia;
}
