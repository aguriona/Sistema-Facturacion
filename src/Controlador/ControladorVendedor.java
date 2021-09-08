package Controlador;


import Modelo.Vendedor;
import Vista.Login;
import Vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class ControladorVendedor implements ActionListener {

    private Vendedor vendedor;
    private Login vistaLog;
    private ControladorPrincipal principal;
    VendedorJpaController jpaVend = new VendedorJpaController();
    boolean ret;

    public ControladorVendedor(Vendedor vendedor, Login vistaLog, ControladorPrincipal principal) {
        this.vendedor = vendedor;
        this.vistaLog = vistaLog;
        this.principal = principal;
        this.vistaLog.btnIngresar.addActionListener(this);

    }
    
    public void iniciarLogin() {
        this.vistaLog.setTitle("Ingreso");
        this.vistaLog.setLocationRelativeTo(null);
        this.vistaLog.setVisible(true);
    }

    public boolean validarUsuario(String usuario, String contraseña) {

        List<Vendedor> listaVend = jpaVend.findVendedorEntities();
        try {
            for (Vendedor vend : listaVend) {

                if (vend.getNombre().equals(usuario) && vend.getContraseña().equals(contraseña)) {
                    ret = true;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            ret = false;
        }
        return ret;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLog.btnIngresar) {
            vendedor.setNombre(vistaLog.txtusuario.getText());
            vendedor.setContraseña(vistaLog.txtContraseña.getText());
            if(validarUsuario(vendedor.getNombre(), vendedor.getContraseña())){
                principal.iniciarPrinicpal();
                this.vistaLog.dispose();
               // JOptionPane.showMessageDialog(null, "Acceso Correcto");
            }
            else{
                JOptionPane.showMessageDialog(null, "Error");
            }
        }
    }

}
