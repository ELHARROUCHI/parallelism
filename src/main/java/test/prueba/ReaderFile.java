package test.prueba;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 *
 */
public class ReaderFile implements Runnable {

    private BlockingQueue<String> blockingQueue;
    private String file;

    public ReaderFile(
            BlockingQueue<String> blockingQueue,
            String file
    ) {
        this.blockingQueue = blockingQueue;
        this.file = file;
    }

    @Override
    public void run() {

        // consumer function to reading & saving lines readed
        Consumer<String> consumer = line -> {
            try {
                blockingQueue.put(line);
                blockingQueue.put("EOF");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // reading file with autoclose option
        try (Stream<String> lines = Files.lines(Paths.get(file), Charset.forName("utf-8"))) {
            lines.forEach(consumer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
