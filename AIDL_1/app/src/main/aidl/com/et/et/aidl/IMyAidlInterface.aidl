// IMyAidlInterface.aidl
package com.et.et.aidl;

// Declare any non-default types here with import statements
import com.et.et.aidl.StudentBean;

interface IMyAidlInterface {

    void addStudent(in StudentBean bean);

    String getStudent();
}
