package Client.IU;

import Client.Comando;
import Client.Intefaces.Informacion;
import Client.TCPClient;
import javax.swing.JOptionPane;

public class InterfazGraficaPrincipal extends javax.swing.JFrame implements Informacion {

    private TCPClient clienteLogica;
    private Comando MostrarRooms = new Comando("LIST_DROOMS", null, null, null);

    public InterfazGraficaPrincipal(TCPClient clienteLogica) {
        this.clienteLogica = clienteLogica;
        initComponents();
        clienteLogica.setIUMain(this);

        clienteLogica.addListener(this);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                clienteLogica.removeListener(InterfazGraficaPrincipal.this);
            }
        });

        bienvenidoTextoUser(clienteLogica.getNombreUsuario());

        jScrollPane1.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Lista = new javax.swing.JList<>();
        botonLista = new javax.swing.JButton();
        BienvenidoBanner = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Lista.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(Lista);

        botonLista.setText("Mostrar lista");
        botonLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonListaActionPerformed(evt);
            }
        });

        BienvenidoBanner.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        BienvenidoBanner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BienvenidoBanner.setText("Bienvenido");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonLista, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(48, 48, 48)
                .addComponent(BienvenidoBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BienvenidoBanner, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(botonLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonListaActionPerformed
        boolean visible = jScrollPane1.isVisible();
        jScrollPane1.setVisible(!visible);
        botonLista.setText(visible ? "Mostrar lista" : "Ocultar lista");
    }//GEN-LAST:event_botonListaActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TCPClient cliente = new TCPClient();
                new InterfazGraficaPrincipal(cliente).setVisible(true);
            }
        });
    }

    public void bienvenidoTextoUser(String nombre) {
        BienvenidoBanner.setText("Bienvenido " + nombre);
    }

    @Override
    public void ventanaEmergente(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BienvenidoBanner;
    private javax.swing.JList<String> Lista;
    private javax.swing.JButton botonLista;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
