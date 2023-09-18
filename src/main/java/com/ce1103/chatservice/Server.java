/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ce1103.chatservice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *La clase Servidor se encarga de brindar la funcionalidad de escuchar conexiones entrantes de clientes,
 * recibir y distribuir mensajes a todos los clientes conectados.
 *
 * @author Diego
 * @version 1.0
 */

public class Server extends javax.swing.JFrame implements Runnable {

    /**
     * Crea un nuevo form de la clase Server.
     * Inicializa la interfaz gráfica del servidor y los hilos del servidor.
     */
    public Server() {
        initComponents();
        Thread server_thread = new Thread(this);
        server_thread.start();
    }


    /**
     * El puerto que el servidor escucha
     */
    public static int serverPort;
    /**
     * Verifica si un puerto es nuevo o si ya se encuentra dentro de los puertos activos.
     *
     * @param port El puerto a verificar.
     * @param puertosOn La lista de los puertos activos.
     * @return true si el puerto no está dentro de la lista, false de lo contrario.
     */

    public static boolean newPort(int port, ArrayList<Integer> puertosOn){
        for (int puerto: puertosOn) {
            if (puerto == port) {
                return false;
            }
        }
        return true;
    }

    /**
     * Array donde se guardan los puertos de los clientes activos que están conectados 
     * con el servidor.
     */

    public static ArrayList<Integer> activePorts = new ArrayList<Integer>();
    /**
     *Este método es llamado desde el constructor para inicializar el formulario.
     *ADVERTENCIA: NO modifique este código. El contenido de este método es siempre
     *regenerado por el Editor de Formularios.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setTitle("Servidor");
        jScrollPane1 = new javax.swing.JScrollPane();
        messageLog = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        messageLog.setColumns(20);
        messageLog.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        messageLog.setLineWrap(true);
        messageLog.setRows(5);
        jScrollPane1.setViewportView(messageLog);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea messageLog;
    // End of variables declaration//GEN-END:variables
    /**
     * Inicializa el serversocket y busca un puerto libre para que el servidor pueda
     * recibir mensajes y lo muestra en el messageLog. Seguidamente inicia un while loop
     * para que el servidor siempre esté abierto a los nuevos mensajes que llegan de parte
     * de los clientes y mediante un for loop envía los mensajes a todos los clientes que
     * estén conectados a él.
     */
    @Override
    public void run(){
        try{
            ServerSocket server = new ServerSocket(0);
            serverPort = server.getLocalPort();

            messageLog.append("Servidor establecido en 127.0.0.1 Puerto: " + Integer.toString(serverPort) + "\n\n");

            while (true) {

                Socket reader_socket = server.accept();
                DataInputStream read_message = new DataInputStream(reader_socket.getInputStream());

                String received_message = read_message.readUTF();

                String strMensaje = "";

                if (received_message.matches("[0-9]+")) {
                    char lenPuertoChar = received_message.charAt(received_message.length()-1);
                    Integer lenPuertoInt = Character.getNumericValue(lenPuertoChar);
                    Integer finPuerto = received_message.length()-1;
                    Integer inicioPuerto = finPuerto - lenPuertoInt;
                    String strPuerto = received_message.substring(inicioPuerto, finPuerto);
                    Integer puerto = Integer.parseInt(strPuerto);

                    if (newPort(puerto, activePorts)) {
                        activePorts.add(puerto);
                    }
                } else {
                    strMensaje = received_message;
                }

                for (Integer port_to_send: activePorts) {
                    try{
                        Socket socket = new Socket("127.0.0.1", port_to_send);
                        DataOutputStream send = new DataOutputStream(socket.getOutputStream());

                        send.writeUTF(strMensaje);

                        socket.close();

                    } catch(Exception e){
                        System.out.println(e);
                    }
                }
                if (strMensaje.isEmpty()) {
                    //
                } else {
                    messageLog.append(strMensaje + "\n\n");
                }

                reader_socket.close();
            }

        } catch (Exception e){
            System.out.println(e);
        }
    }
}

