package com.anuj.android.moviesearchapp.handlers;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.anuj.android.moviesearchapp.model.Image;
import com.anuj.android.moviesearchapp.model.Movie;

public class MovieHandler extends DefaultHandler {
	
	private StringBuilder buffer = new StringBuilder();
	
	private ArrayList<Movie> moviesList;
	private Movie movie;
	private ArrayList<Image> movieImagesList;
	private Image movieImage;
	
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		
		buffer.setLength(0);
		
		if (localName.equals("movies")) {
			moviesList = new ArrayList<Movie>();
		}
		else if (localName.equals("movie")) {
			movie = new Movie();
		}
		else if (localName.equals("images")) {
			movieImagesList = new ArrayList<Image>();
		}
		else if (localName.equals("image")) {
			movieImage = new Image();
			movieImage.type = atts.getValue("type");
			movieImage.url = atts.getValue("url");
			movieImage.size = atts.getValue("size");
			movieImage.width = Integer.parseInt(atts.getValue("width"));
			movieImage.height = Integer.parseInt(atts.getValue("height"));
		}

	}
	
	@Override
	public void endElement(String uri, String localName, String qName)throws SAXException {
		
		if (localName.equals("movie")) {
			moviesList.add(movie);
		}
		else if (localName.equals("score")) {
			movie.score = buffer.toString();
		}
		else if (localName.equals("popularity")) {
			movie.popularity = buffer.toString();
		}
		else if (localName.equals("translated")) {
			movie.translated = Boolean.valueOf(buffer.toString());
		}
		else if (localName.equals("adult")) {
			movie.adult = Boolean.valueOf(buffer.toString());
		}
		else if (localName.equals("language")) {
			movie.language = buffer.toString();
		}
		else if (localName.equals("original_name")) {
			movie.originalName = buffer.toString();
		}
		else if (localName.equals("name")) {
			movie.name = buffer.toString();
		}
		else if (localName.equals("type")) {
			movie.type = buffer.toString();
		}
		else if (localName.equals("id")) {
			movie.id = buffer.toString();
		}
		else if (localName.equals("imdb_id")) {
			movie.imdbId = buffer.toString();
		}
		else if (localName.equals("url")) {
			movie.url = buffer.toString();
		}
		else if (localName.equals("votes")) {
			movie.votes = buffer.toString();
		}
		else if (localName.equals("rating")) {
			movie.rating = buffer.toString();
		}
		else if (localName.equals("certification")) {
			movie.certification = buffer.toString();
		}
		else if (localName.equals("overview")) {
			movie.overview = buffer.toString();
		}
		else if (localName.equals("released")) {
			movie.released = buffer.toString();
		}
		else if (localName.equals("version")) {
			movie.version = buffer.toString();
		}
		else if (localName.equals("last_modified_at")) {
			movie.lastModifiedAt = buffer.toString();
		}	
		else if (localName.equals("image")) {
			movieImagesList.add(movieImage);
		}	
		else if (localName.equals("images")) {
			movie.imagesList = movieImagesList;
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) {
		buffer.append(ch, start, length);
	}
		
	public ArrayList<Movie> retrieveMoviesList() {
		return moviesList;
	}

}
