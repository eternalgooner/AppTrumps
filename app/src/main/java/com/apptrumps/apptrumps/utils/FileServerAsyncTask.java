package com.apptrumps.apptrumps.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.apptrumps.apptrumps.ui.ConnectAndPlay;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by David on 05-Oct-17.
 */

public class FileServerAsyncTask extends AsyncTask {

    private Context context;
    private TextView statusText;

    public FileServerAsyncTask(Context context, View statusText) {
        this.context = context;
        //this.statusText = (TextView) statusText;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        try {
//            //create server socket & wait for client connections, this call blocks until a connection is accepted from client
//            ServerSocket serverSocket = new ServerSocket(8888);
//            Socket client = serverSocket.accept();
//
//            //if this code has reached, a client has connected & transferred data
//            final File file = new File(Environment.getExternalStorageDirectory() + "/"
//                    + context.getPackageName() + "/wifip2pshared-" + System.currentTimeMillis()
//                    + ".jpg");
//
//            File dirs = new File(file.getParent());
//            if(!dirs.exists()){
//                dirs.mkdirs();
//            }
//            file.createNewFile();
//            //InputStream inputStream = client
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

            Log.d("AST:", "in doInBackground()...!!!");
            //todo trying to sectup server socket & socket (client)
            // needs to be run on background thread
            //using input/output streams to transfer data - UTF
            //need to install app on other phone & test messages
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket client = serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());

            String msgFromClient = dataInputStream.readUTF();
            String message = "from " + client.getInetAddress() + ":" + client.getPort() + "\nmsg from client: " + msgFromClient;


            //todo using hardcoded string first to get it working
            dataOutputStream.writeUTF("testing sockets from server!!");

            return "test string from AST";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
