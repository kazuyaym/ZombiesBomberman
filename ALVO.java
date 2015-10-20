import greenfoot.*; 

public class ALVO extends JOGO
{
    int contagemAnda = 0, contagemColocaTreco = 200, tipo;
    
    public ALVO(int tipo_)
    {
        tipo = tipo_;
    }

    public void act() 
    {
        if(!((MENU)getWorld()).jogoPausado){
            if(contagemAnda <= 19) {setLocation(getX()-1, getY()+1); contagemAnda++;} 
        
            contagemColocaTreco--;
            if(contagemColocaTreco == 0){ 
                if(tipo == 0) ((MENU)getWorld()).addObject(new BOMBAS(1,1,3), getX(), getY());
                else outros(); 
                
                getWorld().removeObject(this);
            }
        }
    }
    
    
    
    public void outros()
    {
        switch(Greenfoot.getRandomNumber(30)){
                case 0: case 1: case 20:((MENU)getWorld()).addObject(new ZUMBIE(1 ,3 , 1), getX(), getY()); break;
                case 2: case 3: case 21: ((MENU)getWorld()).addObject(new ZUMBIE(1 ,5 , 2), getX(), getY()); break;
                case 4: case 5: ((MENU)getWorld()).addObject(new ZUMBIE(1 ,7 , 3), getX(), getY()); break;
                case 6: case 7: ((MENU)getWorld()).addObject(new ZUMBIE(2 ,8 , 4), getX(), getY()); break;
                case 8: case 9: case 23: ((MENU)getWorld()).addObject(new ZUMBIE(1 ,5 , 5), getX(), getY()); break;
                case 10: case 11: ((MENU)getWorld()).addObject(new ZUMBIE(2 ,5 , 6), getX(), getY()); break;
                case 12: case 13: case 22: ((MENU)getWorld()).addObject(new ZUMBIE(1 ,4 , 7), getX(), getY()); break;
                case 14: case 15: ((MENU)getWorld()).addObject(new ZUMBIE(1 ,4 , 8), getX(), getY()); break;
                case 16: case 17: ((MENU)getWorld()).addObject(new MUMIA(2 ,5), getX(), getY()); break;
                case 18: case 19: ((MENU)getWorld()).addObject(new MORTE(1 ,3), getX(), getY()); break;
                case 24: case 28: ((MENU)getWorld()).addObject(new ITENS(0), getX(), getY()); break;
                case 25: case 29: ((MENU)getWorld()).addObject(new ITENS(3), getX(), getY()); break;
                case 26: ((MENU)getWorld()).addObject(new ITENS(7), getX(), getY()); break;
                case 27: ((MENU)getWorld()).addObject(new ITENS(8), getX(), getY()); break;
        }
    }
}
