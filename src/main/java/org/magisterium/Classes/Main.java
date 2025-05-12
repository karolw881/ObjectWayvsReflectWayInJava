package org.magisterium.Classes;
import org.magisterium.Classes.LolScanner.MyScanner;
import java.io.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    public static void main(String[] args) {
        MyScanner scanner = new MyScanner(System.in);
        scanner.run();
    }

}