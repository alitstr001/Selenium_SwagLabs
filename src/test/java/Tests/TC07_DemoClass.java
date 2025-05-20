package Tests;

public class TC07_DemoClass {
    //just add a file

    public TC07_DemoClass() {
        System.out.println("Hi, i am constractor");
    }

    //add isOlder method
    public boolean isOlder(double age) {
        return age > 60.0;
    }
    //add isMan method
    public boolean isMan(String type){
        return type.equalsIgnoreCase("man");
    }

}
