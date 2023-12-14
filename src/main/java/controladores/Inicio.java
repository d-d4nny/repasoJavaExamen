package controladores;

import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import daos.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class Inicio {

	public static void main(String[] args) {

		//Pruebas del CRUD
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejercicioCRUD");

		//Acceso
		Acceso accesoCliente = new Acceso("Cliente", "Acceso cliente estandar de la biblioteca");
		Acceso accesoEmpleado = new Acceso("Empleado", "Empleados de la biblioteca");
		Acceso accesoDelete = new Acceso("PruebaDelete", "Acceso para probar el delete");		
		//Create Accesos
		Acceso.createAcceso(emf, accesoEmpleado); 
		Acceso.createAcceso(emf, accesoCliente);
		Acceso.createAcceso(emf, accesoDelete);	
		//Select Accesos
		Acceso accesoRecuperado = Acceso.selectAccesoId(emf, 1); 
		//Update Accesos
		accesoRecuperado.setDescripcionAcceso(JOptionPane.showInputDialog("Introduce la nueva descripcion del acceso"));
        Acceso.updateAcceso(emf, accesoRecuperado); 
        accesoRecuperado = Acceso.selectAccesoId(emf, 3);
        //Delete Accesos
        Acceso.deleteAcceso(emf, 3); 

        //Editorial
        Editorial primeraEditorial = new Editorial("Grupo comercial Anaya");
        //Create editorial
		Editorial.createEditorial(emf, primeraEditorial);
		Editorial.createEditorial(emf, new Editorial("Grupo comercial Planeta"));		
		//Select Editorial
		Editorial editorialRecuperada = Editorial.selectEditorialId(emf, 1); 		
		//Update Editorial
		editorialRecuperada.setNombreEditorial(JOptionPane.showInputDialog("Introduce el nuevo nombre de la editorial"));
		Editorial.updateEditorial(emf, editorialRecuperada);    
		//Delete Editorial
		Editorial.deleteEditorial(emf, 2);
		      
		//Genero
		Genero genero1 = new Genero("Terror y misterio",
				"Género literario que se define por la sensación de miedo que provoca en el lector");
		Genero genero2 = new Genero("Genero delete",
				"Género para borrar");
		//Create genero
		Genero.createGenero(emf, genero1);
		Genero.createGenero(emf, genero2);
		//Select genero
		Genero generoRecuperado = Genero.selectGeneroId(emf, 1);
		//Update genero
		generoRecuperado.setDescripcion_genero(JOptionPane.showInputDialog("Introduce nueva descripcion del genero"));
		Genero.updateGenero(emf, generoRecuperado);
		//Delete genero
		Genero.deleteGenero(emf, 2);
		
		//Coleccion
		Coleccion coleccionTerror = new Coleccion("Clasicos del terror mas vendidos");
		Coleccion coleccionborrar = new Coleccion("Coleccion de prueba");
		//Create coleccion
		Coleccion.createColeccion(emf, coleccionTerror);
		Coleccion.createColeccion(emf, coleccionborrar);
		//Select coleccion
		Coleccion coleccionRecuperada = Coleccion.selectColeccionId(emf, 1);
		//Update coleccion
		coleccionRecuperada.setNombreColeccion(JOptionPane.showInputDialog("Introduce el nuevo nombre de la coleccion"));
		Coleccion.updateColeccion(emf, coleccionRecuperada);
		//Delete coleccion
		Coleccion.deleteColeccion(emf, 2);

		//Autor
		Autor autor1 = new Autor("Bram", "Stoker");
		Autor autor2 = new Autor("Prueba", "Delete");
		//Create autor
		Autor.createAutor(emf, autor1);
		Autor.createAutor(emf, autor2);
		//Select autor
		Autor autorRecuperado = Autor.selectAutorId(emf, 1);
		//Update autor
		autorRecuperado.setNombreAutor(JOptionPane.showInputDialog("Introduce el nuevo nombre del autor"));
		Autor.updateAutor(emf, autorRecuperado);
		//Delete autor
		Autor.deleteAutor(emf, 2);
		
		//Libro
		Libro libro1 = new Libro("SW-0-7645-2641-3", "Drácula", "3522", primeraEditorial, genero1, coleccionTerror);
		Libro libro2 = new Libro("22222", "Libro delete", "3522", primeraEditorial, genero1, coleccionTerror);
		//Create libro
		libro1.getListaAutoresLibro().add(autor1);
		autor1.getListaLibrosAutor().add(libro1);
		Libro.createLibro(emf, libro1);
		Libro.createLibro(emf, libro2);
		//Select libro
		Libro libroRecuperado = Libro.selectLibroId(emf, 1);
		//Update libro
		libroRecuperado.setTituloLibro(JOptionPane.showInputDialog("Introduce el nuevo titulo del libro"));
		Libro.updateLibro(emf, libroRecuperado);
		//Delete libro
		Libro.deleteLibro(emf, 2);
		
		//Usuario
		Usuario usuarioCliente = new Usuario("11223344W", "Leo", "Messi", "23441231", "nomail@no.com", "1234", false,
				Calendar.getInstance(), Calendar.getInstance(), Calendar.getInstance(), accesoCliente);
		Usuario usuarioBorrar = new Usuario("22334455K", "Cristiano", "Ronaldo", "612312122", "nomail@no.com", "1234",
				false, Calendar.getInstance(), Calendar.getInstance(), Calendar.getInstance(), accesoEmpleado);
		//Create usuario
		Usuario.createUsuario(emf, usuarioCliente);
		Usuario.createUsuario(emf, usuarioBorrar);
		//Select usuario
		Usuario usuarioRecuperado = Usuario.selectUsuarioId(emf, 1);
		//Update usuario
		usuarioRecuperado.setNombreUsuario(JOptionPane.showInputDialog("Introduce el nuevo nombre del usuario"));
		Usuario.updateUsuario(emf, usuarioRecuperado);
		//Delete usuario
		Usuario.deleteUsuario(emf, 2);

		//EstadoPrestamo
		EstadoPrestamo estadoOk = new EstadoPrestamo("Devuelto", "Prestamo devuelto correctamente en tiempo y fecha");
		EstadoPrestamo estadoBorrar = new EstadoPrestamo("No devuelto",
				"Prestamo no devuelto cumpliendo la fecha límite");
		//Create estado
		EstadoPrestamo.createEstadoPrestamo(emf, estadoOk);
		EstadoPrestamo.createEstadoPrestamo(emf, estadoBorrar);
		//Select estado
		EstadoPrestamo estadoRecuperado = EstadoPrestamo.selectEstadoPrestamoId(emf, 1);
		//Update estado
		estadoRecuperado.setDescripcionEstadoPrestamo(JOptionPane.showInputDialog("Introduce la nueva descripcion del estado"));
		EstadoPrestamo.updateEstadoPrestamo(emf, estadoRecuperado);
		//Delete estado
		EstadoPrestamo.deleteEstadoPrestamo(emf, 2);
		
		//Prestamo		
	    Prestamo prestamo1 = new Prestamo(Calendar.getInstance(), Calendar.getInstance(), Calendar.getInstance(),
				usuarioCliente, estadoOk);
	    Prestamo prestamoBorrar = new Prestamo(Calendar.getInstance(), Calendar.getInstance(), Calendar.getInstance(),
				usuarioCliente, estadoOk);
	    prestamo1.getListalibros().add(libro1);
	    libro1.getListaPrestamos().add(prestamo1);
	    Libro.createLibro(emf, libro1);
	    //Create prestamo
	    Prestamo.createPrestamo(emf, prestamo1);
	    Prestamo.createPrestamo(emf, prestamoBorrar);
	    //Select prestamo
	    Prestamo prestamoRecuperado = Prestamo.selectPrestamoId(emf, 1);
	    //Update prestamo
	    prestamoRecuperado.setFechaFinPrestamo(Calendar.getInstance());
	    Prestamo.updatePrestamo(emf, prestamoRecuperado);
	    //Delete prestamo
	    Prestamo.deletePrestamo(emf, 2);
	    
	    
	    Usuario.mostrarTodos(emf, usuarioRecuperado);
	}

}
