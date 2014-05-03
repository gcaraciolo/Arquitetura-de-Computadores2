package com.unicap.unicap_arq2;
import java.util.Locale;
import java.util.Random;

import com.unicap.unicap_arq2.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TextoAudio extends Activity {

   TextToSpeech ttobj;
   private EditText write;
   String texto;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.fragment_texto_audio);
      Intent it  = getIntent();
		if(it != null){
		  Bundle params  = it.getExtras();
		  if(params != null){
		    texto   = params.getString("onibus");
		  }
		}
		
      
      
      write = (EditText)findViewById(R.id.editText1);
      
      ttobj=new TextToSpeech(getApplicationContext(), 
      new TextToSpeech.OnInitListener() {
      @Override
      public void onInit(int status) {
         if(status != TextToSpeech.ERROR){
             ttobj.setLanguage(Locale.getDefault());
            }				
         }
      });
   }
   @Override
   public void onPause(){
      if(ttobj !=null){
         ttobj.stop();
         ttobj.shutdown();
      }
      super.onPause();
   }
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }
   public void speakText(View view){
	   String toSpeak = "voce quis dizer"+texto;
      //String toSpeak = write.getText().toString();
      Toast.makeText(getApplicationContext(), toSpeak, 
      Toast.LENGTH_SHORT).show();
      ttobj.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

   }
}