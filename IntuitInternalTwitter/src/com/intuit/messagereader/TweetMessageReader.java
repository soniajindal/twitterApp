package com.intuit.messagereader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.jaxb.model.Tweet;

@Provider
@Consumes({"text/plain", "application/json"})
public class TweetMessageReader implements MessageBodyReader<Tweet> {
 
    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return true;
    }
 
    @Override
    public Tweet readFrom(Class<Tweet> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, String> mm, InputStream in) throws IOException, WebApplicationException {
        if (mt.getType().equals("application") && mt.getSubtype().equals("json")) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(in, Tweet.class);
        } 
        throw new UnsupportedOperationException("Not supported MediaType: " + mt);
    }
}
