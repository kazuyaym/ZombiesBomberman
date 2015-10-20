import greenfoot.*; 

public class NUMEROS2 extends BOTOES_E_NUMEROS
{
   public int valor = 0, contador = 0;
   
   
   
   
   public void act() 
   {
       atualizaImagem();
       delete();
   }   
    
    public void atualizaImagem()
    {
        switch(valor){
            case 1:
                setImage("0-01.png");
            break;
            case 2:
                setImage("0-02.png");
            break;
            case 3:
                setImage("0-03.png");
            break;
            case 4:
                setImage("0-04.png");
            break;
            case 5:
                setImage("0-05.png");
            break;
        }
    }
    
    int acelerar = 1;
    
    public void delete()
    {
        contador++;
        if(contador > 127) {setLocation(getX()-acelerar, getY()); acelerar++;}
        if(getX() < 10) {contador = 0; acelerar = 1; getWorld().removeObject(this);}
    }
}

