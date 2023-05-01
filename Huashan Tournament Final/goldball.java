import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class goldball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class goldball extends Actor
{
    int playertype,dx=3,x,y;
    int itemtype=1;
    boolean start=true;
    hitbox Hitbox;
    boolean facingright,flag=false,end=false;
    goldball(buddhahand b){
        playertype=b.playertype;
        facingright=b.facingright;
        x=b.getX();
        y=b.getY();
        if(facingright){
            setLocation(x+10, y);
        }
        else{
            setLocation(x-10, y);
        }
        setImage("goldball.png");

    }
    public void act()
    {
        if(start) {
            init_hitbox(this);
            start=false;
        }
        if(facingright){
            x+=dx;
        }
        else {
            x-=dx;
        }
        setLocation(x,y);
        if(end){
            getWorld().removeObject(this);
            end=false;
        }
    }
    public void hp_minus(int minusHP){
        player p=(player)getEnemy();
        MyWorld my=(MyWorld)getWorld();
        if(p!=null){
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

    public hitbox getplayerHitbox(){
        hitbox h=null;
        MyWorld my=(MyWorld)getWorld();
        List l = my.getObjects(hitbox.class);
        if(l.size() != 0){
            for(int i=0;i<l.size();i++){
                h=(hitbox)l.get(i);
                if(h.playertype==playertype && h.itemtype==0){
                    break;
                }
            }
        }
        return h;
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
        if(l.size() != 0){
            for(int i=0;i<2;i++){
                p=(player)l.get(i);
                if(p.playertype==playertype){
                    break;
                }
            }
        }
        return p;
    }

    public void init_hitbox(goldball gb){
        getWorld().addObject(Hitbox=new hitbox(this), getX(), getY());
    }

    public boolean isOnEnemyRight(){
        boolean bool;
        if(getX()>getEnemyHitbox().getX()){
            bool=true;
        }
        else{
            bool=false;
        }
        return bool;
    }
}
