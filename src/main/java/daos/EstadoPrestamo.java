package daos;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "estados_prestamos", schema = "gbp_operacional")
public class EstadoPrestamo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estado_prestamo", nullable = false)
	private long idEstadoPrestamo;

	@Column(name = "codigo_estado_prestamo", nullable = false)
	private String codigoEstadoPrestamo;

	@Column(name = "descripcion_estado_prestamo")
	private String descripcionEstadoPrestamo;

	@OneToMany(mappedBy = "estadoPrestamo")
	private List<Prestamo> listaPrestamos;

	public EstadoPrestamo() {

	}

	public EstadoPrestamo(String codigoEstadoPrestamo, String descripcionEstadoPrestamo) {
		super();
		this.codigoEstadoPrestamo = codigoEstadoPrestamo;
		this.descripcionEstadoPrestamo = descripcionEstadoPrestamo;
	}

	public long getIdEstadoPrestamo() {
		return idEstadoPrestamo;
	}

	public String getCodigoEstadoPrestamo() {
		return codigoEstadoPrestamo;
	}

	public String getDescripcionEstadoPrestamo() {
		return descripcionEstadoPrestamo;
	}

	public List<Prestamo> getListaPrestamos() {
		return listaPrestamos;
	}

	
	public void setIdEstadoPrestamo(long idEstadoPrestamo) {
		this.idEstadoPrestamo = idEstadoPrestamo;
	}

	public void setCodigoEstadoPrestamo(String codigoEstadoPrestamo) {
		this.codigoEstadoPrestamo = codigoEstadoPrestamo;
	}

	public void setDescripcionEstadoPrestamo(String descripcionEstadoPrestamo) {
		this.descripcionEstadoPrestamo = descripcionEstadoPrestamo;
	}

	public void setListaPrestamos(List<Prestamo> listaPrestamos) {
		this.listaPrestamos = listaPrestamos;
	}

	// Metodos para el crud
	public static void createEstadoPrestamo(EntityManagerFactory emf, EstadoPrestamo estadoPrestamo) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(estadoPrestamo);
		em.getTransaction().commit();
		em.close();
	}

	public static EstadoPrestamo selectEstadoPrestamoId(EntityManagerFactory emf, long id) {
		EntityManager em = emf.createEntityManager();
		return em.find(EstadoPrestamo.class, id);
	}

	public static void updateEstadoPrestamo(EntityManagerFactory emf, EstadoPrestamo estadoPrestamo) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(estadoPrestamo);
		em.getTransaction().commit();
		em.close();
	}

	public static void deleteEstadoPrestamo(EntityManagerFactory emf, long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		EstadoPrestamo estadoPrestamo = em.find(EstadoPrestamo.class, id);
		if (estadoPrestamo != null) {
			em.remove(estadoPrestamo);
		}
		em.getTransaction().commit();
		em.close();
	}

}
