package mithly.test.barcodereader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    EditText contentTxt;
    EditText formatTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentTxt = (EditText) findViewById(R.id.scan_content);
        formatTxt = (EditText) findViewById(R.id.scan_format);

    }

    public void scanNow(View view){
        //Log.d("test","It Works!!");
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.setResultDisplayDuration(0);
        integrator.setWide();
        integrator.setCameraId(0);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (scanningResult != null){

            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            contentTxt.setText(scanFormat);
            formatTxt.setText(scanContent);

        }else {

            Toast.makeText(getApplicationContext(), "No scan data", Toast.LENGTH_SHORT).show();
        }
    }
}
