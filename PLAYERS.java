import greenfoot.*; 

public class PLAYERS extends JOGO
{
    public int player, contadorDaMorte = 0;
    public boolean cogumeloDoMal = false, cogumeloDoMal2 = false;
    
    public int originalX, originalY, imuni = 125, VIDA = 4;
    public int NumeroBombas = 1, TamanhoFogo = 1, BombaQual = 1, efeito = 1;
    
    public int direcao = 1;
    private final int CIMA = 0, BAIXO = 1, DIREITA = 2, ESQUERDA = 3;
    
    public Actor objetoFoguete;
    
    public PLAYERS(int i)
    {
        player = i;
        switch(player){
            case 1:
                setImage("BIXO01.png");
            break;
            case 2:
                setImage("PIRATA01.png");
            break;
        }
        
        VIDA = 3;
    }
    
    public boolean ande = true;
    public int velo = 2, velocidadeBixo = 2;

    public void act() 
    {
        if(!((MENU)getWorld()).jogoPausado){
            if(ande){
                switch(player){
                    case 1:
                    if(cogumeloDoMal2){andaAndaAnda();}
                    
                    if(!cogumeloDoMal) {ANDA_01();}
                    else {ANDA_01_INVERTIDO();}
                    
                    EFEITO_01();
                    break;
                    case 2:
                    if(cogumeloDoMal2){andaAndaAnda();}
                    
                    if(!cogumeloDoMal) {ANDA_02();}
                    else {ANDA_02_INVERTIDO();}
                    
                    EFEITO_02();
                    break;
                }
                ande = false;
            }   
        
            //imunidade
            if(contadorDaMorte < imuni) {contadorDaMorte++; getImage().setTransparency(140);} 
            if(contadorDaMorte == imuni) getImage().setTransparency(250);
        
            if(velocidadeBixo == 0) {velocidadeBixo = velo; ande = true;}
            else {velocidadeBixo--;}
        
            encontraFoguete();     
            tiraEfeitoCogumelo();
        }
    }   
    
    private int contadorCogumelo = 0;
    
    public void tiraEfeitoCogumelo()
    {
        if(velo == 4 || cogumeloDoMal || cogumeloDoMal2){
            contadorCogumelo++;
            if(contadorCogumelo == 1500){
                contadorCogumelo = 0;
                cogumeloDoMal = false;
                cogumeloDoMal2 = false;
                velo = 2;
            }
        }
    }
    
    public void andaAndaAnda()
    {
        switch(direcao){
            case CIMA:
                if(PodeAndar(0, -9))
                    setLocation(getX(), getY()-9);
            break;
            case BAIXO:
                if(PodeAndar(0, 9))
                    setLocation(getX(), getY()+9);
            break;
            case DIREITA:
                if(PodeAndar(9, 0))
                    setLocation(getX()+9, getY());
            break;
            case ESQUERDA:       
                if(PodeAndar(-9, 0))
                    setLocation(getX()-9, getY());
            break;
        }
    }
    
    public void ANDA_01()
    {
        if(Greenfoot.isKeyDown("up")){
            setImage("BIXO03.png");
            efeito = 2; direcao = CIMA;
            if(PodeAndar(0, -9))
                setLocation(getX(), getY()-9);
        }
        if(Greenfoot.isKeyDown("down")){
            setImage("BIXO01.png");
            efeito = 1; direcao = BAIXO;
            if(PodeAndar(0, 9))
                setLocation(getX(), getY()+9);
        }
        if(Greenfoot.isKeyDown("right")){
            setImage("BIXO05.png");
            efeito = 0; direcao = DIREITA;
            if(PodeAndar(9, 0))
                setLocation(getX()+9, getY());
        }
        if(Greenfoot.isKeyDown("left")){
            setImage("BIXO04.png");
            efeito = 0; direcao = ESQUERDA;
            if(PodeAndar(-9, 0))
                setLocation(getX()-9, getY());
        }
        
        if(Greenfoot.isKeyDown("enter")){ //PUTA CONTA DO DEMONIO haha
            jogaBomba();
        }
    }
    
        public void ANDA_01_INVERTIDO()
    {
        if(Greenfoot.isKeyDown("down")){
            setImage("BIXO03.png");
            efeito = 2; direcao = CIMA;
            if(PodeAndar(0, -9))
                setLocation(getX(), getY()-9);
        }
        if(Greenfoot.isKeyDown("up")){
            setImage("BIXO01.png");
            efeito = 1; direcao = BAIXO;
            if(PodeAndar(0, 9))
                setLocation(getX(), getY()+9);
        }
        if(Greenfoot.isKeyDown("left")){
            setImage("BIXO05.png");
            efeito = 0; direcao = DIREITA;
            if(PodeAndar(9, 0))
                setLocation(getX()+9, getY());
        }
        if(Greenfoot.isKeyDown("right")){
            setImage("BIXO04.png");
            efeito = 0; direcao = ESQUERDA;
            if(PodeAndar(-9, 0))
                setLocation(getX()-9, getY());
        }
        
        if(Greenfoot.isKeyDown("enter")){ //PUTA CONTA DO DEMONIO haha
            jogaBomba();
        }
    }
    
    public void ANDA_02()
    {
        if(Greenfoot.isKeyDown("w")){
            setImage("PIRATA05.png");
            efeito = 0; direcao = CIMA;
            if(PodeAndar(0, -9))
                setLocation(getX(), getY()-9);
        }
        if(Greenfoot.isKeyDown("s")){
            setImage("PIRATA01.png");
            efeito = 1; direcao = BAIXO;
            if(PodeAndar(0, 9))
                setLocation(getX(), getY()+9);
        }
        if(Greenfoot.isKeyDown("d")){
            setImage("PIRATA03.png");
            efeito = 0; direcao = DIREITA;
            if(PodeAndar(9, 0))
                setLocation(getX()+9, getY());
        }
        if(Greenfoot.isKeyDown("a")){
            setImage("PIRATA04.png");
            efeito = 0;  direcao = ESQUERDA;
            if(PodeAndar(-9, 0))
                setLocation(getX()-9, getY());
        }
        
        if(Greenfoot.isKeyDown("space")){ //PUTA CONTA DO DEMONIO haha
            jogaBomba();
        }
    }
    
        public void ANDA_02_INVERTIDO()
    {
        if(Greenfoot.isKeyDown("s")){
            setImage("PIRATA05.png");
            efeito = 0; direcao = CIMA;
            if(PodeAndar(0, -9))
                setLocation(getX(), getY()-9);
        }
        if(Greenfoot.isKeyDown("w")){
            setImage("PIRATA01.png");
            efeito = 1; direcao = BAIXO;
            if(PodeAndar(0, 9))
                setLocation(getX(), getY()+9);
        }
        if(Greenfoot.isKeyDown("a")){
            setImage("PIRATA03.png");
            efeito = 0; direcao = DIREITA;
            if(PodeAndar(9, 0))
                setLocation(getX()+9, getY());
        }
        if(Greenfoot.isKeyDown("d")){
            setImage("PIRATA04.png");
            efeito = 0; direcao = ESQUERDA;
            if(PodeAndar(-9, 0))
                setLocation(getX()-9, getY());
        }
        
        if(Greenfoot.isKeyDown("space")){ //PUTA CONTA DO DEMONIO haha
            jogaBomba();
        }
    }
    
    public void jogaBomba()
    {
        if(((MENU)getWorld()).campo[(getX()-30+(31-(getX()-30)%63))/63+1][(getY()-30+(31-(getY()-30)%63))/63+1] == 0){
            if(((MENU)getWorld()).numBombas[player-1] < NumeroBombas){ 
                 ((MENU)getWorld()).addObject(new BOMBAS(BombaQual, TamanhoFogo, player), getX()+(31-(getX()-30)%63), getY()+(31-(getY()-30)%63));
            }
        }
    }
    
    private int mudaDeImagem = 0;
    private boolean muda = false;
    
    public void EFEITO_01()
    {
        if(efeito == 1){
            mudaDeImagem++;
            if(mudaDeImagem == 5){
                if(muda)
                    setImage("BIXO01.png");
                else
                    setImage("BIXO02.png");
                muda = !muda;
                mudaDeImagem = 0;
            }
        }
        if(efeito == 2){
            mudaDeImagem++;
            if(mudaDeImagem == 5){
                if(muda)
                    setImage("BIXO03.png");
                else
                    setImage("BIXO03-02.png");
                muda = !muda;
                mudaDeImagem = 0;
            }
        }
    }
    
    public void EFEITO_02()
    {
        if(efeito != 0){
            mudaDeImagem++;
            if(mudaDeImagem == 5){
                if(muda)
                    setImage("PIRATA01.png");
                else
                    setImage("PIRATA02.png");
                 muda = !muda;
                 mudaDeImagem = 0;
            }
        }
    }
    
    


    
    public void addedToWorld(World world) 
    {
        originalX = getX();
        originalY = getY();
    }
    
    public void reset()
    {
        VIDA--;
        ((MENU)getWorld()).removeObject(((MENU)getWorld()).vidinha[player-1][VIDA]);
        
        velo = 2;
        cogumeloDoMal = false;
        cogumeloDoMal2 = false;
        
        imuni = 125;
        contadorDaMorte = 0;
        NumeroBombas = 1;
        TamanhoFogo = 1;
        BombaQual = 1;
        efeito = 1;
        M = 0;
        
        setLocation(originalX, originalY);
    }
    
    public void ganhaVida()
    {
        VIDA++;
        if(player == 1) ((MENU)getWorld()).addObject(((MENU)getWorld()).vidinha[player-1][VIDA-1], 750+((VIDA-1)*8), 110);
        if(player == 2) ((MENU)getWorld()).addObject(((MENU)getWorld()).vidinha[player-1][VIDA-1], 750+((VIDA-1)*8), 405);
    }
    
    public void die()
    {
        if(podeMorrer()){
            ((MENU)getWorld()).addObject(new POW(), getX(), getY());
            if(VIDA > 1) {reset();} 
            else {((MENU)getWorld()).removeObject(((MENU)getWorld()).vidinha[player-1][0]); getWorld().removeObject(this);}  
        }
    }
    
    public boolean podeMorrer()
    {
        return (contadorDaMorte >= imuni);
    }
    
    public boolean jaEmbarcou = false;
    
    public void encontraFoguete()
    {
        if(!jaEmbarcou){
            objetoFoguete = getOneObjectAtOffset(10,10,BOTAO_MENU.class);
            if(objetoFoguete != null){
                BOTAO_MENU foguetinho = (BOTAO_MENU) getOneIntersectingObject(BOTAO_MENU.class);    
                foguetinho.delete();
                ((MENU)getWorld()).addObject(new BOTAO_MENU(16), 457+30, 400+30);
                setLocation(originalX, originalY);
                ((MENU)getWorld()).play1.jaEmbarcou = true;
                ((MENU)getWorld()).play2.jaEmbarcou = true;
            }
        }
    }
    
    public int M = 0; //caso tenha item de passar por cima da plantas, o M aumentará para 19, caso contrario M = 0; 
    
    public boolean PodeAndar(int x1, int y1)
    {
        int 
        k = (getX()-30 + x1)%7,
        w = (getY()-30 + y1)%7,
        x2 = (getX()-30)/63,
        y2 = (getY()-30)/63;
        
        if(k == 4) {k = 0;}
        else if(k == 6) {k = 1;}
        else if(k == 1) {k = 2;}
        else if(k == 3) {k = 3;}
        else if(k == 5) {k = 4;}
        else if(k == 0) {k = 5;}
        else if(k == 2) {k = 6;}
        
        if(w == 4) {w = 0;}
        else if(w == 6) {w = 1;}
        else if(w == 1) {w = 2;}
        else if(w == 3) {w = 3;}
        else if(w == 5) {w = 4;}
        else if(w == 0) {w = 5;}
        else if(w == 2) {w = 6;}
        
        boolean
        a = getX() + x1 != 13+30,
        b = getX() + x1 != 679+30,
        c = getY() + y1 != 13+30,
        d = getY() + y1 != 553+30;
        
        if(((MENU)getWorld()).mundo == 1 && ((MENU)getWorld()).nivel == 4)
            if((getY() + y1) > 169 && (getY() + y1) < 457 && a && b) {return ajudaPodeAndar(x1,y1,x2,y2,k,w);}
        
        if(a && b && c && d)
            if ((k > 1 && k < 5) || (w > 1 && w < 5)) {return ajudaPodeAndar(x1,y1,x2,y2,k,w);}
        
        return false;
    }  
    
    public boolean ajudaPodeAndar(int x1, int y1, int x2, int y2, int k, int w)
    {
        x1 = x1/9;
        y1 = y1/9;
                
        if((k <= 1 && x1 == -1) || (k >= 5 && x1 == 1))
            return ((MENU)getWorld()).campo[x2+x1+1][y2+1] <= M;
        if((w <= 1 && y1 == -1) || (w >= 5 && y1 == 1))
            return ((MENU)getWorld()).campo[x2+1][y2+y1+1] <= M;
                    
        if(!(k >= 1 && k <= 5))
            return ((MENU)getWorld()).campo[x2+x1+1][y2+1] <= M;
        if(!(w >= 1 && w <= 5))
            return ((MENU)getWorld()).campo[x2+1][y2+y1+1] <= M;
                    
        return true;
    }
}
