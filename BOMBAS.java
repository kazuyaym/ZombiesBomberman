import greenfoot.*;
import java.util.*;

public class BOMBAS extends JOGO
{
    public int num, tam, contador = 0, player;
    public boolean confirmou = false, temFogo = true;
    
    
    public BOMBAS(int num_, int tam_, int player_) 
    {
        num = num_;
        tam = tam_;
        player = player_;
        
        switch(num){
            case 1:
                setImage("BOMBA01-01.png");
            break;
            case 2:
                setImage("BOMBA02-01.png");
            break;
            case 3:
                setImage("BOMBA03-01.png");
            break;
        }
        
        if(tam_ == 0) setImage("BOMBA00.png");
        
        ((MENU)getWorld()).numBombas[player-1]++;
        
    }

    public List objetoPlayer, pessoaPerto;
     
    public void act() 
    {
        if(tam != 0){
            if(!((MENU)getWorld()).jogoPausado){
                if(!confirmou) {confirmaPresenca();} 
                EFEITO();
                preparaParaExplodir();
            }
        }
        else{
            if(((MENU)getWorld()).nivel > 1) 
                getImage().setTransparency(25);
            
            pessoaPerto = getObjectsInRange(103, PLAYERS.class);
            if(!pessoaPerto.isEmpty()) getImage().setTransparency(250);
            
            objetoPlayer = getObjectsInRange(27, PLAYERS.class);
            if(!objetoPlayer.isEmpty()){
                PLAYERS jogadores = (PLAYERS) getOneIntersectingObject(PLAYERS.class);    
                jogadores.die();
                exploda();
            }
        }
        
        if(Greenfoot.isKeyDown("m") || Greenfoot.isKeyDown("backspace")) {/*para nao ficar gravado!*/}
    }    
    
    public void preparaParaExplodir()
    {
        if(Greenfoot.isKeyDown("backspace") && player == 1 && num == 3) exploda();
        if(Greenfoot.isKeyDown("m") && player == 2 && num == 3) exploda();
        
        if(num != 3){
            if(contador == 140) exploda();
            contador++;
        }
    }
    
    public void exploda()
    {
        int i = getX()-30, j = getY()-30;
       ((MENU)getWorld()).campo[i/63+1][j/63+1] = 0;
       
       
       if(temFogo){
           ((MENU)getWorld()).addObject(new FOGO(), getX(), getY());
       
           //criar FOGO no centro e nas 4 direções
       
            for(int tF = 1;
            tF <= tam && !bordas(1,0,tF-1) && !pedras(1,0,tF-1) && !plantas(1,0,tF-1);
            tF ++){
                ((MENU)getWorld()).addObject(new FOGO(), getX()+(63*tF), getY());     
            }
        
            for(int tF = 1;
            tF <= tam && !bordas(-1,0,tF-1) && !pedras(-1,0,tF-1) && !plantas(-1,0,tF-1);
            tF ++){
                ((MENU)getWorld()).addObject(new FOGO(), getX()-(63*tF), getY());
            }
            
            for(int tF = 1;
            tF <= tam && !bordas(0,-1,tF-1) && !pedras(0,-1,tF-1) && !plantas(0,-1,tF-1);
            tF ++){
                ((MENU)getWorld()).addObject(new FOGO(), getX(), getY()-(63*tF));    
            }
                
            for(int tF = 1;
            tF <= tam && !bordas(0,1,tF-1) && !pedras(0,1,tF-1) && !plantas(0,1,tF-1);
            tF ++){
                ((MENU)getWorld()).addObject(new FOGO(), getX(), getY()+(63*tF));          
            }
        }
       
       ((MENU)getWorld()).numBombas[player-1]--;
       getWorld().removeObject(this);
    }
    
    public boolean bordas(int x, int y, int tF)
    {
        if(x != 0){
            if(x == 1)
                return getX()+(63*tF) == 661+30;
            else
                return getX()-(63*tF) == 31+30;
        }
        else{
            if(y == 1)
                return getY()+(63*tF) == 535+30;
            else
                return getY()-(63*tF) == 31+30;
        }
    }
    
    public boolean pedras(int x, int y, int tF)
    {
        if(x != 0){
            if(x == 1)
                return ((MENU)getWorld()).campo[(getX()-30+(63*tF))/63+2][(getY()-30)/63+1] >= 20;
            else
                return ((MENU)getWorld()).campo[(getX()-30-(63*tF))/63][(getY()-30)/63+1] >= 20;
        }
        else{
            if(y == 1)
                return ((MENU)getWorld()).campo[(getX()-30)/63+1][(getY()-30+(63*tF))/63+2] >= 20;
            else
                return((MENU)getWorld()).campo[(getX()-30)/63+1][(getY()-30-(63*tF))/63] >= 20;
        }
    }
    
    private boolean tevePlantaN = false;
    private boolean tevePlantaS = false;
    private boolean tevePlantaD = false;
    private boolean tevePlantaE = false;
    
    public boolean plantas(int x, int y, int tF)
    {
        if(num != 2){
            if(x != 0){
                if(x == 1){
                    if(tevePlantaN) return true;
                    tevePlantaN = (((MENU)getWorld()).campo[(getX()-30+(63*tF))/63+2][(getY()-30)/63+1] >= 10 &&
                                   ((MENU)getWorld()).campo[(getX()-30+(63*tF))/63+2][(getY()-30)/63+1] <= 19);
                }
                else{
                    if(tevePlantaS) return true;
                    tevePlantaS = (((MENU)getWorld()).campo[(getX()-30-(63*tF))/63][(getY()-30)/63+1] >= 10 &&
                                   ((MENU)getWorld()).campo[(getX()-30-(63*tF))/63][(getY()-30)/63+1] <= 19);
                }
            }
            else{
                if(y == 1){
                    if(tevePlantaD) return true;
                    tevePlantaD = (((MENU)getWorld()).campo[(getX()-30)/63+1][(getY()-30+(63*tF))/63+2] >= 10 &&
                                   ((MENU)getWorld()).campo[(getX()-30)/63+1][(getY()-30+(63*tF))/63+2] <= 19);
                }
                else{
                    if(tevePlantaE) return true;
                    tevePlantaE = (((MENU)getWorld()).campo[(getX()-30)/63+1][(getY()-30-(63*tF))/63] >= 10 &&
                                   ((MENU)getWorld()).campo[(getX()-30)/63+1][(getY()-30-(63*tF))/63] <= 19);
                }
            }
            return false;
        }
        return false; //caso num == 2, bomba espinho, como se nao tivesse plantas atrapalhando!
    }
    
    
    
    public void confirmaPresenca()
    {
       int i = getX()-30, j = getY()-30;
       ((MENU)getWorld()).campo[i/63+1][j/63+1] = 1;
       confirmou = !confirmou;
    }
    
    private int mudaDeImagem = 0;
    private int mudaDeImagemPlus = Greenfoot.getRandomNumber(10);
    private boolean muda = false;
    
    public void EFEITO()
    {
        mudaDeImagem++;
        if(mudaDeImagem == (10+mudaDeImagemPlus)){
            if(muda){
                switch(num){
                    case 1:
                        setImage("BOMBA01-01.png");
                    break;
                    case 2:
                        setImage("BOMBA02-01.png");
                    break;
                    case 3:
                        setImage("BOMBA03-01.png");
                    break;
                }
            }
            else{
                switch(num){
                    case 1:
                        setImage("BOMBA01-02.png");
                    break;
                    case 2:
                        setImage("BOMBA02-02.png");
                    break;
                    case 3:
                        setImage("BOMBA03-02.png");
                    break;
                }
            }
            muda = !muda;
            mudaDeImagem = 0;
        }
    } 
}
