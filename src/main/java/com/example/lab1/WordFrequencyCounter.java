package com.example.lab1;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "WordFrequencyCounter", value = "/WordFrequencyCounter")
public class WordFrequencyCounter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Текст для анализа
        String text = "All the world's a stage,\n" +
                "And all the men and women merely players,\n" +
                "They have their exits and entrances,\n" +
                "And one man in his time plays many parts,\n" +
                "His acts being seven ages. At first the infant,\n" +
                "Mewling and puking in the nurse's arms.\n" +
                "Then, the whining schoolboy with his satchel\n" +
                "And shining morning face, creeping like snail\n" +
                "Unwillingly to school. And then the lover,\n" +
                "Sighing like furnace, with a woeful ballad\n" +
                "Made to his mistress' eyebrow. Then a soldier,\n" +
                "Full of strange oaths, and bearded like the pard,\n" +
                "Jealous in honour, sudden, and quick in quarrel,\n" +
                "Seeking the bubble reputation\n" +
                "Even in the cannon's mouth. And then the justice\n" +
                "In fair round belly, with good capon lin'd,\n" +
                "With eyes severe, and beard of formal cut,\n" +
                "Full of wise saws, and modern instances,\n" +
                "And so he plays his part. The sixth age shifts\n" +
                "Into the lean and slipper'd pantaloon,\n" +
                "With spectacles on nose, and pouch on side,\n" +
                "His youthful hose well sav'd, a world too wide,\n" +
                "For his shrunk shank, and his big manly voice,\n" +
                "Turning again towards childish treble, pipes\n" +
                "And whistles in his sound. Last scene of all,\n" +
                "That ends this strange eventful history,\n" +
                "Is second childishness and mere oblivion,\n" +
                "Sans teeth, sans eyes, sans taste, sans everything.";

        // Преобразование текста в массив слов
        String[] words = text.split("\\s+");

        // Создание словаря для подсчета частоты встречаемости слов
        Map<String, Integer> wordFrequency = new HashMap<>();

        // Заполнение словаря
        for (String word : words) {
            // Приведение слова к нижнему регистру для учета регистра
            word = word.toLowerCase();
            // Если слово уже встречалось, увеличить его частоту на 1, иначе добавить слово в словарь
            if (wordFrequency.containsKey(word)) {
                wordFrequency.put(word, wordFrequency.get(word) + 1);
            } else {
                wordFrequency.put(word, 1);
            }
        }

        // Запрос слова у пользователя

        System.out.print("Введите слово для поиска: ");
        String searchWord = request.getParameter("word");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>The found word:</h2>");

        // Поиск слова в словаре и вывод частоты его встречаемости
        if (wordFrequency.containsKey(searchWord)) {
            out.println("Слово \"" + searchWord + "\" встречается " + wordFrequency.get(searchWord) + " раз(а).");
        } else {
            out.println("Слово \"" + searchWord + "\" не найдено в тексте.");
        }

        out.println("</body></html>");
    }
}