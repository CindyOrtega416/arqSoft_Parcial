# Segundo Parcial


Serie de tareas que se van a poder realizar 
Esas tareas son asignables a distintos usuarios
Esos usuarios tambien tiene que tener permisos sobre esas tareas
	Yo no puedo asignar una tarea a un usuario que no está dado de alta. Que no tiene habilitado para trabajar en esa tarea

Yo tengo proyectos dados de alta. Usuarios dados de alta. Un usuario puede estar en más de un proyecto trabajando. Un proyecto puede tener muchos usuarios (many to many)

Dado un proyecto puedo cargar tareas al proyecto que pueden ser tareas de desarrollo (validacion de una api; definir interfaz; diseñar casos de uso; reportar bugs)

O sea encuentro un bug y se lo asigno a una persona y esa persona será la encargada de encontrar y ver si realmente existe y se lo va a asignar a otra persona (agustin) y agustin va a ser el encargado de decir che cuando está listo, y así va a ir cambiando de estado

La tarea va a tener una coleccion de anotaciones que pueden ir surgiendo. Francisco puede entrar e ir cambiando el estado (asignarlo a Agustin para desarrollo) y puede dejar un comentario (encontré esto). Agustín puede dejar un comentario tambien y devolverle la tarea a Francisco. Hasta que haya alguien que termine cerrando esa tarea. 

No enum porque el core es la cantidad de estados que uno puede tener. Concentrarse mucho en entender los estados que puede tener, cuales son las transiciones posibles de estado de un lado a otro (la tarea puede estar ASIGNADA, CREADA, EN DESARROLLO, PAUSADA (a la espera de que se complete otra tarea)) y cada vez que yo tenga que cambiar de estado una tarea debería poder chequear de que al estado nuevo al que tenga que ir sea un estado de varios(? (si yo voy y digo la tarea está CREADA y yo digo bueno ahora la quiero CERRAR, no es factible que se pueda cerrar del estado creada

MODEL
Usuario
Proyecto
Tarea (las tareas se asignan a usuarios)
Comentario
Estados de proyecto (no enum) (entidad mas)
Estados de tareas (no enum) (entidad mas) one to many contra el estado de la tarea

* Tenemos que poder dar de alta usuarios; alta de proyectos; 
asignar usuarios a los proyectos (no entrar en temas de roles) (cualquier persona puede hacer cualquier cosa); 
* poder decir en este proyecto trabajan estas 5, 6 personas; 
* poder dar de alta tareas (tickets) a un usuario en particular y a un proyecto en particular (validacion deberia decir que le estoy asignado tickets a un usuario que tiene permisos sobre el proyecto para poder ejecutar cosas;
* Transicionar tickets (que cambien de estado) (de CREADO a ASIGNADO) (esa asignacion tiene que ir a una persona que va a ser el responsable);
* Que cada cambio de estado setee una inserción de un comentario (no hace falta que sea uno puesto por un usuario)("se cambio de estado de CREADO a ASIGNADO a tal persona" comentario automático);
* Debería poder ver la tarea para ver todos los comentarios (ordenados por fecha(?);
* Debería poder cargar un comentario a la tarea sin que haya un cambio de estado;
* Las tareas nunca se borran (nunca DELETE)
* Nunca se hace un DELETE de un comentario
* No puedo agregar un comentario a una tarea que se encuentra cerrada (exception??)
* Se puede reabrir una tarea. Pero mientras esté cerrada la tarea no puedo agregar un comentario, debo reabrirla primero.
* Poder buscar proyectos por nombres (para hacer más fácil una carga) 
* Poder buscar tareas del proyecto que vengan con todos sus comentarios y a quien está asignada
* Una tarea si la voy a reasignar a otra persona debería tener un comentario generado automáticamente. La tarea no cambia de estado pero si de persona asignada
* Asignacion no es una entidad
* Doy de alta un proyecto, solo asigno a una persona (Nara) y si yo quiero enviar una tarea de Francisco no tengo que poder hacerlo
* Cualquiera puede crear una tarea. Pero cuando ASIGNO una tarea tiene que ser a una persona que tenga permisos sobre el proyecto para poder hacer algo.
* NO ENTRAR EN TEMA DE ROLES
