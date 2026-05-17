package Client.IU;

import Client.Comando;
import Client.Intefaces.Informacion;
import Client.TCPClient;
import javax.swing.JOptionPane;

public class InterfazGraficaSalon extends javax.swing.JFrame implements Informacion {

    private TCPClient clienteLogica;
    private String salon;
    private Comando msg;

    public InterfazGraficaSalon(TCPClient clienteLogica, String salon) {
        this.clienteLogica = clienteLogica;
        this.salon = salon;
        this.msg = new Comando("MSG", clienteLogica.getNombreUsuario(), salon, null);

        initComponents();
        setTitle("Salon: " + salon);
        clienteLogica.setIUSalon(this);
        clienteLogica.addListener(this);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                clienteLogica.removeListener(InterfazGraficaSalon.this);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        areaChat = new javax.swing.JTextArea();
        scrollChat = new javax.swing.JScrollPane(areaChat);
        campoMensaje = new javax.swing.JTextField();
        botonEnviar = new javax.swing.JButton();
        bannerSalon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        areaChat.setEditable(false);
        areaChat.setLineWrap(true);
        areaChat.setWrapStyleWord(true);

        botonEnviar.setText("Enviar");
        botonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarMensaje();
            }
        });

        // Enviar tambien con Enter
        campoMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarMensaje();
            }
        });

        bannerSalon.setFont(new java.awt.Font("Segoe UI", 1, 16));
        bannerSalon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bannerSalon.setText(salon);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bannerSalon, javax.swing.GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
                    .addComponent(scrollChat)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(campoMensaje)
                        .addGap(10, 10, 10)
                        .addComponent(botonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(bannerSalon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(scrollChat, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        pack();
    }

    private void enviarMensaje() {
        String texto = campoMensaje.getText().trim();

        if (texto.isEmpty()) return;

        if (texto.length() > 190) {
            ventanaEmergente("El mensaje no puede superar 190 caracteres.");
            return;
        }

        msg.setDatos(texto);
        clienteLogica.mandarComando(msg);
        campoMensaje.setText("");
    }

    public void recibirMensaje(String usuario, String mensaje) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            areaChat.append(usuario + ": " + mensaje + "\n");
            areaChat.setCaretPosition(areaChat.getDocument().getLength());
        });
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            TCPClient cliente = new TCPClient();
            new InterfazGraficaSalon(cliente, "TestSalon").setVisible(true);
        });
    }

    @Override
    public void ventanaEmergente(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    // Variables declaration
    private javax.swing.JTextArea areaChat;
    private javax.swing.JScrollPane scrollChat;
    private javax.swing.JTextField campoMensaje;
    private javax.swing.JButton botonEnviar;
    private javax.swing.JLabel bannerSalon;
    // End of variables declaration
}