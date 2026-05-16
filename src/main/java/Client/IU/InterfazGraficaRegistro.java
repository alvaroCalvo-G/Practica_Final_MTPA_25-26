package Client.IU;

import Client.Comando;
import Client.Intefaces.Informacion;
import Client.TCPClient;
import java.util.HashSet;
import javax.swing.JOptionPane;

public class InterfazGraficaRegistro extends javax.swing.JFrame implements Informacion{

    private TCPClient clienteLogica;
    private Comando REG = new Comando("REG", "CLIENTE", "SERVIDOR", null);

    private String datos = null;

    public InterfazGraficaRegistro(TCPClient clienteLogica) {
        this.clienteLogica = clienteLogica;
        initComponents();
        clienteLogica.setIUReg(this);

        clienteLogica.addListener(this);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                clienteLogica.removeListener(InterfazGraficaRegistro.this);
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BannerMain = new javax.swing.JLabel();
        UserName = new javax.swing.JTextField();
        userbanner = new javax.swing.JLabel();
        informationsBanner = new javax.swing.JLabel();
        home = new javax.swing.JButton();
        confirmacion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BannerMain.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BannerMain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BannerMain.setText("REGISTRO");

        UserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserNameActionPerformed(evt);
            }
        });

        userbanner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        userbanner.setText("Usuario:");

        informationsBanner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        informationsBanner.setText("INFO...");

        home.setText("HOME");
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });

        confirmacion.setText("Confirmar");
        confirmacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(BannerMain, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(userbanner, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(313, 313, 313)
                        .addComponent(informationsBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(home))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(374, 374, 374)
                        .addComponent(confirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(283, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(BannerMain, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userbanner, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(informationsBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(confirmacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE)
                .addComponent(home)
                .addGap(59, 59, 59))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserNameActionPerformed
        String usuario = UserName.getText();
        
        if (!usuario.isEmpty()) {
            datos = usuario;
        }
    }//GEN-LAST:event_UserNameActionPerformed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        InterfazGraficaInicio home = new InterfazGraficaInicio(clienteLogica);

        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_homeActionPerformed

    private void confirmacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmacionActionPerformed
        REG.setComando(datos);
        
        clienteLogica.mandarComando(REG);
    }//GEN-LAST:event_confirmacionActionPerformed

    public void infoBannerChange(String mensaje){
        informationsBanner.setText(mensaje);
    }
    
    public void infoBannerRed(){
        informationsBanner.setForeground(new java.awt.Color(255, 0, 0));
    }
    
    public void infoBannerGreen(){
        informationsBanner.setForeground(new java.awt.Color(0, 255, 0));
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
    private javax.swing.JLabel BannerMain;
    private javax.swing.JTextField UserName;
    private javax.swing.JButton confirmacion;
    private javax.swing.JButton home;
    private javax.swing.JLabel informationsBanner;
    private javax.swing.JLabel userbanner;
    // End of variables declaration//GEN-END:variables

    @Override
    public void ventanaEmergente(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
