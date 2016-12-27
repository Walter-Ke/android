package tw.com.walter.stockquery;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();
    Stack<String> sStockIds = new Stack<String>();
    Stack<String> sStockQuotes = new Stack<String>();

    Button bSearch = null;
    EditText etStockId = null;
    EditText etStockQuotes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bSearch = (Button) findViewById(R.id.bSearch);

        etStockId = (EditText) findViewById(R.id.etStockId);
        etStockQuotes = (EditText) findViewById(R.id.etStockQuotes);

        bSearch.setOnClickListener(onclicklistener);
    }


    private View.OnClickListener onclicklistener = new View.OnClickListener() {


        @Override
        public void onClick(View view) {

            etStockQuotes.setText("");
            sStockIds.clear();
            sStockQuotes.clear();
            String StockIds = etStockId.getText().toString();

            for (String StockId : StockIds.split(" ")) {

                sStockIds.push(StockId);
                Thread t = new Thread(doQueryStockQuotes);
                t.start();

            }
        };


        private Runnable doQueryStockQuotes = new Runnable() {
            @Override
            public void run() {

             String StockId = null;


             //TODO: while read sStockIds, and use StockUrl to get the StockQuote from Internet,
             // then add to sStockQuotes and run doUpdateGUI using handler
             while(!sStockIds.empty()){

                 String stockId = sStockIds.pop();
                 String StockUrl =
                         "http://download.finance.yahoo.com/d/quotes.csv?s="
                                 + StockId + "&f=a";

                 HttpGet httpGet = new HttpGet(StockUrl);

                 try{
                     HttpResponse httpResponse =
                             new DefaultHttpClient().execute(httpGet);

                     String StockQuotes =
                             EntityUtils.toString(httpResponse.getEntity());

                     sStockQuotes.push(StockId + ":" + StockQuotes);

                     handler.post(doUpdateGUI);

                 }
                 catch (Exception e){
                     e.printStackTrace();
                 }


             }

            }
        };

        private Runnable doUpdateGUI = new Runnable() {
            @Override
            public void run() {

                //TODO: while read sStockQuotes until it is empty, and append the data to
                // etStockQuotes
                while(!sStockQuotes.isEmpty()) {
                    String StockQuotes = sStockQuotes.pop();
                    etStockQuotes.setText(etStockQuotes.getText().toString() + StockQuotes);
                }

            }
        };

    };
}