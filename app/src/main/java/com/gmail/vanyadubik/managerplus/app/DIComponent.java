package com.gmail.vanyadubik.managerplus.app;

import com.gmail.vanyadubik.managerplus.activity.SettingsActivity;
import com.gmail.vanyadubik.managerplus.activity.StartActivity;
import com.gmail.vanyadubik.managerplus.fragment.FuelListFragment;
import com.gmail.vanyadubik.managerplus.fragment.VisitListFragment;
import com.gmail.vanyadubik.managerplus.fragment.WaybillListFragment;
import com.gmail.vanyadubik.managerplus.fragment.WorkPlaceFragmentMap;
import com.gmail.vanyadubik.managerplus.fragment.WorkPlaceFragmentMapAndroidAPI;
import com.gmail.vanyadubik.managerplus.fragment.WorkPlaseFragment;
import com.gmail.vanyadubik.managerplus.modules.ActivityUtilsApiModule;
import com.gmail.vanyadubik.managerplus.modules.DataApiModule;
import com.gmail.vanyadubik.managerplus.modules.ErrorUtilsApiModule;
import com.gmail.vanyadubik.managerplus.modules.NetworkUtilsApiModule;
import com.gmail.vanyadubik.managerplus.modules.PhoneUtilsApiModule;
import com.gmail.vanyadubik.managerplus.service.gps.GPSTrackerService;
import com.gmail.vanyadubik.managerplus.service.gps.GPSTrackerServiceAndroidAPI;
import com.gmail.vanyadubik.managerplus.service.gps.SyncIntentTrackService;
import com.gmail.vanyadubik.managerplus.task.SyncIntentService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DataApiModule.class, NetworkUtilsApiModule.class,
        ActivityUtilsApiModule.class, ErrorUtilsApiModule.class, PhoneUtilsApiModule.class})
public interface DIComponent {

    void inject(StartActivity startActivity);

    void inject(SettingsActivity settingsActivity);

    void inject(WorkPlaseFragment workPlaseFragment);

    void inject(WorkPlaceFragmentMap workPlaceFragmentMap);

    void inject(WorkPlaceFragmentMapAndroidAPI workPlaceFragmentMapAndroidAPI);

    void inject(WaybillListFragment waybillListFragment);

    void inject(FuelListFragment fuelListFragment);

    void inject(VisitListFragment visitListFragment);

    void inject(GPSTrackerService gpsTrackerService);

    void inject(GPSTrackerServiceAndroidAPI gpsTrackerServiceAndroidAPI);

    void inject(SyncIntentTrackService syncIntentTrackService);

    void inject(SyncIntentService syncIntentService);
}
