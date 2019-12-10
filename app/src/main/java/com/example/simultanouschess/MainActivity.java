package com.example.simultanouschess;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.net.Socket;
import java.util.Arrays;

import static com.example.simultanouschess.MoveSendReceiveInterface.sendMoveToServerAndAwaitOpponent;


public class MainActivity extends AppCompatActivity {
    private Position lastChosenPositionInTurn;
    volatile Socket socket;
    Side side;
    Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        SocketInitializer.setSocket(this);
        lastChosenPositionInTurn = null;
        board = new Board();
        SetSideInterface.setSide(this);
    }

    public void setSide(Side side){
        this.side = side;
    }

    public void setSocket(Socket socket){
        this.socket = socket;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Position getPositionFromCheckerName(View view){
        String name = view.getResources().getResourceName(view.getId());
        char y = name.charAt(name.length() - 1);
        char x = name.charAt(name.length() - 3);
        return new Position(x, y);
    }

    public void pieceOnClick(View view) {
        Position position = getPositionFromCheckerName(view);
//        if(board.getPieceSide(position) == side) {
//            //highlight this piece
//        }
        // enemy chosen:
        //if (lastChosenPositionInTurn != null && board.getPieceSide(lastChosenPositionInTurn) == side &&
                //board.getPieceSide(lastChosenPositionInTurn) == side) {
            lastChosenPositionInTurn = new Position(1,6);
            final Move move = new Move(lastChosenPositionInTurn, position);
            lastChosenPositionInTurn = position;
            while(socket == null);
            sendMoveToServerAndAwaitOpponent(move, this, socket);
        //}
    }

    public void postMove(Move opponentMove){
        System.out.println("reached post move \n");
        System.out.println(Arrays.toString(opponentMove.toIntArray()));
    }
}
