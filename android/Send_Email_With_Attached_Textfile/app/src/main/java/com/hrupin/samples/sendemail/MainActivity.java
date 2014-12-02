package com.hrupin.samples.sendemail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class MainActivity extends Activity implements View.OnClickListener {

    private static final String FILENAME = "TextFileToSend.txt";
    private Button btnSend;
    private EditText editText;
    private FileWriter writer;
    private String emailBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = (Button)findViewById(R.id.button);
        btnSend.setOnClickListener(this);

        editText = (EditText)findViewById(R.id.editText);

        emailBody = "This is email from '" + getString(R.string.app_name) + "' application. Details you can read here: http://www.hrupin.com/2014/12/send-email-with-text-file-as-attachment-android-sample";
    }

    @Override
    public void onClick(View view) {
        File fileToSend = createFileWithContent(editText.getText().toString());

        sendIntentToGmailApp(fileToSend);
    }

    private void sendIntentToGmailApp(File fileToSend) {
        if(fileToSend != null){
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_SUBJECT, "Send Text File As Attachment Example");
            email.putExtra(Intent.EXTRA_TEXT, emailBody);
            email.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + fileToSend.getAbsoluteFile()));
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email , "Send Text File"));
        }
    }

    private File createFileWithContent(String content) {
        if(TextUtils.isEmpty(content)){
            content = emailBody;
        }
        File file = null;
        try{
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), FILENAME);

            writer = new FileWriter(file);
            writer.write(content);
            writer.close();
            Toast.makeText(getBaseContext(), "Temporarily saved contents in " + file.getPath(), Toast.LENGTH_LONG).show();
        }catch(IOException e){
            Toast.makeText(getBaseContext(), "Unable create temp file. Check logcat for stackTrace", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return file;
    }
}
