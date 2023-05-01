import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class buddhahand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class buddhahand extends Actor
{
    int playertype,dx=1,x,y,dy,iniy=-25,cd=10;
    boolean start=true;
    boolean end=false;
    boolean facingright;
    goldball ball;

    buddhahand(player p){
        facingright=p.faceRight();
        playertype=p.playertype;
        if(facingright){
            x=p.getX()+10;
            y=p.getY()+iniy;
            setImage("buddha_hand_right.png");
            setLocation(x, y);
        }
        else {
            x=p.getX()-10;
            y=p.getY()+iniy;
            setImage("buddha_hand_left.png");
            setLocation(x, y);
        }
    }

    public void act()
    {
        if(facingright){
            x+=dx;
        }
        else {
            x-=dx;
        }
        if(cd==0){
            getWorld().addObject(ball=new goldball(this), getX(), getY());
            cd=180;
        }
        if(cd>0)cd--;
        setLocation(x,y);
        if(isAtEdge()){
            end=true;
        }
        if(end)getWorld().removeObject(this);
    }
    public void hp_minus(int minusHP){
        player p=(player)getEnemy();
        MyWorld my=(MyWorld)getWorld();
        if(p!=null && !p.invincible){
            p.hp-=minusHP;
            if(p.playertype==1){
                my.healthbar.subtract(minusHP);
            }
            else{
                my.healthbar2.subtract(minusHP);
            }
        }
    }
    public player getEnemy(){
        MyWorld my=(MyWorld)getWorld();
        List l = my.getObjects(player.class);
        player p=null;
        if(l.size() != 0){
            for(int i=0;i<2;i++){
               p=(player)l.get(i);
                if(p.playertype!=playertype){
                    break;
                }
            }
        }
        return p;
    }
}
