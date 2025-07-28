package org.example;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileInputStream;
import java.util.Collections;

public class Main {
    private static final String APPLICATION_NAME = "MASUKKAN APLIKASI NAME";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SERVICE_ACCOUNT_FILE = "MASUKKAN SERVICE ACCOUNT DARI GOOGLE DRIVE";
    private static final String FOLDER_ID = "FOLDER ID DARI GOOGLE DRIVE";

    public static Drive getDriveService() throws Exception {
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(new FileInputStream(SERVICE_ACCOUNT_FILE))
                .createScoped(Collections.singleton(DriveScopes.DRIVE));

        com.google.api.client.http.javanet.NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new Drive.Builder(httpTransport, JSON_FACTORY, new HttpCredentialsAdapter(credentials))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static String uploadImage(String imagePath) throws Exception {
        Drive driveService = getDriveService();

        File fileMetadata = new File();
        fileMetadata.setName(new java.io.File(imagePath).getName());
        fileMetadata.setParents(Collections.singletonList(FOLDER_ID));

        FileContent mediaContent = new FileContent("image/jpeg", new java.io.File(imagePath));
        File file = driveService.files().create(fileMetadata, mediaContent)
                .setFields("id, webViewLink")
                .execute();

        // Set file permission to public
        Permission permission = new Permission()
                .setType("anyone")
                .setRole("reader");
        driveService.permissions().create(file.getId(), permission).execute();

        return file.getWebViewLink();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Starting...");
        String uploadedUrl = uploadImage("D://pj1.jpg");
        System.out.println("Public Link: " + uploadedUrl);
    }
}