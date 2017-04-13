package com.navamoore.networkdevicedisplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    TextView mTextview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.start_network_discovery);
        mTextview = (TextView) findViewById(R.id.network_devices);

        setListener(mButton);
    }

    private void setListener(Button mButton) {
        mButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
               mTextview.setText(startNetworkdiscovery());
            }
        });
    }

    private String startNetworkdiscovery() {
        String s = "";

        try{
            Enumeration nis = NetworkInterface.getNetworkInterfaces();

            while(nis.hasMoreElements()){
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration ias = ni.getInetAddresses();
                while (ias.hasMoreElements()){
                    InetAddress ia = (InetAddress) ias.nextElement();
                    s += ia.toString() + "\n";
                }
            }

        }

        catch(SocketException e){
            Log.e("Network Services", e.toString());
        }

        return s;


    }


}
