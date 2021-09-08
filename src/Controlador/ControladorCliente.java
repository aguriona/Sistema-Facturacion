package Controlador;

import Modelo.Cliente;
import Modelo.Conexion;
import Vista.ABMCliente;
import Vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//Todo lo comentado son metodos implementados con JDBC
public class ControladorCliente extends Conexion implements ActionListener {

    private ABMCliente abmCliente;
    private Cliente cliente;
    private VistaPrincipal principal;
    ClienteJpaController clienteJpa = new ClienteJpaController();

    //SistemaFacturacionMVC main = new SistemaFacturacionMVC();
    public ControladorCliente(ABMCliente abmCliente, Cliente cliente, VistaPrincipal principal) {

        this.abmCliente = abmCliente;
        this.cliente = cliente;
        this.abmCliente.btnAgregar.addActionListener(this);
        this.abmCliente.btnBuscar.addActionListener(this);
        this.abmCliente.btnCancelar.addActionListener(this);
        this.abmCliente.btnEliminar.addActionListener(this);
        this.abmCliente.btnGuardar.addActionListener(this);
        this.abmCliente.btnListar.addActionListener(this);
        this.abmCliente.btnModificar.addActionListener(this);
        this.principal = principal;
    }

    public void iniciarABMClientes() {
        this.abmCliente.setTitle("Administrar Cliente");
        this.abmCliente.setLocation(150, 250);
        this.abmCliente.setVisible(true);
    }

    public boolean guardarCliente(Cliente cliente) {

        try {
            clienteJpa.create(cliente);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        /* 
        JDBC 
                        
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

        /* 
        JDBC 
                        
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
        }*/
    }

    public boolean modificarCliente(Cliente cliente) {

        try {
            clienteJpa.edit(cliente);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        /*PreparedStatement ps;
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
        /*PreparedStatement ps;
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
        }*/
    }

    public boolean eliminarCliente(int idcliente) {
        try {
            clienteJpa.destroy(idcliente);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        /*
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
        /*
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
        }*/
    }

    public boolean buscarCliente(int idcliente) {

        try {
            cliente = clienteJpa.findCliente(idcliente);

            return true;
        } catch (Exception ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        /*
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
        }*/
    }

    public boolean listarClientes() {

        DefaultTableModel tabla = new DefaultTableModel();
        abmCliente.tablaClientes.setModel(tabla);
        List<Cliente> filasCliente = clienteJpa.findClienteEntities();

        try {
            tabla.addColumn("Codigo");
            tabla.addColumn("Nombre");
            tabla.addColumn("Apellido");
            tabla.addColumn("Genero");
            tabla.addColumn("Direccion");
            tabla.addColumn("Localidad");
            tabla.addColumn("CUIT");
            tabla.addColumn("Mail");
            Object[] filas = new Object[8];
            for (Cliente client : filasCliente) {
                filas[0] = client.getIdCliente();
                filas[1] = client.getNombre();
                filas[2] = client.getApellido();
                filas[3] = client.getGenero();
                filas[4] = client.getDireccion();
                filas[5] = client.getLocalidad();
                filas[6] = client.getCuit();
                filas[7] = client.getMail();
                tabla.addRow(filas);
            }

            return true;

        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

        /*
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
        }*/
    }

    public boolean agregarCliente() {
        try {
            int codigo = abmCliente.tablaClientes.getSelectedRow();
            cliente = clienteJpa.findCliente(codigo + 1);
            principal.txtNombre.setText(cliente.getNombre());
            principal.txtApellido.setText(cliente.getApellido());
            principal.txtDireccion.setText(cliente.getDireccion());
            principal.txtLocalidad.setText(cliente.getLocalidad());
            principal.txtCuit.setText(cliente.getCuit());
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
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
            // cliente.setIdCliente(0);
            //cliente.setIdCliente(Integer.parseInt(abmCliente.txtIdcliente.getText()));
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
            int idcliente = Integer.parseInt(abmCliente.txtIdcliente.getText());

            if (eliminarCliente(idcliente)) {
                JOptionPane.showMessageDialog(null, "Cliente Eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
            }

        }

        if (e.getSource() == abmCliente.btnBuscar) {

            int idcliente = Integer.parseInt(abmCliente.txtIdcliente.getText());
            if (buscarCliente(idcliente)) {

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
            if (listarClientes()) {
                JOptionPane.showMessageDialog(null, "Clientes Listados");
            } else {
                JOptionPane.showMessageDialog(null, "No se Encontro");
            }
        }

        if (e.getSource() == abmCliente.btnAgregar) {
            if (agregarCliente()) {
                JOptionPane.showMessageDialog(null, "Agregado");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }
    }

}
