package Controlador;

import Modelo.Articulo;
import Modelo.Cliente;
import Modelo.Conexion;
import Vista.ABMArticulo;
import Vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorArticulo extends Conexion implements ActionListener {

    private ABMArticulo abmArticulo;
    private Articulo articulo;
    private VistaPrincipal principal;
    
    ArticuloJpaController articuloJPA = new ArticuloJpaController();

    public ControladorArticulo(ABMArticulo abmArticulo, Articulo articulo, VistaPrincipal principal) {
        this.abmArticulo = abmArticulo;
        this.articulo = articulo;
        this.abmArticulo.btnBuscarProd.addActionListener(this);
        this.abmArticulo.btnEliminarProd.addActionListener(this);
        this.abmArticulo.btnGuardarProd.addActionListener(this);
        this.abmArticulo.btnListarTProd.addActionListener(this);
        this.abmArticulo.btnModificarProd.addActionListener(this);
        this.abmArticulo.btnAgregarProd.addActionListener(this);
        this.principal = principal;

    }

    public void iniciarABMArticulo() {
        this.abmArticulo.setTitle("Administrar Articulo");
        //this.abmArticulo.setLocationRelativeTo(principal);
        this.abmArticulo.setLocation(1300, 250);
        this.abmArticulo.setVisible(true);
    }

    public void limpiar() {
        abmArticulo.txtCodigoProd.setText(null);
        abmArticulo.txtNombreProd.setText(null);
        abmArticulo.txtProveedorProd.setText(null);
        abmArticulo.txtPrecioCosto.setText(null);
        abmArticulo.txtPrecioVenta.setText(null);

    }

    public boolean guardarArticulo(Articulo articulo) {
        try {
            articuloJPA.create(articulo);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean modificarArticulo(Articulo articulo) {
        try {
            articuloJPA.edit(articulo);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean eliminarArticulo(int idarticulo) {
        try {
            articuloJPA.destroy(idarticulo);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean buscarArticulo(int idarticulo) {
        try {
            articulo = articuloJPA.findArticulo(idarticulo);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean listarArticulos() {
        DefaultTableModel tabla = new DefaultTableModel();
        abmArticulo.tablaProductos.setModel(tabla);
        List<Articulo> filasArticulo = articuloJPA.findArticuloEntities();

        try {
            tabla.addColumn("Codigo");
            tabla.addColumn("Nombre");
            tabla.addColumn("Proveedor");
            tabla.addColumn("Precio Costo");
            tabla.addColumn("Precio Venta");

            Object[] filas = new Object[5];

            for (Articulo art : filasArticulo) {
                filas[0] = art.getIdArticulo();
                filas[1] = art.getNombre();
                filas[2] = art.getProveedor();
                filas[3] = art.getPrecioCosto();
                filas[4] = art.getPrecioVenta();
                tabla.addRow(filas);
            }
            return true;
        } 
        catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    public boolean agregarSeleccionado(int cantidad) {
        DefaultTableModel tabla = new DefaultTableModel();
        Object[] seleccion = new Object[4];
        try{
        int codigo = abmArticulo.tablaProductos.getSelectedRow();
        articulo = articuloJPA.findArticulo(codigo + 1);

        tabla.addColumn("Descripcion");
        tabla.addColumn("Cantidad");
        tabla.addColumn("Precio Unidad");
        tabla.addColumn("Precio Total");
        principal.tablaFactura.setModel(tabla);
        
        float precio = articulo.getPrecioVenta();
        float importe = precio * cantidad; 
                
        seleccion[0] = articulo.getNombre();
        seleccion[1] = cantidad;
        seleccion[2] = precio;
        seleccion[3] = importe;

        tabla.addRow(seleccion);
        return true;
        }
        
        catch(Exception e) {
            System.err.println(e);
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == abmArticulo.btnGuardarProd) {

            articulo.setIdArticulo(Integer.parseInt(abmArticulo.txtCodigoProd.getText()));
            articulo.setNombre(abmArticulo.txtNombreProd.getText());
            articulo.setProveedor(abmArticulo.txtProveedorProd.getText());
            articulo.setPrecioCosto(Float.parseFloat(abmArticulo.txtPrecioCosto.getText()));
            articulo.setPrecioVenta(Float.parseFloat(abmArticulo.txtPrecioCosto.getText()));

            if (guardarArticulo(articulo)) {
                JOptionPane.showMessageDialog(null, "Articulo Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
            }
        }

        if (e.getSource() == abmArticulo.btnModificarProd) {

            articulo.setIdArticulo(Integer.parseInt(abmArticulo.txtCodigoProd.getText()));
            articulo.setNombre(abmArticulo.txtNombreProd.getText());
            articulo.setProveedor(abmArticulo.txtProveedorProd.getText());
            articulo.setPrecioCosto(Float.parseFloat(abmArticulo.txtPrecioCosto.getText()));
            articulo.setPrecioVenta(Float.parseFloat(abmArticulo.txtPrecioVenta.getText()));

            if (modificarArticulo(articulo)) {
                JOptionPane.showMessageDialog(null, "Articulo Modificado");

            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
            }
        }

        if (e.getSource() == abmArticulo.btnEliminarProd) {

            int idarticulo = Integer.parseInt(abmArticulo.txtCodigoProd.getText());

            if (eliminarArticulo(idarticulo)) {
                JOptionPane.showMessageDialog(null, "Articulo Eliminado");

            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
            }
        }

        if (e.getSource() == abmArticulo.btnBuscarProd) {

            int idarticulo = Integer.parseInt(abmArticulo.txtCodigoProd.getText());

            if (buscarArticulo(idarticulo)) {

                abmArticulo.txtNombreProd.setText(articulo.getNombre());
                abmArticulo.txtProveedorProd.setText(articulo.getProveedor());
                abmArticulo.txtPrecioCosto.setText(String.valueOf(articulo.getPrecioCosto()));
                abmArticulo.txtPrecioVenta.setText(String.valueOf(articulo.getPrecioVenta()));

                JOptionPane.showMessageDialog(null, "Articulo Encontrado");

            } else {
                JOptionPane.showMessageDialog(null, "Error al Buscar");
            }
        }
        if (e.getSource() == abmArticulo.btnListarTProd) {

            if (listarArticulos()) {
                JOptionPane.showMessageDialog(null, "Articulos Listados");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }
        if (e.getSource() == abmArticulo.btnAgregarProd) {
           int cantidad = Integer.parseInt(abmArticulo.txtCantidad.getText());
           if(agregarSeleccionado(cantidad)){
            JOptionPane.showMessageDialog(null, "Agregado");
           }
           else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }

    }

}
