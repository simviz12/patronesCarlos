package com.globaldocs.ui;

import com.globaldocs.batch.BatchProcessor;
import com.globaldocs.model.Country;
import com.globaldocs.model.Document;
import com.globaldocs.model.DocumentFormat;
import com.globaldocs.model.DocumentType;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainApp extends JFrame {

    // ==================== PALETA DE COLORES ====================
    private static final Color PRIMARY = new Color(26, 58, 108);
    private static final Color PRIMARY_LIGHT = new Color(41, 82, 148);
    private static final Color ACCENT = new Color(0, 150, 136);
    private static final Color ACCENT_DARK = new Color(0, 121, 107);
    private static final Color DANGER = new Color(211, 47, 47);
    private static final Color SUCCESS_COLOR = new Color(46, 125, 50);
    private static final Color BG_MAIN = new Color(240, 242, 245);
    private static final Color BG_PANEL = Color.WHITE;
    private static final Color TEXT_PRIMARY = new Color(33, 33, 33);
    private static final Color TEXT_SECONDARY = new Color(117, 117, 117);
    private static final Color BORDER_COLOR = new Color(224, 224, 224);

    // ==================== DATOS ====================
    private List<Document> lote = new ArrayList<>();
    private JTextArea areaResultados;
    private DefaultListModel<String> listModel;
    private JList<String> listaDocumentos;
    private JLabel lblContadorLote;

    public MainApp() {
        setTitle("GlobalDocs Solutions - Sistema de Procesamiento de Documentos Empresariales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 680);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(900, 550));
        getContentPane().setBackground(BG_MAIN);
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setBackground(BG_MAIN);

        // ==================== HEADER ====================
        JPanel header = createHeader();
        mainPanel.add(header, BorderLayout.NORTH);

        // ==================== CONTENIDO ====================
        JPanel contentPanel = new JPanel(new BorderLayout(12, 0));
        contentPanel.setBackground(BG_MAIN);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(12, 15, 15, 15));

        JPanel leftPane = createLeftPanel();
        JPanel rightPane = createRightPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);
        splitPane.setDividerLocation(420);
        splitPane.setDividerSize(6);
        splitPane.setBorder(null);
        splitPane.setBackground(BG_MAIN);

        contentPanel.add(splitPane, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // ==================== FOOTER ====================
        JPanel footer = createFooter();
        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // ==================== HEADER ====================
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(PRIMARY);
        header.setPreferredSize(new Dimension(0, 65));
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel("GlobalDocs Solutions");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);

        JLabel subtitleLabel = new JLabel("Sistema de Procesamiento de Documentos Empresariales");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(180, 200, 230));

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);

        lblContadorLote = new JLabel("Lote: 0 documentos");
        lblContadorLote.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblContadorLote.setForeground(new Color(180, 220, 255));

        header.add(titlePanel, BorderLayout.WEST);
        header.add(lblContadorLote, BorderLayout.EAST);

        return header;
    }

    // ==================== PANEL IZQUIERDO ====================
    private JPanel createLeftPanel() {
        JPanel leftPane = new JPanel();
        leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.Y_AXIS));
        leftPane.setBackground(BG_MAIN);

        // --- Sección: Cargar Archivos ---
        JPanel fileSection = createCardPanel("Cargar Archivos");
        fileSection.setLayout(new BoxLayout(fileSection, BoxLayout.Y_AXIS));

        JLabel fileInstruction = new JLabel(
                "<html>Seleccione uno o varios archivos. Formatos permitidos:<br/><b>.pdf, .doc, .docx, .md, .csv, .txt, .xlsx</b></html>");
        fileInstruction.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        fileInstruction.setForeground(TEXT_SECONDARY);
        fileInstruction.setAlignmentX(Component.LEFT_ALIGNMENT);
        fileInstruction.setBorder(BorderFactory.createEmptyBorder(0, 5, 10, 5));

        JButton btnSeleccionarArchivos = createStyledButton("Seleccionar Archivos...", ACCENT, Color.WHITE);
        btnSeleccionarArchivos.setAlignmentX(Component.LEFT_ALIGNMENT);

        fileSection.add(fileInstruction);
        fileSection.add(btnSeleccionarArchivos);

        // --- Sección: Registro Manual ---
        JPanel manualSection = createCardPanel("Registro Manual");
        manualSection.setLayout(new BoxLayout(manualSection, BoxLayout.Y_AXIS));

        JTextField txtNombre = new JTextField(15);
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtNombre.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR), BorderFactory.createEmptyBorder(6, 8, 6, 8)));

        JComboBox<DocumentType> cbTipo = createStyledCombo(DocumentType.values());
        JComboBox<DocumentFormat> cbFormato = createStyledCombo(DocumentFormat.values());
        JComboBox<Country> cbPais = createStyledCombo(Country.values());

        JPanel formGrid = new JPanel(new GridLayout(4, 2, 8, 8));
        formGrid.setBackground(BG_PANEL);
        formGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
        formGrid.add(createFieldLabel("Nombre:"));
        formGrid.add(txtNombre);
        formGrid.add(createFieldLabel("Tipo Doc:"));
        formGrid.add(cbTipo);
        formGrid.add(createFieldLabel("Formato:"));
        formGrid.add(cbFormato);
        formGrid.add(createFieldLabel("País Origen:"));
        formGrid.add(cbPais);

        JButton btnAgregar = createStyledButton("Añadir a Lote", PRIMARY_LIGHT, Color.WHITE);
        btnAgregar.setAlignmentX(Component.LEFT_ALIGNMENT);

        manualSection.add(formGrid);
        manualSection.add(Box.createVerticalStrut(10));
        manualSection.add(btnAgregar);

        // --- Sección: Documentos en el Lote ---
        JPanel loteSection = createCardPanel("Documentos en el Lote");
        loteSection.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        listaDocumentos = new JList<>(listModel);
        listaDocumentos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        listaDocumentos.setSelectionBackground(new Color(200, 230, 255));
        listaDocumentos.setFixedCellHeight(24);
        JScrollPane listScroll = new JScrollPane(listaDocumentos);
        listScroll.setPreferredSize(new Dimension(0, 120));
        listScroll.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));

        JPanel loteButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        loteButtons.setBackground(BG_PANEL);
        JButton btnEliminar = createStyledButton("Eliminar Seleccionado", DANGER, Color.WHITE);
        JButton btnLimpiar = createStyledButton("Limpiar Todo", TEXT_SECONDARY, Color.WHITE);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 10));
        btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 10));
        loteButtons.add(btnEliminar);
        loteButtons.add(btnLimpiar);

        loteSection.add(listScroll, BorderLayout.CENTER);
        loteSection.add(loteButtons, BorderLayout.SOUTH);

        // --- Agregar todo al panel izquierdo ---
        leftPane.add(fileSection);
        leftPane.add(Box.createVerticalStrut(8));
        leftPane.add(manualSection);
        leftPane.add(Box.createVerticalStrut(8));
        leftPane.add(loteSection);

        // ==================== EVENTOS ====================

        // Seleccionar múltiples archivos
        btnSeleccionarArchivos.addActionListener(e -> seleccionarMultiplesArchivos(cbTipo, cbPais));

        // Agregar manual
        btnAgregar.addActionListener(e -> {
            String name = txtNombre.getText();
            DocumentType type = (DocumentType) cbTipo.getSelectedItem();
            DocumentFormat format = (DocumentFormat) cbFormato.getSelectedItem();
            Country country = (Country) cbPais.getSelectedItem();

            if (name == null || name.trim().isEmpty()) {
                mostrarError("Por favor ingrese un nombre para el documento.");
                return;
            }

            Document newDoc = new Document(name.trim(), type, format, country);
            lote.add(newDoc);
            listModel.addElement(newDoc.getName() + "  |  " + newDoc.getType() + "  |  ." + newDoc.getFormat() + "  |  "
                    + newDoc.getOriginCountry() + "  (Manual)");
            actualizarContador();

            txtNombre.setText("");
            cbTipo.setSelectedIndex(0);
            cbFormato.setSelectedIndex(0);
            cbPais.setSelectedIndex(0);
        });

        // Eliminar seleccionado
        btnEliminar.addActionListener(e -> {
            int idx = listaDocumentos.getSelectedIndex();
            if (idx >= 0) {
                lote.remove(idx);
                listModel.remove(idx);
                actualizarContador();
            }
        });

        // Limpiar todo
        btnLimpiar.addActionListener(e -> {
            lote.clear();
            listModel.clear();
            areaResultados.setText("");
            actualizarContador();
        });

        return leftPane;
    }

    // ==================== PANEL DERECHO ====================
    private JPanel createRightPanel() {
        JPanel rightPane = createCardPanel("Resultados del Procesamiento");
        rightPane.setLayout(new BorderLayout(0, 10));

        areaResultados = new JTextArea();
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font("Consolas", Font.PLAIN, 12));
        areaResultados.setBackground(new Color(250, 250, 250));
        areaResultados.setForeground(TEXT_PRIMARY);
        areaResultados.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollResults = new JScrollPane(areaResultados);
        scrollResults.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));

        JButton btnProcesar = createStyledButton("  Procesar Lote  ", PRIMARY, Color.WHITE);
        btnProcesar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnProcesar.setPreferredSize(new Dimension(0, 42));
        JPanel procPanel = new JPanel(new BorderLayout());
        procPanel.setBackground(BG_PANEL);
        procPanel.add(btnProcesar, BorderLayout.CENTER);

        rightPane.add(scrollResults, BorderLayout.CENTER);
        rightPane.add(procPanel, BorderLayout.SOUTH);

        btnProcesar.addActionListener(e -> {
            if (lote.isEmpty()) {
                mostrarError("No hay documentos en el lote para procesar.");
                return;
            }
            BatchProcessor processor = new BatchProcessor();
            String res = processor.processBatch(lote);
            areaResultados.setText(res);
            areaResultados.setCaretPosition(0);
        });

        return rightPane;
    }

    // ==================== FOOTER ====================
    private JPanel createFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(new Color(55, 55, 65));
        footer.setPreferredSize(new Dimension(0, 30));
        footer.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        JLabel copyright = new JLabel("GlobalDocs Solutions © 2026 - Taller Patrón Factory Method");
        copyright.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        copyright.setForeground(new Color(170, 170, 180));

        JLabel countries = new JLabel("Colombia  |  México  |  Argentina  |  Chile");
        countries.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        countries.setForeground(new Color(170, 170, 180));

        footer.add(copyright, BorderLayout.WEST);
        footer.add(countries, BorderLayout.EAST);

        return footer;
    }

    // ==================== SELECCIÓN MÚLTIPLE DE ARCHIVOS ====================
    private void seleccionarMultiplesArchivos(JComboBox<DocumentType> cbTipo, JComboBox<Country> cbPais) {
        // First ask for Type and Country that will apply to all files
        JPanel panel = new JPanel(new GridLayout(2, 2, 8, 8));
        JComboBox<DocumentType> tipoBatch = createStyledCombo(DocumentType.values());
        JComboBox<Country> paisBatch = createStyledCombo(Country.values());
        panel.add(createFieldLabel("Tipo de Documento:"));
        panel.add(tipoBatch);
        panel.add(createFieldLabel("País de Origen:"));
        panel.add(paisBatch);

        int option = JOptionPane.showConfirmDialog(this, panel,
                "Configurar tipo y país para los archivos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (option != JOptionPane.OK_OPTION)
            return;

        DocumentType selectedType = (DocumentType) tipoBatch.getSelectedItem();
        Country selectedCountry = (Country) paisBatch.getSelectedItem();

        // Open file chooser with multi-selection
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Documentos");
        fileChooser.setMultiSelectionEnabled(true);

        String[] exts = DocumentFormat.getSupportedExtensions();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Documentos (pdf, doc, docx, md, csv, txt, xlsx)", exts);
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            int addedCount = 0;
            StringBuilder rejected = new StringBuilder();

            for (File file : selectedFiles) {
                String fileName = file.getName();
                String extension = "";
                int dotIndex = fileName.lastIndexOf('.');
                if (dotIndex > 0) {
                    extension = fileName.substring(dotIndex + 1);
                }

                DocumentFormat detectedFormat = DocumentFormat.fromExtension(extension);
                if (detectedFormat == null) {
                    rejected.append("  • ").append(fileName).append(" (.").append(extension).append(")\n");
                    continue;
                }

                String baseName = fileName.substring(0, dotIndex);
                Document newDoc = new Document(baseName, selectedType, detectedFormat, selectedCountry, file);
                lote.add(newDoc);
                listModel.addElement(baseName + "  |  " + selectedType + "  |  ." + detectedFormat + "  |  "
                        + selectedCountry + "  (Archivo)");
                addedCount++;
            }

            actualizarContador();

            // Summary message
            StringBuilder summary = new StringBuilder();
            summary.append("Se añadieron ").append(addedCount).append(" archivo(s) al lote.\n");
            if (rejected.length() > 0) {
                summary.append("\nArchivos rechazados por formato no válido:\n").append(rejected);
            }

            JOptionPane.showMessageDialog(this, summary.toString(),
                    "Resultado de Carga",
                    addedCount > 0 ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);
        }
    }

    // ==================== UTILIDADES DE UI ====================

    private JPanel createCardPanel(String title) {
        JPanel card = new JPanel();
        card.setBackground(BG_PANEL);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                "  " + title + "  ",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13), PRIMARY);
        card.setBorder(BorderFactory.createCompoundBorder(
                titledBorder, BorderFactory.createEmptyBorder(10, 12, 10, 12)));
        return card;
    }

    private JButton createStyledButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bg.darker(), 1),
                BorderFactory.createEmptyBorder(8, 16, 8, 16)));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Hover effect
        Color hoverBg = bg.brighter();
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(hoverBg);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(bg);
            }
        });
        return btn;
    }

    private JLabel createFieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(TEXT_PRIMARY);
        return label;
    }

    private <T> JComboBox<T> createStyledCombo(T[] items) {
        JComboBox<T> combo = new JComboBox<>(items);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        combo.setBackground(Color.WHITE);
        return combo;
    }

    private void actualizarContador() {
        lblContadorLote.setText("Lote: " + lote.size() + " documento(s)");
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Validación", JOptionPane.WARNING_MESSAGE);
    }
}
