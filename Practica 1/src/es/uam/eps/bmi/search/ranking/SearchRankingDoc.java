package es.uam.eps.bmi.search.ranking;

import org.apache.lucene.search.ScoreDoc;

public class SearchRankingDoc implements Comparable<SearchRankingDoc> {

    private ScoreDoc scoreDoc;
    private String ruta;

    public SearchRankingDoc(ScoreDoc score, String ruta) {
        this.scoreDoc = score;
        this.ruta = ruta;
    }

    @Override
    public int compareTo(SearchRankingDoc o) {
        int pos = Double.compare(this.scoreDoc.score, o.scoreDoc.score);

        if(pos!=0)return pos;

        return Integer.compare(scoreDoc.doc,scoreDoc.doc);

    }

    @Override
    public String toString() {
        return  scoreDoc.score + "\t" + ruta ;
    }
}