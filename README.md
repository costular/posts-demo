# Post demo 📰🚀

## Stack
- **100%** Kotlin
- RxJava2
- Jetpack navigation
- Retrofit
- Koin
- Material
- Room
- MockK
- Truth
- Espresso

## Architecture

### Clean Architecture with MVVM

I used MVVM  because of its benefits and the extended use since it’s part of Android Jetpack. 

However, I’m a big fan of MVI since it works pretty good with user interfaces and reactive programming like RxJava or coroutines with Flow. I didn’t used it looking for simplicity since MVVM has a better learning curve and readability so will be easier to understand at first look for everyone.

### Modules

I decided to just include a single module **app** separated by three packages which are the clean architecture layers (data, domain & presentation).

In other projects I’ve used multimode projects since it has a lot of benefits:	
+ Faster build time
* Conflicts reduction (related to work collaborations).
* Re-usability
* Feature encapsulation
* Better dependencies management.
* Team work collaboration between teams so you don’t need to rebuild the whole app just the module you touched.

Also thanks to **buildSrc** plugin the project handles the dependency much better than normal 

However, I’ve decided to not use multimode on this project because of the next reasons:
- over engineering
- creating three modules (data, domain, presentation) should be the same as reason above and also distributed monolith which is not great IMO.


### Data layer

First you could think we should create a repository for every entity following the repository pattern:

- `UserRepository`
- `PostRepository`
- `CommentRepository`

However,  `Comment` it’s an associated entity of `Post` so it makes no sense by its own. In conclusion, `Post` acts as an aggregate root.

Keeping in mind the aggregate root and we don’t need to interact with comment or user entities directly so I just created a single repository to handle the posts just exposing an API.

## Tests
### Unit tests

To run the tests just execute the next command
```
./gradlew test
```

### Instrumentation tests (integration & UI)

To run instrumented tests just execute the next command

```
./gradlew connectedAndroidTest
```

Or you can execute `./run_instrumented_tests.sh` which disables device animations and enables them when it finishes.

---
UI tests uses [Robot Pattern]([Presentation: Testing Robots - Jake Wharton](https://jakewharton.com/testing-robots/)) in order to separate the what from the how in order to let QA write UI tests by themselves without needing to know about views nor Espresso.