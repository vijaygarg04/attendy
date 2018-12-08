package com.example.shivam.attendanceappminor;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shivam.attendanceappminor.Model.Faculty;
import com.example.shivam.attendanceappminor.Model.Student;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class AdminDashboardActivity extends AppCompatActivity {
EditText filename;
Button studentfilebutton,facultyfilebutton;
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
ArrayList<Student> studentArrayList;
ArrayList<Faculty> facultyArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        filename=findViewById(R.id.filename);
        firebaseDatabase=FirebaseDatabase.getInstance();
        studentArrayList=new ArrayList<>();
        facultyArrayList=new ArrayList<>();


        studentfilebutton=findViewById(R.id.studentfileupload);
        facultyfilebutton=findViewById(R.id.facultyfileupload);
        studentfilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentfileupload();
            }
        });

        facultyfilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facultyfileupload();
            }
        });

        //TODO : Add Students using excel sheet for login validation purpose
        // Data to be Added
        //Student_name
        //Student_id(enrollment number)
        //student_dob
        //password
        //batch_id sy_ey_course_stream
        //batch_start_year
        //batch_end_year
        //stream
        //mobile_number
        //gender
        //course

        //ToDO : Add Faculty using excel sheet for login validation purpose
        //Data to be added
        //faculty_name
        //faculty_id
        //dob
        //password
        //gender
        //mobile_number

        //This needs to be done only once by the administrator to register all students and faculties
        //so that they can be accessed from their respective dashboards.

    }

    private void studentfileupload() {
        String file=filename.getText().toString();
        databaseReference=firebaseDatabase.getReference().child("student");
        readExcelFile(AdminDashboardActivity.this,file);
    }

    private void readExcelFile(Context context, String file) {
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly())
        {
            Log.w("FileUtils", "Storage not available or read only");
            return;
        }
        try{
            // Creating Input Stream
            File file1 = new File(context.getExternalFilesDir(null), file);
            FileInputStream myInput = new FileInputStream(file1);
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator<Row> rowIter = mySheet.rowIterator();
            rowIter.next();
            while(rowIter.hasNext()){
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator<Cell> cellIter = myRow.cellIterator();
                String values[]=new String[11];
                int i=0;
                while(cellIter.hasNext()){
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    values[i]=myCell.toString();
                    i++;
                    Log.w("FileUtils", "Cell Value: " +  myCell.toString());
                    Toast.makeText(context, "cell Value: " + myCell.toString(), Toast.LENGTH_SHORT).show();
                }
                String id  = values[1];
                id =  id.replace(".","");
                id  = id.replace("E10","");


                Student student=new Student(values[0],id,values[2],values[3],values[4],values[5],values[6],values[7],values[8],values[9],values[10]);
                studentArrayList.add(student);


            }
            MyTask myTask=new MyTask();
            myTask.execute();
        }catch (Exception e){
            e.printStackTrace();
        }

        return;
    }
    class MyTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Date date=new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("ddMMyyyy");
            String sdate=simpleDateFormat.format(date).toString().trim();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            for( int i=0;i<studentArrayList.size();i++){
                inputdata(studentArrayList.get(i));

            }

            return null;


        }
        private void inputdata(final Student student){


            databaseReference.child(student.getStudendid()).setValue(student).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.e("DATAENTRY","SUCCESS");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("DATAENTRY","FAILED");
                }
            });

        }
    }
    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private void facultyfileupload() {
        String file=filename.getText().toString();


    }
}