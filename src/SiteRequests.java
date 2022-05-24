import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.FileWriter;

import org.json.JSONObject;
import org.json.JSONArray;

public class SiteRequests {
    private JSONObject country_list;
    private JSONArray trust_services_providers_list;
    private static SiteRequests single_instance = null;

    /**
     * Private constructor because SiteRequests is a singleton
     */
    private SiteRequests() {
        country_list = new JSONObject();
        trust_services_providers_list = new JSONArray();
    };

    /**
     * get instance of the singleton object
     * 
     * @return instance
     */
    public static SiteRequests getInstance() {
        if (single_instance == null)
            single_instance = new SiteRequests();

        return single_instance;
    }

    /**
     * Returns the list of countries that provides services in EU
     * 
     * @return List of all countries
     */
    public JSONObject country_json() throws IOException {
        if (country_list.isEmpty()) {
            URL url = new URL("https://esignature.ec.europa.eu/efda/tl-browser/api/v1/search/countries_list");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            // System.out.println("GET Response Code :: " + responseCode);

            String inputLine;
            StringBuffer response = new StringBuffer();

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

            } else {
                return new JSONObject("{ \"error\" : \"BAD_CODE: " + responseCode);
            }

            JSONArray array = new JSONArray(response.toString());
            String s = "{";
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                // System.out.println(object.getString("countryCode"));
                s += "\"" + object.getString("countryCode") + "\": " + "\"" + object.getString("countryName") + "\",";
                // System.out.println(object.getString("countryName"));
            }
            s += "}";

            JSONObject jsonObject = new JSONObject(s);
            country_list = jsonObject;
        }

        return country_list;
    }

    /**
     * Get complete list of trust service providers with their services from current
     * member states trusted lists
     * 
     * @return
     */
    public JSONArray trust_services_providers_json() throws IOException {
        if (trust_services_providers_list.isEmpty()) {
            URL url = new URL("https://esignature.ec.europa.eu/efda/tl-browser/api/v1/search/tsp_list");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            // System.out.println("GET Response Code :: " + responseCode);

            String inputLine;
            StringBuffer response = new StringBuffer();

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

            } else {
                return new JSONArray("{ \"error\" : \"BAD_CODE: " + responseCode);
            }

            JSONArray array = new JSONArray(response.toString());
            // String s = "{";
            // for (int i = 0; i < array.length(); i++) {
            // JSONObject object = array.getJSONObject(i);
            // // System.out.println(object.getString("countryCode"));
            // s += "\"" + object.getString("countryCode") + "\": " + "\"" +
            // object.getString("countryName") + "\",";
            // // System.out.println(object.getString("countryName"));
            // }
            // s += "}";

            // JSONObject jsonObject = new JSONObject(s);
            try {
                FileWriter myWriter = new FileWriter("src/aaa.json");
                myWriter.write(array.toString());
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            trust_services_providers_list = array;
        }

        return trust_services_providers_list;
    }
}