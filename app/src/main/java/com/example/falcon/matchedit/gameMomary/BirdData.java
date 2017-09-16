package com.example.falcon.matchedit.gameMomary;

import com.example.falcon.matchedit.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by falcon on 16/09/2017.
 */

public class BirdData {

    ArrayList<ImagReTag> BirdDataArray =new ArrayList<>();

    ImagReTag image1 = new ImagReTag(R.drawable.one,"bird");
    ImagReTag image2 = new ImagReTag(R.drawable.two,"bee");
    ImagReTag image3 = new ImagReTag(R.drawable.three,"elephant");
    ImagReTag image4 = new ImagReTag(R.drawable.foure,"rabbit");
    ImagReTag image5 = new ImagReTag(R.drawable.five,"fox");
    ImagReTag image6 = new ImagReTag(R.drawable.ten,"rabiite");
    ImagReTag image7 = new ImagReTag(R.drawable.seven,"batot");
    ImagReTag image8 = new ImagReTag(R.drawable.eight,"cat");


    ImagReTag [] arraImagRtage ={image1,image2,image3,image4,image5,image6,image7,image8};


    int range = arraImagRtage.length;
    int myImgCount = 0;

    Random random = new Random();


    public  ArrayList<ImagReTag> getBirdDataArrayArray() {

        for( int i = 0; i < range; ++i)
        {
            myImgCount = random.nextInt(range);
            if(!BirdDataArray.contains(arraImagRtage[myImgCount])){
                BirdDataArray.add(arraImagRtage[myImgCount]);
            }
        }

        return BirdDataArray;
    }
}
