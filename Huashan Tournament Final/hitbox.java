import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class hitbox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class hitbox extends Actor
{
    player p;
    goldball gb;
    int playertype=0;
    int itemtype=0; //player:0 goldball:1
    int dx=17;
    int dy=5;
    boolean isplayer=false;
    boolean end;
    boolean flag=false;
    hitbox(player getp){
        setImage("hitbox.png");
        //setImage("hitbox_test.jpg");
        p=getp;
        playertype=p.playertype;
        setLocation(p.getX(), p.getY());
        isplayer=true;
    }
    hitbox(goldball tgb){
        gb=tgb;
        playertype=gb.playertype;
        itemtype=1;
        setImage("goldenball_hitbox.png");
        //setImage("goldenball_hitbox_test.png");
        setLocation(gb.getX(), gb.getY());
    }
    public void act()
    {
        if(!end){
            if(isplayer){
                if(p.faceRight()==true){
                    setLocation(p.getX()-dx, p.getY()-dy);
                }
                else {
                    setLocation(p.getX()+dx, p.getY()-dy);
                }   
            }
            else{
                setLocation(gb.getX(), gb.getY());
            }
            if(itemtype==1 && isAtEdge()){
                end=true;
                flag=true;
            }
            if(itemtype==1 && gold_inter_player()){
                if(!getEnemy().invincible && !(getEnemy().invincibleright && isOnEnemyRight()) && !(getEnemy().invincibleleft && !isOnEnemyRight())){
                    hp_minus(80);
                    if(getEnemy().getX()>getX()){
                        getEnemy().damagedLeft=true;
                        getEnemy().flag3=true;
                    }
                    else{
                        getEnemy().damagedRight=true;
                        getEnemy().flag3=true;
                    }
                }
                end=true;
                flag=true;
            }
        }
        if(end && flag){
            getWorld().removeObject(this);
            gb.end=true;
            flag=false;
        }
    }

    public boolean gold_inter_player(){
        if(itemtype==1 && intersects(getEnemyHitbox())){
            end=true;
            return true;
        }
        return false;
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

    public boolean isInterEnemyHitbox(){
        if(intersects(getEnemyHitbox())){
            return true;
        }
        return false;
    }
}



