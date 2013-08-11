package com.anuj.android.moviesearchapp.services;

import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by anuj on 8/9/13.
 */

public abstract class GenericSeeker<E> {

    protected static final String BASE_URL = "http://api.themoviedb.org/2.1/";
    protected static final String LANGUAGE_PATH = "en/";
    protected static final String XML_FORMAT = "xml/";
    protected static final String API_KEY = "044559c636560da40b176167d344c99e";
    protected static final String SLASH = "/";

    protected HttpRetriever httpRetriever = new HttpRetriever();
    protected XmlParser xmlParser = new XmlParser();

    public abstract ArrayList<E> find(String query);
    public abstract ArrayList<E> find(String query, int maxResults);

    public abstract String retrieveSearchMethodPath();

    protected String constructSearchUrl(String query) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL);
        sb.append(retrieveSearchMethodPath());
        sb.append(LANGUAGE_PATH);
        sb.append(XML_FORMAT);
        sb.append(API_KEY);
        sb.append(SLASH);
        sb.append(URLEncoder.encode(query));
        return sb.toString();
    }

    public ArrayList<E> retrieveFirstResults(ArrayList<E> list, int maxResults) {
        ArrayList<E> newList = new ArrayList<E>();
        int count = Math.min(list.size(), maxResults);
        for (int i=0; i<count; i++) {
            newList.add(list.get(i));
        }
        return newList;
    }

}
