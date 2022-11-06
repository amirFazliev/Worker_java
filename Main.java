package hm42FuncInterface;

public class Main {
    public static int KEY = 33;

    public static void main(String[] args) {
        OnTaskDoneListener listener = System.out::println;
        OnTaskErrorListener error = () -> KEY;

        Worker worker = new Worker(listener, error);
        worker.start();
    }
}

@FunctionalInterface
interface OnTaskDoneListener {
    void onDone(String result);
}

@FunctionalInterface
interface OnTaskErrorListener {
    int onError();
}
class Worker implements OnTaskDoneListener {
    private OnTaskDoneListener callback;
    private OnTaskErrorListener errorCallback;

    public Worker(OnTaskDoneListener callback, OnTaskErrorListener errorCallback) {
        this.callback = callback;
        this.errorCallback = errorCallback;
    }

    public void start() {
        for (int i = 0; i < errorCallback.onError(); i++) {
            callback.onDone("Task " + i + " is done");
        }
        callback.onDone("Код не сработал. Ошибка значения - " + errorCallback.onError());
        for (int i = errorCallback.onError()+1; i < 100; i++) {
            callback.onDone("Task " + i + " is done");
        }
    }

    @Override
    public void onDone(String result) {
        System.out.println(result);
    }

}
