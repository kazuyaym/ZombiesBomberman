import greenfoot.*;   

public class NEBLINA extends JOGO
{
    private int transp, inicio, j = -1;
    
    public NEBLINA(int i)
    {
        transp = i;
        inicio = i;
    }
    
    public void act() 
    {
        if(inicio == 250){
            transp += j;
            if(transp == 150) j = 1;
            if(transp == 230) j = -1;
        }
        
        if(inicio == 160){
            transp += j;
            if(transp == 85) j = 1;
            if(transp == 159) j = -1;
        }
        
        if(inicio == 90){
            transp += j;
            if(transp == 30) j = 1;
            if(transp == 89) j = -1;
        }
        
        getImage().setTransparency(transp);
    }    
}
