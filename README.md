# RiskProject
SYSC3110 Project/ Fall2020
SYSC3110 Fall 2020 RISK Game Project Version 1.0; Cost: FREE ; 23/11/2020

The project can be reached at:
Voice: 613 111 2222
Website: www.culearn.carleton.ca
Email: Sarah Jaber: sarahjaber@cmail.carleton.ca
       Judy Hamwi: judyhamwi@cmail.carleton.ca
       Walid Baitul Islam: walidbaitulislam@cmail.carleton.ca
       Diana Marie Miraflor: dianamariemiraflor@cmail.carleton.ca


Description:
___________

- The project is a GUI-absed playable version of RISK Game that applies 
the auto-setup of the game. The program is played via mouse and users are to 
enter the number of players, and number of AI players through the menu bar. 
The program launches a GUI that allows  the user to start a new game, choose 
the number of players, number of AI players playing the game, attack a country 
by choosing the country to attack from and attack to, draft bonus armies,
fortify armies, end their turn, and exit the game by button presses. 

- Run main() of risk.view.RiskViewFrame to load the game

- The project is made up of twenty-six files:
   
risk.controller
- AIInitializationController.java
- AttackController.java
- AttackPhaseController.java
- DraftPhaseController.java
- EndTurnController.java
- FortifyController.java
- FortifyPhaseController.java
- HelpController.java
- InitializationController.java
- NewGameController.java
- QuitGameController.java

risk.model
- AttackPhase.java
- Board.java
- Continent.java
- Country.java
- Dice.java
- Die.java
- DraftPhase.java
- FortifyPhase.java
- Game.java
- GameState.java
- Player.java

risk.view
- BoardView.java
- ContinentView.java
- risk.view.java
- RiskViewFrame.java

Changes:
________

Milestone 2
- The game is a GUI-based version
- Added unit tests for the risk.model of the game
- UML and sequence diagrams are up to date
- Fixed errors from Milestone 1 such as:
	- Added "Road Map" and "Known Issues" in README file
	- Separated logic for creating the board in several methods for each continent 
	- Used a hash map to associate the number of players with the 
		number of armies each player receives
		- Got rid of "magic numbers"

Milestone 3
- New features such as: 
	- Draft Phase: Players receive bonus armies dependent on the number of countries
	and continents they own
	- Fortify Phase: Players move an army from a country they own to another 
	country they own
	- AI Players: User now has the option to play with AI players.
- Removed the background image to avoid an error, but will include for Milestone 4
- Added JUnit tests for AI's fortifying phase and drafting phase.
NOTE: Code still does not read the board from a file, but will implement for Milestone 4


Road Map:
__________

MILESTONE 2 (Accomplished)
Achieved by November 9, 2020
- GUI-based version of the RISK game
	- Add View and Control of MVC
- JUnit tests for the game's risk.model
- Fix errors from Milestone 1 and update UML and sequence diagrams

MILESTONE 3
Achieve by November 23, 2020
- Add features such as:
	- AI player
	- Bonus army placement
	- Troupe movement phase

MILESTONE 4
Achieve by December 7, 2020
- Add save/load features
- Add custom maps that are able to be loaded into the game


Known Issues: 
______________

- It is crucial for players to follow the rules of how to attack, draft and fortify
(rules on how to attack, fortify and draft are in README.txt). Otherwise, code will break.
- After selecting an attacking country and pressing the attack button, players are not 
able to select another country to attack from and are forced to fortify, or pass their turn 
to another player.
- Countries with the same coloured font as their continent's background camouflages the 
name of the country. 
- No test for AIAttack() because the dice values for the AI player are randomized - Cannot
assert an expected value.


Installation:
____________

IntelliJ IDEA 2020.2.1 must be installed
JDK 14.0.2 recommended or any other version must be installed


References:
___________

Brynen, Rex. "Teaching International Relations through Popular Games, Culture
and Simulations (Part 2)." PAXsins, 9 Sept. 2014, pax sims.wordpress.com/2014/
09/08/teaching-international-relations-through-popular-games-culture-and-simu
Lotions-part-2/.

Parker Brothers, 1959. RISK!. Pawtucket: Hasbro Inc. 


License:
_______

Copyright (c) 2020 Sarah Jaber 
Copyright (c) 2020 Walid Baitul Islam
Copyright (c) 2020 Judy Hamwi
Copyright (c) 2020 Diana Marie Miraflor 
 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

