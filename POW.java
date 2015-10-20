import greenfoot.*; 

public class POW extends JOGO
{
    int contador = 0;
    public void act() 
    {
        contador++;
        if(contador > 12) setImage("POW01.png");
        if(contador == 20) getWorld().removeObject(this);
    }    
}
