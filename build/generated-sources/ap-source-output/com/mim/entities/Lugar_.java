package com.mim.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Lugar.class)
public abstract class Lugar_ {

	public static volatile SingularAttribute<Lugar, String> direccion1;
	public static volatile SingularAttribute<Lugar, String> estado;
	public static volatile SingularAttribute<Lugar, String> direccion2;
	public static volatile SingularAttribute<Lugar, String> ciudad;
	public static volatile ListAttribute<Lugar, Equipo> equipoList;
	public static volatile SingularAttribute<Lugar, String> nombre;
	public static volatile SingularAttribute<Lugar, Integer> idlugar;

}

