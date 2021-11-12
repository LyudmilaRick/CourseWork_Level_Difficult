package ru.skypro.Level_Difficult;

/**
 * Класс приложения по задаче Employee.
 */
public class Main {
    /**
     * Точка входа в приложение. public static void main
     */
    public static void main(String[] args) {
        System.out.println("Задача о сотрудниках");
        System.out.println("Очень сложно");
        System.out.println();

        EmployeeBook employeeBook = EmployeeBookFactory.createInstance();
        System.out.print("Количество сотрудников:  ");
        System.out.println(employeeBook.count());
        System.out.println();

        System.out.println("Изменить сотрудника: ");
        employeeBook.change("Гуревич", "Любовь", "Яковлевна", Department.SECOND, Float.NaN);
        employeeBook.change("Ланда", "Яков", "Семёнович", null, 9999999);
        employeeBook.change(45, Department.FIFTH, 9999999);

        System.out.println("Удалить  сотрудника: ");
        employeeBook.remove(27);
        employeeBook.remove("Гуревич", "Любовь", "Яковлевна");
        employeeBook.remove(new Person("Деревянкин", "Андрей", "Николаевич"));

        System.out.println("Список сотрудников по отделам");
        for (Department department : Department.values()) {
            System.out.println(department);
            employeeBook.printPerson("- ", department);
            System.out.println();

        }
    }
}