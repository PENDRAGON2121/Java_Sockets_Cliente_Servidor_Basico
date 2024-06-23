
package Modelos;

import java.io.Serializable;
import java.time.LocalTime;


public class Vehiculo implements Serializable{
  
    //Datos para vehiculo
    private String placa;
    private String tipoDeVehiculo;
    private LocalTime  horaDeEntrada;
    private LocalTime  horaDeSalida;
    private String TotalAPagar;

    public Vehiculo(String placa, String tipoDeVehiculo) {
        this.placa = placa;
        this.tipoDeVehiculo = tipoDeVehiculo;
    }

    public String getTotalAPagar() {
        return TotalAPagar;
    }

    public void setTotalAPagar(String TotalAPagar) {
        this.TotalAPagar = TotalAPagar;
    }

    public LocalTime getHoraDeEntrada() {
        return horaDeEntrada;
    }

    public void setHoraDeEntrada(LocalTime horaDeEntrada) {
        this.horaDeEntrada = horaDeEntrada;
    }

    public LocalTime getHoraDeSalida() {
        return horaDeSalida;
    }

    public void setHoraDeSalida(LocalTime horaDeSalida) {
        this.horaDeSalida = horaDeSalida;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoDeVehiculo() {
        return tipoDeVehiculo;
    }

    public void setTipoDeVehiculo(String tipoDeVehiculo) {
        this.tipoDeVehiculo = tipoDeVehiculo;
    }


    @Override
    public String toString() {
        return "----DATA DEL VEHICULO---- \nPLACA: " + placa + "\nTIPO: " + tipoDeVehiculo  + "\nHORA DE ENTRADA:" + horaDeEntrada+ ""
                + "\nHORA DE SALIDA: "+ horaDeSalida + "\nTOTAL A PAGAR: $" + TotalAPagar; 
    }
    
    
    
}
