Spring usa Jackson como convertidor -> Marshaller

SLF4J es por defacto la librerìa que logea.

server.compression.enabled = true ??????

TLS ?????????

![img.png](img.png)


Spring tiene la opciòn de exponer interfaces gràficas dentro de los controllers.

Basta con crear el archivo html en la carpeta templates, y crear un @Controller,
es decir un archivo java donde se haga referencia


Spring tiene una opcion llamada SpringCommandLineRunner
Ej:

![img_1.png](img_1.png)

## Paginación

Para pagina bastará con que nuestro repositorio extienda de JpaRepository o PagingAndSortingRepository.

Cont alguna de estas dos interfaces usadas en nuestro repositorio, el siguiente pasó será que nuestros métodos que retornen 
una lista de datos, cambiarles la firma de esto _List<T>_ a _Page<T>_ No obstante, adicional a los parámetros ya creados
Añadirle _Pageable_.
Ver _**PersonRepository.java**_


RestController directamente al repositorio. **_@RepositoryRestResource_**

Usando _@RestResource_ en cada método spring automaticamente expondrá el método a un servicio.
Observar el link https://www.arquitecturajava.com/repositoryrestresource-y-spring-framework/

## Documentation

Con esta dependencia, todos nuestros servicios web quedan documentados automaticamente.
```
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-rest-hal-browser</artifactId>
    <version>3.3.9.RELEASE</version>
</dependency>
```

Para ver el funcionamiento solo será necesario iniciar la aplicación como localhost con el puerto configurado.
ejemplo:

`http://localhost:8080/browser/index.html#/
`
