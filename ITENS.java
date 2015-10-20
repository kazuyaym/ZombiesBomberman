import greenfoot.*; 

public class ITENS extends JOGO
{
    public int botaoNum, imuni = 0;
    public Actor objetoPlayer;
    public String[] nomeItens = new String[9];
    
    public ITENS (int i)
    {
        for(int h = 0; h < nomeItens.length ; h++){
            nomeItens[h] = "" + "ITEM0" + h + ".png";
        }
        
        setImage(nomeItens[i]);
        botaoNum = i;
    }
    
    public void act() 
    {
        objetoPlayer = getOneObjectAtOffset(10,10,PLAYERS.class);
        if(objetoPlayer != null){
            PLAYERS jogadores = (PLAYERS) getOneIntersectingObject(PLAYERS.class);    
            
            switch (botaoNum){
                case 0:
                    jogadores.NumeroBombas++;
                break;
                case 1:
                    jogadores.BombaQual = 2;
                break;
                case 2:
                    jogadores.BombaQual = 3;
                break;
                case 3:
                    jogadores.TamanhoFogo++;
                break;
                case 4:
                    if(Greenfoot.getRandomNumber(3) == 0)
                        jogadores.cogumeloDoMal = true;
                    else if(Greenfoot.getRandomNumber(2) == 0)
                        jogadores.cogumeloDoMal2 = true;
                    else 
                        jogadores.velo = 4;
                break;
                case 5:
                    jogadores.velo = 1;
                break;
                case 6:
                    jogadores.M = 19;
                break;
                case 7:
                    jogadores.imuni = 1500;
                    jogadores.contadorDaMorte = 0;
                break;
                case 8:
                    if(jogadores.VIDA < 8) jogadores.ganhaVida();
                break;
            }
            
            
            getWorld().removeObject(this);
        }
        
        if(imuni < 20)
            imuni++;
    }
    
    public void delete()
    {
        if(botaoNum != 4 && imuni == 20) //cogumelos nao podem ser destruidos haha
            getWorld().removeObject(this);
    }
}
