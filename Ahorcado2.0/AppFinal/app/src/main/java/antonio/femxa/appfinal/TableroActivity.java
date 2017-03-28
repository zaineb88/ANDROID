package antonio.femxa.appfinal;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;


public class TableroActivity extends AppCompatActivity {

    private String palabra,palabraAux;
    private int[] array_pics = {R.drawable.ic_cuerda, R.drawable.ic_cabeza, R.drawable.ic_cuerpo, R.drawable.ic_brazo, R.drawable.ic_brazos, R.drawable.ic_pierna};
    private static int contador;
    private static int tamaño_palabra;
    private static int contador_aciertos;
    private static Intent intent;
    private static boolean sonidoOnOff;
    private MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero);
        View v = findViewById(R.id.btnImagen);
        final ImageButton ib = (ImageButton) v;

        contador = 0;
        contador_aciertos = 0;

        palabra = getIntent().getStringExtra("palabra_clave");

///////// sonido

        mediaPlayer = MediaPlayer.create(this, R.raw.durantejugar);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100, 100);

        sonidoOnOff = getIntent().getBooleanExtra("SonidoOn-Off",true);

        if(sonidoOnOff)
        {
            mediaPlayer.start();
        }
        else
        {
            ib.setImageResource(R.drawable.ic_volume_off);
        }

        ib.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    ib.setImageResource(R.drawable.ic_volume_off);
                }else
                {
                    ib.setImageResource(R.drawable.ic_volume_up);
                    mediaPlayer.start();
                }
            }
        });


        palabraAux = palabra;

        tamaño_palabra = obtenerTamañoPalabra(palabraAux);

        ImageView imageView = (ImageView) findViewById(R.id.imagenes_ahorcado);
        imageView.setImageResource(array_pics[contador]);


        dibujarPanel(palabra);
        TableRow fila1 = (TableRow)findViewById(R.id.lugar_inflado);
        TableRow fila2 = (TableRow)findViewById(R.id.lugar_inflado2);
        TableRow fila3 = (TableRow)findViewById(R.id.lugar_inflado3);
        TableRow fila4 = (TableRow)findViewById(R.id.lugar_inflado4);

        fila1 = (fila1.getChildCount() == 0) ? null : fila1;
        fila2 = (fila2.getChildCount() == 0) ? null : fila2;
        fila3 = (fila3.getChildCount() == 0) ? null : fila3;
        fila4 = (fila4.getChildCount() == 0) ? null : fila4;

        identificarTextView(fila1, fila2, fila3, fila4);
        ocultarEspacios(palabra);

        TextView textViewCategoria = (TextView) findViewById(R.id.textviewcategoria);

        String categoria = getIntent().getStringExtra("categoria_seleccionada");

        textViewCategoria.setText(categoria);




    }

    public void mostrarLetra(String letra, String palabra)
    {

        char letrita = letra.charAt(0);
        for (int i = 0; i < palabra.length(); i++)
        {
            if (letrita == palabra.charAt(i))
            {
                //EditText et = (EditText)findViewById(i);
                TextView tv = (TextView) findViewById(i);
                tv.setText(letrita+"");
                Log.d("MENSAJE", "HA ENCONTRADO LA LETRA " + letrita);

            }
        }
    }

    public void ocultarEspacios(String palabra)
    {

        char letrita = ' ';
        for (int i = 0; i < palabra.length(); i++)
        {
            if (letrita == palabra.charAt(i))
            {
                //EditText et = (EditText)findViewById(i);
                TextView tv = (TextView) findViewById(i);
                tv.setVisibility(View.INVISIBLE);
                Log.d("MENSAJE", "HA ENCONTRADO LA LETRA " + letrita);

            }
        }
    }

    private void dibujarPanel (String palabra_oculta) {

        ViewGroup fila1 = (ViewGroup) TableroActivity.this.findViewById(R.id.lugar_inflado);
        ViewGroup fila2 = (ViewGroup) TableroActivity.this.findViewById(R.id.lugar_inflado2);
        ViewGroup fila3 = (ViewGroup) TableroActivity.this.findViewById(R.id.lugar_inflado3);
        ViewGroup fila4 = (ViewGroup) TableroActivity.this.findViewById(R.id.lugar_inflado4);

        int longi_palabra = palabra_oculta.length();
        LayoutInflater layoutInflater = TableroActivity.this.getLayoutInflater(); //o LayoutInflater.from(a)

        String[] lista_palabra = palabra_oculta.split(" ");

       /*for (int z = 0; z < lista_palabra.length-1; z++)
       {
           lista_palabra[z] = lista_palabra[z] + " ";
       }*/

        int pos_palabra = 0;
        int n_linea = 1;
        int caracteres_linea_actual = 0;


        for (int i = 0; i<longi_palabra; i++)
        {
            // CONTAR LÍNEAS CON SWITCH
            switch (n_linea)
            {
                case 1:
                    if(palabra_oculta.charAt(i)==' ')
                    {
                        pos_palabra++;
                        View v1 = layoutInflater.inflate(R.layout.panel, fila1, true);
                        if (lista_palabra[pos_palabra].length() + caracteres_linea_actual < 10)
                        {
                            caracteres_linea_actual++;
                            Log.d("MENSAJE", "if Case 1: letra " + palabra_oculta.charAt(i) + ", linea " + n_linea);
                        }
                        else
                        {
                            caracteres_linea_actual = 1;
                            n_linea = 2;
                            Log.d("MENSAJE", "Else Case 1: letra " + palabra_oculta.charAt(i) + ", linea " + n_linea);
                        }
                    }
                    else
                    {
                        View v1 = layoutInflater.inflate(R.layout.panel, fila1, true);
                        caracteres_linea_actual++;
                        Log.d("MENSAJE", "Case 1: letra " + palabra_oculta.charAt(i) + ", linea " + n_linea);
                    }

                    break;

                case 2:
                    if(palabra_oculta.charAt(i)==' ')
                    {
                        pos_palabra++;
                        View v1 = layoutInflater.inflate(R.layout.panel, fila2, true);
                        if (lista_palabra[pos_palabra].length() + caracteres_linea_actual < 10) {

                            caracteres_linea_actual++;
                            Log.d("MENSAJE", "Case 2: letra " + palabra_oculta.charAt(i) + ", linea " + n_linea);
                        } else {
                            caracteres_linea_actual = 1;
                            n_linea = 3;
                            Log.d("MENSAJE", "Else Case 2: letra " + palabra_oculta.charAt(i) + ", linea " + n_linea);
                        }
                    }
                    else
                    {
                        View v1 = layoutInflater.inflate(R.layout.panel, fila2, true);
                        caracteres_linea_actual++;
                        Log.d("MENSAJE","Case 2: letra " + palabra_oculta.charAt(i) + ", linea " + n_linea);
                    }



                    break;
                case 3:
                    if(palabra_oculta.charAt(i)==' ')
                    {
                        pos_palabra++;

                        View v1 = layoutInflater.inflate(R.layout.panel, fila3, true);
                        if(lista_palabra[pos_palabra].length()+caracteres_linea_actual<10)
                        {

                            caracteres_linea_actual++;
                            Log.d("MENSAJE", "Case 3: letra " + palabra_oculta.charAt(i) + ", linea " + n_linea);
                        }
                        else
                        {

                            caracteres_linea_actual = 1;
                            n_linea = 4;
                            Log.d("MENSAJE", "Else Case 3: letra " + palabra_oculta.charAt(i) + ", linea " + n_linea);
                        }
                    }
                    else
                    {
                        View v1 = layoutInflater.inflate(R.layout.panel, fila3, true);
                        caracteres_linea_actual++;
                        Log.d("MENSAJE", "Case 3: letra " + palabra_oculta.charAt(i) + ", linea " + n_linea);
                    }


                    break;

                case 4:
                    if(palabra_oculta.charAt(i)==' ')
                    {
                        pos_palabra++;

                        View v1 = layoutInflater.inflate(R.layout.panel, fila4, true);
                        if(lista_palabra[pos_palabra].length()+caracteres_linea_actual<10)
                        {
                            caracteres_linea_actual++;
                            Log.d("MENSAJE", "Case 4: letra " + palabra_oculta.charAt(i) + ", linea " + n_linea);
                        }
                        else
                        {
                            Log.d("MENSAJE", "La cadena tiene más extensión de la permitida");
                        }
                    }
                    else
                    {
                        View v1 = layoutInflater.inflate(R.layout.panel, fila4, true);
                        caracteres_linea_actual++;
                        Log.d("MENSAJE", "Case 4: letra " + palabra_oculta.charAt(i) + ", linea " + n_linea);
                    }

                    break;

                default:

                    break;

            }
        }
    }


    public void identificarTextView(ViewGroup rowLugarInflado1, ViewGroup rowLugarInflado2, ViewGroup rowLugarInflado3, ViewGroup rowLugarInflado4)
    {
        int cont_aux = 0;

        try {


            for (int i = 0; i < rowLugarInflado1.getChildCount(); i++) {
                ViewGroup linear = (ViewGroup) rowLugarInflado1.getChildAt(i);
                //EditText et = (EditText) linear.getChildAt(0);
                TextView tv = (TextView) linear.getChildAt(0);
                tv.setId(i);
                Log.d("MENSAJE", "editado EditText n: " + i);
                Log.d("MENSAJE", "Id de EditText: " + tv.getId());
                cont_aux++;
            }

            //  Log.d("MENSAJE", rowLugarInflado2.toString());
            if (rowLugarInflado2 != null) {

                for (int i = 0; i < (rowLugarInflado2.getChildCount()); i++) {

                    ViewGroup linear = (ViewGroup) rowLugarInflado2.getChildAt(i);
                    //EditText et = (EditText) linear.getChildAt(0);
                    TextView tv = (TextView) linear.getChildAt(0);
                    Log.d("MENSAJE", tv.toString());
                    tv.setId(cont_aux);
                    Log.d("MENSAJE", "editado EditText n: " + cont_aux);
                    Log.d("MENSAJE", "Id de EditText: " + tv.getId());
                    cont_aux++;
                }
            } else {
                Log.d("MENSAJE", "no hay segunda fila");
            }

            if (rowLugarInflado3 != null) {
                int cont_aux2 = 0;
                cont_aux2 = cont_aux;
                for (int i = 0; i < (rowLugarInflado3.getChildCount()); i++) {
                    ViewGroup linear = (ViewGroup) rowLugarInflado3.getChildAt(i);
                    //EditText tv = (EditText) linear.getChildAt(0);
                    TextView tv = (TextView) linear.getChildAt(0);
                    tv.setId(cont_aux);
                    Log.d("MENSAJE", "editado EditText n: " + cont_aux);
                    Log.d("MENSAJE", "Id de EditText: " + tv.getId());
                    cont_aux++;
                }
            } else {
                Log.d("MENSAJE", "no hay tercera fila");
            }

            if (rowLugarInflado4 != null) {
                int cont_aux2 = 0;
                cont_aux2 = cont_aux;
                for (int i = 0; i < (rowLugarInflado4.getChildCount()); i++) {
                    ViewGroup linear = (ViewGroup) rowLugarInflado4.getChildAt(i);
                    //EditText et = (EditText) linear.getChildAt(0);
                    TextView tv = (TextView) linear.getChildAt(0);
                    tv.setId(cont_aux);
                    Log.d("MENSAJE", "editado EditText n: " + cont_aux);
                    Log.d("MENSAJE", "Id de EditText: " + tv.getId());
                    cont_aux++;
                }
            } else {
                Log.d("MENSAJE", "no hay cuarta fila");
            }
        } catch (Throwable t)
        {
            Log.e("MENSAJE" , "ERROR", t);
        }
    }

    public void escribirNumero(View boton){
        // declaramos variables y hacemos el casteo del boton para usarle
        String palabra = getPalabra();
        Log.d("MENSAJE",palabra);
        Button btnPulsado = (Button) boton;
        String pulsado=  btnPulsado.getText().toString();//cogemos el texto del boton pulsado


        //nos creamos una variable boleana que nos dara si es falso o verdadero con lo que salga del metodo
        // haremos una condicion if en la que nos dira si la encuentra que cambie el texto del boton y lo ponga del color verde
        //sino que la ponga de color rojo y no deje pulsarla otra vez la deshabilita
        palabra = palabra.toUpperCase();
        boolean encontrada=buscarLetra(pulsado, palabra);
        if(encontrada)
        {
            letraAcertada(btnPulsado);
            mostrarLetra(pulsado, palabra);
        }
        else
        {
            letraFallada(btnPulsado);
        }
    }

    /**
     * Se cambia a verde el boton introducido y si el contador_aciertos es igual al tamaño de la palabra oculta
     * se redirige a VictoriaActivity
     * @param button
     */
    public void letraAcertada(Button button){

        button.setTextColor(Color.rgb(34, 153, 84));
        button.setEnabled(false);

        Log.d("MENSAJE",contador_aciertos+" contador");
        Log.d("MENSAJE",tamaño_palabra+" tamaño");

        if(contador_aciertos == tamaño_palabra)
        {
            intent = new Intent(TableroActivity.this, VictoriaActivity.class);

            intent.putExtra("palabra_clave",palabra);

            intent.putExtra("SonidoOn-Off",sonidoOnOff);

            startActivity(intent);
        }
    }

    /**
     * Cambia el color a rojo e inutiliza el boton introducido, si el contador de fallos
     * es igual a 6 se redirige a DerrotaActivity, si no, cambia la imagen del ahoracado
     * @param button
     */
    public void letraFallada(Button button){
        button.setTextColor(Color.RED);
        button.setEnabled(false);
        contador++;

        if(contador == 6)
        {
            intent = new Intent(TableroActivity.this, DerrotaActivity.class);

            intent.putExtra("palabra_clave",palabra);

            intent.putExtra("SonidoOn-Off",sonidoOnOff);

            startActivity(intent);
        }//puede ocurrir que antes de que acabe el intent se pueda pulsar otra vez el boton, por lo tanto
        //se pone el limitador de menor que 6 para que no se introduzca, en caso de haber pulsado otra vez, un 7
        else if (contador <6)
        {
            ImageView imageView = (ImageView) findViewById(R.id.imagenes_ahorcado);
            imageView.setImageResource(array_pics[contador]);
        }
    }

    /**
     * Busca en una palabra una letra introducidas, cada vez que encuentre esa letra en la
     * palabra se añade mas uno al contador_palabra_verdadero y devuelve un true
     * @param letra
     * @param palabra
     * @return
     */
    public boolean  buscarLetra(String letra, String palabra){
        boolean encontrado = false;
        char letrita= letra.charAt(0);
        for(int i=0; i<palabra.length(); i++){
            if(letrita == palabra.charAt(i))
            {
                encontrado=true;
                contador_aciertos++;
            }
        }

        return encontrado;
    }

    public String getPalabra(){
        return palabra;
    }

    /**
     * Dada una palabra introducida te dice su numero de letras, no cuenta los espacios
     * @param palabra
     * @return numero de posiciones de esa palabra
     */
    public int obtenerTamañoPalabra(String palabra){
        int contador = 0;

        palabra = palabra.replace(" ","");

        for(int i = 0;i<palabra.length();i++)
        {
            contador++;
        }

        return contador;
    }


     @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }


}
