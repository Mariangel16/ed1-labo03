package ed.lab;

import java.util.*;

public class E01TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        // 1) Contar frecuencias
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // 2) Crear buckets donde índice = frecuencia, cada bucket es lista de números
        @SuppressWarnings("unchecked")
        List<Integer>[] buckets = (List<Integer>[]) new List[nums.length + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            int num = e.getKey(), f = e.getValue();
            buckets[f].add(num);
        }

        // 3) Recorremos los buckets de mayor a menor frecuencia y extraemos k elementos
        int[] result = new int[k];
        int idx = 0;
        for (int i = buckets.length - 1; i >= 0 && idx < k; i--) {
            for (int num : buckets[i]) {
                result[idx++] = num;
                if (idx == k) break;
            }
        }

        return result;
    }
}
