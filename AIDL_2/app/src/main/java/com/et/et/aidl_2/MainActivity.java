package com.et.et.aidl_2;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.et.et.aidl.IMyAidlInterface;
import com.et.et.aidl.StudentBean;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAIDLButton(View v) {
        Intent intent = new Intent();
        intent.setPackage("com.et.et.aidl_1");
        intent.setAction("com.et.et.aidl_1.AIDLService");//这个是上面service的action
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection connection = new ServiceConnection() {

        private IMyAidlInterface service;

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            service = IMyAidlInterface.Stub.asInterface(iBinder);
            try {
                StudentBean bean = new StudentBean();
                bean.setName("tao");
                service.addStudent(bean);
                StudentBean bean1 = new StudentBean();
                bean1.setName("_");
                service.addStudent(bean1);
                StudentBean bean2 = new StudentBean();
                bean2 .setName("oat");
                service.addStudent(bean2);


                Toast.makeText(MainActivity.this, service.getStudent(), Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connection != null) {
            unbindService(connection);
        }
    }
}
