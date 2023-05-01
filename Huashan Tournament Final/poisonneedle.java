import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class poisonneedle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class poisonneedle extends Actor
{
    /**
     * Act - do whatever the poisonneedle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int x;
    boolean flag=true;
    boolean right;
    boolean stop=false;
    boolean end=false;
    int playertype;

    
    poisonneedle(int type,boolean isRight){
        right=isRight;
        playertype=type;
    }
    public void act()
    {
        if(!stop){
            player p=getCaster();
            right=p.turnRight;
            x=p.getX();
            if(!right){
                setRotation(180);
            }
            stop=true;
        }
        if(stop && right){
            x+=20;
            if(flag){
                setLocation(x, getY());
            }
            if(x>=1200)
            {
                end=true;
                flag=false;
            }
        }
        if(stop && !right){
            x-=20;
            if(flag){
                setLocation(x, getY());
            }
            if(x<=0)
            {
                end=true;
                flag=false;
            }
        }
        if(intersects(getEnemyHitbox()) && !end){
            if(!getEnemy().invincible && !(getEnemy().invincibleright && isOnEnemyRight()) && !(getEnemy().invincibleleft && !isOnEnemyRight())){
                hp_minus(100);
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
        }

        if(end) getWorld().removeObject(this);
    }
    public void hp_minus(int minusHP){
        player p=(player)getEnemy();
        MyWorld my=(MyWorld)getWorld();
        if(!p.invincible){
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

