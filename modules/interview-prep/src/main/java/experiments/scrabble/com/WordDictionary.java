package experiments.scrabble.com;

import java.util.HashSet;
import java.util.List;

public class WordDictionary{

    public final HashSet<String> words;

    public WordDictionary(List<String> words){
        this.words = new HashSet<>(words);
    }

    
}
