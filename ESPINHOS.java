import greenfoot.*;  
import java.util.*;
 

public class ESPINHOS extends JOGO
{
    List objetoPlayer;
    boolean confirmou = false;
    
    public void act() 
    {
        EFEITO();
        
        objetoPlayer = getObjectsInRange(27, PLAYERS.class);
        if(!objetoPlayer.isEmpty()){
            PLAYERS jogadores = (PLAYERS) getOneIntersectingObject(PLAYERS.class);    
            jogadores.die();
        }
        
        if(!confirmou) {((MENU)getWorld()).campo[(getX()-30)/63+1][(getY()-30)/63+1] = 0; confirmou = true;}
    }    
    
    private int mudaDeImagem = 0;
    private int mudaDeImagemPlus = Greenfoot.getRandomNumber(5);
    private boolean muda = false;
    
    public void EFEITO()
    {
        mudaDeImagem++;
        if(mudaDeImagem == (25+mudaDeImagemPlus)){
            if(muda){setImage("ESPI01.png");}
            else{setImage("ESPI02.png");}
               
            muda = !muda;
            mudaDeImagem = 0;
        }
    }
}
