import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Data {
    HttpURLConnection connection;
    private ArrayList<List<String>> data = new ArrayList<>(); // The data from the csv file

    public Data() {
        try {
            connection = (HttpURLConnection) new URL(
                    "https://raw.githubusercontent.com/ipavlopoulos/java-cs-aueb/refs/heads/main/demo/io/reviews.csv")
                    .openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            ArrayList<String> lines = new ArrayList<>();
            while ((inputLine = in.readLine()) != null) {
                lines.add(inputLine);
            }
            in.close();

            // Skip header (start from 1)
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                List<String> fields = new ArrayList<>();
                StringBuilder sb = new StringBuilder();
                boolean inQuotes = false;
                for (int j = 0; j < line.length(); j++) {
                    char c = line.charAt(j);
                    if (c == '"') {
                        inQuotes = !inQuotes;
                    } else if (c == ',' && !inQuotes) {
                        fields.add(sb.toString());
                        sb.setLength(0);
                    } else {
                        sb.append(c);
                    }
                }
                fields.add(sb.toString()); // add last field
                data.add(fields);
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<List<String>> getData() {
        return data;
    }
}