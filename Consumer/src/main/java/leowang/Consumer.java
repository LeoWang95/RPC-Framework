package leowang;

import com.leowang.common.Invocation;
import com.leowang.protocol.HttpClient;
import com.leowang.proxy.ProxyFactory;

import java.io.IOException;

public class Consumer {
    public static void main(String[] args) throws IOException {
        HelloServcie helloServcie = ProxyFactory.getProxy(HelloServcie.class);
        String result = helloServcie.sayHello("leowang");
//        System.out.println(result);
//        Invocation invocation  = new Invocation(HelloServcie.class.getName(), "sayHello",
//                new Class[]{String.class},new Object[]{"leowang"});
//        HttpClient client = new HttpClient();
//
//        String result = client.send("localhost", 8080, invocation);

        System.out.println(result);
    }
}
