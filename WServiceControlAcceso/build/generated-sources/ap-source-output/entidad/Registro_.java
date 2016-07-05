package entidad;

import entidad.RegistroPK;
import entidad.Tarjeta;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2016-07-05T08:04:56")
@StaticMetamodel(Registro.class)
public class Registro_ { 

    public static volatile SingularAttribute<Registro, Character> estado;
    public static volatile SingularAttribute<Registro, Date> fechaHora;
    public static volatile SingularAttribute<Registro, Tarjeta> tarjeta;
    public static volatile SingularAttribute<Registro, RegistroPK> registroPK;

}