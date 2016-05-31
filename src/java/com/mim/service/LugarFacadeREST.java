/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.service;

import com.mim.entities.Equipo;
import com.mim.entities.Lugar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@Path("com.mim.entities.lugar")
public class LugarFacadeREST extends AbstractFacade<Lugar> {

    @PersistenceContext(unitName = "MantenimientoRestPU")
    private EntityManager em;

    public LugarFacadeREST() {
        super(Lugar.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Lugar entity) {
        super.create(entity);
    }

    /*@GET
    @Path("/lugares")
    public void addEquipments() {
        List<Lugar> list = this.findAll();
        for (Lugar lugar : list) {
            Equipo eq = new Equipo();
            eq.setCodigoBarras("n/a");
            eq.setNumeroEquipo("n/a");
            eq.setNumeroEquipo("n/a");
            eq.setListaNombreEquiposIdlistaNombre(7);
            eq.setLugarIdlugar(lugar);
            em.persist(eq);
        }
        em.flush();
    }*/

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Lugar entity) {
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
    public Lugar find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Lugar> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Lugar> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
