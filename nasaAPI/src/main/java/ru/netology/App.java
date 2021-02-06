package ru.netology;


import java.io.IOException;


public class App 
{


    public static void main( String[] args )
    {
        try {
            new NasaImageLoader().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
