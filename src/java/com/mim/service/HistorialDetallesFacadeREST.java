/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.service;

import com.mim.entities.Detalles;
import com.mim.entities.Equipo;
import com.mim.entities.HistorialDetalles;
import com.mim.entities.ListaNombreEquipos;
import com.mim.entities.Orden;
import java.util.ArrayList;
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
@Path("com.mim.entities.historialdetalles")
public class HistorialDetallesFacadeREST extends AbstractFacade<HistorialDetalles> {

    @PersistenceContext(unitName = "MantenimientoRestPU")
    private EntityManager em;

    public HistorialDetallesFacadeREST() {
        super(HistorialDetalles.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(HistorialDetalles entity) {
        super.create(entity);
    }

    @POST
    @Path("lista/{orden}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void persistHistoryList(@PathParam("orden") int id, List<HistorialDetalles> list) {
        System.out.println("si soy invocado");
        TypedQuery<Orden> query = em.createQuery("SELECT c FROM Orden c WHERE c.idorden = :id", Orden.class);
        query.setParameter("id", id);
        Orden res = query.getSingleResult();
        if (res != null) {
            System.out.println("tamaño lista: " + list.size());
            for (HistorialDetalles hs : list) {
                hs.setOrdenIdorden(res);
                em.persist(hs);
            }
        } else {
            System.out.println("NO ENCONTRE LA ORDEN");
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, HistorialDetalles entity) {
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
    public HistorialDetalles find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("orden/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<HistorialDetalles> retriveHistorial(@PathParam("id") Integer id) {
        TypedQuery<HistorialDetalles> query = em.createQuery("SELECT c FROM HistorialDetalles c WHERE c.ordenIdorden.idorden = :id", HistorialDetalles.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<HistorialDetalles> findAll() {
        return super.findAll();
    }

    /**
     * returns historyDetails list constructed by checking
     * "lista_nombre_equipos"
     *
     * @param idEquipo
     * @return
     */
    @GET
    @Path("history/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<HistorialDetalles> getHistoryParams(@PathParam("id") int idEquipo) {
        TypedQuery<Equipo> query = em.createQuery("SELECT c FROM Equipo c WHERE c.idequipo = :id", Equipo.class);
        query.setParameter("id", idEquipo);
        Equipo res = query.getSingleResult();
        if (res != null) {
            int nombre = res.getListaNombreEquiposIdlistaNombre();
            TypedQuery<ListaNombreEquipos> queryNombre = em.createQuery("SELECT c FROM ListaNombreEquipos c WHERE c.idlistaNombre = :idNombre", ListaNombreEquipos.class);
            queryNombre.setParameter("idNombre", nombre);
            ListaNombreEquipos nombreEquipo = queryNombre.getSingleResult();
            nombreEquipo.getDetallesList().size();

            List<HistorialDetalles> filtered = new ArrayList<>();
            for (Detalles dl : nombreEquipo.getDetallesList()) {
                if (dl.getShowParam()) {
                    filtered.add(new HistorialDetalles(dl.getParametro()));
                }
            }
            return filtered;
        } else {
            return null;
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<HistorialDetalles> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
