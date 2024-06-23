package servidor;

import Modelos.ArchivoDeRecuperacion;
import Modelos.Registro;
import Modelos.Tarifa;
import Modelos.Vehiculo;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class GestionDeParqueo {

    //NUMERO DE TRANSACCION;
    private int consecutivo = 1;

    //ARCHIVO
    private final String ARCHIVO_TARIFAS = "Tarifas.txt";
    private final String ARCHIVO_GUARDADO = "datos.dat";

    //listas
    private ArrayList<Tarifa> listaDeTarifas;
    private ArrayList<Vehiculo> listaVehiculosEnParqueo;
    private ArrayList<Registro> registroDeVehiculos;

    public GestionDeParqueo() {
        listaDeTarifas = new ArrayList<>();
        listaVehiculosEnParqueo = new ArrayList<>();
        registroDeVehiculos = new ArrayList<>();

        CargaDeDatos();
    }

    private void CargaDeDatos() {
        cargarTarifasDesdeArchivo(ARCHIVO_TARIFAS);
        recuperarData();
    }

    private void cargarTarifasDesdeArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 3) {

                    String tipoVehiculo = partes[0].trim();
                    int codigo = Integer.parseInt(partes[1].trim());
                    String descuento = partes[2].trim();
                    String tarifa = partes[3].trim();

                    Tarifa tarifaObj = new Tarifa(tipoVehiculo, codigo, tarifa, descuento);
                    listaDeTarifas.add(tarifaObj);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de Tarifas: " + e.getMessage());
        }
    }

    public ArrayList<String> obtenerListaDeVehiculos() {

        ArrayList<String> lista = new ArrayList<>();

        for (Tarifa data : listaDeTarifas) {
            lista.add(data.getTipoVehiculo());
        }

        return lista;
    }

    public void registrarVehiculo(Vehiculo _vehiculo) {

        LocalDate fechaI = LocalDate.now();
        LocalTime horaI = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond());

        //settear hora de entrada
        Vehiculo vehiculo = _vehiculo;
        vehiculo.setHoraDeEntrada(horaI);

        //METER AL REGISTRO
        Registro registro = new Registro(consecutivo, fechaI, horaI, vehiculo.getPlaca(), vehiculo.getTipoDeVehiculo());
        registroDeVehiculos.add(registro);
        consecutivo++;

        //Meter a la lista de carros en parqueo
        listaVehiculosEnParqueo.add(vehiculo);

        //guarda Los Cambios
        guardarData();
    }

    public String retirarVehiculo(String _placa) {

        Vehiculo salida = null;

        for (Vehiculo v : listaVehiculosEnParqueo) {
            if (v.getPlaca().equals(_placa)) {
                salida = v;
            }
        }

        //SALIDA
        if (salida != null) {
            Vehiculo _Vehiculo = Calculos(salida);
            ActualizarCambiosEnListas(_Vehiculo);
            guardarData();
            return _Vehiculo.toString();
        }
        System.out.println("SALIDA: EN NULL");

        return null;
    }

    //Guardar Los Registros
    public void guardarData() {
        //DATA A GUARDAR
        if (consecutivo != 0) {
            ArchivoDeRecuperacion data = new ArchivoDeRecuperacion(consecutivo, listaDeTarifas, listaVehiculosEnParqueo, registroDeVehiculos);

            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO_GUARDADO))) {
                out.writeObject(data);
            } catch (IOException e) {
                System.out.println("Error al guardar en el archivo: " + e.getLocalizedMessage());
            }

        }
    }

    //Recuperar Los Registros
    public void recuperarData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO_GUARDADO))) {
            // Leer el objeto contenedor desde el archivo
            ArchivoDeRecuperacion contenedor = (ArchivoDeRecuperacion) in.readObject();

            //rellenar
            consecutivo = contenedor.getConsecutivo();
            listaDeTarifas = contenedor.getListaDeTarifas();
            listaVehiculosEnParqueo = contenedor.getListaVehiculosEnParqueo();
            registroDeVehiculos = contenedor.getRegistroDeVehiculos();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ERROR:" + e.getLocalizedMessage());
        }
    }

    public ArrayList<Vehiculo> getListaVehiculosEnParqueo() {
        return listaVehiculosEnParqueo;
    }

    public ArrayList<Registro> getRegistroDeVehiculos() {
        return registroDeVehiculos;
    }

    private Vehiculo Calculos(Vehiculo _salida) {

        Vehiculo v = _salida;
        //HORAS
        LocalTime horaDeEntrada = v.getHoraDeEntrada();

        LocalTime horaDeSalida = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond());

        Duration duracion = Duration.between(horaDeEntrada, horaDeSalida);

        long LSegundos = duracion.getSeconds();
        double segundos = LSegundos;

        //Tarifas
        double t_VC = Double.parseDouble(listaDeTarifas.get(0).getTarifa());
        double t_VE = Double.parseDouble(listaDeTarifas.get(1).getTarifa());
        double t_MC = Double.parseDouble(listaDeTarifas.get(2).getTarifa());
        double t_ME = Double.parseDouble(listaDeTarifas.get(3).getTarifa());

        //Cortesia
        double c_VC = Double.parseDouble(listaDeTarifas.get(0).getDescuento());
        double c_VE = Double.parseDouble(listaDeTarifas.get(1).getDescuento());
        double c_MC = Double.parseDouble(listaDeTarifas.get(2).getDescuento());
        double c_ME = Double.parseDouble(listaDeTarifas.get(3).getDescuento());

        switch (v.getTipoDeVehiculo()) {
            case "Vehiculo de Combustion" -> {

                //hora de salida
                v.setHoraDeSalida(horaDeSalida);
                //TOTAL A PAGAR                
                Double total = segundos * t_VC;
                total = total - c_VC;

                if (total < 0.0) {
                    total = 0.0;
                }

                v.setTotalAPagar(String.valueOf(total));

                //retorna vehiculo con calculos
                return v;

            }
            case "Vehiculo Electrico" -> {
                v.setHoraDeSalida(horaDeSalida);
                //TOTAL A PAGAR                
                Double total = segundos * t_VE;
                total = total - c_VE;

                if (total < 0.0) {
                    total = 0.0;
                }

                v.setTotalAPagar(String.valueOf(total));

                return v;
            }
            case "Moticleta de Combustion" -> {
                v.setHoraDeSalida(horaDeSalida);
                //TOTAL A PAGAR                
                Double total = segundos * t_MC;
                total = total - c_MC;

                if (total < 0.0) {
                    total = 0.0;
                }
                v.setTotalAPagar(String.valueOf(total));
                //retorna vehiculo con calculos
                return v;
            }
            case "Moticleta Electrica" -> {
                v.setHoraDeSalida(horaDeSalida);
                //TOTAL A PAGAR                
                Double total = segundos * t_ME;
                total = total - c_ME;

                if (total < 0.0) {
                    total = 0.0;
                }
                v.setTotalAPagar(String.valueOf(total));

                //retorna vehiculo con calculos
                return v;
            }
            default ->
                throw new AssertionError();
        }
    }

    private void ActualizarCambiosEnListas(Vehiculo _Vehiculo) {

        int indice = -1;

        for (Vehiculo vehiculo : listaVehiculosEnParqueo) {
            if (_Vehiculo.getPlaca().equals(vehiculo.getPlaca())) {
                indice = listaVehiculosEnParqueo.indexOf(vehiculo);
                break;
            }
        }

        if (indice != -1) {
            listaVehiculosEnParqueo.remove(indice);
        } else {
            System.out.println("Vehículo no encontrado en la lista de parqueo.");
        }

        for (int i = 0; i < registroDeVehiculos.size(); i++) {
            Registro registro = registroDeVehiculos.get(i);

            if (registro.getPlaca().equals(_Vehiculo.getPlaca())) {
                registro.setHoraSalida(_Vehiculo.getHoraDeSalida());
                registro.setMontoCobrado(Double.parseDouble(_Vehiculo.getTotalAPagar()));
                registroDeVehiculos.set(i, registro); // Actualizar el elemento existente
                break;
            }
        }
    }
}