import greenfoot.*; 
import java.util.*;

public class INIMIGOS_E_FLORES extends Actor
{
    public boolean ande = true;
    public int velocidadeBixo = 10; //inversamente proporcional!!!!!!!
    public int contagemRegressiva = Greenfoot.getRandomNumber(38);
    public int velo;

    public final int FRENTE = 1, TRAS = 2, ESQUERDA = 3, DIREITA = 4;
    public int DIRECAO = ESQUERDA, direcaoAntes = ESQUERDA;
    public boolean ehZumbie00 = false;
    
    public void ANDA()
    {
        if(ande){
           if(contagemRegressiva > 0){
               switch(DIRECAO){ 
               case FRENTE:
                    if(PodeAndar(0, 9))
                        setLocation(getX(), getY()+9);
                    else{
                        DIRECAO = Greenfoot.getRandomNumber(3)+1;
                        contagemRegressiva = Greenfoot.getRandomNumber(38);
                    }
               break;
               case TRAS:
                    if(PodeAndar(0, -9))
                        setLocation(getX(), getY()-9);    
                    else{
                        DIRECAO = Greenfoot.getRandomNumber(3)+1;
                        contagemRegressiva = Greenfoot.getRandomNumber(38);
                    }
               break;
               case ESQUERDA:
                    if(PodeAndar(-9, 0))
                        setLocation(getX()-9, getY());
                    else{
                        DIRECAO = Greenfoot.getRandomNumber(3)+1;
                        contagemRegressiva = Greenfoot.getRandomNumber(38);
                    }
               break;
               case DIREITA:
                    if(PodeAndar(9, 0))
                        setLocation(getX()+9, getY());
                    else{
                        DIRECAO = Greenfoot.getRandomNumber(3)+1;
                        contagemRegressiva = Greenfoot.getRandomNumber(38);
                    }
               break;
               }
               contagemRegressiva--;
           }
           else{
               //tenta mudar direção; 
               
               if(DIRECAO == ESQUERDA || DIRECAO == DIREITA){
                   if(Greenfoot.getRandomNumber(1) == 0){
                       if(PodeAndar(0, 9)){DIRECAO = FRENTE;}
                       else if(PodeAndar(0, -9)){DIRECAO = TRAS;}
                   }
                   else{
                       if(PodeAndar(0, -9)){DIRECAO = TRAS;}
                       else if(PodeAndar(0, 9)){DIRECAO = FRENTE;}
                   }
               }
               
               if(DIRECAO == FRENTE || DIRECAO == TRAS){
                   if(Greenfoot.getRandomNumber(1) == 0){
                       if(PodeAndar(9, 0)){DIRECAO = DIREITA;}
                       else if(PodeAndar(-9, 0)){DIRECAO = ESQUERDA;}
                   }
                   else{
                       if(PodeAndar(-9, 0)){DIRECAO = ESQUERDA;}
                       else if(PodeAndar(9, 0)){DIRECAO = DIREITA;}
                   }
               }
            
               contagemRegressiva = Greenfoot.getRandomNumber(55);
               
           }
           ande = false;
        }
        
        if(velocidadeBixo == 0) {velocidadeBixo = velo; ande = true;}
        else {velocidadeBixo--;}
    }
    
    public int M = 0;
    
    public boolean PodeAndar(int x1, int y1)
    {
        int 
        k = (getX()-30 + x1)%7,
        w = (getY()-30 + y1)%7,
        x2 = (getX()-30)/63,
        y2 = (getY()-30)/63;
        
        if(k == 4) {k = 0;}
        else if(k == 6) {k = 1;}
        else if(k == 1) {k = 2;}
        else if(k == 3) {k = 3;}
        else if(k == 5) {k = 4;}
        else if(k == 0) {k = 5;}
        else if(k == 2) {k = 6;}
        
        if(w == 4) {w = 0;}
        else if(w == 6) {w = 1;}
        else if(w == 1) {w = 2;}
        else if(w == 3) {w = 3;}
        else if(w == 5) {w = 4;}
        else if(w == 0) {w = 5;}
        else if(w == 2) {w = 6;}
        
        boolean 
        a = getX() + x1 != 13+30,
        b = getX() + x1 != 679+30,
        c, d;
        
        if(ehZumbie00){c = getY() + y1 != 139+30; d = getY() + y1 != 427+30;}
        else {c = getY() + y1 != 13+30; d = getY() + y1 != 553+30;}

        if(a && b && c && d){
            if ((k > 1 && k < 5) || (w > 1 && w < 5)){
                
                x1 = x1/9;
                y1 = y1/9;
                
                if((k <= 1 && x1 == -1) || (k >= 5 && x1 == 1))
                    return ((MENU)getWorld()).campo[x2+x1+1][y2+1] <= M && ((MENU)getWorld()).campo[x2+x1+1][y2+1] != 1;
                if((w <= 1 && y1 == -1) || (w >= 5 && y1 == 1))
                    return ((MENU)getWorld()).campo[x2+1][y2+y1+1] <= M && ((MENU)getWorld()).campo[x2+x1+1][y2+1] != 1;
                    
                if(!(k >= 1 && k <= 5))
                    return ((MENU)getWorld()).campo[x2+x1+1][y2+1] <= M && ((MENU)getWorld()).campo[x2+x1+1][y2+1] != 1;
                if(!(w >= 1 && w <= 5))
                    return ((MENU)getWorld()).campo[x2+1][y2+y1+1] <= M && ((MENU)getWorld()).campo[x2+x1+1][y2+1] != 1;
                    
                return true;
            }
        }
        return false;
    }  
    
    public List objetoPlayer;
        
    public void encontraPlayer()
    {
        objetoPlayer = getObjectsInRange(28,PLAYERS.class);
       if(!objetoPlayer.isEmpty()){
            PLAYERS jogadores = (PLAYERS) getOneIntersectingObject(PLAYERS.class);    
            jogadores.die();
       }
    }
}
