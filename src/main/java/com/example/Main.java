package com.example;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Main {
    public static void main(String[] args) {
   /* // Skapa en snygg Gson-instans för läsbar JSON-output
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type personListType = new TypeToken<ArrayList<Person>>(){}.getType();

        String medlemmarStr=""; 
        String fhjalpenStr="";
        String avstangdaStr="";
        String besokandeStr="";
        try {
            // --- STEG 1: LÄSA IN ALLA FILER ---
             medlemmarStr = Files.readString(Paths.get("medlemmar.json"));
             fhjalpenStr = Files.readString(Paths.get("f_hjälpen.json"));
             avstangdaStr = Files.readString(Paths.get("avstängda.json"));
             besokandeStr = Files.readString(Paths.get("besökande.json"));

        } catch (IOException e) {
            System.err.println("FIL fel vid inläsning: " + e.getMessage());
            System.out.println("Avslutar");
            //avslutar programmet
            return;
        }
        
            // Omvandla till Listor
            List<Person> medlemmar = gson.fromJson(medlemmarStr, personListType);
            List<Person> fhjalpen = gson.fromJson(fhjalpenStr, personListType);
            List<Person> avstangda = gson.fromJson(avstangdaStr, personListType);
            List<Person> besokande = gson.fromJson(besokandeStr, personListType);

            // --- DEL A: SÄKERHETSANSVARIGA (SNITT / INTERSECTION) ---
            // Vi vill ha medlemmar SOM OCKSÅ finns i första hjälpen-listan
            HashSet<Person> medlemmarSet = new HashSet<>(medlemmar);
            HashSet<Person> fhjalpenSet = new HashSet<>(fhjalpen);

            medlemmarSet.retainAll(fhjalpenSet); // Behåller bara de som finns i båda
            IO.println("Medlemar som kan 1:a hjälpen : ");
            for (Person p: medlemmarSet) {
                IO.println(p.getNamn());
            }
            System.out.println("ANTAL: "+ medlemmarSet.size());   
            System.out.println("------------------------------------------------");
            
 */

         // LÄS IN ALLA FILER
        HashSet<Person> avstängda = new HashSet<>();
        HashSet<Person> medlemmar = new HashSet<>();
        HashSet<Person> besökande = new HashSet<>();
        HashSet<Person> f_hjälpen = new HashSet<>();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Type typ = new TypeToken<ArrayList<Person>>() {}.getType();
        try {

            // AVSTÄNGDA
            String avstängda_String = Files.readString(Paths.get("avstängda.json"));
            ArrayList<Person> allaAvstängda = gson.fromJson(avstängda_String, typ);
            avstängda = new HashSet<>(allaAvstängda);

            // MEDLEMMAR
            String medlemmar_String = Files.readString(Paths.get("medlemmar.json"));
            ArrayList<Person> allamedlemmar = gson.fromJson(medlemmar_String, typ);
            medlemmar = new HashSet<>(allamedlemmar);

            // BESÖKANDE
            String besökande_String = Files.readString(Paths.get("besökande.json"));
            ArrayList<Person> allabesökande = gson.fromJson(besökande_String, typ);
            besökande = new HashSet<>(allabesökande);

            // F_HJÄLPEN
            String f_hjälpen_String = Files.readString(Paths.get("f_hjälpen.json"));
            ArrayList<Person> allaf_hjälpen = gson.fromJson(f_hjälpen_String, typ);
            f_hjälpen = new HashSet<>(allaf_hjälpen);

        } catch (Exception e) {
            IO.println("FEL: " + e.getMessage());
        }
       /*  IO.println(medlemmar + "\nantal: " + medlemmar.size() + "\n");

        IO.println(f_hjälpen + "\nantal: " + f_hjälpen.size()); */
        medlemmar.retainAll(f_hjälpen);
        IO.println("MEDLEMMAR: "+ medlemmar + "\nANTAL: " + medlemmar.size()); 
    }
}