# Blix
App powered by TMDB

**Contents**

[TOCM]

## Features

- Version min 21
- Implementation of Androidx
- Injection of dependencies with Dagger
- Zoom instagram style with zoomy
- Architecture MVP
- Render images with Glide
- Show videos on YouTube with YouTube API
- Managing lists with
- SearchView in toolbar
- Request with Retrofit, functions with cache and gson
- Languages Spanish and English

##Rappi section

#### App layers

Persistence layer. It is the place where all the information of the app is stored, either to obtain it or to save it.

- DetailPresenter,
- DiscoverPresenter,
- SearchPresenter

Business layer. It is the necessary logic so that the user interface can work with each request. In summary is the communication between user and data.

- ItemMedia
- ItemVideo
- ResponseDiscover
- ResponseVideos
- ItemMediaAdapter
- ItemMediaAdapterListener
- SearchMediaAdapter
- SearchMediaAdapterListener
- ItemMediaViewHolder
- DetailListener
- DiscoverListener
- SearchListener
- AdapterModule
- PresenterModule

Presentation layer. The interaction with the business layer is given by means of this layer.

- DetailActivity
- DiscoverFragment
- InformationActivity
- SearchFragment
- SplashFragment
- MainActivity

Session layer. It is the duration of the instances of each object within the flow of the app.

- SessionScope
- ApplicationScope
- ApplicationContextModule 

Network layer. Connection with services.

- ApiClient
- ApiConstants
- RetrofitModule 

#### Responsibilities

>Models
- ItemMedia. Model for movies
- ItemVideo. Model for video of movie
- ResponseDiscover. Model full of response in top,pupular,etc.
- ResponseVideos. Model full of response for videos

>Adapters
- ItemMediaAdapter. Adapter for model ItemMedia
- ItemMediaAdapterListener. Communication between view and adapter 
- SearchMediaAdapter. Adapter for items in SearchFragment
- SearchMediaAdapterListener. Communication between SearchFragment and adapter
- ItemMediaViewHolder. Control of each part of item

>API
- ApiClient. Contains the endpoints
- ApiConstants. All the constants of ApiClient

>APP
- BaseApplication. Contains and initializes the app and its context to be able to inject dependencies

>DI
- ApplicationComponent. Is the interactor between the views and the dependency injection
- PresenterSubcomponent. Interactor used only to inject adapters and presenters
- AdapterModule. Module of adapters
- PresenterModule. Module of presenters
- RetrofitModule. Configuration of services, cache, etc. 
- ApplicationContextModule. Module of app and context

>Scope
- SessionScope. Scope specific for Session
- ApplicationScope. Scope general of app 
                           
>UI
- SplashActivity. Splash of app 
- MainActivity. View general and main
- InformationActivity. View of information of app
- DiscoverFragment. View of section discover
- DiscoverListener. In MVP is the presenter
- DiscoverPresenter. Interactor between view and presenter of discover
- SearchFragment. View of section search
- SearchListener. In MVP is the presenter
- SearchPresenter. Interactor between view and presenter of search
- DetailActivity. View of detail 
- DetailListener. In MVP is the presenter
- DetailPresenter. Interactor between view and presenter of detail

>Util
- Constants. Constants of app
- CarrouselOnScrollListener. Extends of OnScrollListener and controls the speed 
- DiscoverOnScrollListener.Extends of OnScrollListener and controls the speed
- SearchOnScrollListener.Extends of OnScrollListener and controls the speed
- SpeedyGridLinearLayoutManager.Extends of GridLayoutManager and controls the speed
- SpeedyLinearLayoutManager.Extends of LinearLayoutManager and controls speed
- SpinnerCustomAdapter. Custom adapter 
- SpinnerCustomItem. spinner item model

#### Single Responsibility Principle

- Es el principio más importante de SOLID, hablá de encapsular todas las cosas que son parecidas en la misma clase, en otras palabras reúne las cosas que cambian por las mismas razones y separa las que cambian por razones deferentes.
- Su propósito es evitar clases complejas que tengan un objetivo único y en caso de cambios una modificación encapsulada.
    También nos brinda la posibilidad de acoplar y desacoplar clases  de manera sencilla. 

#### Clean code

- Un buen código limpio tiene principalmente comentarios de creación y modificación.
- Código autodescriptible
- Comentarios útiles
- Código escalable y fácil de mantener
