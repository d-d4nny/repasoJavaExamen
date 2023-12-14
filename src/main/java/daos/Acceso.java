package daos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Persistence;
import jakarta.persistence.Table;

@Entity
@Table(name = "accesos", schema = "gbp_operacional")
public class Acceso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_acceso", nullable = false)
	private long idAcceso;

	@Column(name = "codigo_acceso", nullable = false)
	private String codigoAcceso;

	@Column(name = "descripcion_acceso")
	private String descripcionAcceso;

	@OneToMany(mappedBy = "acceso")
	private List<Usuario> listaUsuariosConAcceso;
	
	public Acceso() {
		super();
	}

	public Acceso(String codigoAcceso, String descripcionAcceso) {
		super();
		this.codigoAcceso = codigoAcceso;
		this.descripcionAcceso = descripcionAcceso;
	}
	
	public String getCodigoAcceso() {
		return codigoAcceso;
	}

	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}

	public String getDescripcionAcceso() {
		return descripcionAcceso;
	}

	public void setDescripcionAcceso(String descripcionAcceso) {
		this.descripcionAcceso = descripcionAcceso;
	}
	
	
	//Metodos para el crud
	public static void createAcceso(EntityManagerFactory emf, Acceso acceso) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
        em.persist(acceso); 
        em.getTransaction().commit();
        em.close();
    }

    public static Acceso selectAccesoId(EntityManagerFactory emf, long idAcceso) {
		EntityManager em = emf.createEntityManager();
        return em.find(Acceso.class, idAcceso);
    }

    public static void updateAcceso(EntityManagerFactory emf, Acceso acceso) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
        em.merge(acceso);
        em.getTransaction().commit();
        em.close();
    }

    public static void deleteAcceso(EntityManagerFactory emf, long id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
        Acceso acceso = em.find(Acceso.class, id);
        if (acceso != null) {
            em.remove(acceso);
        }
        em.getTransaction().commit();
		em.close();
    }

}








