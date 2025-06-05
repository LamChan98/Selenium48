package heroku;

public class Person {
    private String firstName, lastName;
    private double due;

    public Person(String firstName, String lastName, double due) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.due = due;
    }

    public void info() {
//        System.out.println("First name: "+ firstName);
//        System.out.println("Last name: "+ lastName);
//        System.out.println("Due: "+ due);

//        System.out.printf("First name: %s%n", firstName);
//        System.out.printf("Last name: %s%n", lastName);
//        System.out.printf("Due: %.2f%n", due);

        System.out.printf("First name: %s Last name: %s Due: %.2f%n", firstName, lastName, due);
    }

    public double getDue(){
        return due;
    }

    public String getFullname() {
        return String.format("%s %s",firstName, lastName);
    }
}
