
package Controlador;


import Vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class ControladorPrincipal implements ActionListener{
    
    private VistaPrincipal principal;
    private ControladorCliente contCliente;
    private ControladorArticulo contArticulo;

    public ControladorPrincipal(VistaPrincipal principal, ControladorCliente cliente, ControladorArticulo articulo ) {
        this.principal = principal; 
        this.contCliente = cliente;
        this.contArticulo = articulo;
        this.principal.btnGuardar.addActionListener(this);
        this.principal.btnIniciarArticulos.addActionListener(this);
        this.principal.btnIniciarClientes.addActionListener(this);
    }
     public void iniciarPrinicpal() {
        this.principal.setTitle("SISTEMA DE FACTURACION");
        this.principal.setLocationRelativeTo(null);
        this.principal.setVisible(true);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==principal.btnIniciarArticulos){
            contArticulo.iniciarABMArticulo();
        }
        if(e.getSource()==principal.btnIniciarClientes){
            contCliente.iniciarABMClientes();
        }
    }
    
}
