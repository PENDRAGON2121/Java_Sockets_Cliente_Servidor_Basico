
package Modelos;

import java.io.Serializable;
import java.util.ArrayList;

public class ArchivoDeRecuperacion implements Serializable {

    private int consecutivo;
    private ArrayList<Tarifa> listaDeTarifas;
    private ArrayList<Vehiculo> listaVehiculosEnParqueo;
    private ArrayList<Registro> registroDeVehiculos;

    public ArchivoDeRecuperacion(int consecutivo, ArrayList<Tarifa> listaDeTarifas, ArrayList<Vehiculo> listaVehiculosEnParqueo, ArrayList<Registro> registroDeVehiculos) {
        this.consecutivo = consecutivo;
        this.listaDeTarifas = listaDeTarifas;
        this.listaVehiculosEnParqueo = listaVehiculosEnParqueo;
        this.registroDeVehiculos = registroDeVehiculos;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public ArrayList<Tarifa> getListaDeTarifas() {
        return listaDeTarifas;
    }

    public void setListaDeTarifas(ArrayList<Tarifa> listaDeTarifas) {
        this.listaDeTarifas = listaDeTarifas;
    }

    public ArrayList<Vehiculo> getListaVehiculosEnParqueo() {
        return listaVehiculosEnParqueo;
    }

    public void setListaVehiculosEnParqueo(ArrayList<Vehiculo> listaVehiculosEnParqueo) {
        this.listaVehiculosEnParqueo = listaVehiculosEnParqueo;
    }

    public ArrayList<Registro> getRegistroDeVehiculos() {
        return registroDeVehiculos;
    }

    public void setRegistroDeVehiculos(ArrayList<Registro> registroDeVehiculos) {
        this.registroDeVehiculos = registroDeVehiculos;
    }

    
}
