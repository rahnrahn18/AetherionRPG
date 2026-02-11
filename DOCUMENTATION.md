# Pixel RPG Engine - Documentation

## 1. Architecture Overview
The project is built using a modular component-based architecture in Kotlin for Android.

### Core Engine (`com.rpg.aetherion.engine`)
*   **GameSurface**: The main view handling the `Canvas` and user input. It manages the `GameLoop` and delegates drawing to the current `Scene`.
    *   **Scaling**: The canvas is scaled (2x-4x) based on device density to preserve the pixel-art aesthetic on high-resolution screens.
*   **GameLoop**: A dedicated thread that runs the game loop at a fixed 60 FPS target.
*   **InputManager**: Handles touch events and converts them into a virtual joystick vector (`joystickX`, `joystickY`).
*   **AssetManager / AssetLoader**:
    *   `AssetLoader`: A generic caching loader for Bitmaps.
    *   `AssetManager`: A singleton that preloads specific game assets (Player sprites, Map tiles) from the complex `assets/PixelPack` structure.
*   **Sprite / Animation**: Classes to handle rendering of single frames or animated sequences. Supports horizontal flipping.

### Game Logic (`com.rpg.aetherion.game`)
*   **Scene**: Abstract base class for game states (Menu, Play, etc.).
*   **PlayScene**: The main gameplay scene. It manages the `Player`, `MapManager`, and `Camera`.
    *   **Collision**: Implements sliding collision detection against map walls.
*   **Entity**: Base class for game objects.
*   **Player**:
    *   Handles movement based on `InputManager`.
    *   Manages animation states (Idle vs Run) and directions (Up, Down, Side).
    *   Dynamically estimates frame counts from sprite sheets to avoid jitter.
*   **MapManager**:
    *   Procedurally generates a simple walled arena.
    *   Renders tiles using assets from `PixelPack/Environment`.
    *   Handles scaling and culling (drawing only visible tiles).

## 2. Asset System
The game uses assets located in `app/src/main/assets/PixelPack`.
*   **Characters**: `PixelPack/Entities/Characters/Body_A/Animations/`
    *   Uses `Run_Base` and `Idle_Base` folders.
*   **Environment**: `PixelPack/Environment/Tilesets/`
    *   Uses `Floors_Tiles.png` and `Wall_Tiles.png`.

## 3. How to Add New Assets
1.  **Place the file**: Put the `.png` file in the appropriate folder under `assets/PixelPack`.
2.  **Load it**: In `AssetManager.kt`, add a variable and call `loadBitmap` with the relative path.
3.  **Use it**:
    *   For Entities: Create a `Sprite` or `Animation` using the loaded bitmap.
    *   For Maps: Update `MapManager` to reference the new bitmap.

## 4. Progress Checkpoint
- [x] Core Engine Loop & Surface
- [x] Virtual Joystick Input
- [x] Modular Scene System
- [x] Player Movement & Physics (Sliding Collision)
- [x] Player Animation (Idle/Run, 4-Directional, Flipping)
- [x] Map Rendering & Scaling (Fixed black screen & small size issues)
- [x] Asset System Integration (Complex folder structure support)

## 5. Troubleshooting
*   **Black Screen**: Ensure `MapManager` is picking a valid tile coordinate (currently offset to 16,16).
*   **Jittery Animation**: The `Player` class attempts to guess frame counts. If a sprite sheet has non-standard dimensions, adjust `estimateFrameCount`.
