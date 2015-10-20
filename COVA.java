import greenfoot.*;  

public class COVA extends INIMIGOS_E_FLORES
{
    private int vida = 0;
    public COVA()
    {
        vida = 0;
    }
     
    public void QUEIMA()
    {
        if(vida == 15) {
            ((MENU)getWorld()).addObject(new MUMIA(Greenfoot.getRandomNumber(2)+1,Greenfoot.getRandomNumber(12)+1), getX(), getY());
            ((MENU)getWorld()).campo[(getX()-30)/63+1][(getY()-30)/63+1] = 0;
            getWorld().removeObject(this);
        }
        
        vida++;
    } 
       
}
