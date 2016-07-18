package com.demo.sauravp.udpclient;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.button);

    /*    String text;

        int server_port = 45357;
        byte[] message = new byte[1500];
        DatagramPacket p = new DatagramPacket(message, message.length);
        DatagramSocket s = null;
        try {
            s = new DatagramSocket(45357);

            s.setReuseAddress(true);
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
      *//*  try {
            s = new DatagramSocket(server_port);
            s.receive(p);
        } catch (Exception e) {
            Log.d("saurav", "saurav RTP@ Excpetion is" + e);
        }
        text = new String(message, 0, p.getLength());
        Log.d("saurav", "RTP@ message:" + text);*//*
            while (true) {
                Log.d("UDP", "RTP@: Waiting to Receive Data...");
                try {
                    s.receive(p);
                } catch (IOException e) {
                    Log.d("saurav", "Saurav IOException is" + e);
                }
                String rec_str = new String(p.getData());
                Log.d("saurav", " RTP@ Received String " + new String(message).substring(0,9));
                Toast.makeText(getApplicationContext(),new String(message).substring(0,9),Toast.LENGTH_LONG).show();

                InetAddress ipaddress = p.getAddress();
                int port = p.getPort();
                Log.d("RTP@ IPAddress : ", ipaddress.toString());
                Log.d("RTP@ Port : ", Integer.toString(port));
            }
        }
        catch(Exception e){
            Log.d("saurav","RTP@ Exception is:"+e);
        }
        s.close();*/
      /*  UdpRequest req = new UdpRequest();
        req.execute();*/
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execute();
            }
        });
        mHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                switch(msg.what)
                {
                    case 1:
                        Bundle res = msg.getData();
                        String actualResult = res.getString("res");
                        Toast.makeText(getApplicationContext(), "Sent Message from Client is:"+actualResult, Toast.LENGTH_LONG).show();



                }

            }
        };
    }
    public void execute()
    {
        Log.d("saurav","RTP@ Starting Server here to receive messages!");
        UdpRequest req = new UdpRequest();
        req.execute();
    }
    public class UdpRequest extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            Log.d("UDP", "RTP@: Inside Preexecute of the AsyncTask!");
        }

        @Override
        protected String doInBackground(Void... params) {

            String text;

            int server_port = 45357;
            byte[] message = new byte[1500];
            DatagramPacket p = new DatagramPacket(message, message.length);
            DatagramSocket s = null;
            try {
                s = new DatagramSocket(45357);

                s.setReuseAddress(true);
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
      /*  try {
            s = new DatagramSocket(server_port);
            s.receive(p);
        } catch (Exception e) {
            Log.d("saurav", "saurav RTP@ Excpetion is" + e);
        }
        text = new String(message, 0, p.getLength());
        Log.d("saurav", "RTP@ message:" + text);*/
               // while (true) {
                    Log.d("UDP", "RTP@: Waiting to Receive Data...");
                    try {
                        s.receive(p);
                    } catch (IOException e) {
                        Log.d("saurav", "Saurav IOException is" + e);
                    }
                    String rec_str = new String(p.getData());
                    Log.d("saurav", " RTP@ Received String " + new String(message));
                    //sToast.makeText(getApplicationContext(),new String(message).substring(0,9),Toast.LENGTH_LONG).show();

                    InetAddress ipaddress = p.getAddress();
                    int port = p.getPort();
                    Log.d("RTP@ IPAddress : ", ipaddress.toString());
                    Log.d("RTP@ Port : ", Integer.toString(port));
               // }
            }
            catch(Exception e){
                Log.d("saurav","RTP@ Exception is:"+e);
            }
            s.close();
            return new String(message);
        }

        @Override
        protected void onPostExecute(String result) {
            Bundle resultStr = new Bundle();
            resultStr.putString("res",result);
            Message msg = mHandler.obtainMessage(1);
            msg.setData(resultStr);
            mHandler.sendMessage(msg);
            Log.d("saurav","RTP@ Received String is:"+result);
        }
    }
}
