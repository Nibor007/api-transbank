package com.falabella.transbank.api.services;

import cl.transbank.transaccioncompleta.FullTransaction;
import cl.transbank.transaccioncompleta.model.*;
import cl.transbank.webpay.exception.TransactionCreateException;

import com.falabella.transbank.api.domain.*;
import com.falabella.transbank.api.exception.CrearTransactionException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import cl.transbank.transaccioncompleta.FullTransaction;
import cl.transbank.transaccioncompleta.FullTransaction;
import cl.transbank.transaccioncompleta.FullTransaction;

@Service
public class TransaccionServiceImpl implements ITransaccionService {

	@Override
	public RespuestaCrearTransaccion crearTransaccion(Transaccion transaccion) {

		RespuestaCrearTransaccion respuestaCrearTransaccion = new RespuestaCrearTransaccion();

		try{
			final FullTransactionCreateResponse response =  FullTransaction.Transaction.create(
					transaccion.getOrdenCompra(),                         // ordenCompra12345678
					transaccion.getIdSession(),                        // sesion1234564
					transaccion.getMonto(),                           // 10000
					transaccion.getNumeroTarjeta(),
					transaccion.getFechaExpiracionTarjeta(),
					transaccion.getCvv()    // 123
			);
			respuestaCrearTransaccion.setCodigo("00");
			respuestaCrearTransaccion.setMensaje("OK");
			respuestaCrearTransaccion.setToken(response.getToken());
			return respuestaCrearTransaccion;
		}catch(Exception e){
			throw new CrearTransactionException("Error al crear la transsaccion", e);
		}
	}
	
	@Override
	public RespuestaTransaccion confirmarTransaccion(DataConfirmacion dataConfirmacion) {

		RespuestaTransaccion respuestaTransaccion = new RespuestaTransaccion();

		try{

			final FullTransactionCommitResponse response = FullTransaction.Transaction.commit(
					dataConfirmacion.getToken(), dataConfirmacion.getIdentificadorCuota(), null,
					dataConfirmacion.getPeriodoGracia()
			);
			respuestaTransaccion.setCodigoAutorizacion(response.getAuthorizationCode());
			//TODO devuelve 0, ver si este codigo es el correcto
			respuestaTransaccion.setCodigo(String.valueOf(response.getResponseCode()));
			respuestaTransaccion.setCodigoTipoPago(response.getPaymentTypeCode());
			respuestaTransaccion.setFechaContable(response.getAccountingDate());
			respuestaTransaccion.setIdSession(response.getSessionId());
			respuestaTransaccion.setMonto(response.getAmount());
			respuestaTransaccion.setNumeroCuotas(Byte.toUnsignedInt(response.getInstallmentsNumber()));
			//TODO no devuelve la tarjeta
			respuestaTransaccion.setNumeroTarjeta(response.getCardNumber());
			respuestaTransaccion.setOrdenCompra(response.getBuyOrder());
			respuestaTransaccion.setStatus(response.getStatus());
			respuestaTransaccion.setFechaTransaccion(response.getTransactionDate());
			//respuestaTransaccion.setCodigo("00");
			respuestaTransaccion.setMensaje("OK");

			return respuestaTransaccion;
		}catch(Exception e){
			throw new CrearTransactionException("Error al confirmar la transsaccion", e);
		}
	}

	@Override
	public RespuestaCuota consultarCuota(ConsultaCuota consultaCuota) {
		RespuestaCuota respuestaCuota = new RespuestaCuota();

		try{
			byte data = (byte) consultaCuota.getCantidadCuotas().intValue();
			final FullTransactionInstallmentResponse response =  FullTransaction.Transaction.installments(
					consultaCuota.getToken(), data);

			respuestaCuota.setCodigo("00");
			respuestaCuota.setMensaje("OK");
			respuestaCuota.setMontoCuota(response.getInstallmentsAmount());
			respuestaCuota.setIdentificadorCuota(response.getIdQueryInstallments());
			respuestaCuota.setPeriodosDiferidos(response.getDeferredPeriods().subList(0, response.getDeferredPeriods().size()));
			return respuestaCuota;
		}catch(Exception e){
			throw new CrearTransactionException("Error al consulta la cuota", e);
		}
	}

	@Override
	public RespuestaTransaccion consultarEstado(String token) {
		
		RespuestaTransaccion respuestaTransaccion = new RespuestaTransaccion();
		
		try{
			
			final FullTransactionStatusResponse response = FullTransaction.Transaction.status(token);
			System.out.println("data: " + token);
			respuestaTransaccion.setCodigoAutorizacion(response.getAuthorizationCode());
			//TODO devuelve 0, ver si este codigo es el correcto
			respuestaTransaccion.setCodigo(String.valueOf(response.getResponseCode()));
			respuestaTransaccion.setCodigoTipoPago(response.getPaymentTypeCode());
			respuestaTransaccion.setFechaContable(response.getAccountingDate());
			respuestaTransaccion.setIdSession(response.getSessionId());
			respuestaTransaccion.setMonto(response.getAmount());
			respuestaTransaccion.setNumeroCuotas(Byte.toUnsignedInt(response.getInstallmentsNumber()));
			//TODO no devuelve la tarjeta
			respuestaTransaccion.setNumeroTarjeta(response.getCardNumber());
			respuestaTransaccion.setOrdenCompra(response.getBuyOrder());
			respuestaTransaccion.setStatus(response.getStatus());
			respuestaTransaccion.setFechaTransaccion(response.getTransactionDate());
			//respuestaTransaccion.setCodigo("00");
			respuestaTransaccion.setMensaje("OK");

			return respuestaTransaccion;
			
		}catch(Exception e){
			throw new CrearTransactionException("Error al consulta el estado", e);
		}
	}

	@Override
	public RespuestaReversa reversarTransaccion(Reversa reversa) {

		RespuestaReversa respuestaReversa = new RespuestaReversa();

		try{

			final FullTransactionRefundResponse response = FullTransaction.Transaction.refund(
					reversa.getToken(), reversa.getMonto());

			respuestaReversa.setCodigo("00");
			respuestaReversa.setMensaje("OK");
			respuestaReversa.setTipo(response.getType());

			return respuestaReversa;
		}catch(Exception e){
			throw new CrearTransactionException("Error al crear la transsaccion", e);
		}
	}
}