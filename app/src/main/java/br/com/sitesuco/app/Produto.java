package br.com.sitesuco.app;

/**
 * Created by 16254866 on 29/11/2017.
 */

public class Produto {

    int id;
    String caminhoIMG;
    String nome;
    String descricao;
    String preco;
    String detalhes;
    Float avalicao;


    public static Produto create(String nome, String caminhoIMG, String descricao,String detalhes, String preco, int id,Float Avaliacao)
    {

        Produto p = new Produto();
        p.setId(id);
        p.setNome(nome);
        p.setCaminhoIMG(caminhoIMG);
        p.setDescricao(descricao);
        p.setDetalhes(detalhes);
        p.setPreco(preco);
        p.setAvalicao(Avaliacao);
        return p;
    }

    public Float getAvalicao() {
        return avalicao;
    }

    public void setAvalicao(Float avalicao) {
        this.avalicao = avalicao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaminhoIMG() {
        return caminhoIMG;
    }

    public void setCaminhoIMG(String caminhoIMG) {
        this.caminhoIMG = caminhoIMG;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }


}
