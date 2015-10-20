import greenfoot.*;  
import java.util.*;

public class RAIO extends JOGO
{
    int dire;
    public RAIO(int i)
    {
        dire = i;
    }
    
    

    public void act() 
    {
        mataPlayer();
        EFEITO();
        SOME();
       
    }    
    
    public List objetoPlayer;
    
    public void mataPlayer()
    {
        objetoPlayer = getIntersectingObjects(PLAYERS.class);
        if(!objetoPlayer.isEmpty()){
            PLAYERS jogadores = (PLAYERS) getOneIntersectingObject(PLAYERS.class);    
            jogadores.die();
        }
    }
    
    private int mudaDeImagem = 0;
    private boolean muda = false;
    
    public void EFEITO()
    {
        mudaDeImagem++;
        if(mudaDeImagem == (1)){
            if(muda){setImage("RAIO00.png");}
            else{setImage("RAIO01.png");}
               
            muda = !muda;
            mudaDeImagem = 0;
        }
        
        if(dire == 1) getImage().mirrorHorizontally();
        if(dire == 2) setRotation(90);
        if(dire == 3) setRotation(270);
    }
    
    private int some = 0;
    public void SOME()
    {
        some++;
        if(some == 17) getWorld().removeObject(this);
    }
}
