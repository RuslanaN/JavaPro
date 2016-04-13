package net.ukr.ruslana;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by ruslana on 12.04.16.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        File f=new File("json.txt");
        try(FileReader reader = new FileReader(f)) {
            char[] buffer = new char[(int)f.length()];
            reader.read(buffer);
            Gson gson = new GsonBuilder().create();
            Person person = gson.fromJson(new String(buffer), Person.class);
            System.out.println(person.toString());
        } catch(IOException e){
            System.out.println(e);
        }
    }
}
