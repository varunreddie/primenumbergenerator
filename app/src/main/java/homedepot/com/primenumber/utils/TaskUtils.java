package homedepot.com.primenumber.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by varun on 27/04/17.
 */

public class TaskUtils {
    public static List<Integer> primaeNumbersList = Collections.synchronizedList(new ArrayList<Integer>());
    private static String TAG = "TaskUtils";

    public static void generatePrimeNumbers(int n) {

        if (primaeNumbersList.size() != 0) {
            return;
        }

        for (int num = 2; num < Long.MAX_VALUE; num++) {

            int flag = 0;
            for (long currentNum = 2; currentNum < num; currentNum++) {
                if (num % currentNum == 0) {
                    flag++;
                }
            }
            if (flag == 0) {
                Log.v(TAG, " Prime Number :" + num);
                primaeNumbersList.add(num);
            }
            if (n <= primaeNumbersList.size()) {
                break;
            }
        }
    }
}
