package com.example.simultanouschess;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class SetSideInterface extends AsyncTask<MainActivity, Void, Void> {
    public static void setSide(MainActivity mainActivity){
        new SocketInitializer().execute(mainActivity);
    }

    @Override
    protected Void doInBackground(MainActivity... mainActivities) {
        MainActivity mainActivity = mainActivities[0];
        Socket socket = mainActivity.socket;
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Side side = (Side) objectInputStream.readObject();
            mainActivity.setSide(side);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
