package simple.test.com.myapplication;

import android.app.Application;

import com.eden.common.util.ContextProvider;

/**
 * Created by Administrator on 2016/4/3.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ContextProvider.getInstance().setContext(this);
    }
}
