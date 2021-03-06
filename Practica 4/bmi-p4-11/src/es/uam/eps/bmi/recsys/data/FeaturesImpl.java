package es.uam.eps.bmi.recsys.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FeaturesImpl<F> implements Features<F>{

    protected Map<Integer,Map<F, Double>> features;  //Suponiendo que el id es la posicion en la lista
    private Parser<F> parser;

    public FeaturesImpl(String dataPath, String separator, Parser<F> parser){
        features = new HashMap<>();

        if (dataPath!=null && parser!=null){
            this.parser=parser;
            try {
                for (String line: Files.readAllLines(Paths.get(dataPath))){
                    readLine(line.split(separator));
                }
            } catch(IOException e){
                System.out.print("Error al abrir el fichero de grafos");
                e.printStackTrace();
            }
        }
    }

    /**
     * @param info
     */
    private void readLine(String[] info) throws IOException {
        if (info.length != 3)
            throw new IOException("Linea con formato incorrecto");
        setFeature(Integer.valueOf(info[0]), parser.parse(info[1]), Double.valueOf(info[2]));
    }

    @Override
    public Set getFeatures(int id) {
        if (features.containsKey(id))
            return new HashSet<>(features.get(id).keySet());
        return null;
    }

    @Override
    public Double getFeature(int id, F feature) {
        if (features.containsKey(id) && features.get(id).containsKey(feature))
            return features.get(id).get(feature);
        return null;
    }

    @Override
    public void setFeature(int id, F feature, double value) {
        if(features.containsKey(id)) {      //El item esta en el map
            if (features.get(id).containsKey(feature))
                features.get(id).replace(feature, value);
            else
                features.get(id).put(feature, value);
        }else{                              //El item NO esta en el map
            Map <F, Double> map = new HashMap<>();
            map.put(feature, value);
            features.put(id, map);
        }
    }

    @Override
    public Set<Integer> getIDs() {
        return new HashSet<>(features.keySet());
    }
}
