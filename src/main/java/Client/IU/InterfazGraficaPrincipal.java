package Client.IU;

import Client.Comando;
import Client.Intefaces.Informacion;
import Client.TCPClient;
import javax.swing.JOptionPane;

public class InterfazGraficaPrincipal extends javax.swing.JFrame implements Informacion {

    private TCPClient clienteLogica;
    private Comando MostrarRooms = new Comando("LIST_DROOMS", null, "Server", null);
    private Comando join = new Comando("JOIN", null, "Server", null);
    private Comando leave = new Comando("LEAVE", null, "Server", null);

    public InterfazGraficaPrincipal(TCPClient clienteLogica) {
        MostrarRooms.setOrigen(clienteLogica.getNombreUsuario());
        join.setOrigen(clienteLogica.getNombreUsuario());
        leave.setOrigen(clienteLogica.getNombreUsuario());

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
        joinRoom = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        AbandonarButtom = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListUnido = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Lista.setModel(new javax.swing.DefaultListModel<>());
        jScrollPane1.setViewportView(Lista);

        botonLista.setText("Salones disponibles");
        botonLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonListaActionPerformed(evt);
            }
        });

        BienvenidoBanner.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        BienvenidoBanner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BienvenidoBanner.setText("Bienvenido");

        joinRoom.setText("Unirse");
        joinRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinRoomActionPerformed(evt);
            }
        });

        logoutButton.setText("logout");

        AbandonarButtom.setText("Abandonar");
        AbandonarButtom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbandonarButtomActionPerformed(evt);
            }
        });

        ListUnido.setModel(new javax.swing.DefaultListModel<>());
        jScrollPane2.setViewportView(ListUnido);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(AbandonarButtom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(joinRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(48, 48, 48)
                .addComponent(BienvenidoBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BienvenidoBanner, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(botonLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(joinRoom)
                .addGap(12, 12, 12)
                .addComponent(AbandonarButtom, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logoutButton)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonListaActionPerformed
        boolean visible = jScrollPane1.isVisible();
        jScrollPane1.setVisible(!visible);
        botonLista.setText(visible ? "Salones disponibles" : "Ocultar lista");

        if (!visible) {
            clienteLogica.mandarComando(MostrarRooms);
        }

    }//GEN-LAST:event_botonListaActionPerformed

    private void joinRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinRoomActionPerformed
        String salonSeleccionado = Lista.getSelectedValue();

        if (salonSeleccionado == null) {
            ventanaEmergente("Selecciona un salon de la lista.");
            return;
        }

        join.setDatos(salonSeleccionado);

        clienteLogica.mandarComando(join);
    }//GEN-LAST:event_joinRoomActionPerformed

    private void AbandonarButtomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbandonarButtomActionPerformed
        String salonSeleccionado = ListUnido.getSelectedValue();

        if (salonSeleccionado == null) {
            ventanaEmergente("Selecciona un salon de la lista para abandonarlo.");
            return;
        }

        leave.setDatos(salonSeleccionado);
        clienteLogica.mandarComando(leave);
    }//GEN-LAST:event_AbandonarButtomActionPerformed

    public void unirseASalon(String salon) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                javax.swing.DefaultListModel<String> RoomDisponibles = (javax.swing.DefaultListModel<String>) Lista.getModel();
                RoomDisponibles.removeElement(salon);

                javax.swing.DefaultListModel<String> RoomUnidos = (javax.swing.DefaultListModel<String>) ListUnido.getModel();
                RoomUnidos.addElement(salon);

                Lista.revalidate();
                Lista.repaint();
                ListUnido.revalidate();
                ListUnido.repaint();
            }
        });
    }
    
    public void abandonarSalon(String salon) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                javax.swing.DefaultListModel<String> RoomUnidos = (javax.swing.DefaultListModel<String>) ListUnido.getModel();
                RoomUnidos.removeElement(salon);

                javax.swing.DefaultListModel<String> RoomDisponibles = (javax.swing.DefaultListModel<String>) Lista.getModel();
                RoomDisponibles.addElement(salon);

                ListUnido.revalidate();
                ListUnido.repaint();
                Lista.revalidate();
                Lista.repaint();
            }
        });
    }

    public void actualizarSalones(String[] salones) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                javax.swing.DefaultListModel<String> modelo = new javax.swing.DefaultListModel<>();
                for (String salon : salones) {
                    modelo.addElement(salon);
                }
                Lista.setModel(modelo);

                Lista.revalidate();
                Lista.repaint();
            }
        });
    }

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
    private javax.swing.JButton AbandonarButtom;
    private javax.swing.JLabel BienvenidoBanner;
    private javax.swing.JList<String> ListUnido;
    private javax.swing.JList<String> Lista;
    private javax.swing.JButton botonLista;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton joinRoom;
    private javax.swing.JButton logoutButton;
    // End of variables declaration//GEN-END:variables

}
