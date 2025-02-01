package githubActivity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Github_user_activity {

    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Usage: java Github_user_activity" + "<"+args[0]+">");
            return;
        }

        Github_user_activity activity = new Github_user_activity();
        activity.fetchGithubUserActivity(args[0]);

    }

    private void fetchGithubUserActivity(String username) {
        final String GITHUB_URL = "https://api.github.com/users/" + username + "/events";

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(GITHUB_URL)).header("Accept", "application/vnd.github+json").GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404) {
                System.out.println("User not found.");
                return;
            }
            if (response.statusCode() == 200) {
                JsonParser parser = new JsonParser();
                JsonArray jsonArray = parser.parseString(response.body()).getAsJsonArray();
                displayActivity(jsonArray);
            }else{
                System.out.println("Error: " + response.statusCode() + " " + response.body());
            }
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            interruptedException.printStackTrace();
        }
    }

    private void displayActivity(JsonArray events) {
        for (JsonElement jsonElement : events) {
            JsonObject event = jsonElement.getAsJsonObject();
            String type = event.get("type").getAsString();
            String action;

            switch (type){
                case "PushEvent":
                    int commitCount = event.get("payload").getAsJsonObject().get("commits").getAsJsonArray().size();
                    action = "Pushed " + commitCount + " commit(s) to " + event.get("repo").getAsJsonObject().get("name");
                    break;
                case "IssuesEvent":
                    action = event.get("payload").getAsJsonObject().get("action").getAsString().toUpperCase().charAt(0)
                            + event.get("payload").getAsJsonObject().get("action").getAsString()
                            + " an issue in ${event.repo.name}";
                    break;
                case "WatchEvent":
                    action = "Starred " + event.get("repo").getAsJsonObject().get("name").getAsString();
                    break;
                case "ForkEvent":
                    action = "Forked " + event.get("repo").getAsJsonObject().get("name").getAsString();
                    break;
                case "CreateEvent":
                    action = "Created " + event.get("payload").getAsJsonObject().get("ref_type").getAsString()
                            + " in " + event.get("repo").getAsJsonObject().get("name").getAsString();

                    break;
                default:
                    action = event.get("type").getAsString().replace("Event", "")
                            + " in " + event.get("repo").getAsJsonObject().get("name").getAsString();
                    break;
            }

            System.out.println(action);
        }
    }


}
