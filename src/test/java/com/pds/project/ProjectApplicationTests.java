package com.pds.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.pds.project.Implementation.CompradorServiceImpl;
import com.pds.project.Implementation.CompradorServiceImpl.ResultadoComprador;
import com.pds.project.Implementation.PagosServices.PagoServiceImpl;
import com.pds.project.Implementation.PagosServices.PagoServiceImpl.ResultadoPago;
import com.pds.project.Models.Comprador;
import com.pds.project.Models.ConcesionarioSingleton;
import com.pds.project.Models.Pago;
import com.pds.project.Models.MetodosPago.Contado;
import com.pds.project.Repository.ICompradorRepository;
import com.pds.project.Models.Concesionario;
import com.pds.project.ServiceInterface.ICompradorService;
import com.pds.project.Repository.IPagoRepository;
import com.pds.project.Models.MetodosPago.Tarjeta;
import com.pds.project.Implementation.PagosServices.TarjetaService;
import com.pds.project.Implementation.PagosServices.TarjetaService.ResultadoTarjeta;
import com.pds.project.Implementation.PagosServices.TransferenciaService;
import com.pds.project.Models.MetodosPago.Transferencia;
import com.pds.project.Implementation.PagosServices.TransferenciaService.ResultadoTransferencia;

@SpringBootTest
class ProjectApplicationTests {

	@Mock
	private ICompradorRepository compradorRepository;

	@InjectMocks
	private CompradorServiceImpl compradorService;

	@Test
	void contextLoads() {
	}

	@Test
	void testStringsIguales() {
		String a = "hola";
		String b = "hola";
		assertEquals(a, b);
	}

	@Test
	void testListaVacia() {
		List<String> lista = new ArrayList<>();
		assertTrue(lista.isEmpty());
	}

	@Test
	void testEliminarComprador() {
		Comprador comprador = new Comprador();
		comprador.setIdComprador(1L);

		when(compradorRepository.existsById(1L)).thenReturn(true);
		doNothing().when(compradorRepository).deleteById(1L);

		boolean result = compradorService.eliminarComprador(1L);
		assertTrue(result);
	}

	@Test
	void testEditarCompradorNoExiste() {
		when(compradorRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(NoSuchElementException.class, () -> compradorService.actualizarComprador(1L, new Comprador()));
	}

	@Test
	void testGuardarComprador() {
		Comprador comprador = new Comprador();
		comprador.setEmail("test@mail.com");

		when(compradorRepository.existsByEmail("test@mail.com")).thenReturn(false);
		when(compradorRepository.save(any(Comprador.class))).thenReturn(comprador);

		ResultadoComprador resultado = compradorService.guardarComprador(comprador);
		assertEquals(ResultadoComprador.OK, resultado);
	}

	@Test
	void testConcesionarioSingleton() {
		Concesionario instance1 = ConcesionarioSingleton.getConcesionario();
		Concesionario instance2 = ConcesionarioSingleton.getConcesionario();
		assertSame(instance1, instance2);
	}

	@Test
	void testCompradorSettersAndGetters() {
		Comprador comprador = new Comprador();
		comprador.setNombreApellido("Juan Pérez");
		assertEquals("Juan Pérez", comprador.getNombreApellido());
	}

	@Test
	void testGuardarCompradorExitoso() {
		Comprador comprador = new Comprador(null, "Juan Pérez", "12345678", "Calle Falsa 123", "123456789",
				"juan@mail.com", "20304050607", "clave123");

		when(compradorRepository.existsByEmail("juan@mail.com")).thenReturn(false);
		when(compradorRepository.existsByDocumento("12345678")).thenReturn(false);
		when(compradorRepository.save(any(Comprador.class))).thenReturn(comprador);

		ResultadoComprador status = compradorService.guardarComprador(comprador);

		assertEquals(ResultadoComprador.OK, status);
	}

	@Test
	void testGuardarCompradorDuplicadoEmail() {
		Comprador comprador = new Comprador(null, "Juan Pérez", "12345678", "Calle Falsa 123", "123456789",
				"juan@mail.com", "20304050607", "clave123");

		when(compradorRepository.existsByEmail("juan@mail.com")).thenReturn(true);

		ResultadoComprador status = compradorService.guardarComprador(comprador);

		assertEquals(ResultadoComprador.EMAIL_DUPLICADO, status);
	}

	@Test
	void testGuardarCompradorConNombreVacio() {
		Comprador comprador = new Comprador(null, "", "12345678", "Calle Falsa 123", "123456789", "juan@mail.com",
				"20304050607", "clave123");

		when(compradorRepository.existsByEmail("juan@mail.com")).thenReturn(false);
		when(compradorRepository.existsByDocumento("12345678")).thenReturn(false);
		when(compradorRepository.save(any(Comprador.class))).thenReturn(comprador);

		ResultadoComprador status = compradorService.guardarComprador(comprador);

		assertEquals(ResultadoComprador.OK, status);
	}

	@Test
	void testGetAllCompradoresListaVacia() {
		when(compradorRepository.findAll()).thenReturn(Collections.emptyList());

		List<Comprador> compradores = compradorService.getCompradores();

		assertNotNull(compradores);
		assertTrue(compradores.isEmpty());
	}

	@Test
	void testEliminarCompradorNoExiste() {
		when(compradorRepository.existsById(99L)).thenReturn(false);

		boolean result = compradorService.eliminarComprador(99L);

		assertFalse(result);
	}

	@Test
	void testCrearPago() {
		Contado contado = new Contado(0, null, null);
		contado.setMonto(5000);

		assertEquals(5000, contado.getMonto());
	}

	@Mock
	private IPagoRepository pagoRepository;

	@InjectMocks
	private TarjetaService tarjetaService;

	@Test
	void testGuardarPago() {
		Tarjeta tarjeta = new Tarjeta(1546, null, null);
		tarjeta.setMonto(10000);

		when(pagoRepository.save(tarjeta)).thenReturn(tarjeta);

		ResultadoTarjeta resultado = tarjetaService.guardarTarjeta(tarjeta);

		assertNotNull(resultado);
		verify(pagoRepository, times(1)).save(tarjeta);
	}

}
