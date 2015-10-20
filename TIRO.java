import greenfoot.*; 
import java.util.*;
 

public class TIRO extends JOGO
{
    List objetoPlayer;
    int contagem = 0, dire, velo = 8;
    boolean del;
    
    public TIRO(int dire_, boolean del_)
    {
        dire = dire_;
        del = del_;
    }
    
    
    public void act() 
    {
        if(!((MENU)getWorld()).jogoPausado){
            EFEITO();
            ANDA_();
        
            objetoPlayer = getObjectsInRange(18, PLAYERS.class);
            if(!objetoPlayer.isEmpty()){
                PLAYERS jogadores = (PLAYERS) getOneIntersectingObject(PLAYERS.class);    
                jogadores.die();
            }
        
            if(del) contagem++;
            deleta();
        }
    }    
    
    public void ANDA_()
    {
        if(dire == 3) setLocation(getX()-velo, getY());
        else if(dire == 4) setLocation(getX()+velo, getY());
        else if(dire == 2) setLocation(getX(), getY()-velo);
        else if(dire == 1) setLocation(getX(), getY()+velo);
    }
    
    private int mudaDeImagem = 0;
    private boolean muda = false;
    
    public void EFEITO()
    {
        mudaDeImagem++;
        if(mudaDeImagem == (1)){
            if(muda){setImage("TIRO00.png");}
            else{setImage("TIRO01.png");}
               
            muda = !muda;
            mudaDeImagem = 0;
        }
        
        if(dire == 4) getImage().mirrorHorizontally();
        if(dire == 2) setRotation(90);
        if(dire == 1) setRotation(270);
    }
    
    public void deleta()
    {
        if(getX() < 35 || getX() > 680 || getY() < 35 || getY() > 592 || contagem > 18){
            ((MENU)getWorld()).addObject(new POW(), getX(), getY());
            getWorld().removeObject(this);
        }
    }
}
