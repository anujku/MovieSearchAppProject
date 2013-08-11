package com.anuj.android.moviesearchapp.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by anuj on 8/9/13.
 */
public class Person implements Serializable {
    public String score;
    public String popularity;
    public String name;
    public String id;
    public String biography;
    public String url;
    public String version;
    public String lastModifiedAt;
    public ArrayList<Image> imagesList;
}
