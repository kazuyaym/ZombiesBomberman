import greenfoot.*;  

public class GIRASSOL extends JOGO
{
    public int alcance, qualZum;

    public GIRASSOL(int i, int j)
    {
        alcance = -(i*63);
        qualZum = j;
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
            if(muda){setImage("GIRA01.png");}
            else{setImage("GIRA02.png");}
               
            muda = !muda;
            mudaDeImagem = 0;
        }
    }
    
    private int vidaGira = 0;
     
    public void QUEIMA()
    {
        setImage("GIRA03.png");
        
        if(vidaGira == 15){
            vidaGira = 0;
            for(int i = alcance; i <= -alcance; i += 63)
                for(int j = alcance; j <= -alcance; j += 63)
                    if(((MENU)getWorld()).campo[(getX()-30+i)/63+1][(getY()-30+j)/63+1] == 0){
                        ((MENU)getWorld()).addObject(new ZUMBIE(1,7,qualZum), getX()+i, getY()+j);  
                    }
        }
        
        vidaGira++;
    }  
}
