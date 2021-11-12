package ru.skypro.Level_Difficult;

/**
 * Класс {@code EmployeeBook}, который содержит
 * штатный список сотрудников и информацию о них.
 */
public class EmployeeBook {
    private static final int CAPACITY = 1000;

    // Массив, выполняющий роль хранилища для записей о сотрудниках.
    private final Employee[] employees;
    // Количество действительных сотрудников.
    private int count = 0;

    /**
     * Конструктор по умолчанию нового
     * экземпляра класса {@code EmployeeBook}.
     */
    public EmployeeBook() {
        this(CAPACITY);
    }

    /**
     * Конструктор нового экземпляра класса {@code EmployeeBook},
     * создающий хранилище штатных сотрудников заданного размера.
     *
     * @param capacity Размер нового хранилища.
     */
    public EmployeeBook(int capacity) {
        employees = new Employee[capacity];
    }

    /**
     * Получить количество сотрудников.
     *
     * @return количество сотрудников.
     */
    public int count() {
        return count;
    }

    /**
     * Добавить нового сотрудника в штатное расписание.
     * Если хранилище переполнено, то запись не добавляется.
     *
     * @param department Отдел, где трудится сотрудник.
     * @param surname    Фамилия сотрудника.
     * @param firstName  Имя сотрудника.
     * @param middleName Отчество сотрудника.
     * @param salary     Размер заработной платы сотрудника.
     * @return {@code true} Если запись сотрудника успешно добавлена;
     * {@code false} в противном случае.
     */
    public boolean add(Department department, String surname, String firstName, String middleName, float salary) {
        return add(new Employee(department, surname, firstName, middleName, salary));
    }

    /**
     * Добавить нового сотрудника в штатное расписание.
     * Если хранилище переполнено, то запись не добавляется.
     *
     * @param employee Объект нового сотрудника.
     * @return {@code true} Если запись сотрудника успешно добавлена;
     * {@code false} в противном случае.
     */
    public boolean add(Employee employee) {
        int index = 0;
        while (index < employees.length && employees[index] != null) {
            index++;
        }

        if (index < employees.length) {
            employees[index] = employee;
            count++;
            return true;
        }
        return false;
    }

    /**
     * Распечатоль Ф.И.О. сотрудников отдела.
     *
     * @param prefix     префикс в начале строки, перед Ф.И.О.
     * @param department код отдела.
     */
    public void printPerson(String prefix, Department department) {
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == department) {
                System.out.println(prefix.concat(employee.getPerson().toString()));
            }
        }
    }

    /**
     * Получить сотрудника по идентификатору.
     *
     * @param id уникальный идентификационный номер.
     * @return объект сотрудника, если он найден в списке,
     * {@code null} в противном случае.
     */
    public Employee get(int id) {
        int index = searchRecord(id);
        if (index < 0) return null;

        return employees[index];
    }

    /**
     * Получить сотрудника по полному имени.
     *
     * @param fio объект полного иммени сотрудника.
     * @return объект сотрудника, если он найден в списке,
     * {@code null} в противном случае.
     */
    public Employee get(Person fio) {
        int index = searchRecord(fio);
        if (index < 0) return null;

        return employees[index];
    }

    /**
     * Получить сотрудника по Ф.И.О.
     *
     * @param surname    Фамилия
     * @param firstName  Имя
     * @param middleName Отчество
     * @return объект сотрудника, если он найден в списке,
     * {@code null} в противном случае.
     */
    public Employee get(String surname, String firstName, String middleName) {
        return get(new Person(surname, firstName, middleName));
    }

    /**
     * Удалить заданного сотрудника из списка.
     *
     * @param id уникальный идентификационный номер.
     * @return {@code true} Если запись сотрудника успешно удалена;
     * {@code false} в противном случае.
     */
    public boolean remove(int id) {
        int index = searchRecord(id);
        if (index < 0) return false;

        removeByIndex(index);
        return true;
    }

    /**
     * Удалить заданного сотрудника из списка.
     *
     * @param surname    Фамилия
     * @param firstName  Имя
     * @param middleName Отчество
     * @return {@code true} Если запись сотрудника успешно удалена;
     * {@code false} в противном случае.
     */
    public boolean remove(String surname, String firstName, String middleName) {
        return remove(new Person(surname, firstName, middleName));
    }

    /**
     * Удалить заданного сотрудника из списка.
     *
     * @param fio объект полного иммени сотрудника.
     * @return {@code true} Если запись сотрудника успешно удалена;
     * {@code false} в противном случае.
     */
    public boolean remove(Person fio) {
        int index = searchRecord(fio);
        if (index < 0) return false;

        removeByIndex(index);
        return true;
    }


    /**
     * Изменить отдел и/или зарплату сотрудника по его идентификатору.
     *
     * @param id         уникальный идентификационный номер.
     * @param department Новый код подразделения или {@code null},
     *                   если не требуется менять значение отдела.
     * @param salary     Новая сумма или {@code Float.NaN},
     *                   если не требуется менять значение зарплаты.
     * @return {@code true} Если запись сотрудника успешно изменена;
     * {@code false} в противном случае.
     */
    public boolean change(int id, Department department, float salary) {
        int index = searchRecord(id);
        if (index < 0) return false;

        changeByIndex(index, department, salary);
        return true;
    }

    /**
     * Изменить отдел и/или зарплату сотрудника по полному имени.
     *
     * @param fio        объект полного иммени сотрудника.
     * @param department Новый код подразделения или {@code null},
     *                   если не требуется менять значение отдела.
     * @param salary     Новая сумма или {@code Float.NaN},
     *                   если не требуется менять значение зарплаты.
     * @return {@code true} Если запись сотрудника успешно изменена;
     * {@code false} в противном случае.
     */
    public boolean change(Person fio, Department department, float salary) {
        int index = searchRecord(fio);
        if (index < 0) return false;

        changeByIndex(index, department, salary);
        return true;
    }

    /**
     * Изменить отдел и/или зарплату сотрудника по Ф.И.О..
     *
     * @param surname    Фамилия
     * @param firstName  Имя
     * @param middleNam  Отчество
     * @param department Новый код подразделения или {@code null},
     *                   если не требуется менять значение отдела.
     * @param salary     Новая сумма или {@code Float.NaN},
     *                   если не требуется менять значение зарплаты.
     * @return {@code true} Если запись сотрудника успешно изменена;
     * {@code false} в противном случае.
     */
    public boolean change(String surname, String firstName, String middleNam, Department department, float salary) {
        return change(new Person(surname, firstName, middleNam), department, salary);
    }

    //region Internal implementation methods
    private int searchRecord(int id) {
        // поиск по индексу
        for (int I = 0; I < employees.length; I++) {
            if (employees[I] != null && employees[I].getId() == id) {
                return I;
            }
        }
        return -1;
    }

    private int searchRecord(Person fio) {
        //поиск по индексу
        for (int I = 0; I < employees.length; I++) {
            if (employees[I] != null && employees[I].getPerson().equals(fio)) {
                return I;
            }
        }
        return -1;
    }

    private void removeByIndex(int index) {
        employees[index] = null;
        count--;
    }

    private void changeByIndex(int index, Department department, float salary) {
        if (department != null) {
            employees[index].setDepartment(department);
        }

        if (!Float.isNaN(salary)) {
            employees[index].setSalary(salary);
        }

    }
    //endregion
}
