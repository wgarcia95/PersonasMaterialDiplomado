package com.example.android.personasmaterialdiplomado;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class EditarPersona extends AppCompatActivity {
    private EditText txtCedula;
    private EditText txtNombre;
    private EditText txtApellido;
    private TextInputLayout cajaCedula;
    private TextInputLayout cajaNombre;
    private TextInputLayout cajaApellido;
    private Persona persona;
    private ArrayList<Integer> fotos;
    private Resources res;
    private Spinner sexo;
    private ArrayAdapter<String> adapter;
    private String[] opc;

    private Bundle bundle;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_persona);

        res = this.getResources();
        i = getIntent();
        bundle = i.getBundleExtra("editar");

        txtCedula = (EditText)findViewById(R.id.txtEditarCedula);
        txtNombre = (EditText)findViewById(R.id.txtEditarNombre);
        txtApellido = (EditText)findViewById(R.id.txtEditarApellido);
        cajaCedula = (TextInputLayout)findViewById(R.id.cajaEditarCedula);
        cajaNombre = (TextInputLayout)findViewById(R.id.cajaEditarNombre);
        cajaApellido = (TextInputLayout)findViewById(R.id.cajaEditarApellido);
        sexo = (Spinner)findViewById(R.id.cmbEditarSexo);
        opc = res.getStringArray(R.array.sexo);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opc);
        sexo.setAdapter(adapter);
        persona = new Persona(bundle.getInt("foto"),bundle.getString("cedula"),
                                bundle.getString("nombre"), bundle.getString("apellido"),
                                bundle.getInt("sexo"));

        reiniciar();
    }

    public void editar(View v){
        int pos = Metodos.obtenerPosicionGuardada(Datos.obtenerPersonas(), persona);
        Persona personaEdit = new Persona(persona.getFoto(), txtCedula.getText().toString(),
                txtNombre.getText().toString(), txtApellido.getText().toString(), sexo.getSelectedItemPosition());
        if (Metodos.iguales(persona, personaEdit) == false){
            if (persona.getCedula().equals(personaEdit.getCedula())){
                if (validar_otros_campos()){
                    Datos.editarPErsona(pos, personaEdit);
                    persona = personaEdit;
                    onBackPressed();
                }
            }else{
                if(validar()) {
                    Datos.editarPErsona(pos, personaEdit);
                    persona = personaEdit;
                    onBackPressed();
                }
            }
        }else{
            Snackbar.make(v, res.getString(R.string.mensaje_guardado), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            onBackPressed();
        }

    }

    public boolean validar(){
        if (validar_aux(txtCedula,cajaCedula)) return false;
        else  if (validar_aux(txtNombre,cajaNombre)) return false;
        else  if (validar_aux(txtApellido,cajaApellido)) return false;
        else if (Metodos.exitencia_persona(Datos.obtenerPersonas(),txtCedula.getText().toString())){
            txtCedula.setError(res.getString(R.string.persona_existente_error));
            txtCedula.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validar_otros_campos(){
        if (validar_aux(txtCedula,cajaCedula)) return false;
        else  if (validar_aux(txtNombre,cajaNombre)) return false;
        else  if (validar_aux(txtApellido,cajaApellido)) return false;
        return true;
    }

    public boolean validar_aux(TextView t, TextInputLayout ct) {
        if (t.getText().toString().isEmpty()) {
            t.requestFocus();
            t.setError(res.getString(R.string.no_vacio_error));
            return true;
        }
        return false;
    }

    private void reiniciar(){
        txtCedula.setText(persona.getCedula());
        txtNombre.setText(persona.getNombre());
        txtApellido.setText(persona.getApellido());
        sexo.setSelection(persona.getSexo());
        txtCedula.requestFocus();
    }
    public void reiniciar(View v){
        reiniciar();
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(EditarPersona.this, DetallePersona.class);
        Bundle b = new Bundle();
        b.putString("cedula",persona.getCedula());
        b.putString("nombre",persona.getNombre());
        b.putString("apellido",persona.getApellido());
        b.putInt("sexo",persona.getSexo());
        b.putInt("foto",persona.getFoto());

        i.putExtra("datos",b);
        startActivity(i);
    }
}
