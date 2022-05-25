package uniandes.cupi2.almacen.mundo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.jupiter.api.*;

public class TestAlmacen {
	
	private Almacen almacen;
	private Categoria categoria;
	
	@BeforeEach
	
	public void setUp() throws AlmacenException {
		this.almacen =  new Almacen(new File("data/datos.txt"));
		
		
	}
	
	
	@Test
	public void buscarNodos() {
		
		assertEquals("Hogar", this.almacen.buscarNodo("113").darNombre());
		assertEquals("Computadores", this.almacen.buscarNodo("112").darNombre());
		assertEquals("Ashley Homestore", this.almacen.buscarNodo("1131A2").darNombre());
	
	}
	
	
	@Test
	
	public void agregarNodos() throws AlmacenException {
		
		this.almacen.agregarNodo("111", "Marca", "1322", "Puki");

	    assertEquals("Puki", this.almacen.buscarNodo("1322").darNombre());
	    
	}
	
	@Test
	
	public void eliminarNodos() throws AlmacenException {
		
		 this.almacen.eliminarNodo("1322");
		    
		 assertNull(this.almacen.buscarNodo("1322"));
		
	}
	

}
