### v1.1.1
* Bug Fixes:
    * #8 the program won't allow Strings with underscores now, as they would catch you into a
	     endless loop.

### v1.1.0
* Bug Fixes:
    * #1 On program start the user can decide to use a own word or a random one from file.  
      If you chose to use an own word, but then entered an empty one, you where stuck in an endless loop.
    * in-game if you accidentally entered an empty character to guess, the program crashed
    * #6 The fully unguessed String get's printed before you enter the first letter or guess
    * #7 If you enter an own String, the console prints 1000 empty lines, that the other players searching don't see it

* Enhancements:
    * #2 The game can now differentiate if a word or a letter was entered to guess.  
         So, if the word was wrong you'll be one step closer to death, too.
    * #3 On loose you never knew what the word actually was. That's not very fair
    * #4 After the game you have the choice to replay the game
    * #5 If you enter a String not a single word, every character apart from letters are shown directly
    *    Add illusion of a heavy search if a random word is choosen by introduce a one second sleep 3x
