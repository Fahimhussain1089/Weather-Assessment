# Weather Assessment App

This Android application demonstrates weather data fetching and user management capabilities, implemented as part of the I Payments Android Developer assessment.

## Features

- Onboarding screen with welcome message
- Login screen with validation
- User management (CRUD operations with Room database)
- Weather data display from OpenWeatherMap API
- MVVM architecture pattern
- Kotlin implementation

## Project Structure
app/
├── manifests/
│   └── AndroidManifest.xml
├── kotlin+java/
│   └── com.hussain.weatherassessmenttest/
│       ├── Activity/
│       │   ├── LoginActivity.kt
│       │   ├── MainActivity.kt
│       │   ├── OnboardingActivity.kt
│       │   ├── UserFormActivity.kt
│       │   ├── UserListActivity.kt
│       │   └── WeatherActivity.kt
│       ├── Adapter/
│       │   └── UserAdapter.kt
│       ├── data/
│       │   ├── AppDatabase.kt
│       │   ├── User.kt
│       │   ├── UserDao.kt
│       │   └── WeatherData.kt
│       ├── network/
│       │   ├── ApiClient.kt
│       │   └── WeatherApiService.kt
│       ├── repository/
│       │   ├── UserRepository.kt
│       │   └── WeatherRepository.kt
│       └── viewmodel/
│           ├── UserViewModel.kt
│           └── WeatherViewModel.kt
└── res/
    ├── drawable/
    ├── layout/
    ├── values/
    └── ...

![WhatsApp Image 2025-06-25 at 14 32 29 (1)](https://github.com/user-attachments/assets/6de59dd9-89bf-45a9-a5be-b36a9bd1ab5c)
![WhatsApp Image 2025-06-25 at 14 32 29](https://github.com/user-attachments/assets/bea57c4c-a33d-47e6-a643-f1ba5eaeb090)

![WhatsApp Image 2025-06-25 at 14 32 28](https://github.com/user-attachments/assets/8e3f914f-a2a1-4ce2-98ee-52aa7bbd58b1)

![WhatsApp Image 2025-06-25 at 14 32 27 (1)](https://github.com/user-attachments/assets/f11dc37c-440b-42e1-bbae-67169eb41ebc)
![WhatsApp Image 2025-06-25 at 14 32 27](https://github.com/user-attachments/assets/b7fb7eb5-390b-48f4-9819-a091a29d179e)


## Implementation Details

### Completed Tasks

1. **Onboarding Screen**
   - Welcome message with Login button

2. **Login Screen**
   - Email and password validation
   - Test credentials: 
     - Username: `testapp@google.com`
     - Password: `Test@123456`

3. **User Management**
   - Room database implementation for user data
   - User list display with swipe-to-delete functionality
   - User form with validation
   - CRUD operations

4. **Weather Integration**
   - OpenWeatherMap API integration
   - Displays:
     - Current temperature
     - Weather type
     - Humidity
     - Wind speed

5. **Navigation**
   - Back button functionality
   - Logout flow
   - Proper activity navigation

### Technical Specifications

- **Architecture**: MVVM (Model-View-ViewModel)
- **Language**: Kotlin
- **Libraries Used**:
  - Retrofit for network calls
  - Room for local database
  - ViewModel and LiveData
  - RecyclerView with swipe-to-delete

## API Usage

The app uses OpenWeatherMap API with the following endpoint:
