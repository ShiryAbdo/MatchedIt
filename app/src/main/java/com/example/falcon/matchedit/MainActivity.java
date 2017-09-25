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
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
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


public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;

    AnimailData animailData ;
    ArrayList<ImagReTag>arrayList  ,AreeNewRandom;
    ImageView chose_one  ,chose_two,chose_three,chose_four;
    ImageView answer_one ,answer_two,answer_three,answer_four;
     Random random = new Random();
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
    int score  ,checkScore;
    SharedPreferences.Editor editor;
    Typewriter nameOfDrage ;

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
        nameOfDrage =(Typewriter)findViewById(R.id.nameOfDrage);

        if (check==true) {
            count= 0;
            Toast.makeText(MainActivity.this,"true",Toast.LENGTH_LONG).show();


        }if (check==false){
            count=  prefs.getInt("newcount",0);
            Toast.makeText(MainActivity.this,"false",Toast.LENGTH_LONG).show();


        }

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

            score= 0;

        }else  if(count<4 &&count>=1){
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

            score= 0;

        }


        else if (count<=10 &&count>=4) {
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
            score=1;

        }else  if (count<=25 &&count>10){


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
            score=2;


            answer_two.setImageResource(arrayList.get(0).getImage());
            answer_two.setTag(arrayList.get(0).getTage());


            answer_three.setImageResource(arrayList.get(1).getImage());
            answer_three.setTag(arrayList.get(1).getTage());


        }else if(count<=50&&count>26){

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
            score=3;

        }



                image(answer_one);
                image(answer_two);
                image(answer_three);
                image(answer_four);



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


    public  ImageView Recolor (ImageView imageView){




//
        ColorFilter filter = new LightingColorFilter( Color.WHITE, Color.rgb(30,30,30) );
        imageView.setColorFilter( filter);




        return  imageView;

    }

    public  ImageView image (ImageView imageView){


//        ColorFilter filter = new LightingColorFilter( Color.WHITE, Color.rgb(50,50,50) );
//
//        BitmapDrawable abmp = (BitmapDrawable)  imageView.getDrawable();
//        bmp = abmp.getBitmap();
//
//        operation= Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),bmp.getConfig());
//
//        for(int i=0; i<bmp.getWidth(); i++){
//            for(int j=0; j<bmp.getHeight(); j++){
//                int p = bmp.getPixel(i, j);
//                int r = Color.red(p);
//                int g = Color.green(p);
//                int b = Color.blue(p);
//                int alpha = Color.alpha(p);
//
//
//                operation.setPixel(i, j, Color.argb(50, r, g, b));
//            }
//        }
//
//        imageView.setImageBitmap(operation);
////        imageView.setColorFilter( filter);


//
        ColorFilter filter = new LightingColorFilter( Color.WHITE, Color.rgb(50,80,50)+250 );
        imageView.setColorFilter( filter);
//        PorterDuffColorFilter greyFilter = new PorterDuffColorFilter(Color.rgb(50,50,50) , PorterDuff.Mode.MULTIPLY);
//        imageView.setColorFilter(greyFilter);



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
                 return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener  {

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            ImageView view = (ImageView) event.getLocalState();
             switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:

//                        Toast.makeText(getApplicationContext(),"started",Toast.LENGTH_SHORT).show();
                    if(view!=null){
                        view.setVisibility(View.VISIBLE);
                    }

                     break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    if(view!=null)
                    view.setVisibility(View.VISIBLE);
//                     Toast.makeText(getApplicationContext(),"entered",Toast.LENGTH_SHORT).show();
                     break;
                case DragEvent.ACTION_DRAG_EXITED:
//                        Toast.makeText(getApplicationContext(),"exited",Toast.LENGTH_SHORT).show();
                    if(view!=null)
                    view.setVisibility(View.VISIBLE);
                     break;
                case DragEvent.ACTION_DROP:
//                    Toast.makeText(getApplicationContext(),"drobeded",Toast.LENGTH_SHORT).show();

                     view = (ImageView) event.getLocalState();
                    if(view!=null)
                    view.setVisibility(View.VISIBLE);



                    final ImageView test = (ImageView)v;

                    final ImageView test2 = (ImageView)view;

                    final Bitmap bmap = ((BitmapDrawable)test.getDrawable()).getBitmap();
                    final Bitmap bmap2 = ((BitmapDrawable)test2.getDrawable()).getBitmap();

                    if(((ImageView)v).getTag()==((ImageView)view).getTag()) {
//
                         String toSpeak = (String) ((ImageView)view).getTag();
                        Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
                        ((ImageView)v).setImageDrawable(((ImageView)view).getDrawable());
                        Recolor(((ImageView)v));
                        final int num =checkScore++;
                        Toast.makeText(getApplicationContext(),num+"",Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),score+"",Toast.LENGTH_SHORT).show();

                        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                        int amStreamMusicMaxVol = am.getStreamMaxVolume(am.STREAM_MUSIC);
                        am.setStreamVolume(am.STREAM_MUSIC, amStreamMusicMaxVol, 0);
                        nameOfDrage.setCharacterDelay(150);
                        nameOfDrage.animateText(toSpeak);
//                        nameOfDrage.setText("toSpeak");
                        t1.speak(toSpeak, TextToSpeech.LANG_MISSING_DATA, null);
//                        t1.speak(toSpeak,TextToSpeech.QUEUE_ADD,null);

                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {

                                if (num==score){
//                            Toast.makeText(getApplicationContext(),"congratulations",Toast.LENGTH_SHORT).show();
                                    t1.speak("will done",TextToSpeech.QUEUE_ADD,null);

                                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.TransparentDialog));
                                    View alertView;
                                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                                    alertView = inflater.inflate(R.layout.custom_alert_layout, null);
                                    dialogBuilder.setView(alertView);
                                    dialogBuilder.setCancelable(false);


                                    final ImageView img_animation = (ImageView)alertView.findViewById(R.id.img_animation);
                                    final ImageView newImage =(ImageView)alertView.findViewById(R.id.newImage);
                                    Button nextgame =(Button)alertView.findViewById(R.id.nextgame);
                                    Button backToMenu =(Button)alertView.findViewById(R.id.backToMenu);
                                    backToMenu.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent =new Intent(getApplicationContext(),CatogeryMenuActivity.class);
                                             finish();
                                            startActivity(intent);

                                        }
                                    });
                                    nextgame.setOnClickListener(new View.OnClickListener() {
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

                                    TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                                            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                                            0.0f, Animation.RELATIVE_TO_SELF, -5.0f);

                                    //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
                                    animation.setDuration(900);  // animation duration
                                    animation.setRepeatCount(2);  // animation repeat count
                                    animation.setRepeatMode(2);   // repeat animation (left to right, right to left )
                                    animation.setFillAfter(true);



                                    animation.setAnimationListener(new Animation.AnimationListener() {

                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {
                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {

                                            img_animation.clearAnimation();
                                            img_animation.setVisibility(View.GONE);



                                        }

                                    });

                                    img_animation.startAnimation(animation);

                                    TranslateAnimation anation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                                            Animation.RELATIVE_TO_SELF, 5.0f, Animation.RELATIVE_TO_SELF,
                                            0.0f, Animation.RELATIVE_TO_SELF, -5.0f);
//                                    TranslateAnimation anmation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
//                                            400.0f, -5.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
                                    anation.setDuration(500);  // animation duration
                                    anation.setRepeatCount(5);  // animation repeat count
                                    anation.setRepeatMode(2);   // repeat animation (left to right, right to left )
                                    anation.setFillAfter(true);
                                    anation.setAnimationListener(new Animation.AnimationListener() {

                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {
                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {

                                            newImage.clearAnimation();
                                            newImage.setVisibility(View.GONE);



                                        }

                                    });

                                    newImage.startAnimation(anation);

                                    AlertDialog alertDialog = dialogBuilder.create();
                                    alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                                    alertDialog.show();
                                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                                    lp.copyFrom(alertDialog.getWindow().getAttributes());
                                    lp.width = 600;
                                    lp.height = 800;
                                    alertDialog.getWindow().setAttributes(lp);

                                }
                            }
                        }, SPLASH_TIME_OUT);


                        break;
//
                    }else{
                        Toast.makeText(getApplicationContext()," note done",Toast.LENGTH_SHORT).show();
                    }
//

//

                case DragEvent.ACTION_DRAG_ENDED:
                    if(view!=null){
                        view.setVisibility(View.VISIBLE);
                    }

//                      Toast.makeText(getApplicationContext(),"ended",Toast.LENGTH_SHORT).show();

                    break;
                default:

                        view.setVisibility(View.VISIBLE);


//                    Toast.makeText(getApplicationContext(),"default",Toast.LENGTH_SHORT).show();

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