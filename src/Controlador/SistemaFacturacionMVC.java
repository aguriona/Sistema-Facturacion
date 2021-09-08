/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.Articulo;
import Modelo.Vendedor;
import Vista.ABMCliente;
import Vista.ABMArticulo;
import Vista.Login;
import Vista.VistaPrincipal;
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
        VistaPrincipal principal = new VistaPrincipal();
        Login login = new Login();
        Cliente cliente = new Cliente();
        Vendedor vendedor = new Vendedor();
        Articulo articulo = new Articulo();
        ABMCliente abmCliente = new ABMCliente();
        ABMArticulo abmArticulo = new ABMArticulo();
        
        ControladorArticulo controlArticulo = new ControladorArticulo(abmArticulo, articulo, principal);
        ControladorCliente controlCliente = new ControladorCliente(abmCliente, cliente, principal);
        ControladorPrincipal controlPrincipal = new ControladorPrincipal(principal, controlCliente , controlArticulo);
        
        ControladorVendedor controlVend = new ControladorVendedor(vendedor, login,controlPrincipal);
        
        controlVend.iniciarLogin();
        //controlCliente.iniciarABMClientes();
        

    }

}
