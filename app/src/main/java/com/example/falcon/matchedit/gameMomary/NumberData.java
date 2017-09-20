package com.example.falcon.matchedit.gameMomary;

import com.example.falcon.matchedit.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by falcon on 20/09/2017.
 */

public class NumberData {

    ArrayList<ImagReTag> numberArray =new ArrayList<>();

    ImagReTag image1 = new ImagReTag(R.drawable.zerozero,"zero");
    ImagReTag image2 = new ImagReTag(R.drawable.oneone,"one");
    ImagReTag image3 = new ImagReTag(R.drawable.twotwo,"two");
    ImagReTag image4 = new ImagReTag(R.drawable.threethree,"three");
    ImagReTag image5 = new ImagReTag(R.drawable.fourefoure,"four");
    ImagReTag image6 = new ImagReTag(R.drawable.fivefive,"five");
    ImagReTag image7 = new ImagReTag(R.drawable.sixsix,"six");
    ImagReTag image8 = new ImagReTag(R.drawable.sevenseven,"seven");


    ImagReTag [] arraImagRtage ={image1,image2,image3,image4,image5,image6,image7,image8};


    int range = arraImagRtage.length;
    int myImgCount = 0;

    Random random = new Random();


    public  ArrayList<ImagReTag> getnumberArray() {

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
