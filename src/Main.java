import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {

        Employee jhon = new Employee("Jhon jhon", 30);
        Employee tim = new Employee("Tim tim", 21);
        Employee jack = new Employee("Jack jack", 40);
        Employee snow = new Employee("Snow snow", 22);
        Employee red = new Employee("Red red", 35);
        Employee charming = new Employee("Charming charming", 31);

        List<Employee> employees = new ArrayList<>();
        employees.add(jhon);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(red);
        employees.add(charming);

        Function<Employee,String> getLastName = (Employee employee) ->{
            return employee.getName().substring(employee.getName().indexOf(' ') + 1);
        };

        String lastName = getLastName.apply(employees.get(1));
        System.out.println(lastName);

        Function<Employee,String> getFirstName = (Employee employee) ->{
            return employee.getName().substring(0,employee.getName().indexOf(' '));
        };

        Random random1 = new Random();

        for (Employee employee : employees){
            if (random1.nextBoolean()) {
                System.out.println(getAName(getFirstName, employee));
            } else {
                System.out.println(getAName(getLastName,employee));
            }
        }

        Function<Employee,String> upperCase = employee -> employee.getName().toUpperCase();
        Function<String,String> firstName = name -> name.substring(0,name.indexOf(' '));
        Function chainedFunction = upperCase.andThen(firstName);
        System.out.println(chainedFunction.apply(employees.get(0)));

        BiFunction<String ,Employee, String> concatAge = (String name, Employee employee) -> {
            return name.concat(" " + employee.getAge());
        };

        String upperName = upperCase.apply(employees.get(0));
        System.out.println(concatAge.apply(upperName,employees.get(0)));

        IntUnaryOperator incBy5 = i -> i+5;
        System.out.println(incBy5.applyAsInt(10));

//        printEmployeesByAge(employees,"Employees over 30",employee -> employee.getAge() > 30);
//        printEmployeesByAge(employees,"\nEmployees 30 and under",employee -> employee.getAge() <= 30);
//        printEmployeesByAge(employees, "Employees younger than 25", new Predicate<Employee>() {
//            @Override
//            public boolean test(Employee employee) {
//                return employee.getAge() < 25;
//            }
//        });
//
//        IntPredicate greaterThan15 = i -> i > 15;
//        IntPredicate lessThan100 = i -> i < 100;
//        System.out.println(greaterThan15.test(10));
//        int a = 20;
//        System.out.println(greaterThan15.test(a));
//
//        System.out.println(greaterThan15.and(lessThan100).test(50));
//
//        Random random = new Random();
//        for (int i = 0; i < 10; i++){
//            System.out.println(random.nextInt());
//        }
//
//        employees.forEach(employee -> {
//            String lastName = employee.getName().substring(employee.getName().indexOf(' ') + 1);
//            System.out.println("last name is: " + lastName);
//        });

    }

    private static String getAName(Function<Employee,String> getName, Employee employee){
        return getName.apply(employee);
    }
    private static  void printEmployeesByAge(List<Employee> employees,
                                             String ageText,
                                             Predicate<Employee> ageCondition){
        System.out.println(ageText);
        System.out.println("===================");
        for (Employee employee : employees){
            if(ageCondition.test(employee)){
                System.out.println(employee.getName());
            }
        }
    }
}


