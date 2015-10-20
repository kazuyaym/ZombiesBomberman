import greenfoot.*;
import java.util.List;

public class BOTAO_MENU extends BOTOES_E_NUMEROS
{
    public int IDENTIF, contador = 5;
    public boolean podeDeletar = false;
    
    public static int telaInstrucoes = 0;
    
    
    
    public BOTAO_MENU (int i)
    {
        IDENTIF = i;
        
        switch(i){
            case 1: //portugues
                setImage("BOT_LIN_10.png");
            break;
            case 2: //ingles
                setImage("BOT_LIN_20.png");
            break;
            case 3: //iniciar jogo
                if(((MENU)getWorld()).English) {setImage("BOT01_01EN.png");}
                else {setImage("BOT01_01.png");}
            break;
            case 4: //creditos
                if(((MENU)getWorld()).English) {setImage("BOT04_01EN.png");}
                else {setImage("BOT04_01.png");}
            break;
            case 5: //codigo
                setImage("BOT03_01EN.png");
            break;
            case 6: 
                
            break;
            case 7: //intru 1
                if(((MENU)getWorld()).English) {setImage("COMO_JOGAR_01.png");}
                else {setImage("COMO_JOGAR_01_PT.png");}
            break;
            case 8: //instru 2
            
            break;
            case 9: //codigooo
                setImage("CODIGO.png");
            break;
            case 10: //creditos
                if(((MENU)getWorld()).English) {setImage("CREDITOS_EN.png");}
                else {setImage("CREDITOS_PT.png");}
            break;
            case 11:
                setImage("SETA.png");
            break;
            case 12:
                setImage("OKAY.png");
            break;
            case 14:
                setImage("PLAYER01-01.png");
            break;
            case 15:
                setImage("PLAYER02-01.png");
            break;
            case 16:
                if(((MENU)getWorld()).numJogadores == 1) {setImage("FOGUETE01.png");}
                else {setImage("FOGUETE02.png");}   
            break;
            case 17:
                setImage("FOGUETE03.png");
            break;
            
            case 18:
                if(((MENU)getWorld()).English) {setImage("paused_en.png");}
                else {setImage("paused_pt.png");}
            break;
            case 19:
                if(((MENU)getWorld()).English) {setImage("gameover_en.png");}
                else {setImage("gameover_pt.png");}
            break;
            
            case 20:
                if(((MENU)getWorld()).English) {setImage("vj_en_01.png");}
                else {setImage("vj_pt_01.png");}
            break;
            case 21:
                if(((MENU)getWorld()).English) {setImage("me_en_01.png");}
                else {setImage("me_pt_01.png");}
            break;
        }
    }

    public boolean mouseEncima = false;
    
    public void act() 
    {
        efeitoMouse();
        mouseEncima = false;
        
        if(IDENTIF == 11) if(getY() < 490+30) {setLocation(getX(), getY()+17);}
        if(IDENTIF == 16) {efeitoFoguete();}
        if(IDENTIF == 17) {efeitoFoguete17();}
        if(podeDeletar){contador--; setLocation(getX(), getY()+5);  if(contador == 0){delete();}}
        
    }    
    
    BOTAO_MUNDO obejtoMUNDO;
    
    public void efeitoMouse()
    {
        if(mouseEncima){
            switch(IDENTIF){
                case 1:
                    setImage("BOT_LIN_11.png");
                break;
                case 2:
                    setImage("BOT_LIN_21.png");
                break; 
                case 3:
                    if(((MENU)getWorld()).English) {setImage("BOT01_02EN.png");}
                    else {setImage("BOT01_02.png");}
                break;
                case 4:
                    if(((MENU)getWorld()).English) {setImage("BOT04_02EN.png");}
                    else {setImage("BOT04_02.png");}
                break;
                case 5:
                    setImage("BOT03_02EN.png");
                break;
                case 6:
                    
                break; 
                case 14:
                    setImage("PLAYER01-00.png");
                break;
                case 15:
                    setImage("PLAYER02-00.png");
                break;
                
                case 20:
                    if(((MENU)getWorld()).English) {setImage("vj_en_02.png");}
                    else {setImage("vj_pt_02.png");}
                break;
                case 21:
                    if(((MENU)getWorld()).English) {setImage("me_en_02.png");}
                    else {setImage("me_pt_02.png");}
                break;
            }
        }
        else {
            switch(IDENTIF){
                case 1:
                    setImage("BOT_LIN_10.png");
                break;
                case 2:
                    setImage("BOT_LIN_20.png");
                break;
                case 3:
                    if(((MENU)getWorld()).English) {setImage("BOT01_01EN.png");}
                    else {setImage("BOT01_01.png");}
                break;
                case 4:
                    if(((MENU)getWorld()).English) {setImage("BOT04_01EN.png");}
                    else {setImage("BOT04_01.png");}
                break;
                case 5:
                    setImage("BOT03_01EN.png");     
                break;
                case 6:
                    
                break;
                case 14:
                    setImage("PLAYER01-01.png");
                break;
                case 15:
                    setImage("PLAYER02-01.png");
                break;
                
                case 20:
                    if(((MENU)getWorld()).English) {setImage("vj_en_01.png");}
                    else {setImage("vj_pt_01.png");}
                break;
                case 21:
                    if(((MENU)getWorld()).English) {setImage("me_en_01.png");}
                    else {setImage("me_pt_01.png");}
                break;
                
                
            }
        }
        
        if(Greenfoot.mouseClicked(this)){
            switch(IDENTIF){
                case 1:
                    ((MENU)getWorld()).English = false;
                    ((MENU)getWorld()).voltaMenuNormal();
                break;
                case 2:
                    ((MENU)getWorld()).English = true;
                    ((MENU)getWorld()).voltaMenuNormal();  
                break; 
                case 3: //iniciar
                    ((MENU)getWorld()).zerarPlayer();
                    ((MENU)getWorld()).codigo = 0;
                    ((MENU)getWorld()).confereCodigo();

                break;
                case 4:
                    telaInstrucoes = 1;
                    ((MENU)getWorld()).addObject(new BOTAO_MENU(10), 430+30, 360+30);
                    ((MENU)getWorld()).addObject(new BOTAO_MENU(11), 625+30, 1+30);
                break;
                case 5:
                    ((MENU)getWorld()).addObject(new BOTAO_MENU(9), 382+30, 270+30);

                    ((MENU)getWorld()).adicionaNumeroCodigo();
                    
                    ((MENU)getWorld()).addObject(new BOTAO_MAIS_MENOS(true, 4), 158+30, 210+30);
                    ((MENU)getWorld()).addObject(new BOTAO_MAIS_MENOS(true, 3), 275+30, 210+30);
                    ((MENU)getWorld()).addObject(new BOTAO_MAIS_MENOS(true, 2), 392+30, 210+30);
                    ((MENU)getWorld()).addObject(new BOTAO_MAIS_MENOS(true, 1), 509+30, 210+30);
                    
                    ((MENU)getWorld()).addObject(new BOTAO_MAIS_MENOS(false, 4), 158+30, 410+30);
                    ((MENU)getWorld()).addObject(new BOTAO_MAIS_MENOS(false, 3), 275+30, 410+30);
                    ((MENU)getWorld()).addObject(new BOTAO_MAIS_MENOS(false, 2), 392+30, 410+30);
                    ((MENU)getWorld()).addObject(new BOTAO_MAIS_MENOS(false, 1), 509+30, 410+30);
                    
                    ((MENU)getWorld()).addObject(new BOTAO_MENU(12), 600+30, 460+30);
                    
                break;
                case 6:
                    
                break;
                case 11:
                    verificaOutros();

                    if(telaInstrucoes == 1){
                        ((MENU)getWorld()).addObject(new BOTAO_MENU(7), 382+30, 283+30);
                        ((MENU)getWorld()).addObject(new BOTAO_MENU(11), 660+30, 1+30);
                        telaInstrucoes = 2;
                    }
                    
                    podeDeletar = true;
                break;
                case 12:
                    ((MENU)getWorld()).zerarPlayer();
                    ((MENU)getWorld()).codigo = 
                    ((MENU)getWorld()).num4.valor*1000 + ((MENU)getWorld()).num3.valor*100 + ((MENU)getWorld()).num2.valor*10 + ((MENU)getWorld()).num1.valor;
                    
                    ((MENU)getWorld()).confereCodigo();
                break; 
                case 14:
                    ((MENU)getWorld()).numJogadores = 1;
                    obejtoMUNDO = (BOTAO_MUNDO) getOneIntersectingObject(BOTAO_MUNDO.class) ;
                    obejtoMUNDO.deletaFundo();
                    ((MENU)getWorld()).colocaFoguete();
                break;
                case 15:
                    ((MENU)getWorld()).numJogadores = 2;
                    obejtoMUNDO = (BOTAO_MUNDO) getOneIntersectingObject(BOTAO_MUNDO.class); 
                    obejtoMUNDO.deletaFundo();
                    ((MENU)getWorld()).colocaFoguete();
                break;
                
                case 20:
                    ((MENU)getWorld()).jogoPausado = false;
                    List despausar = ((MENU)getWorld()).getObjects(BOTOES_E_NUMEROS.class);
                    ((MENU)getWorld()).removeObjects(despausar);
                break;
                case 21:
                    ((MENU)getWorld()).voltaMenuNormal();
                break;
            }
        }
    }
    
    Actor voltar;
    
    public void verificaOutros()
    {
        // Botão voltar! 
        voltar = getOneObjectAtOffset(1, 1, BOTAO_MENU.class);
                if(voltar != null){
                    BOTAO_MENU tirar = (BOTAO_MENU) getOneIntersectingObject(BOTAO_MENU.class);
                    tirar.delete();
                }
    }
    
    public int y = -7, outroContador = 20;
    public Actor objetoMundinho;
    
    public void efeitoFoguete()
    {
        if(outroContador == 0){y++; outroContador = 5;}
        outroContador--;
        setLocation(getX()-5, getY()+y);
        
        if(((MENU)getWorld()).nivel == 4) ((MENU)getWorld()).nivel -= 6;
        
        ((MENU)getWorld()).play1.getImage().setTransparency(15);
        if(((MENU)getWorld()).numJogadores == 2) ((MENU)getWorld()).play2.getImage().setTransparency(15);
        
        if(((MENU)getWorld()).nivel <= 0){
            objetoMundinho = getOneObjectAtOffset(20,0,BOTAO_MUNDO.class);
            if(objetoMundinho != null && !((MENU)getWorld()).dentroJogo){
                BOTAO_MUNDO mundoinho = (BOTAO_MUNDO) getOneIntersectingObject(BOTAO_MUNDO.class);
                mundoinho.fogueteChegou();
            }
            else if(getX() <= 35){
                if(((MENU)getWorld()).precisaMudarMundo) {((MENU)getWorld()).mundo++; ((MENU)getWorld()).MENU_MUNDOS();}
                else if(((MENU)getWorld()).nivel != -2) {((MENU)getWorld()).iniciaFase();}
                else ((MENU)getWorld()).voltaMenuNormal();
            }
        }
    }
    

    
    public void efeitoFoguete17()
    {
        if(getX() != 457+30) setLocation(getX()-1, getY()+1);
    }
    
    
    public void delete()
    {
        getWorld().removeObject(this);   
    }
}
