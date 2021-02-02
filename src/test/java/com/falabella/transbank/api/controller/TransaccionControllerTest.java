package com.falabella.transbank.api.controller;

import com.falabella.transbank.api.domain.*;
import com.falabella.transbank.api.services.ITransaccionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransaccionControllerTest {

    @Mock
    private ITransaccionService transaccionService;

    @InjectMocks
    private TransaccionController controller;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCrearTransaccion_Ok(){

        RespuestaCrearTransaccion respuestaCrearTransaccion = new RespuestaCrearTransaccion();
        respuestaCrearTransaccion.setToken("111111111111AAA0239839");
        respuestaCrearTransaccion.setCodigo("00");
        when(transaccionService.crearTransaccion( any(Transaccion.class))).thenReturn(respuestaCrearTransaccion);

        //El servicio que se va evaluar, no puede ser un parametro mock
        Transaccion transaccion = new Transaccion();
        RespuestaCrearTransaccion response = controller.crearTransaccion(transaccion);
        assertEquals(response.getCodigo(), respuestaCrearTransaccion.getCodigo());
        assertEquals(response.getToken(), respuestaCrearTransaccion.getToken());
    }

    @Test
    public void testConsultarCuotas(){
        RespuestaCuota respuestaCuota = new RespuestaCuota();
        respuestaCuota.setMontoCuota(Double.valueOf(10000));
        when(transaccionService.consultarCuota( any(ConsultaCuota.class))).thenReturn(respuestaCuota);

        ConsultaCuota consultaCuota = new ConsultaCuota(); //Mockito.any(ConsultaCuota.class); NO!!
        RespuestaCuota response = controller.consultarCuota(consultaCuota);
        assertEquals(response.getMontoCuota(), respuestaCuota.getMontoCuota());
    }

    @Test
    public void testConfirmarTransaccion(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RespuestaTransaccion respuestaTransaccion = new RespuestaTransaccion();
        respuestaTransaccion.setCodigo("00");
        respuestaTransaccion.setNumeroTarjeta("1234qwer12345678");
        when(transaccionService.confirmarTransaccion( any(DataConfirmacion.class))).thenReturn(respuestaTransaccion);

        DataConfirmacion dataConfirmacion = new DataConfirmacion();
        RespuestaTransaccion response = controller.confirmarTransaccion(dataConfirmacion);
        assertEquals(response.getCodigo(), respuestaTransaccion.getCodigo());
        assertEquals(response.getNumeroTarjeta(), respuestaTransaccion.getNumeroTarjeta());
    }

    @Test
    public void testConsultarEstado(){

        RespuestaTransaccion respuestaTransaccion = new RespuestaTransaccion();
        respuestaTransaccion.setCodigo("00");
        respuestaTransaccion.setNumeroTarjeta("1234qwer12345678");
        when(transaccionService.consultarEstado( anyString())).thenReturn(respuestaTransaccion);

        String token = "1213213456457457";
        RespuestaTransaccion response = controller.consultarEstado(token);
        assertEquals(response.getCodigo(), respuestaTransaccion.getCodigo());
        assertEquals(response.getNumeroTarjeta(), respuestaTransaccion.getNumeroTarjeta());
    }

    @Test
    public void testReversarTransaccion(){

        RespuestaReversa respuestaReversa = new RespuestaReversa();
        respuestaReversa.setCodigo("00");
        respuestaReversa.setTipo("ahasx");
        when(transaccionService.reversarTransaccion( any(Reversa.class))).thenReturn(respuestaReversa);

        Reversa reversa = new Reversa();
        RespuestaReversa response = controller.reversarTransaccion(reversa);
        assertEquals(response.getCodigo(), respuestaReversa.getCodigo());
        assertEquals(response.getTipo(), respuestaReversa.getTipo());
    }
}