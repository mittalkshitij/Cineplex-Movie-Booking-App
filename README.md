# Cineplex - Prototype Movie Booking App

Cineplex is a movie booking application built with Jetpack Compose, following the MVVM architectural pattern with Clean Architecture principles. It provides a seamless and intuitive experience for browsing and booking movies.

## Features

- **Movie Listings**
  - Browse current and upcoming movies.
  - View detailed movie information including overview, cast, and rating.
- **Booking**
  - Select preferred movie showtimes and seats.
  - Multiple payment options including credit/debit cards, digital wallets, and net banking.
  - Booking history and e-tickets.
- **User Profile**
  - Manage personal information.
  - View booking history.

## Technology Stack

- **Frontend:**
  - Jetpack Compose
  - ViewModel
  - Navigation Component
- **Architecture:**
  - MVVM (Model-View-ViewModel)
  - Clean Architecture    
    - Domain Layer
    - Data Layer
    - Presentation Layer
- **Backend:**
  - RESTful API
    
- **Additional Tools and Libraries:**
  - Dagger Hilt (Dependency Injection)
  - Retrofit (Network Calls)
  - Coroutines and Flow (Asynchronous Programming)
  - Glide (Image Loading)

## Installation

1. Clone the repository:
   ```bash
   https://github.com/mittalkshitij/Cineplex-Movie-Booking-App.git
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the app on an emulator or a physical device.

## References

- **API:** The movie data is sourced from [The Movie Database (TMDb) API](https://www.themoviedb.org/documentation/api).
- **UI Design:** The UI design is available on [Figma](https://www.figma.com/community/file/1329360533750743940).
