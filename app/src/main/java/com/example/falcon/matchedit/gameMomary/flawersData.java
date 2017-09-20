package com.example.falcon.matchedit.gameMomary;

import com.example.falcon.matchedit.R;

import java.util.ArrayList;
import java.util.Random;


public class flawersData {



    ArrayList<ImagReTag> numberArray =new ArrayList<>();

    ImagReTag image1 = new ImagReTag(R.drawable.flawr_one,"flawer red");
    ImagReTag image2 = new ImagReTag(R.drawable.flower_two,"flawer orange");
    ImagReTag image3 = new ImagReTag(R.drawable.flower_three,"flawer Purple");
    ImagReTag image4 = new ImagReTag(R.drawable. flower_foure,"flawer foure");
    ImagReTag image5 = new ImagReTag(R.drawable.flawer_five,"flawer Yellow");


    ImagReTag [] arraImagRtage ={image1,image2,image3,image4,image5};


    int range = arraImagRtage.length;
    int myImgCount = 0;

    Random random = new Random();


    public  ArrayList<ImagReTag> getFlawerArray() {

        for( int i = 0; i < range; ++i)
        {
            myImgCount = random.nextInt(range);
            if(!numberArray.contains(arraImagRtage[myImgCount])){
                numberArray.add(arraImagRtage[myImgCount]);
            }
        }

        return numberArray;
    }
}
