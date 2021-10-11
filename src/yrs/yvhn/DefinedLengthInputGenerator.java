package yrs.yvhn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Генератор слів заданої довжини з вхідного алфавіту символів
 */
public class DefinedLengthInputGenerator {

    // Згенеровані слова
    private AbstractList<String> _words = new ArrayList<>();

    // Довжина генерованих слів
    private final int _length;
    // Алфавіт символів
    private final String _alphabet;
    // Максимальна кількість згенерованих слів
    private final int _wordsCount;

    // Номер наступного слова, згенерованого функцією GetNextWord()
    private int _currentIndex = 0;

    /**
     * Конструктор із даних вхідного текстового файлу
     * @param fileName Назва файлу
     * @throws IOException Помилка читання файлу
     */
    public DefinedLengthInputGenerator(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader reader1 = new FileReader(file);
        BufferedReader reader = new BufferedReader(reader1);

        _length = Integer.parseInt(reader.readLine());
        _alphabet = reader.readLine();
        _wordsCount = (int)Math.pow(_alphabet.length(), _length);

        reader.close();
    }

    /**
     * Згенерувате наступне слово
     * @return Згенероване слово
     */
    public String GetNextWord() {
        if (_currentIndex >= _wordsCount) return null;

        return GenerateWord(_currentIndex++, _alphabet, _length);
    }

    /**
     * Генерування слів заданої довжини з вхідного алфавіту
     * @return Набір згенерованих слів
     */
    public AbstractList<String> Generate() {
        if (_alphabet.length() < _length) throw new IllegalArgumentException("|А| < length");

        for (int i = 0; i < _wordsCount; i++)
        {
            GenerateWord(i, _alphabet, _length);
        }
        return _words;
    }

    /**
     * Генерування набору слів
     * @param n Індекс набору
     * @param alphabet Алфавіт символів
     * @param length Довжина генерованих слів
     */
    private String GenerateWord (int n, String alphabet, int length) {
        int alphLength = alphabet.length();

        StringBuilder word = new StringBuilder();
        for (int i = 0; i < length; i++)
        {
            word.append(alphabet.charAt(n % alphLength));
            n /= alphLength;
        }
        _words.add(word.toString());

        return word.toString();
    }
}
