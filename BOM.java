import greenfoot.*; 
import java.util.*;

public class BOM extends INIMIGOS_E_FLORES
{
    public void act() 
    {
        EFEITO();
    }   
    
    int tempo = 0;
    
    public void EFEITO()
    {
  
        if(tempo < 12) setImage("BOM01.png");
        else if(tempo < 24) setImage("BOM02.png");
        else if(tempo < 36) setImage("BOM03.png");
        else if(tempo == 36){
            ((MENU)getWorld()).addObject(new PEDRA(6), getX(), getY()); 
            getWorld().removeObject(this);
        }
        tempo++;        
    }
}
