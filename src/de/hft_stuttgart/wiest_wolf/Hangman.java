package de.hft_stuttgart.wiest_wolf;

// imports
import java.util.Random;

/**
 *
 * @author Lukas Wiest
 * @author Erik Wolf
 */
public class Hangman
{
    
    public static void main(String[] args)
    {
        // TODO code application logic here
    }
    
    public static int randInt(int min, int max)
    {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
