package test.prueba;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 *
 */
public class WriterFile implements Runnable {

    private BlockingQueue<String> blockingQueue;
    private String file;

    public WriterFile(
            BlockingQueue<String> blockingQueue,
            String file
    ) {
        this.blockingQueue = blockingQueue;
        this.file = file;
    }

    @Override
    public void run() {
        BufferedWriter bw = null;
        try {
            File mFile = new File(file);
            // get file name
            String fileName = mFile.getName();

            // create new file name
            String newFileName = "copy_" + fileName;

            File newFile = new File(file.replace(fileName, newFileName));
            if (!newFile.exists()) {
                newFile.createNewFile();
            }

            // writing new file
            bw = new BufferedWriter(new FileWriter(newFile, true));
            while (true) {
                String line = blockingQueue.take();
                if (line.equalsIgnoreCase("EOF")) {
                    break;
                }
                bw.write(line + "\n");
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

        }

    }

}
