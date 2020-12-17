## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

## Dependency Management

The `JAVA DEPENDENCIES` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-pack/blob/master/release-notes/v0.9.0.md#work-with-jar-files-directly).
"# Qualite_logiciel" 


Bienvenue dans le programme du bowling.  

Ce programme va gérer votre partie de bowling avec les régles habituelles du bowling. 

Pour lancer la partie, il vous suffit d'éxécuter le fichier bowling.jar. Ensuite une fenetre va s'afficher. 
"Nom du joueur: " d'ici vous pouvez entrer les noms des joueurs . Vous pouvez en entrer autant que vous voulez. Pour valider le nombre de joueurs, il vous suffit de valider le champs en le laissant vide. 
Puis la partie commence. Vous pouvez voir d'afficher le numero du tour et la personne qui doit jouer.
Vous accedez à une nouvelle entrée clavier pour mettre le nombre de quilles tombées (compris entre 0 et 10) ("Nombre de quilles tombées:"). 

Le programme est capable de reconnaitre un strike et un spare. Pour le compte des points, il ne fait qu'additionner les points et n'integre pas encore les particularités du strike et du spare. 
Pour le 10eme tour de jeu, les joueurs ne jouent qu'un lancer s'ils font un strike et joue deux lancer dans les autres cas. 
