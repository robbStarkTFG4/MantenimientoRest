package com.mim.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Orden.class)
public abstract class Orden_ {

	public static volatile SingularAttribute<Orden, String> descripcion;
	public static volatile SingularAttribute<Orden, String> numeroOrden;
	public static volatile SingularAttribute<Orden, Integer> estatus;
	public static volatile SingularAttribute<Orden, Date> endDate;
	public static volatile SingularAttribute<Orden, String> encargado;
	public static volatile SingularAttribute<Orden, Equipo> equipoIdequipo;
	public static volatile SingularAttribute<Orden, Integer> idorden;
	public static volatile ListAttribute<Orden, HistorialDetalles> historialDetallesList;
	public static volatile SingularAttribute<Orden, String> prioridad;
	public static volatile SingularAttribute<Orden, Date> startDate;
	public static volatile SingularAttribute<Orden, String> actividad;

}

