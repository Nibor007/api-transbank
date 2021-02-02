package com.falabella.transbank.api.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RespuestaCuota extends Response{
	
	private Double montoCuota;
	private Long identificadorCuota;
	private List periodosDiferidos;
}
