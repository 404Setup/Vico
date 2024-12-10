package one.tranic.vico.updater.spigot;

import one.tranic.vico.updater.UpdateRecord;
import one.tranic.vico.updater.Updater;
import one.tranic.vico.updater.VersionComparator;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class SpigotUpdate implements Updater {
    private final String localVersion;
    private final int resourceId;
    private final boolean simpleMode;
    private final HttpClient client = HttpClient.newHttpClient();

    public SpigotUpdate(String localVersion, int resourceId) {
        this(localVersion, resourceId, true);
    }

    public SpigotUpdate(String localVersion, int resourceId, boolean simpleMode) {
        this.localVersion = localVersion;
        this.resourceId = resourceId;
        this.simpleMode = simpleMode;
    }

    @Override
    public UpdateRecord getUpdate() throws IOException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spigotmc.org/legacy/update.php?resource=" + resourceId))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();

            if (response.statusCode() != 200) {
                if (response.statusCode() == 404 && "Invalid resource".equals(body)) {
                    throw new IOException("Invalid resource");
                } else {
                    throw new IOException("Unexpected code " + response.statusCode());
                }
            }

            if (body == null || body.isEmpty()) return empty;

            if (simpleMode) {
                if (!Objects.equals(localVersion, body)) {
                    return new UpdateRecord(true, body, "Update info is empty", "https://www.spigotmc.org/resources/" + resourceId + "/");
                }
            } else if (VersionComparator.cmpVer(localVersion, body) < 0) {
                return new UpdateRecord(true, body, "Update info is empty", "https://www.spigotmc.org/resources/" + resourceId + "/");
            }
        } catch (InterruptedException e) {
            throw new IOException("Request interrupted", e);
        }

        return empty;
    }
}
