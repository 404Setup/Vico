package one.tranic.vico.updater.github;

import com.google.gson.Gson;
import one.tranic.vico.updater.UpdateRecord;
import one.tranic.vico.updater.Updater;
import one.tranic.vico.updater.VersionComparator;
import one.tranic.vico.updater.github.source.GithubLatestReleaseSource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class GithubUpdate implements Updater {
    private final String localVersion;
    private final String repo;
    private final boolean simpleMode;
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public GithubUpdate(String localVersion, String repo) {
        this(localVersion, repo, true);
    }

    public GithubUpdate(String localVersion, String repo, boolean simpleMode) {
        this.localVersion = localVersion;
        this.repo = repo;
        this.simpleMode = simpleMode;
    }

    @Override
    public UpdateRecord getUpdate() throws IOException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/repos/" + repo + "/releases/latest"))
                .header("Accept", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new IOException("Unexpected code " + response.statusCode());
            }

            String responseBody = response.body();
            if (responseBody == null || responseBody.isEmpty()) return empty;

            GithubLatestReleaseSource updater = gson.fromJson(responseBody, GithubLatestReleaseSource.class);
            String cleanBody = updater.getBody().replaceAll("\\s*\\*\\*Full Changelog\\*\\*.*", "");

            if (simpleMode) {
                if (!Objects.equals(localVersion, updater.getTagName())) {
                    return new UpdateRecord(true, updater.getTagName(), cleanBody, "https://github.com/" + repo + "/releases/tag/" + updater.getTagName());
                }
            } else if (VersionComparator.cmpVer(localVersion, updater.getTagName()) < 0) {
                return new UpdateRecord(true, updater.getTagName(), cleanBody, "https://github.com/" + repo + "/releases/tag/" + updater.getTagName());
            }
        } catch (InterruptedException e) {
            throw new IOException("Request interrupted", e);
        }

        return empty;
    }
}
