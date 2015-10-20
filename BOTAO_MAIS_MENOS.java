import greenfoot.*;

public class BOTAO_MAIS_MENOS extends BOTOES_E_NUMEROS
{
    public int casaDecimal;
    public boolean mais;
    
    public BOTAO_MAIS_MENOS(boolean i, int j)
    {
        if(i) setImage("MAIS.png");
        else  setImage("MENOS.png");
       
        mais = i;
        casaDecimal = j;
    }

    public void act() 
    {
        verificaClick();
    }   
    
    public void verificaClick()
    {
        if(Greenfoot.mouseClicked(this)){
            if(mais){
                switch(casaDecimal){
                    case 4:
                    ((MENU)getWorld()).num4.mais();
                    break;
                    case 3:
                    ((MENU)getWorld()).num3.mais();
                    break;
                    case 2:
                    ((MENU)getWorld()).num2.mais();
                    break;
                    case 1:
                    ((MENU)getWorld()).num1.mais();
                    break;
                }
            }
            else{
                switch(casaDecimal){
                    case 4:
                    ((MENU)getWorld()).num4.menos();
                    break;
                    case 3: 
                    ((MENU)getWorld()).num3.menos();
                    break;
                    case 2:
                    ((MENU)getWorld()).num2.menos();
                    break;
                    case 1:
                    ((MENU)getWorld()).num1.menos();
                    break;
                }
            }
        }
    }
}
