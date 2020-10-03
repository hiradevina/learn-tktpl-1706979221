# Lab Report 3: Stopwatch

[Repository](https://github.com/hiradevina/learn-tktpl-1706979221/tree/lab-3)

## Stopwatch
![Preview](https://lh3.googleusercontent.com/Q8mxI3K2qAeGZQ0TmgC5iuMOzIUZh4_5PFcWok_2B8WIM6uh5TnAYEQfRURu_YZRraq0uD9_AgvpTZjCZSi0S9bYXEr5x4sb9tMnQOq-ZTCEKBeME32BJk03n82EJt8Cje9RpOEV8SECtLfdGIZyQCS5jWQkOwsiRi6do2NUDdbw13MnNV8pVV9G3LEtM0eIWlTe7DJpRBgvVZ-kwIY2yMgd0IgOSu50bDyktYCV4bNjFQMdi3jHvuxuzfQX1uieOFlIOQ1HxrQBKLiZvw80MJTRd9eFoPWDJuSGCl0KsGJDFobbtTFW2ApukMVrOn5bl6uyjPeTFMH6rsazszhmL6l5XNv_wznFBc2JzI-xAyo84L8xeQxPFG21SKXoScmjNBXkyutQcngx8vlfSkJl8iOMK31lUXg2kfBOCRswcC87Zpob3TcsWqRUeg6CjPRmFQqrZP7j72CGYCR18dO16GnJHAXI7R0wEfFp0G3dbq_wxSpwNMDHOOoSrN5PJqk0K_bEwwdrIOeSKDIT7C3vD45mCVRDCMbZOV-3k-X3TIPB-xJpUxwHCR5bFtHyGBgMlYz3-ETbSHtK5FHgZzE8A1rEO6I6zMw07LdGFz4hLxUJxBQoCOmNdTLN7X4dDreh8W-RMZeyRj_Zt3TxaWmCVTPHWMnatBLEv9guq7dYaBTmuEIm9GYeBDnBjfR1=w401-h840-no?authuser=0)
I implemented the stopwatch function in `MainActivity.java` and the UI in `activity_main.xml`.
1. I created two global variables for the countdown such as the `running` flag and `second` to store the current timer
```
private boolean running;
private int second;
```
2. Create `onClick` function to trigger the countdown
```
final Button startButton = (Button) findViewById(R.id.button_start);
final Button stopButton = (Button) findViewById(R.id.button_stop);
final Button resetButton = (Button) findViewById(R.id.button_reset);
startButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        onClickStart(v);
    }
});
resetButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        onClickReset(v);
    }
});
stopButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        onClickStop(v);
    }
});
...

public void onClickStart(View view) {
        running = true;
    }

public void onClickStop(View view) {
    running = false;
}

public void onClickReset(View view) {
    running = false;
    second = 0;
}
```
3. Implement the countdown on `runTimer()` function. I used `Handler` to run the function in different thread from the Activity class (Not using Handler will cause error `CalledFromWrongThreadException`)
```
public void runTimer() {
    final TextView placeholder = (TextView) findViewById(R.id.text_second);
    final Handler handler = new Handler();
    handler.post(new Runnable() {
        @Override
        public void run() {
            int hours = second/3600;
            int minutes = (second%3600)/60;
            int sec = second%60;
            String timeFormat = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, sec);
            placeholder.setText(timeFormat);
            if (running) {
                second++;
            }
            handler.postDelayed(this, 1000);
        }
    });
}
```
4. The timer will still run even if the Application is not on foreground

## Overriding Back Button
![Preview](https://lh3.googleusercontent.com/CFIpfuxzkRo_cfAIhLuD3NcmOZC0SdqdXuVMjcACbGgecoNEZca03WKsOa2OmnGZxAXuWCVcZ8Y4MhGEFyQi3lm3zzGJUlNFZaThs9fyE7FsR4mllRV6K5sp0WmT2GiOZ4CWlmX4OBJGW0rLWR3Rq2_OO4pMXZ_JhLu8mLzox_6jLWRMNdhVA0CMER28hj1tP6SaW2owbQJgy7Ev0t9oAPB8ODRAyrXYEsnHbFCXeM6J4wRV15yOp-ifJx3lgJ1Jb-aQ0nRekDqs8V5QkKPPo1AitHaQ8sDAGUiFLtoA3q4nsR-_p8hGjwxXA17Nj5E2ca8i_rIazNPn-8fhF04_pCbetechI0K61-wOa5wtWorAfBbHn6N4_hYNtZtAoHYweM1e6GVzEedZyeaLQW3pWIp73LFWO-v2SCEbHyhfi4hb8efcDPirNsb3ZcE59cDaSBeT96QsUJC6lIM37Xs_tx2wJc7hDyuy0s3tn0PCGVb3MGZARLbMFGx8KG4g85dLD0rzQ8wvn3wUC-DrwFfaIBUmbAXXehXNF8-QgrjHrYalshDbKYCu5pJ2Yj1lBhLFbC6TxSHB3Z7CxTMQ1oXYN_yLWc4yBz0GoZFS9madH449I3r-lG11GFiUU6V8YYIZLduDjMmS-eUKqnlJMvf40FCDN2LzOiPlapkg8ZwhUlbub3qDLn0WVp3cSV5t=w387-h834-no?authuser=0)
I override the Back button that will ask user to ask the back button twice before leaving the application
1. I created a global variable `allowedToExitTheApplication` as a flag
```
private boolean allowedToExitTheApplication = false;
```
2. Override the `onBackPressed` method from the Activity class and add the logic to only allow user to exit the application if back button already pressed twice. If the back button has only pressed once, the code will set the flag as `true` and will exit at the second attempt of pressing back
```
if (allowedToExitTheApplication) {
    super.onBackPressed();
    return;
}
this.allowedToExitTheApplication = true;
Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_LONG).show();
```
3. If user changed his/her mind from exiting the back button (indicated by the duration between the first attempt of pressing back is more than 2 seconds) , set the flag back to `false` using Handler 
```
new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {
        allowedToExitTheApplication =false;
    }
}, 2000);
```

