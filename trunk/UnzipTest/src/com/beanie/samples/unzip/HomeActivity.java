
package com.beanie.samples.unzip;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class HomeActivity extends Activity
{
    private TextView tv;

    private static final String ROOT_FOLDER = Environment.getExternalStorageDirectory()
            + File.separator + "samples";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv = (TextView) findViewById(R.id.tv);
        tv.append("Reading zip file from assets folder...");

        // Start the unzipping process
        thread.start();
    }

    private Thread thread = new Thread()
    {

        @Override
        public void run()
        {
            sendMessage("-----------------------------------------------");

            // Create a directory in the SDCard to store the files
            File file = new File(ROOT_FOLDER);
            if (!file.exists())
            {
                file.mkdirs();
            }
            else
            {
                file.delete();
            }
            try
            {
                // Open the ZipInputStream
                ZipInputStream inputStream = new ZipInputStream(getAssets().open("ZipTest.zip"));

                // Loop through all the files and folders
                for (ZipEntry entry = inputStream.getNextEntry(); entry != null; entry = inputStream
                        .getNextEntry())
                {
                    sendMessage("Extracting: " + entry.getName() + "...");

                    String innerFileName = ROOT_FOLDER + File.separator + entry.getName();
                    File innerFile = new File(innerFileName);
                    if (innerFile.exists())
                    {
                        innerFile.delete();
                    }

                    // Check if it is a folder
                    if (innerFileName.charAt(innerFileName.length() - 1) == '/')
                    {
                        // Its a folder, create that folder
                        innerFile.mkdirs();
                    }
                    else
                    {

                        // Create a file output stream
                        FileOutputStream outputStream = new FileOutputStream(innerFileName);
                        final int BUFFER = 2048;

                        // Buffer the ouput to the file
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream,
                                BUFFER);

                        // Write the contents
                        int count = 0;
                        byte[] data = new byte[BUFFER];
                        while ((count = inputStream.read(data, 0, BUFFER)) != -1)
                        {
                            bufferedOutputStream.write(data);
                        }

                        // Flush and close the buffers
                        bufferedOutputStream.flush();
                        bufferedOutputStream.close();
                    }
                    sendMessage("DONE");
                }
                inputStream.close();
                sendMessage("-----------------------------------------------");
                sendMessage("Unzipping complete");

            }
            catch (IOException e)
            {
                sendMessage("Exception occured: " + e.getMessage());
                e.printStackTrace();
            }
        }

    };

    private Handler handler = new Handler()
    {

        @Override
        public void handleMessage(Message msg)
        {
            tv.append("\n" + msg.getData().getString("data"));
            super.handleMessage(msg);
        }

    };

    private void sendMessage(String text)
    {
        Message message = new Message();
        Bundle data = new Bundle();
        data.putString("data", text);
        message.setData(data);
        handler.sendMessage(message);
    }
}
