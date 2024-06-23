package Modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Registro implements Serializable {

    //PROPIOS DE REGISTROS
    private int numeroTransaccion;
    private LocalDate fechaIngreso;

    //DATOS DE VEHICULO
    private String tipoVehiculo;
    private String placa;
    private LocalTime horaIngreso;
    private LocalTime horaSalida = null;
    private double montoCobrado = 0;

    public Registro(int numeroTransaccion, LocalDate fechaDeIngreso, LocalTime horaIngreso, String placa, String tipoVehiculo) {
        this.numeroTransaccion = numeroTransaccion;
        this.fechaIngreso = fechaDeIngreso;
        this.horaIngreso = horaIngreso;
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;

        //SALIDA Y MONTO SE SETEAN AL SALIR EL VEHICULO
    }

    public int getNumeroTransaccion() {
        return numeroTransaccion;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public LocalTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getPlaca() {
        return placa;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setMontoCobrado(double montoCobrado) {
        this.montoCobrado = montoCobrado;
    }

    public double getMontoCobrado() {
        return montoCobrado;
    }

    @Override
    public String toString() {
        return "------------Registro------------"
                + "\nNumero Consecutivo: " + numeroTransaccion
                + "\nFecha de Ingreso: " + fechaIngreso
                + "\nHora de Ingreso: " + horaIngreso
                + "\nHora de Salida: " + horaSalida
                + "\nPlaca: " + placa
                + "\nTipo: " + tipoVehiculo
                + "\nMonto Cobrado: " + montoCobrado + "\n";
    }
}
