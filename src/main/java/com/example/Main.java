package com.example;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Main {
    public static void main(String[] args) {
       
        // LÄS IN ALLA FILER
        HashSet<Person> avstängda = new HashSet<>();
        HashSet<Person> medlemmar = new HashSet<>();
        HashSet<Person> besökande = new HashSet<>();
        HashSet<Person> f_hjälpen = new HashSet<>();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Type typ = new TypeToken<ArrayList<Person>>() {
        }.getType();
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

        HashSet<Person> allaDeltagare = new HashSet<>(medlemmar);
        
        medlemmar.retainAll(f_hjälpen);
        IO.println("MEDLEMMAR: " + medlemmar + "\nANTAL: " + medlemmar.size());

        String json = gson.toJson(medlemmar);
        
        try {
            Files.writeString(Paths.get("medlemmar_f_hjälp.json"), json);
        } catch (Exception e) {
            IO.println("FEL: " + e.getMessage());
        }

        allaDeltagare.addAll(besökande);
        allaDeltagare.removeAll(avstängda);
        IO.println("DELTAGARE: " + allaDeltagare + "\nANTAL: " + allaDeltagare.size());

        json = gson.toJson(allaDeltagare);
        
        try {
            Files.writeString(Paths.get("deltagare.json"), json);
        } catch (Exception e) {
            IO.println("FEL: " + e.getMessage());
        }
    }
}