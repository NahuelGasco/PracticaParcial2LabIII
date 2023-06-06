package mame.utn.practicaparcial2;

public class ClienteComun extends Cliente {

    public ClienteComun() {
        super();
        this.tiempoDeEspera = calculaTiempoEspera();
    }

    @Override
    public int calculaTiempoEspera() {
        int tiempoArticulos = getCantidadArticulos() * 30;

        int tiempoTipoCliente = 0;
        tiempoTipoCliente = (int) (tiempoArticulos * 0.15);

        int tiempoTipoPago = 0;
        switch (getTipoDePago()) {
            case "Efectivo":
                tiempoTipoPago = (int) (tiempoArticulos * 0.10);
                break;
            case "Tarjeta s/problemas":
                tiempoTipoPago = (int) (tiempoArticulos * 0.15);
                break;
            case "Tarjeta c/problemas":
                tiempoTipoPago = (int) (tiempoArticulos * 0.50);
                break;
            default:
                break;
        }

        return tiempoArticulos + tiempoTipoCliente + tiempoTipoPago;
    }

    @Override
    public String toString() {
        return "ClienteComun{" + super.toString() + '}';
    }  
       
}
