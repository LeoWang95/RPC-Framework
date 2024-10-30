package com.leowang.proxy;
import com.leowang.common.Invocation;
import com.leowang.common.URL;
import com.leowang.loadbalance.Loadbalance;
import com.leowang.protocol.HttpClient;
import com.leowang.register.MapRemoteRegister;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory {
    public ProxyFactory() {
    }

    public static <T> T getProxy(final Class interfaceClass) {
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

//                String mock = System.getProperty("mock");
//                if(mock != null && mock.startsWith("return:")){
//                    String reslut = mock.replace("return:", "");
//                    return reslut;
//                }

                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);
                HttpClient httpClient = new HttpClient();

                //服务发现，返回list包含多个提供该服务的服务器ip
                List<URL> list = MapRemoteRegister.get(interfaceClass.getName());

                //负载均衡
                URL url = Loadbalance.random(list);//加到服务调用中，使用最大调用次数可以实现服务重试

                //服务调用
                String result = null;
                try {
                    result = httpClient.send(url.getHostname(), url.getPort(), invocation);
                }catch (Exception e){
                    // 容错实现
                    // error-callback=com.leowang.HelloServiceErrorCallback im
                    return "proxyFactory-自定义报错";
                }

                return result;
            }
        });
        return (T) proxyInstance;
    }
}

