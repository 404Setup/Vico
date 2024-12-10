package one.tranic.vico.updater.spiget;

import com.google.gson.Gson;
import one.tranic.vico.Utils;
import one.tranic.vico.updater.UpdateRecord;
import one.tranic.vico.updater.Updater;
import one.tranic.vico.updater.VersionComparator;
import one.tranic.vico.updater.spiget.source.SpigetLatestUpdateSource;
import one.tranic.vico.updater.spiget.source.SpigetLatestVersionSource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class SpigetUpdate implements Updater {
    private final String localVersion;
    private final int resourceId;
    private final boolean simpleMode;
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public SpigetUpdate(String localVersion, int resourceId) {
        this(localVersion, resourceId, true);
    }

    public SpigetUpdate(String localVersion, int resourceId, boolean simpleMode) {
        this.localVersion = localVersion;
        this.resourceId = resourceId;
        this.simpleMode = simpleMode;
    }

    @Override
    public UpdateRecord getUpdate() throws IOException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spiget.org/v2/resources/" + resourceId + "/versions/latest"))
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

            SpigetLatestVersionSource updater = gson.fromJson(responseBody, SpigetLatestVersionSource.class);
            if (simpleMode) {
                if (!Objects.equals(localVersion, updater.getName())) {
                    return do2(updater);
                }
            } else if (VersionComparator.cmpVer(localVersion, updater.getName()) < 0) {
                return do2(updater);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Request interrupted", e);
        }

        return empty;
    }

    private UpdateRecord do2(SpigetLatestVersionSource updater) throws IOException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spiget.org/v2/resources/" + resourceId + "/updates/latest"))
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

            SpigetLatestUpdateSource updater2 = gson.fromJson(responseBody, SpigetLatestUpdateSource.class);
            return new UpdateRecord(
                    true,
                    updater.getName(),
                    Utils.decodeAndStripHtml(updater2.getDescription()),
                    "https://www.spigotmc.org/resources/" + resourceId + "/"
            );
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Request interrupted", e);
        }
    }
}
