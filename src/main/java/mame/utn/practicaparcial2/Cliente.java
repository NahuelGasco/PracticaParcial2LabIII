package mame.utn.practicaparcial2;

import java.util.Random;

public abstract class Cliente {

    private static final Random random = new Random();

    protected int cantidadArticulos;
    protected int tiempoDeEspera;
    protected String tipoDePago;

    public Cliente() {
        this.cantidadArticulos = random.nextInt(20) + 1; // Genera un número aleatorio entre 1 y 20
        this.tipoDePago = generarTipoPago();
    }

    private String generarTipoPago() {
        int randomNum = random.nextInt(100) + 1; // Genera un número aleatorio entre 1 y 100
        String tipoPago = " ";

        if (randomNum <= 30) {
            tipoPago = "Efectivo";
        } else if (randomNum <= 60) {
            tipoPago = "Tarjeta s/problemas";
        } else if (randomNum <= 100) {
            tipoPago = "Tarjeta c/problemas";
        }
        return tipoPago;
    }

    public int getCantidadArticulos() {
        return cantidadArticulos;
    }

    public String getTipoDePago() {
        return tipoDePago;
    }

    public void setTipoDePago(String tipoDePago) {
        this.tipoDePago = tipoDePago;
    }

    public int getTiempoDeEspera() {
        return tiempoDeEspera;
    }
    
    

    public abstract int calculaTiempoEspera();

    @Override
    public String toString() {
        return  "cantidadArticulos=" + cantidadArticulos + ", tiempoDeEspera=" + tiempoDeEspera + ", tipoDePago=" + tipoDePago + '}';
    }

}
