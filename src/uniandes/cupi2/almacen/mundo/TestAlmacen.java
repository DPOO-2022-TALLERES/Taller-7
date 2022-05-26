package uniandes.cupi2.almacen.mundo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;


import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.*;

public class TestAlmacen {
	
	private Almacen almacen;
	
	
	
	@BeforeEach
	
	public void setUp() throws AlmacenException {
		this.almacen =  new Almacen(new File("data/datos.txt"));
		
		// Crea un producto de prueba para las excepciones
		
		this.almacen.agregarProducto("1111", "2215", "Pukini", "xd", 100);
		
		// Crea un nodo de prueba para las excepciones
		
		this.almacen.agregarNodo("111", "Marca", "1390", "Puki");

	
	}
	
	
	// Pruebas correctas
	
	@Test
	public void buscarNodos() {
		
		assertEquals("Hogar", this.almacen.buscarNodo("113").darNombre());
		assertEquals("Computadores", this.almacen.buscarNodo("112").darNombre());
		assertEquals("Ashley Homestore", this.almacen.buscarNodo("1131A2").darNombre());
	
	}
	
	
	@Test
	
	public void agregaryeliminarNodos() throws AlmacenException {
		
		this.almacen.agregarNodo("111", "Marca", "1322", "Puki");

	    assertEquals("Puki", this.almacen.buscarNodo("1322").darNombre());
	    
	    this.almacen.eliminarNodo("1322");
	    
		assertNull(this.almacen.buscarNodo("1322"));
		
	    
	}
	
	
	@Test
	
	public void agregaryeliminarProducto() throws AlmacenException {
		
		this.almacen.agregarProducto("1111", "2213", "Pukini", "xd", 100);
		
		assertNotNull(this.almacen.darCategoriaRaiz().buscarProducto("2213"));
		
		this.almacen.eliminarProducto("2213");
		
		assertNull(this.almacen.darCategoriaRaiz().buscarProducto("2213"));
		
		
	}
	
	@Test
	
	public void venderProducto() throws AlmacenException {
		
		this.almacen.agregarProducto("1111", "2214", "ASUS ROG 14", "xd", 100);
		
		this.almacen.venderProducto("2214", 13);
		
		assertEquals(13,this.almacen.darCategoriaRaiz().buscarProducto("2214").darCantidadUnidadesVendidas());
		
		
	}
	
	
	@Test
	
	public void valorVentas() {
		
		assertEquals(0, this.almacen.buscarNodo("112").darValorVentas(),0);
	}
	
	
	@Test
	public void darLista() {
		
		assertFalse(this.almacen.darCategoriaRaiz().darNodos().isEmpty());
	
		assertFalse(this.almacen.darCategoriaRaiz().darProductos().isEmpty());
		
		assertFalse(this.almacen.darCategoriaRaiz().darPosorden().isEmpty());
		
		assertFalse(this.almacen.darCategoriaRaiz().darPreorden().isEmpty());
	}
	
	
	
	
	// Excepciones del Almacen
	
	/* 
	 * Si se elimina la raiz, lanzara excepcion
	 */
	
	@Test
	
	public void eliminarLaRaizLanzaExcepcion () {
		
		assertThrows(AlmacenException.class,() -> this.almacen.eliminarNodo("1"));
	}
	
	
	/*	
	 * Si se crea un nuevo producto que ya existe lanzara excepcion
	 */
	
	@Test
	public void agregarUnProductoQueYaExisteLanzaExcepcion() {
		
		assertThrows(AlmacenException.class,() -> this.almacen.agregarProducto("1111", "2215", "Pukini", "xd", 100));
	}
	
	
	/*	
	 * Si se crea un nuevo nodo con el identificador de un nodo existente lanzara excepcion
	 */
	
	@Test
	public void agregarUnNodoLanzaExcepcion() {
		
		assertThrows(AlmacenException.class,() -> this.almacen.agregarNodo("111", "Marca", "1390", "Puki"));
	}
	
	
	
	

}
