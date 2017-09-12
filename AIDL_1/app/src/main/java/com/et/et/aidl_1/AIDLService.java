package com.et.et.aidl_1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.et.et.aidl.IMyAidlInterface;
import com.et.et.aidl.StudentBean;

import java.util.ArrayList;
import java.util.List;

public class AIDLService extends Service {
    List<StudentBean> list = new ArrayList<>();

    public AIDLService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return myBinder;
    }

    IMyAidlInterface.Stub myBinder = new IMyAidlInterface.Stub() {

        @Override
        public void addStudent(StudentBean bean) throws RemoteException {
            list.add(bean);

        }

        @Override
        public String getStudent() throws RemoteException {
            StringBuffer sb = new StringBuffer();
            for (StudentBean bean : list) {
                sb.append(bean.getName() + ",");
            }
            if (sb.toString().endsWith(","))
                sb.subSequence(0, sb.length() - 1);

            return sb.toString();
        }
    };
}
