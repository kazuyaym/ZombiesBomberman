import greenfoot.*; 

public class GELO extends JOGO
{
    public int transp = 250, cont = 0;

    public void act() 
    {
        if(!((MENU)getWorld()).jogoPausado){
            getImage().setTransparency(transp);
        
            if(cont == 5) {cont = 0; transp--;}
            if(transp == 40) {delete();}
            cont++;
        }
    }    
    
    public void delete()
    {
          ((MENU)getWorld()).campo[(getX()-30)/63+1][(getY()-30)/63+1] = 0;
          getWorld().removeObject(this);
    }
}
