# ControlAcceso
Control de acceso basico con manejo de puerto serial, JAVA, MySQL, ACCESS y webservice

Programa hecho en JAVA con el NetBeans 8, para realizar un control básico de acceso, en base a registros con marcas de tiempo, usuarios y códigos identificadores, también sectores (dando lugar a una posible extensión para control distribuido).

Capacidad de lectura y escritura de datos en puerto serie. En caso de recibir los datos de un dispositivo que los use.

Librerías utilizadas JAVA:
+ MySQL driver Connector 5.1.23 Driver conector para Bases de datos MySQL.
+ UCanAccess-3.0.5 Driver conector para manejar bases de datos Microsoft Access.
+ Poi-3.8 Librería para manejar documentos de Microsoft (Excel en este caso).
+ JSSC-2.8.0 Librería para manejar puerto serial
+ DateChooser.jar Plugin de netbeans para interface gráfica de calendarios y fechas.

Funcionalidades:
+ Administración de usuarios, códigos de identidad (tarjetas), registros y sectores.
+ Configuración de puerto serial para recibir y enviar datos a un dispositivo externo.
+ Exportación de registros a documentos Excel.
+ Configuración de base de datos flexible.
+ Esta versión trabaja con base de datos Access o MySQL. Se pueden agregar más agregando algo de código.
+ Tiene opción de un log de seguimiento de lecturas del puerto serial en caso de pérdidas de registros.
+ Interfaz de usuario amigable y vista en vivo de registros del puerto serial a través de una pantalla de monitoreo.
+ Obtención de informes en formato Excel.
+ Búsqueda de registros, por fecha, código, fecha, hora y sectores.

Datos teó/tecnicos: - Patrones implementados:
+ DataMapper, para acceso a datos.
+ Facade (Fachada), diseño.
+ Observer, diseño (actualiza las vistas, sobre todo las del monitor de lectura del puerto serial).

Es un buen ejemplo del uso de la api JSSC de java, muy versátil y sencilla para usar dispositivos que se comuniquen por puerto serie.
