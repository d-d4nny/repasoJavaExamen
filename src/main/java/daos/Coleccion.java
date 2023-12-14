package daos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "colecciones", schema = "gbp_operacional")
public class Coleccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_coleccion", nullable = false)
	private long idColeccion;

	@Column(name = "nombre_coleccion", nullable = false)
	private String nombreColeccion;

	@OneToMany(mappedBy = "coleccion")
	private List<Libro> listaLibrosColeccion;

	public Coleccion() {

	}

	public Coleccion(String nombreColeccion) {
		super();
		this.nombreColeccion = nombreColeccion;
	}

	public long getIdColeccion() {
		return idColeccion;
	}

	public String getNombreColeccion() {
		return nombreColeccion;
	}

	public List<Libro> getListaLibrosColeccion() {
		return listaLibrosColeccion;
	}
	
	public void setIdColeccion(long idColeccion) {
		this.idColeccion = idColeccion;
	}

	public void setNombreColeccion(String nombreColeccion) {
		this.nombreColeccion = nombreColeccion;
	}

	public void setListaLibrosColeccion(List<Libro> listaLibrosColeccion) {
		this.listaLibrosColeccion = listaLibrosColeccion;
	}

	//Metodos para el crud
	public static void createColeccion(EntityManagerFactory emf, Coleccion coleccion) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(coleccion);
		em.getTransaction().commit();
		em.close();
	}

	public static Coleccion selectColeccionId(EntityManagerFactory emf, long id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Coleccion.class, id);
	}

	public static void updateColeccion(EntityManagerFactory emf, Coleccion coleccion) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(coleccion);
		em.getTransaction().commit();
		em.close();
	}

	public static void deleteColeccion(EntityManagerFactory emf, long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Coleccion coleccion = em.find(Coleccion.class, id);
		if (coleccion != null) {
			em.remove(coleccion);
		}
		em.getTransaction().commit();
		em.close();
	}

}
