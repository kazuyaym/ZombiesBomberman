import greenfoot.*; 
import java.util.*; 

public class MUMIA extends INIMIGOS_E_FLORES
{
    public int vida, imuni = 25;
    
    public MUMIA(int vida_, int velo_)
    {
        vida = vida_;
        velo = velo_;
    }
    
    public void act() 
    {
        if(!((MENU)getWorld()).jogoPausado){
          ANDA();
          EFEITO();
          bomba();
          if(imuni != 0) imuni--;
          encontraPlayer();
        }
    }  
    
    int contadorJB = 0, plus = Greenfoot.getRandomNumber(100);
    public void bomba()
    {
        if(contadorJB == 250+plus){
            contadorJB = 0 ;
            if(((MENU)getWorld()).campo[(getX()-30+(31-(getX()-30)%63))/63+1][(getY()-30+(31-(getY()-30)%63))/63+1] == 0){
                if(((MENU)getWorld()).numBombas[2] < 5){ 
                     ((MENU)getWorld()).addObject(new BOMBAS(1, 0, 3), getX()+(31-(getX()-30)%63), getY()+(31-(getY()-30)%63));
                }
            }
        }
        else contadorJB++;
    }
    
    public void EFEITO()
    {
        if(direcaoAntes != DIRECAO){
            direcaoAntes = DIRECAO;
            
            switch(DIRECAO){
            case FRENTE:
                setImage("MUMIA01.png");
            break;
            case TRAS:
                setImage("MUMIA02.png");
            break;
            case ESQUERDA:
                setImage("MUMIA03.png");
            break;
            case DIREITA:
                setImage("MUMIA04.png");
            break;
            }
        }
    }

    public void mataInimigo ()
    {
        setImage("MUMIA05.png");
        if(imuni == 0){vida--; imuni = 25;}
        if(vida == 0){
            ((MENU)getWorld()).addObject(new POW(), getX(), getY());
            getWorld().removeObject(this);
        }   
    }
}
