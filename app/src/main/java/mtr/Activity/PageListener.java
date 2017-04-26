package mtr.Activity;

import android.app.Activity;

/**
 * Created by saurabh on 10/6/16.
 */
public interface PageListener {
    void onActivityCreated(Activity activity);

    void onActivityStarted(Activity activity);

    void onActivityResumed(Activity activity);

    void onActivityPaused(Activity activity);

    void onActivityStopped(Activity activity);

    void onActivitySaveInstanceState(Activity activity);

    void onActivityDestroyed(Activity activity);
}