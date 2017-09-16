package com.example.falcon.matchedit;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.falcon.matchedit.gameMomary.AnimailData;
import com.example.falcon.matchedit.gameMomary.ImagReTag;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    AnimailData animailData ;
    ArrayList<ImagReTag>arrayList ;
    ImageView chose_one  ,chose_two,chose_three,chose_four;
    ImageView answer_one ,answer_two,answer_three,answer_four;
    private Bitmap bmp;
    private Bitmap operation;
    Random random = new Random();
    int nextImage ;
    int range  ;
    int myImgCount = 0;
    ArrayList<Integer>index,this_New;
    ArrayList<Integer> newIndex;
    int [] mmm ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList=new ArrayList<>();
        index=new ArrayList<>();
        this_New=new ArrayList<>();
        animailData= new AnimailData();
        arrayList=animailData.getAimalArray();
        newIndex= new ArrayList<Integer>();
        arrayList.get(0).getTage();
        nextImage =random.nextInt(arrayList.size());
        range = arrayList.size();
        myImgCount = 0;

        mmm = new int[]{0, 1, 2, 3};
        newIndex=getAlArray();


        chose_one=(ImageView)findViewById(R.id.chose_one);
        chose_two=(ImageView)findViewById(R.id.chose_two);
        chose_three=(ImageView)findViewById(R.id.chose_three);
        chose_four=(ImageView)findViewById(R.id.chose_four);

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


        chose_one.setOnTouchListener(new MyTouchListener() );
        chose_two.setOnTouchListener(new MyTouchListener() );
        chose_three.setOnTouchListener(new MyTouchListener() );
        chose_four.setOnTouchListener(new MyTouchListener() );
//****************************************************************************************************************************
//        ********************************************************************************************************************


        answer_one=(ImageView)findViewById(R.id.answer_one);
        answer_two=(ImageView)findViewById(R.id.answer_two);
        answer_three=(ImageView)findViewById(R.id.answer_three);
        answer_four=(ImageView)findViewById(R.id.answer_four);

         answer_one.setImageResource(arrayList.get(index.get(0)).getImage());
         answer_one.setTag(arrayList.get(index.get(0)).getTage());


        answer_two.setImageResource(arrayList.get(index.get(1)).getImage());
        answer_two.setTag(arrayList.get(index.get(1)).getTage());


        answer_three.setImageResource(arrayList.get(index.get(2)).getImage());
        answer_three.setTag(arrayList.get(index.get(2)).getTage());


        answer_four.setImageResource(arrayList.get(index.get(3)).getImage());
        answer_four.setTag(arrayList.get(index.get(3)).getTage());

        answer_one.setOnDragListener(new MyDragListener() );
        answer_two.setOnDragListener(new MyDragListener() );
        answer_three.setOnDragListener(new MyDragListener() );
        answer_four.setOnDragListener(new MyDragListener() );

        image(answer_one);
        image(answer_two);
        image(answer_three);
        image(answer_four);


    }



    public  ImageView image (ImageView imageView){




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

                r = 100  +  r;
                g = 100  + g;
                b = 100  + b;
                alpha = 100 + alpha;
                operation.setPixel(i, j, Color.argb(alpha, r, g, b));
            }
        }

        imageView.setImageBitmap(operation);


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
//
                        Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
                        ((ImageView)v).setImageDrawable(((ImageView)view).getDrawable());

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
}
