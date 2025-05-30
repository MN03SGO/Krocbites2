/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Panta_principal;

import Clase_Conexiones_BD.Clase_Conexion_Categorias;
import Conexiones_BD.Conexion_Consultas_BD;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author anoni
 */
public final class Catego extends javax.swing.JPanel {
    //CONEXIONES
    Clase_Conexion_Categorias Class_Cate = new Clase_Conexion_Categorias();  
    Conexion_Consultas_BD Consul_BD = new Conexion_Consultas_BD();
    //TABLA
    DefaultTableModel TablaCategoria = new DefaultTableModel();
    
    

    /**
     * Creates new form Catego
     */
    public Catego() {
      initComponents();
      TablaCategoria.addColumn("ID");
      TablaCategoria.addColumn("Categoría");
      Categorias_Tabla.setModel(TablaCategoria);
      listar_Categorias();
        
    }
    
    //LISTAR TABLA
    public void listar_Categorias(){
        List<Clase_Conexion_Categorias> lista = Consul_BD.listar();
        Object[] obj=new Object[2];
        for (int i = 0; i < lista.size(); i++) {

            obj[0]=lista.get(i).getId_Categoria();
            obj[1]=lista.get(i).getCategoria();
            TablaCategoria.addRow(obj);
        }
        Categorias_Tabla.setModel(TablaCategoria);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panelRound1 = new PANELES.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ID_CATEGORIAS = new javax.swing.JTextField();
        CATEGORIAS_NOMB = new javax.swing.JTextField();
        panelRound2 = new PANELES.PanelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        Categorias_Tabla = new javax.swing.JTable();
        Catego_Edi = new RSMaterialComponent.RSButtonMaterialDos();
        Catego_Eli = new RSMaterialComponent.RSButtonMaterialDos();
        Catego_Bus = new RSMaterialComponent.RSButtonMaterialDos();
        jButton2 = new javax.swing.JButton();

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Registro de categorias");

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(25);
        panelRound1.setRoundBottomRight(25);
        panelRound1.setRoundTopLeft(25);
        panelRound1.setRoundTopRight(25);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ID:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nombre:");

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CATEGORIAS_NOMB, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelRound1Layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)))
                        .addGroup(panelRound1Layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addComponent(ID_CATEGORIAS, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ID_CATEGORIAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(CATEGORIAS_NOMB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setRoundBottomLeft(25);
        panelRound2.setRoundBottomRight(25);
        panelRound2.setRoundTopLeft(25);
        panelRound2.setRoundTopRight(25);

        Categorias_Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre"
            }
        ));
        Categorias_Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Categorias_TablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Categorias_Tabla);

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        Catego_Edi.setBackground(new java.awt.Color(6, 47, 64));
        Catego_Edi.setText("Editar");
        Catego_Edi.setBackgroundHover(new java.awt.Color(93, 158, 193));
        Catego_Edi.setRound(25);
        Catego_Edi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Catego_EdiActionPerformed(evt);
            }
        });

        Catego_Eli.setBackground(new java.awt.Color(6, 47, 64));
        Catego_Eli.setText("Eliminar");
        Catego_Eli.setBackgroundHover(new java.awt.Color(93, 158, 193));
        Catego_Eli.setRound(25);

        Catego_Bus.setBackground(new java.awt.Color(6, 47, 64));
        Catego_Bus.setText("Buscar");
        Catego_Bus.setBackgroundHover(new java.awt.Color(93, 158, 193));
        Catego_Bus.setRound(25);

        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar_1.png"))); // NOI18N
        jButton2.setText("Guardar");
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar_2.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addGap(63, 63, 63)
                        .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(392, 392, 392)
                        .addComponent(Catego_Edi, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Catego_Eli, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Catego_Bus, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Catego_Edi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Catego_Eli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Catego_Bus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Catego_EdiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Catego_EdiActionPerformed
        int fila = Categorias_Tabla.getSelectedRow();
        if(fila == -1 ){
            JOptionPane.showConfirmDialog(null, "Seleccione una categoria");
        }else{
            Class_Cate.setId_Categoria(Integer.parseInt(ID_CATEGORIAS.getText()));
            Class_Cate.setCategoria(CATEGORIAS_NOMB.getText());
            if(Consul_BD.editar(Class_Cate)){
                JOptionPane.showConfirmDialog(null, "Se modifico con exito");
                limpiar_labels_Categoria();
                limpiar_Tabla_Categoria();
            }
            
        }
    }//GEN-LAST:event_Catego_EdiActionPerformed

    private void Categorias_TablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Categorias_TablaMouseClicked
      
        
         int fila = Categorias_Tabla.getSelectedRow();
       ID_CATEGORIAS.setText(Categorias_Tabla.getValueAt(fila, 0).toString());
       CATEGORIAS_NOMB.setText(Categorias_Tabla.getValueAt (fila,1).toString());
       
            
      
    }//GEN-LAST:event_Categorias_TablaMouseClicked
    void limpiar_labels_Categoria(){
    ID_CATEGORIAS.setText("");
    CATEGORIAS_NOMB.setText("");
    }
    
    void limpiar_Tabla_Categoria(){
        for(int i=0;i<TablaCategoria.getRowCount();i++){
            TablaCategoria.removeRow(i);
            i=0;
        }
  
    }
   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CATEGORIAS_NOMB;
    private RSMaterialComponent.RSButtonMaterialDos Catego_Bus;
    private RSMaterialComponent.RSButtonMaterialDos Catego_Edi;
    private RSMaterialComponent.RSButtonMaterialDos Catego_Eli;
    private javax.swing.JTable Categorias_Tabla;
    private javax.swing.JTextField ID_CATEGORIAS;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private PANELES.PanelRound panelRound1;
    private PANELES.PanelRound panelRound2;
    // End of variables declaration//GEN-END:variables
}
