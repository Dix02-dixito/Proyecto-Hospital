package controlador;

import java.util.ArrayList;
import entidad.Cita;

public class ArregloCita {

    // 🔹 Atributo privado
    private ArrayList<Cita> cit;

    private int correlativo;

    // 🔹 Constructor
    public ArregloCita() {

        cit = new ArrayList<Cita>();
        correlativo = 1;

        // Datos de prueba (opcional)
        adicionar(new Cita(generarNumero(), 1001, 501,
                301, "15/02/2026",
                "10:00", 0, "Control"));
    }

    // 🔹 Operaciones básicas

    public void adicionar(Cita x) {
        cit.add(x);
    }

    public int tamanio() {
        return cit.size();
    }

    public Cita obtener(int i) {
        return cit.get(i);
    }

    // 🔹 Operaciones complementarias

    public int generarNumero() {
        return correlativo++;
    }

    public Cita buscarPorNumero(int num) {
        for (int i = 0; i < tamanio(); i++) {
            if (obtener(i).getNumCita() == num) {
                return obtener(i);
            }
        }
        return null;
    }

    // cancelar cita
    public void cancelar(Cita c) {
        c.setEstado(2);
    }

    // marcar como atendida
    public void atender(Cita c) {
        c.setEstado(1);
    }
}
