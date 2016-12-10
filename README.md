# Hangman

## Description
We had a group project on our university to program a Hangman game.
The following points had to be covered:
* 1. make *first* notes about a single round of Hangman
* 2. make a draft of the programs flow
* 3. note the now better thought program flow possibly neat and detailed
* 4. Think about which steps are very complex and create a plan for their flow, too.
* 5. *Now* create a new project in your IDE and call it "Hangman"
* 6. code the program according to your previously made notes.

## Our Implementation
* 1. Brainstorm about it, try a few rudimental things in the IDE if they even work like we want them to
* 2. Creating a flowchart with [Dia](http://dia-installer.de/) for the whole program (means for main and all other methods)
* 3. testing the program working
* 4. fix bugs
* 5. make further enhancements as time was left to do them. (all things done after the first working version, where added to the flowchart aswell)

## How to play
You have two options to play:
* you enter an own word to play with
* you can let the program choose an own random one from the file values.conf in the working directory  
  We have added a values.conf with MANY random words, so happy play if you bored :D

In game you can guess single letters or a whole word.  
If the game ends, never mind if you won or failed, you have the option to replay or not.

## values.conf
You can replace (or create if it doesn't exist) the values.conf in the working dir from which you play  
with an own one.  
IMPORTANT: Each line get's interpreted as one String to choose from.  
That means, you can also make complete sentences or similar.
