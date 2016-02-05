package com.fsdstaff.ifwwtttt.WeatherAPI;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by adithyah on 1/10/16.
 */
public class WeatherResponse {
    private Document weatherDoc;

    public WeatherResponse(String xmlResponse){
        parse(xmlResponse);
    }

    public void parse(String xmlString){
        try {
            DocumentBuilder wBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            this.weatherDoc = wBuilder.parse(xmlString);

            this.weatherDoc.getDocumentElement().normalize();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Document getWeatherDoc(){
        return weatherDoc;
    }
}
