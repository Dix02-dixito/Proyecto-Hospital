package controlador;

import java.util.ArrayList;
import entidad.Consultorio;

public class ArregloConsultorio {

    // 🔹 Atributo privado
    private ArrayList<Consultorio> con;

    private int correlativo;

    // 🔹 Constructor
    public ArregloConsultorio() {

        // creación del ArrayList
        con = new ArrayList<Consultorio>();

        // inicializar correlativo
        correlativo = 301;

        //  datos de prueba
        adicionar(new Consultorio(generarCodigo(), "C-305",
                3, "Torre A – Ala Sur", 1, 1));

        adicionar(new Consultorio(generarCodigo(), "Sala 2",
                1, "Torre B – Ala Norte", 1, 1));
    }

    // 🔹 Operaciones públicas básicas

    // adicionar objeto Consultorio
    public void adicionar(Consultorio x) {
        con.add(x);
    }

    // retornar cantidad de consultorios
    public int tamanio() {
        return con.size();
    }

    // obtener consultorio por posición
    public Consultorio obtener(int i) {
        return con.get(i);
    }

    // 🔹 Operaciones complementarias

    // generar código correlativo
    public int generarCodigo() {
        return correlativo++;
    }

    // buscar por nombre (único)
    public Consultorio buscarPorNombre(String nombre) {
        for (int i = 0; i < tamanio(); i++) {
            if (obtener(i).getNombre().equalsIgnoreCase(nombre)) {
                return obtener(i);
            }
        }
        return null;
    }

    // buscar por código
    public Consultorio buscarPorCodigo(int cod) {
        for (int i = 0; i < tamanio(); i++) {
            if (obtener(i).getCodConsultorio() == cod) {
                return obtener(i);
            }
        }
        return null;
    }

    // eliminar lógico (cambiar estado a 0)
    public void eliminar(Consultorio c) {
        c.setEstado(0);
    }
}
