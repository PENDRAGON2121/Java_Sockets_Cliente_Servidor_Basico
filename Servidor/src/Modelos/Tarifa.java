
package Modelos;

import java.io.Serializable;


public class Tarifa implements Serializable{
    
    private String tipoVehiculo;
    private int codigo;
    private String tarifa;
    private String descuento;

    public Tarifa(String tipoVehiculo, int codigo, String tarifa,String descuento) {
        this.tipoVehiculo = tipoVehiculo;
        this.codigo = codigo;
        this.tarifa = tarifa;
        this.descuento = descuento;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    
    @Override
    public String toString() {
        return "Tipo: " + tipoVehiculo + "\n"
                + "Codigo: " + codigo + "\n"
                + "tarifa: " + tarifa + "\n"
                + "Descuento: " + descuento; 
    
    }
    
    
    
}
