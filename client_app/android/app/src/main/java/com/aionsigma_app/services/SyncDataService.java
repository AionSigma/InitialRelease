package com.aionsigma_app.services;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.aionsigma_app.LocationUtils;
import com.aionsigma_app.R;
import com.aionsigma_app.room.AppDatabase;
import com.aionsigma_app.room.UserInfo;
import com.aionsigma_app.room.UserInfoDao;

import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class SyncDataService extends Service {
    private static Timer timer = new Timer();
    private Context context;
    private final int timerInterval = 1000 * 30;// * Integer.parseInt(getString(R.string.timer_Interval_minutes));
    
    String lattitude,longitude;
    String error = "";
    String message = "";
    Date lastTime = new Date();

    public SyncDataService(){
        super();
        context = this;
        
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(@android.support.annotation.Nullable Intent intent, int flags, int startId) {
        timer.scheduleAtFixedRate(new mainTask(startId), 0, timerInterval);
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        AppDatabase.destroyInstance();
        Toast.makeText(context, "Service stopped ..", Toast.LENGTH_LONG).show();
    }

    @SuppressLint("HandlerLeak")
    private final Handler toastHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if(error!=""){
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
            }
            if(message!=""){
                long diffDate = diffDateInSeconds(lastTime, new Date());
                Toast.makeText(getApplicationContext(),"Seconds: "+ diffDate+" --"+ message, Toast.LENGTH_SHORT).show();
                lastTime = new Date();
            }         
        }
    };

    private long diffDateInSeconds(Date startDate, Date endDate){
        long diffInMs = endDate.getTime() - startDate.getTime();
        long diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMs);
        return diffInSec;
    }

    private class mainTask extends TimerTask
    {
        int taskId;
        public mainTask(int taskId){
            this.taskId = taskId;
        }
        public void run()
        {
            

            //to do ....
            //Toast.makeText(getApplicationContext(), "timer interval time", Toast.LENGTH_LONG).show();
            // try{
            //     PopulateDbAsync task = new PopulateDbAsync(context );
            //     task.execute();
            // }catch (Exception ex){
            //     error = ex.getMessage();
            // }

            
            try{
                AppDatabase db = AppDatabase.getAppDatabase(context);
                UserInfo userInfo = new UserInfo("test","123456789","test");
                UserInfoDao myDao = db.userInfoDao();
                myDao.insert(userInfo);
                List<UserInfo> userInfoList = myDao.getAll();
                int index = userInfoList.size()-1;
                message = userInfoList.get(index).getUserInfoId() + "---" + userInfoList.get(index).getData();
            }catch (Exception ex){
                error = ex.getMessage();
            }
            toastHandler.sendEmptyMessage(0);
           
            //stopSelf(taskId);
        }
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, String> {

        private final AppDatabase db;
        private final Context context;
        LocationUtils locationUtils;

        PopulateDbAsync(Context context) {
            db = AppDatabase.getAppDatabase(context);
            locationUtils = new LocationUtils(context);
            this.context = context;
        }

        @Override
        protected String doInBackground(final Void... params) {
            String lattitude, longitude;

            try {
                Location location = locationUtils.getLocation();
                if(location!= null){
                    double latti = location.getLatitude();
                    double longi = location.getLongitude();
                    lattitude = String.valueOf(latti);
                    longitude = String.valueOf(longi);
                }
            } catch (Exception e) {
                return e.getMessage();
            }

            try{
                UserInfo userInfo = new UserInfo("test","123456789","test");
                UserInfoDao myDao = db.userInfoDao();
                myDao.insert(userInfo);
                List<UserInfo> userInfoList = myDao.getAll();
                return userInfoList.get(0).getUserInfoId() + "---" + userInfoList.get(0).getData();
            }catch (Exception ex){
                return ex.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String agentsCount) {
            Toast.makeText(context, agentsCount, Toast.LENGTH_LONG).show();
        }

    }
}
