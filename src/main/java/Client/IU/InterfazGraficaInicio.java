package Client.IU;

import Client.Comando;
import Client.Intefaces.Informacion;
import Client.TCPClient;
import javax.swing.JOptionPane;

public class InterfazGraficaInicio extends javax.swing.JFrame implements Informacion {

    private TCPClient clienteLogica;
    private Comando REG = new Comando("REG", "origen", "destino", "datos");

    public InterfazGraficaInicio(TCPClient clienteLogica) {
        this.clienteLogica = clienteLogica;
        initComponents();

        clienteLogica.addListener(this);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                clienteLogica.removeListener(InterfazGraficaInicio.this);
            }
        });
    }

@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Login = new javax.swing.JButton();
        Banner = new javax.swing.JLabel();
        Registro = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Login.setText("LOGIN");
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });

        Banner.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Banner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Banner.setText("BIENVENIDO A DISCORD");
        Banner.setToolTipText("");

        Registro.setText("REGISTER");
        Registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Registro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Banner, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(Banner, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(Registro, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Login)
                .addContainerGap(336, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
        InterfazGraficaLogin logIn = new InterfazGraficaLogin(clienteLogica);

        logIn.setVisible(true);
        
        clienteLogica.removeListener(this);
        this.dispose();
    }//GEN-LAST:event_LoginActionPerformed

    private void RegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistroActionPerformed
        if (clienteLogica != null) {
            clienteLogica.mandarComando(REG);
        }
    }//GEN-LAST:event_RegistroActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TCPClient cliente = new TCPClient();
                new InterfazGraficaInicio(cliente).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Banner;
    private javax.swing.JButton Login;
    private javax.swing.JButton Registro;
    // End of variables declaration//GEN-END:variables

    @Override
public void ventanaEmergente(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    
}
