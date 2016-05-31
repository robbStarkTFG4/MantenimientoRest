package com.mim.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Fotos.class)
public abstract class Fotos_ {

	public static volatile SingularAttribute<Fotos, Integer> idfotos;
	public static volatile SingularAttribute<Fotos, String> descripcion;
	public static volatile SingularAttribute<Fotos, String> archivo;
	public static volatile SingularAttribute<Fotos, String> titulo;
	public static volatile SingularAttribute<Fotos, Orden> ordenIdorden;

}

