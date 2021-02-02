package com.falabella.transbank.api.services;

import com.falabella.transbank.api.domain.*;

public interface ITransaccionService {

	RespuestaCrearTransaccion crearTransaccion(Transaccion transaccion);
	
	RespuestaTransaccion confirmarTransaccion(DataConfirmacion dataConfirmacion);
	
	RespuestaCuota consultarCuota(ConsultaCuota consultaCuota);

	RespuestaTransaccion consultarEstado(String token);

	RespuestaReversa reversarTransaccion(Reversa reversa);
}
