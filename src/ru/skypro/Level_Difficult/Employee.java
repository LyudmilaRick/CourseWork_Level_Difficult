package ru.skypro.Level_Difficult;

/**
 * Класс {@code Employee}, который содержит информацию
 * о Ф.И.О., отделе и зарплате сотрудника.
 * Отделы для простоты названы от 1 до 5.
 */
public class Employee {
    private static int counter = 0;      // Добавить статическую переменную-счетчик

    private final int id;                // Уникальный идентификационный номер сотрудника.
    private final Person person;         // ФИО сотрудника.
    private Department department;       // Отдел сотрудника.
    private float salary;                // Текущая заработная плата сотрудника.

    /**
     * Конструктор нового экземпляра класса {@code Employee},
     * заполняющий все реквизиты сотрудника, кроме идентификатора.
     * Уникальный идентификационный номер (@code id) заполняется
     * автоматически по глобальному счетчику (@code counter).
     *
     * @param department Отдел, где трудится сотрудник.
     * @param person     ФИО сотрудника.
     * @param salary     Размер заработной платы сотрудника.
     */

    public Employee(Department department, Person person, float salary) {
        this.person = person;
        this.department = department;
        this.salary = salary;
        id = ++counter;
    }

    /**
     * Конструктор нового экземпляра класса {@code Employee},
     * заполняющий все реквизиты сотрудника, кроме идентификатора.
     * Уникальный идентификационный номер (@code id) заполняется
     * автоматически по глобальному счетчику (@code counter).
     *
     * @param department Отдел, где трудится сотрудник.
     * @param surname    * ФИО сотрудника.     Фамилия
     * @param firstName  * ФИО сотрудника.     Имя
     * @param middleName * ФИО сотрудника.    Отчество
     * @param salary     * Размер заработной платы сотрудника
     */
    public Employee(Department department, String surname, String firstName, String middleName, float salary) {
        this(department, new Person(surname, firstName, middleName), salary);
    }
    //  Добавить возможность получать значения полей из Employee (геттеры) для всех полей.

    /**
     * Получить полное имя (Ф.И.О.) сотрудника.
     *
     * @return Значение Ф.И.О. сотрудника.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Получить значение заработной платы сотрудника.
     *
     * @return Зарплата сотрудника.
     */
    public float getSalary() {
        return salary;
    }

    /**
     * Получить отдел сотрудника.
     *
     * @return Отдел сотрудника.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Получить значение идентификационного номера сотрудника.
     *
     * @return Идентификационный номер сотрудника.
     */
    public int getId() {
        return id;
    }
    //5. Добавить возможность устанавливать значения полей отдела и зарплаты (сеттеры).

    /**
     * Установить новое значение отдела сотрудника.
     *
     * @param department Отдел сотрудника.
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Получить значение заработной платы сотрудника.
     */
    public void setSalary(float salary) {
        this.salary = salary;
    }

    /**
     * Последний идентификационный номер, присвоенный сотрудникам.
     *
     * @return идентификационный номер.
     */
    public static int CurrentNumberOfEmployees() {
        return counter;
    }

    @Override
    public String toString() {
        return String.format("\"%6s %3d %-15s %-15s %-15s %.2f\", ",
                department, id, person.getSurname(), person.getFirstName(), person.getMiddleName(), salary);
    }
}
