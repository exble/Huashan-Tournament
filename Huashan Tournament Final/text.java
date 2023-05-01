import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class text extends Actor
{
    char c;
    char h;

    text(char ch){
        c=ch;
        if(ch==':'){
            setImage("colon.png");
        }
        if(ch=='0'){
            setImage("zero.png");
        }
        if(ch=='1'){
            setImage("one.png");
        }
        if(ch=='2'){
            setImage("two.png");
        }
        if(ch=='3'){
            setImage("three.png");
        }
        if(ch=='l'){
            setImage("victory.png");
        }
        if(ch=='r'){
            setImage("victory.png");
        }
        if(ch=='q'){
            setImage("question.png");
        }
        if(ch=='a'){
            setImage("restart.jpg");
        }
    }
    text(char ch, char hitbox){
        c=ch;
        h=hitbox;
        if(ch=='h'){
            setImage("iconHitbox.png");
        }
    }

    public void act(){
        MyWorld my=(MyWorld)getWorld();
        if(c=='q' && Greenfoot.mouseClicked(this)){
            Greenfoot.playSound("click.mp3");
            my.remove_all_Actor();
            my.setBackground("manual.jpg");
            my.addObject(my.exitHitbox, 1105, 72);
            my.addObject(my.skillHitbox, 1106, 671);
        }
        if(c=='h' && h=='s' && Greenfoot.mouseClicked(this)){
            Greenfoot.playSound("click.mp3");
            my.remove_all_Actor();
            my.setBackground("Skill sheet.jpg");
            my.addObject(my.exitHitbox, 1105, 72);
            my.addObject(my.returnHitbox, 66, 58);
        }
        if(c=='h' && h=='r' && Greenfoot.mouseClicked(this)){
            Greenfoot.playSound("click.mp3");
            my.remove_all_Actor();
            my.setBackground("manual.jpg");
            my.addObject(my.exitHitbox, 1105, 72);
            my.addObject(my.skillHitbox, 1106, 671);
        }
        if(c=='h' && h=='e' && Greenfoot.mouseClicked(this)){
            Greenfoot.playSound("click.mp3");
            my.remove_all_Actor();
            my.menu();
        }
        if(c=='a' && Greenfoot.mouseClicked(this)){
            Greenfoot.playSound("click.mp3");
            my.restart();
        }
    }
}
