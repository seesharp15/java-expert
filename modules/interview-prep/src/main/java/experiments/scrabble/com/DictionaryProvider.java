package experiments.scrabble.com;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DictionaryProvider {

    public WordDictionary getDictionary(){
        try {
            var words = Files.readAllLines(
                    Paths.get("src/main/java/experiments/scrabble/data/dictionary.txt")
                         .toAbsolutePath());
            return new WordDictionary(words);
        }catch (Exception e){
            return new WordDictionary(List.of());
        }
    }
}
