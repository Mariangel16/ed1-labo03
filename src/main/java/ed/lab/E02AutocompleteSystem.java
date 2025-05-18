package ed.lab;

import java.util.*;

public class E02AutocompleteSystem {
    // Mapa oración → frecuencia
    private final Map<String, Integer> freq;
    // Construye el prefijo según los caracteres introducidos
    private final StringBuilder prefix;

    public E02AutocompleteSystem(String[] sentences, int[] times) {
        freq = new HashMap<>();
        prefix = new StringBuilder();
        // Inicializar mapa con las oraciones y sus frecuencias
        for (int i = 0; i < sentences.length; i++) {
            freq.put(sentences[i],
                    freq.getOrDefault(sentences[i], 0) + times[i]);
        }
    }

    public List<String> input(char c) {
        // Si es '#', finaliza la cadena actual
        if (c == '#') {
            String completed = prefix.toString();
            freq.put(completed,
                    freq.getOrDefault(completed, 0) + 1);
            // reset prefix
            prefix.setLength(0);
            return Collections.emptyList();
        }

        // Añadir el carácter al prefijo
        prefix.append(c);
        String pre = prefix.toString();

        // 1) Filtrar todas las oraciones que empiezan por el prefijo
        List<String> candidates = new ArrayList<>();
        for (String s : freq.keySet()) {
            if (s.startsWith(pre)) {
                candidates.add(s);
            }
        }

        // 2) Ordenar por frecuencia descendente y lexicográficamente ascendente
        candidates.sort((a, b) -> {
            int fa = freq.get(a), fb = freq.get(b);
            if (fa != fb) {
                return Integer.compare(fb, fa);
            }
            return a.compareTo(b);
        });

        // 3) Devolver las primeras 3 (o menos si no hay suficientes)
        List<String> result = new ArrayList<>();
        for (int i = 0; i < Math.min(3, candidates.size()); i++) {
            result.add(candidates.get(i));
        }
        return result;
    }
}
