/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.service;

import com.google.gson.Gson;
import com.mim.entities.Equipo;
import com.mim.entities.Lugar;
import com.mim.entities.Orden;
import com.mim.session.beans.ListaNombreEquiposFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
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

    @POST
    @Path("reportphoto/{lugar}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String uploadFotoReport(@PathParam("lugar") String lugar, Orden orden) {

        TypedQuery<Equipo> queryEquipo = em.createQuery("SELECT c FROM Equipo c WHERE c.codigoBarras = :codigo AND c.lugarIdlugar.nombre = :lug", Equipo.class);
        queryEquipo.setParameter("codigo", "n/a");
        queryEquipo.setParameter("lug", lugar);

        Equipo eq = queryEquipo.getSingleResult();

        Gson gson = new Gson();

        orden.setNumeroOrden("n/a");
        orden.setEncargado("n/a");

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

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
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
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Orden> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
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
