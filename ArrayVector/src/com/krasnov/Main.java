package com.krasnov;

class A {
    String x = "A";
    void printX() {
        System.out.println(x);
    }
}
class B extends A {
    String x = "B";
    B() {
        x = null;
    }
}
class C extends B {
    public A getIt() {
        return new B();
    }
}
public class Main {

    private void test() {
        System.out.println("1");
        int i=1;
        switch (i){
            default:System.out.println("2");
        }
    }
    public static void main(String[] args) {
        ArrayVector arrayVector = new ArrayVectorImpl();
        double[] arr = {5,6,3,1,35,5};
        arrayVector.set(arr);
        arrayVector.set(arrayVector.getSize()+1, 6);
        for (int i =0; i<arrayVector.getSize(); i++) {
            System.out.print(arrayVector.get(i)+" ");
        }
        System.out.println();
        ComplexNumber complexNumber = new ComplexNumberImpl(0,1);
        System.out.println(complexNumber.toString());
        //complexNumber.negate();
        System.out.println(complexNumber.toString());
        ComplexNumber complexNumber1 = new ComplexNumberImpl(-1,-1);
        System.out.println(complexNumber.equals(complexNumber1));
        B b = new B();
        b.printX();
        Employee employee1 = new EmployeeImpl();
        employee1.setFirstName("A");
        employee1.setLastName("A");
        Employee employee2 = new EmployeeImpl();
        employee2.setFirstName("B");
        employee2.setLastName("B");
        Employee employee3 = new EmployeeImpl();
        employee3.setFirstName("C");
        employee3.setLastName("C");
        employee1.setManager(employee2);
        employee2.setManager(employee3);
        System.out.println(employee1.getTopManager().getFullName());
        Location location = new LocationImpl();
        location.setName("Russia");
        location.setType(Location.Type.COUNTRY);
        System.out.println(location.toString());
    }
}
