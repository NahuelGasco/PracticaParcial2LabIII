package mame.utn.practicaparcial2;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

public class Supermercado {

    private static final Random random = new Random();
    private List<Caja> cajas;
    private FilaDeEspera<Cliente> filaEspera = new FilaDeEspera<>();
    private int numCajas;
    private int numClientes;

    public Supermercado(int numCajas, int numClientes) {
        this.cajas = new ArrayList<>();
        this.numCajas = numCajas;
        this.numClientes = numClientes;
        for (int i = 0; i < numCajas; i++) {
            cajas.add(new Caja());
        }

        for (int i = 0; i < numClientes; i++) {
            int r = random.nextInt(3) + 1;
            switch (r) {
                case 1 ->
                    filaEspera.addFilaEspera(new ClienteComun());
                case 2 ->
                    filaEspera.addFilaEspera(new ClienteJubilado());
                case 3 ->
                    filaEspera.addFilaEspera(new ClientaEmbarazada());
            }
        }
    }

    public void repartirClientesEnCajas() {

        /* for (int i = 0; i < numCajas; i++) {
            
            this.cajas.get(0).addClienteCaja(filaEspera.getFila_espera().pollFirst());
        }*/
        int j = this.filaEspera.getFila_espera().size();
        /*for (int i = 0; i < j; i++) {
            Caja cajaMenosLlena = obtenerCajaMenosLlena();
            cajaMenosLlena.addClienteCaja(filaEspera.getFila_espera().poll());
        }*/

        for (int i = 0; i < j; i++) {
            Caja cajaMenosTiempo = obtenerCajaMenorTiempo();
            cajaMenosTiempo.addClienteCaja(filaEspera.getFila_espera().pollFirst());
        }
    }

    public void atender() throws conProblemas {
        int numeroCaja = 1;
        int acumTiempoTotal = 0;
        for (Caja caja : cajas) {
            System.out.println("\nCAJA N" + numeroCaja
                    + "\nTiempo de atencion en caja = " + caja.getTiempoDeCaja()
                    + "\nClientes Atendidos = " + caja.getEsperaCaja().size());
            numeroCaja++;
            acumTiempoTotal += caja.getTiempoDeCaja();
            
            if(caja.buscandoExcepcion() >= 3){
                throw new conProblemas("Mas de 3 clientes con problemas en su tarjeta en una caja");
            }
           // caja.getEsperaCaja().clear();
        }

        System.out.println("\nClientes totales atendidos = " + this.numClientes
                + "\nTiempo total acumulado en minutos = " + acumTiempoTotal / 60);

    }

    public Caja obtenerCajaMenorTiempo() {

        Caja conMenosTiempo = this.cajas.get(0);

        int i = 0;
        while (!this.filaEspera.getFila_espera().isEmpty() && i < cajas.size()) {

            if (conMenosTiempo.getTiempoDeCaja() > cajas.get(i).getTiempoDeCaja()) {
                conMenosTiempo = cajas.get(i);
            }
            i++;
        }
        return conMenosTiempo;
    }

    public Caja obtenerCajaMenosLlena() {

        Caja cajaMenor = this.cajas.get(0);

        int i = 1;
        while (!this.filaEspera.getFila_espera().isEmpty() && i < this.cajas.size()) {

            if (cajaMenor.getEsperaCaja().size() > this.cajas.get(i).getEsperaCaja().size()) {
                cajaMenor = cajas.get(i);
            }
            i++;
        }

        return cajaMenor;
    }

    public void mostrarFila() {

        for (Cliente c : this.filaEspera.getFila_espera()) {
            System.out.println(c);
        }

    }

    public void mostrarCaja() {
        for (int i = 0; i < cajas.size(); i++) {
            System.out.println("Caja num: " + i);
            cajas.get(i).verCaja();
        }
    }
    
    public JSONArray exportarContenidoCajas() {
        JSONArray jsonArray = new JSONArray();
        for (Caja caja : cajas) {
            jsonArray.put(caja.exportarContenido());
        }
        return jsonArray;
    }
}


