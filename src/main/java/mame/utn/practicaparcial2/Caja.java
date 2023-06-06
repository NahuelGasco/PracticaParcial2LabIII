package mame.utn.practicaparcial2;

import java.util.LinkedList;
import java.util.Queue;
import org.json.JSONArray;
import org.json.JSONObject;

public class Caja {

    private Queue<Cliente> esperaCaja;
    private int tiempoDeCaja;

    public Caja() {
        this.esperaCaja = new LinkedList<>();
        this.tiempoDeCaja = 0;
    }

    public void addClienteCaja(Cliente cliente) {
        this.esperaCaja.add(cliente);
        this.tiempoDeCaja += cliente.getTiempoDeEspera();
    }

    public Queue<Cliente> getEsperaCaja() {
        return esperaCaja;
    }

    public int getTiempoDeCaja() {
        return tiempoDeCaja;
    }

    public int buscandoExcepcion() {
        int i = 0;
        int j = esperaCaja.size();
        int contadorProblemas = 0;
        Queue<Cliente> aux = new LinkedList<>();
        while (i < j) {
            Cliente c = this.esperaCaja.poll();
            if (c.tipoDePago.equals("Tarjeta c/problemas")) {
                contadorProblemas++;
            }
            aux.add(c);
            i++;
        }
        while (!aux.isEmpty()) {
            this.esperaCaja.add(aux.poll());
        }
        return contadorProblemas;
    }

    public void verCaja() {

        int i = 0;
        int j = esperaCaja.size();
        Queue<Cliente> aux = new LinkedList<>();

        while (i < j) {
            Cliente c = this.esperaCaja.poll();
            System.out.println(c.toString());
            aux.add(c);
            i++;
        }

        while (!aux.isEmpty()) {
            this.esperaCaja.add(aux.poll());
        }

    }
    public JSONArray exportarContenido() {
        int i=0;
        int j= esperaCaja.size();     
        JSONArray jsonArray = new JSONArray();
        Queue<Cliente> aux = new LinkedList<>();

        while (i < j) {
            Cliente c = this.esperaCaja.poll();
            JSONObject jsonObject = new JSONObject();
            //jsonObject.put("cantidadArticulos", c.getCantidadArticulos());
            jsonObject.put("tipoCliente", c.toString());
            //jsonObject.put("tipoPago", c.getTipoDePago());
            jsonArray.put(jsonObject);
            aux.add(c);
            i++;
        }
        while (!aux.isEmpty()) {
            this.esperaCaja.add(aux.poll());
        }
        return jsonArray;
    }

}
