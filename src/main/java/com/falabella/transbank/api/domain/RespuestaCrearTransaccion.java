package com.falabella.transbank.api.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaCrearTransaccion extends Response{
    private String token;
}
