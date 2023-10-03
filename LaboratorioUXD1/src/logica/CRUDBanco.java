/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author jairo
 */
public class CRUDBanco {

    public static ArrayList<Queue<Cliente>> cajas = new ArrayList<>();
    public static int clientesAtendidos = 0;
    private static int clientesAtendidosTipoA = 0;
    private static int clientesAtendidosTipoB = 0;
    private static int clientesAtendidosTipoC = 0;
    private static int clientesAtendidosTipoD = 0;

    public static void generarClientes() {
        Random rand = new Random();

        for (int i = 0; i < 15; i++) { // Genera 15 clientes
            if (clientesAtendidos >= 15) {
                mostrarMensaje("La fila está llena. Espere a que se atienda a algunos clientes.");
                return;
            }

            int numero = clientesAtendidos + 1;
            char prioridad;

            int randomPrioridad = rand.nextInt(4);
            switch (randomPrioridad) {
                case 0:
                    prioridad = 'A';
                    break;
                case 1:
                    prioridad = 'B';
                    break;
                case 2:
                    prioridad = 'C';
                    break;
                default:
                    prioridad = 'D';
            }

            Cliente cliente = new Cliente(numero, prioridad);
            cajas.get(rand.nextInt(4)).add(cliente);
            clientesAtendidos++;

            mostrarMensaje("Cliente generado: Número " + numero + " Tipo: " + prioridad);
        }

    }

    public static int generarTiempoAtencion() {
        Random rand = new Random();
        return rand.nextInt(24) + 2; // Tiempo de atención aleatorio entre 2 y 25 minutos.
    }

    public static void consultarClientesPorCaja() {
        for (int i = 0; i < cajas.size(); i++) {
            Queue<Cliente> caja = cajas.get(i);
            mostrarMensaje("Caja " + (i + 1) + ": " + caja.size() + " clientes atendidos");
        }
    }

    private static void atenderClientesTipo(char tipo) {
        for (int i = 0; i < cajas.size(); i++) {
            Queue<Cliente> caja = cajas.get(i);
            Iterator<Cliente> iterator = caja.iterator();
            while (iterator.hasNext()) {
                Cliente clienteAtendido = iterator.next();
                if (clienteAtendido.getPrioridad() == tipo) {
                    int tiempoAtencion = generarTiempoAtencion();
                    mostrarMensaje("Atendiendo al cliente en la caja " + (i + 1) + ": Número " + clienteAtendido.getNumero() + clienteAtendido.getPrioridad());
                    mostrarMensaje("Tiempo de atención: " + tiempoAtencion + " minutos");
                    iterator.remove(); // Eliminar al cliente de la cola
                    // Registra estadísticas de tiempo de atención y más si es necesario.

                    // Actualizar el contador de clientes atendidos por tipo
                    switch (tipo) {
                        case 'A':
                            clientesAtendidosTipoA++;
                            break;
                        case 'B':
                            clientesAtendidosTipoB++;
                            break;
                        case 'C':
                            clientesAtendidosTipoC++;
                            break;
                        case 'D':
                            clientesAtendidosTipoD++;
                            break;
                    }
                }
            }
        }
    }

    public static void realizarConsultas() {
        mostrarMensaje("Consulta de clientes:");

        // Mostrar la cantidad de clientes atendidos por tipo
        mostrarMensaje("Clientes de tipo A atendidos: " + clientesAtendidosTipoA);
        mostrarMensaje("Clientes de tipo B atendidos: " + clientesAtendidosTipoB);
        mostrarMensaje("Clientes de tipo C atendidos: " + clientesAtendidosTipoC);
        mostrarMensaje("Clientes de tipo D atendidos: " + clientesAtendidosTipoD);

    }

    public static void atenderClientes() {
        // Atender clientes de tipo A
        atenderClientesTipo('A');

        // Atender clientes de tipo B
        atenderClientesTipo('B');

        // Atender clientes de tipo C
        atenderClientesTipo('C');

        // Atender clientes de tipo D
        atenderClientesTipo('D');
    }

    public static void calcularPromedioTiempoEspera() {
        double sumaTiemposEspera = 0;
        int totalClientesAtendidos = 0;

        for (int i = 0; i < cajas.size(); i++) {
            Queue<Cliente> caja = cajas.get(i);

            for (Cliente cliente : caja) {
                sumaTiemposEspera += cliente.getTiempoEspera();
                totalClientesAtendidos++;
            }
        }

        if (totalClientesAtendidos > 0) {
            double promedioTiempoEspera = sumaTiemposEspera / totalClientesAtendidos;
            mostrarMensaje("Promedio de tiempo de espera por cajero: " + promedioTiempoEspera + " minutos");
        } else {
            mostrarMensaje("No se han atendido clientes aún.");
        }
    }

    public static void reiniciar() {
        // Restablecer todas las variables y datos a su estado inicial
        clientesAtendidos = 0;
        clientesAtendidosTipoA = 0;
        clientesAtendidosTipoB = 0;
        clientesAtendidosTipoC = 0;
        clientesAtendidosTipoD = 0;
        // Limpiar todas las colas
        for (Queue<Cliente> caja : cajas) {
            caja.clear();
        }
    }

    public static void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }

}
