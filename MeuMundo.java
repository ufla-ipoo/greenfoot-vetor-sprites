import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Mundo simples criado apenas para mostrar exemplo de vetores.
 * 
 * @author Julio César Alves
 * @version 2023.10.09
 */
public class MeuMundo extends World
{

    /**
     * Constroi um objeto MeuMundo
     * 
     */
    public MeuMundo()
    {    
        // Cria um novo mundo com 800x600 células,
        // com células de tamanho 1x1 pixels.
        super(800, 600, 1); 
        GreenfootImage imagemFundoPreto = new GreenfootImage(800, 600);
        imagemFundoPreto.setColor(Color.BLACK);
        imagemFundoPreto.fillRect(0, 0, 800, 600);
        setBackground(imagemFundoPreto);
        prepare();
    }
    /**
     * Prepara o mundo para o início do programa.
     * Ou seja: criar os objetos iniciais e adicioná-los ao mundo.
     */
    private void prepare()
    {
        Cavaleiro cavaleiro = new Cavaleiro();
        addObject(cavaleiro,129,455);
    }
}
