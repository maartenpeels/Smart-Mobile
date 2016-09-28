package nl.maartenpeels.csiweek2;

import android.graphics.drawable.Drawable;
import android.media.Image;

import java.io.Serializable;

/**
 * Created by Maarten Peels on 9/16/2016.
 */
public class Criminal implements Serializable{
    String name, gender, bounty, details;
    int age;
    Drawable img;

    public Criminal(String name, String gender, int age, String bounty, String details, Drawable img)
    {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.bounty = bounty;
        this.details = details;
        this.img = img;
    }
}
