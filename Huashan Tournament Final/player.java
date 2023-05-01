import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class player extends Actor
{
    int playertype;    //player1 or player2
    boolean isPlayer;
    int playercharacter;    //1:Hong 2:Ouyang
    String playerImagetype;
    String playerGifright="right.gif";
    String playerGifleft="left.gif";
    String playerStandright="standright.png";
    String playerStandleft="standleft.png";
    String playerJumpright="jumpright.png";
    String playerJumpleft="jumpleft.png";
    String needleimage;
    GifImage gifImageRight;
    GifImage gifImageLeft;
    String keyUp="w";
    String keyDown="s";
    String keyRight="d";
    String keyLeft="a";
    String keyNormalattack="c";
    String keyLongattack="b";
    String keySuperattack="v";
    String keyBlock="x";
    boolean block=false;
    int blockdelay=25;
    int blockflag=1;
    int blockCD=50;
    boolean normalattack=false;
    int normalAttackCD=30;
    int thornCD=50;
    boolean goldstart=false;
    int goldexecuting=250;
    int atk_dis=100;
    boolean invincible=false;
    boolean invincibleright=false;
    boolean invincibleleft=false;
    int delay=2;
    int flag=1;
    public int hp=1000;
    int vSpeed=-22;
    boolean startJump=false;
    boolean startDoubleJump=false;
    boolean flag2=true;
    boolean flagStop=false;
    boolean turnRight=true;
    boolean xDown=false;
    boolean cDown=false;
    boolean damagedRight=false;
    boolean damagedLeft=false;
    boolean end=false;
    boolean start=true;
    hitbox Hitbox;
    int basespeed=-10;
    int spd=basespeed;
    int itemtype=0;
    boolean flag3=false;
    boolean teleportright=false;
    boolean teleportleft=false;

    player(int player_type, int player_character, String player_imagetype){
        playertype=player_type;
        playercharacter=player_character;
        playerImagetype=player_imagetype;
        playerGifright=player_imagetype+playerGifright;
        playerGifleft=player_imagetype+playerGifleft;
        gifImageRight = new GifImage(playerGifright);
        gifImageLeft = new GifImage(playerGifleft);
        playerStandright=player_imagetype+playerStandright;
        playerStandleft=player_imagetype+playerStandleft;
        playerJumpright=player_imagetype+playerJumpright;
        playerJumpleft=player_imagetype+playerJumpleft;
        if(playertype==2){
            keyUp="up";
            keyDown="down";
            keyRight="right";
            keyLeft="left";
            keyNormalattack=",";
            keyLongattack="/";
            keySuperattack=".";
            keyBlock="m";
        }
        if(playertype==1){
            setImage(playerStandright);
        }
        if(playertype==2){
            setImage(playerStandleft);
            turnRight=false;
        }
    }

    public void act(){
        MyWorld my=(MyWorld)getWorld();
        if(my.start){
            Move();
            if(playercharacter==1){
                doubleJump();
                buddhahand();
            }
            if(playercharacter==2){
                Jump();
                gold();
                thorn();
                Teleport();
            }
            normalAttack();
            Block();
            longAttack();
            new_damaged();
            if(start){
                hitbox_init(this);
                start=false;
            }
        }
    }

    public void Move(){
        MyWorld my=(MyWorld)getWorld();
        if(Greenfoot.isKeyDown(keyLeft) && !normalattack && !block && my.start){
            int x=getX();
            x-=5;
            setLocation(x, getY());
            setImage(gifImageLeft.getCurrentImage());
            if(!Greenfoot.isKeyDown(keyRight)){
                turnRight=false;
            }
        }
        if(Greenfoot.isKeyDown(keyRight) && !normalattack && !block && my.start){
            int x=getX();
            x+=5;
            setLocation(x, getY());
            setImage(gifImageRight.getCurrentImage());
            if(!Greenfoot.isKeyDown(keyLeft)){
                turnRight=true;
            }
        }
        if(!Greenfoot.isKeyDown(keyLeft) && !Greenfoot.isKeyDown(keyRight) && turnRight && !normalattack && !block && my.start){
            setImage(playerStandright);
        }
        if(!Greenfoot.isKeyDown(keyLeft) && !Greenfoot.isKeyDown(keyRight) && !turnRight && !normalattack && !block && my.start){
            setImage(playerStandleft);
        }
    }

    public void gravity(){
        int acceleration=1;
        setLocation(getX(), getY()+vSpeed);
        vSpeed+=acceleration;
    }

    public void Jump(){
        MyWorld my=(MyWorld)getWorld();
        if(Greenfoot.isKeyDown(keyUp) && !flagStop && !teleportright && !teleportleft && my.start){
            startJump=true;
            flagStop=true;
        }
        if(!Greenfoot.isKeyDown(keyUp) && flagStop){
            flagStop=false;
        }
        if(startJump){
            gravity();
            if(Greenfoot.isKeyDown(keyLeft) || !turnRight){
                setImage(playerJumpleft);
            }
            else if(Greenfoot.isKeyDown(keyRight) || turnRight){
                setImage(playerJumpright);
            }
            if(getY()>=(600-vSpeed)){
                setLocation(getX(), 600);
                startJump=false;
                vSpeed=-22;
                if(Greenfoot.isKeyDown(keyLeft) || !turnRight){
                    setImage(playerStandleft);
                }
                else if(Greenfoot.isKeyDown(keyRight) || turnRight){
                    setImage(playerStandright);
                }

            }
        }
    }

    public void doubleJump(){
        MyWorld my=(MyWorld)getWorld();
        if(Greenfoot.isKeyDown(keyUp) && !flagStop && !startJump && !teleportright && !teleportleft && my.start){
            startJump=true;
            flagStop=true;
        }
        if(!Greenfoot.isKeyDown(keyUp) && flagStop){
            flagStop=false;
        }
        if(Greenfoot.isKeyDown(keyUp) && startJump && !flagStop){
            startDoubleJump=true;
            flagStop=true;
        }
        if(startJump && !startDoubleJump){
            gravity();
            if(Greenfoot.isKeyDown(keyLeft) || !turnRight){
                setImage(playerJumpleft);
            }
            else if(Greenfoot.isKeyDown(keyRight) || turnRight){
                setImage(playerJumpright);
            }
            if(getY()>=(600-vSpeed)){
                setLocation(getX(), 600);
                startJump=false;
                vSpeed=-22;
                if(Greenfoot.isKeyDown(keyLeft) || !turnRight){
                    setImage(playerStandleft);
                }
                else if(Greenfoot.isKeyDown(keyRight) || turnRight){
                    setImage(playerStandright);
                }
            }
        }
        if(startDoubleJump){
            if(Greenfoot.isKeyDown(keyLeft) || !turnRight){
                setImage(playerJumpleft);
            }
            else if(Greenfoot.isKeyDown(keyRight) || turnRight){
                setImage(playerJumpright);
            }
            if(flag2){
                vSpeed=-22;
                flag2=false;
            }
            gravity();
            if(getY()>=(600-vSpeed)){
                setLocation(getX(), 600);
                startJump=false;
                startDoubleJump=false;
                flag2=true;
                vSpeed=-22;
                if(Greenfoot.isKeyDown(keyLeft) || !turnRight){
                    setImage(playerStandleft);
                }
                else if(Greenfoot.isKeyDown(keyRight) || turnRight){
                    setImage(playerStandright);
                }
            }
        }
    }

    public void Teleport(){
        MyWorld my=(MyWorld)getWorld();
        if(((playertype==1 && my.teleport.getValue()==300) || (playertype==2 && my.teleport2.getValue()==300)) && my.start){
            if(Greenfoot.isKeyDown(keyDown) && Greenfoot.isKeyDown(keyRight) && !Greenfoot.isKeyDown(keyLeft)){
                teleportright=true;
            }
            if(Greenfoot.isKeyDown(keyDown) && Greenfoot.isKeyDown(keyLeft) && !Greenfoot.isKeyDown(keyRight)){
                teleportleft=true;
            }
            if(teleportright){
                int x=getX();
                x+=100;
                setLocation(x,getY());
                teleportright=false;
                if(playertype==1){
                    my.teleport.subtract(300);
                }
                if(playertype==2){
                    my.teleport2.subtract(300);
                }
            }
            if(teleportleft){
                int x=getX();
                x-=100;
                setLocation(x,getY());
                teleportleft=false;
                if(playertype==1){
                    my.teleport.subtract(300);
                }
                if(playertype==2){
                    my.teleport2.subtract(300);
                }
            }
        }
        else{
            if(playertype==1){
                my.teleport.add(1);
            }
            if(playertype==2){
                my.teleport2.add(1);
            }
        }
    }

    public void Block(){
        MyWorld my=(MyWorld)getWorld();
        if(Greenfoot.isKeyDown(keyBlock) && !Greenfoot.isKeyDown(keyNormalattack) && blockCD==50 && !goldstart && !normalattack && my.start){
            xDown=true;
        }
        if(xDown && !startJump && !startDoubleJump &&  blockdelay==25 && blockCD==50){
            block=true;
            if(turnRight){
                if(blockflag==1){
                    setImage(playerImagetype+"blockright.png");
                    invincibleright=true;
                    blockdelay=0;
                }
                if(blockflag==2){
                    invincibleright=false;
                    xDown=false;
                    blockflag=0;
                    blockdelay=2;
                    block=false;
                    blockCD=0;
                }
            }
            if(!turnRight){
                if(blockflag==1){
                    setImage(playerImagetype+"blockleft.png");
                    invincibleleft=true;
                    blockdelay=0;
                }
                if(blockflag==2){
                    invincibleleft=false;
                    xDown=false;
                    blockflag=0;
                    blockdelay=2;
                    block=false;
                    blockCD=0;
                }
            }
            blockflag++;
        }
        if(blockCD<50){
            blockCD++;
        }
        if(blockdelay<25){
            blockdelay++;
        }
    }

    public void hitbox_init(player p){
        getWorld().addObject(Hitbox=new hitbox(this), getX(), getY());
    }

    public void normalAttack(){
        MyWorld my=(MyWorld)getWorld();
        if(Greenfoot.isKeyDown(keyNormalattack) && !Greenfoot.isKeyDown(keyBlock) && normalAttackCD==30 && !block && my.start){
            cDown=true;
        }
        if(cDown && !startJump && !startDoubleJump && delay==2 && normalAttackCD==30){
            normalattack=true;
            if(turnRight){
                if(flag==1) {setImage(playerImagetype+"swiperight_1.png"); delay=0;}
                if(flag==2) {setImage(playerImagetype+"swiperight_2.png"); delay=0;}
                if(flag==3) {setImage(playerImagetype+"swiperight_3.png"); delay=0;}
                if(flag==4) {setImage(playerImagetype+"swiperight_4.png"); delay=0;}
                if(flag==5) {setImage(playerImagetype+"swiperight_5.png"); delay=0;}
                if(flag==6) {flag=0; delay=2; cDown=false; normalattack=false; if(detect_atk())hp_minus(80); normalAttackCD=0;}
            }
            if(!turnRight){
                if(flag==1) {setImage(playerImagetype+"swipeleft_1.png"); delay=0;}
                if(flag==2) {setImage(playerImagetype+"swipeleft_2.png"); delay=0;}
                if(flag==3) {setImage(playerImagetype+"swipeleft_3.png"); delay=0;}
                if(flag==4) {setImage(playerImagetype+"swipeleft_4.png"); delay=0;}
                if(flag==5) {setImage(playerImagetype+"swipeleft_5.png"); delay=0;}
                if(flag==6) {flag=0; delay=2; cDown=false; normalattack=false; if(detect_atk())hp_minus(80); normalAttackCD=0;}
            }
            flag++;
        }
        if(normalAttackCD<30){
            normalAttackCD++;
        }
        if (delay<2){
            delay++;
        }
    }

    public void longAttack(){
        MyWorld my=(MyWorld)getWorld();
        if(((playertype==1 && my.longAttack.getValue()==300) || (playertype==2 && my.longAttack2.getValue()==300)) && my.start){
            if(Greenfoot.isKeyDown(keyLongattack)){
                if(playercharacter==1){
                    my.addNeedle(playertype,getEnemy().getX());
                }
                if(playercharacter==2){
                    my.addPoisonNeedle(playertype,this);
                }
                if(playertype==1){
                    my.longAttack.subtract(300);
                }
                if(playertype==2){
                    my.longAttack2.subtract(300);
                }
            }
        }
        else{
            my.longAttack.add(1);
            my.longAttack2.add(1);
        }
    }

    public void buddhahand(){
        MyWorld my=(MyWorld)getWorld();
        if(((playertype==1 && my.superAttack.getValue()==1000) || (playertype==2 && my.superAttack2.getValue()==1000)) && my.start){
            if(Greenfoot.isKeyDown(keySuperattack)){
                my.addbuddhahand(this);
                if(playertype==1){
                    my.superAttack.subtract(1000);
                }
                if(playertype==2){
                    my.superAttack2.subtract(1000);
                }
            }
        }
        else{
            my.superAttack.add(1);
            my.superAttack2.add(1);
        }
    }

    public void gold(){
        MyWorld my=(MyWorld)getWorld();
        if((Greenfoot.isKeyDown(keySuperattack)  && !goldstart && goldexecuting==250) && ((playertype==1 && my.superAttack.getValue()==1000) || (playertype==2 && my.superAttack2.getValue()==1000)) && !damagedRight && !damagedLeft && my.start){
            playerImagetype="gold"+playerImagetype;
            playerGifright="gold"+playerGifright;
            playerGifleft="gold"+playerGifleft;
            gifImageRight = new GifImage(playerGifright);
            gifImageLeft = new GifImage(playerGifleft);
            playerStandright="gold"+playerStandright;
            playerStandleft="gold"+playerStandleft;
            playerJumpright="gold"+playerJumpright;
            playerJumpleft="gold"+playerJumpleft;
            invincible=true;
            goldstart=true;
            if(playertype==1){
                my.superAttack.subtract(1000);
            }
            if(playertype==2){
                my.superAttack2.subtract(1000);
            }
        }
        if(goldexecuting>0 && goldstart){
            goldexecuting--;
        }
        if(goldexecuting==0){
            goldstart=false;
            goldexecuting=250;
            invincible=false;
            playerImagetype=playerImagetype.substring(4);
            playerGifright=playerGifright.substring(4);
            playerGifleft=playerGifleft.substring(4);
            gifImageRight = new GifImage(playerGifright);
            gifImageLeft = new GifImage(playerGifleft);
            playerStandright=playerStandright.substring(4);
            playerStandleft=playerStandleft.substring(4);
            playerJumpright=playerJumpright.substring(4);
            playerJumpleft=playerJumpleft.substring(4);
        }
        if(my.superAttack.getValue()<1000){
            my.superAttack.add(1);
        }
        if(my.superAttack2.getValue()<1000){
            my.superAttack2.add(1);
        }
    }

    public void hp_minus(int minusHP){
        MyWorld my=(MyWorld)getWorld();
        if(my.start){
            hitbox Enemyhitbox=getEnemyHitbox();
            hitbox Playerhitbox=getHitbox();
            player p=(player)getEnemy();
                if(p!=null && !p.invincible){
                    p.hp-=minusHP;
                    Greenfoot.playSound("hurt.mp3");
                    if(p.playertype==1){
                        my.healthbar.subtract(minusHP);
                    }
                    else{
                        my.healthbar2.subtract(minusHP);
                    }
                    if(Playerhitbox.getX()<Enemyhitbox.getX()){
                        p.damagedLeft=true;
                        p.flag3=true;
                    }
                    else{
                        p.damagedRight=true;
                        p.flag3=true;
                    }
                }
        }
    }

    public player getEnemy(int range){
        MyWorld my=(MyWorld)getWorld();
        List l = getObjectsInRange(range,player.class);
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

    public boolean faceRight(){
        return turnRight;
    }
    public void new_damaged(){
        if(!invincible){
            if(damagedRight){
                if(flag3){
                    if(playerStandright.charAt(0)!='r' && playerStandleft.charAt(0)!='r'){
                        playerStandright="red"+playerStandright;
                        playerStandleft="red"+playerStandleft;
                    }
                    flag3=false;
                }
                setImage(playerStandright);
                flagStop=true;
                turnRight=true;
                int dx=6;
                setLocation(getX()-dx,getY()+spd);
                spd+=1;
    
                if(getY()>600){
                    if(playerStandright.charAt(0)=='r' && playerStandleft.charAt(0)=='r'){
                        playerStandright=playerStandright.substring(3);
                        playerStandleft=playerStandleft.substring(3);
                    }
                    flagStop=false;
                    setLocation(getX(), 600);
                    damagedRight=false;
                    spd=basespeed;
                }
            }
            if(damagedLeft){
                if(flag3){
                    if(playerStandright.charAt(0)!='r' && playerStandleft.charAt(0)!='r'){
                        playerStandright="red"+playerStandright;
                        playerStandleft="red"+playerStandleft;
                    }
                    flag3=false;
                }
                setImage(playerStandleft);
                flagStop=true;
                turnRight=false;
                int dx=6;
    
                setLocation(getX()+dx,getY()+spd);
                spd+=1;
                
                if(getY()>600){
                    if(playerStandright.charAt(0)=='r' && playerStandleft.charAt(0)=='r'){
                        playerStandright=playerStandright.substring(3);
                        playerStandleft=playerStandleft.substring(3);
                        
                    }
                    flagStop=false;
                    setLocation(getX(), 600);
                    damagedLeft=false;
                    spd=basespeed;
                }
            }
        }
        if(hp<=0){
            MyWorld w = (MyWorld)getWorld();
            w.roundend=true;
            if(playertype==1 && getEnemy().hp!=0){
                w.player2win++;
            }
            else if(playertype==2 && getEnemy().hp!=0){
                w.player1win++;
            }
            else if(playertype==1){
                int n=(int)(Math.random()+0.5);
                if(n==1){
                    w.player1win++;
                }
                else{
                    w.player2win++;
                }
            }
            if(w.player1win==2 && w.player2win==0){
                w.round=4;
            }
            if(w.player1win==0 && w.player2win==2){
                w.round=4;
            }
        }
    }
    public boolean detect_atk(){
        hitbox Enemyhitbox=getEnemyHitbox();
        hitbox Playerhitbox=getHitbox();
        if(isEnemyAtRight() && Enemyhitbox.getX()-Playerhitbox.getX()<atk_dis && faceRight() && !getEnemy().invincibleleft){
            return true;
        }
        else if(!isEnemyAtRight() && Playerhitbox.getX()-Enemyhitbox.getX()<atk_dis && !faceRight() && !getEnemy().invincibleright){
            return true;
        }
        return false;
    }
    
    public boolean isEnemyAtRight(){
        hitbox Enemyhitbox=getEnemyHitbox();
        hitbox Playerhitbox=getHitbox();
        if(Enemyhitbox.getX()-Playerhitbox.getX()>0)return true;
        return false;
    }

    public hitbox getHitbox(){
            hitbox h=null;
            MyWorld my=(MyWorld)getWorld();
            List l = my.getObjects(hitbox.class);
            if(l.size() != 0){
                for(int i=0;i<l.size();i++){
                    h=(hitbox)l.get(i);
                    if(h.playertype==playertype && h.itemtype==itemtype){
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
                    if(h.playertype!=playertype && h.itemtype==itemtype){
                        break;
                    }
                }
            }
        return h;
    }

    public void thorn(){
        if(invincible && getHitbox().isInterEnemyHitbox() && !getEnemy().invincible && thornCD==50){
            hp_minus(80);
            thornCD=0;
        }
        if(thornCD<50){
            thornCD++;
        }
    }
}
