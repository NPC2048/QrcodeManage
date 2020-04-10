package com.liangyuelong.qrcode;

import org.junit.jupiter.api.Test;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Tests {

    @Test
    public void test() {
        Queue<String> queue = new LinkedBlockingQueue<>();
        queue.offer("asdf");
        queue.offer("ffff");
        System.out.println(queue);
        queue.remove("asdf");
        System.out.println(queue);
    }
}
