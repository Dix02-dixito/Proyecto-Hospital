package entidad;

public class Paciente {

	//clases privadas
	private int codPaciente;
	private String nombres;
	private String apellidos;
	private String dni;
	private int edad;
	private String celular;
	private String correo;
	private int estado;
	
	//constructor
	public Paciente(int codPaciente, String nombres, String apellidos, String dni, int edad, String celular,
			String correo, int estado) {
		this.codPaciente = codPaciente;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.edad = edad;
		this.celular = celular;
		this.correo = correo;
		this.estado = estado;
	}
	
	public Paciente() {
	}

	
	//metodos get y set
	public int getCodPaciente() {
		return codPaciente;
	}

	public void setCodPaciente(int codPaciente) {
		this.codPaciente = codPaciente;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
	
}
