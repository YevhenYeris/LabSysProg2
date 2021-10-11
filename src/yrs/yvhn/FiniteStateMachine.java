package yrs.yvhn;

import java.io.*;
import java.util.*;

/**
 * Реалізація скінченного автомату
 */
public class FiniteStateMachine {

    // Початковий стан
    private final int _initialState;
    // Чинний стан
    private int _currentState;
    // Множина заключних станів
    private Set<Integer> _finishStates = new HashSet<>();

    // Прапорець, чи завершена робота автомату
    private boolean _isFinished = false;

    // Словник переходів
    private Dictionary<String, Integer> _transitionsDict = new Hashtable<>();


    public FiniteStateMachine(String fileName) throws IOException {
        int alphLength;
        int statesLength;

        File file = new File(fileName);
        FileReader reader1 = new FileReader(file);
        BufferedReader reader = new BufferedReader(reader1);

        // Зчитуємо потужність алфавіту, множини стану та початковий стан
        alphLength = Integer.parseInt(reader.readLine());
        statesLength = Integer.parseInt(reader.readLine());
        _currentState = Integer.parseInt(reader.readLine());
        _initialState = _currentState;

        // Зчитуємо заключні стани
        String fstr = reader.readLine();
        String[] fs = fstr.split(" ");

        for (int i = 1; i <= Integer.parseInt(fs[0]); ++i) {
            _finishStates.add(new Integer(fs[i]));
        }

        // Зчитаємо переходи
        String line;
        while ((line = reader.readLine()) != null) {
            String[] strs = line.split(" ");

            _transitionsDict.put(strs[0]+strs[1], Integer.parseInt(strs[2]));
        }
        reader.close();
    }

    /**
     * Обчислити результат роботи автомату на даному слові
     * @param input Слово для обчислення результату
     */
    public void ComputeResult(String input) {
        for (int i = 0; i < input.length(); ++i) {
            SetState(input.charAt(i));

            if (_isFinished) return;
        }
    }

    /**
     * Перевірити, чи допускає автомат слово
     * @param input Слово для перевірки
     * @return булеве значення
     */
    public boolean DoesAccept(String input) {
        for (int i = 0; i < input.length(); ++i) {
            SetState(input.charAt(i));

            if (_isFinished) return true;
        }
        return _isFinished;
    }


    private void SetState(char input) {
        Integer value = _transitionsDict.get(new Integer(_currentState) + Character.toString(input));

        if (value != null) {
            _currentState = value;

            if (_finishStates.contains(_currentState)) {
                _isFinished = true;
            }
        }
    }

    /**
     * Отримати чинний стан автомату
     * @return стан автомату
     */
    public int GetCurrentState() {
        return _currentState;
    }

    /**
     * Перевести чинний стан у початковий
     */
    public void Reload() {
        _isFinished = false;
        _currentState = _initialState;
    }
}
