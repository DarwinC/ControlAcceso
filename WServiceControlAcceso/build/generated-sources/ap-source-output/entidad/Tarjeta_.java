package entidad;

import entidad.Registro;
import entidad.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2016-07-05T08:04:56")
@StaticMetamodel(Tarjeta.class)
public class Tarjeta_ { 

    public static volatile SingularAttribute<Tarjeta, String> codigo;
    public static volatile CollectionAttribute<Tarjeta, Registro> registroCollection;
    public static volatile SingularAttribute<Tarjeta, Integer> id;
    public static volatile CollectionAttribute<Tarjeta, Usuario> usuarioCollection;

}