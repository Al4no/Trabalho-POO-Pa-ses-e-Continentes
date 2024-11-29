package continentes_paises;

import java.util.ArrayList;
import java.util.List;

public class Pais {
    private String codigoISO;
    private String nome;
    private long populacao;
    private double dimensao; // em Km2
    private List<Pais> fronteiras;

    public Pais(String codigoISO, String nome, double dimensao) {
        this.codigoISO = codigoISO;
        this.nome = nome;
        this.dimensao = dimensao;
        this.fronteiras = new ArrayList<>();
    }

    public String getCodigoISO() {
        return codigoISO;
    }

    public void setCodigoISO(String codigoISO) {
        this.codigoISO = codigoISO;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getPopulacao() {
        return populacao;
    }

    public void setPopulacao(long populacao) {
        this.populacao = populacao;
    }

    public double getDimensao() {
        return dimensao;
    }

    public void setDimensao(double dimensao) {
        this.dimensao = dimensao;
    }

    public void adicionarFronteira(Pais pais) {
        if (!fronteiras.contains(pais) && fronteiras.size() < 40) {
            fronteiras.add(pais);
            pais.fronteiras.add(this); // Adicionar este país à lista de fronteiras do outro país
        }
    }

    public boolean equals(Pais outro) {
        return this.codigoISO.equals(outro.getCodigoISO());
    }

    public boolean eLimitrofe(Pais outro) {
        return fronteiras.contains(outro);
    }

    public double densidadePopulacional() {
        return populacao / dimensao;
    }

    public List<Pais> vizinhosComuns(Pais outro) {
        List<Pais> comuns = new ArrayList<>(this.fronteiras);
        comuns.retainAll(outro.fronteiras);
        return comuns;
    }
}
