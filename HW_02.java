import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/* Дана json строка [{ "фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},
{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
Задача написать метод(ы), который распарсит строку и выдаст ответ вида:
Студент Иванов получил 5 по предмету Математика.
Студент Петрова получил 4 по предмету Информатика.
Студент Краснов получил 5 по предмету Физика.

Используйте StringBuilder для подготовки ответа

Исходная json строка это просто String !!! Для работы используйте методы String, такие как replace, split, substring и т.д. по необходимости

Создать метод, который запишет результат работы в файл. Обработайте исключения и запишите ошибки в лог файл.
2. *Получить исходную json строку из файла, используя FileReader или Scanner
3. *Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.
*/

public class HW_02{
    public static void main(String[] args) {
        String s = getString();
        s = delSymb(s);      
        s = changeWords(s);
        String[] str = s.split(" ");
        s = makeString(str);
        System.out.println(s);
        saveToFile(s);
    }

    public static String changeWords(String str) {
        str = str.replace("фамилия", "Студент");
        str = str.replace("оценка", "получил");
        str = str.replace("предмет", "по предмету");
        return str;
    }

    public static String makeString(String[] str) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            if((i + 1) % 7 == 0){
                stringBuilder.append(str[i] + ".\n");
            }
            else{
                stringBuilder.append(str[i] + " ");  
            }
        }
        return stringBuilder.toString();        
    }

    public static String delSymb(String str) {
        str = str.replace("[", "");
        str = str.replace("]", "");
        str = str.replace("{", "");
        str = str.replace("}", "");
        str = str.replace("\"", "");
        str = str.replace(":", " ");
        str = str.replace(",", " ");
        return str;
    }

    public static String getString() {
        String str = "[{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"},{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"},{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]";
        return str;       
    }

    public static void saveToFile(String s) {
        Logger logger = Logger.getAnonymousLogger();   
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("log.txt");
        } catch (IOException e) {            
            e.printStackTrace();
        }   
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);  
        logger.addHandler(fileHandler);
        String path = "test.txt";
        FileWriter printWriter = null;
        try {             
            printWriter = new FileWriter(path, true);      
            printWriter.write(s);
            printWriter.flush();
        } catch (Exception e){
            e.printStackTrace();
            logger.log(Level.WARNING, "Что то пошло не так");
        } finally {
            try {
                printWriter.close();
                fileHandler.close();
            } catch (IOException e) {
                
                e.printStackTrace();
            }  
        }
    }
}