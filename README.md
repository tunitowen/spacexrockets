## Space X Rockets Android App

An Android app to display a list of all SpaceX Rockets, and let the users click into a rocket to read more.

### Architecture
This app uses MVVM, and a Repository Pattern. <br/>
Screen -> ViewModel -> Repository -> Service

### Modules
The app is made up of modules to help with build times and keep the code organised
* app: main application
* core: shared utilties and extension functions
* models: network and business data models
* network: repository and services
* ui: custom UI components

### Testing
We reply on Robolectric Compose UI testing. For the ViewModel and upwards, standard Junit testing is implemented.

### Libraries

* Dependency Injection: Koin
* Networking: Retrofit, OKHttp, Kotlin Serialization
* Image Loading: Coil
* Testing: Robolectric, MockK

### Areas to improve with more times
* Further unit testing of the navigation controller, more testing on the details screen
* Given a more complicated app, we would need to implement Espresso End-to-End testing
* More work on App Theming, to ensure every component is styled according to a design system / app designs