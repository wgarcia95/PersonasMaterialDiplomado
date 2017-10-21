package com.example.android.personasmaterialdiplomado;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by android on 07/10/2017.
 */

public class Datos {
    private static String db = "Personas";
    private static DatabaseReference dbreference = FirebaseDatabase.getInstance().getReference();
    private static ArrayList<Persona> personas = new ArrayList();

    public static void guardarPersona(Persona p) {
        p.setId(dbreference.push().getKey());
        dbreference.child(db).child(p.getId()).setValue(p);
    }

    public static void editarPersona(Persona p){
        dbreference.child(db).child(p.getId()).child("cedula").setValue(p.getCedula());
        dbreference.child(db).child(p.getId()).child("nombre").setValue(p.getNombre());
        dbreference.child(db).child(p.getId()).child("apellido").setValue(p.getApellido());
        dbreference.child(db).child(p.getId()).child("sexo").setValue(p.getSexo());
        dbreference.child(db).child(p.getId()).child("foto").setValue(p.getFoto());
        //personas.set(pos, p);
    }

    public static ArrayList<Persona> obtenerPersonas() {
        return personas;
    }

    public static boolean eliminarPersona(Persona p){
        for (int i = 0; i <personas.size() ; i++) {
            if(p.getCedula().equals(personas.get(i).getCedula())){
                personas.remove(i);
                return true;
            }
        }
        return false;

    }

    public static void setPersonas(ArrayList<Persona> per){
        personas = per;
    }

    public static void eliminarpersona(Persona P){
        dbreference.child(db).child(P.getId()).removeValue();
    }

}

