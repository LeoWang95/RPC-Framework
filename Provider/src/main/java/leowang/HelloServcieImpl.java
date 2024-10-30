package leowang;

public class HelloServcieImpl implements HelloServcie {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}