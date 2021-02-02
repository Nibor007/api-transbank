package com.falabella.transbank.api.services.impl;

import cl.transbank.transaccioncompleta.FullTransaction;
import cl.transbank.transaccioncompleta.model.FullTransactionCreateResponse;
import cl.transbank.webpay.exception.TransactionCreateException;
import com.falabella.transbank.api.domain.RespuestaCrearTransaccion;
import com.falabella.transbank.api.domain.Transaccion;
import com.falabella.transbank.api.services.TransaccionServiceImpl;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;


@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class TransaccionServiceImplTest{

    @InjectMocks
    TransaccionServiceImpl service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCrearSolicitudCarga_OK(){

        //FullTransactionCreateResponse fullTransactionCreateResponse = new FullTransactionCreateResponse();

        //mockStatic(FullTransaction.Transaction.class);

        /*when(FullTransaction.Transaction.create(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyDouble(), Mockito.anyString(), Mockito.anyString(), Mockito.anyShort())).
                thenReturn(fullTransactionCreateResponse);*/


        Transaccion transaccion = new Transaccion();
        //RespuestaCrearTransaccion response = service.crearTransaccion(transaccion);
    }

}