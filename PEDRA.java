import greenfoot.*;

public class PEDRA extends JOGO
{ 
    public int numPedra;
    public PEDRA(int i)
    {
        numPedra = i;
        int pedra4 = Greenfoot.getRandomNumber(4);
        
        switch(numPedra){
            case 1:
                setImage("PEDRA01-01.png");
            break;
            case 2:
                setImage("PEDRA03-01.png");
            break;
            case 3:
                if(pedra4 == 0)
                    setImage("PEDRA04-01.png");
                else if(pedra4 == 1)
                    setImage("PEDRA04-02.png");
                else if(pedra4 == 2)
                    setImage("PEDRA04-03.png");
                else if(pedra4 == 3)
                    setImage("PEDRA04-04.png");
                else if(pedra4 == 4)
                    setImage("PEDRA04-05.png");
            break;
            case 4:
                setImage("PEDRA05-01.png");
            break;
            case 6:
                setImage("PLAN_BOM_04.png");
            break;
        }
    }

    private boolean confirmou = false;
    
    public void act() 
    {
        if(!confirmou) {confirmaPresenca();}
        if(numPedra != 4){EFEITO();}
    }
    
    public void confirmaPresenca()
    {
       int i = getX()-30, j = getY()-30;
       if(numPedra != 6)((MENU)getWorld()).campo[(int)(i/63+1)][(int)(j/63+1)] = 21; 
       else ((MENU)getWorld()).campo[(int)(i/63+1)][(int)(j/63+1)] = 20;
       confirmou = !confirmou;
    }
    
    private int mudaDeImagem = 0;
    private int mudaDeImagemPlus = Greenfoot.getRandomNumber(10);
    private boolean muda = false;
    
    public void EFEITO()
    {
        mudaDeImagem++;
        if(mudaDeImagem == (25 + mudaDeImagemPlus)){
            if(muda)
                switch(numPedra){
                        case 1:
                        setImage("PEDRA01-01.png");
                    
                        break;
                        case 2:
                        setImage("PEDRA03-01.png");
                        break;
                        case 4:
                        setImage("PEDRA05-01.png");
                        break;
                    }
            else
                 switch(numPedra){
                        case 1:
                        setImage("PEDRA01-02.png");
                        break;
                        case 2:
                        setImage("PEDRA03-02.png");
                        break;
                        case 4:
                        setImage("PEDRA05-02.png");
                        break;
                    }
            muda = !muda;
            mudaDeImagem = 0;
        }
    }
}
