package Networking;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// s204503 - Niels Raunkjær Holm
// This class builds and sends an http POST request from the target IP and sets the FieldDTO as payload.
// An HttpClient is created and an HttpRequest is built using the HttpRequest.Builder class.
// The format of the http request is URI: "http://{IP}:{PORT}/swoop" and the payload is a
// serialized plaintext representation of the given FieldDTO object.
// The client then sends a non-blocking request using the sendAsync method (note: is mostly asynchronous)

public abstract class ClientDriver {
    // ASYNC
    // chained methods after sendAsync makes it work async
    private static HttpClient client;

    public static void simpleAsyncRequestPrintFieldDTO(String IP, int PORT, FieldDTO fieldDTO) {
        if (client == null)
            client = HttpClient.newBuilder().build();
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