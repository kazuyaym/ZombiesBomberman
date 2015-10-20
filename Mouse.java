import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Mouse extends Actor
{
    public Actor botao;
    
    public void act() 
    {
        if(Greenfoot.mouseMoved(null)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            setLocation(mouse.getX(), mouse.getY());
        }        
        botao = getOneObjectAtOffset(0, 0, BOTAO_MENU.class);
        if(botao != null){
            BOTAO_MENU pressionar = (BOTAO_MENU) getOneIntersectingObject(BOTAO_MENU.class);
            pressionar.mouseEncima = true;
        }
    }
}   
