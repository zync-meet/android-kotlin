# Zync Android Client (Kotlin)

This is the native Android client for Zync, built with **Kotlin, Jetpack Compose, Room, Retrofit**, and **Coroutines**.

## Architecture

The project uses an MVVM (Model-View-ViewModel) architecture with a clean Offline-First strategy:

- **UI Layer**: Jetpack Compose screens for native material design rendering.
- **ViewModel**: Standard state management and reactive data fetching using Coroutines & Flows.
- **Repository**: Cache-then-Network synchronization. Room Database holds local cache, falling back gracefully to REST API responses on network failure.
- **Room DB Cache**: Local persistence layer for Projects, Tasks, and Meetings.
- **Retrofit HTTP Layer**: Modern HTTP network calling layer connecting directly to Render production APIs.

## Theme & Branding

The app adopts the brand guidelines of Zync: Deep Slate background (`#060B14`), card layout backgrounds (`#0D1421`), and cyan/brand blue highlights (`#1A8FD1`).

## Testing Strategy

We implement a clean, decoupled testing framework consisting of local JVM-based unit and integration tests.

### Phased Testing Structure

1. **Domain Models Phase**: Validates immutable instantiations and model integrity for core schemas (`User`, `Project`, `Task`, `Meeting`).
   - *Test file*: [ModelTests.kt](file:///C:/Users/Chitkul%20Lakshya/.gemini/antigravity/brain/3be101ac-c6b9-4c2f-bf9e-330790a2d575/scratch/temp-android-work/app/src/test/java/com/zync/android/models/ModelTests.kt)
2. **Local Caching (DAO) Phase**: Tests query filters and Room DB transactions.
3. **Repository Sync Phase**: Employs decoupling fakes ([Fakes.kt](file:///C:/Users/Chitkul%20Lakshya/.gemini/antigravity/brain/3be101ac-c6b9-4c2f-bf9e-330790a2d575/scratch/temp-android-work/app/src/test/java/com/zync/android/fakes/Fakes.kt)) to verify the cache-then-network strategy. Validates that cache remains intact during network failures and parses REST API updates correctly.
   - *Test files*: [ProjectRepositoryTest.kt](file:///C:/Users/Chitkul%20Lakshya/.gemini/antigravity/brain/3be101ac-c6b9-4c2f-bf9e-330790a2d575/scratch/temp-android-work/app/src/test/java/com/zync/android/repository/ProjectRepositoryTest.kt), [AuthRepositoryTest.kt](file:///C:/Users/Chitkul%20Lakshya/.gemini/antigravity/brain/3be101ac-c6b9-4c2f-bf9e-330790a2d575/scratch/temp-android-work/app/src/test/java/com/zync/android/repository/AuthRepositoryTest.kt)
4. **UI State & ViewModel Phase**: Tests coroutine-based state emissions via Flow and standard test dispatchers. Simulates UI navigation flow transitions.
   - *Test files*: [ProjectViewModelTest.kt](file:///C:/Users/Chitkul%20Lakshya/.gemini/antigravity/brain/3be101ac-c6b9-4c2f-bf9e-330790a2d575/scratch/temp-android-work/app/src/test/java/com/zync/android/viewmodel/ProjectViewModelTest.kt), [UiStateTests.kt](file:///C:/Users/Chitkul%20Lakshya/.gemini/antigravity/brain/3be101ac-c6b9-4c2f-bf9e-330790a2d575/scratch/temp-android-work/app/src/test/java/com/zync/android/ui/UiStateTests.kt)

### Running Tests

To execute all local JVM unit tests, run the following Gradle command:
```bash
./gradlew test
```

