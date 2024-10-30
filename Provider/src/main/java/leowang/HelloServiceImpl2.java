package leowang;

public class HelloServiceImpl2 implements HelloServcie {
    @Override
    public String sayHello(String name) {
        return "hello: " + name;
    }
}
