import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class needle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class needle extends Actor
{
    boolean end=false;
    int playertype;
    boolean right;
    needle(int type){
        playertype=type;
        setImage("needle.png");
    }
    private int y;
    boolean flag=true;
    boolean stop=false;
    
    public void act()
    {
        MyWorld my=(MyWorld)getWorld();
        y+=20;
        if(flag){
            setLocation(getX(), y);
        }
        if(isAtEdge()){
            end=true;
            flag=false;
            stop=true;
        }
        if(!stop){
            if(intersects(getEnemyHitbox())){
                if(!getEnemy().invincible){
                    hp_minus(20);
                    if(getCaster().getX()<getEnemy().getX()){
                        getEnemy().damagedLeft=true;
                        getEnemy().flag3=true;
                    }
                    else{
                        getEnemy().damagedRight=true;
                        getEnemy().flag3=true;
                    }
                }
                end=true;
                stop=true;
            }
        }
        if(end)getWorld().removeObject(this);
    }
    public void hp_minus(int minusHP){
        player p=(player)getEnemy();
        MyWorld my=(MyWorld)getWorld();
        if(p!=null && !p.invincible){
            p.hp-=minusHP;
            Greenfoot.playSound("hurt.mp3");
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

    public hitbox getEnemyHitbox(){
        hitbox h=null;
        MyWorld my=(MyWorld)getWorld();
        List l = my.getObjects(hitbox.class);
        if(l.size() != 0){
            for(int i=0;i<l.size();i++){
                h=(hitbox)l.get(i);
                if(h.playertype!=playertype && h.itemtype==0){
                    break;
                }
            }
        }
        return h;
    }


    public player getCaster(){
        MyWorld my=(MyWorld)getWorld();
        List l = my.getObjects(player.class);
        player p=null;
        for(int i=0;i<2;i++){
            p=(player)l.get(i);
            if(p.playertype==playertype){
                break;
            }
        }
        return p;
    }
}
