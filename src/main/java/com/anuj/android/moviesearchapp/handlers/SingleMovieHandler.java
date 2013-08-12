package com.anuj.android.moviesearchapp.handlers;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.anuj.android.moviesearchapp.model.Movie;

/**
 * Created by anuj on 8/11/13.
 */
public class SingleMovieHandler extends DefaultHandler {

    private StringBuilder builder = new StringBuilder();

    private Movie movie;

    @Override
    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts) throws SAXException {

        builder.setLength(0);

        if (localName.equals("movie")) {
            movie = new Movie();
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName)throws SAXException {

        if (localName.equals("name")) {
            movie.name = builder.toString();
        }
        else if (localName.equals("imdb_id")) {
            movie.imdbId = builder.toString();
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) {
        builder.append(ch, start, length);
    }

    public Movie retrieveMovie() {
        return movie;
    }

}
