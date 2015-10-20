import greenfoot.*; 
import java.util.*;

public class ZUMBIE extends INIMIGOS_E_FLORES
{
    public int qualZumbie, vida;
    public String[][] nomeZumbie = new String[10][10];
    
    public ZUMBIE(int vida_, int velo_, int qual)
    {
        qualZumbie = qual;
        vida = vida_;
        velo = velo_;
        
        for(int h = 0; h < nomeZumbie.length; h++){
            for(int g = 0; g < nomeZumbie[0].length; g++){
                nomeZumbie[h][g] = "" + "ZUMBIE0" + h + "-0" + g + ".png";
            }
        }
        
        if(qualZumbie == 0) ehZumbie00 = true;
        if(qualZumbie == 2 || qualZumbie == 6){M = 20;}
        if(qualZumbie == 4) {M = 9;}
        
        setImage(nomeZumbie[qualZumbie][0]);
    }
    
    public boolean podeMorrer = false;
    
    public void act() 
    {
        if(!((MENU)getWorld()).jogoPausado){
            ANDA();
            EFEITO();
            if(qualZumbie == 4) {geloZumbie4();}
            if(qualZumbie == 5) {efeitoZumbie5 ();}
            if(qualZumbie == 6) {efeitoZumbie6();} 
            imunidade();
            encontraPlayer();
            morre();
        }
    }    
    
    private int mudaDeImagem = 0;
    private int ultimoLado = 3;
    private boolean muda = false;
    
    public void EFEITO()
    {
        mudaDeImagem++;
        if(mudaDeImagem == (15)){
            if(muda){
                setImage(nomeZumbie[qualZumbie][0]);
                if(ultimoLado == 4)  {getImage().mirrorHorizontally();}
            }
            else{
                setImage(nomeZumbie[qualZumbie][1]);
                if(ultimoLado == 4)  {getImage().mirrorHorizontally();}
            }
               
            muda = !muda;
            mudaDeImagem = 0;
        }
        
        if(ultimoLado == 3 && DIRECAO == 4){getImage().mirrorHorizontally(); ultimoLado = 4;}
        if(ultimoLado == 4 && DIRECAO == 3){ultimoLado = 3;}
    }
    
    public void geloZumbie4()
    {
        if(((MENU)getWorld()).campo[(getX()-30+(31-(getX()-30)%63))/63+1][(getY()-30+(31-(getY()-30)%63))/63+1] == 0){
            ((MENU)getWorld()).addObject(new GELO(), getX()+(31-(getX()-30)%63), getY()+(31-(getY()-30)%63));
            ((MENU)getWorld()).campo[(getX()-30+(31-(getX()-30)%63))/63+1][(getY()-30+(31-(getY()-30)%63))/63+1] = 8;
        }
    }
    
    private int contadorZ5 = 0, transpZ5 = 245, plus = Greenfoot.getRandomNumber(300);
    private boolean some = true, aparece = false;
    
    private void efeitoZumbie5 ()
    {
        if(contadorZ5 >= 250){
            getImage().setTransparency(transpZ5);
           
            if(some) {transpZ5 -= 5;}
            if(aparece) {transpZ5 += 5;}
            
            if(transpZ5 == 0){
                some = false;
                contadorZ5++;
                if(contadorZ5 == 330 + plus) aparece = true;
            }
            
            if(transpZ5 == 245) {contadorZ5 = 0; some = true; aparece = false;}
            
        }
        else contadorZ5++;
    }
    
     private int contadorZ6 = 0;
    
    private void efeitoZumbie6()
    {
        if(contadorZ6 >= 240){
            ((MENU)getWorld()).addObject(new RAIO(0), getX()-45, getY());
            ((MENU)getWorld()).addObject(new RAIO(1), getX()+45, getY());
            ((MENU)getWorld()).addObject(new RAIO(2), getX(), getY()-45);
            ((MENU)getWorld()).addObject(new RAIO(3), getX(), getY()+45);
            contadorZ6 = 0;
        }
        contadorZ6++;
    }
    
    public void mataInimigo ()
    {
        setImage(nomeZumbie[qualZumbie][2]);
        if(contagemVida == 0 && vida > 0) {vida--; contagemVida = 25;}
        if(vida == 0 && !podeMorrer){podeMorrer = true; contagemVida = 10;}
    }
    
    public void morre()
    {
       if(podeMorrer && contagemVida == 0){
            ((MENU)getWorld()).addObject(new POW(), getX(), getY());
            getWorld().removeObject(this);    
       }
    }
    
    private int contagemVida = 25;
    
    public void imunidade()
    {
        if(contagemVida != 0) contagemVida--;
    }
}
