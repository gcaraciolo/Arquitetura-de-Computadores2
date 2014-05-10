package com.unicap.audiorec;


import java.util.ArrayList;
import java.util.Locale;

import com.unicap.unicap_arq2.R;
import com.unicap.unicap_arq2.TextoAudio;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
 
public class Audio extends Activity implements OnInitListener {
 
    protected static final int RESULT_SPEECH = 1;
    TextToSpeech ttobj;
    private ImageButton btnSpeak;
    private TextView txtText;
    String texto;
    int saida=0;
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_audio);
        Intent it  = getIntent();
		if(it != null){
		  Bundle params  = it.getExtras();
		  if(params != null){
		    texto   = params.getString("onibus");
		  }
		}
        
        
        txtText = (TextView) findViewById(R.id.txtText);
 
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        
                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
 
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "pt-BR");
 
                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    txtText.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
               
            
        
        
           
            
    }
 
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.audio, menu);
        return true;
    }
 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
 
        switch (requestCode) {
        case RESULT_SPEECH: {
            if (resultCode == RESULT_OK && null != data) {
 
                ArrayList<String> text = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
 
                txtText.setText(text.get(0));
                trocardeTela();
            }
            break;
        }
 
        }
    }
    void trocardeTela(){
    	Intent textoAudio = new Intent(this, TextoAudio.class);
    	 texto = txtText.getText().toString();
         Bundle parametros = new Bundle();
			parametros.putString("onibus",texto);
			textoAudio.putExtras(parametros);// adiciona os parametros a intent
         startActivity(textoAudio);
    }

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		
	}
}