import greenfoot.*; 
import java.util.List;

public class MENU extends World
{
    public static int[][] campo = new int [13][11];
    public static int[] numBombas = new int [3];
    public static int mundo = 0, numJogadores = 1, nivel = 0;  
     
    static public int[] itensAColocar = new int[9];
    static public int[] cord = new int[12];
    static public int[][] FASES = new int[4][4];
    
    public static boolean English = true, precisaMudarMundo = true;
    public static int achouEstrela = 0;
    public List A,B,C,D,INIM,playerNoJogo;
    
    public boolean dentroJogo = false, jogoPausado = false;
    
    public VIDA[][] vidinha = new VIDA[2][10];
    
          
    public MENU()
    {    
        super(825, 627, 1); 
        botao_lingua();
        
        //codigos
        FASES[0][0] = 0; FASES[0][1] = 3512; FASES[0][2] = 4798; FASES[0][3] = 9674;
        FASES[1][0] = 2224; FASES[1][1] = 7531; FASES[1][2] = 5300; FASES[1][3] = 7577;
        FASES[2][0] = 2481; FASES[2][1] = 8422; FASES[2][2] = 2843; FASES[2][3] = 2571;
        FASES[3][0] = 6221; FASES[3][1] = 1015; FASES[3][2] = 7258; FASES[3][3] = 8901;
        
        for(int i_ = 0; i_ <= 1; i_++) for(int j_ = 0; j_ <= 9; j_++) vidinha[i_][j_] = new VIDA();
        //cordenadas
        for (int c = 0; c < 12; c++) cord[c] = 61+63*(c-1);
    }
    
    BOTAO_MENU foguetinho = new BOTAO_MENU(17);
    List outroFoguete;
    
    public void act()
    {
        colocaNaOrdem();
        adicionaPlayer2();
        if(mundo != 3 || nivel != 4) acabouInimigos();
        if(mundo == 3 && nivel == 4 && achouEstrela >= 3){ajudaAcabouInim();}
        
        pausandoJogo();
        gameOverJogo();
    }
    
    public void adicionaPlayer2()
    {
        if(dentroJogo && Greenfoot.isKeyDown("w") && numJogadores == 1){  
            
            int b_, c_;
            if(codigo == 7577 || codigo == 2571 || codigo == 8901){b_ = 1; c_ = 6;}
            else {b_ = 11; c_ = 9;}   
            if(play2.VIDA > 0) {addObject(play2, cord[b_], cord[c_]);}
            
            numJogadores = 2;
            
            removeObject(tecla3);
            addObject(tecla2, 775, 475);
            for(int i_ = 0; i_ < play2.VIDA; i_++) addObject(vidinha[1][i_], 750+(i_*8), 405);
        }
    }
    
    public void pausandoJogo()
    {
        if(Greenfoot.isKeyDown("escape")){
            if(!jogoPausado && dentroJogo){
                jogoPausado = true;
                pausar();
            }
            else if(jogoPausado && dentroJogo){
                jogoPausado = false;
                List despausar = getObjects(BOTOES_E_NUMEROS.class);
                removeObjects(despausar);
            }
        }
    }
    
    public void gameOverJogo()
    {
        if(dentroJogo){
            playerNoJogo = getObjects(PLAYERS.class);
            if(playerNoJogo.isEmpty() && !jogoPausado){
                pausar();
            }
        }
    }
    
    
    
    public void pausar()
    {
        addObject(new BOTAO_MUNDO(5,5), cord[7]-30, cord[5]+15);
        
        NUMEROS num11 = new NUMEROS(false, false); num11.valor = codigo/1000;
        NUMEROS num12 = new NUMEROS(false, false); num12.valor = (codigo%1000)/100;
        NUMEROS num13 = new NUMEROS(false, false); num13.valor = (codigo%100)/10;
        NUMEROS num14 = new NUMEROS(false, false); num14.valor = codigo%10;
   
        addObject(num11, cord[8]-30, cord[7]-20);
        addObject(num12, cord[8]-5, cord[7]-20);
        addObject(num13, cord[8]+20, cord[7]-20);
        addObject(num14, cord[8]+45, cord[7]-20);
        
        if(jogoPausado){
            addObject(new BOTAO_MENU(18), cord[7]-35, cord[5]-60);
            addObject(new BOTAO_MENU(20), cord[7]-35, cord[5]+5);
            addObject(new BOTAO_MENU(21), cord[7]-35, cord[5]+50);
        }
        else{
            jogoPausado = true;
            addObject(new BOTAO_MENU(19), cord[7]-35, cord[5]-60);
            addObject(new BOTAO_MENU(21), cord[7]-35, cord[5]+25);
        }
    }
    
    public void acabouInimigos()
    {    
        if(!precisaMudarMundo){
            INIM = getObjects(INIMIGOS_E_FLORES.class);
            if(INIM.isEmpty() && nivel > 0){ajudaAcabouInim();}//muda de fase 
            if(!INIM.isEmpty() && nivel < 0){
                nivel += 6;
                removeObject(foguetinho);
            }
        }
    }
    
    public void ajudaAcabouInim()
    {
        if(mundo == 4 && nivel == 4){
            addObject(foguetinho, 507, 410);
        }    
        else{
            if(nivel != 4){codigo = FASES[mundo-1][nivel];}//aqui o nivel é o mesmo por que na matriz, o nivel certo é nivel - 1
            else{precisaMudarMundo = true; codigo = FASES[mundo][0];}
        }
        
        if(nivel != 4) nivel -= 6;
        
        outroFoguete = getObjects(BOTAO_MENU.class);
        if(outroFoguete.isEmpty()) { addObject(foguetinho, 507, 410); }
    }
    
    public void colocaNaOrdem()
    {
        setPaintOrder(GELO.class);
        setPaintOrder(ITENS.class);
        setPaintOrder(BOMBAS.class);
        setPaintOrder(PLANTA.class);
        setPaintOrder(PEDRA.class);
        setPaintOrder(GIRASSOL.class);
        setPaintOrder(COGUMELO.class);
        setPaintOrder(COVA.class);
        setPaintOrder(PLANTA_BOMBA.class);
        setPaintOrder(VASO.class);
        setPaintOrder(ALVO.class);
        setPaintOrder(MUMIA.class);
        setPaintOrder(MORTE.class);
        setPaintOrder(PLAYERS.class);
        setPaintOrder(RAIO.class);
        setPaintOrder(ZUMBIE.class);
        setPaintOrder(FOGO.class);
        setPaintOrder(TIRO.class);
        setPaintOrder(CHEFAO.class);
        setPaintOrder(BOM.class);
        setPaintOrder(POW.class);
        setPaintOrder(NEBLINA.class);
        setPaintOrder(BOTOES_E_NUMEROS.class);
        
    }
    
    public static int codigo = 0;
    NUMEROS num4 = new NUMEROS(false, true);
    NUMEROS num3 = new NUMEROS(false,true);
    NUMEROS num2 = new NUMEROS(false,true);
    NUMEROS num1 = new NUMEROS(false,true);
    PLAYERS play1 = new PLAYERS(1);
    PLAYERS play2 = new PLAYERS(2);
    
    public void adicionaNumeroCodigo()
    {
        addObject(num4, 193, 340);
        addObject(num3, 310, 340);
        addObject(num2, 427, 340);
        addObject(num1, 544, 340);
        codigo = 0;
    }
    
        public void botao_lingua()
    {
        addObject(new Mouse(), 1, 1);
        addObject(new BOTAO_MENU(1), 414, 340);
        addObject(new BOTAO_MENU(2), 414, 420);   
    }
    
    
    public void voltaMenuNormal()
    {
        // COLOCA MENU NORMAL
        B = getObjects(JOGO.class); removeObjects(B);
        C = getObjects(BOTOES_E_NUMEROS.class); removeObjects(C);
        D = getObjects(INIMIGOS_E_FLORES.class); removeObjects(D);
        
        
        mundo = 0; nivel = 0;
        precisaMudarMundo = true;
        dentroJogo = false;
        numBombas[0] = 0; numBombas[1] = 0; numBombas[2] = 0;
        
        setBackground("MENU_002.jpg");
        List objetoBotao = getObjects(BOTAO_MENU.class);           
        removeObjects(objetoBotao); 
        
        addObject(new BOTAO_MENU(3), 365, 250);
        addObject(new BOTAO_MENU(5), 365, 325);
        addObject(new BOTAO_MENU(4), 475, 420);
        
    }
    
    public void colocaFoguete()
    {
        List objetoBotao = getObjects(BOTAO_MENU.class);           
        removeObjects(objetoBotao); 
        
        addObject(new BOTAO_MENU(16), 794, 510);
    }
    
    
    public void confereCodigo()
    {
        List num = getObjects(NUMEROS.class);
        List mais  = getObjects(BOTAO_MAIS_MENOS.class);
        List bot = getObjects(BOTAO_MENU.class);
        removeObjects(bot);
        mundo = 0;
            
        if(codigoCorreto()){
            MENU_MUNDOS();
        }
        else{
            codigo = 0;
            voltaMenuNormal();
        }
        
        removeObjects(num);
        removeObjects(mais);
    }
    
    public boolean codigoCorreto()
    {
        for(int k = 0; k < 4; k++){
            for(int ky = 0; ky < 4; ky++){
                if(FASES[k][ky] == codigo){
                    mundo = k+1;
                    k = 4; ky = 4;
                }
            }
        }
        
        if(codigo == 1111) mundo = 1;
        
        return mundo != 0;
    }
    
    public void MENU_MUNDOS()
    {
        A = getObjects(BOTAO_MENU.class); removeObjects(A);
        B = getObjects(JOGO.class); removeObjects(B);
        C = getObjects(BOTAO_MUNDO.class); removeObjects(C);
        D = getObjects(INIMIGOS_E_FLORES.class); removeObjects(D);
        dentroJogo = false;
        
        if(precisaMudarMundo){
            precisaMudarMundo = false;
            
            setBackground("MUNDO05.jpg");
            switch(mundo){
                case 1:
                    addObject(new BOTAO_MUNDO(2,1), 226, 240);
                    addObject(new BOTAO_MUNDO(3,2), 374, 121);
                    addObject(new BOTAO_MUNDO(4,3), 548, 103);
                
                break;
                case 2:
                    addObject(new BOTAO_MUNDO(3,1), 226, 240);
                    addObject(new BOTAO_MUNDO(4,2), 374, 121);
             
                break;
                case 3:
                    addObject(new BOTAO_MUNDO(4,1), 226, 240);
                break;
            }
        
            addObject(new BOTAO_MUNDO(6,0), 530, 430);
            addObject(new BOTAO_MENU(14), 440, 430);
            addObject(new BOTAO_MENU(15), 595, 430);
            addObject(new BOTAO_MUNDO(mundo,0), 190, 450);
        }
    }
    
    public void colocaVasos()
    {
        int[] Ci = new int[3];
        int[] Cj = new int[3];
        
        while((Ci[0] == Ci[1] && Cj[0] == Cj[1]) || (Ci[0] == Ci[2] && Cj[0] == Cj[2]) || (Ci[2] == Ci[1] && Cj[2] == Cj[1])){
            for(int i = 0; i <= 2; i ++){Ci[i] = (Greenfoot.getRandomNumber(5)*2)+3;}
            for(int j = 0; j <= 2; j ++){Cj[j] = (Greenfoot.getRandomNumber(5)*2)+1;}
        }
        
        for(int i = 3; i <= 11; i += 2) {
            for(int j = 1; j <= 9; j += 2) {
                addObject(new VASO(), cord[i], cord[j]);
                
                if(i == Ci[0] && j == Cj[0]) campo[i][j] = 19;
                else if (i == Ci[1] && j == Cj[1]) campo[i][j] = 19;
                else if (i == Ci[2] && j == Cj[2]) campo[i][j] = 19;
                else campo[i][j] = 10;
            }
        }
        
        
    }
    
    public void colocaPedras()
    {
        if(mundo == 1 && nivel == 4){ 
            for(int i = 124; i <= 691; i += 126) addObject(new PEDRA(mundo), i, 124);
            for(int i = 124; i <= 691; i += 126) addObject(new PEDRA(mundo), i, 502);
        }
        else{
            for(int i = 124; i <= 691; i += 126)
                 for(int j = 124; j <= 565; j +=126)
                     addObject(new PEDRA(mundo), i, j);
        }
    }
    
    public void ajeitaPlantas()
    {
        int porcen = 7;
        if(mundo == 1 && nivel == 4) porcen = 8;
        if(mundo == 4 || mundo == 3) porcen = 9;
        
        //marca as plantas em lugares aleatorios
        for(int i = 1; i <= 11; i += 2) for(int j = 1; j <= 9; j++) if(Greenfoot.getRandomNumber(porcen) <= 2){campo[i][j] = 10;}
        for(int i = 1; i <= 9; i += 2) for(int j = 1; j <= 11; j++)if(Greenfoot.getRandomNumber(porcen) <= 2){campo[j][i] = 10;}

        //marca os lugares onde nao devem vir plantas
        campo[1][1] = 0; campo[1][2] = 0; campo[2][1] = 0;
        campo[11][9] = 0; campo[10][9] = 0; campo[11][8] = 0;
    }
    
    public void colocaPlantas()
    {
        if(mundo == 1 && nivel == 4){
            
            for(int i = 1; i <= 11; i++){
                for(int j = 1; j <= 2; j++){
                    if(campo[i][j] == 10)
                        addObject(new PLANTA(1), 61+63*(i-1), 61+63*(j-1));
                }
            }
            
            for(int i = 1; i <= 11; i++){
                for(int j = 3; j <= 7; j++){
                    if(campo[i][j] == 10)
                        addObject(new PLANTA(5), 61+63*(i-1), 61+63*(j-1));
                }
            }
            
            for(int i = 1; i <= 11; i++){
                for(int j = 8; j <= 9; j++){
                    if(campo[i][j] == 10)
                        addObject(new PLANTA(1), 61+63*(i-1), 61+63*(j-1));
                }
            }
        }
        else{
            for(int i = 1; i <= 11; i++){
                for(int j = 1; j <= 9; j++){
                    if(campo[i][j] == 10)
                        addObject(new PLANTA(mundo), 61+63*(i-1), 61+63*(j-1));
                }
            }
        }
    }
    
    public int [] itemColocado = new int[7];
    public boolean REVEZA = true;
    
    public void colocaItens(int[] item)
    {
        for(int i = 0; i < itemColocado.length; i++)
            itemColocado[i] = 0;
            
        for(int i = 0; i < itemColocado.length; i++){
            
            while(itemColocado[i] < item[i]){
                
                if(REVEZA){
                    for(int x = 11; x >= 1; x--){
                        for(int y = 1; y <= 9; y++){
                            if(campo[x][y] == 10 && Greenfoot.getRandomNumber(60) == 0){
                                campo[x][y] = 11 + i;
                                itemColocado[i]++;
                                if(itemColocado[i] == item[i]){x = 0; y = 10;}
                            }
                        }
                    }
                    REVEZA = !REVEZA;
                }
                else{
                    for(int x = 1; x <= 11; x++){
                        for(int y = 9; y >= 1; y--){
                            if(campo[x][y] == 10 && Greenfoot.getRandomNumber(60) == 0){
                                campo[x][y] += (i + 1);
                                itemColocado[i]++;
                                if(itemColocado[i] == item[i]){x = 10; y = 0;}
                            }
                        }
                    }
                    REVEZA = !REVEZA;
                }
                
            }
            
        }
    }
    
    public void zerarPlayer()
    {
        play1.NumeroBombas = 1;
        play1.TamanhoFogo = 1;
        play1.BombaQual = 1;
        play1.contadorDaMorte = 0;
        play1.VIDA = 4;
        play1.velo = 2;
        play1.cogumeloDoMal = false;
        play1.cogumeloDoMal2 = false;
        play1.M = 0;
        play2.NumeroBombas = 1;
        play2.TamanhoFogo = 1;
        play2.BombaQual = 1;
        play2.contadorDaMorte = 0;
        play2.VIDA = 4;
        play2.velo = 2;
        play2.cogumeloDoMal = false;
        play2.cogumeloDoMal2 = false;
        play2.M = 0;
    }
    
    public void colocaAlvoChefao01()
    {
        if(Greenfoot.getRandomNumber(5) <= 2) {addObject(new ALVO(0), cord[5]+20, cord[3]-20);}            
        if(Greenfoot.getRandomNumber(5) <= 2) {addObject(new ALVO(0), cord[5]+20, cord[7]-20);} 
        if(Greenfoot.getRandomNumber(5) <= 1) {addObject(new ALVO(0), cord[7]+20, cord[1]-20);} 
        if(Greenfoot.getRandomNumber(5) <= 1) {addObject(new ALVO(0), cord[8]+20, cord[5]-20);} 
        if(Greenfoot.getRandomNumber(5) <= 1) {addObject(new ALVO(0), cord[8]+20, cord[7]-20);} 
        if(Greenfoot.getRandomNumber(5) <= 1) {addObject(new ALVO(0), cord[8]+20, cord[3]-20);} 
        if(Greenfoot.getRandomNumber(5) <= 1) {addObject(new ALVO(0), cord[9]+20, cord[9]-20);}
        if(Greenfoot.getRandomNumber(5) <= 1) {addObject(new ALVO(0), cord[11]+20, cord[6]-20);}
        if(Greenfoot.getRandomNumber(5) <= 1) {addObject(new ALVO(0), cord[11]+20, cord[4]-20);}
        if(Greenfoot.getRandomNumber(5) <= 1) {addObject(new ALVO(0), cord[9]+20, cord[1]-20);}
        if(Greenfoot.getRandomNumber(5) <= 1) {addObject(new ALVO(0), cord[7]+20, cord[9]-20);} 
        if(Greenfoot.getRandomNumber(5) <= 2) {addObject(new ALVO(0), cord[5]+20, cord[5]-20);}
    }
    
    public void colocaAlvoChefao02()
    {
        if(Greenfoot.getRandomNumber(5) <= 2) {addObject(new ALVO(Greenfoot.getRandomNumber(2)), cord[5]+20, cord[3]-20);}            
        if(Greenfoot.getRandomNumber(5) <= 1) {addObject(new ALVO(Greenfoot.getRandomNumber(2)), cord[5]+20, cord[7]-20);} 
        if(Greenfoot.getRandomNumber(5) <= 2) {addObject(new ALVO(Greenfoot.getRandomNumber(2)), cord[7]+20, cord[1]-20);} 
        if(Greenfoot.getRandomNumber(5) <= 1) {addObject(new ALVO(Greenfoot.getRandomNumber(2)), cord[8]+20, cord[5]-20);} 
        if(Greenfoot.getRandomNumber(5) <= 2) {addObject(new ALVO(Greenfoot.getRandomNumber(2)), cord[8]+20, cord[7]-20);} 
        if(Greenfoot.getRandomNumber(5) <= 1) {addObject(new ALVO(Greenfoot.getRandomNumber(2)), cord[8]+20, cord[3]-20);} 
        if(Greenfoot.getRandomNumber(5) <= 2) {addObject(new ALVO(Greenfoot.getRandomNumber(2)), cord[9]+20, cord[9]-20);}
        if(Greenfoot.getRandomNumber(5) <= 1) {addObject(new ALVO(Greenfoot.getRandomNumber(2)), cord[11]+20, cord[6]-20);}
        if(Greenfoot.getRandomNumber(5) <= 2) {addObject(new ALVO(Greenfoot.getRandomNumber(2)), cord[11]+20, cord[4]-20);}
        if(Greenfoot.getRandomNumber(5) <= 1) {addObject(new ALVO(Greenfoot.getRandomNumber(2)), cord[9]+20, cord[1]-20);}
        if(Greenfoot.getRandomNumber(5) <= 2) {addObject(new ALVO(Greenfoot.getRandomNumber(2)), cord[7]+20, cord[9]-20);} 
        if(Greenfoot.getRandomNumber(5) <= 1) {addObject(new ALVO(Greenfoot.getRandomNumber(2)), cord[5]+20, cord[5]-20);}
    }
    
    public void ajeitaNivel(boolean primeira)
    {
        if(primeira){
            for(int i = 0; i < 7; i++)
                itensAColocar[i] = 0;
        
                switch(codigo){
                    case 0: nivel = 1; 
                                    campo[5][5] = 0;
                                    campo[9][1] = 0; 
                                    campo[1][9] = 0; 
                                    campo[3][4] = 19;
                                    campo[10][3] = 19; 
                                    campo[7][6] = 19;
                                    itensAColocar[0] = 1;
                    break;
                    case 3512: nivel = 2; campo[3][3] = 0; campo[9][7] = 0; campo[3][7] = 0;  campo[2][5] = 19; campo[6][3] = 19; 
                                          campo[9][3] = 0; campo[5][5] = 0; campo[7][5] = 0;  campo[10][5] = 19; campo[6][7] = 19;
                                          campo[3][1] = 10; campo[1][3] = 10; campo[11][7] = 10;  campo[9][9] = 10;
                                          break;  
                    case 4798: nivel = 3; campo[1][6] = 0; campo[1][9] = 0;  campo[11][1] = 0; campo[5][1] = 0; 
                                          campo[7][7] = 0; campo[6][5] = 0;  campo[2][5] = 19;  campo[10][5] = 19; campo[7][5] = 19; 
                                          itensAColocar[1] = 1; itensAColocar[5] = 2; 
                                          break; 
                    case 9674: nivel = 4; campo[5][3] = 0; campo[4][5] = 0; campo[9][7] = 0; campo[5][7] = 0; campo[11][3] = 0; campo[1][7] = 0; 
                                          campo[8][1] = 0; campo[4][7] = 0; itensAColocar[8] = 1;
                                          campo[11][1] = 19; campo[1][9] = 19; itensAColocar[5] = 1;  itensAColocar[1] = 1; setBackground("FUNDO02.jpg");
                    break;
                               
                    case 2224: nivel = 1; campo[5][1] = 0; campo[7][3] = 0; campo[11][2] = 0; campo[5][5] = 0; campo[2][7] = 0; campo[7][9] = 0;
                                          campo[2][5] = 19; campo[4][5] = 19; campo[6][5] = 19; campo[8][5] = 19; itensAColocar[6] = 1; itensAColocar[7] = 1;
                                          itensAColocar[5] = 1; itensAColocar[2] = 2;
                    break;
                    case 7531: nivel = 2; campo[3][9] = 0; campo[5][3] = 0; campo[7][6] = 0; campo[9][1] = 0;
                                          campo[2][5] = 19; campo[6][5] = 19; campo[10][5] = 19; campo[3][1] = 10; campo[1][3] = 10; campo[11][7] = 10; campo[9][9] = 10;
                                          campo[3][2] = 19; campo[3][8] = 19; campo[4][5] = 19;campo[6][3] = 19; campo[6][7] = 19; campo[8][5] = 19;campo[9][2] = 19; campo[9][8] = 19; 
                    break;
                    
                    case 5300: nivel = 3; campo[1][9] = 0; campo[3][4] = 0; campo[5][6] = 0; campo[7][4] = 0; campo[9][6] = 0; campo[11][1] = 0; itensAColocar[8] = 1;
                                          campo[3][8] = 19; campo[6][5] = 19; campo[9][2] = 19; campo[3][1] = 10; campo[1][3] = 10; campo[11][7] = 10; campo[9][9] = 10;
                                          campo[4][3] = 19; campo[5][2] = 19; campo[7][2] = 19;campo[8][3] = 19; campo[4][7] = 19; campo[5][8] = 19;campo[7][8] = 19; campo[8][7] = 19; 
                    break;
                    
                    case 7577: nivel = 4; podePlantas = false; campo[10][3] = 19; campo[10][7] = 19;
                    break;
                    
                    case 2481: nivel = 1; campo[1][9] = 0; campo[3][7] = 0; campo[11][1] = 0; campo[9][3] = 0; campo[5][3] = 19; 
                                          itensAColocar[5] = 1;
                    break;
                    case 8422: nivel = 2; campo[3][7] = 0; campo[9][3] = 0; campo[3][3] = 19; campo[9][7] = 19; campo[5][4] = 19; campo[5][6] = 19;
                                          campo[7][4] = 19; campo[7][6] = 19; campo[6][5] = 19; itensAColocar[5] = 1; itensAColocar[1] = 1; itensAColocar[7] = 1;
                    break;
                    case 2843: nivel = 3;  campo[1][9] = 0; campo[5][7] = 0; campo[11][1] = 0; campo[7][3] = 0; campo[5][5] = 0; campo[7][5] = 0;
                                           campo[4][5] = 19; campo[6][5] = 0; campo[8][5] = 19; itensAColocar[6] = 1; itensAColocar[2] = 1;
                    break;
                    case 2571: nivel = 4; podePlantas = false;
                    break;
                    
                    case 6221: nivel = 1; campo[1][9] = 0; campo[4][9] = 0; campo[6][5] = 0; campo[8][1] = 0; campo[11][1] = 0;
                                          campo[2][5] = 19; campo[3][4] = 19; campo[3][5] = 19; campo[3][6] = 19; campo[4][5] = 19;
                                          campo[8][5] = 19; campo[9][4] = 19; campo[9][5] = 19; campo[9][6] = 19; campo[10][5] = 19;
                                          campo[3][2] = 19; campo[9][8] = 19; itensAColocar[1] = 1;  itensAColocar[2] = 1; itensAColocar[6] = 1; itensAColocar[7] = 1;
                    break;
                    case 1015: nivel = 2; campo[1][9] = 0; campo[4][9] = 0; campo[6][3] = 0; campo[6][7] = 0; campo[8][1] = 0; campo[11][1] = 0;
                                          campo[3][1] = 19; campo[1][3] = 19; campo[11][7] = 19; campo[9][9] = 19; itensAColocar[2] = 1; itensAColocar[5] = 1;
                    break;
                    case 7258: nivel = 3; campo[1][9] = 0; campo[11][1] = 0; campo[3][7] = 0; campo[5][3] = 0; campo[7][7] = 0;
                                          campo[6][5] = 0; campo[9][3] = 0; campo[1][3] = 19; campo[3][1] = 19; campo[11][7] = 19; itensAColocar[8] = 1;
                                          campo[9][9] = 19; campo[6][3] = 19; campo[6][7] = 19; itensAColocar[2] = 2; itensAColocar[5] = 2;
                    break;
                    case 8901: nivel = 4; podePlantas = false;
                    break;
                    
                }
                if(nivel != 5){itensAColocar[0] += 2; itensAColocar[3] += 2; itensAColocar[4] += 2;}
            }
            else{
                
                switch(codigo){
                    case 0:  addObject(new COGUMELO(), cord[3], cord[4]);
                                addObject(new COGUMELO(), cord[10], cord[3]); 
                                addObject(new COGUMELO(), 409+30, 346+30);   
                                addObject(new ZUMBIE(1,8,1), 31+30, 535+30);
                                addObject(new ZUMBIE(1,8,1), 535+30, 31+30);
                                addObject(new ZUMBIE(1,10,1), 283+30, 283+30); 
                    break;    
                    case 3512: addObject(new COGUMELO(), 94+30, 283+30); 
                               addObject(new COGUMELO(), cord[10], 283+30);
                               addObject(new COGUMELO(), 346+30, cord[3]);
                               addObject(new COGUMELO(), 346+30, 409+30);  
                               addObject(new ZUMBIE(1,5,1), 535+30, 409+30);
                               addObject(new ZUMBIE(1,5,7), 535+30, cord[3]);
                               addObject(new ZUMBIE(1,6,1), 409+30, 283+30);
                               addObject(new ZUMBIE(1,7,1), cord[3], 409+30);
                               addObject(new ZUMBIE(1,8,1), 283+30, 283+30);
                    break;
                    case 4798: addObject(new COGUMELO(), 94+30, 283+30);
                               addObject(new COGUMELO(), cord[10], 283+30); 
                               addObject(new GIRASSOL(1, 1), 409+30, 283+30);
                               addObject(new ZUMBIE(1,2,1), 31+30, 346+30); 
                               addObject(new ZUMBIE(1,3,1), 346+30, 283+30); 
                               addObject(new ZUMBIE(1,5,7), 31+30, 535+30); 
                               addObject(new ZUMBIE(1,5,7), 661+30, 31+30);
                               addObject(new ZUMBIE(1,7,7), 283+30, 31+30); 
                    break;
                    case 9674: addObject(new ZUMBIE(1,4,0), 283+30, cord[3]); 
                               addObject(new ZUMBIE(1,5,0), cord[4], 283+30); 
                               addObject(new ZUMBIE(1,3,0), 283+30, 409+30);
                               addObject(new COGUMELO(), 661+30, 31+30);
                               addObject(new COGUMELO(), 31+30, 535+30);
                               addObject(new ZUMBIE(2,7,4), 502, 61);
                               addObject(new ZUMBIE(2,8,4), 250, 565);
                             
                               
                    break;
                    case 2224:  
                               addObject(new ZUMBIE(1,5,8), 409+30, cord[3]); 
                               addObject(new ZUMBIE(1,7,8), 94+30, 409+30); 
                               addObject(new ZUMBIE(1,2,1), 409+30, 535+30);
                               addObject(new ZUMBIE(1,5,2), 661+30, 94+30);
                               addObject(new ZUMBIE(1,5,2), 283+30, 283+30);
                               addObject(new COGUMELO(), 472+30, 283+30);
                               addObject(new COGUMELO(), 94+30, 283+30);
                               addObject(new COGUMELO(), cord[4], 283+30);
                               addObject(new GIRASSOL(1, 2), 346+30, 283+30); 
                               
                               
                               
                    break;
                    case 7531: addObject(new ZUMBIE(1 ,4 , 8), cord[9],cord[1]); 
                               addObject(new ZUMBIE(1 ,4 , 8), cord[3],cord[9]); 
                               addObject(new ZUMBIE(1 ,7 , 7), cord[5],cord[3]); 
                               addObject(new ZUMBIE(1 ,4 , 2), cord[7],cord[6]); 
                               addObject(new COGUMELO(), cord[2],cord[5]);
                               addObject(new COGUMELO(), cord[6],cord[5]);
                               addObject(new COGUMELO(), cord[10],cord[5]);
                               addObject(new ESPINHOS(), cord[3], cord[2]); addObject(new ESPINHOS(), cord[3], cord[8]);
                               addObject(new ESPINHOS(), cord[4], cord[5]); addObject(new ESPINHOS(), cord[8], cord[5]);
                               addObject(new ESPINHOS(), cord[6], cord[3]); addObject(new ESPINHOS(), cord[6], cord[7]);
                               addObject(new ESPINHOS(), cord[9], cord[2]); addObject(new ESPINHOS(), cord[9], cord[8]);
                             
                    break;
                    
                    case 5300:  addObject(new ZUMBIE(1 ,4 , 8), cord[11],cord[1]); 
                                addObject(new ZUMBIE(1 ,4 , 8), cord[9],cord[6]); 
                                addObject(new ZUMBIE(1 ,4 , 8), cord[3],cord[4]); 
                                addObject(new ZUMBIE(1 ,4 , 8), cord[1],cord[9]); 
                                addObject(new ZUMBIE(1 ,4 , 2), cord[5],cord[6]); 
                                addObject(new ZUMBIE(1 ,4 , 2), cord[7],cord[4]);
                                addObject(new COGUMELO(), cord[3],cord[8]);
                                addObject(new COGUMELO(), cord[9],cord[2]);
                                addObject(new GIRASSOL(1, 8), cord[6], cord[5]);
                                addObject(new ESPINHOS(), cord[4], cord[3]); addObject(new ESPINHOS(), cord[5], cord[2]);
                                addObject(new ESPINHOS(), cord[7], cord[2]); addObject(new ESPINHOS(), cord[8], cord[3]);
                                addObject(new ESPINHOS(), cord[4], cord[7]); addObject(new ESPINHOS(), cord[5], cord[8]);
                                addObject(new ESPINHOS(), cord[7], cord[8]); addObject(new ESPINHOS(), cord[8], cord[7]);
                                
                                
                    break;
                    case 7577: addObject(new CHEFAO(1), cord[9],cord[6]);
                               addObject(new ESPINHOS(), cord[1], cord[1]); addObject(new ESPINHOS(), cord[1], cord[9]);
                               addObject(new ESPINHOS(), cord[5], cord[1]); addObject(new ESPINHOS(), cord[5], cord[9]);
                               addObject(new ESPINHOS(), cord[11], cord[1]); addObject(new ESPINHOS(), cord[11], cord[9]);
                               addObject(new PLANTA_BOMBA(1), cord[10],cord[3]); addObject(new PLANTA_BOMBA(1), cord[10],cord[7]);

                    break;  
                    case 2481: addObject(new MUMIA(2,3), cord[1],cord[9]);
                               addObject(new MUMIA(2,3), cord[3],cord[7]);
                               addObject(new MUMIA(2,3), cord[11],cord[1]);
                               addObject(new MUMIA(2,3), cord[9],cord[3]);
                               addObject(new COVA(), cord[5],cord[3]);
                    
                    break;
                    case 8422: addObject(new MUMIA(2,3), cord[9],cord[3]);
                               addObject(new MUMIA(2,3), cord[3],cord[7]);
                               addObject(new COVA(), cord[5],cord[4]);
                               addObject(new COVA(), cord[5],cord[6]);
                               addObject(new COVA(), cord[7],cord[4]);
                               addObject(new COVA(), cord[7],cord[6]);
                               addObject(new PLANTA_BOMBA(1), cord[6],cord[5]);
                               addObject(new COGUMELO(), cord[3],cord[3]);
                               addObject(new COGUMELO(), cord[9],cord[7]);
              
                    break;
                    case 2843: addObject(new MORTE(1,3), cord[11],cord[1]);
                               addObject(new MORTE(1,3), cord[7],cord[3]);
                               addObject(new MORTE(1,3), cord[1],cord[9]);
                               addObject(new MUMIA(2,3), cord[6],cord[5]);
                               addObject(new COGUMELO(), cord[4],cord[5]);
                               addObject(new COGUMELO(), cord[8],cord[5]);
                               addObject(new NEBLINA(90), 412,313);
                               
                    break;
                    case 2571: achouEstrela = 0;
                               colocaVasos();
                               addObject(new NEBLINA(160), 412,313);
                    break;
                    case 6221: addObject(new ZUMBIE(1 ,5 , 5), cord[8],cord[1]); 
                               addObject(new ZUMBIE(1 ,5 , 5), cord[11],cord[1]); 
                               addObject(new ZUMBIE(1 ,5 , 5), cord[6],cord[5]); 
                               addObject(new ZUMBIE(1 ,5 , 5), cord[1],cord[9]); 
                               addObject(new ZUMBIE(1 ,4 , 2), cord[6],cord[3]); 
                               addObject(new ZUMBIE(1 ,4 , 2), cord[6],cord[7]); 
                               addObject(new COGUMELO(), cord[3],cord[4]);
                               addObject(new COGUMELO(), cord[2],cord[5]);
                               addObject(new COGUMELO(), cord[4],cord[5]);
                               addObject(new COGUMELO(), cord[3],cord[6]);
                               addObject(new COGUMELO(), cord[9],cord[4]);
                               addObject(new COGUMELO(), cord[8],cord[5]);
                               addObject(new COGUMELO(), cord[10],cord[5]);
                               addObject(new COGUMELO(), cord[9],cord[6]);
                               addObject(new PLANTA_BOMBA(2), cord[3],cord[5]);
                               addObject(new PLANTA_BOMBA(2), cord[9],cord[5]);
                               addObject(new GIRASSOL(1, 5), cord[3],cord[2]);
                               addObject(new GIRASSOL(1, 5), cord[9],cord[8]);
                               addObject(new NEBLINA(90), 412,313);
            
                    break;
                    case 1015: addObject(new ZUMBIE(2 ,4 , 3), cord[4],cord[9]);
                               addObject(new ZUMBIE(2 ,5 , 3), cord[8],cord[1]);
                               addObject(new ZUMBIE(1 ,4 , 5), cord[1],cord[9]);
                               addObject(new ZUMBIE(1 ,5 , 5), cord[11],cord[1]);
                               addObject(new ZUMBIE(2 ,7 , 4), cord[6],cord[3]);
                               addObject(new ZUMBIE(2 ,8 , 4), cord[6],cord[7]);
                               addObject(new COGUMELO(), cord[3],cord[1]);
                               addObject(new COGUMELO(), cord[1],cord[3]);
                               addObject(new COGUMELO(), cord[11],cord[7]);
                               addObject(new COGUMELO(), cord[9],cord[9]);
                               addObject(new NEBLINA(160), 412,313);
                
                    break;
                    case 7258: addObject(new ZUMBIE(1 ,4 , 5), cord[5],cord[3]);
                               addObject(new ZUMBIE(2 ,5 , 3), cord[7],cord[7]);
                               addObject(new MUMIA(2,3), cord[6],cord[5]);
                               addObject(new COGUMELO(), cord[3],cord[1]);
                               addObject(new COGUMELO(), cord[1],cord[3]);
                               addObject(new COGUMELO(), cord[11],cord[7]);
                               addObject(new COGUMELO(), cord[9],cord[9]);
                               addObject(new PLANTA_BOMBA(1), cord[6],cord[7]);
                               addObject(new GIRASSOL(1, 1), cord[6],cord[3]);
                               addObject(new NEBLINA(250), 412,313);
                               addObject(new ZUMBIE(2 ,4 , 6), cord[11],cord[1]);
                               addObject(new ZUMBIE(2 ,5 , 6), cord[1],cord[9]);
                
                    break;
                    case 8901: addObject(new CHEFAO(0), cord[9],cord[6]);
                               addObject(new NEBLINA(250), 412,313);
                            
                    break;

                }  
        }
    }
    
    NUMEROS2 num5 = new NUMEROS2();
    NUMEROS num6 = new NUMEROS(true, true);
    
    NUMEROS num7 = new NUMEROS(true, true);
    NUMEROS num8 = new NUMEROS(true, true);
    NUMEROS num9 = new NUMEROS(true, true);
    NUMEROS num10 = new NUMEROS(true,true);
    
    boolean podePlantas;
    BOMBAS bombaLado = new BOMBAS(1,0,2);
    
    public TECLADO tecla1 = new TECLADO(1);
    public TECLADO tecla2 = new TECLADO(2);
    public TECLADO tecla3 = new TECLADO(3);
    public TECLADO tecla4 = new TECLADO(4);
       
    public void iniciaFase()
    {
        A = getObjects(BOTAO_MENU.class); removeObjects(A);
        B = getObjects(JOGO.class); removeObjects(B);
        C = getObjects(BOTAO_MUNDO.class); removeObjects(C);
        D = getObjects(INIMIGOS_E_FLORES.class); removeObjects(D);
        
        addObject(bombaLado, 810, 615);
        bombaLado.getImage().setTransparency(1);
        
        jogoPausado = false;
        dentroJogo = true;
        podePlantas = true;
        //zera todas as casas
        for(int i = 0; i < 13; i++){
            for(int j = 0; j < 11; j++){
                campo[i][j] = 0;
            }
        }
        
        switch(mundo){
            case 1:
                setBackground("FUNDO01.jpg"); 
            break;
            case 2:
                setBackground("FUNDO03.jpg"); 
            break;
            case 3:
                setBackground("FUNDO04.jpg"); 
            break;
            case 4:
                setBackground("FUNDO05.jpg"); 
            break;
        }
        
        if(codigo == 1111){
            codigo = 0;
            play1.NumeroBombas = 5; play1.TamanhoFogo = 5; play1.BombaQual = 3; play1.velo = 1; play1.M = 19;
            play2.NumeroBombas = 5; play2.TamanhoFogo = 5; play2.BombaQual = 3; play2.velo = 1; play2.M = 19;
        }
        
        ajeitaNivel(true);
        colocaPedras();
        if(podePlantas) ajeitaPlantas();
        ajeitaNivel(true);
        if(podePlantas) colocaPlantas();
        ajeitaNivel(false);
        if(podePlantas) colocaItens(itensAColocar);
        
        int a_, b_, c_;
        
        if(codigo == 7577 || codigo == 2571 || codigo == 8901){a_ = 4; b_ = 1; c_ = 6;}
        else{a_ = 1; b_ = 11; c_ = 9;}   
        
        if(play1.VIDA > 0) {addObject(play1, cord[1], cord[a_]);}
        if(play2.VIDA > 0) {if(numJogadores == 2){addObject(play2, cord[b_], cord[c_]);}}
        
        play1.getImage().setTransparency(140);
        play2.getImage().setTransparency(140);
        
        numBombas[0] = 0; numBombas[1] = 0; numBombas[2] = 0; //zera o numero de bombas!
        
        addObject(tecla1, 775, 180);
        for(int i_ = 0; i_ < play1.VIDA; i_++) addObject(vidinha[0][i_], 750+(i_*8), 110);
        
        if(numJogadores == 1) {addObject(tecla3, 785, 380);}
        if(numJogadores == 2) {addObject(tecla2, 775, 475); for(int i_ = 0; i_ < play2.VIDA; i_++) addObject(vidinha[1][i_], 750+(i_*8), 405);}
        
        addObject(tecla4, 775, 575);
        
        //coloca a os numeros da fase =D
        num5.valor = mundo;
        num6.valor = nivel;
        num7.valor = codigo/1000;
        num8.valor = (codigo%1000)/100;
        num9.valor = (codigo%100)/10;
        num10.valor = codigo%10;
        
        
        addObject(num5, 250+30, 200+30);
        addObject(num6, 380+30, 210+30);
        
        play1.jaEmbarcou = false;
        play2.jaEmbarcou = false;
                
        if(mundo != 1 || nivel != 1){
            addObject(num7, 280+30, 350+30);
            addObject(num8, 360+30, 350+30);
            addObject(num9, 440+30, 350+30);
            addObject(num10, 520+30, 350+30);
        }
    }
}
