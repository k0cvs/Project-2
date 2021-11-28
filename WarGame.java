package project2;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

/*
This is the main file for the game
Here is where you start the game, set your name and number of soldiers you want
Number of enemies will be randomly determined as will possibility of a bonus lead in attack for 
the user.
Game contains a master loop so gameplay will never end.
*/


public class WarGame extends ConsoleProgram
{
	
	
	public static void main(String[] args) 
	{
		//Initialise Hail Mary variable
	    int hailMary = 0;
	    ConsoleProgram a1 = new ConsoleProgram();
	  //Welcomes you to game, tells you the rules, allows you to enter your name
        System.out.println("Welcome to War!");
        System.out.println("Once all of your soldiers die or you have killed all of the enemy soldiers, you will be returned to the choice of how many soldiers you want.");
        System.out.println("You will start with a random available number of tanks, planes and nukes. Your enemy has an unlimited number.");
        System.out.println("There will be a random number of enemies from 1 to 1000 so choose your number of soldiers carefully!");
        System.out.println("There is a small random chance for you to have a bonus lead in attack.");
        System.out.println("Once you fall to less than 10 soldiers you will be given a one time choice to try to recruit a small number more or not.");
        System.out.println();
        String name = a1.readLine("Please enter your name ---> ");
        System.out.println();
        
        //Returns player to this point after all enemy or all soldiers are killed
        while(true)
        {
            hailMary = 0;//Reset Hail Mary variable
            int soldiers = a1.readInt("Ok, " + name + " enter number of soldiers you want to start with ---> ");
            dice dice = new dice();
            dice.rollDice();//Rolls for number of enemies
            int enemy = dice.getRoll();//Sets number of enemies
            System.out.println();
            System.out.println("You will be facing off against " + enemy + " enemies!");
            System.out.println();
            System.out.println();
            System.out.println("Let the carnage begin!");
            System.out.println();
            System.out.println();
            War war = new War(name, soldiers, enemy);//Generates new instance of game
            
            //Determines if bonus lead in attack is granted
            dice dice2 = new dice();//New dice instance
            dice2.rollDice();//Roll for bonus attack chance
            if(dice2.getRoll() < 250)//25% chance to get bonus attack
            {
                System.out.println("Aren't you lucky. Your enemy is stupid and forgot to hide. You get an extra first attack!");
                System.out.println();
                war.attackChoice();
                System.out.println("Attacking enemy with " +  war.attackChoices[war.choice]);
                
                //If user chooses nukes, calls fallout method to see how many friendlies killed
                if(war.choice == 2)
                {
                    war.radiation();
                    
                }
                war.bonuses();//Calls bonus method
                war.setEnemy(war.bonus);//Sets enemy to remaining numer left after bonus attack
                System.out.println();
                System.out.println();
                System.out.println("Your bonus attack killed " + war.bonus + " enemies. There are now  " + war.getEnemy() + " enemies left");
                System.out.println();
                
            }
            
            
            //Game loops to here as long as there are enemies or soldiers left alive
            while(war.getSoldiers() != 0 || war.getEnemy() !=0)
            {
                
                Fire fire = new Fire();//New instance of Fire for defeat or victory messages
                dice dice3 = new dice();//New dice instance
                war.attackChoice();//User makes attack choice
                System.out.println();
                System.out.println("Attacking enemy with " +  war.attackChoices[war.choice]);
                System.out.println();
                System.out.println();
                System.out.println("Your attack killed " + war.killedEnem() + " enemies!");
                war.setEnemy(war.killede);
                System.out.println("There are " + war.getEnemy() + " enemies left!");
            
            
            
            
                //If number of enemies falls to or below 0, prints victory message, how many soldiers you ahve left and invites you to play again. 
                if(war.getEnemy() <= 0)
                {
                    System.out.println();
                    System.out.println();
                    System.out.println(fire.victory());
                    System.out.println("You have killed all your enemies and still have " + war.getSoldiers() + " soldiers left. Lets play again!");
                    System.out.println();
                    System.out.println();
                    break;//Breaks loop and returns to start of game
                }
            
            
            
                System.out.println();
                System.out.println();
                
                //Enemies turn to attack. Generates random enemy attack and random number of soldiers killed
                System.out.println("It is now your enemies turn to attack!");
                System.out.println();
                System.out.println("Your enemy attacks you with " +  war.attackChoices[war.generateRandomNum(10)]);
                System.out.println("Your enemy killed " + war.killedSol() + " of your soldiers!");
                war.setSoldiers(war.killeda);
                 
                //Prints a random death message for your killed soldiers to scream
                if(war.killeda > 0)
                {
                    System.out.println("Your dieing soldiers scream " + war.deathMessage());
                }
                
                //Tells you how many soldiers are left
                System.out.println("You have " + war.getSoldiers() + " soldiers left!");
            
                //When number of available soldiers falls below 10, you are given a choice to recruit more or not.
                if(war.getSoldiers() < 10 && war.getSoldiers() > 0)
                {
                    if(hailMary > 0)//If Hail Mary has already been used, aborts recruitment
                    {
                        System.out.println();
                        System.out.println();
                        continue;
                    
                    }
                
                    System.out.println();
                    System.out.println();
                    //Gives you a choice to recruit more soldiers or not
                    boolean recruiting = a1.readBoolean("You are running low on soldiers. Would you like to try an emergency recruitment drive? You only get this chance ONCE per game. (true/false) --> ");
                    
                    //If you choose to, recruites from 1 to 25 additional soldiers and sets Hail Mary bit to avoid resuse more than once per game
                    if(recruiting)
                    {
                        System.out.println();
                        System.out.println();
                        war.recruit();
                        System.out.println("Congratulaions! You successfully recruited " + war.recruit + " new soldiers. You now have " + war.getSoldiers() + " soldiers.");
                        hailMary = hailMary + 1;
                        System.out.println();
                        continue;
                    
                    
                    }
                    
                    //Wishes you luck if you choose not to recruit
                    else if(!recruiting)
                    {
                        System.out.println();
                        System.out.println();
                        hailMary = hailMary + 1;
                        System.out.println("Ok then. Good luck!");
                    }
                }
            
            
            
            
                //When all of your soldiers are killed, prints defeat message, number of enemies that were left and invites you to play again.
                if(war.getSoldiers() <=0)
                {
                    System.out.println();
                    System.out.println();
                    System.out.println(fire.defeat());
                    System.out.println("All of your soldiers have died! The enemy still had  "  + war.getEnemy() + "  enemies left. Lets play again.");
                    System.out.println();
                    System.out.println();
                    break;//Breaks loop and returns to start of game
                }
            
            
            
                System.out.println();
                System.out.println();
            }
                continue;//returns to top of loop
         
            
        }
	}

}
