package test.prueba;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {

    public static void main(String[] args) {

        String file = args[0];

        if (file == null || file.isEmpty() || file.isBlank()) {
            throw new RuntimeException("provide a good file");
        }

        BlockingQueue blockingQueue = new LinkedBlockingDeque();

        ReaderFile reader = new ReaderFile(blockingQueue, file);
        WriterFile writer = new WriterFile(blockingQueue, file);

        Thread threadReader = new Thread(reader);
        Thread threadWriter = new Thread(writer);

        threadReader.start();
        threadWriter.start();


    }

}
