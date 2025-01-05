# Android Threads Tutorial

This repository demonstrates the use of threads in Android to perform background tasks efficiently without blocking the main UI thread. The project includes examples of `Handler`, `Looper`, and `Executors`.

## Processes and threads overview 

- Don't block the UI thread.
- Don't access the Android UI toolkit from outside the UI thread.

## Features

- **UI Updates:** Showcasing how to update the UI from a background thread using `Handler`.
- **Background Task Execution:** Using `Executors` for managing multiple background threads.
- **Thread Communication:** Example of `Looper` for enabling thread communication.

## Code Exmple 

- Don't block the UI thread.

```java
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("Thread", "Button Case 1");

                for (int i = 0; i <= 10; i++) { // UI thread block
                    TextView textView = findViewById(R.id.textView);
                    textView.setText(String.valueOf(i));

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });
```

- Don't access the Android UI toolkit from outside the UI thread.

```java
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread t = new Thread() { // block the Android Tool kit
                    @Override
                    public void run() {
                        for (int i = 0; i <= 10; i++) {
                            TextView textView = findViewById(R.id.textView);
                            textView.setText(String.valueOf(i));

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }

                };
                t.start();
            }
        });
```

## Code Solutions

- **Worker threads**
  Because of this single-thread model, it's vital to the responsiveness of your application's UI that you don't block the UI thread. If you have operations to perform that aren't instantaneous, make sure to do them in separate background or worker threads. Just remember that you can't update the UI from any thread other than the UI, or main, thread.

`runOnUiThread`

```java
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Thread t = new Thread() { // solutions
                    @Override
                    public void run() {
                        for (int i = 0; i <= 10; i++) {
                            final int x = i;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    TextView textView = findViewById(R.id.textView);
                                    textView.setText(String.valueOf(x));
                                }
                            });
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }

                };
                t.start();
            }
        });

```
`View.post(Runnable)`

```java
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              TextView textView = findViewById(R.id.textView);

                Thread t = new Thread() { // solutions
                    @Override
                    public void run() {
                        for (int i = 0; i <= 10; i++) {
                            final int x = i;
                            textView.post(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(String.valueOf(x));
                                }
                            });
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }

                };
                t.start();
            }
        });

```
`postDelayed`

```java
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              TextView textView = findViewById(R.id.textView);

                Thread t = new Thread() { // solutions
                    @Override
                    public void run() {
                        for (int i = 0; i <= 10; i++) {
                            final int x = i;
                            textView.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(String.valueOf(x));
                                }
                            },5000);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }

                };
                t.start();
            }
        });

```

![image](https://github.com/user-attachments/assets/13c303f1-53d3-4428-ab3e-beec0ec03c9f)
