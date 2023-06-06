

package mame.utn.practicaparcial2;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;


public class PracticaParcial2 {

    public static void main(String[] args) {
        
        Supermercado supermercado = new  Supermercado(5, 20);
        
        supermercado.mostrarFila();
        
        try {
            supermercado.repartirClientesEnCajas();
            supermercado.atender();
        } catch (conProblemas e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        JSONArray contenidoCajas = supermercado.exportarContenidoCajas();
        System.out.println(contenidoCajas.toString());
     
        try (FileWriter fileWriter = new FileWriter("contenido_cajas.json")) {
                fileWriter.write(contenidoCajas.toString());
                System.out.println("Contenido de las cajas exportado correctamente en el archivo 'contenido_cajas.json'.");
            } catch (IOException e) {
                System.out.println("Error al guardar el contenido de las cajas en el archivo.");
                
            }
    }
}

    

