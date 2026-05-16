
package Client.IU;

import Client.Comando;
import Client.Intefaces.Informacion;
import Client.TCPClient;
import javax.swing.JOptionPane;

public class InterfazGraficaLogin extends javax.swing.JFrame implements Informacion{

    private TCPClient clienteLogica;
    private Comando LOG = new Comando(null, null, null, null);
    private String datosU = null;
    private String datosC = null;
    
    public InterfazGraficaLogin(TCPClient clienteLogica){
        this.clienteLogica = clienteLogica;
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Banner = new javax.swing.JLabel();
        home = new javax.swing.JButton();
        usuarioText = new javax.swing.JTextField();
        BannaerUser = new javax.swing.JLabel();
        BannerPassword = new javax.swing.JLabel();
        Confirmacion = new javax.swing.JButton();
        PasswordText = new javax.swing.JTextField();
        infoName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Banner.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Banner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Banner.setText("LOGIN");
        Banner.setToolTipText("");

        home.setText("Home");
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });

        usuarioText.setToolTipText("");
        usuarioText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioTextActionPerformed(evt);
            }
        });

        BannaerUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BannaerUser.setText("Usuario:");

        BannerPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BannerPassword.setText("Contraseña:");

        Confirmacion.setText("Confirmar");
        Confirmacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmacionActionPerformed(evt);
            }
        });

        PasswordText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordTextActionPerformed(evt);
            }
        });

        infoName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoName.setText("INFO...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(home))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(Banner, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(324, 324, 324)
                        .addComponent(Confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(BannaerUser, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addComponent(BannerPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usuarioText, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                            .addComponent(PasswordText)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(infoName, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(263, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(Banner, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usuarioText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BannaerUser))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BannerPassword)
                    .addComponent(PasswordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addComponent(Confirmacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(infoName)
                .addGap(138, 138, 138)
                .addComponent(home)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        InterfazGraficaInicio home = new InterfazGraficaInicio(clienteLogica);
        
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_homeActionPerformed

    private void usuarioTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioTextActionPerformed
        String nombreUsuario = usuarioText.getText();
        
        if(!nombreUsuario.isEmpty()){
            datosU=nombreUsuario;
        }
    }//GEN-LAST:event_usuarioTextActionPerformed

    private void ConfirmacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmacionActionPerformed
        LOG.setComando("LOGIN");
        LOG.setOrigen("Cliente");
        LOG.setDestino("Server");
        LOG.setDatos(datos());
        
        clienteLogica.mandarComando(LOG);
    }//GEN-LAST:event_ConfirmacionActionPerformed

    private void PasswordTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordTextActionPerformed
        String passwordUsuario = PasswordText.getText();
        
        if(!passwordUsuario.isEmpty()){
            datosC=passwordUsuario;
        }
    }//GEN-LAST:event_PasswordTextActionPerformed

    private String datos(){
        return datosU + "/" + datosC;
    }
    
    public void statusLog(String mensaje) {
        infoName.setText(mensaje);
        System.out.println(mensaje);
    }
    
    public void redInfoLabel(){
        infoName.setForeground(new java.awt.Color(255, 0, 0));
    }
    
    public void greenInfoLabel(){
        infoName.setForeground(new java.awt.Color(0, 255, 0));
    }
    
    public void logOk(){
//        InterfazGraficaPrincipal p = new InterfazGraficaPrincipal(clienteLogica);
//
//        p.setVisible(true);
//        this.dispose();
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TCPClient cliente = new TCPClient(); 
                new InterfazGraficaLogin(cliente).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BannaerUser;
    private javax.swing.JLabel Banner;
    private javax.swing.JLabel BannerPassword;
    private javax.swing.JButton Confirmacion;
    private javax.swing.JTextField PasswordText;
    private javax.swing.JButton home;
    private javax.swing.JLabel infoName;
    private javax.swing.JTextField usuarioText;
    // End of variables declaration//GEN-END:variables

    @Override
    public void ventanaEmergente(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
