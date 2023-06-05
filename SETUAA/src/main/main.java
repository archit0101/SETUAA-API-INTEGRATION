import java.io.IOException;
import java.util.Scanner;

import apis.ConsentAPI;
import apis.DataSessionsAPI;
import com.google.gson.JsonObject;

public class main {

  public static void main(String[] args) throws IOException {
    ConsentAPI consentAPI = new ConsentAPI();
    DataSessionsAPI dataSessionsAPI = new DataSessionsAPI();
    Integer choice = null;

    while(true) {
      System.out.println("Press 1 for Handshake");
      System.out.println("Press 2 to fetch a specific consent");
      System.out.println("Press 3 to create Data-Session");
      System.out.println("Press 4 to fetch session");
      System.out.println("Press 5 to exit");
      Scanner myObj = new Scanner(System.in);  // Create a Scanner object
      System.out.println("Enter Choice");
      choice = Integer.parseInt(myObj.nextLine());  // Read user input
      switch (choice) {
      case 1:
        System.out.println("Please give proper payload in src/main/resorces/payloadhandshake file, then press enter to continue");
        myObj.nextLine();
        System.out.println("Please wait....");
        JsonObject consent = consentAPI.createConsentHandshake();
        System.out.println(consent);
        break;
      case 2:
        System.out.println("Enter Consent Id");
        String id = myObj.nextLine();  // Read user input
        System.out.println("Please wait....");
        String consentobj = consentAPI.getConsent(id);
        System.out.println(consentobj);
        break;
      case 3:
        System.out.println("Please give proper payload in src/main/resorces/payloadsession file, then press enter to continue");
        myObj.nextLine();
        System.out.println("Please wait....");
        JsonObject session = dataSessionsAPI.createSession();
        System.out.println(session);
        break;
      case 4:
        System.out.println("Enter Session Id");
        String sid = myObj.nextLine();  // Read user input
        System.out.println("Please wait....");
        String sessiontobj = dataSessionsAPI.getSession(sid);
        System.out.println(sessiontobj);
        break;
      case 5:
        System.out.println("Exit");
        return;
        default:
          System.out.println("Enter coorect choice Id");
      }
    }
  }

}
