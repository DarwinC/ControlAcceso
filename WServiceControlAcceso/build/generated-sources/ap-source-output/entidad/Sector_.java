package entidad;

import entidad.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2016-07-05T08:04:56")
@StaticMetamodel(Sector.class)
public class Sector_ { 

    public static volatile SingularAttribute<Sector, String> descripcion;
    public static volatile SingularAttribute<Sector, Integer> id;
    public static volatile CollectionAttribute<Sector, Usuario> usuarioCollection;
    public static volatile SingularAttribute<Sector, String> nombre;

}