package Controlador;

import Modelo.Cliente;
import Modelo.Conexion;
import Vista.ABMCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Controlador extends Conexion implements ActionListener {
    
    private ABMCliente abmCliente;
    private Cliente cliente;

    //SistemaFacturacionMVC main = new SistemaFacturacionMVC();
    public Controlador(ABMCliente abmCliente, Cliente cliente) {
        
        this.abmCliente = abmCliente;
        this.cliente = cliente;
        this.abmCliente.btnAgregar.addActionListener(this);
        this.abmCliente.btnBuscar.addActionListener(this);
        this.abmCliente.btnCancelar.addActionListener(this);
        this.abmCliente.btnEliminar.addActionListener(this);
        this.abmCliente.btnGuardar.addActionListener(this);
        this.abmCliente.btnListar.addActionListener(this);
        this.abmCliente.btnModificar.addActionListener(this);
    }
    
    public void iniciarABMClientes() {
        this.abmCliente.setTitle("Administrar Cliente");
        this.abmCliente.setLocationRelativeTo(null);
        this.abmCliente.setVisible(true);
    }
    
    public boolean guardarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection conexion = null;
        conexion = getConnection();
        String sql = "INSERT INTO cliente (idcliente, nombre, apellido, direccion, localidad, genero, cuit, mail) VALUES (?,?,?,?,?,?,?,?)";
        
        try {
            ps = conexion.prepareStatement(sql);
            
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getLocalidad());
            ps.setString(6, cliente.getGenero());
            ps.setString(7, cliente.getCuit());
            ps.setString(8, cliente.getMail());
            ps.execute();
            
            conexion.close();
            
            return true;
            
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection conexion = null;
        conexion = getConnection();
        String sql = "UPDATE cliente SET nombre=?, apellido=?, direccion=?, localidad=?, genero=?, cuit=?, mail=? WHERE idcliente=?";
        
        try {
            ps = conexion.prepareStatement(sql);
            
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getLocalidad());
            ps.setString(5, cliente.getGenero());
            ps.setString(6, cliente.getCuit());
            ps.setString(7, cliente.getMail());
            ps.setInt(8, cliente.getIdCliente());
            ps.execute();
            
            conexion.close();
            
            return true;
            
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection conexion = null;
        conexion = getConnection();
        String sql = "DELETE FROM cliente WHERE idcliente=?";
        
        try {
            ps = conexion.prepareStatement(sql);
            
            ps.setInt(1, cliente.getIdCliente());
            
            ps.execute();
            
            conexion.close();
            
            return true;
            
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean buscarCliente(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = null;
        conexion = getConnection();
        String sql = "SELECT * FROM cliente WHERE idcliente=?";
        
        try {
            ps = conexion.prepareStatement(sql);
            
            ps.setInt(1, cliente.getIdCliente());
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                cliente.setIdCliente(Integer.parseInt(rs.getString("idcliente")));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setLocalidad(rs.getString("localidad"));
                cliente.setGenero(rs.getString("genero"));
                cliente.setCuit(rs.getString("cuit"));
                cliente.setMail(rs.getString("mail"));
                
                return true;
            }
            
            return false;
            
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean listarClientes() {
        
        DefaultTableModel tabla = new DefaultTableModel();
        abmCliente.tablaClientes.setModel(tabla);
        
        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = null;
        conexion = getConnection();
        String sql = "SELECT * FROM cliente";
        
        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnas = rsmd.getColumnCount();
            
            tabla.addColumn("Codigo");
            tabla.addColumn("Nombre");
            tabla.addColumn("Apellido");
            tabla.addColumn("Genero");
            tabla.addColumn("Direccion");
            tabla.addColumn("Localidad");
            tabla.addColumn("CUIT");
            tabla.addColumn("Mail");
            
            while (rs.next()) {
                Object[] filas = new Object[columnas];
                
                for (int i = 0; i < columnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                tabla.addRow(filas);
                
                return true;
            }
            
            return false;
            
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public void limpiar() {
        abmCliente.txtIdcliente.setText(null);
        abmCliente.txtNombre.setText(null);
        abmCliente.txtApellido.setText(null);
        abmCliente.txtDireccion.setText(null);
        abmCliente.txtLocalidad.setText(null);
        abmCliente.cmbGenero.setSelectedIndex(0);
        abmCliente.txtCuit.setText(null);
        abmCliente.txtMail.setText(null);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == abmCliente.btnGuardar) {
            cliente.setIdCliente(Integer.parseInt(abmCliente.txtIdcliente.getText()));
            cliente.setNombre(abmCliente.txtNombre.getText());
            cliente.setApellido(abmCliente.txtApellido.getText());
            cliente.setDireccion(abmCliente.txtDireccion.getText());
            cliente.setLocalidad(abmCliente.txtLocalidad.getText());
            cliente.setGenero(abmCliente.cmbGenero.getSelectedItem().toString());
            cliente.setCuit(abmCliente.txtCuit.getText());
            cliente.setMail(abmCliente.txtMail.getText());
            if (guardarCliente(cliente)) {
                JOptionPane.showMessageDialog(null, "Cliente Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
            }
            
        }
        
        if (e.getSource() == abmCliente.btnModificar) {
            // cliente.setIdCliente(Integer.parseInt(abmCliente.txtIdcliente.getText()));
            cliente.setNombre(abmCliente.txtNombre.getText());
            cliente.setApellido(abmCliente.txtApellido.getText());
            cliente.setDireccion(abmCliente.txtDireccion.getText());
            cliente.setLocalidad(abmCliente.txtLocalidad.getText());
            cliente.setGenero(abmCliente.cmbGenero.getSelectedItem().toString());
            cliente.setCuit(abmCliente.txtCuit.getText());
            cliente.setMail(abmCliente.txtMail.getText());
            if (modificarCliente(cliente)) {
                JOptionPane.showMessageDialog(null, "Cliente Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
            }
            
        }
        
        if (e.getSource() == abmCliente.btnEliminar) {
            cliente.setIdCliente(Integer.parseInt(abmCliente.txtIdcliente.getText()));
            
            if (eliminarCliente(cliente)) {
                JOptionPane.showMessageDialog(null, "Cliente Eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
            }
            
        }
        
        if (e.getSource() == abmCliente.btnBuscar) {
            cliente.setIdCliente(Integer.parseInt(abmCliente.txtIdcliente.getText()));
            
            if (buscarCliente(cliente)) {
                //abmCliente.txtIdcliente.setText(String.valueOf(cliente.getIdCliente()));
                abmCliente.txtNombre.setText(cliente.getNombre());
                abmCliente.txtApellido.setText(cliente.getApellido());
                abmCliente.txtDireccion.setText(cliente.getDireccion());
                abmCliente.txtLocalidad.setText(cliente.getLocalidad());
                abmCliente.cmbGenero.setSelectedItem(cliente.getGenero());
                abmCliente.txtCuit.setText(cliente.getCuit());
                abmCliente.txtMail.setText(cliente.getMail());
                
                JOptionPane.showMessageDialog(null, "Cliente Encontrado");
                
            } else {
                JOptionPane.showMessageDialog(null, "No se Encontro");
            }
            
        }
        
        if (e.getSource() == abmCliente.btnListar) {
            if (listarClientes()){
               JOptionPane.showMessageDialog(null, "Clientes Listados"); 
            }
            else {
                JOptionPane.showMessageDialog(null, "No se Encontro");
            }
        }
        
    }
    
}
