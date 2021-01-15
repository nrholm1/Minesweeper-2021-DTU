package Networking;


import Model.Field;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ClientDriver {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FieldDTO[] fieldDTOs = new FieldDTO[] { new FieldDTO(5,2, Field.State.FLAGGED, "X"),
                new FieldDTO(3,7, Field.State.PRESSED, "X"),
                new FieldDTO(32,26, Field.State.UNFLAGGED, "X"),
                new FieldDTO(32,26, Field.State.FLAGGED, "X"),
                new FieldDTO(32,26, Field.State.PRESSED, "X"),
                new FieldDTO(17,9, Field.State.UNFLAGGED, "X")};

        System.out.println("--print--");
        testPrintFieldDTO(fieldDTOs);
        System.out.println("--get--");
        testGetFieldDTO(fieldDTOs);
    }

    static void testGetFieldDTO(FieldDTO[] fieldDTOs) throws InterruptedException, ExecutionException {
        ArrayList<String> responses = new ArrayList<>();

        // executes synchronously I think?
        for (FieldDTO fieldDTO : fieldDTOs)
            responses.add(simpleAsyncRequestGetFieldDTO("192.168.1.222", 5050, fieldDTO).get());

        for (String s : responses)
            System.out.println(s);

        Thread.sleep(1000);
    }

    static void testPrintFieldDTO(FieldDTO[] fieldDTOs) throws InterruptedException {
        // executes asynchronously I think?
        for (FieldDTO fieldDTO : fieldDTOs)
            simpleAsyncRequestPrintFieldDTO("192.168.1.222", 5050, fieldDTO);

        Thread.sleep(1000);
    }

    // SYNC
    // "await" makes it work sync
    static CompletableFuture<String> simpleAsyncRequestGetFieldDTO(String IP, int PORT, FieldDTO fieldDTO) {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + IP + ":" + PORT + "/swoop"))
                .header("Content-Type", "text/plain")
                .POST(HttpRequest.BodyPublishers.ofByteArray(fieldDTO.toBytes()))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    // ASYNC
    // chained methods after sendAsync makes it work async
    static void simpleAsyncRequestPrintFieldDTO(String IP, int PORT, FieldDTO fieldDTO) {
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