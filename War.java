package project2;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;




/*
This file contains the code to read from the deathMessages.txt file
and the code to display and choose the attack type
It also inherits from Gameplay class
*/

public class War extends Gameplay
{
    //Initialize variable
    int choice = 0;
    
    //Constructor for War
    War(String name, int soldiers, int enemy)
    {
        super(name, soldiers, enemy);
    }            
    

    //Reads from deathMessage.txt, populates a string array with read data. Also randomly picks a death message for display.
    public String deathMessage()
    {
        String[] deathMessages = new String[10];
        
        //Read from deathMessage.txt
        try
        {
        	//Path to deathMessages.txt will need to be changed to reflect its location on your computer
            FileReader fileReader = new FileReader("D:\\Eclipse\\Projects\\project-2\\src\\project2\\deathMessage.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            deathMessages[0] = bufferedReader.readLine();
            deathMessages[1] = bufferedReader.readLine();
            deathMessages[2] = bufferedReader.readLine();
            deathMessages[3] = bufferedReader.readLine();
            deathMessages[4] = bufferedReader.readLine();
            deathMessages[5] = bufferedReader.readLine();
            deathMessages[6] = bufferedReader.readLine();
            deathMessages[7] = bufferedReader.readLine();
            deathMessages[8] = bufferedReader.readLine();
            deathMessages[9] = bufferedReader.readLine();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        int randomDeath = generateRandomNum(10);//Generates random death message
        return deathMessages[randomDeath];
    }
    
    
    //Allows for user to make their choice of available attacks
    public int attackChoice()
    {
        //Prints the list of attacks
        while(true)
        {
        System.out.println("Here is Your inventory of attacks: ");
        for(int i = 0; i < attackCount; i++)
        {
            System.out.println("\tAttack " + i + ": "  + attackChoices[i]);
            
        }
        System.out.println();
        choice = readInt("Please Choose attack number: ");
        
        //When planes chosen, will call method to decrement avail number of planes
        if(choice == 1)
        {
            planes();
            if(plane < 1)//If no more planes are left, tells you so and prompts for another choice
                {
                    System.out.println();
                    System.out.println("You are out of planes. Make another choice.");
                    System.out.println();
                    attackChoice();
                }
            if(plane > 0)//Tells you how many panes are left
            {
                System.out.println();
                System.out.println("You have " + (plane - 1) + " planes left");
                System.out.println();
            }
            
        }
        
        
        //When tanks are chosen, calls method to decrement available number of tanks
        if(choice == 0)
        {
            
            tanks();
            if(tank < 1)//If no more tanks are left, tells you so and prompts for another choice
                {
                    System.out.println();
                    System.out.println("You are out of tanks. Make another choice.");
                    System.out.println();
                    attackChoice();
                }
            if(tank > 0)//Tells you how many tanks are left
            {
                System.out.println();
                System.out.println("You have " + (tank - 1) + " tanks left");
                System.out.println();
            }
        }
        
        //When nukes are chosen, calls method to decrement available number of nukes
        if(choice == 2)
        {
            nukes();
            if(nuke < 1)//If no more nukes are available, tells you so and prompts for another choice
                {
                    System.out.println();
                    System.out.println("You are out of nukes. Make another choice.");
                    System.out.println();
                    attackChoice();
                }
                
            if(nuke > 0)//Tells you how many nukes are left
            {
                System.out.println();
                System.out.println("You have " + (nuke - 1) + " nukes left");
                System.out.println();
            }
        }
        
        //If an invalid attack choice is made, admonishes user and propmts to try again
        if(choice > 9)
        {
             System.out.println();
             System.out.println("No dummy! You can only choose 0-9. Try again!");
             System.out.println();
             continue;
        }
          return choice;
        }
        
    }
    
    
    
    
    
    
    
    
   
}