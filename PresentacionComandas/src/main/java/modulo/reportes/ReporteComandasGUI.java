/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package modulo.reportes;

import DTOs.viejos.ComandaViejoDTO;
import DTOs.viejos.MesaViejoDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controlGUI.ControlGUI;
import excepciones.NegocioException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.Main;
import manejadoresBO.ManejadorBO;
import modulo.comandas.IComandaBO;
import modulo.comandas.IMesaBO;
import modulo.ingredientes.BuscadorIngredienteGUI;

/**
 *
 * @author Beto_
 */
public class ReporteComandasGUI extends javax.swing.JFrame {
    ControlGUI control = obtenerControlador();
    IComandaBO comandaBO = ManejadorBO.crearComandaBO();
    IMesaBO mesaBO = ManejadorBO.crearMesaBO();
//    ICliente clienteBO = ManejadorBO.crearClienteBO;
    List<ComandaViejoDTO> listaComandas;
    DefaultTableModel modelo;
    
    //VARIABLES DEL REPORTE
        // tipos de letra, diferente para el titulo, cabecera para que se vea más bonito
    private static final Font FUENTE_TITULO = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
    private static final Font FUENTE_CABECERA = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
    private static final Font FUENTE_NORMAL = FontFactory.getFont(FontFactory.HELVETICA, 10);
    
        // Formateo para la fecha para que se vea más bonita tmbn
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
    
    /**
     * Creates new form ReporteComandasGUI
     */
    public ReporteComandasGUI() {
        initComponents();
        modelo = (DefaultTableModel) tblComandas.getModel();
        listaComandas = new ArrayList<>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        dtcFechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        dtcFechaFin = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblComandas = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();
        btnVistaPrevia = new javax.swing.JButton();
        btnGenerarPDF1 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Broadway", 0, 18)); // NOI18N
        jLabel1.setText("Reporte de comandas");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Fecha Inicio");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Fecha Fin");

        tblComandas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Hora", "Mesa", "Total de venta", "Estado", "Cliente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblComandas);

        btnRegresar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnVistaPrevia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnVistaPrevia.setText("Vista Previa");
        btnVistaPrevia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVistaPreviaActionPerformed(evt);
            }
        });

        btnGenerarPDF1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGenerarPDF1.setText("Generar PDF");
        btnGenerarPDF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPDF1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtcFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtcFechaFin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)))
                .addGap(94, 94, 94))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(189, 189, 189))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126)
                        .addComponent(btnGenerarPDF1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108)
                        .addComponent(btnVistaPrevia, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtcFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(btnVistaPrevia)
                    .addComponent(btnGenerarPDF1))
                .addGap(26, 26, 26))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVistaPreviaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVistaPreviaActionPerformed
        // TODO add your handling code here:
        LocalDateTime fechaInicio = null;
        LocalDateTime fechaFin = null;
        if(dtcFechaInicio.getDate() != null){
            fechaInicio = toLocalDateTime(dtcFechaInicio.getDate());
        }
        if(dtcFechaFin.getDate() != null){
            fechaFin = toLocalDateTime(dtcFechaInicio.getDate());
        }

        if(fechaInicio == null && fechaFin != null){
            JOptionPane.showMessageDialog(this, "Selecciones una fecha de inicio", "Campos incompletos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(fechaInicio != null && fechaFin == null){
            JOptionPane.showMessageDialog(this, "Selecciones una fecha fin", "Campos incompletos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        cargarComandasPorFecha(toLocalDateTime(dtcFechaInicio.getDate()), toLocalDateTime(dtcFechaFin.getDate()));
        
        
    }//GEN-LAST:event_btnVistaPreviaActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        control.ocultarFrameActual();
        control.mostrarFrame("MenuReportesGUI");
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnGenerarPDF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPDF1ActionPerformed
        // TODO add your handling code here:
        if(tblComandas.getRowCount() <= 0){
            JOptionPane.showMessageDialog(this, "No se puede generar el reporte sin vista previa", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(listaComandas == null){
            JOptionPane.showMessageDialog(this, "No se puede generar el reporte sin comandas", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        crearReporteComandas(listaComandas, "Reporte_comandas.pdf");
        
        JOptionPane.showMessageDialog(this, "Reporte generado (No es cierto) ", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnGenerarPDF1ActionPerformed

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
            java.util.logging.Logger.getLogger(ReporteComandasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteComandasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteComandasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteComandasGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReporteComandasGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarPDF1;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnVistaPrevia;
    private com.toedter.calendar.JDateChooser dtcFechaFin;
    private com.toedter.calendar.JDateChooser dtcFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblComandas;
    // End of variables declaration//GEN-END:variables

    private ControlGUI obtenerControlador(){
        return Main.controlGUI;
    }
    
    private LocalDateTime toLocalDateTime (Date date) {
        if (date == null) {
            return null;
        }
        // Convertir java.util.Date a java.time.Instant
        Instant instant = date.toInstant();

        // Obtener la zona horaria del sistema
        ZoneId systemZone = ZoneId.systemDefault();

        // Convertir Instant a ZonedDateTime (Instant con zona horaria)
        LocalDateTime localDateTime = instant.atZone(systemZone).toLocalDateTime();

        // Extraer la parte de la fecha (LocalDate)
        return localDateTime;
    }
    
    
    private void cargarComandasPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        //Empezar de 0 la tabla
        modelo.setRowCount(0);
        listaComandas.clear();
        
        try{
            List<ComandaViejoDTO> comandas = comandaBO.obtenerPorFechas(fechaInicio, fechaFin);
            
            if (comandas == null || comandas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron comandas", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            for (ComandaViejoDTO comanda : comandas) {
                String fecha = crearFechaFormato(
                        comanda.getFechaHora().getYear(), 
                        comanda.getFechaHora().getMonthValue(),
                        comanda.getFechaHora().getDayOfMonth()
                    );
                String hora = crearHoraFormato(
                        comanda.getFechaHora().getHour(),
                        comanda.getFechaHora().getMinute()
                );
                
                MesaViejoDTO mesa = obtenerMesa(comanda.getIdMesa());
                if(mesa == null){
                    JOptionPane.showMessageDialog(this, "Chale, esto no debería pasar", "Error", JOptionPane.ERROR);
                }
                
                modelo.addRow(new Object[] {
                    fecha,
                    hora,
                    mesa.getNumeroMesa(),
                    comanda.getTotalVenta(),
                    comanda.getEstado(),
                    comanda.getIdCliente() //Aqui va el nombre VD BRANDON
                });
                listaComandas.add(comanda);
            }
        }catch(NegocioException ne){
            JOptionPane.showMessageDialog(this, "Error al buscar comandas por rango de fechas: " + ne.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String crearFechaFormato(int anio, int mes, int dia){
        return (dia + "-" + mes + "-" + anio);
    }
    
    private String crearHoraFormato(int hora, int minuto){
        return (hora + ":" + minuto);
    }
    
    private MesaViejoDTO obtenerMesa(Long idMesa){
        try{
            return mesaBO.obtenerPorId(idMesa);
        }catch(NegocioException ne){
            JOptionPane.showMessageDialog(this, "No se obtuvo una mesa relacionada a la comanda", "Error", JOptionPane.ERROR);
        }
        return null;
    }
    
//    private ClienteViejoDTO obtenerCliente(Long idCliente){
//        
//    }
    
    private void crearReporteComandas(List<ComandaViejoDTO> comandas, String nombreArchivo){
        try{
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
            document.open();

            //Agregar el título
            Paragraph titulo = new Paragraph("Reporte de Comandas", FUENTE_TITULO);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(new Paragraph(" "));
            
            //Agregar texto descriptivo si esta seleccionado el filtro de fechas
            Paragraph descripcion;
            if(dtcFechaInicio.getDate() != null && dtcFechaFin.getDate() != null){
                descripcion = new Paragraph("Reporte de comandas desde '" + dtcFechaInicio.getDate().toString() +
                                                       "' hasta '" + dtcFechaFin.getDate().toString() + "' ", FUENTE_NORMAL);
            }else{
                descripcion = new Paragraph("Reporte de todas las comandas", FUENTE_NORMAL);
            }
            descripcion.setAlignment(Element.ALIGN_CENTER);
            document.add(descripcion);
            document.add(new Paragraph(" "));

            //Crear la tabla
            PdfPTable tabla = new PdfPTable(6);
            tabla.setWidthPercentage(100);

            //Agregar las cabeceras de la tabla
            agregarCabecerasComanda(tabla);

            //Aagregar los datos de las comandas a la tabla
            for (ComandaViejoDTO comanda : comandas) {
                agregarFilaComanda(tabla, comanda);
            }

            //Agregar la tabla al documento
            document.add(tabla);

            document.close(); //Cerramos para que no se le metan bichitos
        }catch(DocumentException | FileNotFoundException e){
            JOptionPane.showMessageDialog(this, "Error al crear el PDF del reporte", "Error", JOptionPane.ERROR);
        }
    }
    
    //Se podría hacer un método, pero así mejor para mayor legibilidad
    private void agregarCabecerasComanda(PdfPTable tabla) {
        agregarCeldaCabecera(tabla, "Fecha");
        agregarCeldaCabecera(tabla, "Hora");
        agregarCeldaCabecera(tabla, "Mesa");
        agregarCeldaCabecera(tabla, "Total venta");
        agregarCeldaCabecera(tabla, "Estado");
        agregarCeldaCabecera(tabla, "Cliente");
    }
    
    private void agregarCeldaCabecera(PdfPTable tabla, String texto) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, FUENTE_CABECERA));
        tabla.addCell(cell);
    }
    
    //Lo mismo, pero para la info
    private void agregarFilaComanda(PdfPTable tabla, ComandaViejoDTO comanda) {
        String numMesa = obtenerMesa(comanda.getIdMesa()).getNumeroMesa().toString();
        agregarCeldaNormal(tabla, comanda.getFechaHora() != null ? comanda.getFechaHora().format(FORMATO_FECHA) : "");
        agregarCeldaNormal(tabla, comanda.getFechaHora() != null ? comanda.getFechaHora().format(FORMATO_HORA) : "");
        agregarCeldaNormal(tabla, comanda.getIdMesa() != null ? numMesa : "");
        agregarCeldaNormal(tabla, comanda.getTotalVenta() != null ? String.format("%.2f", comanda.getTotalVenta()) : ""); // Formatear a 2 decimales
        agregarCeldaNormal(tabla, comanda.getEstado() != null ? comanda.getEstado().toString() : "");
        agregarCeldaNormal(tabla, comanda.getIdCliente() != null ? comanda.getIdCliente().toString() : "");
    }
    
    private void agregarCeldaNormal(PdfPTable tabla, String texto) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, FUENTE_NORMAL));
        tabla.addCell(cell);
    }
}
