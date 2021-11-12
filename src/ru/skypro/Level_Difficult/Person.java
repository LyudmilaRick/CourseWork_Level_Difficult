package ru.skypro.Level_Difficult;

/**
 * Класс {@code Person}, который содержит
 * информацию о Фамилии, имени и Отчестве.
 */
public class Person {
    private final String surname;        // Фамилия
    private final String firstName;      // Имя
    private final String middleName;           // Отчество

    private final int hash;            // Хэш-сумма ФИО
    private final String name;         // Строка полного имени

    /**
     * Конструктор нового экземпляра класса  Полное имя (Ф.И.О.).
     *
     * @param surname           // Фамилия
     * @param firstName         // Имя
     * @param middleName        // Отчество
     */
    public Person(String surname, String firstName, String middleName) {
        this.firstName = normalize(surname);
        this.middleName = normalize(middleName);
        this.surname = normalize(firstName);
        // Переменные класса неизменяемые,
        // можно сразу вычислить следующие значения.
        name = String.join(" ", this.surname, this.firstName, this.middleName).trim();
        hash = name.hashCode();
    }

    /**
     * Конструктор нового экземпляра класса
     * Короткое имя (без отчества).
     *
     * @param surname          // Фамилия
     * @param firstName       // Имя
     */
    public Person(String surname, String firstName) {
        this(surname, firstName, "");
    }

    /**
     * Нормализовать строку имени.
     * Значение {@code null} заменяестся на пустую строку.
     * Убираются лишние пробелы из имени.
     * Все символы, кроме первого, преобразуются в нижний регистр.
     * Первый символ имени преобразуется верхний регистр.
     *
     * @param str Исходная строка имени.
     * @return Нормализованная строка имени.
     */
    protected final String normalize(String str) {
        if (str == null || str.isEmpty()) return " ";
        if (str.length() == 1) return str.toUpperCase();

        String res = str.replace(" ", "");
        return Character.toUpperCase(res.charAt(0)) + res.substring(1).toLowerCase();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSurname() {
        return surname;
    }

    public String getFullName() {
        return String.join(" ", surname, firstName, middleName);
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Возвращает хэш-код для полного имени (Ф.И.О.).
     *
     * @return значение хэш-кода для этого объекта.
     */
    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Person) {
            return hash == obj.hashCode();
        }
        return false;
    }
}
