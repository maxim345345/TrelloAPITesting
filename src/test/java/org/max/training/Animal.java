package org.max.training;

public class Animal {

  public int age;
  public String name;
    public Animal(int age) {
        this.age = age;
    }

    public Animal(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
 class Zebra extends Animal {
    public Zebra(int age) {
        super(age);
    }
    public Zebra() {
        this(4);
    }

     public static void main(String[] args) {
        Zebra z=new Zebra();
        Zebra z1=new Zebra(5);

         System.out.println(z.age);
         System.out.println(z1.age);

         Gorilla gorilla=new Gorilla();

         System.out.println(z1.age);
         System.out.println(z1.age);
     }
}

class Gorilla extends Animal {
    public Gorilla(int age) {
        super(age);
    }
    public Gorilla() {
        super(5);
    }
}

