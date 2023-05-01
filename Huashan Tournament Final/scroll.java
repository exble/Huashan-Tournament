import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class scroll here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class scroll extends Actor
{     
    public scroll(int round){
        if(round==1){
            setImage("first_round.png");
        }
        if(round==2){
            setImage("second_round.png");
        }
        if(round==3){
            setImage("third_round.png");
        }
    }
}
