package continentes_paises;

import java.util.ArrayList;
import java.util.List;

public class Continente {
    private String nome;
    private List<Pais> paises;

    public Continente(String nome) {
        this.nome = nome;
        this.paises = new ArrayList<>();
    }

    public void adicionarPais(Pais pais) {
        paises.add(pais);
    }

    public double dimensaoTotal() {
        return paises.stream().mapToDouble(Pais::getDimensao).sum();
    }

    public long populacaoTotal() {
        return paises.stream().mapToLong(Pais::getPopulacao).sum();
    }

    public double densidadePopulacional() {
        return paises.isEmpty() ? 0 : populacaoTotal() / dimensaoTotal();
    }

    public Pais paisMaiorPopulacao() {
        if (paises.isEmpty()) return null;
        return paises.stream().max((p1, p2) -> Long.compare(p1.getPopulacao(), p2.getPopulacao())).orElse(null);
    }

    public Pais paisMenorPopulacao() {
        if (paises.isEmpty()) return null;
        return paises.stream().min((p1, p2) -> Long.compare(p1.getPopulacao(), p2.getPopulacao())).orElse(null);
    }

    public Pais paisMaiorDimensao() {
        if (paises.isEmpty()) return null;
        return paises.stream().max((p1, p2) -> Double.compare(p1.getDimensao(), p2.getDimensao())).orElse(null);
    }

    public Pais paisMenorDimensao() {
        if (paises.isEmpty()) return null;
        return paises.stream().min((p1, p2) -> Double.compare(p1.getDimensao(), p2.getDimensao())).orElse(null);
    }

    public double razaoTerritorial() {
        Pais maior = paisMaiorDimensao();
        Pais menor = paisMenorDimensao();
        if (maior != null && menor != null && menor.getDimensao() > 0) {
            return maior.getDimensao() / menor.getDimensao();
        }
        return 0.0;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public String getNome() {
        return nome;
    }
}


