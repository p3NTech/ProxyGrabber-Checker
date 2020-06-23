package com.sw3rd;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class API {
    /*

@Author SW3RD
@Github iiKillerxSG
@param country defines the proxy country according to proxy scrap api must in the next format US , EG , IQ
@param type defines the proxy type
@method GrabProxies send an http request with method get to get the proxies from proxy scrap api then call WriteFile method to write the proxies in a txt file
@method WriteFile this method for write any string in a txt file
@class API.java

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


    static  HashMap<String,Integer> proxies;
    static  HashMap<String,Integer> goodproxies;

    // Make a simple method for get the proxies from the proxyscrape api
    public static void GrabProxies(String country , String type){
        HttpURLConnection connection; // Setting UP The connection
        String URLL = null;

        try{
            if(type.equalsIgnoreCase("all")){
                URLL = "https://api.proxyscrape.com/?request=displayproxies&proxytype=http&timeout=10000&country=all&ssl=all&anonymity=all";

            }else
            if(type.equalsIgnoreCase("http")) {
                URLL = "https://api.proxyscrape.com/?request=displayproxies&proxytype=http&timeout=10000&country=" + country + "&ssl=all&anonymity=all";
            }else
            if(type.equalsIgnoreCase("socks4")){
                URLL = "https://api.proxyscrape.com/?request=displayproxies&proxytype=socks4&timeout=10000&country=" + country;

            }else
            if(type.equalsIgnoreCase("socks5")){
                URLL = "https://api.proxyscrape.com/?request=displayproxies&proxytype=socks5&timeout=10000&country=" + country;

            }
            assert URLL != null;
            URL url = new URL(URLL); // Setting up the url
            connection = (HttpURLConnection) url.openConnection(); // Define the connection to url
            connection.setRequestMethod("GET"); // Set the request method
            connection.setReadTimeout(10000); // Set the read timeout
            connection.setConnectTimeout(10000); // Set the connection timeout in milliseconds

            BufferedReader reader; // Define the buffered reader
            StringBuilder builder = new StringBuilder(); // Define String Builder that will store the proxies from the String lines;
            String lines; // Define String that all proxies will be in this variable

            int status = connection.getResponseCode(); // Get the status code

            if(status > 299){ // If the connection have any problem
                System.out.println("There is a problem with proxyscrab"); // send an error message for the user
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream())); // then send the error message that will the server send as a response
                while ((lines = reader.readLine()) != null){ // If the reader doesn't reach the end of lines or doesn't read all the lines make a loop to store all the lines in a StringBuilder
                    builder.append(lines); // Store the lines
                }
                reader.close(); // Close the reader
            }else{
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((lines = reader.readLine()) != null){
                    builder.append(lines).append("\n");
                }
                reader.close();
            }

            String proxies = builder.toString(); // Put the proxies in A String


            WriteFile(proxies,type);

        }catch (Exception ex){
            System.out.println("There is a problem with proxyscrab"); // send an error message for the user

        }


    }

    public static void CheckProxies(String type){
        HttpURLConnection connection;
        goodproxies = new HashMap<String, Integer>();
        if(type.equalsIgnoreCase("http")){
            ReadFile("http"); // Get the proxies

            try {

                for(Map.Entry<String,Integer> proxies1 : proxies.entrySet()){
                    String host = proxies1.getKey();
                    int port = proxies1.getValue();

                    System.setProperty("http.proxyHost",host);
                    System.setProperty("http.proxyPort", String.valueOf(port));
                    try {
                        URL url = new URL("http://www.google.com");
                        connection = (HttpURLConnection) url.openConnection();
                        connection.connect();
                        int status = connection.getResponseCode();
                        if(status > 299){
                            System.out.println(ANSI_RED + "[BAD] " + host + ":" + port);
                        }else{
                            System.out.println("[GOOD] " + host + ":" + port);
                            goodproxies.put(host + ":",port);

                        }

                    } catch (Exception ex) {
                        if(ex.toString().contains("Connection timed out")){
                            System.out.println(ANSI_RED + "[BAD] " + host + ":" + port);
                            System.out.print(ANSI_GREEN);
                        }
                        continue;

                    }

                }




            }catch (Exception ex){

            }
        }else if(type.equalsIgnoreCase("socks4")){
            ReadFile("socks4"); // Get the proxies

            try {

                for(Map.Entry<String,Integer> proxies1 : proxies.entrySet()){
                    String host = proxies1.getKey();
                    int port = proxies1.getValue();

                    System.setProperty("http.proxyHost",host);
                    System.setProperty("http.proxyPort", String.valueOf(port));
                    try {
                        URL url = new URL("http://www.google.com");
                        connection = (HttpURLConnection) url.openConnection();
                        connection.connect();
                        int status = connection.getResponseCode();
                        if(status > 299){
                            System.out.println(ANSI_RED + "[BAD] " + host + ":" + port);
                        }else{
                            System.out.println("[GOOD] " + host + ":" + port);
                            goodproxies.put(host + ":",port);

                        }

                    } catch (Exception ex) {
                        if(ex.toString().contains("Connection timed out")){
                            System.out.println(ANSI_RED + "[BAD] " + host + ":" + port);
                            System.out.print(ANSI_GREEN);
                        }
                        continue;

                    }

                }




            }catch (Exception ex){

            }
        }else  if(type.equalsIgnoreCase("socks5")){
            ReadFile("socks5"); // Get the proxies

            try {

                for(Map.Entry<String,Integer> proxies1 : proxies.entrySet()){
                    String host = proxies1.getKey();
                    int port = proxies1.getValue();

                    System.setProperty("http.proxyHost",host);
                    System.setProperty("http.proxyPort", String.valueOf(port));
                    try {
                        URL url = new URL("http://www.google.com");
                        connection = (HttpURLConnection) url.openConnection();
                        connection.connect();
                        int status = connection.getResponseCode();
                        if(status > 299){
                            System.out.println(ANSI_RED + "[BAD] " + host + ":" + port);
                        }else{
                            System.out.println("[GOOD] " + host + ":" + port);
                            goodproxies.put(host + ":",port);

                        }

                    } catch (Exception ex) {
                        if(ex.toString().contains("Connection timed out")){
                            System.out.println(ANSI_RED + "[BAD] " + host + ":" + port);
                            System.out.print(ANSI_GREEN);
                        }
                        continue;

                    }

                }




            }catch (Exception ex){

            }
        }else if(type.equalsIgnoreCase("all")){
            ReadFile("all"); // Get the proxies

            try {

                for(Map.Entry<String,Integer> proxies1 : proxies.entrySet()){
                    String host = proxies1.getKey();
                    int port = proxies1.getValue();

                    System.setProperty("http.proxyHost",host);
                    System.setProperty("http.proxyPort", String.valueOf(port));
                    try {
                        URL url = new URL("http://www.google.com");
                        connection = (HttpURLConnection) url.openConnection();
                        connection.connect();
                        int status = connection.getResponseCode();
                        if(status > 299){
                            System.out.println(ANSI_RED + "[BAD] " + host + ":" + port);
                        }else{
                            System.out.println("[GOOD] " + host + ":" + port);
                            goodproxies.put(host + ":",port);

                        }

                    } catch (Exception ex) {
                        if(ex.toString().contains("Connection timed out")){
                            System.out.println(ANSI_RED + "[BAD] " + host + ":" + port);
                            System.out.print(ANSI_GREEN);
                        }
                        continue;

                    }

                }




            }catch (Exception ex){

            }
        }

    }

    public static void WriteFile(String proxies,String type){
        try{
            String currentDirectory = System.getProperty("user.dir"); // If want to get the CurrentDirectory of the user
            FileWriter writer = null;

            if(type.equalsIgnoreCase("all")){
                writer = new FileWriter("all.txt");

            }else
            if(type.equalsIgnoreCase("http")){
                writer = new FileWriter("http.txt");

            }else
            if(type.equalsIgnoreCase("socks4")){
                writer = new FileWriter("socks4.txt");

            }else
            if(type.equalsIgnoreCase("socks5")){
                writer = new FileWriter("socks5.txt");
            }else
            if(type.equalsIgnoreCase("good")){
                writer = new FileWriter("good.txt");
            }


            assert writer != null;
            writer.write(proxies);
            writer.flush();
            writer.close();


        }catch (Exception ex){
            System.out.println("There is a problem with writing the proxies and save it");


        }
    }

    public static void ReadFile(String type){ // I used in this FileInputStream to read the proxies and put in hashmap

        // I didn't use The FileReader because its so so so so so bad so I will use FileInputStream to read all files :C
        // What's FileInPutStream its get all the information from the file that u want to read it but wait how to read this files
        // In this case we must use the same method for get the information from the servers BufferedReader and InputStreamReader
        // Now InputStreamReader it use to read all the bytes from the server or from the FileInputStream and BufferedReader is used to read the lines from the InPutStream reader
        // And Yeah after that we store the proxies in hashmap

        proxies = new HashMap<String, Integer>();
        try{
            String currentDirectory = System.getProperty("user.dir"); // If want to get the CurrentDirectory of the user
            BufferedReader reader = null; // Define the buffered reader
            if(type.equalsIgnoreCase("all")){
                FileInputStream stream = new FileInputStream("all.txt");
                reader = new BufferedReader(new InputStreamReader(stream));

            }else
            if(type.equalsIgnoreCase("http")){
                FileInputStream stream = new FileInputStream("http.txt");
                reader = new BufferedReader(new InputStreamReader(stream));

            }else
            if(type.equalsIgnoreCase("socks4")){
                FileInputStream stream = new FileInputStream("socks4.txt");
                reader = new BufferedReader(new InputStreamReader(stream));

            }else
            if(type.equalsIgnoreCase("socks5")){
                FileInputStream stream = new FileInputStream("sock5.txt");
                reader = new BufferedReader(new InputStreamReader(stream));
            }

            String lines;
            assert reader != null;
            while((lines = reader.readLine()) != null){
                if(lines.contains(":")){
                    String[] args = lines.split(":");
                    if(args.length == 2){
                        String host = args[0];
                        int port = Integer.parseInt(args[1]);
                        proxies.put(host,port);

                    }

                }
            }
            reader.close();
            System.out.println("Loadded " + proxies.size());



        }catch (Exception ex){
            System.out.println("There is a problem with writing the proxies and save it");


        }

    }
}
