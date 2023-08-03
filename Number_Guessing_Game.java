/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package number_guessing_game;

import java.util.Scanner;
import java.util.Random;

class Game {

    public int computerNo;
    public int userNo;
    public int attemptNo = 0;

    Scanner sc = new Scanner(System.in);

    void randomGenerate() {
        Random r = new Random();
        computerNo = r.nextInt(1, 100);
    }

    void userInput() {
        System.out.println("Enter a guess number between 1 to 100");
        userNo = sc.nextInt();
    }

    boolean guessNoIsCorrect() {
        if (userNo == computerNo) {
            System.out.println("OOhhOO!, Your Number is Correct. You Win the Game!");
            return true;
        } else if (userNo > computerNo) {
            System.out.println("Your Guess Number is Greater.");
        } else if (userNo < computerNo) {
            System.out.println("Your Guess Number is Smaller.");
        }
        
        return false;
    }

}

public class Number_Guessing_Game {

    public static void main(String[] args) {
        Game g = new Game();
        System.out.println("Welcome to Guess Number Game");
        System.out.println("You Will Be Asked To Guess A Number, To Win The Game Your Guess Should Be Correct");
        System.out.println("You have Maximum 5 Attempt Limit");
        g.randomGenerate();
        System.out.println(g.computerNo);
        boolean b = false;
        while (!b && g.attemptNo < 5) {
            g.attemptNo++;
            g.userInput();
            b = g.guessNoIsCorrect();
            if (g.attemptNo == 5 && !b) {
                System.out.format("OOhh NO!, Correct Number is %d.\nYou Lose the Game!, Better Luck Next Time!", g.computerNo);
            }
        }
        
    }

}
