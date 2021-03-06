/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Fachada.Fachada;
import ModeloDatos.BaseDatos;
import ModeloDatos.PuertoSerie;
import java.util.Observable;
import java.util.Observer;
/**
 *
 * @author Darwin
 */
public class Configuracion extends javax.swing.JFrame implements Observer{

    /**
     * Creates new form Configuracion
     */
    private static Fachada modelo=null;
    
    public Configuracion() {
        initComponents();
        modelo=Fachada.getInstancia();
        modelo.addObserver(this);
        this.CargaListas();
        this.CargarConfiguracionBD();
        this.CargarConfiguracionPuertoSerie();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdcerrar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtenvio = new javax.swing.JTextField();
        cmdenviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtrecibo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmdrecibir = new javax.swing.JButton();
        btnscan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmbPuertos = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cmbBaudiosRate = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cmbBitesSize = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cmbStopBits = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cmbparidad = new javax.swing.JComboBox();
        btncon = new javax.swing.JButton();
        btnGuardarCfgPS = new javax.swing.JButton();
        chkregistro = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        txtservidor = new javax.swing.JTextField();
        txtnombrebd = new javax.swing.JTextField();
        txtusuario = new javax.swing.JTextField();
        cmbmotores = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtclave = new javax.swing.JPasswordField();
        txtclaveconfirma = new javax.swing.JPasswordField();
        btnGuardarCfgBD = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtpuerto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmdcerrar.setText("Cancelar");
        cmdcerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdcerrarActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(txtenvio);

        cmdenviar.setText("Enviar");
        cmdenviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdenviarActionPerformed(evt);
            }
        });

        txtrecibo.setAutoscrolls(false);
        jScrollPane1.setViewportView(txtrecibo);

        jLabel6.setText("Recibiendo");

        cmdrecibir.setText("Forzar volcado de memoria");
        cmdrecibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdrecibirActionPerformed(evt);
            }
        });

        btnscan.setText("Escanear puertos");
        btnscan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnscanActionPerformed(evt);
            }
        });

        jLabel1.setText("Puertos libres");

        cmbPuertos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPuertosActionPerformed(evt);
            }
        });

        jLabel2.setText("Baudios rate");

        cmbBaudiosRate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1" }));

        jLabel4.setText("Bites size");

        cmbBitesSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "5", "6", "7", "8" }));
        cmbBitesSize.setSelectedIndex(3);
        cmbBitesSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBitesSizeActionPerformed(evt);
            }
        });

        jLabel5.setText("Stop bits");

        cmbStopBits.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1" }));

        jLabel3.setText("Bits de paridad");

        cmbparidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NONE" }));
        cmbparidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbparidadActionPerformed(evt);
            }
        });

        btncon.setText("Conectar");
        btncon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconActionPerformed(evt);
            }
        });

        btnGuardarCfgPS.setText("Guardar");
        btnGuardarCfgPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCfgPSActionPerformed(evt);
            }
        });

        chkregistro.setText("Registro");
        chkregistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkregistroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(cmbBaudiosRate, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbparidad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbPuertos, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnscan, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addComponent(cmbBitesSize, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(btncon, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbStopBits, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkregistro, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cmdenviar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdrecibir, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarCfgPS, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbPuertos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnscan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbBaudiosRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdenviar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbBitesSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbStopBits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkregistro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdrecibir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbparidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btncon, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(btnGuardarCfgPS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Puerto Serial", jPanel1);

        jPanel2.setLayout(null);

        txtservidor.setText("localhost");
        txtservidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtservidorActionPerformed(evt);
            }
        });
        jPanel2.add(txtservidor);
        txtservidor.setBounds(100, 80, 410, 20);

        txtnombrebd.setText("nombre de base de datos");
        jPanel2.add(txtnombrebd);
        txtnombrebd.setBounds(100, 110, 220, 20);

        txtusuario.setText("usuario");
        jPanel2.add(txtusuario);
        txtusuario.setBounds(100, 140, 220, 20);

        cmbmotores.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MySQL", "Access" }));
        jPanel2.add(cmbmotores);
        cmbmotores.setBounds(10, 40, 123, 20);

        jLabel7.setText("Motor de base de datos");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(10, 20, 114, 14);

        jLabel8.setText("Servidor:");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(10, 81, 44, 14);

        jLabel9.setText("Nombre BD:");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(10, 110, 65, 14);

        jLabel10.setText("Usuario:");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(10, 140, 50, 14);

        jLabel11.setText("Clave:");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(10, 210, 80, 14);

        jLabel12.setText("Confirma Clave:");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(10, 240, 90, 14);

        txtclave.setText("jPasswordField1");
        jPanel2.add(txtclave);
        txtclave.setBounds(100, 210, 111, 20);

        txtclaveconfirma.setText("jPasswordField2");
        jPanel2.add(txtclaveconfirma);
        txtclaveconfirma.setBounds(100, 240, 111, 20);

        btnGuardarCfgBD.setText("Guardar");
        btnGuardarCfgBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCfgBDActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardarCfgBD);
        btnGuardarCfgBD.setBounds(400, 253, 110, 40);

        jLabel13.setText("Puerto:");
        jPanel2.add(jLabel13);
        jLabel13.setBounds(10, 170, 36, 14);
        jPanel2.add(txtpuerto);
        txtpuerto.setBounds(100, 170, 70, 20);

        jTabbedPane1.addTab("Base de Datos", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmdcerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdcerrar)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbPuertosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPuertosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPuertosActionPerformed

    private void btnconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconActionPerformed
        this.Conectar();
    }//GEN-LAST:event_btnconActionPerformed

    private void cmdenviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdenviarActionPerformed
        this.Enviar();
    }//GEN-LAST:event_cmdenviarActionPerformed

    private void cmbBitesSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBitesSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBitesSizeActionPerformed

    private void cmbparidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbparidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbparidadActionPerformed

    private void cmdrecibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdrecibirActionPerformed
        this.RecibeDatos();
    }//GEN-LAST:event_cmdrecibirActionPerformed

    private void cmdcerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcerrarActionPerformed
        this.Cerrar();
    }//GEN-LAST:event_cmdcerrarActionPerformed

    private void btnscanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnscanActionPerformed
        this.EscanearPuertos();
    }//GEN-LAST:event_btnscanActionPerformed

    private void txtservidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtservidorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtservidorActionPerformed

    private void btnGuardarCfgBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCfgBDActionPerformed
        GuardarCfgBD();
    }//GEN-LAST:event_btnGuardarCfgBDActionPerformed

    private void btnGuardarCfgPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCfgPSActionPerformed
        GuardarCfgPuertoSerie();
    }//GEN-LAST:event_btnGuardarCfgPSActionPerformed

    private void chkregistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkregistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkregistroActionPerformed

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
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Configuracion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarCfgBD;
    private javax.swing.JButton btnGuardarCfgPS;
    private javax.swing.JButton btncon;
    private javax.swing.JButton btnscan;
    private javax.swing.JCheckBox chkregistro;
    private javax.swing.JComboBox cmbBaudiosRate;
    private javax.swing.JComboBox cmbBitesSize;
    private javax.swing.JComboBox cmbPuertos;
    private javax.swing.JComboBox cmbStopBits;
    private javax.swing.JComboBox cmbmotores;
    private javax.swing.JComboBox cmbparidad;
    private javax.swing.JButton cmdcerrar;
    private javax.swing.JButton cmdenviar;
    private javax.swing.JButton cmdrecibir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPasswordField txtclave;
    private javax.swing.JPasswordField txtclaveconfirma;
    private javax.swing.JTextField txtenvio;
    private javax.swing.JTextField txtnombrebd;
    private javax.swing.JTextField txtpuerto;
    private javax.swing.JTextField txtrecibo;
    private javax.swing.JTextField txtservidor;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables

    public void CargaListas(){
        this.cmbBaudiosRate.setModel(modelo.GetListadoComboBaudiosRate());
        this.cmbPuertos.setModel(modelo.GetListadoComboPuertosLibres());
        this.cmbparidad.setModel(modelo.GetListadoComboParity());
        this.cmbStopBits.setModel(modelo.GetListadoComboStopBits());
    }
    
    public void Conectar(){
        if(this.modelo.ConectarPuerto(this.cmbPuertos.getSelectedItem().toString(), Integer.valueOf(this.cmbBaudiosRate.getSelectedItem().toString()), Integer.valueOf(this.cmbBitesSize.getSelectedItem().toString()), this.cmbStopBits.getSelectedIndex(), this.cmbparidad.getSelectedIndex())){
            this.btncon.setText("Desconectar");
        }else{
            this.btncon.setText("Conectar");
        }
    }
    
    public void Desconectar(){
        if(this.modelo.DesconectarPuerto()){
            this.btncon.setText("Conectar");
        }
    }
    
    public void Enviar(){
        this.modelo.EnviarDatosAPuertoSerie(this.txtenvio.getText());
    }
    public void RecibeDatos(){
        this.txtrecibo.setText(this.modelo.RecibeDatos());
    }
    
    public void EscribeDatosRecibidos(String datos){
        this.txtrecibo.setText(this.txtrecibo.getText()+datos);
    }
    
    public void EscanearPuertos(){
        this.cmbPuertos.setModel(modelo.GetListadoComboPuertosLibres());
    }
    
    public void Cerrar(){
        this.dispose();
    }
    
    public void GuardarCfgBD(){
        modelo.GuardarConfiguracionBD(this.txtservidor.getText(), this.txtnombrebd.getText(), this.txtusuario.getText(), this.txtclave.getText(), this.txtpuerto.getText(), this.cmbmotores.getSelectedItem().toString());
    }
    
    public void GuardarCfgPuertoSerie(){
        String puerto=this.cmbPuertos.getModel().getSelectedItem().toString();
        Integer baurate=Integer.valueOf(this.cmbBaudiosRate.getModel().getSelectedItem().toString());
        Integer databytesize=Integer.valueOf(this.cmbBitesSize.getModel().getSelectedItem().toString());
        Integer stopbytes=this.cmbStopBits.getSelectedIndex();
        Integer parity=this.cmbparidad.getSelectedIndex();
        //Integer baurate=Integer.valueOf(this.cmbBaudiosRate.getModel().getSelectedItem().toString());
        modelo.GuardarConfiguracionPuertoSerie(puerto,baurate,databytesize,stopbytes,parity,this.chkregistro.isSelected());
        /*
        System.out.println("puerto: " + puerto + System.getProperty("line.separator")
                + "baurate: " + baurate.toString() + System.getProperty("line.separator")
                + "databytesize: " + databytesize.toString() + System.getProperty("line.separator")
                + "stopbytes: " + stopbytes.toString() + System.getProperty("line.separator")
                + "parity: " + parity.toString());
                */
    }
    
    public void CargarConfiguracionBD(){
        BaseDatos bd=modelo.CargarConfiguracionDB();
        if(bd!=null){
            this.cmbmotores.setSelectedItem(bd.getMotorBD());
            this.txtservidor.setText(bd.getServidor());
            this.txtnombrebd.setText(bd.getNombreBD());
            this.txtusuario.setText(bd.getUsuario());
            this.txtpuerto.setText(bd.getPuerto());
            if(modelo.EstadoConexionPuertoSerie()){
                this.btncon.setText("Conectado");
            }
        }
    }
    
    public void CargarConfiguracionPuertoSerie(){
        PuertoSerie ps=modelo.CargarConfiguracionPuertoSerie();
        if(ps!=null){
        this.cmbPuertos.getModel().setSelectedItem(ps.getPuerto());
        this.cmbBaudiosRate.getModel().setSelectedItem(ps.getBaurate());
        this.cmbBitesSize.getModel().setSelectedItem(ps.getDatabytesize());
        this.cmbStopBits.setSelectedIndex(ps.getStopbits());
        this.cmbparidad.setSelectedIndex(ps.getParity());
        this.chkregistro.setSelected(ps.getRegistro());
        }
        
    }

    public void update(Observable o, Object arg) {
        if(arg.getClass().getName().equals("String")){
            this.EscribeDatosRecibidos(arg.toString());
        }
    }
    
}
