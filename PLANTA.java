import greenfoot.*;

public class PLANTA extends JOGO
{
    int planta;
    boolean planta3;
    
    public PLANTA(int i)
    {
        planta = i;
        
        switch(planta){
        case 1: setImage("PLANTA01-01.png"); break;
        case 5: setImage("PLANTA02-01.png"); break;
        case 2: setImage("PLANTA01-01.png"); break;
        case 3:
            if(planta3 = Greenfoot.getRandomNumber(4) == 0){setImage("PLANTA004-01.png");}
            else{setImage("PLANTA01-01.png");}
        break;
        case 4: setImage("PLANTA05-01.png"); break;
        }
    }

    private boolean confirmou = false;
    
    public void act() 
    {
        if(!((MENU)getWorld()).jogoPausado)
            EFEITO();
    } 
    
    private int vidaPlanta = 0;
     
    public void QUEIMA()
    {
        switch(planta){
        case 1:
            setImage("PLANTA01-03.png");
        break;
        case 5:
            setImage("PLANTA02-03.png");
        break;
        case 2:
            setImage("PLANTA01-03.png");
        break;
        case 3:
            if(planta3){setImage("PLANTA004-03.png");}
            else {setImage("PLANTA01-03.png");}
        break;
        case 4:
            setImage("PLANTA05-03.png");
        break;
        }
        
        if(vidaPlanta == 12){
                
            if(((MENU)getWorld()).campo[(getX()-30)/63+1][(getY()-30)/63+1] != 10)
                getWorld().
                addObject(new ITENS(((MENU)getWorld()).campo[(getX()-30)/63+1][(getY()-30)/63+1] - 11)
                , getX(), getY());
   
            ((MENU)getWorld()).campo[(getX()-30)/63+1][(getY()-30)/63+1] = 0;
            getWorld().removeObject(this);
        }
        
        vidaPlanta++;
    }  
    

    
    private int mudaDeImagem = 0;
    private int mudaDeImagemPlus = Greenfoot.getRandomNumber(5);
    private boolean muda = false;
    
    public void EFEITO()
    {
        mudaDeImagem++;
        if(mudaDeImagem == (25+mudaDeImagemPlus)){
            if(muda)
                switch(planta){
                    case 1:
                    setImage("PLANTA01-01.png");
                    break;
                    case 5:
                    setImage("PLANTA02-01.png");
                    break;
                    case 2:
                    setImage("PLANTA01-01.png");
                    break;
                    case 3:
                        if(planta3){setImage("PLANTA004-01.png");}
                        else {setImage("PLANTA01-01.png");}
                    break;
                    case 4:
                    setImage("PLANTA05-01.png");
                    break;
                }
            else
                switch(planta){
                    case 1:
                    setImage("PLANTA01-02.png");
                    break;
                    case 5:
                    setImage("PLANTA02-02.png");
                    break;
                    case 2:
                    setImage("PLANTA01-02.png");
                    break;
                    case 3:
                        if(planta3){setImage("PLANTA004-02.png");}
                        else {setImage("PLANTA01-02.png");}
                    break;
                    case 4:
                    setImage("PLANTA05-02.png");
                    break;
                }
            muda = !muda;
            mudaDeImagem = 0;
        }
    }
}
