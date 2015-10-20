import greenfoot.*; 
import java.util.*;

public class FOGO extends JOGO
{
    public void act() 
    {
        if(!((MENU)getWorld()).jogoPausado){
            botaFogo();
            APAGA();
            EFEITO();
        }
    }  
    
    List objetoPlanta, objetoPlanta1, objetoPlanta2, objetoPlayer, objetoBomba, objetoItem, objetoVaso,
    objetoInimigo, objetoInimigo2, objetoPlantaBomba, objetoCova, objetoGelo, objetoMorte, objetoChefao;
    
    public void botaFogo()
    {
        int raio = 28;
        
        objetoPlanta = getObjectsInRange(raio, PLANTA.class);
        if(!objetoPlanta.isEmpty()){
            PLANTA plantinha = (PLANTA) getOneIntersectingObject(PLANTA.class);
            plantinha.QUEIMA();
        }
        
        objetoPlanta1 = getObjectsInRange(raio, GIRASSOL.class);
        if(!objetoPlanta1.isEmpty()){
            GIRASSOL plantinha1 = (GIRASSOL) getOneIntersectingObject(GIRASSOL.class);
            plantinha1.QUEIMA();
        }
        
        objetoPlanta2 = getObjectsInRange(raio, COGUMELO.class);
        if(!objetoPlanta2.isEmpty()){
            COGUMELO plantinha2 = (COGUMELO) getOneIntersectingObject(COGUMELO.class);
            plantinha2.QUEIMA();
        }
        
        objetoPlayer = getObjectsInRange(raio, PLAYERS.class);
        if(!objetoPlayer.isEmpty()){
            PLAYERS jogadores = (PLAYERS) getOneIntersectingObject(PLAYERS.class);    
            jogadores.die();
        }
        
        objetoBomba = getObjectsInRange(raio, BOMBAS.class);
        if(!objetoBomba.isEmpty()){
            BOMBAS bombinhas = (BOMBAS) getOneIntersectingObject(BOMBAS.class);    
            bombinhas.exploda();
        }
        
        objetoItem = getObjectsInRange(raio, ITENS.class);
        if(!objetoItem.isEmpty()){
            ITENS itenzinhos = (ITENS) getOneIntersectingObject(ITENS.class);    
            itenzinhos.delete();
        }
        
        objetoInimigo = getObjectsInRange(raio, MUMIA.class);
        if(!objetoInimigo.isEmpty()){
            MUMIA inimiguinhos = (MUMIA) getOneIntersectingObject(MUMIA.class);    
            inimiguinhos.mataInimigo();
        }
        
        objetoInimigo2 = getObjectsInRange(raio, ZUMBIE.class);
        if(!objetoInimigo2.isEmpty()){
            ZUMBIE inimiguinhos2 = (ZUMBIE) getOneIntersectingObject(ZUMBIE.class);    
            inimiguinhos2.mataInimigo();
        }
        
        objetoPlantaBomba = getObjectsInRange(raio, PLANTA_BOMBA.class);
        if(!objetoPlantaBomba.isEmpty()){
            PLANTA_BOMBA plantinhaBombinha = (PLANTA_BOMBA) getOneIntersectingObject(PLANTA_BOMBA.class);    
            plantinhaBombinha.QUEIMA();
        }
        
        objetoCova = getObjectsInRange(raio, COVA.class);
        if(!objetoCova.isEmpty()){
            COVA covinha = (COVA) getOneIntersectingObject(COVA.class);    
            covinha.QUEIMA();
        }
        
        objetoGelo = getObjectsInRange(raio, GELO.class);
        if(!objetoGelo.isEmpty()){
            GELO gelinho = (GELO) getOneIntersectingObject(GELO.class);    
            gelinho.delete();
        }
        
        objetoMorte = getObjectsInRange(raio, MORTE.class);
        if(!objetoMorte.isEmpty()){
            MORTE mortinha = (MORTE) getOneIntersectingObject(MORTE.class);    
            mortinha.mataInimigo();
        }
        
        objetoChefao = getObjectsInRange(raio, CHEFAO.class);
        if(!objetoChefao.isEmpty()){
            CHEFAO chefaozinho = (CHEFAO) getOneIntersectingObject(CHEFAO.class);    
            chefaozinho.tiraVida();
        }
        
        objetoVaso  = getObjectsInRange(raio, VASO.class);
        if(!objetoVaso.isEmpty()){
            VASO vasinho = (VASO) getOneIntersectingObject(VASO.class);
            vasinho.quebra();
        }
    }
    
    private int some = 0;
    
    public void APAGA()
    {
        some++;
        if(some == 16)
            getWorld().removeObject(this);
    }
    
    private int mudaDeImagem = 0;
    private int mudaDeImagemPlus = Greenfoot.getRandomNumber(5);
    private boolean muda = false;
    
    public void EFEITO()
    {
        mudaDeImagem++;
        if(mudaDeImagem == (4 + mudaDeImagemPlus)){
            if(muda)
                setImage("FOGO01.png");
            else
                setImage("FOGO02.png");
            muda = !muda;
            mudaDeImagem = 0;
        }
    }
}
