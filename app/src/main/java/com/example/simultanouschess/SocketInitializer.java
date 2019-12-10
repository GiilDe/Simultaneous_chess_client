package com.example.simultanouschess;

import android.os.AsyncTask;
import android.util.Pair;

import java.io.IOException;
import java.net.Socket;

public class SocketInitializer extends AsyncTask<MainActivity, Void, Pair<Socket, MainActivity>> {
    private final static String serverAddress = "192.168.1.19";
    private final static int port = 59090;

    public static void setSocket(MainActivity mainActivity){
        new SocketInitializer().execute(mainActivity);
    }

    @Override
    protected Pair<Socket, MainActivity> doInBackground(MainActivity... mainActivities) {
        MainActivity mainActivity = mainActivities[0];
        Socket socket = null;
        try {
            socket = new Socket(serverAddress, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Pair<Socket, MainActivity>(socket, mainActivity);
    }

    @Override
    protected void onPostExecute(Pair<Socket, MainActivity> socketAndMain) {
        MainActivity mainActivity = socketAndMain.second;
        mainActivity.setSocket(socketAndMain.first);
    }
}
