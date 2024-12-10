package one.tranic.vico.updater.hangar;

import com.google.gson.Gson;
import one.tranic.vico.updater.UpdateRecord;
import one.tranic.vico.updater.Updater;
import one.tranic.vico.updater.VersionComparator;
import one.tranic.vico.updater.hangar.source.CombinedResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;

public class HangarUpdate implements Updater {
    private final String localVersion;
    private final String projectId;
    private final String address;
    private final boolean simpleMode;
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public HangarUpdate(String localVersion, String projectId, String address) {
        this(localVersion, projectId, address, true);
    }

    public HangarUpdate(String localVersion, String projectId, String address, boolean simpleMode) {
        this.localVersion = localVersion;
        this.projectId = projectId;
        this.address = address;
        this.simpleMode = simpleMode;
    }

    @Override
    public UpdateRecord getUpdate() throws IOException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://hangar.papermc.io/api/v1/projects/" + projectId + "/versions"))
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

            CombinedResponse updater = gson.fromJson(responseBody, CombinedResponse.class);
            List<CombinedResponse.VersionResult> result = updater.getResult();
            if (result.isEmpty()) return empty;

            CombinedResponse.VersionResult first = result.get(0);

            if (simpleMode) {
                if (!Objects.equals(localVersion, first.getName())) {
                    return new UpdateRecord(true, first.getName(), first.getDescription(), address + "/versions/" + first.getName());
                }
            } else if (VersionComparator.cmpVer(localVersion, first.getName()) < 0) {
                return new UpdateRecord(true, first.getName(), first.getDescription(), address + "/versions/" + first.getName());
            }
        } catch (InterruptedException e) {
            throw new IOException("Request interrupted", e);
        }

        return empty;
    }
}
