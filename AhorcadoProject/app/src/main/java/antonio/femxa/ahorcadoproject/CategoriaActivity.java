package antonio.femxa.ahorcadoproject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;


public class CategoriaActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private Spinner spCategorias;
    private static MediaPlayer mediaPlayer;
    private static Intent intent;
    private static boolean musicaOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        this.spCategorias = (Spinner) findViewById(R.id.spinner_categorias);

        loadSpinnerCategorias();

    }

    /**
     * Cada vez que el activity vuelva de una pausa el spinner se coloca en la posicion selecciona una categoria
     */
    @Override
    protected void onResume() {
        super.onResume();
        Spinner spinner = (Spinner) findViewById(R.id.spinner_categorias);
        spinner.setSelection(0);

        musicaOnOff = getIntent().getBooleanExtra("SonidoOn-Off",true);


        mediaPlayer = MediaPlayer.create(this, R.raw.inicio);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100, 100);

        if(musicaOnOff)
        {
            mediaPlayer.start();
        }

        ponerMusica();
    }

    public void abrirCreditos (View v)
    {
        Intent intent = new Intent(this, CreditosActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        View v = findViewById(R.id.btnImagen);
        final ImageButton ib = (ImageButton) v;

        mediaPlayer.stop();

        if(!musicaOnOff)
            ib.setImageResource(R.drawable.ic_volume_up);}

    /**
     * Cargamos el spinner con el array que esta en categorias.xml
     */
    public void loadSpinnerCategorias() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categorias, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.spCategorias.setAdapter(adapter);

        this.spCategorias.setOnItemSelectedListener(this);
    }

    /**
     * Cada vez que se cambie el spinner y no sea la posicion 0(selecciona una categoria) carga
     * el array conrrespondiente de esa categoria, obtiene un string aleatorio de ella
     * y se redirige a activity_tablero con el string conseguido
     * @param parent
     * @param view
     * @param pos La posicion del array de categorias
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        if(pos!=0)
        {
            TypedArray array_categorias = getResources().obtainTypedArray(R.array.array_categorias);
            CharSequence [] array_especifico = array_categorias.getTextArray(pos);
            array_categorias.recycle();

            String palabra = palabraOculta(array_especifico);

            Log.d("MENSAJE2",palabra);

            intent = new Intent(CategoriaActivity.this, TableroActivity.class);

            intent.putExtra("palabra_clave",palabra);

            if(musicaOnOff)
            {
                intent.putExtra("SonidoOn-Off",true);
            }else
            {
                intent.putExtra("SonidoOn-Off",false);
            }

            startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    /**
     * Dado un array de strings te devuelve un string aleatorio de ese array
     * @param array_especifico
     * @return
     */
    public String palabraOculta( CharSequence [] array_especifico){
        String palabra = null;

            int aleatoria = (int) (Math.random() * (array_especifico.length));
            Log.d("MENSAJE", aleatoria + "");
            palabra = array_especifico[aleatoria].toString();

        return palabra;

    }


   public void ponerMusica() {
        super.onStart();


        View v = findViewById(R.id.btnImagen);
        final ImageButton ib = (ImageButton) v;

        ib.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    ib.setImageResource(R.drawable.ic_volume_off);
                    musicaOnOff = false;
                }
                else
                {
                   // ;
                    ib.setImageResource(R.drawable.ic_volume_up);
                    mediaPlayer = MediaPlayer.create(CategoriaActivity.this, R.raw.inicio);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.setVolume(100, 100);
                    mediaPlayer.start();
                    musicaOnOff = true;
                }
            }
        });
    }
}
