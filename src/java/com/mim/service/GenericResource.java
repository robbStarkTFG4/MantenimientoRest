/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.service;

import com.mim.entities.Equipo;
import java.io.File;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author marcoisaac
 */
@Stateless
@Path("generic")
public class GenericResource {

    @PersistenceContext(unitName = "MantenimientoRestPU")
    private EntityManager em;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.mim.service.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        boolean available = false;
        String code = null;
        while (!available) {
            code = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
            TypedQuery<Equipo> query = em.createQuery("SELECT c FROM Equipo c WHERE c.codigoBarras = :cod", Equipo.class);
            query.setParameter("cod", code);
            List<Equipo> codeList = query.getResultList();
            if (!(codeList.size() > 0)) {
                available = true;
            }
        }
        return code;
    }

    @GET
    @Path("dir")
    @Produces("application/json")
    public String archiveDir() {
        //TODO return proper representation object
        return System.getenv("OPENSHIFT_DATA_DIR");
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    /**
     * creates directory for images
     *
     * @return
     */
    @GET
    @Path("directory")
    @Produces("application/json")
    public String build() {
        File file = new File(System.getenv("OPENSHIFT_DATA_DIR") + "imagenes/");
        String status = "hola";
        if (!file.exists()) {
            if (file.mkdir()) {
                status = "Directory is created!";
            } else {
                status = "Failed to create directory!";
            }
        }
        return status;
    }

    @GET
    @Path("files")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFilesList() {
        File folder = new File(System.getenv("OPENSHIFT_DATA_DIR") + "imagenes/");
        File[] listOfFiles = folder.listFiles();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
                builder.append("File ").append(listOfFiles[i].getName()).append("  ").append("tamaño ").append(listOfFiles[i].length()).append("  ");
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        return builder.toString();
    }

    @GET
    @Path("size")
    @Produces(MediaType.TEXT_PLAIN)
    public void getDirectorySize() {
        File folder = new File(System.getenv("OPENSHIFT_DATA_DIR") + "imagenes/");
        File[] listOfFiles = folder.listFiles();

        Long suma = null;
        for (File fl : listOfFiles) {
            if (fl != null) {
                suma = suma + fl.length();
            }
        }
        System.out.println("suma: " + suma);
    }

    @GET
    @Path("valide/{codigo}")
    public String validateCode(@PathParam("codigo") String codigo) {
        boolean available = false;
        TypedQuery<Equipo> query = em.createQuery("SELECT c FROM Equipo c WHERE c.codigoBarras = :cod", Equipo.class);
        query.setParameter("cod", codigo);
        List<Equipo> codeList = query.getResultList();
        available = !(codeList.size() > 0);

        if (available) {
            return "valido";
        } else {
            return "invalido";
        }
    }
}
