package org.max.training;

public class Shark extends Fish {

    private int numberOfFins = 8;

    public Shark(int age) {
        super(age);
        this.size="50";
    }
    public Shark(){}

    public void displaySharkDetails() {
        System.out.print("Shark with age: " + getAge());
        System.out.print("Shark with age: " + this.getAge());
        System.out.print("Shark with age: " + super.getAge());
    }

    public static void main(String[] args) {
        Shark a=new Shark(10);
        Shark b=new Shark();
        System.out.println(a.size);
        System.out.println(a.numberOfFins);
        a.displaySharkDetails();
    }
}
class Fish {
    protected String size;
    private int age=1;

    public Fish(int age) {
        this.age = age;
        System.out.println("Hello");
    }
 public Fish(){}
    public int getAge() {
        return age;
    }
}