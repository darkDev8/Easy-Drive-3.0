package org.drive.root;

import java.util.Objects;

public class DatabaseDownloadProgress implements Runnable {
    private long size;
    private byte[] array;

    public DatabaseDownloadProgress(long size, byte[] array) {
        this.size = size;
        this.array = array;
    }

    @Override
    public void run() {
        long currentProgress = 0, nextProgress = 0;
        System.out.print("[");

        try {
            if (Objects.isNull(array)) {
                return;
            } else {
                if (array.length == size) {
                    Shell.getConsole().printCharacters('-', 50, false);
                    System.out.println("]");
                    return;
                }
            }

            while (true) {
                currentProgress = (array.length * 100L) / size;

                if (currentProgress != nextProgress) {
                    nextProgress = currentProgress;

                    if (nextProgress % 2 == 0) {
                        System.out.print("-");
                    }
                }

                if (size == array.length) {
                    break;
                }
            }

            System.out.println("]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
