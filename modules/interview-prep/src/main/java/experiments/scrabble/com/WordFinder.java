package experiments.scrabble.com;
import java.util.*;

public class WordFinder {

    private final HashMap<String, List<String>> dictMap;

    public WordFinder(DictionaryProvider provider) {
        WordDictionary dictionary = provider.getDictionary();
        dictMap = mapify(dictionary.words);
    }

    private static HashMap<String, List<String>> mapify(HashSet<String> dict) {
        var map = new HashMap<String, List<String>>();
        for(var word: dict) {
            var chars = word.toCharArray();
            Arrays.sort(chars);
            var normalized = new String(chars);
            map.putIfAbsent(normalized, new ArrayList<>());
            map.get(normalized).add(word);

        }
        return map;
    }

    public Set<String> findWords(char[] letters) {
        var combined = new String(letters);
        var cleaned = combined.replaceAll("\\*", "");
        var wildcards = combined.length() - cleaned.length();

        var wordDict = new HashSet<String>();
        wordDict.add(cleaned);

        var combinations = allAlphaCombos(wildcards);
        for(var combo: combinations) {
            wordDict.add(cleaned+combo);
        }

        var result = new HashSet<String>();
        var available = mapify(wordDict);

        for (var c: available.entrySet()) {
            var word = c.getKey();
            var keyChars = word.toCharArray();

            var perms = permutationsAllLengths(keyChars);
            for(var perm: perms) {
                if (dictMap.containsKey(perm)) {
                    result.addAll(dictMap.get(perm));
                }
            }
        }
        return result;
    }

    public static List<String> allAlphaCombos(int n) {
        char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        List<String> out = new ArrayList<>();
        build(n, new StringBuilder(n), letters, out);
        return out;
    }

    private static void build(int remaining, StringBuilder sb, char[] letters, List<String> out) {
        if (remaining == 0) {
            out.add(sb.toString());
            return;
        }
        for (char c : letters) {
            sb.append(c);
            build(remaining - 1, sb, letters, out);
            sb.setLength(sb.length() - 1);
        }
    }

    public static List<String> permutationsAllLengths(char[] chars) {
        List<String> out = new ArrayList<>();
        boolean[] used = new boolean[chars.length];
        backtrack(chars, used, new StringBuilder(), out);
        return out;
    }

    private static void backtrack(char[] chars, boolean[] used, StringBuilder path, List<String> out) {
        if (path.length() > 0) out.add(path.toString()); // collect current length
        if (path.length() == chars.length) return;

        for (int i = 0; i < chars.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            path.append(chars[i]);
            backtrack(chars, used, path, out);
            path.setLength(path.length() - 1);
            used[i] = false;
        }
    }
}

