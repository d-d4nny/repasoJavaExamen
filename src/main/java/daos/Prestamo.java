package daos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "prestamos", schema = "gbp_operacional")
public class Prestamo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_prestamo", nullable = false)
	private long idPrestamo;

	@Column(name = "fch_inicio_prestamo")
	private Calendar fechaInicioPrestamo;
	@Column(name = "fch_fin_prestamo")
	private Calendar fechaFinPrestamo;
	@Column(name = "fch_entrega_prestamo")
	private Calendar fechaEntregaPrestamo;

	@ManyToMany(mappedBy = "listaPrestamos")
	private List<Libro> listalibros;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_estado_prestamo")
	EstadoPrestamo estadoPrestamo;

	public Prestamo(Calendar fechaInicioPrestamo, Calendar fechaFinPrestamo, Calendar fechaEntregaPrestamo,
			Usuario usuario, EstadoPrestamo estadoPrestamo) {
		super();
		this.fechaInicioPrestamo = fechaInicioPrestamo;
		this.fechaFinPrestamo = fechaFinPrestamo;
		this.fechaEntregaPrestamo = fechaEntregaPrestamo;
		this.usuario = usuario;
		this.estadoPrestamo = estadoPrestamo;
		this.listalibros = new ArrayList<>();
		
	}

	public Prestamo() {

	}

	public long getIdPrestamo() {
		return idPrestamo;
	}

	public Calendar getFechaInicioPrestamo() {
		return fechaInicioPrestamo;
	}

	public Calendar getFechaFinPrestamo() {
		return fechaFinPrestamo;
	}

	public Calendar getFechaEntregaPrestamo() {
		return fechaEntregaPrestamo;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public List<Libro> getListalibros() {
		return listalibros;
	}

	public EstadoPrestamo getEstadoPrestamo() {
		return estadoPrestamo;
	}
	
	public void setIdPrestamo(long idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public void setFechaInicioPrestamo(Calendar fechaInicioPrestamo) {
		this.fechaInicioPrestamo = fechaInicioPrestamo;
	}

	public void setFechaFinPrestamo(Calendar fechaFinPrestamo) {
		this.fechaFinPrestamo = fechaFinPrestamo;
	}

	public void setFechaEntregaPrestamo(Calendar fechaEntregaPrestamo) {
		this.fechaEntregaPrestamo = fechaEntregaPrestamo;
	}

	public void setListalibros(List<Libro> listalibros) {
		this.listalibros = listalibros;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}

	//Metodos crud
	public static void createPrestamo(EntityManagerFactory emf, Prestamo prestamo) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(prestamo);
		em.getTransaction().commit();
		em.close();
	}

	public static Prestamo selectPrestamoId(EntityManagerFactory emf, long id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Prestamo.class, id);
	}

	public static void updatePrestamo(EntityManagerFactory emf, Prestamo prestamo) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(prestamo);
		em.getTransaction().commit();
		em.close();
	}

	public static void deletePrestamo(EntityManagerFactory emf, long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Prestamo prestamo = em.find(Prestamo.class, id);
		if (prestamo != null) {
			em.remove(prestamo);
		}
		em.getTransaction().commit();
		em.close();
	}
}
