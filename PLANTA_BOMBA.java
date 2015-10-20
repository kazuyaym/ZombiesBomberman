import greenfoot.*;  

public class PLANTA_BOMBA extends INIMIGOS_E_FLORES
{   
    public int alcance;
    
    public PLANTA_BOMBA(int i)
    {
        alcance = -63*i;
    }
    
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
            if(muda){setImage("PLAN_BOM_01.png");}
            else{setImage("PLAN_BOM_02.png");}
               
            muda = !muda;
            mudaDeImagem = 0;
        }
    }
    
    private int vidaPlanta = 0;
     
    public void QUEIMA()
    {
        if(vidaPlanta < 11) {setImage("PLAN_BOM_03.png");}
        if(vidaPlanta == 6){((MENU)getWorld()).addObject(new BOM(), getX(), getY());
            for(int k = 0; k < 2; k++)
                for(int i = alcance; i <= -alcance; i += 63)
                    for(int j = alcance; j <= -alcance; j += 63)
                        if(Greenfoot.getRandomNumber(3) < 2)
                            ((MENU)getWorld()).addObject(new FOGO(), getX()+i, getY()+j);        
        }
        if(vidaPlanta == 12){getWorld().removeObject(this);}
        
        vidaPlanta++;
    }  
}
