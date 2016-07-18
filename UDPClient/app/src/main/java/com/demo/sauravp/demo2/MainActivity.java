package com.demo.sauravp.demo2;

import android.content.IntentFilter;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.Format;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {
Button btn1,btn2,btn3;
    Handler mHandler;
    IntentFilter ifil;
/*    TextView tv;
    private TestPttButtonReceiver mReceiver;
    public static final String SON_PTT_KEY_DOWN =
            "com.sonim.intent.action.PTT_KEY_DOWN";
    public static final String SON_PTT_KEY_UP =
            "com.sonim.intent.action.PTT_KEY_UP";
    private final int PTT_KEYCODE = 228;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
/*        tv = (TextView) findViewById(R.id.textView);
        tv.setText("Press Any Key !!");
        tv.setTextColor(Color.RED);*/
   /*     String messageStr="Hello RTP";
        int server_port = 45357;
        DatagramSocket s = null;
     *//*   StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);*//*
        try {
            s = new DatagramSocket();
        } catch (SocketException e) {
            Log.d("saurav","RTP@ Exception is"+e);
        }
        InetAddress local = null;
        try {
            //use the below line to send data to same device
            //local = InetAddress.getByAddress(getLocalIpAddress ());
            local = InetAddress.getByAddress(new byte[] {(byte)10, (byte)11, (byte)9, (byte)48 });
        } catch (UnknownHostException e) {
            Log.d("saurav","RTP@ Exception is"+e);
        }
        int msg_length=messageStr.length();
        byte[] message = messageStr.getBytes();
        DatagramPacket p = new DatagramPacket(message, msg_length,local,server_port);
        try {
            Log.d("saurav","RTP@ Sending Packet here!");
            s.send(p);
        } catch (IOException e) {
            Log.d("saurav","RTP@ Exception is"+e);
        }*/

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageStr="10 byte";
                int i =0;
     /*           for(int i=0;i<100;i++) {*/
                    SendUDPData sendObj = new SendUDPData();
                    String  temp2;
                    temp2 = String.format("%04d", i);
                    //messageStr = messageStr+temp2;
                    Log.d("saurav ", "RTP@ Starting to Send message BTN1");
                /*    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    sendObj.execute(messageStr+temp2);
               // }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageStr="50 byte RTP is such a long message man. Its very..";
                SendUDPData sendObj = new SendUDPData();
                Log.d("saurav ","RTP@ Starting to Send message BTN2");
                sendObj.execute(messageStr);

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageStr="100 Byte RTP message is even overrate. We never even know when will this 100 character end and the!";
                SendUDPData sendObj = new SendUDPData();
                Log.d("saurav ","RTP@ Starting to Send message BTN3");
                sendObj.execute(messageStr);

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
                        Toast.makeText(getApplicationContext(), "Timer Expired", Toast.LENGTH_LONG).show();


                }

            }
        };
    }
    public static byte[] getLocalIpAddress() {
        try {
            for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        Log.e("s@urav ip", inetAddress.getHostAddress());
                        Log.e("s@urav ip2", "" + inetAddress.getAddress());

                        return inetAddress.getAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public class SendUDPData extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            Log.d("saurav ","RTP@ Starting to SendMessage to server");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String str) {
            Message msg = mHandler.obtainMessage(1);
           // mHandler.sendMessage(msg);
            super.onPostExecute(str);
        }

        @Override
        protected String doInBackground(String... params) {
            String messageStr = params[0];
            int server_port = 45357;
            DatagramSocket s = null;
     /*   StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);*/
            Log.d("saurav","RTP@ Message to send is"+messageStr);
            try {
                s = new DatagramSocket();
            } catch (SocketException e) {
                Log.d("saurav","RTP@ Exception is"+e);
            }
            InetAddress local = null;
            try {
                //local = InetAddress.getByAddress(getLocalIpAddress ());
                //change your wifi IP here
                local = InetAddress.getByAddress(new byte[] {(byte)10, (byte)11, (byte)8, (byte)255 });
            } catch (UnknownHostException e) {
                Log.d("saurav","RTP@ Exception is"+e);
            }
            int msg_length=messageStr.length();
            byte[] message = messageStr.getBytes();
            DatagramPacket p = new DatagramPacket(message, msg_length,local,server_port);
            try {
                Log.d("saurav","RTP@ Sending send()");
                s.send(p);
            } catch (IOException e) {
                Log.d("saurav","RTP@ Exception is"+e);
            }
            return null;
        }
    }
   /* public void registerPttReceiver(){
        if(null != mReceiver){
            return;
        }
        mReceiver = new TestPttButtonReceiver();
        ifil = new IntentFilter();
        ifil.addAction(SON_PTT_KEY_DOWN);
        ifil.addAction(SON_PTT_KEY_UP);
        Log.v("saurav","PTT Key Receiver REGISTERED !! ");
        this.registerReceiver(mReceiver, ifil);
    }

    public void unregPttReceiver(){
        if(null != mReceiver){
            Log.v("saurav","PTT Key Receiver UNREGISTERED !! ");
            unregisterReceiver(mReceiver);
            mReceiver = null;
            ifil = null;
        }
    }*/
  /*  @Override
    public void onResume(){
        super.onResume();
        unregPttReceiver();
    }

    @Override
    public void onPause(){
        super.onPause();
        registerPttReceiver();
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        unregPttReceiver();
        Toast.makeText(this,"Test App Closed !!" , Toast.LENGTH_SHORT).show();
    }*/
/*    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.v("saurav","onKeyUp UP count "+event.getKeyCode()+" rep count:" +event.getRepeatCount());
      *//*  tv.setText("Pressed KeyUP code: "+event.getKeyCode());*//*
        return super.onKeyUp(keyCode, event);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Toast.makeText(this,"PTT Down!!" , Toast.LENGTH_SHORT).show();
        Log.v("saurav","RTP@ onKeyDown DOWN count "+event.getKeyCode()+" rep count:" +event.getRepeatCount());
        Log.d("Saurav","RTP@ OnKeyDown At Application"+ " Time:"+System.currentTimeMillis());
        if(event.getRepeatCount()==0) {
        *//*    tv.setText("Pressed KeyDOWN code: " + event.getKeyCode());*//*
            if(event.getKeyCode() == 223) {
                String messageStr="RTP@ Sending a UDP packet in PTT key press!";
                SendUDPData sendObj = new SendUDPData();
                sendObj.execute(messageStr);

            }
        }
        else if(keyCode == PTT_KEYCODE && event.getRepeatCount()>0)
            tv.setText("LONG Pressed KeyDOWN code: "+event.getKeyCode());
        if(keyCode == PTT_KEYCODE && event.getRepeatCount()==0){
            event.startTracking();
            return true;
        }

        return super.onKeyDown(keyCode, event);

    }*/
}
