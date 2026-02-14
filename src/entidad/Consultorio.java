package entidad;

public class Consultorio {
	//class privates
	private int codConsultorio;
	private String nombre;
	private int piso;
	private String ubicacion;
	private int capacidad;
	private int estado;
	
	//Constructor
	public Consultorio(int codConsultorio, String nombre, int piso, String ubicacion, int capacidad, int estado) {
		this.codConsultorio = codConsultorio;
		this.nombre = nombre;
		this.piso = piso;
		this.ubicacion = ubicacion;
		this.capacidad = capacidad;
		this.estado = estado;
	}
	
	//metodos get y set

	public int getCodConsultorio() {
		return codConsultorio;
	}

	public void setCodConsultorio(int codConsultorio) {
		this.codConsultorio = codConsultorio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
}
