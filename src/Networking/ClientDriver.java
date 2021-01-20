package Networking;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class ClientDriver {


    // ASYNC
    // chained methods after sendAsync makes it work async
    public static void simpleAsyncRequestPrintFieldDTO(String IP, int PORT, FieldDTO fieldDTO) {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + IP + ":" + PORT + "/swoop"))
                .header("Content-Type", "text/plain")
                .POST(HttpRequest.BodyPublishers.ofByteArray(fieldDTO.toBytes()))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept((System.out::println));
    }
}