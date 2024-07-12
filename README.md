# WeatherWise

WeatherApp is a simple, elegant weather application built using modern Android development tools. It leverages the power of Jetpack Compose for UI, Retrofit for networking, and follows the MVVM (Model-View-ViewModel) architecture pattern.

## Features

- Display current weather information for any location.
- Beautiful UI with Jetpack Compose.
- Real-time weather data from a weather API.
- MVVM architecture for a clean and maintainable codebase.

## Technologies Used

- **Kotlin**: Programming language.
- **Jetpack Compose**: Modern toolkit for building native Android UI.
- **Retrofit**: Type-safe HTTP client for Android and Java.
- **MVVM Architecture**: For separation of concerns and better code management.

## Screenshots

Here are some screenshots of the application:

<img src="https://github.com/Aryan1patel/WeatherApp/assets/144524575/6edb6e65-c783-4d0a-8970-43d2843ded18" width="300">
<img src="https://github.com/Aryan1patel/WeatherApp/assets/144524575/219976d3-9fd9-4ff7-b458-cbbd67206ad3" width="300">
<img src="https://github.com/Aryan1patel/WeatherApp/assets/144524575/2e4b1a55-36ba-4536-89b3-7a4e2f32dc7e" width="300">


## Architecture

This project follows the MVVM (Model-View-ViewModel) architecture:

- **Model**: Handles the data layer. In this project, it's responsible for fetching data from the weather API using Retrofit.
- **View**: The UI layer, built using Jetpack Compose.
- **ViewModel**: Acts as a bridge between the Model and the View. It holds the app's UI-related data and handles the business logic.

## Dependencies

Here are the main dependencies used in this project:

- **Retrofit**: For network requests.
    ```gradle
    val retrofitVersion = "2.11.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    ```
- **Jetpack Compose**: For building the UI.
    ```gradle
    implementation 'androidx.compose.ui:ui:1.3.0'
    implementation 'androidx.compose.material3:material3:1.0.1'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1'
    ```
- **Coil**: For image loading.
    ```gradle
    implementation("io.coil-kt:coil-compose:2.6.0")
    ```

## Contributions

Contributions are welcome! Please open an issue or submit a pull request if you have any improvements or bug fixes.

## Contact

For any inquiries or questions, feel free to reach out:

- **Email**: ppparyanpatel@gmail.com
- **GitHub**: [Aryan1patel](https://github.com/Aryan1patel)

---

Feel free to update the placeholders with actual values, paths to your screenshots, and any additional information you find relevant.
