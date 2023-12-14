package daos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "libros", schema = "gbp_operacional")
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_libro", nullable = false)
	private long idLibro;

	@Column(name = "isbn_libro", nullable = false)
	private String isbnLibro;

	@Column(name = "titulo_libro", nullable = false)
	private String tituloLibro;

	@Column(name = "edicion_libro")
	private String edicionLibro;
	
	@Column(name = "cantidad_libro")
	private int cantidadLibro;

	@JoinTable(name = "Rel_Prestamos_Libros",
			joinColumns = @JoinColumn(name = "id_prestamo"),
			inverseJoinColumns = @JoinColumn(name = "id_libro"),
			schema = "gbp_operacional")
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Prestamo> listaPrestamos;

	@ManyToOne
	@JoinColumn(name = "id_editorial")
	Editorial editorial;

	@ManyToOne
	@JoinColumn(name = "id_genero")
	Genero genero;

	@ManyToOne
	@JoinColumn(name = "id_coleccion")
	Coleccion coleccion;

	@JoinTable(name = "Rel_Autores_Libros", // Indica el nombre de la tabla intermedia
			joinColumns = @JoinColumn(name = "id_autor"), // y nombre de los campos que tendran los FK que relacionan
			inverseJoinColumns = @JoinColumn(name = "id_libro"), 
			schema = "gbp_operacional")
	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Autor> listaAutoresLibro;
	

	public Libro() {

	}

	public Libro(String isbnLibro, String tituloLibro, String edicionLibro, Editorial editorial, Genero genero,
			Coleccion coleccion) {
		super();
		this.isbnLibro = isbnLibro;
		this.tituloLibro = tituloLibro;
		this.edicionLibro = edicionLibro;
		this.editorial = editorial;
		this.genero = genero;
		this.coleccion = coleccion;
		this.listaAutoresLibro = new ArrayList<>();
		this.listaPrestamos = new ArrayList<>();

	}

	public List<Autor> getListaAutoresLibro() {
		return listaAutoresLibro;
	}

	public long getIdLibro() {
		return idLibro;
	}

	public String getIsbnLibro() {
		return isbnLibro;
	}

	public String getTituloLibro() {
		return tituloLibro;
	}

	public String getEdicionLibro() {
		return edicionLibro;
	}

	public List<Prestamo> getListaPrestamos() {
		return listaPrestamos;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public Genero getGenero() {
		return genero;
	}

	public Coleccion getColeccion() {
		return coleccion;
	}
	
	public int getCantidadLibro() {
		return cantidadLibro;
	}
	
	
	public void setIdLibro(long idLibro) {
		this.idLibro = idLibro;
	}

	public void setIsbnLibro(String isbnLibro) {
		this.isbnLibro = isbnLibro;
	}

	public void setTituloLibro(String tituloLibro) {
		this.tituloLibro = tituloLibro;
	}

	public void setEdicionLibro(String edicionLibro) {
		this.edicionLibro = edicionLibro;
	}

	public void setCantidadLibro(int cantidadLibro) {
		this.cantidadLibro = cantidadLibro;
	}

	public void setListaPrestamos(List<Prestamo> listaPrestamos) {
		this.listaPrestamos = listaPrestamos;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public void setColeccion(Coleccion coleccion) {
		this.coleccion = coleccion;
	}

	public void setListaAutoresLibro(List<Autor> listaAutoresLibro) {
		this.listaAutoresLibro = listaAutoresLibro;
	}

	//Metodos crud
	public static void createLibro(EntityManagerFactory emf, Libro libro) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(libro);
		em.getTransaction().commit();
		em.close();
	}

	public static Libro selectLibroId(EntityManagerFactory emf, long id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Libro.class, id);
	}

	public static void updateLibro(EntityManagerFactory emf, Libro libro) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(libro);
		em.getTransaction().commit();
		em.close();
	}

	public static void deleteLibro(EntityManagerFactory emf, long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Libro prestamo = em.find(Libro.class, id);
		if (prestamo != null) {
			em.remove(prestamo);
		}
		em.getTransaction().commit();
		em.close();
	}
	
	

}
