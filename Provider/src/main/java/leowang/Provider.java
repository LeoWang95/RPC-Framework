package leowang;

import com.leowang.common.URL;
import com.leowang.protocol.HttpServer;
import com.leowang.register.LocalRegister;
import com.leowang.register.MapRemoteRegister;

public class Provider {
    public static void main(String[] args) {

        LocalRegister.regist(HelloServcie.class.getName(), "1.0", HelloServcieImpl.class);

        //注册中心注册
        URL url  = new URL("localhost", 8080); //可以用java.net灵活的获取到服务存放的主机地址和ip
        MapRemoteRegister.regist(HelloServcie.class.getName(),url);

        //Netty、Tomcat接受网络请求
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());


    }
}
