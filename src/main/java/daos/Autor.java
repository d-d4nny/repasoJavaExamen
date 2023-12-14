package daos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "autores", schema = "gbp_operacional")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_autor", nullable = false)
	private long id_autor;

	@Column(name = "nombre_autor", nullable = false)
	private String nombreAutor;

	@Column(name = "apellidos_autor")
	private String apellidosAutor;

	@ManyToMany(mappedBy = "listaAutoresLibro")
	private List<Libro> listaLibrosAutor;

	public Autor() {

	}

	public Autor(String nombreAutor, String apellidosAutor) {
		super();
		this.nombreAutor = nombreAutor;
		this.apellidosAutor = apellidosAutor;
		this.listaLibrosAutor = new ArrayList<>();
	}

	public long getId_autor() {
		return id_autor;
	}

	public String getNombreAutor() {
		return nombreAutor;
	}

	public String getApellidosAutor() {
		return apellidosAutor;
	}

	public List<Libro> getListaLibrosAutor() {
		return listaLibrosAutor;
	}

	public void setId_autor(long id_autor) {
		this.id_autor = id_autor;
	}

	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	public void setApellidosAutor(String apellidosAutor) {
		this.apellidosAutor = apellidosAutor;
	}

	public void setListaLibrosAutor(List<Libro> listaLibrosAutor) {
		this.listaLibrosAutor = listaLibrosAutor;
	}

	// Metodos para el crud
	public static void createAutor(EntityManagerFactory emf, Autor autor) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(autor);
		em.getTransaction().commit();
		em.close();
	}

	public static Autor selectAutorId(EntityManagerFactory emf, long id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Autor.class, id);
	}

	public static void updateAutor(EntityManagerFactory emf, Autor autor) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(autor);
		em.getTransaction().commit();
		em.close();
	}

	public static void deleteAutor(EntityManagerFactory emf, long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Autor autor = em.find(Autor.class, id);
		if (autor != null) {
			em.remove(autor);
		}
		em.getTransaction().commit();
		em.close();
	}

}
