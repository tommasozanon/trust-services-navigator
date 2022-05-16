import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.JSONObject;
import org.json.JSONArray;

public class SiteRequests {
    private String country_list;

    private static SiteRequests single_instance = null;

    /**
     * Private constructor because SiteRequests is a singleton
     */
    private SiteRequests() {
        country_list = "";
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
     * @return
     */
    public JSONObject country_list() throws IOException {
        if (country_list == "") {
            URL url = new URL("https://esignature.ec.europa.eu/efda/tl-browser/api/v1/search/countries_list");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            // System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                country_list = response.toString();
            } else {
                return new JSONObject("{ \"error\" : \"BAD_CODE: " + responseCode);
            }

        }

        JSONArray array = new JSONArray(country_list);
        String s = "{";
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            // System.out.println(object.getString("countryCode"));
            s += "\"" + object.getString("countryCode") + "\": " + "\"" + object.getString("countryName") + "\",";
            // System.out.println(object.getString("countryName"));
        }
        s += "}";

        JSONObject jsonObject = new JSONObject(s);
        return jsonObject;
    }

}
