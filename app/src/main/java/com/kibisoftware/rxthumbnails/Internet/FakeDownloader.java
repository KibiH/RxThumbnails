package com.kibisoftware.rxthumbnails.Internet;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by kibi on 08/07/17.
 */

public class FakeDownloader {

    private Context context;

    public FakeDownloader(Context context) {
        this.context = context;
    }

    public void assureDownloadedMask(String url) {
        // need to write a file out to disk if it doesn't exist
        if (checkFile(context, url)) {
            // early return
            return;
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // error
        }

        // create a file so we never need to load again
        writeFile(context, url);
    }

    private void writeFile(Context context, String name) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(name, Context.MODE_PRIVATE));
            outputStreamWriter.write("some data");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private boolean checkFile(Context context, String name) {
        File testFile = context.getFileStreamPath(name);
        return testFile.exists();
    }

    public void deleteDownloadedFiles() {
        // just used so we can fake up not having anything downloaded again
        File dir = context.getFilesDir();
        for (File file: dir.listFiles()) {
            file.delete();
        }
    }
}
