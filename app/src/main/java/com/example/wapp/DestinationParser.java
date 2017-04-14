package com.example.wapp;


import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DestinationParser {

    private static final String ns = null;

    public LinkedList parse(InputStream in,int nbrdest) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser,nbrdest);
        } finally {
            in.close();
        }
    }

    private LinkedList readFeed(XmlPullParser parser,int nbrdest) throws XmlPullParserException, IOException {
        LinkedList<Destination> leads = new LinkedList<Destination>();

        parser.require(XmlPullParser.START_TAG, ns, "feed");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            Destination d = readDestination(parser,nbrdest);
            leads.add(d);

        }
        return leads;
    }

    private Destination readDestination(XmlPullParser parser,int nbrdest) throws XmlPullParserException, IOException {
        Destination d = new Destination();
        parser.require(XmlPullParser.START_TAG, ns, "Destination");
        int counter = 0;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            d.add(readLead(parser));
        }
        return d;
    }


    private String readLead(XmlPullParser parser) throws IOException, XmlPullParserException {
  //      parser.require(XmlPullParser.START_TAG, ns, "Lead");
        String lead = readText(parser);
  //      parser.require(XmlPullParser.END_TAG, ns, "Lead");
        return lead;
    }

    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

}
