package com.example.simultanouschess;

import android.os.AsyncTask;
import android.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class MoveSendReceiveInterface extends AsyncTask<Object, Void, Pair<Move, MainActivity>> {
    MoveSendReceiveInterface(){
        super();
    }

    public static void sendMoveToServerAndAwaitOpponent(Move move, MainActivity mainActivity, Socket socket){
        new MoveSendReceiveInterface().execute(move, mainActivity, socket);
    }

    private void sendMove(Socket socket, Move move) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(move.toIntArray());
    }

    private Move getOpponentMove(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        int[] move = (int[]) in.readObject();
        return new Move(move);
    }

    @Override
    protected Pair<Move, MainActivity> doInBackground(Object[] input) {
        Move opponentsMove = null;
        Move move = (Move) input[0];
        MainActivity mainActivity = (MainActivity) input[1];
        Socket socket = (Socket) input[2];
        try {
            sendMove(socket, move);
            opponentsMove = getOpponentMove(socket);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Pair<>(opponentsMove, mainActivity);
    }

    @Override
    protected void onPostExecute(Pair<Move, MainActivity> moveAndActivity) {
        MainActivity mainActivity = moveAndActivity.second;
        Move opponentsMove = moveAndActivity.first;
        mainActivity.postMove(opponentsMove);
    }
}
