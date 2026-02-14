package controlador;

import java.util.ArrayList;
import entidad.Medico;

public class ArregloMedico {

    // 🔹 Atributo privado
    private ArrayList<Medico> med;

    private int correlativo;

    // 🔹 Constructor
    public ArregloMedico() {

        // creación del ArrayList
        med = new ArrayList<Medico>();

        // inicializar correlativo
        correlativo = 501;

        // (Opcional) datos de prueba
        adicionar(new Medico(generarCodigo(), "Carlos", "Lopez",
                "Cardiología", "CMP12345", 1));

        adicionar(new Medico(generarCodigo(), "Andrea", "Perez",
                "Pediatría", "CMP67890", 1));
    }

    // 🔹 Operaciones públicas básicas

    // adicionar objeto Medico
    public void adicionar(Medico x) {
        med.add(x);
    }

    // retornar cantidad de médicos
    public int tamanio() {
        return med.size();
    }

    // obtener médico por posición
    public Medico obtener(int i) {
        return med.get(i);
    }

    // 🔹 Operaciones complementarias

    // generar código correlativo
    public int generarCodigo() {
        return correlativo++;
    }

    // buscar por CMP (único)
    public Medico buscarPorCmp(String cmp) {
        for (int i = 0; i < tamanio(); i++) {
            if (obtener(i).getCmp().equals(cmp)) {
                return obtener(i);
            }
        }
        return null;
    }

    // buscar por código
    public Medico buscarPorCodigo(int cod) {
        for (int i = 0; i < tamanio(); i++) {
            if (obtener(i).getCodMedico() == cod) {
                return obtener(i);
            }
        }
        return null;
    }

    // eliminar lógico (cambiar estado a 0)
    public void eliminar(Medico m) {
        m.setEstado(0);
    }
}
