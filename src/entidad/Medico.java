package entidad;

public class Medico {
	//class private
	private int codMedico;
	private String nombres;
	private String apellidos;
	private String especialidad;
	private String cmp;
	private int estado;
	
    //Constructor
	public Medico(int codMedico, String nombres, String apellidos, String especialidad, String cmp, int estado) {
		this.codMedico = codMedico;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.especialidad = especialidad;
		this.cmp = cmp;
		this.estado = estado;
	}
	
	//metodos get y set

	public int getCodMedico() {
		return codMedico;
	}

	public void setCodMedico(int codMedico) {
		this.codMedico = codMedico;
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

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getCmp() {
		return cmp;
	}

	public void setCmp(String cmp) {
		this.cmp = cmp;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}			
}
