# Minesweeper 2021

Software project for DTU02121, Jan 2021

- Niels
- Magnus
- Massimo
- Simon

## Idea for architecture

##### MVC (model-view-controller)

<b>Model:</b>
Field class for representing each individual field + state, 
Board class for representing the entire board of fields. 
 
 
<b>View:</b>
GameWindow for containing <em>just</em> a 
graphical representation of the board-class with all its fields:
![Board drawing](./PresentationAssets/Minesweeper2021.png?raw=true)

MainMenu for all menu stuff - basically the stuff around the GameWindow.

<b>Controller:</b>
Class containing methods for EventHandlers in the views, 
modifying data in the models and interfacing between the views and models.