import greenfoot.*; 

public class NUMEROS extends BOTOES_E_NUMEROS
{
   public int valor = 0, contador = 0;
   public boolean deleta, maior;
   
   public NUMEROS(boolean i, boolean j)
   {
        deleta = i;
        maior = j;
   }
   
   public void act() 
   {
       atualizaImagem();
       if(deleta) {delete();}
   }   
    
    public void atualizaImagem()
    {
        switch(valor){
            case 0:
                if(maior) setImage("0.png");
                else setImage("000.png");
            break;
            case 1:
                if(maior) setImage("1.png");
                else setImage("001.png");
            break;
            case 2:
                if(maior) setImage("2.png");
                else setImage("002.png");
            break;
            case 3:
                if(maior) setImage("3.png");
                else setImage("003.png");
            break;
            case 4:
                if(maior) setImage("4.png");
                else setImage("004.png");
            break;
            case 5:
                if(maior) setImage("5.png");
                else setImage("005.png");
            break;
            case 6:
                if(maior) setImage("6.png");
                else setImage("006.png");
            break;
            case 7:
                if(maior) setImage("7.png");
                else setImage("007.png");
            break;
            case 8:
                if(maior) setImage("8.png");
                else setImage("008.png");
            break;
            case 9:
                if(maior) setImage("9.png");
                else setImage("009.png");
            break;
        }
    }
    
    public void mais()
    {
        if(valor == 9) valor = 0;
        else valor++;
    }
    public void menos()
    {
        if(valor == 0) valor = 9;
        else valor--;
    }
    
    int acelerar = 1;
    
    public void delete()
    {
        contador++;
        if(contador > 130) {setLocation(getX()+acelerar, getY()); acelerar++;}
        if(getX() > 720) {contador = 0; acelerar = 1; getWorld().removeObject(this);}
    }
}
