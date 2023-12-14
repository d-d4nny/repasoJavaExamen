package daos;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "generos", schema = "gbp_operacional")
public class Genero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_genero", nullable = false)
	private long id_genero;

	@Column(name = "nombre_genero", nullable = false)
	private String nombre_genero;

	@Column(name = "descripcion_genero")
	private String descripcion_genero;

	@OneToMany(mappedBy = "genero")
	private List<Libro> listaLibrosGenero;

	public Genero() {

	}

	public Genero(String nombre_genero, String descripcion_genero) {
		super();
		this.nombre_genero = nombre_genero;
		this.descripcion_genero = descripcion_genero;
	}

	public long getId_genero() {
		return id_genero;
	}

	public String getNombre_genero() {
		return nombre_genero;
	}

	public String getDescripcion_genero() {
		return descripcion_genero;
	}

	public List<Libro> getListaLibrosGenero() {
		return listaLibrosGenero;
	}
	
	public void setId_genero(long id_genero) {
		this.id_genero = id_genero;
	}

	public void setNombre_genero(String nombre_genero) {
		this.nombre_genero = nombre_genero;
	}

	public void setDescripcion_genero(String descripcion_genero) {
		this.descripcion_genero = descripcion_genero;
	}

	public void setListaLibrosGenero(List<Libro> listaLibrosGenero) {
		this.listaLibrosGenero = listaLibrosGenero;
	}

	// Metodos para el crud
	public static void createGenero(EntityManagerFactory emf, Genero genero) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(genero);
		em.getTransaction().commit();
		em.close();
	}

	public static Genero selectGeneroId(EntityManagerFactory emf, long id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Genero.class, id);
	}

	public static void updateGenero(EntityManagerFactory emf, Genero genero) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(genero);
		em.getTransaction().commit();
		em.close();
	}

	public static void deleteGenero(EntityManagerFactory emf, long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Genero genero = em.find(Genero.class, id);
		if (genero != null) {
			em.remove(genero);
		}
		em.getTransaction().commit();
		em.close();
	}

	
}
