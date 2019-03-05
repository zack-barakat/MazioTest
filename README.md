# MozioTest

## 1 App Architecture
#### 1.1 General App Architecture
The application uses [Dagger2](https://google.github.io/dagger/) to inject `repositories` (whom maintain data layer and business logic) into different components of the app (fragments, activities, views, services, test cases, presenters, etc...).

**Important Note:** All presenters and classes interacts with the `repositories` **interfaces** only and does not interact with Preference Helper directly.
#### 1.2 Types of Repsotitoeies:
Repositories interfaces can be found under [`data.repositories`](https://github.com/zack-barakat/MozioTest/tree/master/app/src/main/java/com/android/maziotest/data/repositories) package. Each repository is responsible for handling its side of the business logic.
Dagger2 should maintain **only one copy** of each repository per app session (Singlton behaviour).

* **PizzasRepository**: Responsible for all pizzas data such as (fetching pizzas, persistig pizzas in memory, order pizza,and etc...).

#### 1.3 UI componentes architecture
Model View Preseneter known as MVP is the the architecture pattern used to develop Mozio Test application UI.
**Model:** It is responsible for handling the data part of the application.
**View:** It is responsible for laying out the views with specific data on the screen.
**Presenter:** It is a bridge that connects a Model and a View. It also acts as an instructor to the View.
To read more about MVP Architecture you may refer to these links:
- [MVP Architecture](https://blog.mindorks.com/essential-guide-for-designing-your-android-app-architecture-mvp-part-1-74efaf1cda40#.lkml1yggq)
- [MVP Android Gudelines](https://medium.com/@cervonefrancesco/model-view-presenter-android-guidelines-94970b430ddf)

## 2 Language used
#### 2.1 Kotlin
#### 2.2 Java

## 3 Main Libraries used

* [Dagger2](https://google.github.io/dagger/android.html) - Dependency injection framework
* [Anko](https://github.com/Kotlin/anko) - Set of Kotlin extensions to make android development faster
* [RxJava](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid) - Reactive programming, simplifies work with threading and concurrency in java and android.
* [Mockito](https://github.com/mockito/mockito) and [Robolectric](https://github.com/robolectric/robolectric) - Unit test framework and mocking tools.

## 4 Unit Test

#### All presenters have equivalent test presenets to test mvp intercations and ensure logic is done properly

## 5 App UI flow and functionality
#### There are two screens in the app, menu screen where user can view pizzas and order and custom pizza screen where user the can order half & half pizza, and order summary screen where user can view pizza order summary.
### Menu Screen
<img src="https://user-images.githubusercontent.com/13542406/53826696-32c5d580-3fb4-11e9-80fc-a63a56b8318c.png" width="200" >

### Custom Pizza Screen
<img src="https://user-images.githubusercontent.com/13542406/53826606-ef6b6700-3fb3-11e9-990e-015d534c0826.png" width="200" >

### Order Summary Screen
<img src="https://user-images.githubusercontent.com/13542406/53826607-ef6b6700-3fb3-11e9-83ce-02e7549e612d.png" width="200" >

### To test the the whole flow you can download the apk using this link from bitrise: https://app.bitrise.io/artifact/12636836/p/344a303d704ef2473e3df7d4676ba50c

