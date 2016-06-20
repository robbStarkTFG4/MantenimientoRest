/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.servlet;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mim.entities.Equipo;
import com.mim.entities.Fotos;
import com.mim.entities.HistorialDetalles;
import com.mim.entities.Lugar;
import com.mim.entities.Orden;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marcoisaac
 */
public class ReportGen extends HttpServlet {

    @PersistenceContext(unitName = "MantenimientoRestPU")
    private EntityManager em;

    private Orden orden;
    private final List<HistorialDetalles> observaciones = new ArrayList<>();
    private List<Fotos> fotos;
    private Equipo equipo;
    private Lugar lugar;
    private int ordenId;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("reportid"));
            TypedQuery<Orden> query = em.createQuery("SELECT c FROM Orden c WHERE c.idorden = :id", Orden.class);
            query.setParameter("id", id);

            orden = query.getSingleResult();
            equipo = orden.getEquipoIdequipo();
            lugar = equipo.getLugarIdlugar();

            TypedQuery<HistorialDetalles> queryHis = em.createQuery("SELECT c FROM HistorialDetalles c WHERE c.ordenIdorden.idorden = :idOr ", HistorialDetalles.class);
            queryHis.setParameter("idOr", orden.getIdorden());
            observaciones.clear();
            for (HistorialDetalles hs : queryHis.getResultList()) {
                if (hs.getValor() != null) {
                    if (hs.getValor().length() > 0) {
                        observaciones.add(hs);
                    }
                }
            }

            TypedQuery<Fotos> queryPics = em.createQuery("SELECT c FROM Fotos c WHERE c.ordenIdorden.idorden = :idOrden", Fotos.class);
            queryPics.setParameter("idOrden", orden.getIdorden());

            if (fotos != null) {
                fotos.clear();
            }
            fotos = queryPics.getResultList();
            buildReport(response);
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(ReportGen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buildReport(HttpServletResponse response) throws FileNotFoundException, DocumentException, BadElementException, IOException {
        response.setContentType("application/pdf");
        Document document = new Document(new Rectangle(800, 700), 7f, 7f, 50f, 7f);

        if (orden.getNumeroOrden().equals("n/a")) {
            orden.setNumeroOrden(orden.getActividad());
        }
        document.addTitle(orden.getNumeroOrden());
        //PdfWriter.getInstance(document, new FileOutputStream(orden.getNumeroOrden() + ".pdf"));
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        PdfPTable table = new PdfPTable(8);

        //LEFT HEADER CONTENT
        PdfPTable leftHeaderTable = new PdfPTable(4);

        PdfPCell imgCell = new PdfPCell();
        imgCell.setBorder(Rectangle.NO_BORDER);
        imgCell.setPaddingTop(14);
        imgCell.setColspan(1);
        imgCell.setFixedHeight(25);

        Image img = Image.getInstance("http://mimconstructions.com/img/mim%20trendy.png");

        imgCell.addElement(img);

        PdfPCell reportTitleCell = new PdfPCell(new Paragraph("REPORTE MANTENIMIENTO"));
        reportTitleCell.setPaddingTop(14);
        reportTitleCell.setPaddingLeft(20);
        reportTitleCell.setColspan(3);
        reportTitleCell.setBorder(Rectangle.NO_BORDER);

        leftHeaderTable.addCell(imgCell);
        leftHeaderTable.addCell(reportTitleCell);

        PdfPCell leftHeaderMainCell = new PdfPCell(leftHeaderTable);
        leftHeaderMainCell.setColspan(4);

        //END CONTENT
        //RIGHT HEADER WITH INFO ABOUT ORDER AND DATE
        PdfPTable infHeader = new PdfPTable(3);

        PdfPCell numberOrderLabel = new PdfPCell(new Paragraph("#ORDEN"));
        numberOrderLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
        numberOrderLabel.setColspan(1);
        String numeroOrden = null;
        if (orden.getNumeroOrden() != null) {
            numeroOrden = orden.getNumeroOrden();
        } else {
            numeroOrden = orden.getActividad();
        }

        PdfPCell numberOrderValue = new PdfPCell(new Paragraph(numeroOrden));
        numberOrderValue.setHorizontalAlignment(Element.ALIGN_CENTER);
        numberOrderValue.setColspan(2);

        PdfPCell prioridadLabel = new PdfPCell(new Paragraph("PRIORIDAD"));
        prioridadLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
        prioridadLabel.setColspan(1);

        PdfPCell prioridadValue = new PdfPCell(new Paragraph(orden.getPrioridad()));
        prioridadValue.setHorizontalAlignment(Element.ALIGN_CENTER);
        prioridadValue.setColspan(2);

        PdfPCell fechaLabel = new PdfPCell(new Paragraph("FECHA"));
        fechaLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
        fechaLabel.setColspan(1);
        //dd-MM-yyyy
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = orden.getStartDate();
        if (startDate == null) {
            startDate = new Date();
        }
        String fecha = format1.format(startDate);

        PdfPCell fechaValue = new PdfPCell(new Paragraph(fecha));
        fechaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
        fechaValue.setColspan(2);

        infHeader.addCell(numberOrderLabel);
        infHeader.addCell(numberOrderValue);
        infHeader.addCell(prioridadLabel);
        infHeader.addCell(prioridadValue);
        infHeader.addCell(fechaLabel);
        infHeader.addCell(fechaValue);

        PdfPCell cellHeaderRight
                = new PdfPCell(infHeader);
        cellHeaderRight.setColspan(4);
        //END HEADER

        PdfPCell areaLabel = new PdfPCell(new Paragraph("AREA"));
        areaLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
        areaLabel.setVerticalAlignment(Element.ALIGN_CENTER);
        areaLabel.setFixedHeight(30);
        areaLabel.setPaddingTop(5);
        areaLabel.setColspan(2);

        PdfPCell actividadLabel = new PdfPCell(new Paragraph("ACTIVIDAD"));
        actividadLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
        actividadLabel.setFixedHeight(30);
        actividadLabel.setVerticalAlignment(Element.ALIGN_CENTER);
        actividadLabel.setPaddingTop(5);
        actividadLabel.setColspan(3);

        PdfPCell responsableLabel = new PdfPCell(new Paragraph("RESPONSABLE DE OPERACION"));
        responsableLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
        responsableLabel.setFixedHeight(30);
        responsableLabel.setVerticalAlignment(Element.ALIGN_CENTER);
        responsableLabel.setPaddingTop(5);
        responsableLabel.setColspan(3);

        PdfPCell areaValor = new PdfPCell(new Paragraph("envasado"));
        areaValor.setFixedHeight(25);
        areaValor.setHorizontalAlignment(Element.ALIGN_CENTER);
        areaValor.setColspan(2);

        PdfPCell actividadValor = new PdfPCell(new Paragraph(orden.getActividad()));
        actividadValor.setHorizontalAlignment(Element.ALIGN_CENTER);
        actividadValor.setFixedHeight(25);
        actividadValor.setColspan(3);

        PdfPCell responsableValor = new PdfPCell(new Paragraph(orden.getEncargado()));
        responsableValor.setHorizontalAlignment(Element.ALIGN_CENTER);
        responsableValor.setFixedHeight(25);
        responsableValor.setColspan(3);

        // 2 FILAS PARA INF. EQUIPO Y LUGAR
        PdfPCell equipoLabel = new PdfPCell(new Paragraph("EQUIPO/CONJUNTO"));
        equipoLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
        equipoLabel.setVerticalAlignment(Element.ALIGN_CENTER);
        equipoLabel.setFixedHeight(30);
        equipoLabel.setPaddingTop(5);
        equipoLabel.setColspan(4);

        PdfPCell lugarLabel = new PdfPCell(new Paragraph("LUGAR"));
        lugarLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
        lugarLabel.setFixedHeight(30);
        lugarLabel.setVerticalAlignment(Element.ALIGN_CENTER);
        lugarLabel.setPaddingTop(5);
        lugarLabel.setColspan(4);

        String numeroEquipo = null;
        if (equipo.getNumeroEquipo() != null) {
            numeroEquipo = equipo.getNumeroEquipo();
        } else {
            numeroEquipo = "n/a";
        }

        PdfPCell equipoValor = new PdfPCell(new Paragraph(numeroEquipo));
        equipoValor.setHorizontalAlignment(Element.ALIGN_CENTER);
        equipoValor.setFixedHeight(25);
        equipoValor.setColspan(4);

        PdfPCell lugarValor = new PdfPCell(new Paragraph(lugar.getNombre()));
        lugarValor.setHorizontalAlignment(Element.ALIGN_CENTER);
        lugarValor.setFixedHeight(25);
        lugarValor.setColspan(4);

        //END INFO EQUIPO
        // 4 ROW and 5 ROW
        PdfPCell descripcionLabel = new PdfPCell(new Paragraph("DESCRIPCION"));
        descripcionLabel.setPadding(12);
        descripcionLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
        descripcionLabel.setColspan(8);

        PdfPCell descripcionValor = new PdfPCell(new Paragraph(orden.getDescripcion()));
        descripcionValor.setPadding(10);
        descripcionValor.setColspan(8);
        //END ROWS

        //ROW BEFORE HISTORIAL_DETALLES
        PdfPCell historialLabel = new PdfPCell(new Paragraph("OBSERVACIONES"));
        historialLabel.setPadding(12);
        historialLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
        historialLabel.setColspan(8);
        //END HISTORIAL

        table.addCell(leftHeaderMainCell);
        table.addCell(cellHeaderRight);
        table.addCell(areaLabel);
        table.addCell(actividadLabel);
        table.addCell(responsableLabel);

        table.addCell(areaValor);
        table.addCell(actividadValor);
        table.addCell(responsableValor);
        table.addCell(equipoLabel);
        table.addCell(lugarLabel);
        table.addCell(equipoValor);
        table.addCell(lugarValor);
        table.addCell(descripcionLabel);
        table.addCell(descripcionValor);

        if (observaciones != null) {
            if (observaciones.size() > 0) {
                table.addCell(historialLabel);

                //LOOP HISTORIAL_DETALLES
                for (int i = 0; i < observaciones.size(); i++) {
                    HistorialDetalles historial = observaciones.get(i);

                    PdfPCell paramCell = new PdfPCell();
                    paramCell.setColspan(3);
                    paramCell.addElement(new Paragraph(historial.getParametro()));
                    paramCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    paramCell.setPaddingLeft(10);
                    paramCell.setPaddingBottom(10);

                    PdfPCell valueParamCell = new PdfPCell();
                    valueParamCell.setColspan(5);
                    valueParamCell.setPaddingLeft(10);
                    valueParamCell.setVerticalAlignment(Element.ALIGN_CENTER);
                    valueParamCell.addElement(new Paragraph(historial.getValor()));
                    valueParamCell.setPaddingBottom(10);

                    table.addCell(paramCell);
                    table.addCell(valueParamCell);
                }
            }
        }
        //END LOOP HISTORIAL

        // FIRST ROWS OF FOTOGRAPHIC REPORT
        PdfPCell pasoLabel = new PdfPCell(new Paragraph("PASO"));
        pasoLabel.setFixedHeight(20);
        pasoLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
        pasoLabel.setColspan(1);

        PdfPCell accionLabel = new PdfPCell(new Paragraph("ACCION"));
        accionLabel.setFixedHeight(20);
        accionLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
        accionLabel.setColspan(3);

        PdfPCell imagenLabel = new PdfPCell(new Paragraph("IMAGENES"));
        imagenLabel.setFixedHeight(20);
        imagenLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
        imagenLabel.setColspan(4);
        //END ROWS

        PdfPTable table2 = new PdfPTable(8);

        //ROW BEFORE HISTORIAL_DETALLES
        PdfPCell headerPictures = new PdfPCell(new Paragraph("PROCEDIMIENTO"));
        headerPictures.setPadding(12);
        headerPictures.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerPictures.setColspan(8);

        table2.addCell(headerPictures);
        //END HISTORIAL

        table2.addCell(pasoLabel);
        table2.addCell(accionLabel);
        table2.addCell(imagenLabel);

        //fotos loop
        for (int i = 0; i < fotos.size(); i++) {
            Fotos foto = fotos.get(i);
            PdfPCell pasoVal = new PdfPCell(new Paragraph(String.valueOf(i)));
            pasoVal.setHorizontalAlignment(Element.ALIGN_CENTER);
            pasoVal.setColspan(1);

            PdfPCell detail = new PdfPCell(new Paragraph(foto.getTitulo()));
            detail.setPadding(5);
            detail.setBorder(Rectangle.NO_BORDER);

            PdfPCell accionVal = new PdfPCell();
            accionVal.addElement(new Paragraph(foto.getDescripcion()));
            accionVal.setHorizontalAlignment(Element.ALIGN_CENTER);
            accionVal.setBorder(Rectangle.NO_BORDER);
            //accionVal.setColspan(3);

            PdfPTable infoTable = new PdfPTable(1);
            infoTable.addCell(detail);
            infoTable.addCell(accionVal);

            PdfPCell infiCell = new PdfPCell();
            infiCell.setColspan(3);
            infiCell.addElement(infoTable);

            //Table collumn
            String[] splits = foto.getArchivo().split("/");
            int size = splits.length;
            //System.getenv("OPENSHIFT_DATA_DIR") + "imagenes/" + name)
            Image imgFoto = Image.getInstance("http://mantenimiento-contactres.rhcloud.com/MantenimientoRest/webresources/com.mim.entities.fotos/api/" + foto.getIdfotos());

            PdfPTable imagenTable = new PdfPTable(1);

            PdfPCell fotoCell = new PdfPCell();
            fotoCell.setColspan(1);
            fotoCell.addElement(imgFoto);
            fotoCell.setFixedHeight(310);
            fotoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            fotoCell.setBorder(Rectangle.NO_BORDER);

            imagenTable.addCell(fotoCell);

            PdfPCell imagenVal = new PdfPCell();
            imagenVal.setColspan(4);
            imagenVal.addElement(imagenTable);

            table2.addCell(pasoVal);
            table2.addCell(infiCell);
            table2.addCell(imagenVal);
        }
        //end loop

        //table.addCell(tiempoLabel);
        document.add(table);
        document.newPage();
        document.add(table2);
        document.close();
        System.out.println("ID REPORTE= " + ordenId);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
