/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.service;

import com.mim.entities.Detalles;
import com.mim.entities.InformacionFabricante;
import com.mim.entities.ListaNombreEquipos;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
@Path("com.mim.entities.listanombreequipos")
public class ListaNombreEquiposFacadeREST extends AbstractFacade<ListaNombreEquipos> {

    @PersistenceContext(unitName = "MantenimientoRestPU")
    private EntityManager em;

    public ListaNombreEquiposFacadeREST() {
        super(ListaNombreEquipos.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(ListaNombreEquipos entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, ListaNombreEquipos entity) {
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
    public ListaNombreEquipos find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    /**
     * returns name object
     *
     * @param id
     * @return
     */
    @GET
    @Path("nombre/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ListaNombreEquipos getName(@PathParam("id") Integer id) {
        TypedQuery<ListaNombreEquipos> query = em.createQuery("SELECT c FROM ListaNombreEquipos c WHERE c.idlistaNombre = :id", ListaNombreEquipos.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * llamado por el fragment de la app TypeFragment para obtener los datos del
     * fabricante para uso en registro.
     *
     * @param id
     * @return
     */
    @GET
    @Path("params/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<InformacionFabricante> listaParametrosFabricante(@PathParam("id") Integer id) {
        ListaNombreEquipos nombre = super.find(id);
        nombre.getDetallesList().size();
        List<Detalles> detailsList = nombre.getDetallesList();

        List<InformacionFabricante> resList = new ArrayList<>();

        for (Detalles dl : detailsList) {
            resList.add(new InformacionFabricante(dl.getParametro()));
        }

        return resList;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<ListaNombreEquipos> findAll() {
        List<ListaNombreEquipos> list = super.findAll();
        List<ListaNombreEquipos> filtered = new ArrayList<>();
        for (ListaNombreEquipos l : list) {
            if (!l.getNombre().equals("n/a")) {
                filtered.add(l);
            }
        }
        return filtered;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ListaNombreEquipos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
