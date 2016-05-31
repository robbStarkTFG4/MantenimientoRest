/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.service;

import com.google.gson.Gson;
import com.mim.entities.Equipo;
import com.mim.entities.Lugar;
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
@Path("com.mim.entities.equipo")
public class EquipoFacadeREST extends AbstractFacade<Equipo> {

    @PersistenceContext(unitName = "MantenimientoRestPU")
    private EntityManager em;

    public EquipoFacadeREST() {
        super(Equipo.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Equipo entity) {
        super.create(entity);
    }

    @POST
    @Path("{nombre}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String createEquipment(@PathParam("nombre") String nombre, Equipo entity) {
        TypedQuery<Lugar> query = em.createQuery("SELECT c FROM Lugar c WHERE c.nombre = :name", Lugar.class);
        query.setParameter("name", nombre);
        Lugar lugar = query.getSingleResult();
        if (lugar != null) {
            entity.setLugarIdlugar(lugar);
            super.create(entity);
        }

        Gson gson = new Gson();
        Equipo temp = new Equipo(entity.getIdequipo());
        return gson.toJson(temp);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Equipo entity) {
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
    public Equipo find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    /**
     * returns equipment searched by his codeBar
     * @param code
     * @return 
     */
    @GET
    @Path("{code}")
    @Produces({MediaType.APPLICATION_JSON})
    public Equipo findByCodeBar(@PathParam("code") String code) {
        TypedQuery<Equipo> query = em.createQuery("SELECT c FROM Equipo c WHERE c.codigoBarras = :cod", Equipo.class);
        query.setParameter("cod", code);
        return query.getSingleResult();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Equipo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Equipo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
