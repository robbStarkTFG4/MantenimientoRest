/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.service;

import com.google.gson.Gson;
import com.mim.entities.Equipo;
import com.mim.entities.Fotos;
import com.mim.entities.HistorialDetalles;
import com.mim.entities.Orden;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author marcoisaac
 */
@Stateless
@Path("com.mim.entities.orden")
public class OrdenFacadeREST extends AbstractFacade<Orden> {

    @PersistenceContext(unitName = "MantenimientoRestPU")
    private EntityManager em;

    public OrdenFacadeREST() {
        super(Orden.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Orden entity) {
        super.create(entity);
    }

    /**
     * persist order and returns his id
     *
     * @param entity
     * @return
     */
    @POST
    @Path("sube")
    @Consumes({MediaType.APPLICATION_JSON})
    public String persistOrden(Orden entity) {
        entity.setStartDate(new Date());
        super.create(entity);
        Orden orden = new Orden(entity.getIdorden());
        Gson gson = new Gson();
        em.flush();
        return gson.toJson(orden);
    }

    @GET
    @Path("mark/{numero}")
    public String marcarOrden(@PathParam("numero") String numero) {
        int id = Integer.parseInt(numero);
        TypedQuery<Orden> query = em.createQuery("SELECT c FROM Orden c WHERE c.idorden = :id", Orden.class);
        query.setParameter("id", id);
        Orden or = query.getSingleResult();
        or.setEndDate(new Date());
        Orden orden = new Orden(or.getIdorden());
        Gson gson = new Gson();
        return gson.toJson(orden);
    }

    @POST
    @Path("reportphoto/{lugar}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String uploadFotoReport(@PathParam("lugar") String lugar, Orden orden) {
        //place choosen must have an equipment where barcode = "n/a"
        TypedQuery<Equipo> queryEquipo = em.createQuery("SELECT c FROM Equipo c WHERE c.codigoBarras = :codigo AND c.lugarIdlugar.nombre = :lug", Equipo.class);
        queryEquipo.setParameter("codigo", "n/a");
        queryEquipo.setParameter("lug", lugar);

        Equipo eq = queryEquipo.getSingleResult();

        Gson gson = new Gson();

        orden.setNumeroOrden("n/a");
        orden.setStartDate(new Date());

        orden.setEquipoIdequipo(eq);
        super.create(orden);
        em.flush();
        System.out.println("orden agregada: " + orden.getIdorden());

        return gson.toJson(new Orden(orden.getIdorden()));
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Orden entity) {
        super.edit(entity);
    }

    @GET
    @Path("borrar/{id}")
    public void remove(@PathParam("id") Integer id) {
        Orden or = super.find(id);
        TypedQuery<Fotos> query = em.createQuery("SELECT c FROM Fotos c WHERE c.ordenIdorden.idorden = :idOrden", Fotos.class);
        query.setParameter("idOrden", or.getIdorden());

        for (Fotos ft : query.getResultList()) {
            String[] split = ft.getArchivo().split("/");
            int size = split.length;
            final String name = split[size - 1];
            File file = new File(System.getenv("OPENSHIFT_DATA_DIR") + "imagenes/" + name);

            if (file.exists()) {
                file.delete();
            }

            em.remove(ft);
        }

        for (HistorialDetalles hs : or.getHistorialDetallesList()) {
            em.remove(hs);
        }

        em.remove(or);
        //super.remove();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Orden find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Orden> findAll() {
        return super.findAll();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
