package de.hft_stuttgart.wiest_wolf;


// imports
import java.util.Random;
import java.util.Scanner;
import java.util.LinkedList;

/**
 *
 * @author Lukas Wiest
 * @author Erik Wolf
 */
public class Hangman
{
	public static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        // TODO code application logic here
    	//Lokale Variablen
    	String word = "";
    	String choice;
    	LinkedList<Character> checked = new LinkedList<>();
    	char letter;
    	char[] guessed;
    	boolean foundLetter;
    	int trials;
    	char[]hangmanTexture;
    	
    	menu:
    	do
    	{
    		System.out.print("eigenes Wort eingeben? (y/n)");
    		choice = in.next();
    		
    		switch (choice)
    		{
    		case "y":
    		case "Y": 	System.out.print("bitte Wort eingeben: ");
    					word=in.next();
    			break;
    		case "n":
    		case "N":	System.out.print("Wort wird ausgewaehlt...");
    					word = getWordFromFile();
    			break;
    		default:	continue menu;
    		}
    		
    	}while(false);
    	
    	word = word.toUpperCase();
    	guessed = new char[word.length()];
    	for(int i = 0; i < guessed.length; i++)
    	{
    		guessed[i] = '_';
    	}
    	
    	
    	
    	
    }
    
    public static int randInt(int min, int max)
    {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
    public static String getWordFromFile()
    {
    	
    }
    
    public static String[] initHangman()
    {
    	// local vars
        String[] states = new String[8];
        
        states[7] = "\n __\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n\"\"\"\n|\"|\n| |\n: :\n. .";
        
        states[6] = "\n _\n| |\n| | / /\n| |/ /\n| | /\n| |/\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n\"\"\"\n|\"|\n| |\n: :\n. .\n ";
        
        states[5] = "\n ___________________\n| ._________________|\n| | / /\n| |/ /\n| | /\n| |/\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n\"\"\"\n|\"|\n| |\n: :\n. .\n";
        
        states[4] = "\n ____________________\n| .__________________|\n| | / /\n| |/ /\n| | /\n| |/\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"|\n|\"|\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"|\"|\n| |                  | |\n: :                  : :\n. .                  . .\n";
        
        states[3] = "\n  ___________.._______\n| .__________))______|\n| | / /      ||\n| |/ /       ||\n| | /        ||\n| |/         ||\n| |          ||\n| |          ||\n| |          ||\n| |          (\\__\n| |           `--'\n| |\n| |\n| |\n| |\n| |\n| |\n| |\n\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"|\n|\"|\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"|\"|\n| |                  | |\n: :                  : :\n. .                  . .\n";
        
        states[2] =   "\n" + "  ___________.._______\n"
                    + "| .__________))______|\n" + "| | / /      ||\n"
                    + "| |/ /       ||\n" + "| | /        ||    .-''.\n"
                    + "| |/         ||   /  _  \\\n"
                    + "| |          ||   |  `/,|\n"
                    + "| |          (\\\\_  \\`_.'\n"
                    + "| |           `- .-'--'.\n"
                    + "| |             /Y . . Y\\\n"
                    + "| |            // |   | \\\\\n"
                    + "| |           //  | . |  \\\\\n"
                    + "| |          ')   |   |   (`\n"
                    + "| |               ||'||\n" + "| |               || ||\n"
                    + "| |               || ||\n" + "| |               || ||\n"
                    + "| |              /_| |_\\\n"
                    + "\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"|\n"
                    + "|\"|\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"|\"|\n"
                    + "| |                  | |\n"
                    + ": :                  : :\n"
                    + ". .                  . .\n    ";
        
        states[1] =   "\n" + "  ___________.._______\n"
                    + "| .__________))______|\n" + "| | / /      ||\n"
                    + "| |/ /       ||\n" + "| | /        ||.-''.\n"
                    + "| |/         |/  _  \\\n" + "| |          ||  `/,|\n"
                    + "| |          (\\\\`_.'\n" + "| |         .-`--'.\n"
                    + "| |        /Y . . Y\\\n" + "| |       // |   | \\\n"
                    + "| |      //  | . |  \\\n" + "| |     ')   |   |   (`\n"
                    + "| |          ||'||\n" + "| |          || ||\n"
                    + "| |          || ||\n" + "| |          || ||\n"
                    + "| |         /_| |_\\\n"
                    + "\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"|\n"
                    + "|\"|\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"|\"|\n"
                    + "| |                  | |\n"
                    + ": :                  : :\n"
                    + ". .                  . .\n";
        
        states[0] =   "\n" + "  ___________.._______\n"
                    + "| .__________))______|\n" + "| | / /      ||\n"
                    + "| |/ /       ||\n" + "| | /        ||.-''.\n"
                    + "| |/         |/  _  \\\n" + "| |          ||  `/,|\n"
                    + "| |          (\\\\`_.'\n" + "| |         .-`--'.\n"
                    + "| |        /Y . . Y\\\\\n" + "| |       // |   | \\\\\n"
                    + "| |      //  | . |  \\\\\n"
                    + "| |     ')   |   |   (`\n" + "| |          ||'||\n"
                    + "| |          || ||\n" + "| |          || ||\n"
                    + "| |          || ||\n" + "| |         / | | \\\n"
                    + "\"\"\"\"\"\"\"\"\"\"|_`-' `-' |\"\"\"|\n"
                    + "|\"|\"\"\"\"\"\"\"\\ \\       '\"|\"|\n"
                    + "| |        \\ \\        | |\n"
                    + ": :         \\ \\       : :\n"
                    + ". .          `'       . .\n";
        
        return states;
    }
}
