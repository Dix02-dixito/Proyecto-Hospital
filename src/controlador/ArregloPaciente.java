package controlador;

import java.util.ArrayList;
import entidad.Paciente;

public class ArregloPaciente {

    // 🔹 Atributo privado
    private ArrayList<Paciente> pac;

    private int correlativo;

    // 🔹 Constructor
    public ArregloPaciente() {

        // creación del ArrayList
        pac = new ArrayList<Paciente>();

        // inicializar correlativo
        correlativo = 1001;

        // (Opcional) datos de prueba
        adicionar(new Paciente(generarCodigo(), "Luis", "Ramirez",
                "12345678", 25, "+51987654321",
                "luis@gmail.com", 1));

        adicionar(new Paciente(generarCodigo(), "Maria", "Torres",
                "87654321", 30, "+51912345678",
                "maria@gmail.com", 1));
    }

    // 🔹 Operaciones públicas básicas

    // adicionar objeto Paciente
    public void adicionar(Paciente x) {
        pac.add(x);
    }

    // retornar cantidad de pacientes
    public int tamanio() {
        return pac.size();
    }

    // obtener paciente por posición
    public Paciente obtener(int i) {
        return pac.get(i);
    }

    // 🔹 Operaciones complementarias

    // generar código correlativo
    public int generarCodigo() {
        return correlativo++;
    }

    // buscar por DNI (único)
    public Paciente buscarPorDni(String dni) {
        for (int i = 0; i < tamanio(); i++) {
            if (obtener(i).getDni().equals(dni)) {
                return obtener(i);
            }
        }
        return null;
    }

    // buscar por apellido
    public ArrayList<Paciente> buscarPorApellido(String apellido) {

        ArrayList<Paciente> lista = new ArrayList<Paciente>();

        for (int i = 0; i < tamanio(); i++) {

            Paciente p = obtener(i);

            if (p.getApellidos().equalsIgnoreCase(apellido)) {
                lista.add(p);
            }
        }

        return lista;
    }
    
    //buscar por codigo para el boton modificar
    public Paciente buscarPorCodigo(int codigo) {
        for (int i = 0; i < tamanio(); i++) {
            if (obtener(i).getCodPaciente() == codigo) {
                return obtener(i);
            }
        }
        return null;
    }

    


    // eliminar lo(cambiar estado a 0)
    public void eliminar(Paciente p) {
        p.setEstado(0);
    }
}
