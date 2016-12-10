/**
* Copyright (c) 2016 Lukas Wiest & Erik Wolf. All rights reserved.
* 
*/

package de.hft_stuttgart.wiest_wolf;


// imports
import java.io.*;
import java.util.*;

/**
 *
 * @author Lukas Wiest
 * @author Erik Wolf
 */
public class Hangman
{
    // global variables
    public static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) throws InterruptedException
    {
        // dauerschleife um gesamtes main, da am Ende, falls nochmal gespielt werden soll, auch der Deklarationsblock nochmals durchlaufen werden muss
        init: while (true)
        {
            // locale variables
            String word = "";           // enthaelt spaeter das Wort das gesucht wird
            String choice;              // zum zwischenspeichern einer Entscheidung von Nutzerseite zur Auswertung der Wahl in einem Switch-Case Block
            LinkedList<Character> checked = new LinkedList<>(); // An die LinkedList wird im Verlauf des Spiels jeder Buchstabe, der geraten wird ans Ende der Liste gehaengt. Somit sind doppelte abfragen ausgeschlossen.
            char letter = ' ';          // Buchstabe den der Nutzer raten will
            char[] guessed;             // Char-Array fuer den Ratefortschritt. Zu Beginn mit Unterstrichen gefuellt, je mehr Buchstaben gefunden wurden, desto mehr Unterstriche werden durch Buchstaben ersetzt
            boolean foundLetter;        // Boolean um bei der Suche ob der geratene Buchstabe im Wort enthalten ist festzuhalten, dass einer gefunden wurde
            int trials = 8;             // man hat 8 moegliche Fehlschlaege, sind alle aufgebraucht, dann hat man verloren
            String[] hangmanTexture;    // hier wird die Textur fuer die 8 verschiedenen Galgenmaennchen Zustaende gespeichert. Bei jedem Fehlschlag wird die entsprechend passende Optik ausgegeben.
            
            // dauerschleife um Menu, wird nur bei zulaessiger Auswahl gebrochen
            menu: do
            {
                System.out.print("eigenes Wort eingeben? (y/n)");
                choice = in.nextLine();

                switch (choice)
                {
                case "y":   // Im Fall von yes, wird ein eigenes Wort eingelesen, dass dann die anderen Raten muessen
                case "Y":   System.out.print("bitte Wort eingeben: ");
                            word=in.nextLine();
                            
                            if(word.isEmpty())  // eine leere Eingabe ist unzulaessig, weshalb das menu von vorne gestartet wird
                            {
                                System.out.println("Leere eingabe unzulaessig.");
                                continue menu;
                            }
                            
                            for (int i=0; i < 1000; i++) System.out.println();  // nach Eingabe des zu suchenden Wortes gebe einige Zeilen aus, damit die die suchen es nicht sehen.
                            
                        break menu;
                        
                case "n":   // Im Fall von no lesen wir aus einer Datei zufaellig ein Wort ein
                case "N":   System.out.print("Wort wird ausgewaehlt");
                            word = getWordFromFile();
                            
                            for (int i=0; i < 3; i++)   // drei Durchlaeufe
                            {
                                Thread.sleep(1000);     // eine Sekunde warten
                                System.out.print(".");  // einen Punkt hinten anfuegen
                            }
                            System.out.println();       // danach in die naechste Zeile
                            
                        break menu;
                // wurde etwas anderes als n oder y eingegeben, wiederholt sich das menu kommentarlos
                }

            }while(true);   // Ende der menu-Schleife

            word = word.toUpperCase();              // schreibe das zu ratende Wort in Großbuchstaben
            guessed = new char[word.length()];      // initialisiere guessed mit der Laenge des zu ratenden Wortes
            for(int i = 0; i < guessed.length; i++) // fuelle guessed mit '_'
            {
                if (Character.isLetter(word.charAt(i))) guessed[i] = '_';   // fuer Buchstaben werden Unterstriche eingefuegt
                else guessed[i] = word.charAt(i);                           // fuer alles andere das entsprechende Zeichen aus dem zu suchenden String
                System.out.print(guessed[i]);                               // gebe in jedem Fall das eben eingefuellte Zeichen aus.
            }

            System.out.print("\n\n");               // zwei Leerzeilen als Abstand
            hangmanTexture = initHangman();         // get the Textures for the different states the hangman can have

            // dauerschleife um in-game Code
            loop: while(true)
            {
                foundLetter = false;                // setze foundLetter vor jedem Durchgang false
                String temp;                        // deklariere temporaeren String
                System.out.print("bitte Buchstabe eingeben: ");
                temp = in.nextLine().toUpperCase(); // lese Buchstabe oder ganzes Wort von Konsole ein und schreibe alles groß

                if (temp.isEmpty()) continue loop;  // falls leere Eingabe, neuer durchlauf
                
                if (temp.length() > 1)              // falls das Wort mehr als ein Zeichen hat, handelt es sich um ein Wort, nicht einen Buchstaben
                {
                    if(temp.equals(word))           // Hat der Nutzer das Wort korrekt erraten?
                    {
                        System.out.println("Du hast es erraten ;) das Wort ist:\n" + word);
                        break loop;                 // wenn ja, dann beende den in-game Kreislauf
                    }
                    
                    trials--;                       // falls nicht, verringere die Anzahl der verbleibenden Versuche um 1 und gebe die dazugehoerige Textur aus
                    System.out.println(hangmanTexture[trials]);
                    
                    if (trials <= 0)                // Falls jetzt keine Versuche mehr verbleiben, gebe das gesuchte Wort aus
                    {
                        System.out.println("Das gesuchte Wort war: " + word);
                        break loop;                 // und beende den in-game Kreislauf
                    }
                    
                    continue loop;                  // wenn wir ein Wort raten wollten, es falsch war und noch versuche uebrig sind, dann fange einen neuen durchlauf an
                    
                } // Ende des Wort handling
                
                letter = temp.charAt(0);            // der zu suchende Buchstabe ist der Buchstabe der Eingabe am Index 0 (erster Buchstabe)

                if(checked.contains(letter))
                { // pruefung ob der Buchstabe bereits in der LinkedList checked ist
                    System.out.print("Buchstabe bereits abgefragt.");
                    continue loop;                  // falls ja, beginne neuen Durchlauf
                }
                else
                {
                    checked.add(letter);            // andernfalls fuege in am Ende der Liste an
                }

                for(int i = 0; i < word.length(); i++)
                { // fuer jeden Buchstabe des zu suchenden Wortes
                    if(letter == word.charAt(i))
                    { // pruefe ob der eingegebene uebereinstimmt
                        foundLetter = true;         // falls ja, setzte foundLetter wahr
                        guessed[i] = letter;        // und ersetze in guessed den '_' mit dem Buchstaben
                    }

                    System.out.print(guessed[i]);   // gebe in jedem Fall das Zeichen in guessed aus
                }
                System.out.print("\n\n");           // zwei Leerzeilen als Abstand

                if(!foundLetter)
                { // werte aus ob ein Buchstabe gefunden wurde
                    trials--;                       // falls nicht verringere Anzahl verbleibender Versuche um eins und gebe zugehoerige Textur aus
                    System.out.println(hangmanTexture[trials]);
                }
                if(trials <= 0)
                { // falls keine versuche mehr verbleiben
                    System.out.println("Das gesuchte Wort war: " + word);
                    break;                          // gebe gesuchtes Wort aus und beende in-game kreislauf
                }
                for(char i: guessed)
                { // fuer jedes Zeichen von guessed
                    if(i == '_')
                    { // falls noch '_' enthalten sind
                        continue loop;              // starte neuen Durchlauf
                    }
                }
                break;  // falls keine '_' mehr in guessed vorhanden waren, wurde das Wort vollstaendig erraten. Beende in-game Kreislauf
            }
            
            // nach Ende des in-game
            System.out.print("\n\nwillst du nochmal spielen? (y/n): ");
            choice = in.nextLine();
            
            switch (choice)
            {
                case "y":
                case "Y":   System.out.println("na dann, viel Spaß!");
                            break;          // falls nochmal gespielt werden soll, verlasse den Switch-Case Block. Danach landet man durch die aeußerste Schleife wieder am Programmanfang
                            
                case "n":
                case "N":   System.out.println("auf wiedersehen, bis zum naechsten mal!");
                            break init;     // falls nicht....
                            
                default :   System.out.println("Wir werten das jetzt mal als nein.");
                            break init;     //...oder falls nichts verstaendliches, beende die aeußerste Schleife, wodurch das Programm zu Ende ist.
                
            }
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
    	// local vars
        BufferedReader br;                              // bufferedReader um von der Datei zu lesen
        LinkedList <String> words = new LinkedList<>(); // LinkedList, in die die Worte/Saetze zum raten aufgenommen werden
        int random;                                     // wird spaeter mit passender Zufallszahl gefuellt, um aus den moeglichkeiten Zufaellig zu waehlen
        
        // try-catch, da beim oeffnen einer Datei fehler auftreten koennten.
        try
        {
            // loop variables
            String line;    // temporaerer String, wird nur als zwischenspeicher beim einlesen genutzt
            
                            // oeffne Datei values.conf im ausfuehrungsverzeichnis
            br = new BufferedReader (new InputStreamReader(new FileInputStream("values.conf")));
        
        
        while (true)
        {
            if ((line = br.readLine()) == null)
            { // falls Dateiende erreicht ist, hoere auf einzulesen
                break;
            }
            
            if (line.length() > 3) words.add(line); // falls die eingelesene Zeile mehr als 3 Zeichen hat, fuege sie zur Liste der Moeglichkeiten hinzu
        }
        br.close();         // schließe bufferedReader
        
        } catch (Exception e)
        { // bei einer Exception gehen wir davon aus es gab keine values.conf Datei, und geben als Wort mit dem gespielt wird, einfach File Not Found Error zurueck
            return "FILE NOT FOUND ERROR";
        }
        
        random = randInt(0, words.size() - 1);  // generiere zufaelligen Zahl zwischen 0 und letztem Index der aufgenommenen Moeglichkeiten
        
        return words.get(random);               // gebe das zufaellig ausgewaehlte zurueck ins Spiel
    }
    
    /**
     * Diese Methode erstellt ein String-Array, von der Groeße der Moeglichen Fehlschlaege fuer Hangman (8-Stueck).
     * Fuer jeden Fehlschlag gibt es eine andere Figur, die im jeweiligen Index gespeichert wird.
     * Im Spiel muss dann spaeter nur der entsprechende Index auf die Konsole ausgegeben werden.
     * 
     * @return gibt das in dieser Methode erstellte String-Array zurueck ans Spiel
     */
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
