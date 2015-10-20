import greenfoot.*;  
import java.util.*;

public class MORTE extends INIMIGOS_E_FLORES
{
    public int vida, contagemTiro = 0, plus = Greenfoot.getRandomNumber(190), imuni = 25;
    
    public MORTE (int vida_, int velo_)
    {
        vida = vida_;
        velo = velo_;
    }
    
    public void act() 
    {
        if(!((MENU)getWorld()).jogoPausado){
            EFEITO();
            ANDA();
            encontraPlayer();
            contagemTiro++;
            if(imuni != 0) imuni--;
            if(contagemTiro >= 120 + plus) ATIRA();
        }
    }    

    
    public void ATIRA()
    {
        contagemTiro = 0;
        ((MENU)getWorld()).addObject(new TIRO(DIRECAO, true), getX(), getY());
    }
    
    private int mudaDeImagem = 0;
    private int ultimoLado = 3;
    private boolean muda = false;
    
    public void EFEITO()
    {
        mudaDeImagem++;
        if(mudaDeImagem == (15)){
            if(muda){
                setImage("MORTE00.png");
                if(ultimoLado == 4)  {getImage().mirrorHorizontally();}
            }
            else{
                setImage("MORTE01.png");
                if(ultimoLado == 4)  {getImage().mirrorHorizontally();}
            }
               
            muda = !muda;
            mudaDeImagem = 0;
        }
        
        if(ultimoLado == 3 && DIRECAO == 4){getImage().mirrorHorizontally(); ultimoLado = 4;}
        if(ultimoLado == 4 && DIRECAO == 3){ultimoLado = 3;}
    }
    


    public void mataInimigo ()
    {
        if(imuni == 0) {vida--; imuni = 25;}
        if(vida == 0){
            ((MENU)getWorld()).addObject(new POW(), getX(), getY());
            getWorld().removeObject(this);
        }
    }
}
