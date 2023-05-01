import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private int count = 0;
    private int i=0;
    boolean flag=true;
    public boolean startChoose=false;
    GreenfootImage []back = new GreenfootImage[11];
    GreenfootImage []back2 = new GreenfootImage[11];
    GreenfootSound bgm = new GreenfootSound("bgm.mp3");
    GreenfootSound bgm2 = new GreenfootSound("bgm2.mp3");
    public HealthBar healthbar = new HealthBar("玩家一", "", 1000, 1000);
    public HealthBar healthbar2 = new HealthBar("玩家二", "", 1000, 1000);
    public HealthBar superAttack = new HealthBar("大招", "", 1000, 1000);
    public HealthBar superAttack2 = new HealthBar("大招", "", 1000, 1000);
    public HealthBar longAttack = new HealthBar("遠程", "", 300, 300);
    public HealthBar longAttack2 = new HealthBar("遠程", "", 300, 300);
    public HealthBar teleport;
    public HealthBar teleport2;
    public scroll Scroll1 = new scroll(1);
    public scroll Scroll2 = new scroll(2);
    public scroll Scroll3 = new scroll(3);
    public text colon = new text(':');
    public text zero1 = new text('0');
    public text one1 = new text('1');
    public text two1 = new text('2');
    public text three1 = new text('3');
    public text zero2 = new text('0');
    public text one2 = new text('1');
    public text two2 = new text('2');
    public text three2 = new text('3');
    public text player1end = new text('l');
    public text player2end = new text('r');
    public text question = new text('q');
    public text again = new text('a');
    public text exitHitbox = new text('h','e');
    public text skillHitbox = new text('h','s');
    public text returnHitbox = new text('h','r');
    private needle needleimage;
    private poisonneedle poisonneedleimage;
    private player hongqigong,ouyangfeng,hongqigong2,ouyangfeng2;
    private stoneslab hongimage,ouyangimage,hongimage2,ouyangimage2,p1image,p2image,confirmimage;
    private buddhahand budd;
    public boolean Continue=false;
    public boolean Continue2=false;
    public boolean chooseHong=false;
    public boolean chooseOuyang=false;
    public boolean chooseHong2=false;
    public boolean chooseOuyang2=false;
    public boolean Confirm=false;
    public int round=1;
    public boolean roundend=false;
    public boolean start=false;
    public int player1win=0;
    public int player2win=0;
    
    public MyWorld()
    {    
        super(1200, 800, 1); 
        back[0] = new GreenfootImage("mount.jpg");
        back[1] = new GreenfootImage("10mount.jpg");
        back[2] = new GreenfootImage("20mount.jpg");
        back[3] = new GreenfootImage("30mount.jpg");
        back[4] = new GreenfootImage("40mount.jpg");
        back[5] = new GreenfootImage("50mount.jpg");
        back[6] = new GreenfootImage("60mount.jpg");
        back[7] = new GreenfootImage("70mount.jpg");
        back[8] = new GreenfootImage("80mount.jpg");
        back[9] = new GreenfootImage("90mount.jpg");
        back[10] = new GreenfootImage("fog.jpg");
        back2[0] = new GreenfootImage("bridge.jpg");
        back2[1] = new GreenfootImage("10bridge.jpg");
        back2[2] = new GreenfootImage("20bridge.jpg");
        back2[3] = new GreenfootImage("30bridge.jpg");
        back2[4] = new GreenfootImage("40bridge.jpg");
        back2[5] = new GreenfootImage("50bridge.jpg");
        back2[6] = new GreenfootImage("60bridge.jpg");
        back2[7] = new GreenfootImage("70bridge.jpg");
        back2[8] = new GreenfootImage("80bridge.jpg");
        back2[9] = new GreenfootImage("90bridge.jpg");
        back2[10] = new GreenfootImage("fog.jpg");
        setBackground(back[0]);
    }

    public void act(){
        if(flag){
            bgm.playLoop();
            setBackground(back[0]);
            Greenfoot.delay(100);
            for(int i=1;i<=10;i++){
                setBackground(back[i]);
                Greenfoot.delay(2);
            }
            menu();
            
            startChoose=true;
            flag=false;
        }
        if(Continue && Continue2 && Confirm && round==1){
            bgm.stop();
            start=false;
            remove_all_Actor();
            FogToBoat();
            addObject(Scroll1,600,400);
            Greenfoot.delay(100);
            remove_all_Actor();
            init();
            start=true;
            round++;
        }
        if(round==2 && roundend){
            roundend=false;
            start=false;
            scoretable();
            Greenfoot.delay(100);
            remove_all_Actor();
            bgm2.stop();
            BoatToFog();
            Greenfoot.delay(100);
            FogToBoat();
            addObject(Scroll2,600,400);
            Greenfoot.delay(100);
            remove_all_Actor();
            init();
            start=true;
            round++;
        }
        if(round==3 && roundend){
            roundend=false;
            start=false;
            scoretable();
            Greenfoot.delay(100);
            remove_all_Actor();
            bgm2.stop();
            BoatToFog();
            Greenfoot.delay(100);
            FogToBoat();
            addObject(Scroll3,600,400);
            Greenfoot.delay(100);
            remove_all_Actor();
            init();
            start=true;
            round++;
        }
        if(round==4 && roundend){
            start=false;
            scoretable();
            if(player1win>player2win){
                addObject(player1end,425,75);
                addObject(again,600,400);
            }
            else{
                addObject(player2end,775,100);
                addObject(again,600,400);
            }
        }
    }
    
    public void addHong(){
        addObject(hongqigong=new player(1,1,"hong"), 300, 600);
    }
    
    public void addOuyang(){
        addObject(ouyangfeng=new player(1,2,"ouyang"), 300, 600);
        addObject(teleport=new HealthBar("瞬移", "", 300, 300), 200, 125);
    }
    
    public void addHong2(){
        addObject(hongqigong2=new player(2,1,"2hong"), 900, 600);
    }
    
    public void addOuyang2(){
        addObject(ouyangfeng2=new player(2,2,"2ouyang"), 900, 600);
        addObject(teleport2=new HealthBar("瞬移", "", 300, 300), 1000, 125);
    }
    
    public void addNeedle(int type,int enemy_x){
        int x=enemy_x;
        addObject(needleimage=new needle(type),x,0);
        x+=30;
        addObject(needleimage=new needle(type),x,0);
        x+=30;
        addObject(needleimage=new needle(type),x,0);
        x-=90;
        addObject(needleimage=new needle(type),x,0);
        x-=30;
        addObject(needleimage=new needle(type),x,0);
    }
    
    public void addPoisonNeedle(int type,player p){
        int x=p.getX();
        int y=p.getY();
        addObject(poisonneedleimage= new poisonneedle(type,p.turnRight),x,y);
    }
    
    public void addbuddhahand(player p){
        int x=p.getX();
        int y=p.getY();
        addObject(budd=new buddhahand(p),x,y);
    }

    public void remove_all_Actor(){
        List l = getObjects(Actor.class);
        Actor p=null;
        if(l.size() != 0){
            for(int i=0;i<l.size();i++){
                p=(Actor)l.get(i);
                removeObject(p);
            }
        }
    }

    public void init(){
        if(chooseHong) addHong();
        if(chooseOuyang) addOuyang();
        if(chooseHong2) addHong2();
        if(chooseOuyang2) addOuyang2();
        healthbar = new HealthBar("玩家一", "", 1000, 1000);
        healthbar2 = new HealthBar("玩家二", "", 1000, 1000);
        superAttack = new HealthBar("大招", "", 1000, 1000);
        superAttack2 = new HealthBar("大招", "", 1000, 1000);
        longAttack = new HealthBar("遠程", "", 300, 300);
        longAttack2 = new HealthBar("遠程", "", 300, 300);
        addObject(healthbar,200,50);
        addObject(healthbar2,1000,50);
        addObject(superAttack,200,75);
        addObject(superAttack2,1000,75);
        addObject(longAttack,200,100);
        addObject(longAttack2,1000,100);
        scoretable();
        bgm2.playLoop();
    }
    public void restart(){
        bgm2.stop();
        Continue=false;
        Continue2=false;
        chooseHong=false;
        chooseOuyang=false;
        chooseHong2=false;
        chooseOuyang2=false;
        Confirm=false;
        round=1;
        roundend=false;
        start=false;
        player1win=0;
        player2win=0;
        count = 0;
        i=0;
        flag=true;
        startChoose=false;
        remove_all_Actor();
    }
    public void menu(){
        Continue=false;
        Continue2=false;
        chooseHong=false;
        chooseOuyang=false;
        chooseHong2=false;
        chooseOuyang2=false;
        Confirm=false;
        setBackground(back[10]);
        addObject(p1image=new stoneslab(1,"player1"), 350, 150);
        addObject(hongimage=new stoneslab(2,"hong","ouyang"), 350, 350);
        addObject(ouyangimage=new stoneslab(3,"ouyang","hong"), 350, 550);
        addObject(p2image=new stoneslab(4,"player2"), 850, 150);
        addObject(hongimage2=new stoneslab(5,"hong","ouyang"), 850, 350);
        addObject(ouyangimage2=new stoneslab(6,"ouyang","hong"), 850, 550);
        addObject(confirmimage=new stoneslab(7,"confirm"), 600, 700);
        addObject(question, 1100 ,700);
    }

    public void BoatToFog(){
        for(int i=1;i<=10;i++){
                setBackground(back2[i]);
                Greenfoot.delay(2);
        }
    }
    
    public void FogToBoat(){
        for(int i=9;i>=0;i--){
                setBackground(back2[i]);
                Greenfoot.delay(2);
        }
    }
    public void scoretable(){
        List l = getObjects(text.class);
        Actor p=null;
        if(l.size() != 0){
            for(int i=0;i<l.size();i++){
                p=(text)l.get(i);
                removeObject(p);
            }
        }
        addObject(colon,600,50);
        if(player1win==0){
            addObject(zero1,550,50);
        }
        else if(player1win==1){
            addObject(one1,550,50);
        }
        else if(player1win==2){
            addObject(two1,550,50);
        }
        else if(player1win==3){
            addObject(three1,550,50);
        }

        if(player2win==0){
            addObject(zero2,650,50);
        }
        else if(player2win==1){
            addObject(one2,650,50);
        }
        else if(player2win==2){
            addObject(two2,650,50);
        }
        else if(player2win==3){
            addObject(three2,650,50);
        }
    }
}