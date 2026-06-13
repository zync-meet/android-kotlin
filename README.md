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
