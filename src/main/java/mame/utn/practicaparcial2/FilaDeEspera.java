
package mame.utn.practicaparcial2;


import java.util.LinkedList;

public class FilaDeEspera<T> {
  
    private LinkedList<T> fila_espera;

    public FilaDeEspera() {
        this.fila_espera = new LinkedList<>();
    }
    
    public void addFilaEspera(T c){
        this.fila_espera.add(c);
    }

    public LinkedList<T> getFila_espera() {
        return fila_espera;
    }    
}
