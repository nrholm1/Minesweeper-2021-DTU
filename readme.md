# Minesweeper 2021

Software project for DTU02121, Jan 2021

- Niels
- Magnus
- Massimo
- Simon

## Overview of the project structure:
```bash
src
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
    │   ├── HexTile
    │   ├── PixelButton
    │   ├── PixelSlider
    │   ├── TimeCounter
    │   └── TopMenuView
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
