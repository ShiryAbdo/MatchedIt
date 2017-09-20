package com.example.falcon.matchedit;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.FeatureInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.falcon.matchedit.gameMomary.AnimailData;
import com.example.falcon.matchedit.gameMomary.BirdData;
import com.example.falcon.matchedit.gameMomary.Butterfly;
import com.example.falcon.matchedit.gameMomary.ImagReTag;
import com.example.falcon.matchedit.gameMomary.NumberData;
import com.example.falcon.matchedit.gameMomary.flawersData;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    AnimailData animailData ;
    ArrayList<ImagReTag>arrayList  ,AreeNewRandom;
    ImageView chose_one  ,chose_two,chose_three,chose_four;
    ImageView answer_one ,answer_two,answer_three,answer_four;
    private Bitmap bmp;
    private Bitmap operation;
    Random random = new Random();
    int nextImage ;
    int range  , newRange  ;
    flawersData flawersData ;
    int myImgCount = 0;
    ArrayList<Integer>index,this_New;
    ArrayList<Integer> newIndex;
    int [] mmm ;
    Bundle bundle ;
    TextToSpeech t1;
    String catogery;
    BirdData birdData;
    NumberData numberData;
    Butterfly butterfly;
    String animal_catogery ="animal_catogery";
    String birds_catogery="birds_catogery";
    String numbers_catogery ="numbers_catogery";
    SharedPreferences prefs = null;
     TextView go_next;
    int count ;
    SharedPreferences.Editor editor;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bundle=getIntent().getExtras();
        catogery=bundle.getString("catogery");
        numberData =new NumberData();
        prefs =  getApplicationContext().getSharedPreferences(catogery, Context.MODE_PRIVATE);
        editor = prefs.edit();
        flawersData =new flawersData();
        butterfly = new Butterfly();
        boolean check =prefs.getBoolean("firstRun", true);

        if (check==true) {
            count= 0;
            Toast.makeText(MainActivity.this,"true",Toast.LENGTH_LONG).show();


        }if (check==false){
            count=  prefs.getInt("newcount",0);
            Toast.makeText(MainActivity.this,"false",Toast.LENGTH_LONG).show();


        }

//        Voice[Name: en-AU-afi-network, locale: en_AU, quality: 500, latency: 400, requiresNetwork: true, features: [networkTimeoutMs, networkRetriesCount, male]]
// Speech rate. 1.0 is the normal speech rate, lower values slow down the speech (0.5 is half the normal speech rate), greater values accelerate it (2.0 is twice the normal speech rate).

//        Voice (String name,
//                Locale locale,
//        int quality,
//        int latency,
//        boolean requiresNetworkConnection,
//        Set<String> features)
        Set<String> featc;
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setSpeechRate((float) 0.5);
                    t1.setLanguage(Locale.US);

                  }
            }
        });

        AreeNewRandom =new ArrayList<>();
        chose_one=(ImageView)findViewById(R.id.chose_one);
        chose_two=(ImageView)findViewById(R.id.chose_two);
        chose_three=(ImageView)findViewById(R.id.chose_three);
        chose_four=(ImageView)findViewById(R.id.chose_four);
        answer_one=(ImageView)findViewById(R.id.answer_one);
        answer_two=(ImageView)findViewById(R.id.answer_two);
        answer_three=(ImageView)findViewById(R.id.answer_three);
        answer_four=(ImageView)findViewById(R.id.answer_four);




        birdData =new BirdData();
//        Toast.makeText(MainActivity.this,catogery,Toast.LENGTH_LONG).show();


        go_next =(TextView) findViewById(R.id.go_next);
        arrayList=new ArrayList<>();
        index=new ArrayList<>();
        this_New=new ArrayList<>();
        animailData= new AnimailData();

        if (catogery.equals(animal_catogery)){
            arrayList=animailData.getAimalArray();

        }
        if(catogery.equals(birds_catogery)){

            arrayList=birdData.getBirdDataArrayArray();
        }if(catogery.equals(numbers_catogery)){
            arrayList=numberData.getnumberArray();

        }if(catogery.equals("flawer_catogery")){
            arrayList=flawersData.getFlawerArray();
        }if(catogery.equals("Butterfly_catogery")){
            arrayList=butterfly.getbutterflyArray();
        }

        newIndex= new ArrayList<>();
        range = arrayList.size();
        myImgCount = 0;

        mmm = new int[]{0, 1, 2, 3};
        newIndex=getAlArray();

        if (count ==0){
            chose_two.setVisibility(View.GONE);
            chose_three.setVisibility(View.GONE);
            chose_four.setVisibility(View.GONE);
            answer_two.setVisibility(View.GONE);
            answer_three.setVisibility(View.GONE);
            answer_four.setVisibility(View.GONE);
            AreeNewRandom.add(arrayList.get(0));

            chose_one.setImageResource(arrayList.get(0).getImage());
            chose_one.setTag(arrayList.get(0).getTage());
            String tagecH1 =arrayList.get(0).getTage();


            answer_one.setImageResource(arrayList.get(0).getImage());
            answer_one.setTag(arrayList.get(0).getTage());

        }else if (count<=10 &&count>=1) {
            chose_three.setVisibility(View.GONE);
            chose_four.setVisibility(View.GONE);
            answer_three.setVisibility(View.GONE);
            answer_four.setVisibility(View.GONE);

            chose_one.setImageResource(arrayList.get(1).getImage());
            chose_one.setTag(arrayList.get(1).getTage());
            String tagecH1 =arrayList.get(1).getTage();

            chose_two.setImageResource(arrayList.get(0).getImage());
            chose_two.setTag(arrayList.get(0).getTage());


            answer_one.setImageResource(arrayList.get(0).getImage());
            answer_one.setTag(arrayList.get(0).getTage());

            answer_two.setImageResource(arrayList.get(1).getImage());
            answer_two.setTag(arrayList.get(1).getTage());

        }else  if (count<=20 &&count>10){


             chose_four.setVisibility(View.GONE);
             answer_four.setVisibility(View.GONE);

            chose_one.setImageResource(arrayList.get(0).getImage());
            chose_one.setTag(arrayList.get(0).getTage());
            String tagecH1 =arrayList.get(0).getTage();
            Log.d("tagecH1",tagecH1);

            chose_two.setImageResource(arrayList.get(2).getImage());
            chose_two.setTag(arrayList.get(2).getTage());

            chose_three.setImageResource(arrayList.get(1).getImage());
            chose_three.setTag(arrayList.get(1).getTage());


            answer_one.setImageResource(arrayList.get(2).getImage());
            answer_one.setTag(arrayList.get(2).getTage());


            answer_two.setImageResource(arrayList.get(0).getImage());
            answer_two.setTag(arrayList.get(0).getTage());


            answer_three.setImageResource(arrayList.get(1).getImage());
            answer_three.setTag(arrayList.get(1).getTage());


        }else if(count<=30&&count>12){

       chose_one.setImageResource(arrayList.get(0).getImage());
       chose_one.setTag(arrayList.get(0).getTage());
       String tagecH1 =arrayList.get(0).getTage();
       Log.d("tagecH1",tagecH1);

       chose_two.setImageResource(arrayList.get(1).getImage());
       chose_two.setTag(arrayList.get(1).getTage());

       chose_three.setImageResource(arrayList.get(2).getImage());
       chose_three.setTag(arrayList.get(2).getTage());

       chose_four.setImageResource(arrayList.get(3).getImage());
       chose_four.setTag(arrayList.get(3).getTage());

       answer_one.setImageResource(arrayList.get(index.get(0)).getImage());
       answer_one.setTag(arrayList.get(index.get(0)).getTage());


       answer_two.setImageResource(arrayList.get(index.get(1)).getImage());
       answer_two.setTag(arrayList.get(index.get(1)).getTage());


       answer_three.setImageResource(arrayList.get(index.get(2)).getImage());
       answer_three.setTag(arrayList.get(index.get(2)).getTage());


       answer_four.setImageResource(arrayList.get(index.get(3)).getImage());
       answer_four.setTag(arrayList.get(index.get(3)).getTage());

   }





        chose_one.setOnTouchListener(new MyTouchListener() );
        chose_two.setOnTouchListener(new MyTouchListener() );
        chose_three.setOnTouchListener(new MyTouchListener() );
        chose_four.setOnTouchListener(new MyTouchListener() );
//****************************************************************************************************************************
//        ********************************************************************************************************************






        answer_one.setOnDragListener(new MyDragListener() );
        answer_two.setOnDragListener(new MyDragListener() );
        answer_three.setOnDragListener(new MyDragListener() );
        answer_four.setOnDragListener(new MyDragListener() );

        image(answer_one);
        image(answer_two);
        image(answer_three);
        image(answer_four);


        newRange=  AreeNewRandom.size();

        go_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs =  getApplicationContext().getSharedPreferences(catogery , Context.MODE_PRIVATE);
                editor = prefs.edit();
                int newcount =  count++;
         if(newcount<39){
           editor.putBoolean("firstRun",false);
           editor.putInt("newcount",count++);
           editor.apply();
           Intent intent =new Intent(getApplicationContext(),MainActivity.class);
           intent.putExtra("catogery",bundle.getString("catogery"));
             finish();
           startActivity(intent);
    }else {

         Intent intent =new Intent(getApplicationContext(),CatogeryMenuActivity.class);
         editor.putBoolean("firstRun",true);
         editor.commit();
             finish();
         startActivity(intent);
    }


            }
        });


    }



    public  ImageView image (ImageView imageView){


        ColorFilter filter = new LightingColorFilter( Color.WHITE, Color.rgb(50,50,50) );

        BitmapDrawable abmp = (BitmapDrawable)  imageView.getDrawable();
        bmp = abmp.getBitmap();

        operation= Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),bmp.getConfig());

        for(int i=0; i<bmp.getWidth(); i++){
            for(int j=0; j<bmp.getHeight(); j++){
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);

//                r =50  ;
//                g = 50  ;
//                b = 50  ;
                alpha =  150+alpha;
                operation.setPixel(i, j, Color.argb(50, r, g, b));
            }
        }

        imageView.setImageBitmap(operation);
//        imageView.setColorFilter( filter);


        return  imageView;

    }

    public  ArrayList<Integer> getAlArray() {
        this_New.add(0);
        this_New.add(1);
        this_New.add(2);
        this_New.add(3);


        for( int i = 0; i < 10; ++i)
        {
            myImgCount = random.nextInt(range);
            if(myImgCount<this_New.size()){
                if( !index.contains(myImgCount)){
                    index.add(myImgCount);


                }
            }

        }


        if(index.size()<this_New.size()){

            for( int i = 0; i <10; ++i)
            {
                myImgCount = random.nextInt(range);

                if(myImgCount<this_New.size()&&this_New.size()>myImgCount){
                    if( !index.contains(myImgCount)){
                        index.add(myImgCount);


                    }
                }

            }
        }

        if(index.size()==3){

            for( int i = 0; i <10; ++i)
            {
                myImgCount = random.nextInt(range);

                if(myImgCount<this_New.size()&&this_New.size()>myImgCount){
                    if( !index.contains(myImgCount)){
                        index.add(myImgCount);


                    }
                }

            }
        }


        return index;
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {
//        Drawable enterShape = getResources().getDrawable(
//                R.drawable.shape_droptarget);
//        Drawable normalShape = getResources().getDrawable(R.drawable.shape);

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            ImageView view = (ImageView) event.getLocalState();
            String tag2 = String.valueOf(view.getTag());
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    view.setVisibility(View.VISIBLE);
                     Toast.makeText(getApplicationContext(),tag2,Toast.LENGTH_SHORT).show();
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    view.setVisibility(View.VISIBLE);
                   // Toast.makeText(getApplicationContext(),tag2,Toast.LENGTH_SHORT).show();
//                    v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                //    Toast.makeText(getApplicationContext(),"exited",Toast.LENGTH_SHORT).show();
                    view.setVisibility(View.VISIBLE);
                    //                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    view = (ImageView) event.getLocalState();
                     view.setVisibility(View.VISIBLE);



                    final ImageView test = (ImageView)v;

                    final ImageView test2 = (ImageView)view;

                    final Bitmap bmap = ((BitmapDrawable)test.getDrawable()).getBitmap();
                    final Bitmap bmap2 = ((BitmapDrawable)test2.getDrawable()).getBitmap();

                    if(((ImageView)v).getTag()==((ImageView)view).getTag()) {
//
//                       if((ImageView)v).getDrawable().equals()){
                      String toSpeak = (String) ((ImageView)view).getTag();
                        Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
                        ((ImageView)v).setImageDrawable(((ImageView)view).getDrawable());

                        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                        int amStreamMusicMaxVol = am.getStreamMaxVolume(am.STREAM_MUSIC);
                        am.setStreamVolume(am.STREAM_MUSIC, amStreamMusicMaxVol, 0);
                        t1.speak(toSpeak, TextToSpeech.LANG_MISSING_DATA, null);
//                        t1.speak(toSpeak,TextToSpeech.QUEUE_ADD,null);

//
                    }else{
                        Toast.makeText(getApplicationContext()," note done",Toast.LENGTH_SHORT).show();
                    }
//

//
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    view.setVisibility(View.VISIBLE);
                  //  Toast.makeText(getApplicationContext(),"ended",Toast.LENGTH_SHORT).show();

                    break;
                default:
                    view.setVisibility(View.VISIBLE);
            }
            return true;
        }
    }


    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this,   CatogeryMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
