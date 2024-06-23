/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import Modelos.Vehiculo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mquir
 */
public class SocketCliente {

    //socket del cliente
    private final String HOST = "localhost";
    private final int PORT = 5000;
    private Socket cs;
    
    //flujos de datos
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public SocketCliente() {
        try {
            cs = new Socket(HOST, PORT);
            out = new ObjectOutputStream(cs.getOutputStream());
            in  = new ObjectInputStream(cs.getInputStream());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se obtuvo comunicacion con el servidor", "ERROR",JOptionPane.ERROR_MESSAGE);
            System.out.println("BACKLOG: " + ex.getMessage());
            System.exit(0);
        }
    }
    
    public ArrayList<String> solicitarListaTipoVehiculo() {
        try {           
            //Envia El Parametro 1 para indicarle al servidor que carga debe mandar
            String data = "1";
            //Envia dato de tipo utf
            out.writeObject(data);
            
            //Recibir la Lista
            ArrayList<String>listaTiposV;
            
            listaTiposV = (ArrayList<String>) in.readObject();
            
            //Retorna la lista
            return listaTiposV;
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    public String enviarVehiculoAlServer(Vehiculo _vehiculo){
        try {            
            //TODO: Envia El Parametro 1 para indicarle al servidor que es recibo de carga.
            String data = "2";
            
            //Envia datos
            out.writeObject(data);
            out.writeObject(_vehiculo);
            
            String respuesta = (String)in.readObject();
            
            
            return respuesta;
            
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("ERROR DE ENVIO:"+ ex.getMessage() + "\n"+ ex.getLocalizedMessage());
        }
        return "ERROR AL INSERTAR";
    }
    public String retirarVehiculo(String _placa){
        try {
            //Parametro
            String data = "3";
            out.writeObject(data);
            out.writeObject(_placa);
            
            String respuesta = (String) in.readObject();
            return respuesta;
        } catch (IOException e) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "ERROR Al RETIRAR";
    }
}
