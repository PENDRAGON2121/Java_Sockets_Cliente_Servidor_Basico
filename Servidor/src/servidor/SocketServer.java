/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import Modelos.Vehiculo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JOptionPane;

/**
 *
 * @author mquir
 */
public class SocketServer extends Observable implements Runnable {

    //DATA DEL SOCKET
    private final int PUERTO = 5000;
    private ServerSocket servidor = null;
    private Socket sc = null;//->Corresponde al cliente conectado

    //FLUJO DE DATOS
    private ObjectOutputStream out;
    private ObjectInputStream in;

    //Lista De Tipos de Vehiculos
    private ArrayList<String> listaDeVehiculos;

    //CLASS
    GestionDeParqueo gestion;

    //CTOR
    public SocketServer() {
        listaDeVehiculos = new ArrayList<>();
        gestion = new GestionDeParqueo();
    }

    @Override
    public void run() {

        try {
            //Establecemos el server
            servidor = new ServerSocket(PUERTO);
            while (true) {

                //Lo Dejamos a la escucha
                System.out.println("SERVIDOR EN ESCUCHA.");
                sc = servidor.accept();

                //Conexion del cliente exitosa
                System.out.println("CLIENTE CONECTADO");

                //Abrimos el flujo de Datos
                in = new ObjectInputStream(sc.getInputStream());
                out = new ObjectOutputStream(sc.getOutputStream());
                //Esperamos un dato de ingreso del usuario

                String dataIn = (String) in.readObject();
                //Hacemos algo segun lo que pide el cliente
                switch (dataIn) {
                    case "1" -> {
                        System.out.println("Opcion 1 Devolverle La Lista De Tipos De Vehiculo (CLASS:SS");
                        ArrayList<String> listaTipoVehiculo = gestion.obtenerListaDeVehiculos();

                        //Envia los Datos
                        out.writeObject(listaTipoVehiculo);

                    }
                    case "2" -> {
                        System.out.println("Opcion 2 del cliente (class:SS");

                        Vehiculo v = (Vehiculo) in.readObject();
                        gestion.registrarVehiculo(v);

                        //Actualizar al observador
                        this.setChanged();
                        this.notifyObservers(gestion);
                        this.clearChanged();
                        //respuesta
                        out.writeObject("Recibido");
                    }
                    case "3" -> {
                        System.out.println("opcion 3 retiro de vehiculo (CLASS:SS)");

                        String placa = (String) in.readObject();
                        String v = gestion.retirarVehiculo(placa);

                        //Actualizar al observador
                        this.setChanged();
                        this.notifyObservers(gestion);
                        this.clearChanged();
                        
                        //respuesta
                        out.writeObject(v);

                    }

                    default ->
                        throw new AssertionError();
                }

                in.close();
                sc.close();//cerramos el cliente
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "PUERTO EN USO","ERROR AL CARGAR SERVER",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

}
