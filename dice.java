package project2;

import java.util.Random;

public class dice
{
    private int roll;
    
    
    //Constructor for dice
    dice()
    {
        roll = 0;
    }
    
    
    //Returns dice roll
    public int getRoll()
    {
        return roll;
    }
    
    //Rolls the dice
    public void rollDice()
    {
        
        this.roll = generateRandomNum();
        
    }
    
    //generate random values from 1-1000
    public int generateRandomNum()
    {
        
        Random rand = new Random(); 
        int randomNumber = 1 + rand.nextInt(999);
        return randomNumber;
    }
}