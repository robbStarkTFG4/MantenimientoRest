package com.mim.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Equipo.class)
public abstract class Equipo_ {

	public static volatile SingularAttribute<Equipo, Integer> listaNombreEquiposIdlistaNombre;
	public static volatile ListAttribute<Equipo, InformacionFabricante> informacionFabricanteList;
	public static volatile ListAttribute<Equipo, Orden> ordenList;
	public static volatile SingularAttribute<Equipo, Integer> idequipo;
	public static volatile SingularAttribute<Equipo, Lugar> lugarIdlugar;
	public static volatile SingularAttribute<Equipo, String> numeroEquipo;
	public static volatile SingularAttribute<Equipo, String> codigoBarras;

}

