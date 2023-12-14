package daos;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "editoriales", schema = "gbp_operacional")
public class Editorial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_editorial", nullable = false)
	private long idEditorial;

	@Column(name = "nombre_editorial", nullable = false)
	private String nombreEditorial;

	@OneToMany(mappedBy = "editorial")
	private List<Libro> listaLibrosEditorial;

	public Editorial() {

	}

	public Editorial(String nombreEditorial) {
		super();
		this.nombreEditorial = nombreEditorial;
	}

	public long getIdEditorial() {
		return idEditorial;
	}

	public String getNombreEditorial() {
		return nombreEditorial;
	}

	public List<Libro> getListaLibrosEditorial() {
		return listaLibrosEditorial;
	}

	public void setIdEditorial(long idEditorial) {
		this.idEditorial = idEditorial;
	}

	public void setNombreEditorial(String nombreEditorial) {
		this.nombreEditorial = nombreEditorial;
	}

	public void setListaLibrosEditorial(List<Libro> listaLibrosEditorial) {
		this.listaLibrosEditorial = listaLibrosEditorial;
	}

	// Metodos para el crud
	public static void createEditorial(EntityManagerFactory emf, Editorial editorial) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(editorial);
		em.getTransaction().commit();
	}

	public static Editorial selectEditorialId(EntityManagerFactory emf, long id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Editorial.class, id);
	}

	public static void updateEditorial(EntityManagerFactory emf, Editorial editorial) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(editorial);
		em.getTransaction().commit();
		em.close();
	}

	public static void deleteEditorial(EntityManagerFactory emf, long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Editorial editorial = em.find(Editorial.class, id);
		if (editorial != null) {
			em.remove(editorial);
		}
		em.getTransaction().commit();
		em.close();
	}

}
