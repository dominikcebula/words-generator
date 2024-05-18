package com.dominikcebula.words.generator.application.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordKeyFactory {
    public WordKey createFromWord(String word) {
        Map<Character, Long> characterCountMap = getCharacterCountMap(word);
        Map<Character, Long> characterCountMapSortedByKey = getCharacterCountMapSortedByKey(characterCountMap);
        String wordKey = getWordKey(characterCountMapSortedByKey);

        return new WordKey(wordKey);
    }

    private Map<Character, Long> getCharacterCountMap(String word) {
        return word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
    }

    private Map<Character, Long> getCharacterCountMapSortedByKey(Map<Character, Long> characterCountMap) {
        return characterCountMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    private String getWordKey(Map<Character, Long> characterCountMapSortedByKey) {
        return characterCountMapSortedByKey.entrySet()
                .stream().map(
                        entry -> String.format("%d%c", entry.getValue(), entry.getKey())
                )
                .collect(Collectors.joining());
    }
}
