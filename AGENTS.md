# Agent Instructions for Retro Savings Challenge App

This document provides guidance for AI agents working on this project.

## 1. Project Overview

The goal is to build a retro-themed micro-savings challenge app with a strong emphasis on interactive animations and performance on low-end devices (1 GB RAM).

## 2. Tech Stack

- **UI:** Jetpack Compose (Material3)
- **Complex Animations:** Lottie, MotionLayout (if needed)
- **Custom Rendering:** Skia/Canvas via Compose
- **Database:** Room
- **Networking:** Retrofit + OkHttp
- **Async:** Coroutines + Flow

## 3. Performance is Critical

- **Target:** 1 GB RAM Android Go devices.
- **Memory:** App baseline ≤ 60–90 MB.
- **FPS:** 55–60 fps target (30 fps fallback).
- **Key Principles:**
    - Use GPU-friendly transforms (translate, scale, rotate, opacity).
    - Pool and reuse objects like particles.
    - Pre-bake animations into Lottie or sprite sheets.
    - Implement adaptive fidelity based on device profile (HIGH/MEDIUM/LOW).

## 4. Visual Direction

- **Theme:** 80s/90s synthwave, pixel-smooth UI, soft CRT glow, neon palette.
- **Palette:** Base dark, Neon Cyan (#00E5FF), Magenta (#FF66B3), Retro Yellow (#FFD166).
- **Typography:** Inter/Poppins for headings, pixel-style for badges.

## 5. Development Workflow

1.  **Check the Plan:** Always refer to the current plan before making changes.
2.  **Implement Composables:** Build UI components using Jetpack Compose.
3.  **Prioritize Adaptive UI:** When implementing features, consider how they will adapt to different device profiles (HIGH, MEDIUM, LOW).
4.  **Test on Low-End Profiles:** Regularly simulate or test on low-end device configurations.
5.  **Profile Your Work:** Use the Android Studio Profiler to check for memory leaks, jank, and performance bottlenecks.
