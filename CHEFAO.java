import greenfoot.*;  
import java.util.*;

public class CHEFAO extends INIMIGOS_E_FLORES
{
    public int tipo;
    
    public CHEFAO (int tipo_)
    {
        tipo = tipo_;
        setImage("ZUMBIE_GRANDE0" + tipo + "-00.png");
    }
    
    public final int cima = 0, baixo = 1, esquerda = 2, direita = 3;
    public int direcao = cima, random = Greenfoot.getRandomNumber(400);
    public int contagemAtira = 0, contagemBombas = 0, preparaImuni = 350, contagemImuni = 0, vida = 4, T = 250,
               ATIRA = 60;
    public boolean podeMorrer = false;

    public void act() 
    {
        if(!((MENU)getWorld()).jogoPausado){
            if(tipo == 1) {chefao_01();}
            if(tipo == 0) {chefao_02();}
        
            EFEITO();
            imunidade();
            verificaObjetos();
            if(podeMorrer) mata();
        }
    }    
    
    public void chefao_01()
    {
        anda_01();
        atira_01(); contagemAtira++;
        colocaBombas_01(); contagemBombas++;
    }
    
    public void chefao_02()
    {
        anda_02();
        atira_02(); contagemAtira++;
        colocaBombas_02(); contagemBombas++;
    }

    public void anda_01()
    {
        switch(direcao){
            case cima:
                setLocation(getX(), getY()-1);
                if(getY() < 233) direcao = baixo;
            break;
            case baixo:
                setLocation(getX(), getY()+1);
                if(getY() > 519) direcao = cima;
            break;
        }
        
        random--;
        if(random == 0) {random = Greenfoot.getRandomNumber(350); direcao = Greenfoot.getRandomNumber(2);}
    }
    
        public void anda_02()
    {
        switch(direcao){
            case cima:
                setLocation(getX(), getY()-2);
                if(getY() < 133) direcao = baixo;
            break;
            case baixo:
                setLocation(getX(), getY()+2);
                if(getY() > 529) direcao = cima;
            break;
        }
        
        random--;
        if(random == 0) {random = Greenfoot.getRandomNumber(350); direcao = Greenfoot.getRandomNumber(2);}
    }
    
    public void atira_01()
    {
        if(contagemAtira >= ATIRA){
            if(getY() < 328) {((MENU)getWorld()).addObject(new TIRO(3, false), 565, 187);}
            else if(getY() < 424) {((MENU)getWorld()).addObject(new TIRO(3, false), 565, 313);}
            else {((MENU)getWorld()).addObject(new TIRO(3, false), 565, 439);}
            contagemAtira = 0;
        }
    }
    
    public void atira_02()
    {
        if(contagemAtira >= ATIRA){
            if(getY() < 280) {((MENU)getWorld()).addObject(new TIRO(3, false), 565, 61);}
            else if(getY() < 328) {((MENU)getWorld()).addObject(new TIRO(3, false), 565, 187);}
            else if(getY() < 424) {((MENU)getWorld()).addObject(new TIRO(3, false), 565, 313);}
            else if(getY() < 495) {((MENU)getWorld()).addObject(new TIRO(3, false), 565, 439);}
            else {((MENU)getWorld()).addObject(new TIRO(3, false), 565, 565);}
            contagemAtira = 10;
        }
    }
    
    public void colocaBombas_01()
    {
        if(contagemBombas >= 1000){
            ((MENU)getWorld()).colocaAlvoChefao01();
            contagemBombas = 0;
            preparaImuni = 330;
        }
    }
    
    public void colocaBombas_02()
    {
        if(contagemBombas >= 1350){
            ((MENU)getWorld()).colocaAlvoChefao02();
            contagemBombas = 0;
            preparaImuni = 330;
        }
    }
    
    public void tiraVida()
    {
        if(contagemImuni == 0) {
            if(vida > 0) {vida--; contagemImuni = 60; if(tipo == 1) ATIRA -= 5; else ATIRA -= 3;
                          ((MENU)getWorld()).addObject(new POW(), getX(), getY());}
            else {podeMorrer = true;}
        }
    }
    
    public void mata()
    {
        setRotation(90);
        getImage().setTransparency(T);
        T-=3;
        if(T <= 40){ 
            ((MENU)getWorld()).addObject(new POW(), getX(), getY());
            getWorld().removeObject(this);
        }
    }
    
    public void imunidade()
    {
        if(preparaImuni <= 330 && preparaImuni != 0) {preparaImuni--;}
        else if(preparaImuni == 0){contagemImuni = 60; preparaImuni = 350;}
        
        if(contagemImuni != 0) {contagemImuni--; getImage().setTransparency(180);}
        else getImage().setTransparency(250);
    }
    
    List objetoBomba, objetoPlayer;
    
    public void verificaObjetos()
    {
        objetoBomba  = getObjectsInRange(18, BOMBAS.class);
        if(!objetoBomba.isEmpty()){
            BOMBAS bombinhas = (BOMBAS) getOneIntersectingObject(BOMBAS.class);
            bombinhas.temFogo = false;
            bombinhas.exploda();
        }
        
        objetoPlayer = getObjectsInRange(45, PLAYERS.class);
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
        if(mudaDeImagem == (25)){
            if(muda){setImage("ZUMBIE_GRANDE0" + tipo + "-00.png");}
            else{setImage("ZUMBIE_GRANDE0" + tipo + "-01.png");}
               
            muda = !muda;
            mudaDeImagem = 0;
        }
    }
}
