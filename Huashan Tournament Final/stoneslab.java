import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class stoneslab here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class stoneslab extends Actor
{
    int playertype;    //1:player1 2:1Hong 3:1Ouyang 4:player2 5:2Hong 6:2Ouyang 7:confirm
    int otherplayer;
    String image=".jpg";
    String chosenimage="_chosen.jpg";
    String otherimage=".jpg";

    stoneslab(int player_type, String _image, String _otherimage){
        playertype=player_type;
        if(playertype==2 || playertype==5){
            otherplayer=playertype+1;
        }
        if(playertype==3 || playertype==6){
            otherplayer=playertype-1;
        }
        image=_image+image;
        chosenimage=_image+chosenimage;
        otherimage=_otherimage+otherimage;
        setImage(image);
    }

    stoneslab(int player_type, String _image){
        playertype=player_type;
        if(playertype==2 || playertype==5){
            otherplayer=1;
        }
        if(playertype==3 || playertype==6){
            otherplayer=-1;
        }
        image=_image+image;
        setImage(image);
    }
    
    public void act()
    {
        MyWorld my=(MyWorld)getWorld();
        if(my.startChoose){
            List l = my.getObjects(stoneslab.class);
            stoneslab s=null;
            for(int i=0; i<7; i++){
                s=(stoneslab)l.get(i);
                if(s.playertype==this.otherplayer){
                    break;
                }
            }
            if(Greenfoot.mouseClicked(this) && playertype!=1 && playertype!=4 && playertype!=7){
                Greenfoot.playSound("click.mp3");
                setImage(chosenimage);
                s.setImage(otherimage);
                if(playertype==2){
                    my.Continue=true;
                    my.chooseHong=true;
                    my.chooseOuyang=false;
                }
                if(playertype==3){
                    my.Continue=true;
                    my.chooseOuyang=true;
                    my.chooseHong=false;
                }
                if(playertype==5){
                    my.Continue2=true;
                    my.chooseHong2=true;
                    my.chooseOuyang2=false;
                }
                if(playertype==6){
                    my.Continue2=true;
                    my.chooseOuyang2=true;
                    my.chooseHong2=false;
                }
            }
            if(Greenfoot.mouseClicked(this) && playertype==7 && my.Continue && my.Continue2){
                Greenfoot.playSound("click.mp3");
                my.Confirm=true;
            }
        }
    }
}
