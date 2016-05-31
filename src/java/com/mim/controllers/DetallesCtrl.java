/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.controllers;

import com.mim.entities.Detalles;
import com.mim.entities.ListaNombreEquipos;
import com.mim.session.beans.DetallesFacade;
import com.mim.session.beans.ListaNombreEquiposFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author marcoisaac
 */
@Named("detalles")
@ViewScoped
public class DetallesCtrl implements Serializable {

    private Detalles currentDetail = new Detalles();
    private List<ListaNombreEquipos> nombreEquiposList = new ArrayList<>();
    private ListaNombreEquipos selected;

    @EJB
    ListaNombreEquiposFacade lista;

    @EJB
    DetallesFacade dtl;

    @PostConstruct
    private void init() {
        nombreEquiposList = lista.findAll();
    }

    public void guardarListener(ActionEvent e) {
        System.out.println("hola crayola!!!");
        currentDetail.setListaNombreEquiposIdlistaNombre(selected);
        dtl.create(currentDetail, selected.getNombre());
        currentDetail = new Detalles();
        //selected = new ListaNombreEquipos();
        RequestContext.getCurrentInstance().update("detallesForm");
    }

    public Detalles getCurrentDetail() {
        return currentDetail;
    }

    public List<ListaNombreEquipos> getNombreEquipoList() {
        return nombreEquiposList;
    }

    public List<ListaNombreEquipos> getNombreEquiposList() {
        return nombreEquiposList;
    }

    public void setNombreEquiposList(List<ListaNombreEquipos> nombreEquiposList) {
        this.nombreEquiposList = nombreEquiposList;
    }

    public ListaNombreEquiposFacade getLista() {
        return lista;
    }

    public void setLista(ListaNombreEquiposFacade lista) {
        this.lista = lista;
    }

    public ListaNombreEquipos getSelected() {
        return selected;
    }

    public void setSelected(ListaNombreEquipos selected) {
        this.selected = selected;
    }

}
