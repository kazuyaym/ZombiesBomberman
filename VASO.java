import greenfoot.*; 

public class VASO extends INIMIGOS_E_FLORES
{
    public void act() 
    {
        // Add your action code here.
    }   
    
    public void quebra()
    {
        ((MENU)getWorld()).addObject(new POW(), getX(), getY());
        
        if(((MENU)getWorld()).campo[(getX()-30)/63+1][(getY()-30)/63+1] == 19){
            ((MENU)getWorld()).addObject(new ESTRELA(), getX(), getY());
            ((MENU)getWorld()).achouEstrela++;
        }
        else{
            switch(Greenfoot.getRandomNumber(28)){
                case 0: case 1: case 20:((MENU)getWorld()).addObject(new ZUMBIE(1 ,3 , 1), getX(), getY()); break;
                case 2: case 3: case 21: ((MENU)getWorld()).addObject(new ZUMBIE(1 ,5 , 2), getX(), getY()); break;
                case 4: case 5: case 22:((MENU)getWorld()).addObject(new ZUMBIE(1 ,7 , 3), getX(), getY()); break;
                case 6: case 7: ((MENU)getWorld()).addObject(new ZUMBIE(2 ,8 , 4), getX(), getY()); break;
                case 8: case 9: case 23: ((MENU)getWorld()).addObject(new ZUMBIE(1 ,5 , 5), getX(), getY()); break;
                case 10: case 11: ((MENU)getWorld()).addObject(new ZUMBIE(1 ,5 , 6), getX(), getY()); break;
                case 12: case 13: ((MENU)getWorld()).addObject(new ZUMBIE(1 ,4 , 7), getX(), getY()); break;
                case 14: case 15: ((MENU)getWorld()).addObject(new ZUMBIE(1 ,4 , 8), getX(), getY()); break;
                case 16: case 17: ((MENU)getWorld()).addObject(new MUMIA(2 ,5), getX(), getY()); break;
                case 18: case 19: ((MENU)getWorld()).addObject(new MORTE(1 ,3), getX(), getY()); break;
                case 24: ((MENU)getWorld()).addObject(new ITENS(0), getX(), getY()); break;
                case 25: ((MENU)getWorld()).addObject(new ITENS(3), getX(), getY()); break;
                case 26: ((MENU)getWorld()).addObject(new ITENS(7), getX(), getY()); break;
                case 27: ((MENU)getWorld()).addObject(new ITENS(8), getX(), getY()); break;
            }
        }

        ((MENU)getWorld()).campo[(getX()-30)/63+1][(getY()-30)/63+1] = 0;
        getWorld().removeObject(this);
    }
}
