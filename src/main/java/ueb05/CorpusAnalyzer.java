package ueb05;

import java.util.*;

class CorpusAnalyzer {
    private List<String> theses = new LinkedList<>();


    CorpusAnalyzer(Iterator<String> thesesIterator) {
        // TODO Alle Titel in die this.theses Liste übernehmen
        while (thesesIterator.hasNext()) { // пока есть следующий Эл.
            theses.add(thesesIterator.next()); // следующий элемент

        }
    }

    /**
     * Gibt die Anzahl der angefertigten Theses zurück
     */
    int countTheses() {
        return theses.size();
    }

    /**
     * Gibt die durchschnittliche Länge von Titeln in Worten zurück
     */
    int averageThesisTitleLength() {

        int len = 0;
        for (int i = 0; i < theses.size(); i++) {
            String s = theses.get(i); // Tesis in string
            String[] words = s.split(" "); // Tesis zerlegen auf Wörte
            len += words.length; // worte rechnen in Tesis


        }

        return len / theses.size();
    }

    /**
     * Gibt eine alphabetisch absteigend sortierte und duplikatfreie
     * Liste der ersten Wörter der Titel zurück.
     */
    List<String> uniqueFirstWords() {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < theses.size(); i++) {
            String words[] = theses.get(i).split(" ");
            set.add(words[0]); // first element auf die erste stelle
        }
        List<String> list = new LinkedList<>();
        list.addAll(set);
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) { // alphabetish sort (begin von Ende)
                return o2.compareTo(o1);
            }
        });
        return list;
    }

    /**
     * Gibt einen Iterator auf Titel zurück; dabei werden alle Woerter, welche
     * in `blackList` vorkommen durch Sternchen ersetzt (so viele * wie Buchstaben).
     */
    Iterator<String> censoredIterator(Set<String> blackList) {
        return new Iterator<String>() {
            Iterator<String> iterator = theses.iterator(); // Iterator auf Titel zurück

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public String next() {
                String s = iterator.next();
                for (String str : s.split(" ")) {
                    if (blackList.contains(str)) {
                        String ersetzen = "";
                        for (int i = 0; i < str.length(); i++) {
                            ersetzen += "*";

                        }
                        s = s.replaceAll(str, ersetzen);
                    }
                }
                return s;
            }
        };


    }

    /**
     * Gibt eine Liste von allen Titeln zurueck, wobei Woerter so ersetzt werden,
     * wie sie in der Map abgebildet werden.
     */
    List<String> normalizedTheses(Map<String, String> replace) {
        List<String> norm = new LinkedList<>();
        for (String s : theses) {
            for (Map.Entry<String, String> m : replace.entrySet()) // Worte von Map ableten
                s = s.replaceAll(m.getKey(), m.getValue());
            norm.add(s); // von alle Titel

        }
        norm.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o2.length(), o1.length());
            }
        });
        return norm;

}
}



