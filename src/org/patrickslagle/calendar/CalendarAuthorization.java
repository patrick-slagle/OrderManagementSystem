/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.patrickslagle.calendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.model.*;
import com.google.api.services.calendar.Calendar;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author pslagle12
 */
public class CalendarAuthorization {

    private static final String APPLICATION_NAME
            = "Google Calendar API Java Quickstart";

    private static final List<String> SCOPES
            = Arrays.asList("https://www.googleapis.com/auth/calendar");
    private static final JsonFactory JSON_FACTORY
            = JacksonFactory.getDefaultInstance();
 private static java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/calendar-java-quickstart");
    
    private static InputStream in;
    private static GoogleClientSecrets clientSecrets;
    private static GoogleAuthorizationCodeFlow flow;
    private static Credential credential;
    private static HttpTransport HTTP_TRANSPORT;
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {

        }
    }

    private static Credential authorize() throws IOException {
        in = CalendarAuthorization.class.getResourceAsStream("client_secret.json");
        clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        flow
                = new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        credential
                = new AuthorizationCodeInstalledApp(
                        flow, new LocalServerReceiver()).authorize("user");

        System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    public static Calendar
            getCalendarService() throws IOException {
        Credential cred = authorize();
        setProxy();
        return new Calendar.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, cred)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private static void setProxy() {
        System.setProperty("https.proxyHost", "proxy server");
        System.setProperty("https.proxyPort", "secure proxy port");
    }

}
