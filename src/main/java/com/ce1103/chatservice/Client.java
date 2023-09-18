/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 *
 */
package com.ce1103.chatservice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * La clase Client contiene la interfaz de usuario para que cada usuario
 * pueda mandar mensajes al servidor y recibir mensajes de otros usuarios.
 *
 * Contiene métodos para enviar mensajes y sincronizar el puerto recividor con el servidor.
 *
 * El IP, puerto y nombre de usuario se puede configurar a través del GUI.
 * @author Diego
 * @version 1.0
 */

public class Client extends javax.swing.JFrame implements Runnable {

    /**
     * Crea un nuevo form de la clase Client
     * Inicializa la interfaz gráfica del usuario y los hilos de los clientes.
     */
    public Client() {
        initComponents();
        Thread server_thread = new Thread(this);
        server_thread.start();
    }

    /**
     * El puerto donde el cliente recibe los mensajes.
     */
    public static int receivingPort;

    /**
     * Establece los widgets y las funcionalidades de la interfaz.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setTitle("Cliente");
        label_IP = new javax.swing.JLabel();
        tf_ip = new javax.swing.JTextField();
        label_port = new javax.swing.JLabel();
        tf_port = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageLog = new javax.swing.JTextArea();
        tf_message = new javax.swing.JTextField();
        button_send = new javax.swing.JButton();
        label_user = new javax.swing.JLabel();
        tf_user = new javax.swing.JTextField();
        button_sync_port = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label_IP.setText("IP:");

        tf_ip.setText("127.0.0.1");

        label_port.setText("PORT:");

        messageLog.setColumns(20);
        messageLog.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        messageLog.setLineWrap(true);
        messageLog.setRows(5);
        jScrollPane1.setViewportView(messageLog);

        tf_message.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        button_send.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        button_send.setText("SEND");
        button_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_sendActionPerformed(evt);
            }
        });

        label_user.setText("USER:");

        button_sync_port.setText("Sync");
        button_sync_port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_sync_portActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(17, 17, 17)
                                                .addComponent(label_IP)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_ip, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(label_user)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_user, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(48, 48, 48)
                                                .addComponent(label_port, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_port, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(button_sync_port))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(tf_message, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(button_send))
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label_IP)
                                        .addComponent(tf_ip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label_port)
                                        .addComponent(tf_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label_user)
                                        .addComponent(tf_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button_sync_port))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tf_message, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button_send, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Envia mensajes de cliente al servidor.
     * @param evt Acción del botón para enviar el mensaje y el puerto al servidor.
     */

    private void button_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_sendActionPerformed
        String target_port = tf_port.getText();
        int intPort = Integer.parseInt(target_port);
        try{
            Socket socket = new Socket(tf_ip.getText(), intPort);
            DataOutputStream send = new DataOutputStream(socket.getOutputStream());

            send.writeUTF(tf_user.getText() + ": " + tf_message.getText());

            tf_message.setText("");

            socket.close();

        } catch(Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_button_sendActionPerformed

    /**
     * Sincroniza el puerto de recepción del cliente con el puerto del servidor.
     * Envía la información del puerto de recepción al servidor para la sincronización.
     * @param evt Botón para sincronizar el puerto receptor con el servidor.
     */
    private void button_sync_portActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_sync_portActionPerformed
        String target_port = tf_port.getText();
        int intPort = Integer.parseInt(target_port);
        try{
            Socket socket = new Socket(tf_ip.getText(),intPort);
            DataOutputStream send = new DataOutputStream(socket.getOutputStream());

            String portString = Integer.toString(receivingPort);
            Integer portLen = portString.length();

            send.writeUTF(portString + Integer.toString(portLen));

            socket.close();

        } catch(Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_button_sync_portActionPerformed

    /**
     * El método main que empieza la aplicación del cliente.
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
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_send;
    private javax.swing.JButton button_sync_port;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_IP;
    private javax.swing.JLabel label_port;
    private javax.swing.JLabel label_user;
    private javax.swing.JTextArea messageLog;
    private javax.swing.JTextField tf_ip;
    private javax.swing.JTextField tf_message;
    private javax.swing.JTextField tf_port;
    private javax.swing.JTextField tf_user;

    /**
     * Inicializa el serversocket y busca un puerto libre para que el cliente pueda
     * recibir mensajes. Seguidamente inicia un while loop para que el cliente siempre 
     * esté abierto a los nuevos mensajes que llegan de parte del servidor.
     */
    @Override
    public void run() {
        try{
            ServerSocket server = new ServerSocket(0);
            receivingPort = server.getLocalPort();

            while (true) {

                Socket reader_socket = server.accept();
                DataInputStream read_message = new DataInputStream(reader_socket.getInputStream());

                String received_message = read_message.readUTF();

                if (received_message.isEmpty()) {
                    //
                } else {
                    messageLog.append(received_message + "\n\n");
                }

                reader_socket.close();
            }

        } catch (Exception e){
            System.out.println(e);
        }
    }
    // End of variables declaration//GEN-END:variables
}
