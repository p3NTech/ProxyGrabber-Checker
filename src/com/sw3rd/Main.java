package com.sw3rd;

import com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import com.sw3rd.API.*;

public class Main {
    /*

    @Author SW3RD
    @Github iiKillerxSG
    @param country defines the proxy country according to proxy scrap api must in the next format US , EG , IQ
    @param type defines the proxy type
    @method GrabProxies send an http request with method get to get the proxies from proxy scrap api then call WriteFile method to write the proxies in a txt file
    @method WriteFile this method for write any string in a txt file
    @class Main.java
    All Credits reserved for SW3RD



     */
    // Color section
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    static Scanner sc = new Scanner(System.in);
    static API api = new API();
    public static void main(String[] args) {
        // write your code here
        try {


            System.out.println(ANSI_GREEN);
            System.out.println("____________ _______   ____   __  _____ ______  ___  ______ ___________ \n" +
                    "| ___ \\ ___ \\  _  \\ \\ / /\\ \\ / / |  __ \\| ___ \\/ _ \\ | ___ \\  ___| ___ \\\n" +
                    "| |_/ / |_/ / | | |\\ V /  \\ V /  | |  \\/| |_/ / /_\\ \\| |_/ / |__ | |_/ /\n" +
                    "|  __/|    /| | | |/   \\   \\ /   | | __ |    /|  _  || ___ \\  __||    / \n" +
                    "| |   | |\\ \\\\ \\_/ / /^\\ \\  | |   | |_\\ \\| |\\ \\| | | || |_/ / |___| |\\ \\ \n" +
                    "\\_|   \\_| \\_|\\___/\\/   \\/  \\_/    \\____/\\_| \\_\\_| |_/\\____/\\____/\\_| \\_|\n" +
                    "                                                                        \n" +
                    "                                                                        ");
            System.out.println(" ");
            System.out.println("________   __  _____  _    _  _________________ \n" +
                    "| ___ \\ \\ / / /  ___|| |  | ||____ | ___ \\  _  \\\n" +
                    "| |_/ /\\ V /  \\ `--. | |  | |    / / |_/ / | | |\n" +
                    "| ___ \\ \\ /    `--. \\| |/\\| |    \\ \\    /| | | |\n" +
                    "| |_/ / | |   /\\__/ /\\  /\\  /.___/ / |\\ \\| |/ / \n" +
                    "\\____/  \\_/   \\____/  \\/  \\/ \\____/\\_| \\_|___/  \n" +
                    "                                                \n" +
                    "                                                ");
            System.out.println(" ");
            System.out.println("Type 1 To Grab :)");
            System.out.println("Type 2 To Check :)");
            System.out.println(" ");
            System.out.print("Lets get started : ");
            int num = sc.nextInt();

            if (num == 1) {
                System.out.print("Ok Good choose the country if no specified country type all : ");
                String country = sc.next();
                if(country.length() != 2 && !country.equalsIgnoreCase("all")){
                    System.out.print("Please right in the next format like United States is US and like that");
                    return;
                }else {

                    System.out.print("Ok Good choose now the type http,socks4,socks5,all : ");
                    String type = sc.next();

                    if(!type.equals("http") && !type.equals("socks4") && !type.equals("socks5") && !type.equals("all")){
                        System.out.print("The available proxies type is http,socks4,socks5");
                        return;
                    }else{
                        System.out.println("Ok Grabbing the proxies ...");
                        api.GrabProxies(country, type);
                        System.out.print("Successfully Grabbed the proxies and saved it in " + type + ".txt");

                    }

                }

            } else if(num == 2) {
                System.out.print("OK Good choose the type : ");
                String checktype = sc.next();
                if(!checktype.equals("http") && !checktype.equals("socks4") && !checktype.equals("socks5") && !checktype.equals("all")){
                    System.out.print("The available proxies type is http,socks4,socks5");
                    return;
                }else{
                    System.out.println("Ok Checking the proxies ...");
                    api.CheckProxies(checktype);
                    for(Map.Entry<String,Integer> proxygo : api.goodproxies.entrySet()){
                        String host = proxygo.getKey();
                        int port = proxygo.getValue();
                        api.WriteFile(host + port + "\n","good");
                    }
                    System.out.println(ANSI_GREEN + "Good Proxies :" + api.goodproxies.size() + " Saved in good.txt");

                }

            }else{
                System.out.print(" ");
                System.out.println(ANSI_RED + "I said 1 or 2 not 923219032109390");
                return;
            }

        }catch(InputMismatchException ex){
            System.out.print(" ");
            System.out.println(ANSI_RED + "Please make sure the country and type are strings and the start num is integer");

        }
    }

}
