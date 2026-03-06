package com.globaldocs;

import com.globaldocs.ui.MainApp;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Ejecutar la aplicación Java Swing en el hilo de eventos de AWT
        SwingUtilities.invokeLater(() -> {
            MainApp app = new MainApp();
            app.setVisible(true);
        });
    }
}
