# Minesweeper 2021

Software project for DTU02121, Jan 2021

- Niels
- Magnus
- Massimo
- Simon

## Overview of the project structure:
```bash
├── Controller
│   ├── GameController
│   └── NavigationController
├── Driver
│   └── Main (!)
├── Images
├── Model
│   ├── Util
│   │   └── BoardBuilder
│   ├── Board
│   └── Field
├── Networking
│   ├── ClientDriver
│   ├── FieldDTO
│   ├── HttpListener
│   ├── MultiplayerService
│   └── Parser
├── Services
│   ├── Assets
│   │   ├── Styles.css
│   │   ├── ...
│   │   └── PixelFont.ttf
│   ├── BlankFieldSolver
│   ├── ExternalResources
│   └── ThreadManager
└── View
    ├── Components
    │   ├── Coord
    │   ├── Kurwe
    │   ├── Kurwe
    │   ├── Kurwe
    │   └── Vec2D
    ├── DefeatScreen
    │   ├── DefeatMultiplayerView
    │   └── DefeatView
    ├── Util
    │   ├── BoardView
    │   ├── BoardViewBuilder
    │   └── SingleplayerViewBuilder
    ├── MainMenuView
    ├── MultiplayerMenuView
    ├── MultiplayerView
    ├── SingleplayerMenuView
    ├── SingleplayerView
    └── VictoryView
```
