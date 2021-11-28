package project2;

import java.util.Random;

/*
This file contains majority of the gameplay variables and methods. 
It also contains the string array for attack types
*/


public class Gameplay extends ConsoleProgram
{
    
    //Initialize all variables
    private String name;
    private int soldiers;
    private int enemy;
    int killede = 0;
    int killeda = 0;
    int recruit = 0;
    int bonus = 0;
    int fallout = 0;
    int plane = 2 + generateRandomNum(5);//Genertate 2 to 6 planes
    int tank = 2 + generateRandomNum(5);//Generate 2 to 6 tanks
    int nuke = 2 + generateRandomNum(5);//Generate 2 to 6 nukes
    
    ConsoleProgram a1 = new ConsoleProgram();
    //Constructor for Gameplay
    Gameplay(String name, int soldiers, int enemy)
    {
        this.name = name;
        this.soldiers = soldiers;
        this.enemy = enemy;
    }
    
    //String containing all available attack choices
    String attackChoices [] = {"Tanks", "Planes", "Nukes", "Guns", "Swords", "Daggers", "Tear Gas", "Napalm", "Infantry", "CyberAttack"};
    int attackCount = attackChoices.length;
    
    
    
    //Generates a random number from 0 to upper limit chosen
   public int generateRandomNum(int upperLimit)
    {
        Random rand = new Random(); 
        int randomNumber = rand.nextInt(upperLimit);
        
        return randomNumber;
    } 
    
    
    //Generates random number of killed enemies from 1 to number of enemies left
    public int killedEnem()
    {
        killede = 1 + generateRandomNum(enemy);
        return killede;
    }
    
    
    //Generates random number of killed soldiers from 1 to number of soldiers left
    public int killedSol()
    {
        killeda = 1 + generateRandomNum(soldiers);
        return killeda;
    }
    
    
    //Generates random number of soldiers recruited from 1 to 25
    public int recruit()
    {
        recruit = 1 + generateRandomNum(25);
        soldiers = soldiers + recruit;
        return recruit;
        
    }
    
    
    //Generates number of enemies killed during bonus round.
    public int bonuses()
    {
        bonus = killedEnem();
        return bonus;
    }
    
    
    
    //If nukes are chosen during bonus round, gives a 50/50 chance of having friendly soldiers killed by fallout
    public int radiation()
    {
        fallout = generateRandomNum(100);
        if(fallout < 50)
        {
            killedSol();
            setSoldiers(killeda);
            System.out.println();
            System.out.println();
            System.out.println("Fallout from your nuke killed " + killeda + " of your soldiers.");
            System.out.println("You now have " + getSoldiers() + " soldiers left.");
            System.out.println("You have learned from your mistake and will better shield your soldiers from fallout in the future");
            
        }
        
        return fallout;
        
    }
    
    
    
    
    //Decrements number of planes available each time they are chosen as an attack
    public int planes()
    {
        
        
        plane = plane - 1;
        return plane;
        
    }
    
    
    //Decrements number of tanks available each time they are chosen as an attack
    public int tanks()
    {
        
        tank = tank - 1;
        return tank;
        
    }
    
    
    //Decrements number of nukes available each time they are chosen as an attack
    public int nukes()
    {
        
        
        nuke = nuke - 1;
        return nuke;
        
    }
    
   
    
    
    
    //Getters & Setters below
    
    //Sets enemy value after some are killed
    public void setEnemy(int killedEnemey)
    {
        enemy = enemy - killede;
    }
    
    //Sets soldoiers value after some are killed
    public void setSoldiers(int killedSoldiers)
    {
        soldiers = soldiers - killeda;
    }
    
    //Returns number of enemies left
    public int getEnemy()
    {
        return enemy;
    }
    
    //Returns number of soldiers left
    public int getSoldiers()
    {
        return soldiers;
    }
}