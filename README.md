Proyecto Spring Boot: Consumo de API Externa
Descripción
Este proyecto es una aplicación Spring Boot que consume una API externa (mockapi.io) para obtener una lista de usuarios. 
El objetivo es proporcionar un nuevo endpoint que exponga esta información, manejando errores comunes y aplicando mejores prácticas de desarrollo.

Características
Consumo de API Externa: La aplicación realiza peticiones a la API mockapi.io para obtener información de usuarios.
Exposición de Endpoint: Se ha creado un endpoint REST que permite a los clientes acceder a la lista de usuarios obtenida de la API externa.
Manejo de Errores: Se implementaron mecanismos para manejar errores comunes que pueden ocurrir al realizar solicitudes a la API externa, mejorando la robustez de la aplicación.
Inyección de Dependencias: Se utilizan prácticas recomendadas de inyección de dependencias para la gestión de servicios y controladores, facilitando la prueba y el mantenimiento del código.
Pruebas Unitarias: Se han desarrollado pruebas unitarias para garantizar el correcto funcionamiento de los componentes principales de la aplicación.
Sistema de Caché: Se implementó un sistema de caché para optimizar el rendimiento en la obtención de datos de usuarios, reduciendo el número de solicitudes a la API externa.
Tecnologías Utilizadas
Spring Boot: Framework para crear aplicaciones Java basadas en microservicios.
Spring Web: Para el desarrollo de la API REST.
RestTemplate: Para realizar peticiones HTTP a la API externa.
JUnit: Para la realización de pruebas unitarias.
Caché: Implementación de un sistema de caché para mejorar el rendimiento.
