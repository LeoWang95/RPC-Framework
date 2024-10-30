package com.leowang.loadbalance;

import com.leowang.common.URL;
import java.util.List;
import java.util.Random;

public class Loadbalance {
    public Loadbalance() {
    }

    //随机均衡
    public static URL random(List<URL> urls) {
        Random random = new Random();
        int i = random.nextInt(urls.size());
        return urls.get(i);
    }
}