/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Empleados;


import BDMySQL.ConexionArea;
import BDMySQL.ConexionCargo;
import BDMySQL.ConexionDatos;
import BDMySQL.ConexionEmpleados;
import BDMySQL.ConexionNomina;
import BDMySQL.ConexionPago;
import BDMySQL.ConexionUsuarios;
import BDMySQL.conexion;


import Bod.BOD;

import clases.RenderImagen;

import clases.areas;
import clases.cargo;
import clases.datos;
import clases.empleado;
import clases.nomina;
import clases.pago;
import clases.usuarios;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author anoni
 */
public class KrokEmple extends javax.swing.JFrame {
    cargo cr = new cargo();
    ConexionCargo daoC=new ConexionCargo();
    DefaultTableModel modeloCargo=new DefaultTableModel();
    
    
    areas ar = new areas();
    ConexionArea daoA=new ConexionArea();
    DefaultTableModel modeloArea=new DefaultTableModel();
    
    empleado em = new empleado();
    ConexionEmpleados daoE=new ConexionEmpleados();
    DefaultTableModel modeloEmpleados=new DefaultTableModel();
    
    nomina no=new nomina();
    ConexionNomina daoN=new ConexionNomina();
    DefaultTableModel modeloNomina=new DefaultTableModel();
    
     pago pa=new pago();
    ConexionPago daoP=new ConexionPago();
    DefaultTableModel modeloPago=new DefaultTableModel();
    
    datos da=new datos();
    ConexionDatos daoD=new ConexionDatos();
    DefaultTableModel modeloDatos=new DefaultTableModel();
    
    usuarios usu=new usuarios();
    ConexionUsuarios daoU=new ConexionUsuarios();
    DefaultTableModel modeloUsuario=new DefaultTableModel();
    
    
    
 
    String Ruta = "";
    String valor="1";

    /**
     * Creates new form KrokEmple
     */
    public KrokEmple() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/img/cocodrilo.png")).getImage());
     
        this.setLocationRelativeTo(null);
        this.setExtendedState(KrokEmple.MAXIMIZED_BOTH);
         listar();
         listarArea();
         listarEmpleado();
         listarNomina();
         listarPagos();
         listarUsuarios();
          modeloDatos.addColumn("ID");
        modeloDatos.addColumn("Empresa");
        modeloDatos.addColumn("RUC");
        modeloDatos.addColumn("Razon Social");
        modeloDatos.addColumn("Telefono");
        modeloDatos.addColumn("Direccion");
        modeloDatos.addColumn("Correo");
        modeloDatos.addColumn("Imagen");
         ListarDatos();
         EnviarAreas.setEnabled(false);
         EnviarCargos.setEnabled(false);

         
    }
    
    private void ListarDatos(){
        tabladatos.setDefaultRenderer(Object.class, new RenderImagen());
        
        ArrayList datos;
        Object Datos[] =new Object[8];
        datos=daoD.Listar();
        if(datos!=null){
            for(int i=0;i<datos.size();i++){
                da = (datos) datos.get(i);
                Datos[0]=String.valueOf(da.getId());
                Datos[1]=da.getNombre();
                Datos[2]=da.getRUC();
                Datos[3]=da.getRasonS();
                Datos[4]=da.getTelefono();
                Datos[5]=da.getDireccion();
                Datos[6]=da.getCorreo();
                try{
                    byte[] imagen =da.getImagen();
                    BufferedImage buffer=null;
                    InputStream inputstream=new ByteArrayInputStream(imagen);
                    buffer=ImageIO.read(inputstream);
                    ImageIcon incono=new ImageIcon(buffer.getScaledInstance(60, 60, 0));
                    Datos[7]=new JLabel(incono);
                }catch (Exception e){
                    Datos[7]=new JLabel("SIN IMAGEN");  
                }
                modeloDatos.addRow(Datos);
            }
            tabladatos.setModel(modeloDatos);
            tabladatos.setRowHeight(60);
            tabladatos.getColumnModel().getColumn(0).setPreferredWidth(60);
            tabladatos.getColumnModel().getColumn(1).setPreferredWidth(60);
            tabladatos.getColumnModel().getColumn(2).setPreferredWidth(60);
            tabladatos.getColumnModel().getColumn(3).setPreferredWidth(60);
            tabladatos.getColumnModel().getColumn(4).setPreferredWidth(60);
            tabladatos.getColumnModel().getColumn(5).setPreferredWidth(60);
            tabladatos.getColumnModel().getColumn(6).setPreferredWidth(60);
            tabladatos.getColumnModel().getColumn(7).setPreferredWidth(60);
        }
    }
    
    
    
    
    
    
    
    
    private void listar(){
        List<cargo> lista=daoC.listar();
        modeloCargo=(DefaultTableModel)  tablacargos.getModel();
        Object[] ob=new Object[3];
        for(int i=0;i<lista.size();i++){
            ob[0]=lista.get(i).getId();
            ob[1]=lista.get(i).getNombre();
            ob[2]=lista.get(i).getPago();
            modeloCargo.addRow(ob);   
        }
        tablacargos.setModel(modeloCargo);
    }
    private void listarArea(){
        List<areas> lista=daoA.listar();
        modeloArea=(DefaultTableModel)  tablaArea.getModel();
        Object[] ob=new Object[2];
        for(int i=0;i<lista.size();i++){
            ob[0]=lista.get(i).getIdarea();
            ob[1]=lista.get(i).getNomArea();
            modeloArea.addRow(ob);
        }
        tablaArea.setModel(modeloArea);
    }
    
    private void listarEmpleado(){
            try{
                DefaultTableModel modelo;
                modelo=daoE.listar();
                tablaempleado.setModel(modelo);
            }catch (Exception e){      
        }
    }
    private void listarNomina(){
            try{
                DefaultTableModel modelo;
                modelo=daoN.listar();
               tablanomina.setModel(modelo);
            }catch (Exception e){      
        }
    }
    private void listarPagos(){
        try{
            DefaultTableModel modelo;
            modelo=daoP.listar();
            tablaPago.setModel(modelo);
        }catch(Exception e){
            
        }
    }
    private void listarUsuarios(){
        List<usuarios> lista=daoU.Listar();
        modeloUsuario=(DefaultTableModel) usuarios.getModel();
        Object[] ob=new Object[5];
        for(int i=0;i<lista.size();i++){
            ob[0]=lista.get(i).getIdUser();
            ob[1]=lista.get(i).getNombre();
            ob[2]=lista.get(i).getUsuario();
            ob[3]=lista.get(i).getPassword();
            ob[4]=lista.get(i).getTipo();
            modeloUsuario.addRow(ob);
        }
        usuarios.setModel(modeloUsuario);
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        panel = new javax.swing.JTabbedPane();
        psuarios = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        IDusu = new javax.swing.JTextField();
        USU = new javax.swing.JTextField();
        CBOusu = new javax.swing.JComboBox<>();
        PW = new javax.swing.JPasswordField();
        jLabel59 = new javax.swing.JLabel();
        NombreUsu = new javax.swing.JTextField();
        RegisUsu = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        usuarios = new javax.swing.JTable();
        ModiUsu = new javax.swing.JButton();
        ModiUsu1 = new javax.swing.JButton();
        pcargo = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        TxID = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        TxCargo = new javax.swing.JTextField();
        RegistrarCargos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablacargos = new javax.swing.JTable();
        ModificarCargos = new javax.swing.JButton();
        EliminarCargos = new javax.swing.JButton();
        BuscarCargos = new javax.swing.JButton();
        EnviarCargos = new javax.swing.JButton();
        PagoCargo = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        LimpiarCa = new javax.swing.JButton();
        parea = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        TxIDarea = new javax.swing.JTextField();
        TxNombreArea = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaArea = new javax.swing.JTable();
        RegistrarArea = new javax.swing.JButton();
        ModificarAreas = new javax.swing.JButton();
        EliminarAreas = new javax.swing.JButton();
        BuscarArea = new javax.swing.JButton();
        EnviarAreas = new javax.swing.JButton();
        pempleados = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        TxIDareaEmple = new javax.swing.JTextField();
        TxAreaEmple = new javax.swing.JTextField();
        EmpleAreaBuscar = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        TxEmpleado = new javax.swing.JTextField();
        TxNombreEmple = new javax.swing.JTextField();
        TxApellidoEmple = new javax.swing.JTextField();
        TxDui = new javax.swing.JTextField();
        TxTelefono = new javax.swing.JTextField();
        TxCorreo = new javax.swing.JTextField();
        TipoDoc = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        TxIDcargoEmple = new javax.swing.JTextField();
        TxCargoEmple = new javax.swing.JTextField();
        EmpleCargosBuscar = new javax.swing.JButton();
        RegistrarEmple = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaempleado = new javax.swing.JTable();
        ModificarEmpleados = new javax.swing.JButton();
        BuscarArea1 = new javax.swing.JButton();
        EliminarEmpleado = new javax.swing.JButton();
        Pnomina = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        TxDocumentoNomina = new javax.swing.JTextField();
        BuscarNomina = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        TxNombreNomina = new javax.swing.JTextField();
        TxApellidoNomina = new javax.swing.JTextField();
        TxIdcargoNomina = new javax.swing.JTextField();
        TxCargoNomina = new javax.swing.JTextField();
        IDemple = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        PagoNomina = new javax.swing.JTextField();
        TotalPagar = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        TxCantidadTrabajo = new javax.swing.JTextField();
        TotalNomina = new javax.swing.JTextField();
        dateFecha = new com.toedter.calendar.JDateChooser();
        CalcularNomina = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        idnomina = new javax.swing.JTextField();
        RegistrarNomina = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablanomina = new javax.swing.JTable();
        ModificarNomina = new javax.swing.JButton();
        buscarNomina = new javax.swing.JButton();
        EliminarNomina = new javax.swing.JButton();
        Ppago = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        IDpago = new javax.swing.JTextField();
        TotalPago = new javax.swing.JTextField();
        CalculaPago = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        IDemplePago = new javax.swing.JTextField();
        NombPagoEmple = new javax.swing.JTextField();
        ApePagoEmple = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        IDcargoPago = new javax.swing.JTextField();
        CargoPago = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        DocPagoEmple = new javax.swing.JTextField();
        BuscarNominaPago = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        fecha1 = new com.toedter.calendar.JDateChooser();
        fecha2 = new com.toedter.calendar.JDateChooser();
        RegistrarPago = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaPago = new javax.swing.JTable();
        ModificarPago = new javax.swing.JButton();
        pdf = new javax.swing.JButton();
        EliminarPago1 = new javax.swing.JButton();
        NuevoPago = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        TxEmpresa = new javax.swing.JTextField();
        RazonSocial = new javax.swing.JTextField();
        DirecEmpresa = new javax.swing.JTextField();
        TelefonoEmpresa = new javax.swing.JTextField();
        CorreoEmpre = new javax.swing.JTextField();
        lblImagen = new javax.swing.JLabel();
        RegisEmpre = new javax.swing.JButton();
        CargarIMG = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        RUC = new javax.swing.JTextField();
        IdDato = new javax.swing.JLabel();
        Editar = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabladatos = new javax.swing.JTable();

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Bodega");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(176, 196, 222));
        jPanel1.setPreferredSize(new java.awt.Dimension(384, 837));

        jPanel4.setBackground(new java.awt.Color(6, 47, 64));
        jPanel4.setPreferredSize(new java.awt.Dimension(1253, 130));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(176, 196, 222));

        jPanel6.setBackground(new java.awt.Color(144, 169, 191));
        jPanel6.setPreferredSize(new java.awt.Dimension(263, 63));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Empleados");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(144, 169, 191));
        jPanel7.setPreferredSize(new java.awt.Dimension(263, 63));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Registros de pruductos");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(144, 169, 191));
        jPanel8.setPreferredSize(new java.awt.Dimension(263, 63));

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Bodega");
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(144, 169, 191));
        jPanel9.setAutoscrolls(true);
        jPanel9.setPreferredSize(new java.awt.Dimension(263, 63));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Sala de ventas");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(144, 169, 191));
        jPanel10.setPreferredSize(new java.awt.Dimension(263, 63));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Asistencia");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 44, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(144, 169, 191));
        jPanel11.setPreferredSize(new java.awt.Dimension(263, 63));

        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Salir y cerrar sesion");
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 44, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBackground(new java.awt.Color(6, 47, 64));
        jPanel2.setPreferredSize(new java.awt.Dimension(1253, 130));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/DatosEmple.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(101, 101, 101))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setForeground(new java.awt.Color(0, 0, 0));
        panel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N

        psuarios.setBackground(new java.awt.Color(255, 255, 255));

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Registro de usuarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel52.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("ID:");

        jLabel54.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Nombre:");

        jLabel55.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Password:");

        jLabel56.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Tipo:");

        IDusu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDusuActionPerformed(evt);
            }
        });

        CBOusu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Empleado", "", "", "" }));

        jLabel59.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Usuario:");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(USU, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(PW, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(IDusu, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(NombreUsu, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(CBOusu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IDusu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(NombreUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(USU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBOusu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        RegisUsu.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        RegisUsu.setForeground(new java.awt.Color(0, 0, 0));
        RegisUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        RegisUsu.setText("Registrar");
        RegisUsu.setContentAreaFilled(false);
        RegisUsu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        RegisUsu.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        RegisUsu.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar2.png"))); // NOI18N
        RegisUsu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        RegisUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisUsuActionPerformed(evt);
            }
        });

        usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Usuario", "Password", "Tipo"
            }
        ));
        usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usuariosMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(usuarios);

        ModiUsu.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        ModiUsu.setForeground(new java.awt.Color(0, 0, 0));
        ModiUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar1.png"))); // NOI18N
        ModiUsu.setText("Modificar");
        ModiUsu.setContentAreaFilled(false);
        ModiUsu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ModiUsu.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar1.png"))); // NOI18N
        ModiUsu.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar2.png"))); // NOI18N
        ModiUsu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ModiUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModiUsuActionPerformed(evt);
            }
        });

        ModiUsu1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        ModiUsu1.setForeground(new java.awt.Color(0, 0, 0));
        ModiUsu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/limpiar1.png"))); // NOI18N
        ModiUsu1.setText("Limpiar");
        ModiUsu1.setContentAreaFilled(false);
        ModiUsu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ModiUsu1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/limpiar1.png"))); // NOI18N
        ModiUsu1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/limpiar2.png"))); // NOI18N
        ModiUsu1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ModiUsu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModiUsu1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout psuariosLayout = new javax.swing.GroupLayout(psuarios);
        psuarios.setLayout(psuariosLayout);
        psuariosLayout.setHorizontalGroup(
            psuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(psuariosLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(psuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(psuariosLayout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(psuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(RegisUsu, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                            .addComponent(ModiUsu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(ModiUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(548, Short.MAX_VALUE))
        );
        psuariosLayout.setVerticalGroup(
            psuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(psuariosLayout.createSequentialGroup()
                .addGroup(psuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(psuariosLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(psuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ModiUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RegisUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ModiUsu1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(psuariosLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(241, Short.MAX_VALUE))
        );

        panel.addTab("Usuarios", psuarios);

        pcargo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("ID:");

        TxID.setBackground(new java.awt.Color(255, 255, 255));
        TxID.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Cargo:");

        TxCargo.setBackground(new java.awt.Color(255, 255, 255));
        TxCargo.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N

        RegistrarCargos.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        RegistrarCargos.setForeground(new java.awt.Color(0, 0, 0));
        RegistrarCargos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        RegistrarCargos.setText("Registrar");
        RegistrarCargos.setContentAreaFilled(false);
        RegistrarCargos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        RegistrarCargos.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        RegistrarCargos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar2.png"))); // NOI18N
        RegistrarCargos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        RegistrarCargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarCargosActionPerformed(evt);
            }
        });

        tablacargos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cargo", "Pago"
            }
        ));
        tablacargos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablacargosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablacargos);

        ModificarCargos.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        ModificarCargos.setForeground(new java.awt.Color(0, 0, 0));
        ModificarCargos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar1.png"))); // NOI18N
        ModificarCargos.setText("Modificar");
        ModificarCargos.setContentAreaFilled(false);
        ModificarCargos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ModificarCargos.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar1.png"))); // NOI18N
        ModificarCargos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar2.png"))); // NOI18N
        ModificarCargos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ModificarCargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarCargosActionPerformed(evt);
            }
        });

        EliminarCargos.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        EliminarCargos.setForeground(new java.awt.Color(0, 0, 0));
        EliminarCargos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar1.png"))); // NOI18N
        EliminarCargos.setText("Eliminar");
        EliminarCargos.setContentAreaFilled(false);
        EliminarCargos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EliminarCargos.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar1.png"))); // NOI18N
        EliminarCargos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar2.png"))); // NOI18N
        EliminarCargos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EliminarCargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarCargosActionPerformed(evt);
            }
        });

        BuscarCargos.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        BuscarCargos.setForeground(new java.awt.Color(0, 0, 0));
        BuscarCargos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar1.png"))); // NOI18N
        BuscarCargos.setText("Buscar");
        BuscarCargos.setContentAreaFilled(false);
        BuscarCargos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BuscarCargos.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar1.png"))); // NOI18N
        BuscarCargos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar2.png"))); // NOI18N
        BuscarCargos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BuscarCargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarCargosActionPerformed(evt);
            }
        });

        EnviarCargos.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        EnviarCargos.setForeground(new java.awt.Color(0, 0, 0));
        EnviarCargos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/enviar1.png"))); // NOI18N
        EnviarCargos.setText("Enviar");
        EnviarCargos.setContentAreaFilled(false);
        EnviarCargos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EnviarCargos.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar2.png"))); // NOI18N
        EnviarCargos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/enviar2.png"))); // NOI18N
        EnviarCargos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EnviarCargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarCargosActionPerformed(evt);
            }
        });

        PagoCargo.setBackground(new java.awt.Color(255, 255, 255));
        PagoCargo.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N

        jLabel35.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Pago:");

        LimpiarCa.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        LimpiarCa.setForeground(new java.awt.Color(0, 0, 0));
        LimpiarCa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/limpiar1.png"))); // NOI18N
        LimpiarCa.setText("Limpiar");
        LimpiarCa.setContentAreaFilled(false);
        LimpiarCa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LimpiarCa.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/limpiar1.png"))); // NOI18N
        LimpiarCa.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/limpiar2.png"))); // NOI18N
        LimpiarCa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        LimpiarCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarCaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pcargoLayout = new javax.swing.GroupLayout(pcargo);
        pcargo.setLayout(pcargoLayout);
        pcargoLayout.setHorizontalGroup(
            pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcargoLayout.createSequentialGroup()
                .addGroup(pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pcargoLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BuscarCargos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(RegistrarCargos, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pcargoLayout.createSequentialGroup()
                                .addComponent(ModificarCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(EliminarCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pcargoLayout.createSequentialGroup()
                                .addComponent(LimpiarCa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(EnviarCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pcargoLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PagoCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxCargo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(134, 134, 134)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(392, Short.MAX_VALUE))
        );
        pcargoLayout.setVerticalGroup(
            pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcargoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pcargoLayout.createSequentialGroup()
                        .addGroup(pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(TxID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(TxCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PagoCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))
                        .addGap(50, 50, 50)
                        .addGroup(pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RegistrarCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ModificarCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EliminarCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BuscarCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EnviarCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LimpiarCa, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(327, Short.MAX_VALUE))
        );

        panel.addTab("Cargos", pcargo);

        parea.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("ID:");

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Nombre:");

        TxIDarea.setBackground(new java.awt.Color(255, 255, 255));
        TxIDarea.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N

        TxNombreArea.setBackground(new java.awt.Color(255, 255, 255));
        TxNombreArea.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N

        tablaArea.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Area"
            }
        ));
        tablaArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAreaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaArea);

        RegistrarArea.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        RegistrarArea.setForeground(new java.awt.Color(0, 0, 0));
        RegistrarArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        RegistrarArea.setText("Registrar");
        RegistrarArea.setContentAreaFilled(false);
        RegistrarArea.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        RegistrarArea.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        RegistrarArea.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar2.png"))); // NOI18N
        RegistrarArea.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        RegistrarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarAreaActionPerformed(evt);
            }
        });

        ModificarAreas.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        ModificarAreas.setForeground(new java.awt.Color(0, 0, 0));
        ModificarAreas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar1.png"))); // NOI18N
        ModificarAreas.setText("Modificar");
        ModificarAreas.setContentAreaFilled(false);
        ModificarAreas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ModificarAreas.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar1.png"))); // NOI18N
        ModificarAreas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar2.png"))); // NOI18N
        ModificarAreas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ModificarAreas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarAreasActionPerformed(evt);
            }
        });

        EliminarAreas.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        EliminarAreas.setForeground(new java.awt.Color(0, 0, 0));
        EliminarAreas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar1.png"))); // NOI18N
        EliminarAreas.setText("Eliminar");
        EliminarAreas.setContentAreaFilled(false);
        EliminarAreas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EliminarAreas.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar1.png"))); // NOI18N
        EliminarAreas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar2.png"))); // NOI18N
        EliminarAreas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EliminarAreas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarAreasActionPerformed(evt);
            }
        });

        BuscarArea.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        BuscarArea.setForeground(new java.awt.Color(0, 0, 0));
        BuscarArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar1.png"))); // NOI18N
        BuscarArea.setText("Buscar");
        BuscarArea.setContentAreaFilled(false);
        BuscarArea.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BuscarArea.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar1.png"))); // NOI18N
        BuscarArea.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar2.png"))); // NOI18N
        BuscarArea.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BuscarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarAreaActionPerformed(evt);
            }
        });

        EnviarAreas.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        EnviarAreas.setForeground(new java.awt.Color(0, 0, 0));
        EnviarAreas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/enviar1.png"))); // NOI18N
        EnviarAreas.setText("Enviar");
        EnviarAreas.setContentAreaFilled(false);
        EnviarAreas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EnviarAreas.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar2.png"))); // NOI18N
        EnviarAreas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/enviar2.png"))); // NOI18N
        EnviarAreas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EnviarAreas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarAreasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pareaLayout = new javax.swing.GroupLayout(parea);
        parea.setLayout(pareaLayout);
        pareaLayout.setHorizontalGroup(
            pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pareaLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pareaLayout.createSequentialGroup()
                        .addGroup(pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxNombreArea, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                            .addComponent(TxIDarea))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pareaLayout.createSequentialGroup()
                        .addGroup(pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BuscarArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pareaLayout.createSequentialGroup()
                                .addComponent(RegistrarArea, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ModificarAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EliminarAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EnviarAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(482, 482, 482))
        );
        pareaLayout.setVerticalGroup(
            pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pareaLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pareaLayout.createSequentialGroup()
                        .addGroup(pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(TxIDarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TxNombreArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(43, 43, 43)
                        .addGroup(pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RegistrarArea, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ModificarAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EliminarAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EnviarAreas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BuscarArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(98, 98, 98)))
                .addContainerGap(433, Short.MAX_VALUE))
        );

        panel.addTab("Area", parea);

        pempleados.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Area", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel12.setForeground(new java.awt.Color(0, 0, 0));
        jPanel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("ID:");

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Area:");

        EmpleAreaBuscar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        EmpleAreaBuscar.setForeground(new java.awt.Color(0, 0, 0));
        EmpleAreaBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar1.png"))); // NOI18N
        EmpleAreaBuscar.setText("Buscar area");
        EmpleAreaBuscar.setContentAreaFilled(false);
        EmpleAreaBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        EmpleAreaBuscar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar1.png"))); // NOI18N
        EmpleAreaBuscar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar2.png"))); // NOI18N
        EmpleAreaBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EmpleAreaBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmpleAreaBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxIDareaEmple)
                    .addComponent(TxAreaEmple)
                    .addComponent(EmpleAreaBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxIDareaEmple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxAreaEmple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EmpleAreaBuscar)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Empleados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("ID:");

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Nombre:");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Apellido:");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tipo doc:");

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Doc:");

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Telefono:");

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Correo:");

        TipoDoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DUI", "Pasaporte", "NIE" }));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxNombreEmple)
                            .addComponent(TxApellidoEmple)
                            .addComponent(TxDui)
                            .addComponent(TxTelefono)
                            .addComponent(TxCorreo)
                            .addComponent(TipoDoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxNombreEmple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxApellidoEmple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxDui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Cargo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel14.setForeground(new java.awt.Color(0, 0, 0));
        jPanel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("ID:");

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Cargo:");

        EmpleCargosBuscar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        EmpleCargosBuscar.setForeground(new java.awt.Color(0, 0, 0));
        EmpleCargosBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar1.png"))); // NOI18N
        EmpleCargosBuscar.setText("Buscar cargo");
        EmpleCargosBuscar.setContentAreaFilled(false);
        EmpleCargosBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        EmpleCargosBuscar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar1.png"))); // NOI18N
        EmpleCargosBuscar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar2.png"))); // NOI18N
        EmpleCargosBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EmpleCargosBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmpleCargosBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxIDcargoEmple)
                    .addComponent(TxCargoEmple)
                    .addComponent(EmpleCargosBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxIDcargoEmple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxCargoEmple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmpleCargosBuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        RegistrarEmple.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        RegistrarEmple.setForeground(new java.awt.Color(0, 0, 0));
        RegistrarEmple.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        RegistrarEmple.setText("Registrar");
        RegistrarEmple.setContentAreaFilled(false);
        RegistrarEmple.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        RegistrarEmple.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        RegistrarEmple.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar2.png"))); // NOI18N
        RegistrarEmple.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        RegistrarEmple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarEmpleActionPerformed(evt);
            }
        });

        tablaempleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaempleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaempleadoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tablaempleadoMouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(tablaempleado);

        ModificarEmpleados.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        ModificarEmpleados.setForeground(new java.awt.Color(0, 0, 0));
        ModificarEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar1.png"))); // NOI18N
        ModificarEmpleados.setText("Modificar");
        ModificarEmpleados.setContentAreaFilled(false);
        ModificarEmpleados.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ModificarEmpleados.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar1.png"))); // NOI18N
        ModificarEmpleados.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar2.png"))); // NOI18N
        ModificarEmpleados.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ModificarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarEmpleadosActionPerformed(evt);
            }
        });

        BuscarArea1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        BuscarArea1.setForeground(new java.awt.Color(0, 0, 0));
        BuscarArea1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar1.png"))); // NOI18N
        BuscarArea1.setText("Buscar");
        BuscarArea1.setContentAreaFilled(false);
        BuscarArea1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BuscarArea1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar1.png"))); // NOI18N
        BuscarArea1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar2.png"))); // NOI18N
        BuscarArea1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BuscarArea1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarArea1ActionPerformed(evt);
            }
        });

        EliminarEmpleado.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        EliminarEmpleado.setForeground(new java.awt.Color(0, 0, 0));
        EliminarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar1.png"))); // NOI18N
        EliminarEmpleado.setText("Eliminar");
        EliminarEmpleado.setContentAreaFilled(false);
        EliminarEmpleado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EliminarEmpleado.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar1.png"))); // NOI18N
        EliminarEmpleado.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar2.png"))); // NOI18N
        EliminarEmpleado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EliminarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pempleadosLayout = new javax.swing.GroupLayout(pempleados);
        pempleados.setLayout(pempleadosLayout);
        pempleadosLayout.setHorizontalGroup(
            pempleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pempleadosLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pempleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1059, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pempleadosLayout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pempleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pempleadosLayout.createSequentialGroup()
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pempleadosLayout.createSequentialGroup()
                                .addComponent(RegistrarEmple, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ModificarEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BuscarArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(EliminarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(245, Short.MAX_VALUE))
        );
        pempleadosLayout.setVerticalGroup(
            pempleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pempleadosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pempleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pempleadosLayout.createSequentialGroup()
                        .addGroup(pempleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(22, 22, 22)
                        .addGroup(pempleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RegistrarEmple, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ModificarEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BuscarArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EliminarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(245, Short.MAX_VALUE))
        );

        panel.addTab("Empleados", pempleados);

        Pnomina.setBackground(new java.awt.Color(255, 255, 255));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Datos Empleados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel15.setForeground(new java.awt.Color(0, 0, 0));
        jPanel15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Documento:");

        BuscarNomina.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        BuscarNomina.setForeground(new java.awt.Color(0, 0, 0));
        BuscarNomina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BUSCARpeque.png"))); // NOI18N
        BuscarNomina.setContentAreaFilled(false);
        BuscarNomina.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BuscarNomina.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BUSCARpeque.png"))); // NOI18N
        BuscarNomina.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BUSCARpeque1.png"))); // NOI18N
        BuscarNomina.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        BuscarNomina.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BuscarNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarNominaActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Nombre:");

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Apellido:");

        jLabel28.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Id cargo:");

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Cargo:");

        jLabel34.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("ID Emple:");

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Pago:   ");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TxIdcargoNomina))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxDocumentoNomina)
                            .addComponent(TxApellidoNomina)
                            .addComponent(TxNombreNomina, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(IDemple, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(TxCargoNomina, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(PagoNomina, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))))
                .addGap(18, 18, 18)
                .addComponent(BuscarNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(BuscarNomina, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxDocumentoNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDemple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxNombreNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxApellidoNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxIdcargoNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxCargoNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PagoNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        TotalPagar.setBackground(new java.awt.Color(255, 255, 255));
        TotalPagar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Datos nomina", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Fecha:");

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Cant. de trabajo:");

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Total pagar:");

        TotalNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalNominaActionPerformed(evt);
            }
        });

        CalcularNomina.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        CalcularNomina.setForeground(new java.awt.Color(0, 0, 0));
        CalcularNomina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calcula1.png"))); // NOI18N
        CalcularNomina.setText("Calcular");
        CalcularNomina.setContentAreaFilled(false);
        CalcularNomina.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CalcularNomina.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calcula1.png"))); // NOI18N
        CalcularNomina.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calcular2.png"))); // NOI18N
        CalcularNomina.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CalcularNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalcularNominaActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("ID nomina:");

        javax.swing.GroupLayout TotalPagarLayout = new javax.swing.GroupLayout(TotalPagar);
        TotalPagar.setLayout(TotalPagarLayout);
        TotalPagarLayout.setHorizontalGroup(
            TotalPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TotalPagarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TotalPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TotalPagarLayout.createSequentialGroup()
                        .addGroup(TotalPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(TotalPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TotalPagarLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(TotalPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TxCantidadTrabajo, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                    .addComponent(TotalNomina)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TotalPagarLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(TotalPagarLayout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idnomina, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(CalcularNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        TotalPagarLayout.setVerticalGroup(
            TotalPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TotalPagarLayout.createSequentialGroup()
                .addGroup(TotalPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TotalPagarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TotalPagarLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idnomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(TotalPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(TotalPagarLayout.createSequentialGroup()
                        .addGroup(TotalPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(TotalPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxCantidadTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(CalcularNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TotalPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        RegistrarNomina.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        RegistrarNomina.setForeground(new java.awt.Color(0, 0, 0));
        RegistrarNomina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        RegistrarNomina.setText("Registrar");
        RegistrarNomina.setContentAreaFilled(false);
        RegistrarNomina.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        RegistrarNomina.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        RegistrarNomina.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar2.png"))); // NOI18N
        RegistrarNomina.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        RegistrarNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarNominaActionPerformed(evt);
            }
        });

        tablanomina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablanomina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablanominaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablanomina);

        ModificarNomina.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        ModificarNomina.setForeground(new java.awt.Color(0, 0, 0));
        ModificarNomina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar1.png"))); // NOI18N
        ModificarNomina.setText("Modificar");
        ModificarNomina.setContentAreaFilled(false);
        ModificarNomina.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ModificarNomina.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar1.png"))); // NOI18N
        ModificarNomina.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar2.png"))); // NOI18N
        ModificarNomina.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ModificarNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarNominaActionPerformed(evt);
            }
        });

        buscarNomina.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        buscarNomina.setForeground(new java.awt.Color(0, 0, 0));
        buscarNomina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar1.png"))); // NOI18N
        buscarNomina.setText("Buscar");
        buscarNomina.setContentAreaFilled(false);
        buscarNomina.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscarNomina.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar1.png"))); // NOI18N
        buscarNomina.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar2.png"))); // NOI18N
        buscarNomina.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buscarNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarNominaActionPerformed(evt);
            }
        });

        EliminarNomina.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        EliminarNomina.setForeground(new java.awt.Color(0, 0, 0));
        EliminarNomina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar1.png"))); // NOI18N
        EliminarNomina.setText("Eliminar");
        EliminarNomina.setContentAreaFilled(false);
        EliminarNomina.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EliminarNomina.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar1.png"))); // NOI18N
        EliminarNomina.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar2.png"))); // NOI18N
        EliminarNomina.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EliminarNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarNominaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PnominaLayout = new javax.swing.GroupLayout(Pnomina);
        Pnomina.setLayout(PnominaLayout);
        PnominaLayout.setHorizontalGroup(
            PnominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnominaLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(PnominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 995, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnominaLayout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PnominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PnominaLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(RegistrarNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ModificarNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buscarNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(EliminarNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PnominaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(291, Short.MAX_VALUE))
        );
        PnominaLayout.setVerticalGroup(
            PnominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnominaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnominaLayout.createSequentialGroup()
                        .addComponent(TotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(PnominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RegistrarNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ModificarNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buscarNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EliminarNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(240, Short.MAX_VALUE))
        );

        panel.addTab("Nomina", Pnomina);

        Ppago.setBackground(new java.awt.Color(255, 255, 255));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Nomina", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel38.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("TOTAL:");

        jLabel39.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("ID Pago:");

        CalculaPago.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        CalculaPago.setForeground(new java.awt.Color(0, 0, 0));
        CalculaPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calcula1.png"))); // NOI18N
        CalculaPago.setText("Calcular");
        CalculaPago.setContentAreaFilled(false);
        CalculaPago.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CalculaPago.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calcula1.png"))); // NOI18N
        CalculaPago.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calcular2.png"))); // NOI18N
        CalculaPago.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        CalculaPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalculaPagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(32, 32, 32)
                        .addComponent(IDpago, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CalculaPago)
                            .addComponent(TotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IDpago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(CalculaPago)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Empleado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel40.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Nombre:");

        jLabel41.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Apellido:");

        jLabel42.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("ID Emple:");

        jLabel43.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("ID Cargo:");

        jLabel44.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Cargo:");

        jLabel46.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Doc:");

        BuscarNominaPago.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        BuscarNominaPago.setForeground(new java.awt.Color(0, 0, 0));
        BuscarNominaPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BUSCARpeque.png"))); // NOI18N
        BuscarNominaPago.setContentAreaFilled(false);
        BuscarNominaPago.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BuscarNominaPago.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BUSCARpeque.png"))); // NOI18N
        BuscarNominaPago.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BUSCARpeque1.png"))); // NOI18N
        BuscarNominaPago.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        BuscarNominaPago.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BuscarNominaPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarNominaPagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addGap(26, 26, 26)
                        .addComponent(DocPagoEmple)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BuscarNominaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44)
                            .addComponent(jLabel43)
                            .addComponent(jLabel41)
                            .addComponent(jLabel40)
                            .addComponent(jLabel42))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(IDemplePago, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NombPagoEmple, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ApePagoEmple, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IDcargoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CargoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DocPagoEmple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(BuscarNominaPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IDemplePago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NombPagoEmple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ApePagoEmple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IDcargoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CargoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Nomina", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel45.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Fecha Fin:");

        jLabel47.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Fecha inicio:");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel47)
                    .addComponent(jLabel45)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fecha2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        RegistrarPago.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        RegistrarPago.setForeground(new java.awt.Color(0, 0, 0));
        RegistrarPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        RegistrarPago.setText("Registrar");
        RegistrarPago.setContentAreaFilled(false);
        RegistrarPago.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        RegistrarPago.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        RegistrarPago.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar2.png"))); // NOI18N
        RegistrarPago.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        RegistrarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarPagoActionPerformed(evt);
            }
        });

        tablaPago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaPago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPagoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablaPago);

        ModificarPago.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        ModificarPago.setForeground(new java.awt.Color(0, 0, 0));
        ModificarPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar1.png"))); // NOI18N
        ModificarPago.setText("Modificar");
        ModificarPago.setContentAreaFilled(false);
        ModificarPago.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ModificarPago.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar1.png"))); // NOI18N
        ModificarPago.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar2.png"))); // NOI18N
        ModificarPago.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ModificarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarPagoActionPerformed(evt);
            }
        });

        pdf.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        pdf.setForeground(new java.awt.Color(0, 0, 0));
        pdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pdf1.png"))); // NOI18N
        pdf.setText("PDF");
        pdf.setContentAreaFilled(false);
        pdf.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pdf.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pdf1.png"))); // NOI18N
        pdf.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pdf2.png"))); // NOI18N
        pdf.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfActionPerformed(evt);
            }
        });

        EliminarPago1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        EliminarPago1.setForeground(new java.awt.Color(0, 0, 0));
        EliminarPago1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar1.png"))); // NOI18N
        EliminarPago1.setText("Eliminar");
        EliminarPago1.setContentAreaFilled(false);
        EliminarPago1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EliminarPago1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar1.png"))); // NOI18N
        EliminarPago1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar2.png"))); // NOI18N
        EliminarPago1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EliminarPago1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarPago1ActionPerformed(evt);
            }
        });

        NuevoPago.setText("Nuevo");
        NuevoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoPagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PpagoLayout = new javax.swing.GroupLayout(Ppago);
        Ppago.setLayout(PpagoLayout);
        PpagoLayout.setHorizontalGroup(
            PpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PpagoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(PpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1052, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PpagoLayout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PpagoLayout.createSequentialGroup()
                                .addComponent(RegistrarPago, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ModificarPago, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PpagoLayout.createSequentialGroup()
                                .addGroup(PpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(NuevoPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(EliminarPago1, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pdf, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(265, Short.MAX_VALUE))
        );
        PpagoLayout.setVerticalGroup(
            PpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PpagoLayout.createSequentialGroup()
                .addGroup(PpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PpagoLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(PpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(PpagoLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(PpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ModificarPago, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RegistrarPago, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PpagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pdf, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EliminarPago1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(NuevoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );

        panel.addTab("Pagos", Ppago);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nombre:");

        jLabel37.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Razon social:");

        jLabel48.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Telefono:");

        jLabel49.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Direccion:");

        jLabel50.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Correo:");

        lblImagen.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        RegisEmpre.setText("Registrar");
        RegisEmpre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisEmpreActionPerformed(evt);
            }
        });

        CargarIMG.setText("Cargar imagen");
        CargarIMG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarIMGActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("RUC:");

        IdDato.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        IdDato.setForeground(new java.awt.Color(0, 0, 0));
        IdDato.setText("2");

        Editar.setText("Editar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(RazonSocial)
                    .addComponent(TxEmpresa)
                    .addComponent(DirecEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(TelefonoEmpresa)
                    .addComponent(CorreoEmpre)
                    .addComponent(RUC))
                .addGap(30, 30, 30)
                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(IdDato, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RegisEmpre, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CargarIMG, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(TxEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(RazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TelefonoEmpresa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DirecEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CorreoEmpre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(CargarIMG)
                                .addGap(18, 18, 18)
                                .addComponent(RegisEmpre)
                                .addGap(18, 18, 18)
                                .addComponent(Editar)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(IdDato, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabladatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Razon social", "Telefono", "Direccion", "Correo", "imagen"
            }
        ));
        tabladatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabladatosMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tabladatos);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6))
                .addContainerGap(540, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );

        panel.addTab("Datos", jPanel19);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(panel)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1365, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1067, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   //**TERMINA ACCIONES DE NAV**//
    ////
    //*CARGOS*//
    ////
    //* ACCION DE LA TABLA CARGOS Y BD*//
    private void tablacargosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablacargosMouseClicked
      int fila=tablacargos.getSelectedRow();
      TxID.setText(tablacargos.getValueAt(fila, 0).toString());
      TxCargo.setText(tablacargos.getValueAt(fila, 1).toString());
      PagoCargo.setText(tablacargos.getValueAt(fila, 2).toString());
    }//GEN-LAST:event_tablacargosMouseClicked
    //* ACCION DEL BOTON REGISTRAR EN LA SECCION CARGOS Y BD*//
    private void RegistrarCargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarCargosActionPerformed
        cr.setNombre(TxCargo.getText());
        cr.setPago(Double.parseDouble(PagoCargo.getText()));
        if(daoC.insertar(cr)){
            JOptionPane.showMessageDialog(null, "Cargo registrado con exito");
              limpiarDatosCargo();
              limpiarTablaCargo();
              listar();
        }else{
            JOptionPane.showMessageDialog(null, "Error al registrar");
        }
    }//GEN-LAST:event_RegistrarCargosActionPerformed
    //* ACCION DEL BOTON MODIFICAR EN TABLA CARGOS Y BD*//
    private void ModificarCargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarCargosActionPerformed
        int fila=tablacargos.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Seleccione un cargo");
        }
        else{
            cr.setId(Integer.parseInt(TxID.getText()));
             cr.setNombre(TxCargo.getText());
              cr.setPago(Double.parseDouble(PagoCargo.getText()));
             if(daoC.editar(cr)){
                 JOptionPane.showMessageDialog(null, "Se modifico con exito");
                  limpiarDatosCargo();
                  limpiarTablaCargo();
                  listar();
             }else{
                 JOptionPane.showMessageDialog(null, "Fallo modificar");
             }
        }
    }//GEN-LAST:event_ModificarCargosActionPerformed
    //* ACCION DEL BOTON ELIMINAR EN TABLA CARGOS Y BD*//
    private void EliminarCargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarCargosActionPerformed
        if(!TxID.getText().isEmpty()){
            int confirmacion= JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro de eliminar el cargo?","Confirmacion",2);
            if(confirmacion ==0){
                cr.setId(Integer.parseInt(TxID.getText()));
                 daoC.eliminar(cr);
                 limpiarDatosCargo();
                 limpiarTablaCargo();
                 listar();
            }
        }
    }//GEN-LAST:event_EliminarCargosActionPerformed
    //* ACCION DEL BOTON BUSCAR EN TABLA CARGOS Y BD*//
    private void BuscarCargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarCargosActionPerformed

        cr.setId(Integer.parseInt(TxID.getText()));
        if(daoC.buscar(cr)){
            TxID.setText(cr.getId()+"");
            TxCargo.setText(cr.getNombre());
            PagoCargo.setText(cr.getPago()+"");
        }else{
            JOptionPane.showConfirmDialog(null, "El cargo no existe");
            TxID.setText("");
            TxCargo.setText("");
            PagoCargo.setText("");
        } 
         //* TERMINAN ACCION DE TABLA CARGOS Y BD*//
    }//GEN-LAST:event_BuscarCargosActionPerformed
    ////
    //*AREAS*//
    ////
    //* ACCION REGISTRAR DE LA TABLA AREAS Y BD*//
    private void RegistrarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarAreaActionPerformed
        ar.setNomArea(TxNombreArea.getText());
        if(daoA.insertar(ar)){
            JOptionPane.showMessageDialog(null, "Area ingresada con exito");
              limpiarDatosArea();
              limpiarTablaArea();
              listarArea();
        }else{
            JOptionPane.showMessageDialog(null, "Error al registrar el area");
        }
      
    }//GEN-LAST:event_RegistrarAreaActionPerformed

    private void ModificarAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarAreasActionPerformed
       int fila=tablaArea.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Seleccione una area");
        }
        else{
            ar.setIdarea(Integer.parseInt(TxIDarea.getText()));
            ar.setNomArea(TxNombreArea.getText());
             if(daoA.editar(ar)){
                 JOptionPane.showMessageDialog(null, "Se modifico con exito");
                  limpiarDatosArea();
                  limpiarTablaArea();
                  listarArea();
             }else{
                 JOptionPane.showMessageDialog(null, "Fallo modificar");
             }
        }
    }//GEN-LAST:event_ModificarAreasActionPerformed
        //* ACCION DE LA TABLA CARGOS Y BD*//
    private void tablaAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAreaMouseClicked
       int fila=tablaArea.getSelectedRow();
      TxIDarea.setText(tablaArea.getValueAt(fila, 0).toString());
      TxNombreArea.setText(tablaArea.getValueAt(fila, 1).toString());
    }//GEN-LAST:event_tablaAreaMouseClicked

    private void EliminarAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarAreasActionPerformed
        if(!TxIDarea.getText().isEmpty()){
            int confirmacion= JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro de eliminar la area?","Confirmacion",2);
            if(confirmacion ==0){
                ar.setIdarea(Integer.parseInt(TxIDarea.getText()));
                daoA.eliminar(ar);
                limpiarDatosArea();
                limpiarTablaArea();
                listarArea();
            }
        }
    }//GEN-LAST:event_EliminarAreasActionPerformed

    private void BuscarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarAreaActionPerformed
        ar.setIdarea(Integer.parseInt(TxIDarea.getText()));
        if(daoA.buscar(ar)){
            TxIDarea.setText(ar.getIdarea()+"");
            TxNombreArea.setText(ar.getNomArea());
        }else{
            JOptionPane.showConfirmDialog(null, "El cargo no existe");
            TxID.setText("");
            TxCargo.setText("");
        } 
         //* TERMINAN ACCION DE TABLA CARGOS Y BD*//
    }//GEN-LAST:event_BuscarAreaActionPerformed

    private void RegistrarEmpleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarEmpleActionPerformed
       em.setNombre(TxNombreEmple.getText());
        em.setApellido(TxApellidoEmple.getText());
        em.setTipodoc(TipoDoc.getSelectedItem().toString());
        em.setDocumento(TxDui.getText());
        em.setIdArea(Integer.parseInt(TxIDareaEmple.getText()));
        em.setIdCargo(Integer.parseInt(TxIDcargoEmple.getText()));
        em.setTelefono(TxTelefono.getText());
        em.setCorreo(TxCorreo.getText());
        if(daoE.insertar(em)){
            JOptionPane.showMessageDialog(null, "Empleado registrado con exito");
            limpiarDatosEmpleados();
            limpiarTablaEmpleados();
            listarEmpleado();
            //cantEmpleados();
        }else{
            JOptionPane.showMessageDialog(null, "Error Al Registrar el Empleado");
        }
        
        
        
        
    }//GEN-LAST:event_RegistrarEmpleActionPerformed

    private void EmpleAreaBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmpleAreaBuscarActionPerformed
     EnviarAreas.setEnabled(true);
     panel.setSelectedComponent(parea);


    }//GEN-LAST:event_EmpleAreaBuscarActionPerformed

    private void EmpleCargosBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmpleCargosBuscarActionPerformed
        EnviarCargos.setEnabled(true);
     panel.setSelectedComponent(pcargo);

    }//GEN-LAST:event_EmpleCargosBuscarActionPerformed

    private void EnviarAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarAreasActionPerformed
        TxIDareaEmple.setText(TxIDarea.getText());
        TxAreaEmple.setText(TxNombreArea.getText());
        EnviarAreas.setEnabled(false);
        panel.setSelectedComponent(pempleados);
    }//GEN-LAST:event_EnviarAreasActionPerformed

    private void ModificarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarEmpleadosActionPerformed
       int fila=tablaempleado.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Seleccione un cargo");
        }
        else{
            em.setId(Integer.parseInt(TxEmpleado.getText()));
            em.setNombre(TxNombreEmple.getText());
            em.setApellido(TxApellidoEmple.getText());
            em.setTipodoc(TipoDoc.getSelectedItem().toString());
            em.setDocumento(TxDui.getText());
            em.setIdArea(Integer.parseInt(TxIDareaEmple.getText()));
            em.setIdCargo(Integer.parseInt(TxIDcargoEmple.getText()));
            em.setTelefono(TxTelefono.getText());
            em.setCorreo(TxCorreo.getText());
            if(daoE.editar(em)){
                JOptionPane.showMessageDialog(null, "se modifico con exito");
                limpiarDatosEmpleados();
                limpiarDatosEmpleados();
                listarEmpleado();
            }else{
                JOptionPane.showMessageDialog(null, "Error al modificar");
            }
        }
    }//GEN-LAST:event_ModificarEmpleadosActionPerformed

    private void tablaempleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaempleadoMouseClicked
      int fila=tablaempleado.getSelectedRow();
      TxEmpleado.setText(tablaempleado.getValueAt(fila, 0).toString());
      TxNombreEmple.setText(tablaempleado.getValueAt(fila, 1).toString());
      TxApellidoEmple.setText(tablaempleado.getValueAt(fila, 2).toString());
      TipoDoc.setSelectedItem(tablaempleado.getValueAt(fila, 3).toString());
      TxDui.setText(tablaempleado.getValueAt(fila, 4).toString());
      TxIDareaEmple.setText(tablaempleado.getValueAt(fila, 5).toString());
      TxAreaEmple.setText(tablaempleado.getValueAt(fila, 6).toString());
      TxIDcargoEmple.setText(tablaempleado.getValueAt(fila, 7).toString());
      TxCargoEmple.setText(tablaempleado.getValueAt(fila, 8).toString());
      TxTelefono.setText(tablaempleado.getValueAt(fila, 9).toString());
      TxCorreo.setText(tablaempleado.getValueAt(fila, 10).toString());
    }//GEN-LAST:event_tablaempleadoMouseClicked

    private void BuscarArea1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarArea1ActionPerformed
        em.setId(Integer.parseInt(TxEmpleado.getText()));
        if(daoE.Buscar(em)){
            TxEmpleado.setText(em.getId()+"");
            TxNombreEmple.setText(em.getNombre());
            TxApellidoEmple.setText(em.getApellido());
            TipoDoc.setSelectedItem(em.getTipodoc().toString());
            TxDui.setText(em.getDocumento());
            TxTelefono.setText(em.getTelefono());
            TxCorreo.setText(em.getCorreo());
            TxIDareaEmple.setText(em.getIdArea()+""); 
            TxIDcargoEmple.setText(em.getIdCargo()+"");
            ar.setIdarea(Integer.parseInt(TxIDarea.getText()));
            daoA.buscar(ar);
            TxAreaEmple.setText(ar.getNomArea());
            cr.setId(Integer.parseInt(TxIDcargoEmple.getText()));
            daoC.buscar(cr);
            TxCargoEmple.setText(cr.getNombre());
        }else{
            JOptionPane.showMessageDialog(null, "El Empleado No Existe");
            limpiarDatosEmpleados();
        }
    }//GEN-LAST:event_BuscarArea1ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.dispose();
        KrokEmple newframe = new KrokEmple();
        this.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
       
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel23MouseClicked

    private void EliminarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarEmpleadoActionPerformed

             if(!TxEmpleado.getText().isEmpty()){
            int confirmacion=JOptionPane.showConfirmDialog(rootPane, "¿Seguro de eliminar el empleado?","Confirmar",2);
            if(confirmacion==0){
                em.setId(Integer.parseInt(TxEmpleado.getText()));
                daoE.eliminar(em);
                limpiarDatosEmpleados();
            
                listarEmpleado();
               
            }
        }
    }//GEN-LAST:event_EliminarEmpleadoActionPerformed

    private void BuscarNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarNominaActionPerformed
        em.setDocumento(TxDocumentoNomina.getText());
        if(daoE.BuscarEmpleadoN(em)){
            IDemple.setText(em.getId()+"");
            TxNombreNomina.setText(em.getNombre());
            TxApellidoNomina.setText(em.getApellido());
            TxIdcargoNomina.setText(em.getIdCargo()+"");
            cr.setId(Integer.parseInt(TxIdcargoNomina.getText()));
            daoC.buscar(cr);
            TxCargoNomina.setText(cr.getNombre());
            PagoNomina.setText(cr.getPago()+"");
        }else{
            JOptionPane.showMessageDialog(null, "El Empleado No Existe");
            limpiarDatosEmpleados();
        }
    }//GEN-LAST:event_BuscarNominaActionPerformed

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
       this.dispose();
       BOD newframe=new BOD();
       setVisible(true);
    }//GEN-LAST:event_jLabel25MouseClicked

    private void RegistrarNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarNominaActionPerformed
        Calendar cal;
        int d,m,a;
        cal=dateFecha.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR)-1900;
        no.setFecha(new java.sql.Date(a,m,d));
        
             
        no.setCtrabajo(Integer.parseInt(TxCantidadTrabajo.getText()));
        no.setTotal(Double.parseDouble(TotalNomina.getText()));
        no.setIdempleado(Integer.parseInt(IDemple.getText()));
        no.setIdcargo(Integer.parseInt(TxIdcargoNomina.getText()));
        no.setEstado("pendiente");
       
        if(daoN.insertar(no)){
            JOptionPane.showMessageDialog(null, "Nomina registrada con exito");
            limpiarDatosNomina();
            limpiarTablaNomina();
            listarNomina();
            
        }else{
            JOptionPane.showMessageDialog(null, "Error Al Registrar la Nomina");
        }
                                  
    }//GEN-LAST:event_RegistrarNominaActionPerformed

    private void EnviarCargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarCargosActionPerformed
        TxIDcargoEmple.setText(TxID.getText());
        TxCargoEmple.setText(TxCargo.getText());
        EnviarCargos.setEnabled(false);
        panel.setSelectedComponent(pempleados);
    }//GEN-LAST:event_EnviarCargosActionPerformed

    private void TotalNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalNominaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalNominaActionPerformed

    private void CalcularNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalcularNominaActionPerformed
        Double cantidad, precio, total;
        cantidad=Double.parseDouble(TxCantidadTrabajo.getText());
        precio=Double.parseDouble(PagoNomina.getText());
        total=cantidad*precio;
        TotalNomina.setText(total+"");
        
    }//GEN-LAST:event_CalcularNominaActionPerformed

    private void ModificarNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarNominaActionPerformed
      Calendar cal;
        int d,m,a;
        cal=dateFecha.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR)-1900;
        no.setFecha(new java.sql.Date(a,m,d));
        
        no.setCtrabajo(Integer.parseInt(TxCantidadTrabajo.getText()));
        no.setTotal(Double.parseDouble(TotalNomina.getText()));
        no.setIdempleado(Integer.parseInt(idnomina.getText()));
        no.setIdcargo(Integer.parseInt(TxIdcargoNomina.getText()));
        //no.setEstado("pendiente");
        if(daoN.insertar(no)){
            JOptionPane.showMessageDialog(null, "Nomina registrada con exito");
            limpiarDatosNomina();
            limpiarTablaNomina();
             listarNomina();
             //cantNominaPendientes();
             //cantNominasT();00
        }else{
            JOptionPane.showMessageDialog(null, "Error Al Registrar la Nomina");
        }
    }//GEN-LAST:event_ModificarNominaActionPerformed

    private void tablanominaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablanominaMouseClicked
     int fila=tablanomina.getSelectedRow();
        idnomina.setText(tablanomina.getValueAt(fila, 0).toString());
        dateFecha.setDate(java.sql.Date.valueOf(tablanomina.getValueAt(fila, 1).toString()));
        IDemple.setText(tablanomina.getValueAt(fila, 2).toString());
        TxNombreNomina.setText(tablanomina.getValueAt(fila, 3).toString());
        TxApellidoNomina.setText(tablanomina.getValueAt(fila, 4).toString());
        TxDocumentoNomina.setText(tablanomina.getValueAt(fila, 5).toString());
        TxIdcargoNomina.setText(tablanomina.getValueAt(fila, 6).toString());
        TxCargoNomina.setText(tablanomina.getValueAt(fila, 7).toString());
        PagoNomina.setText(tablanomina.getValueAt(fila, 8).toString());
        TxCantidadTrabajo.setText(tablanomina.getValueAt(fila, 9).toString());
        TotalNomina.setText(tablanomina.getValueAt(fila, 10).toString());
        
    }//GEN-LAST:event_tablanominaMouseClicked

    private void buscarNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarNominaActionPerformed
     no.setId(Integer.parseInt(idnomina.getText()));
        if(daoN.Buscar(no)){
            idnomina.setText(no.getId()+"");
            dateFecha.setDate(java.sql.Date.valueOf(no.getFecha().toString()));
            TxCantidadTrabajo.setText(no.getCtrabajo()+"");
            TotalNomina.setText(no.getTotal()+"");
            IDemple.setText(no.getIdempleado()+"");
            TxIdcargoNomina.setText(no.getIdcargo()+"");
            em.setId(Integer.parseInt(IDemple.getText()));
            daoE.Buscar(em);
            TxDocumentoNomina.setText(em.getDocumento()); 
            TxNombreNomina.setText(em.getNombre());
            TxApellidoNomina.setText(em.getApellido());
            cr.setId(Integer.parseInt(TxIdcargoNomina.getText()));
            daoC.buscar(cr);
            TxCargoNomina.setText(cr.getNombre());
            PagoNomina.setText(cr.getPago()+"");
        }else{
            JOptionPane.showMessageDialog(null, "La nomina No Existe");
            //limpiarDatosNomina();
        }
    }//GEN-LAST:event_buscarNominaActionPerformed

    private void EliminarNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarNominaActionPerformed
       if(!idnomina.getText().isEmpty()){
            int confirmacion=JOptionPane.showConfirmDialog(rootPane, "¿Seguro de eliminar la nomina?","Confirmar",2);
            if(confirmacion==0){
                no.setId(Integer.parseInt(idnomina.getText()));
                daoN.elimiar(no);
                limpiarDatosNomina();
                listarNomina();
                //cantNominaPendientes();
                //cantNominasT();
            }
        }
    }//GEN-LAST:event_EliminarNominaActionPerformed

    private void RegistrarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarPagoActionPerformed
       Calendar cal;
        int d,m,a;
        cal=fecha1.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR)-1900;
        pa.setFecha1(new java.sql.Date(a,m,d));
        
        Calendar cal1;
        int d1,m1,a1;
        cal1=fecha2.getCalendar();
        d1=cal1.get(Calendar.DAY_OF_MONTH);
        m1=cal1.get(Calendar.MONTH);
        a1=cal1.get(Calendar.YEAR)-1900;
        pa.setFecha2(new java.sql.Date(a1,m1,d1));
        
        pa.setIdempleado(Integer.parseInt(IDemplePago.getText()));
        pa.setTotal(Double.parseDouble(TotalPago.getText()));
        
        if(daoP.insertar(pa)){
            JOptionPane.showMessageDialog(null, "Pago registrado con exito");
            
        }else{
            JOptionPane.showMessageDialog(null, "Error Al Registrar el pago");
        }
        int ide;
        String f1,f2;
        
        ide=Integer.parseInt(IDemplePago.getText());
        f1=new SimpleDateFormat("yyyy-MM-dd").format(fecha1.getDate());
        f2=new SimpleDateFormat("yyyy-MM-dd").format(fecha2.getDate());
        
        if((daoP.editarN(ide, f1, f2,"pagado"))){
            //GenerarPDFpagos(ide, f1, f2);
            limpiarTablaPago();
            listarPagos();
            //cantNominaPendientes();
           // cantNominaPagadas();
            //cantPagos();
        }else{
            JOptionPane.showMessageDialog(null, "Error editar de pendiente a pagado");
        }
    }//GEN-LAST:event_RegistrarPagoActionPerformed

    private void BuscarNominaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarNominaPagoActionPerformed
        em.setDocumento(DocPagoEmple.getText());
        if(daoE.BuscarEmpleadoN(em)){
            IDemplePago.setText(em.getId()+"");
            NombPagoEmple.setText(em.getNombre());
            ApePagoEmple.setText(em.getApellido());
            IDcargoPago.setText(em.getIdCargo()+"");
            cr.setId(Integer.parseInt(IDcargoPago.getText()));
            daoC.buscar(cr);
            CargoPago.setText(cr.getNombre());
        }else{
            JOptionPane.showMessageDialog(null, "El Empleado No Existe");
            limpiarDatosEmpleados();
        }
    }//GEN-LAST:event_BuscarNominaPagoActionPerformed

    private void tablaPagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPagoMouseClicked
        int fila=tablaPago.getSelectedRow();
         IDpago.setText(tablaPago.getValueAt(fila, 0).toString());
         IDemplePago.setText(tablaPago.getValueAt(fila, 1).toString());
         NombPagoEmple.setText(tablaPago.getValueAt(fila, 2).toString());
         ApePagoEmple.setText(tablaPago.getValueAt(fila, 3).toString());
         DocPagoEmple.setText(tablaPago.getValueAt(fila, 4).toString());
         IDcargoPago.setText(tablaPago.getValueAt(fila, 5).toString());
        CargoPago.setText(tablaPago.getValueAt(fila, 6).toString());
        fecha1.setDate(java.sql.Date.valueOf(tablaPago.getValueAt(fila, 7).toString()));
        fecha2.setDate(java.sql.Date.valueOf(tablaPago.getValueAt(fila, 8).toString()));
        TotalPago.setText(tablaPago.getValueAt(fila, 9).toString());
    }//GEN-LAST:event_tablaPagoMouseClicked

    private void CalculaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalculaPagoActionPerformed
        int ide;
        String f1,f2;
        
        ide=Integer.parseInt(IDemplePago.getText());
        f1=new SimpleDateFormat("yyyy-MM-dd").format(fecha1.getDate());
        f2=new SimpleDateFormat("yyyy-MM-dd").format(fecha2.getDate()); 
        
        pa=daoP.Total(ide, f1, f2);
        if(pa.getTotal()!=0.0){
            TotalPago.setText(pa.getTotal()+"");
        }else{
            JOptionPane.showMessageDialog(null, "Error al Calcular");
        }
    }//GEN-LAST:event_CalculaPagoActionPerformed

    private void ModificarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarPagoActionPerformed
        int fila=tablaPago.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Seleccione un Pago");
        }else{
            pa.setIdempleado(Integer.parseInt(IDemplePago.getText()));
            pa.setTotal(Double.parseDouble(TotalPago.getText()));
            Calendar cal;
            int d,m,a;
            cal=fecha1.getCalendar();
            d=cal.get(Calendar.DAY_OF_MONTH);
            m=cal.get(Calendar.MONTH);
            a=cal.get(Calendar.YEAR)-1900;
            pa.setFecha1(new java.sql.Date(a,m,d));
            Calendar cal1;
            int d1,m1,a1;
            cal1=fecha2.getCalendar();
            d1=cal1.get(Calendar.DAY_OF_MONTH);
            m1=cal1.get(Calendar.MONTH);
            a1=cal1.get(Calendar.YEAR)-1900;
            pa.setFecha2(new java.sql.Date(a1,m1,d1));
            pa.setId(Integer.parseInt(IDpago.getText()));
            if(daoP.editar(pa)){
                JOptionPane.showMessageDialog(null, "se modifico con exito");
                limpiarDatosPago();
                limpiarTablaPago();
                listarPagos();
                //cantNominaPendientes();
            }else{
                JOptionPane.showMessageDialog(null, "Error al modificar");
            }
        }
    }//GEN-LAST:event_ModificarPagoActionPerformed

    private void pdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfActionPerformed
          int ide;
        String f1,f2;
        
        ide=Integer.parseInt(IDemplePago.getText());
        f1=new SimpleDateFormat("yyyy-MM-dd").format(fecha1.getDate());
        f2=new SimpleDateFormat("yyyy-MM-dd").format(fecha2.getDate());
        GenerarPDFpagos(ide, f1, f2);
    }//GEN-LAST:event_pdfActionPerformed

    private void CargarIMGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarIMGActionPerformed
       JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        fileChooser.setFileFilter(extensionFilter);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            Ruta = fileChooser.getSelectedFile().getAbsolutePath();
            Image mImagen = new ImageIcon(Ruta).getImage();
            ImageIcon mIcono = new ImageIcon(mImagen.getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), 0));
            lblImagen.setIcon(mIcono);
        }
        valor="2";
    }//GEN-LAST:event_CargarIMGActionPerformed

    private void RegisEmpreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisEmpreActionPerformed
            da.setNombre(TxEmpresa.getText());
            da.setRUC(RUC.getText());
            da.setRasonS(RazonSocial.getText());
            da.setTelefono(TelefonoEmpresa.getText());
            da.setDireccion(DirecEmpresa.getText());
            da.setCorreo(CorreoEmpre.getText());
            da.setImagen(getImagen(Ruta));
            daoD.Agregar(da);
            limpiarDatos();
            limpiarTablaDAtos();
            ListarDatos();
    }//GEN-LAST:event_RegisEmpreActionPerformed

    private void tabladatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabladatosMouseClicked
int fila=tabladatos.getSelectedRow();
        IdDato.setText(tabladatos.getValueAt(fila, 0).toString());
        TxEmpresa.setText(tabladatos.getValueAt(fila, 1).toString());
        RUC.setText(tabladatos.getValueAt(fila, 2).toString());
        RazonSocial.setText(tabladatos.getValueAt(fila, 3).toString());
        TelefonoEmpresa.setText(tabladatos.getValueAt(fila, 4).toString());
        DirecEmpresa.setText(tabladatos.getValueAt(fila, 5).toString());
        CorreoEmpre.setText(tabladatos.getValueAt(fila, 6).toString());
        da.setId(Integer.parseInt(IdDato.getText()));
        if(daoD.BuscarImagen(da)){
            try{
                byte[] imagen =da.getImagen();
                BufferedImage buffer=null;
                InputStream inputstream=new ByteArrayInputStream(imagen);
                buffer=ImageIO.read(inputstream);
                ImageIcon incono=new ImageIcon(buffer.getScaledInstance(180, 180, 0));
                lblImagen.setIcon(incono);
                valor="1";
            }catch (Exception e){
                lblImagen.setText("Error");
            }
        }     
    }//GEN-LAST:event_tabladatosMouseClicked

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
      int fila=tabladatos.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Seleccione los datos");
        }else{
            da.setNombre(TxEmpresa.getText());
            da.setRUC(RUC.getText());
            da.setRasonS(RazonSocial.getText());
            da.setTelefono(TelefonoEmpresa.getText());
            da.setDireccion(DirecEmpresa.getText());
            da.setCorreo(CorreoEmpre.getText());
            if(valor.equals("1")){
                da.setImagen(getImagenEditar());
            }else{
                da.setImagen(getImagen(Ruta));
            }
            da.setId(Integer.parseInt(IdDato.getText()));
            if(daoD.editar(da)){
                JOptionPane.showMessageDialog(null, "se modifico con exito");
                limpiarDatos();
                limpiarTablaDAtos();
                ListarDatos();
            }else{
                JOptionPane.showMessageDialog(null, "Error al modificar");
            }
        }
    }//GEN-LAST:event_EditarActionPerformed

    private void EliminarPago1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarPago1ActionPerformed
         if(!IDpago.getText().isEmpty()){
            int confirmacion=JOptionPane.showConfirmDialog(rootPane, "¿Seguro de eliminar el Pago?","Confirmar",2);
            if(confirmacion==0){
                pa.setId(Integer.parseInt(IDpago.getText()));
                daoP.elimiar(pa);
                int ide;
                String f1,f2;
                ide=Integer.parseInt(IDemplePago.getText());
                f1=new SimpleDateFormat("yyyy-MM-dd").format(fecha1.getDate());
                f2=new SimpleDateFormat("yyyy-MM-dd").format(fecha2.getDate()); 
                if(daoP.editarN(ide, f1, f2,"pendiente")){
                limpiarDatosPago();
                limpiarTablaPago();
                listarPagos();
                //cantNominaPagadas();
                //cantNominaPendientes();
                //cantPagos();
        }else{
            JOptionPane.showMessageDialog(null, "Error editar de pendiente a pagado");
        }
            }
        }
    }//GEN-LAST:event_EliminarPago1ActionPerformed

    private void NuevoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoPagoActionPerformed
          limpiarDatosPago();
    }//GEN-LAST:event_NuevoPagoActionPerformed

    private void usuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuariosMouseClicked
        int fila=usuarios.getSelectedRow();
        IDusu.setText(usuarios.getValueAt(fila, 0).toString());
        NombreUsu.setText(usuarios.getValueAt(fila, 1).toString());
        USU.setText(usuarios.getValueAt(fila, 2).toString());
        PW.setText(usuarios.getValueAt(fila, 3).toString());
        CBOusu.setSelectedItem(usuarios.getValueAt(fila, 6).toString());
       
    }//GEN-LAST:event_usuariosMouseClicked

    private void RegisUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisUsuActionPerformed
        usu.setNombre(NombreUsu.getText());
        usu.setUsuario(USU.getText());
        usu.setPassword(PW.getText());
        usu.setTipo(CBOusu.getSelectedItem().toString());
        if(daoU.insertar(usu)){
            JOptionPane.showMessageDialog(null, "Usuario registrado con exito");
            limpiarDatosUsuario();
            limpiarTablaUsuario();
            listarUsuarios();
           
        }else{
            JOptionPane.showMessageDialog(null, "Error Al Registrar el Usuario");
        }
    }//GEN-LAST:event_RegisUsuActionPerformed

    private void IDusuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDusuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDusuActionPerformed

    private void ModiUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModiUsuActionPerformed
         int fila=usuarios.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Seleccione una area");
        }
        else{
            usu.setIdUser(Integer.parseInt(IDusu.getText()));
            usu.setNombre(NombreUsu.getText());
            usu.setUsuario(USU.getText());
            usu.setPassword(PW.getText());
             usu.setTipo(CBOusu.getSelectedItem().toString());
             if(daoA.editar(ar)){
                 JOptionPane.showMessageDialog(null, "se modifico con exito");
                limpiarDatosUsuario();
                limpiarTablaUsuario();
                listarUsuarios();
                  
             }else{
                 JOptionPane.showMessageDialog(null, "Fallo modificar");
             }
        }
    }//GEN-LAST:event_ModiUsuActionPerformed

    private void ModiUsu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModiUsu1ActionPerformed
       IDusu.setText("");
       NombreUsu.setText("");
       USU.setText("");
       PW.setText("");
    }//GEN-LAST:event_ModiUsu1ActionPerformed

    private void LimpiarCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarCaActionPerformed
        TxID.setText("");
        TxCargo.setText("");
        PagoCargo.setText("");
    }//GEN-LAST:event_LimpiarCaActionPerformed

    private void tablaempleadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaempleadoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaempleadoMouseEntered

////
    
      private byte[] getImagenEditar() {
        byte[] imagen =da.getImagen();
        try{
            BufferedImage buffer=null;
            InputStream inputstream=new ByteArrayInputStream(imagen);
            buffer=ImageIO.read(inputstream);
        }catch (Exception e){
            
        }
        return imagen;
    }
    
    private byte[] getImagen(String Ruta) {
        File imagen = new File(Ruta);
        try {
            byte[] icono = new byte[(int) imagen.length()];
            InputStream input = new FileInputStream(imagen);
            input.read(icono);
            return icono;
        } catch (Exception ex) {
            return null;
        }
    }
     void limpiarDatos(){
        TxEmpresa.setText("");
        RazonSocial.setText("");
         TelefonoEmpresa.setText("");
         DirecEmpresa.setText("");
         CorreoEmpre.setText("");
         lblImagen.setText("");
         lblImagen.setIcon(null);
    }
   
    
    /**LIMPIAR TX Y TABLA DE CARGOS **/
    ////
    void limpiarDatosCargo(){
        TxID.setText("");
        TxCargo.setText("");
        PagoCargo.setText("");
    }
    void limpiarTablaCargo(){
        for(int i=0;i<modeloCargo.getRowCount();i++){
        modeloCargo.removeRow(i);
        i=0-1;
        }
     }
    ////
    /**METODO DE LIMPIAR EN LOS TX Y TABLA AREAS**/
    ////
    void limpiarDatosArea(){
        TxIDarea.setText("");
        TxNombreArea.setText("");
    }
    void limpiarTablaArea(){
        for(int i=0;i<modeloArea.getRowCount();i++){
        modeloArea.removeRow(i);
        i=0-1;
        }
     }
    ////
    /**METODO DE LIMPIAR EN LOS TX Y TABLA EMPLEADOS**/
    ////
    void limpiarDatosEmpleados(){
        TxEmpleado.setText("");
        TxNombreEmple.setText("");
        TxApellidoEmple.setText("");
        TxDui.setText("");
        TxTelefono.setText("");
        TxCorreo.setText("");
        TxIDareaEmple.setText("");
        TxAreaEmple.setText("");
        TxIDcargoEmple.setText("");
        TxCargoEmple.setText("");
    }
    void limpiarTablaEmpleados(){
        for(int i=0;i<modeloEmpleados.getRowCount();i++){
        modeloEmpleados.removeRow(i);
        i=0-1;
        }
     }
    ////
    /**METODO DE LIMPIAR EN LOS TX Y TABLA NOMINA**/
    ////
    void limpiarDatosNomina(){
    idnomina.setText("");        
    TxDocumentoNomina.setText("");
    IDemple.setText("");
    TxNombreNomina.setText("");
    TxApellidoNomina.setText("");
    TxIdcargoNomina.setText("");
    TxCargoNomina.setText("");
    PagoNomina.setText("");
    TxCantidadTrabajo.setText("");
    TotalNomina.setText("");
    }
    void limpiarTablaNomina(){
        for(int i=0;i<modeloEmpleados.getRowCount();i++){
        modeloEmpleados.removeRow(i);
        i=0-1;
        }
     }
     ////
    /**METODO DE LIMPIAR EN LOS TX Y TABLA PAGO**/
    ////
     void limpiarDatosPago(){
            IDpago.setText("");
            TotalPago.setText("");
            DocPagoEmple.setText("");
            IDemplePago.setText("");
            NombPagoEmple.setText("");
            ApePagoEmple.setText(""); 
            IDcargoPago.setText("");
            CargoPago.setText("");
    }
       void limpiarTablaPago(){
        for(int i=0;i<modeloPago.getRowCount();i++){
            modeloPago.removeRow(i);
            i=0-1;
        }
    }
       
        void limpiardatos(){
        TxEmpresa.setText("");
         RUC.setText("");
         RazonSocial.setText("");
         TelefonoEmpresa.setText("");
         DirecEmpresa.setText("");
         CorreoEmpre.setText("");
         lblImagen.setText("");
         lblImagen.setIcon(null);
    }
        void limpiarTablaDAtos(){
        for(int i=0;i<modeloDatos.getRowCount();i++){
            modeloDatos.removeRow(i);
            i=0-1;
        }
    }
         void limpiarDatosUsuario(){
        IDusu.setText("");
        NombreUsu.setText("");
        USU.setText("");
        PW.setText("");
    }
           void limpiarTablaUsuario(){
        for(int i=0;i<modeloUsuario.getRowCount();i++){
            modeloUsuario.removeRow(i);
            i=0-1;
        }
           }
        
        
        
        
          private Connection conection=new conexion().conectar();
          void GenerarPDFpagos(int id,String f1,String f2){
        Map p=new HashMap();
        p.put("idempleado", id);
        p.put("fecha1", f1);
        p.put("fecha2", f2);
        //JasperReport report;
        //JasperPrint print;
        
        try{
            //report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+
                    //"/src/reportes/pagoEmpleados.jrxml");
            //print =JasperFillManager.fillReport(report, p,conection);
            //JasperViewer view=new JasperViewer(print,false);
            //view.setTitle("Comprobante de pago");
            //view.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KrokEmple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KrokEmple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KrokEmple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KrokEmple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KrokEmple().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ApePagoEmple;
    private javax.swing.JButton BuscarArea;
    private javax.swing.JButton BuscarArea1;
    private javax.swing.JButton BuscarCargos;
    private javax.swing.JButton BuscarNomina;
    private javax.swing.JButton BuscarNominaPago;
    private javax.swing.JComboBox<String> CBOusu;
    private javax.swing.JButton CalculaPago;
    private javax.swing.JButton CalcularNomina;
    private javax.swing.JButton CargarIMG;
    private javax.swing.JTextField CargoPago;
    private javax.swing.JTextField CorreoEmpre;
    private javax.swing.JTextField DirecEmpresa;
    private javax.swing.JTextField DocPagoEmple;
    private javax.swing.JButton Editar;
    private javax.swing.JButton EliminarAreas;
    private javax.swing.JButton EliminarCargos;
    private javax.swing.JButton EliminarEmpleado;
    private javax.swing.JButton EliminarNomina;
    private javax.swing.JButton EliminarPago1;
    private javax.swing.JButton EmpleAreaBuscar;
    private javax.swing.JButton EmpleCargosBuscar;
    private javax.swing.JButton EnviarAreas;
    private javax.swing.JButton EnviarCargos;
    private javax.swing.JTextField IDcargoPago;
    private javax.swing.JTextField IDemple;
    private javax.swing.JTextField IDemplePago;
    private javax.swing.JTextField IDpago;
    private javax.swing.JTextField IDusu;
    private javax.swing.JLabel IdDato;
    private javax.swing.JButton LimpiarCa;
    private javax.swing.JButton ModiUsu;
    private javax.swing.JButton ModiUsu1;
    private javax.swing.JButton ModificarAreas;
    private javax.swing.JButton ModificarCargos;
    private javax.swing.JButton ModificarEmpleados;
    private javax.swing.JButton ModificarNomina;
    private javax.swing.JButton ModificarPago;
    private javax.swing.JTextField NombPagoEmple;
    private javax.swing.JTextField NombreUsu;
    private javax.swing.JButton NuevoPago;
    private javax.swing.JPasswordField PW;
    private javax.swing.JTextField PagoCargo;
    private javax.swing.JTextField PagoNomina;
    private javax.swing.JPanel Pnomina;
    private javax.swing.JPanel Ppago;
    private javax.swing.JTextField RUC;
    private javax.swing.JTextField RazonSocial;
    private javax.swing.JButton RegisEmpre;
    private javax.swing.JButton RegisUsu;
    private javax.swing.JButton RegistrarArea;
    private javax.swing.JButton RegistrarCargos;
    private javax.swing.JButton RegistrarEmple;
    private javax.swing.JButton RegistrarNomina;
    private javax.swing.JButton RegistrarPago;
    private javax.swing.JTextField TelefonoEmpresa;
    private javax.swing.JComboBox<String> TipoDoc;
    private javax.swing.JTextField TotalNomina;
    private javax.swing.JPanel TotalPagar;
    private javax.swing.JTextField TotalPago;
    private javax.swing.JTextField TxApellidoEmple;
    private javax.swing.JTextField TxApellidoNomina;
    private javax.swing.JTextField TxAreaEmple;
    private javax.swing.JTextField TxCantidadTrabajo;
    private javax.swing.JTextField TxCargo;
    private javax.swing.JTextField TxCargoEmple;
    private javax.swing.JTextField TxCargoNomina;
    private javax.swing.JTextField TxCorreo;
    private javax.swing.JTextField TxDocumentoNomina;
    private javax.swing.JTextField TxDui;
    private javax.swing.JTextField TxEmpleado;
    private javax.swing.JTextField TxEmpresa;
    private javax.swing.JTextField TxID;
    private javax.swing.JTextField TxIDarea;
    private javax.swing.JTextField TxIDareaEmple;
    private javax.swing.JTextField TxIDcargoEmple;
    private javax.swing.JTextField TxIdcargoNomina;
    private javax.swing.JTextField TxNombreArea;
    private javax.swing.JTextField TxNombreEmple;
    private javax.swing.JTextField TxNombreNomina;
    private javax.swing.JTextField TxTelefono;
    private javax.swing.JTextField USU;
    private javax.swing.JButton buscarNomina;
    private com.toedter.calendar.JDateChooser dateFecha;
    private com.toedter.calendar.JDateChooser fecha1;
    private com.toedter.calendar.JDateChooser fecha2;
    private javax.swing.JTextField idnomina;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JTabbedPane panel;
    private javax.swing.JPanel parea;
    private javax.swing.JPanel pcargo;
    private javax.swing.JButton pdf;
    private javax.swing.JPanel pempleados;
    private javax.swing.JPanel psuarios;
    private javax.swing.JTable tablaArea;
    private javax.swing.JTable tablaPago;
    private javax.swing.JTable tablacargos;
    private javax.swing.JTable tabladatos;
    private javax.swing.JTable tablaempleado;
    private javax.swing.JTable tablanomina;
    private javax.swing.JTable usuarios;
    // End of variables declaration//GEN-END:variables
}
