# Movvi
Movie catalog

# Capas
## Vistas
La capa de vistas es la encargada de presentar información al usuario en el UI, la única lógica que debe contener es la que se usa para elementos visuales, como decidir cuando mostrar un texto o no, cambiar colores según un dato, etc.

* MainActivity - Es el punto central de la aplicación, es el host de los fragments que tienen las funciones de la aplicación.
* MovieDetailsActivity - Es la pantalla para ver los detalles de una pelicula, es lanzado al seleccionar una pelicula y recibe 2 parametros-
* MovieDetailsViewModel - Es el view model para MovieDetailsActivity, se encarga de consumir todos los datos necesarios para poder llenar todo lo que muestra el activity
* PopularMoviesFragment - Es el fragmento donde se contiene la lista para las peliculas populares.
* PopularMoviesViewModel - Es el viewModel que se encarga de obtener la lista de peliculas populares y presentarlas para el PopularMoviesFragment
* TopRatedMoviesFragment - Es el fragmento donde se contiene la lista para las peliculas populares.
* TopRatedMoviesFragment - Es el viewModel que se encarga de obtener la lista de peliculas populares y presentarlas para el TopRatedMoviesFragment
* UpcomingMoviesFragment - Es el fragmento donde se contiene la lista para las peliculas populares.
* UpcomingMoviesFragment - Es el viewModel que se encarga de obtener la lista de peliculas populares y presentarlas para el UpcomingMoviesFragment
* SearchFragment- Es el fragmento donde se contiene la lista de peliculas filtradas y las opciones para filtrar.
* SearchViewModel - Es el viewModel encargado de mostrar los datos al SearchFragment y el consumo de datos filtrados
* Clases en package ui/adapters - Son los adapters para presentar la información dentre de las listas de cada Fragment

## Red
La capa de red es la encargada de ser la único que realiza peticiones a internet, debe presentar métodos para el consumo de datos de manera básicamente limpia a quien sea que los vaya a consumir, no debe de tener lógica de negocio más allá de tal vez determinar qué hacer en casos de error o de no tener conectividad a internet. Esta capa no necesita ni debería depender de cualquier otra.

* TheMovieApiClient - Configura el cliente de retrofit y gson para el consumo del API, tambien expone una variable de tipo `TheMovieApiServices` para poder hacer llamado a cualquier servicio.
* TheMovieApiServices - Configura los servicios que pueden ser consumidos atraves de retrofit
* GetMoviesResponse - Data class para el consumo del api
* GetGenresResponse - Data class para el consumo del api

## Datos
La capa de datos se encarga del acceso y consulta de datos junto con todos sus modelos de datos para ser consumidos por quien sea cuando sea. Esta capa no necesita ni debería depender de cualquier otra.

* MovviRoomDatabase - Configuración y creación de base de datos con `Room`
* Clases en package data/db/daos - Entidades que exponen las maneras en las que se puede obtener la información de la base de datos
* Clases en package data/db/models - Modelos utilizados tanto para el consumo de apis como para el mapeo a base de datos

## Negocio
La capa de negocio es la encargada de trabajar con todas las demás capas con el objetivo de ser el centro neurálgico de la aplicación y contener toda la lógica necesaria para que esta funcione.

* MoviesRepository - Expone en métodos sencillos información e internamente se encarga de las llamadas al api y afectaciones a la base de datos
* GenreRepository - Expone en métodos sencillos información e internamente se encarga de las llamadas al api y afectaciones a la base de datos
* MovieDetailsRepository -  Expone en métodos sencillos información e internamente se encarga de las llamadas al api y afectaciones a la base de datos

---
### ¿En qué consiste el principio de responsabilidad única?
Consiste en que elemento dentro de la aplicación tenga una serie de responsabilidades que no involucren a nadie más, de manera que la clase/objeto solo necesite ser consciente de sí mismo para funcionar, con esto hacemos un desarrollo más transparente y mantenible a largo plazo.

### ¿Qué características tiene un código limpio?
* Debe tener una nomenclatura estandarizada.
* Debe respetar a la mayor medida posible los lineamientos según el lenguaje, por ejemplo en la extensión de una línea de código, el tamaño de un método o clase, etc.
* Debe intentar mantener el principio de responsabilidad única siempre que sea posible.
* De preferencia debe estar en inglés, pero caso de estar en otro idioma solo debe usar ese idioma y no combinarlos.
* Identacion correcta.
* Comentar el funcionamiento de las funciones siempre que sea posible.
* No hardcodear datos.
