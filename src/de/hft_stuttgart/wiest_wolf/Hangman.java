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
    	String word;
    	String choice;
    	LinkedList<Character> checked = new LinkedList<>();
    	char letter;
    	char[] guessed;
    	boolean foundLetter;
    	int trials;
    	char[][][]hangmanTexture;
    	
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
}
