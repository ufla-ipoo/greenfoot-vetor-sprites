import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe que representa um Cavaleiro.
 * Usada como exemplo de vetores e animação de sprites.
 * 
 * @author Julio César Alves
 * @version 2023.10.09
 */
public class Cavaleiro extends Actor
{
    // declaramos um vetor de imagens parado olhando para direita
    private GreenfootImage[] imagensParadoDireita;
    // declaramos um vetor de imagens parado olhando para esquerda
    private GreenfootImage[] imagensParadoEsquerda;
    // declaramos um vetor de imagens caminhando para a direita
    private GreenfootImage[] imagensCaminhandoDireita;
    // declaramos um vetor de imagens caminhando para a esquerda
    private GreenfootImage[] imagensCaminhandoEsquerda;
    // guarda o índice da imagem que está sendo utilizada no momento
    private int indiceImagemAtual;
    // indica quantos passos são necessários para atualizar a imagem
    private int passosParaAtualizarImagem;
    // indica quantos passos se passaram desde a última atualização de imagem
    private int passosDesdeUltimaAtualizacaoImagem;
    // indica a ação atual do cavaleiro ("parado" ou "caminhando para direita" ou "caminhando para esquerda")
    private String acaoAtual;
    
    /**
     * Cria um objeto cavaleiro. Carrega as imagens e define a imagem inicial.
     */
    public Cavaleiro() {
        // As imagens serão atualizadas a cada 4 passos
        // (ou seja, a cada 4 chamadas do método act)
        passosParaAtualizarImagem = 4;
        // consideramos que a última atualização de imagem aconteceu agora
        passosDesdeUltimaAtualizacaoImagem = 0;
        
        // carregamos as imagens do personagem parado olhando para a direita
        imagensParadoDireita = carregarImagens("Idle", 10);
        // carregamos as imagens do personagem caminhando para a direita
        imagensCaminhandoDireita = carregarImagens("Walk", 10);
        
        // construímos imagens invertidas para o personagem olhando para a esquerda
        imagensParadoEsquerda = copiaImagensInvertidasHorizontalmente(imagensParadoDireita);
        imagensCaminhandoEsquerda = copiaImagensInvertidasHorizontalmente(imagensCaminhandoDireita);
        // as imagens do cavaleiro caminhando para direita, são as mesmas
        // mas invertidas horizontalmente. O loop abaixo cria cópia das imagens
        // caminhando para a direita e as inverte.
        
        // definimos o cavaleiro começa parado olhando para a direita e escolhemos a primeira 
        // imagem parada como a imagem inicial do cavaleiro
        acaoAtual = "parado direita";
        indiceImagemAtual = 0;
        setImage(imagensParadoDireita[indiceImagemAtual]);        
    }
    
    /**
     * Carrega um conjunto de imagens com mesmo prefixo de arquivo.
     * O método carrega imagens no formato: prefixo (1).png, prefixo (2).png, etc.
     * 
     * @param prefixo Prefixo dos arquivos (ex.: 'Idle', 'Walk', etc.).
     * @param numeroDeImagens Quantidade de imagens a serem carregadas
     */
    private GreenfootImage[] carregarImagens(String prefixo, int numeroDeImagens) {
        // criamos um vetor de imagens
        GreenfootImage[] imagens = new GreenfootImage[numeroDeImagens];
        
        // usamos um loop for que carregará uma imagem de cada vez
        for (int i = 0; i < imagens.length; i++) {
            // em cada iteração, carregamos uma imagem usando o prefixo
            // e o valor i+1 para montar o nome da imagem.
            // e a guardamos na posição i do vetor.
            imagens[i] = new GreenfootImage(prefixo + " (" + (i+1) + ").png");
            // redimensionamos a imagem porque ela é muito grande.
            imagens[i].scale(120,140);
        }
        
        return imagens;
    }
    
    /**
     * Copia as imagens do vetor passado e retorna um vetor com as imagens invertidas
     * horizontalmente
     */
    private GreenfootImage[] copiaImagensInvertidasHorizontalmente(GreenfootImage[] imagens) {
        GreenfootImage[] imagensInvertidas = new GreenfootImage[imagens.length];
        for (int i = 0; i < imagensInvertidas.length; i++) {
            imagensInvertidas[i] = new GreenfootImage(imagens[i]);
            imagensInvertidas[i].mirrorHorizontally();
        }
        return imagensInvertidas;
    }
    
    /**
     * Trata o movimento do cavaleiro. Ele pode ser movimentar para a direta e para 
     * a esquerda. Atualiza tanto a posição do cavaleiro quanto a 'acaoAtual'.
     *
     */
    private void tratarMovimento() {
        // se a tecla seta para a ESQUERDA estiver pressionada
        if (Greenfoot.isKeyDown("left")) {
            // posição do cavaleiro DIMINUI 4 no eixo X e se mantém no eixo Y
            setLocation(getX()-4, getY());            
            // cavaleiro está caminhando para a esquerda
            acaoAtual = "caminhando esquerda";
        }
        // senão se a tecla seta para a DIREITA estiver pressionada
        else if (Greenfoot.isKeyDown("right")) {
            // posição do cavaleiro AUMENTA 4 no eixo X e se mantém no eixo Y
            setLocation(getX()+4, getY());            
            // cavaleiro está caminhando para a direita
            acaoAtual = "caminhando direita";
        }
        else { // se o cavaleiro não está se movimentando ...
            // e estava caminhando para a direita, agora está parado olhando para a direita
            if (acaoAtual.equals("caminhando direita")) {
                acaoAtual = "parado direita";    
            }
            // já se estava caminhando para a esquerda, agora está parado olhando para a esquerda
            else if (acaoAtual.equals("caminhando esquerda")) {
                acaoAtual = "parado esquerda";
            }
        }
        
    }
    
    /**
     * Retorna o vetor de imagens correspondente à ação atual do cavaleiro.
     * Retorna null se é uma ação desconhecida.
     */
    private GreenfootImage[] obterImagensAcaoAtual() {
        if (acaoAtual.equals("parado direita")) {
            return imagensParadoDireita;
        }
        else if (acaoAtual.equals("parado esquerda")) {
            return imagensParadoEsquerda;
        }
        else if (acaoAtual.equals("caminhando direita")) {
            return imagensCaminhandoDireita;
        }
        else if (acaoAtual.equals("caminhando esquerda")) {
            return imagensCaminhandoEsquerda;
        }
        else {
            return null;
        }
    }
    
    /**
     * Trata a atualização da imagem do cavaleiro. Basicamente exibe a próxima
     * imagem do vetor de imagens da ação do cavaleiro a cada X passos (onde
     * X é guardado no atributo 'passosParaAtualizarImagem').
     */
    private void tratarAtualizacaoImagem() {
        // contamos mais um passo desde a última atualização da imagem
        passosDesdeUltimaAtualizacaoImagem++;
        
        // se já se passaram os passos necessários para atualizar a imagem
        if (passosDesdeUltimaAtualizacaoImagem >= passosParaAtualizarImagem) {
            // a imagem será atualizada, então voltamos o valor da variável para zero
            passosDesdeUltimaAtualizacaoImagem = 0;
            
            // obtém o vetor de imagens da ação atual
            GreenfootImage[] imagens = obterImagensAcaoAtual();
            
            // se o vetor foi obtido
            if (imagens != null) {
                // incrementamos o índice da imagem atual para que
                // possamos usar a próxima imagem.
                indiceImagemAtual++;
                // mas se o índice chegou ao final do vetor, voltamos para a primeira posição
                if (indiceImagemAtual >= imagens.length) {
                    indiceImagemAtual = 0;
                }
                // usaremos então a próxima imagem, como imagem do cavaleiro
                setImage(imagens[indiceImagemAtual]);
            }
        }
    }
    
    /**
     * Act - fazo que o Cavaleiro quer fazer. Este método é chamado sempre 
     * que o botão 'Executar uma vez' ou 'Executar' é clicado.
     */
    public void act()
    {        
        tratarMovimento();
        tratarAtualizacaoImagem();        
    }
}
