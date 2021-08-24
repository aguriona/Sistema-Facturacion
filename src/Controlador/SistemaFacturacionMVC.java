/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Vista.ABMCliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Augusto
 */
public class SistemaFacturacionMVC {



    public static void main(String[] args) {
        //new ABMCliente().setVisible(true);
        ABMCliente abmCliente = new ABMCliente();
        Cliente cliente = new Cliente();
        Controlador control = new Controlador(abmCliente,cliente);
        
        
        control.iniciarABMClientes();
        

    }

 
}
