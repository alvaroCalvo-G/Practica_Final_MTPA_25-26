package Client.IU;

import Client.Intefaces.Informacion;
import Client.TCPClient;
import javax.swing.JOptionPane;

public class InterfazGraficaSalon extends javax.swing.JFrame implements Informacion{

    private TCPClient clienteLogica;
    
    public InterfazGraficaSalon(TCPClient clienteLogica) {
        this.clienteLogica = clienteLogica;
        
        initComponents();
        clienteLogica.setIUSalon(this);
        
        clienteLogica.addListener(this);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                clienteLogica.removeListener(InterfazGraficaSalon.this);
            }
        });
        
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 969, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TCPClient cliente = new TCPClient();
                new InterfazGraficaSalon(cliente).setVisible(true);
            }
        });
    }

    @Override
    public void ventanaEmergente(String mensaje) {
       JOptionPane.showMessageDialog(this, mensaje);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
