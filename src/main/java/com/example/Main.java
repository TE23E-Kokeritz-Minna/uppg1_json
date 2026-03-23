package com.example;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import jk_json.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Main {
    public static void main(String[] args) {
        // LÄS IN ALLA FILER
        ArrayList<String> avstängda;
        ArrayList<String> medlemmar;
        ArrayList<String> besökande;
        ArrayList<String> f_hjälpen;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            Type typ = new TypeToken<ArrayList<Person>>() {
            }.getType();
            // AVSTÄNGDA
            String avstängda_String = Files.readString(Paths.get("avstängda.json"));
            ArrayList<String> allaAvstängda = gson.fromJson(avstängda_String, typ);
            HashSet<String> avstängda_rensade = new HashSet<>(allaAvstängda);

            avstängda = new ArrayList<>(avstängda_rensade);

            // MEDLEMMAR
            String medlemmar_String = Files.readString(Paths.get("medlemmar.json"));
            ArrayList<String> allamedlemmar = gson.fromJson(medlemmar_String, typ);
            HashSet<String> medlemmar_rensade = new HashSet<>(allamedlemmar);

            medlemmar = new ArrayList<>(medlemmar_rensade);

            // BESÖKANDE
            String besökande_String = Files.readString(Paths.get("besökande.json"));
            ArrayList<String> allabesökande = gson.fromJson(besökande_String, typ);
            HashSet<String> besökande_rensade = new HashSet<>(allabesökande);

            besökande = new ArrayList<>(besökande_rensade);

            // F_HJÄLPEN
            String f_hjälpen_String = Files.readString(Paths.get("f_hjälpen.json"));
            ArrayList<String> allaf_hjälpen = gson.fromJson(f_hjälpen_String, typ);
            HashSet<String> f_hjälpen_rensade = new HashSet<>(allaf_hjälpen);

            f_hjälpen = new ArrayList<>(f_hjälpen_rensade);

        } catch (Exception e) {
            IO.println("FEL: " + e.getMessage());
        }

        

    }
}