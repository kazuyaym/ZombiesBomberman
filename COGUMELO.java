import greenfoot.*;  

public class COGUMELO extends INIMIGOS_E_FLORES
{
    public void act() 
    {
        EFEITO();
    }  
    
    private int mudaDeImagem = 0;
    private int mudaDeImagemPlus = Greenfoot.getRandomNumber(5);
    private boolean muda = false;
    
    public void EFEITO()
    {
        mudaDeImagem++;
        if(mudaDeImagem == (25+mudaDeImagemPlus)){
            if(muda){setImage("PLANTA04-01.png");}
            else{setImage("PLANTA04-02.png");}
               
            muda = !muda;
            mudaDeImagem = 0;
        }
    }
    
    private int vidaPlanta = 0;
     
    public void QUEIMA()
    {
        setImage("PLANTA04-03.png");
        
        if(vidaPlanta == 12){
            ((MENU)getWorld()).campo[(getX()-30)/63+1][(getY()-30)/63+1] = 0;
            getWorld().removeObject(this);
        }
        
        vidaPlanta++;
    }  
}

