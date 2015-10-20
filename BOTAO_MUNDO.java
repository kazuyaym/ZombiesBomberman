import greenfoot.*; 

public class BOTAO_MUNDO extends BOTOES_E_NUMEROS
{
    int mundinho;

    public BOTAO_MUNDO(int i, int j)
    {
        mundinho = i;
        
        switch(i){
            case 1:
                setImage("MUNDO001.png");
            break;
            case 2:
                setImage("MUNDO002.png");
            break;
            case 3:
                setImage("MUNDO003.png");
            break;
            case 4:
                setImage("MUNDO004.png");
            break;
            case 5:
                setImage("JOGO_MEIO.png");    
            break;
            case 6:
                setImage("MUNDOESCOLHER.png");    
            break;
        }
        
        switch(j){
            case 1:
                getImage().scale(179, 159);
            break;
            case 2:
                getImage().scale(143, 127);
            break;
            case 3:
                getImage().scale(116, 103);
            break;
            case 4:
                getImage().scale(86, 76);
            break;
        }
    }
    
    public void deletaFundo()
    {
            getWorld().removeObject(this);
    }
    
    public void fogueteChegou()
    {
        ((MENU)getWorld()).iniciaFase();
    }
}
