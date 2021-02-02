package com.falabella.transbank.api.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaTransaccion extends Response{
    private Double monto;
    private String status;
    private String ordenCompra;
    private String idSession;
    private String numeroTarjeta;
    private String fechaContable;
    private String fechaTransaccion;
    private String codigoAutorizacion;
    private String codigoTipoPago;
    private Integer numeroCuotas;
}
