package com.example.android.personasmaterialdiplomado;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetallePersona extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Persona p;
    private String id, cedula,nombre,apellido;
    private int fot,sexo;
    private Bundle bundle;
    private Intent i;
    private ImageView foto;
    private Resources res;
    private TextView ced,nomb,app,sex;
    private String [] opc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_persona);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
       setSupportActionBar(toolbar);

        ced =(TextView)findViewById(R.id.lblCedulaD);
        nomb = (TextView)findViewById(R.id.lblNombreD);
        app = (TextView)findViewById(R.id.lblApellidoD);
        sex = (TextView)findViewById(R.id.lblSexoD);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        foto = (ImageView) findViewById(R.id.fotoPersona);
        res = this.getResources();
        i = getIntent();
        bundle = i.getBundleExtra("datos");

        id = bundle.getString("id");
        cedula = bundle.getString("cedula");
        nombre = bundle.getString("nombre");
        apellido = bundle.getString("apellido");
        fot = bundle.getInt("foto");
        sexo = bundle.getInt("sexo");

        opc = res.getStringArray(R.array.sexo);


        collapsingToolbarLayout.setTitle(nombre+" "+apellido);
        foto.setImageDrawable(ResourcesCompat.getDrawable(res,fot,null));
        ced.setText(cedula);
        nomb.setText(nombre);
        app.setText(apellido);
        sex.setText(opc[sexo]);


    }

    public void editar(View v){
        Intent i = new Intent(DetallePersona.this, EditarPersona.class);
        Bundle b = new Bundle();
        b.putString("id", id);
        b.putString("cedula",cedula);
        b.putString("nombre",nombre);
        b.putString("apellido",apellido);
        b.putInt("sexo",sexo);
        b.putInt("foto",fot);

        i.putExtra("editar",b);
        startActivity(i);
    }

    public void eliminar(View v){
        String positivo,negativo;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(res.getString(R.string.titulo_eliminar_mensaje));
        builder.setMessage(res.getString(R.string.eliminar_mensaje));
        positivo = res.getString(R.string.si_eliminar_mensaje);
        negativo = res.getString(R.string.no_eliminar_mensaje);



        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Persona p = new Persona(id);
                p.eliminar();
                onBackPressed();

            }
        });
        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();



    }
    public void onBackPressed(){
        finish();
        Intent i = new Intent(DetallePersona.this,Principal.class);
        startActivity(i);
    }
}
