/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.service;

import com.mim.entities.Equipo;
import com.mim.entities.InformacionFabricante;
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
@Path("com.mim.entities.informacionfabricante")
public class InformacionFabricanteFacadeREST extends AbstractFacade<InformacionFabricante> {

    @PersistenceContext(unitName = "MantenimientoRestPU")
    private EntityManager em;

    public InformacionFabricanteFacadeREST() {
        super(InformacionFabricante.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(InformacionFabricante entity) {
        super.create(entity);
    }

    @POST
    @Path("{equipo}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void registerFactoryInfo(@PathParam("equipo") int equipoId, List<InformacionFabricante> list) {
        TypedQuery<Equipo> query = em.createQuery("SELECT c FROM Equipo c WHERE c.idequipo = :equipo", Equipo.class);
        query.setParameter("equipo", equipoId);
        Equipo res = query.getSingleResult();
        if (res != null) {
            for (InformacionFabricante inf : list) {
                inf.setEquipoIdequipo(res);
                super.create(inf);
            }
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, InformacionFabricante entity) {
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
    public InformacionFabricante find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    /**
     * return factoryParams for the given Equipment Id
     *
     * @param id
     * @return
     */
    @GET
    @Path("/factoryList/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<InformacionFabricante> findFactoryInformation(@PathParam("id") Integer id) {
        TypedQuery<InformacionFabricante> query = em.createQuery("SELECT c FROM InformacionFabricante c WHERE c.equipoIdequipo.idequipo = :id", InformacionFabricante.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<InformacionFabricante> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<InformacionFabricante> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
