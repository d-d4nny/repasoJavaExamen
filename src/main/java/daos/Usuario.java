package daos;

import java.util.Calendar;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios", schema = "gbp_operacional")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario", nullable = false)
	private long idUsuario;

	@Column(name = "dni_usuario", nullable = false)
	private String dniUsuario;

	@Column(name = "nombre_usuario")
	private String nombreUsuario;

	@Column(name = "apellidos_usuario")
	private String apellidosUsuario;

	@Column(name = "tlf_usuario")
	private String tlfUsuario;

	@Column(name = "email_usuario")
	private String emailUsuario;

	@Column(name = "clave_usuario")
	private String claveUsuario;

	@Column(name = "estaBloqueado_usuario")
	private boolean estaBloqueadoUsuario;

	@Column(name = "fch_fin_bloqueo")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fchFinBloqueo;

	@Column(name = "fch_alta_usuario")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fchAltaUsuario;

	@Column(name = "fch_baja_usuario")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fchBajaUsuario;

	@ManyToOne
	@JoinColumn(name = "id_acceso")
	Acceso acceso;

	public Usuario() {
		super();
	}

	public Usuario(String dniUsuario, String nombreUsuario, String apellidosUsuario, String tlfUsuario,
			String emailUsuario, String claveUsuario, boolean estaBloqueadoUsuario, Calendar fchFinBloqueo,
			Calendar fchAltaUsuario, Calendar fchBajaUsuario, Acceso acceso) {
		super();
		this.dniUsuario = dniUsuario;
		this.nombreUsuario = nombreUsuario;
		this.apellidosUsuario = apellidosUsuario;
		this.tlfUsuario = tlfUsuario;
		this.emailUsuario = emailUsuario;
		this.claveUsuario = claveUsuario;
		this.estaBloqueadoUsuario = estaBloqueadoUsuario;
		this.fchFinBloqueo = fchFinBloqueo;
		this.fchAltaUsuario = fchAltaUsuario;
		this.fchBajaUsuario = fchBajaUsuario;
		this.acceso = acceso;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public String getDniUsuario() {
		return dniUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getApellidosUsuario() {
		return apellidosUsuario;
	}

	public String getTlfUsuario() {
		return tlfUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public String getClaveUsuario() {
		return claveUsuario;
	}

	public boolean isEstaBloqueadoUsuario() {
		return estaBloqueadoUsuario;
	}

	public Calendar getFchFinBloqueo() {
		return fchFinBloqueo;
	}

	public Calendar getFchAltaUsuario() {
		return fchAltaUsuario;
	}

	public Calendar getFchBajaUsuario() {
		return fchBajaUsuario;
	}

	public Acceso getAcceso() {
		return acceso;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setApellidosUsuario(String apellidosUsuario) {
		this.apellidosUsuario = apellidosUsuario;
	}

	public void setTlfUsuario(String tlfUsuario) {
		this.tlfUsuario = tlfUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}

	public void setEstaBloqueadoUsuario(boolean estaBloqueadoUsuario) {
		this.estaBloqueadoUsuario = estaBloqueadoUsuario;
	}

	public void setFchFinBloqueo(Calendar fchFinBloqueo) {
		this.fchFinBloqueo = fchFinBloqueo;
	}

	public void setFchAltaUsuario(Calendar fchAltaUsuario) {
		this.fchAltaUsuario = fchAltaUsuario;
	}

	public void setFchBajaUsuario(Calendar fchBajaUsuario) {
		this.fchBajaUsuario = fchBajaUsuario;
	}

	public void setAcceso(Acceso acceso) {
		this.acceso = acceso;
	}

	//Metodos para el crud
	
	public static void mostrarTodos(EntityManagerFactory emf, Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		
		List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    	
    	for (Usuario usuario1 : usuarios) {
            detallesUsuario(usuario1);
        }
	}
	
	
	public static void createUsuario(EntityManagerFactory emf, Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
	}

	public static Usuario selectUsuarioId(EntityManagerFactory emf, long id) {
		EntityManager em = emf.createEntityManager();
		return em.find(Usuario.class, id);
	}

	public static void updateUsuario(EntityManagerFactory emf, Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();
		em.close();
	}

	public static void deleteUsuario(EntityManagerFactory emf, long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Usuario usuario = em.find(Usuario.class, id);
		if (usuario != null) {
			em.remove(usuario);
		}
		em.getTransaction().commit();
		em.close();
	}
	
	private static void detallesUsuario(Usuario usuario) {
	    System.out.println("Usuario encontrado:");
	    System.out.println("ID: " + usuario.getIdUsuario());
	    System.out.println("DNI: " + usuario.getDniUsuario());
	    System.out.println("Nombre: " + usuario.getNombreUsuario());
	    System.out.println("Apellidos: " + usuario.getApellidosUsuario());
	    System.out.println("Teléfono: " + usuario.getTlfUsuario());
	    System.out.println("Email: " + usuario.getEmailUsuario());
	    System.out.println("Clave: " + usuario.getClaveUsuario());
	    System.out.println("¿Está bloqueado?: " + usuario.isEstaBloqueadoUsuario());
	    System.out.println("Fecha fin de bloqueo: " + (usuario.getFchFinBloqueo() != null ? usuario.getFchFinBloqueo().getTime().toString() : "No definida"));
	    System.out.println("Fecha de alta: " + (usuario.getFchAltaUsuario() != null ? usuario.getFchAltaUsuario().getTime().toString() : "No definida"));
	    System.out.println("Fecha de baja: " + (usuario.getFchBajaUsuario() != null ? usuario.getFchBajaUsuario().getTime().toString() : "No definida"));

	    // Acceso del usuario
	    if (usuario.getAcceso() != null) {
	        System.out.println("Codigo de acceso: " + usuario.getAcceso().getCodigoAcceso());
	        System.out.println("Descripción de acceso: " + usuario.getAcceso().getDescripcionAcceso());
	        // Otros detalles del Acceso, según la estructura de la entidad Acceso.
	    } else {
	        System.out.println("No se encontró información de acceso para este usuario.");
	    }

	    System.out.println("-----------------------------------");
	}

}
