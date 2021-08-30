package com.britsa.commons.misc;

import com.britsa.commons.codes.ApplicationCodes;
import com.britsa.commons.codes.HTTPCodes;
import com.britsa.commons.exceptions.WebExceptionType;
import com.britsa.commons.exceptions.WebServiceException;
import com.britsa.commons.loggers.CommonLogger;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Maria Irudaya Regilan J
 */

public class FirebaseServer {

    private static FirebaseServer firebaseServer;

    public static FirebaseServer start() {
        if (Validator.isNull(FirebaseServer.firebaseServer))
            FirebaseServer.firebaseServer = new FirebaseServer();
        return FirebaseServer.firebaseServer;
    }

    public Firestore initialize(String url, String credentials) throws Exception {
        FirebaseOptions options = null;
        CommonLogger.info(FirebaseServer.class, "Parsing database secret to InputStream...");
        InputStream credentialsStream = new ByteArrayInputStream(credentials.getBytes(StandardCharsets.UTF_8));
        GoogleCredentials googleCredentials = null;

        CommonLogger.info(FirebaseServer.class, "Parsing GoogleCredentials...");
        try {
            googleCredentials = GoogleCredentials.fromStream(credentialsStream);
        } catch (IOException e) {
            throw new WebServiceException(ApplicationCodes.FIREBASE_GOOGLE_CREDENTIALS_STREAM_ERROR, HTTPCodes.INTERNAL_ERROR, WebExceptionType.DATABASE_ERROR);
        }

        CommonLogger.info(FirebaseServer.class, "Building FirebaseOptions...");
        options = FirebaseOptions.builder()
                .setCredentials(googleCredentials)
                .setDatabaseUrl(url)
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            CommonLogger.info(FirebaseServer.class, "Initializing Firestore App...");
            FirebaseApp.initializeApp(options);
        } else {
            CommonLogger.info(FirebaseServer.class, "Using already initialized app...");
        }

        return FirestoreClient.getFirestore();
    }

}
