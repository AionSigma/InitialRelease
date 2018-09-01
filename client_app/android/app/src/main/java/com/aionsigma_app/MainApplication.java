package com.aionsigma_app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import com.aionsigma_app.services.SyncDataService;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.Arrays;
import java.util.List;
import com.reactnativenavigation.NavigationApplication;
import com.oblador.vectoricons.VectorIconsPackage;

public class MainApplication extends NavigationApplication {

  @Override
  public boolean isDebug() {
    // Make sure you are using BuildConfig from your own application
    return BuildConfig.DEBUG;
  }

  protected List<ReactPackage> getPackages() {
    // Add additional packages you require here
    // No need to add RnnPackage and MainReactPackage
    return Arrays.<ReactPackage>asList(
            // eg. new VectorIconsPackage()
            new VectorIconsPackage()
    );
  }

  @Override
  public List<ReactPackage> createAdditionalReactPackages() {
    return getPackages();
  }

  @Override
  public String getJSMainModuleName() {
    return "index";
  }

  @Override
  public void onCreate() {
    super.onCreate();
    try {
      if( !isServiceRunning()) {
        Intent intent = new Intent(this, SyncDataService.class);
        startService(intent);
      }
    }
    catch (Exception ex){
      Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
    }

  }


  private boolean isServiceRunning() {
    ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
    for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)){
      if("com.aionsigma_app.services.SyncDataService".equals(service.service.getClassName())) {
        return true;
      }
    }
    return false;
  }
}

// public class MainApplication extends Application implements NavigationApplication {

//   private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
//     @Override
//     public boolean getUseDeveloperSupport() {
//       return BuildConfig.DEBUG;
//     }

//     @Override
//     protected List<ReactPackage> getPackages() {
//       return Arrays.<ReactPackage>asList(
//           new MainReactPackage()
//       );
//     }

//     @Override
//     protected String getJSMainModuleName() {
//       return "index";
//     }
//   };

//   @Override
//   public ReactNativeHost getReactNativeHost() {
//     return mReactNativeHost;
//   }

//   @Override
//   public void onCreate() {
//     super.onCreate();
//     SoLoader.init(this, /* native exopackage */ false);
//     try {
//       if( !isServiceRunning()) {
//         Intent intent = new Intent(this, SyncDataService.class);
//         startService(intent);
//       }
//     }
//     catch (Exception ex){
//       Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
//     }

//   }

//   private boolean isServiceRunning() {
//     ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//     for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)){
//       if("com.aionsigma_app.services.SyncDataService".equals(service.service.getClassName())) {
//         return true;
//       }
//     }
//     return false;
//   }
// }
