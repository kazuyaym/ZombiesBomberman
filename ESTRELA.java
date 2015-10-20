import greenfoot.*;

public class ESTRELA extends JOGO
{
    public void act() 
    {
        EFEITO();
    }    
    
    private int mudaDeImagem = 0;
    private boolean muda = false;
    
    public void EFEITO()
    {
        mudaDeImagem++;
        if(mudaDeImagem == (25)){
            if(muda){setImage("ESTRELA00.png");}
            else{setImage("ESTRELA01.png");}
               
            muda = !muda;
            mudaDeImagem = 0;
        }
    }
}
