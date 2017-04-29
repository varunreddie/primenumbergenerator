package homedepot.com.primenumber;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import homedepot.com.primenumber.utils.TaskUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterPrimeList.RecycleViewListener {

    private static String TAG = "MainActivity";
    private EditText mValueOfN;
    private RecyclerView mPrimeNumberList;
    private Button mPrimeGenerateButton, mClearButton;
    private AdapterPrimeList mAdapter;
    private List<Integer> mList;
    private Context mContext;

    private RadioButton mFullListRadiobutton, mNthPrimeRadiobutton;
    private Button mYesButton, mCancelButton;
    private Dialog mAlertDialog;

    private String enterdValue;
    private boolean showFull = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mValueOfN = (EditText) findViewById(R.id.input_value);

        mPrimeNumberList = (RecyclerView) findViewById(R.id.list_prime);
        mPrimeGenerateButton = (Button) findViewById(R.id.generate_button);
        mClearButton = (Button) findViewById(R.id.clear_button);
        mClearButton.setOnClickListener(this);
        mPrimeGenerateButton.setOnClickListener(this);
        mContext = getApplicationContext();

        mPrimeNumberList.setHasFixedSize(true);

        mPrimeNumberList.setLayoutManager(new GridLayoutManager(mContext, 4));
        mList = new ArrayList<>();
        mAdapter = new AdapterPrimeList(mContext, this, mList);
        mPrimeNumberList.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        enterdValue = mValueOfN.getText().toString().trim();

        if (v == mPrimeGenerateButton) {

            if (TextUtils.isEmpty(enterdValue)) {
                mValueOfN.setText("Enter a value");
                return;
            }
            hide();
            showCustomDialog();
        } else if (v == mClearButton) {
            mValueOfN.setText("");
            mAdapter.clear();

        } else if (v == mCancelButton) {

            mAlertDialog.dismiss();

        } else if (v == mYesButton) {

            showFull = (mFullListRadiobutton.isChecked()) ? true : false;

            mAlertDialog.dismiss();
            showPrimeNumbers();
        }
    }

    private void hide() {


        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }

    private void showPrimeNumbers() {


        try {
            String enterdValue = mValueOfN.getText().toString().trim();
            if (TextUtils.isEmpty(enterdValue)) {
                mValueOfN.setText("Enter value");
                return;
            }
            Integer n = Integer.parseInt(enterdValue);
            TaskUtils.generatePrimeNumbers(n);


            mList = TaskUtils.primaeNumbersList;
            List<Integer> list = new ArrayList<>();

            if (showFull) {

                for (int i = 0; i < n; i++) {
                    list.add(mList.get(i));
                }

            } else {
                list.add(mList.get(n - 1));
            }

            mAdapter.update(list);
            showFull = false;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    private void showCustomDialog() {
        mAdapter.clear();

        if (mAlertDialog == null) {
            mAlertDialog = new Dialog(this);
            mAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mAlertDialog.setCancelable(false);
            mAlertDialog.setContentView(R.layout.layout_dialog);

            mFullListRadiobutton = (RadioButton) mAlertDialog.findViewById(R.id.id_rb_full);
            mNthPrimeRadiobutton = (RadioButton) mAlertDialog.findViewById(R.id.id_rb_nprime);
            mYesButton = (Button) mAlertDialog.findViewById(R.id.id_btn_yes);
            mCancelButton = (Button) mAlertDialog.findViewById(R.id.id_btn_cancel);
            mYesButton.setOnClickListener(this);
            mCancelButton.setOnClickListener(this);
        }
        mAlertDialog.show();

    }

    @Override
    public void onItemClick(int position, View v) {

    }
}