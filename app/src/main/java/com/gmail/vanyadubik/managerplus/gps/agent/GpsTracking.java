//package com.gmail.vanyadubik.managerplus.gps.agent;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.pm.PackageManager;
//import android.os.SystemClock;
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.gmail.vanyadubik.managerplus.gps.service.ServiceGpsTracking;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Locale;
//
//import com.gmail.vanyadubik.managerplus.gps.agent.MdmService;
//import com.gmail.vanyadubik.managerplus.gps.agent.SharedStorage;
//import com.gmail.vanyadubik.managerplus.gps.agent.BuildConfig;
//import com.gmail.vanyadubik.managerplus.gps.agent.Provider;
//
//public class GpsTracking {
//    static final String BOOLEAN_PARAMS = "boolean_shared_preferences";
//    static final String CHECK_PREFERENCES = "initializer_wait_for_check_preferences";
//    static final String INITIALIZER_ACTION = "ru.agentp2.GpsTracking.initializerBroadcaster";
//    static final String LONG_PARAMS = "long_shared_preferences_from_service";
//    static final String NEW_LOCATION = "service_have_a_new_location_for_initializer";
//    static final String NUMERIC_PARAMS = "numeric_shared_preferences";
//    static final String PREF_INTERVAL = "gpsTrackingInterval";
//    static final String PREF_PERIOD = "gpsTrackingPeriod";
//    static final String PREF_SERVERADDRESS = "gpsTrackingServerAddress";
//    static final String RECEIVER_ACTION = "brc_receiver_action";
//    static final String RENEW_PREFERENCES = "initializer_have_new_preferences";
//    static final String SERVICE_ACTION = "ru.agentp2.serviceGpsTracking.serviceBroadcaster";
//    static final String SET_PREFERENCES = "send_preferences_to_service";
//    static final String STRING_PARAMS = "string_shared_preferences";
//    static final String STRING_PARAMS_FROM_SERVICE = "string_shared_preferences_from_service";
//    private final String LAST_DATE_KEY;
//    private final String LAST_LATITUDE_KEY;
//    private final String LAST_LOCATIONSOURCE_KEY;
//    private final String LAST_LONGITUDE_KEY;
//    private final String LAST_SPEED_KEY;
//    private final String PREF_DAYS;
//    private final String PREF_ENABLE;
//    private final String PREF_ERPID;
//    private final String PREF_FILE;
//    private final String PREF_GPSTIME;
//    private final String PREF_ISLOCATIONSOURCE;
//    private final String PREF_LOCATIONSOURCE;
//    private final String PREF_PASSIVECONNECTION;
//    private final String PREF_PASSWORD;
//    private final String PREF_PORT;
//    private final String PREF_PPCGUID;
//    private final String PREF_SENDNULL;
//    private final String PREF_SERVERTYPE;
//    private final String PREF_SPEED;
//    private final String PREF_TIME;
//    private final String PREF_TRACKFILENAME;
//    private final String PREF_TRACKFILEPATH;
//    private final String PREF_USERNAME;
//    private boolean[] _booleanParams;
//    private Context _context;
//    private int[] _integerParams;
//    private boolean _isReceiverRegistered;
//    private boolean _isStarted;
//    private ArrayList<String> _stringParams;
//    private BroadcastReceiver gpsTrackingReceiver;
//
//    /* renamed from: ru.agentplus.tracking.GpsTracking.1 */
//    class C04711 extends BroadcastReceiver {
//        C04711() {
//        }
//
//        public void onReceive(Context context, Intent intent) {
//            GpsTracking.this.setLastGpsData(intent.getLongExtra(GpsTracking.LONG_PARAMS, 0), intent.getStringArrayListExtra(GpsTracking.STRING_PARAMS_FROM_SERVICE));
//        }
//    }
//
//    private class GpsData {
//        String _date;
//        double _latitude;
//        int _locationSource;
//        double _longitude;
//        double _speed;
//
//        GpsData(Context context) {
//            long longTime = SharedStorage.getLong(context, "last_date_key", 0);
//            Calendar.getInstance().setTimeInMillis(longTime);
//            this._date = String.format(Locale.US, "%d:%d:%d %d:%d", new Object[]{Integer.valueOf(timeStamp.get(1)), Integer.valueOf(timeStamp.get(2) + 1), Integer.valueOf(timeStamp.get(5)), Integer.valueOf(timeStamp.get(11)), Integer.valueOf(timeStamp.get(12))});
//            this._longitude = Double.parseDouble(SharedStorage.getString(context, "last_longitude_key", "0"));
//            this._latitude = Double.parseDouble(SharedStorage.getString(context, "last_latitude_key", "0"));
//            this._speed = Double.parseDouble(SharedStorage.getString(context, "last_speed_key", "0"));
//            this._locationSource = Integer.parseInt(SharedStorage.getString(context, "last_locationsource_key", "0"));
//        }
//    }
//
//    enum booleanPrefs {
//        SPEED(0),
//        GPS_TIME(1),
//        LOCATION_SOURCE(2),
//        SEND_NULL(3),
//        PASSIVE_CONNECTION(4);
//
//        private int _prefId;
//
//        private booleanPrefs(int prefId) {
//            this._prefId = prefId;
//        }
//
//        public int getID() {
//            return this._prefId;
//        }
//    }
//
//    enum integerPrefs {
//        TIME(0),
//        INTERVAL(1),
//        DAYS(2),
//        PERIOD(3),
//        PORT(4),
//        SERVER_TYPE(5),
//        INDEX_LOCATION_SOURCE(6);
//
//        private int _prefId;
//
//        private integerPrefs(int prefId) {
//            this._prefId = prefId;
//        }
//
//        public int getID() {
//            return this._prefId;
//        }
//    }
//
//    enum serviceStringPrefs {
//        LATITUDE(0),
//        LONGTITUDE(1),
//        SPEED(2),
//        LOCATION_SOURCE(3);
//
//        private int _prefId;
//
//        private serviceStringPrefs(int prefId) {
//            this._prefId = prefId;
//        }
//
//        public int getID() {
//            return this._prefId;
//        }
//    }
//
//    enum stringPrefs {
//        FILE(0),
//        SERVER_ADDRESS(1),
//        PPC_GUID(2),
//        ERP_ID(3),
//        USERNAME(4),
//        PASSWORD(5),
//        FILEPATH(6),
//        FILENAME(7);
//
//        private int _prefId;
//
//        private stringPrefs(int prefId) {
//            this._prefId = prefId;
//        }
//
//        public int getID() {
//            return this._prefId;
//        }
//    }
//
//    private native void takeGpsTrackingSettingsFromJava(boolean z, int i, int i2, int i3, boolean z2, boolean z3, String str, int i4, String str2, String str3, String str4, int i5, int i6, int i7, boolean z4, boolean z5, String str5, String str6, String str7, boolean z6);
//
//    public GpsTracking(Context context) {
//        this.PREF_ENABLE = "gpsTrackingEnable";
//        this.PREF_LOCATIONSOURCE = "LocationSource";
//        this.PREF_DAYS = "gpsTrackingDays";
//        this.PREF_TIME = "gpsTrackingTime";
//        this.PREF_SPEED = "gpsTrackingSpeed";
//        this.PREF_GPSTIME = "gpsTrackingGpsTime";
//        this.PREF_SERVERTYPE = "gpsTrackingServerType";
//        this.PREF_FILE = "gpsTrackingFile";
//        this.PREF_PPCGUID = "gpsTrackingPPCGuid";
//        this.PREF_ISLOCATIONSOURCE = "IsWriteLocationSource";
//        this.PREF_ERPID = "gpsTrackingErpId";
//        this.PREF_PORT = "gpsTrackingPort";
//        this.PREF_SENDNULL = "gpsTrackingFixGpsDisabling";
//        this.PREF_USERNAME = "gpsTrackingUsername";
//        this.PREF_PASSWORD = "gpsTrackingPassword";
//        this.PREF_TRACKFILEPATH = "gpsTrackingFilePath";
//        this.PREF_TRACKFILENAME = "gpsTrackingFileName";
//        this.PREF_PASSIVECONNECTION = "gpsTrackingIsPassiveConnection";
//        this.LAST_DATE_KEY = "last_date_key";
//        this.LAST_LATITUDE_KEY = "last_latitude_key";
//        this.LAST_LONGITUDE_KEY = "last_longitude_key";
//        this.LAST_SPEED_KEY = "last_speed_key";
//        this.LAST_LOCATIONSOURCE_KEY = "last_locationsource_key";
//        this._isStarted = false;
//        this._isReceiverRegistered = false;
//        this._integerParams = new int[7];
//        this._booleanParams = new boolean[5];
//        this._stringParams = new ArrayList();
//        this._context = context;
//    }
//
//    private Context getContext() {
//        return this._context;
//    }
//
//    private boolean isSupported() {
//        PackageManager packageManager = getContext().getPackageManager();
//        boolean hasSystemFeatureLocation = packageManager.hasSystemFeature(Provider.FromIndex(SharedStorage.getInteger(getContext(), "LocationSource", 1)).getFeatureName());
//        boolean gpsLocation = packageManager.hasSystemFeature("android.hardware.location.gps");
//        boolean networkLocation = packageManager.hasSystemFeature("android.hardware.location.network");
//        if ((gpsLocation || networkLocation) && hasSystemFeatureLocation) {
//            return true;
//        }
//        return false;
//    }
//
//    private void sendNewPreferences(String intentAction) {
//        readGpsTrackingSettings(true);
//        Intent intent = new Intent(SERVICE_ACTION);
//        intent.putExtra(NUMERIC_PARAMS, this._integerParams);
//        intent.putExtra(BOOLEAN_PARAMS, this._booleanParams);
//        intent.putExtra(STRING_PARAMS, this._stringParams);
//        intent.putExtra(RECEIVER_ACTION, intentAction);
//        getContext().sendBroadcast(intent);
//    }
//
//    private void setReceiver() {
//        this.gpsTrackingReceiver = new C04711();
//        getContext().registerReceiver(this.gpsTrackingReceiver, new IntentFilter(INITIALIZER_ACTION));
//        this._isReceiverRegistered = true;
//    }
//
//    public boolean startGpsTracking() {
//        if (!isSupported() || this._isStarted) {
//            return false;
//        }
//        this._isStarted = true;
//        SharedStorage.setBoolean(getContext(), "gpsTrackingEnable", Boolean.valueOf(true));
//        setReceiver();
//        getContext().startService(new Intent(getContext(), ServiceGpsTracking.class));
//        SystemClock.sleep(300);
//        sendNewPreferences(SET_PREFERENCES);
//        return true;
//    }
//
//    public void stopGpsTracking() {
//        this._isStarted = false;
//        SharedStorage.setBoolean(getContext(), "gpsTrackingEnable", Boolean.valueOf(false));
//        if (this._isReceiverRegistered) {
//            this._isReceiverRegistered = false;
//            getContext().unregisterReceiver(this.gpsTrackingReceiver);
//        }
//        getContext().stopService(new Intent(getContext(), ServiceGpsTracking.class));
//    }
//
//    private int getGpsTrackingStatus() {
//        return SharedStorage.getBoolean(getContext(), "gpsTrackingEnable", false) ? 1 : 0;
//    }
//
//    private void setLastGpsData(long datetime, ArrayList<String> stringParams) {
//        SharedStorage.setLong(getContext(), "last_date_key", datetime);
//        SharedStorage.setString(getContext(), "last_latitude_key", (String) stringParams.get(serviceStringPrefs.LATITUDE.getID()));
//        SharedStorage.setString(getContext(), "last_longitude_key", (String) stringParams.get(serviceStringPrefs.LONGTITUDE.getID()));
//        SharedStorage.setString(getContext(), "last_speed_key", (String) stringParams.get(serviceStringPrefs.SPEED.getID()));
//        SharedStorage.setString(getContext(), "last_locationsource_key", (String) stringParams.get(serviceStringPrefs.LOCATION_SOURCE.getID()));
//    }
//
//    private GpsData getLastGpsData() {
//        if (SharedStorage.getLong(getContext(), "last_date_key", 0) == 0) {
//            return null;
//        }
//        return new GpsData(getContext());
//    }
//
//    private void writeGpsTrackingSettings(int interval, int time, int days, boolean bSpeed, boolean bGpsTime, String fileName, int serverType, String serverAddress, String ppcGuid, String erpId, int period, int port, int locationSource, boolean bLocationSource, boolean bSendNull, String FTPfileName, String userName, String password, String FTPfolder, boolean passiveConnection) {
//        boolean isUpdated = false;
//        SharedStorage.setBoolean(getContext(), "gpsTrackingFixGpsDisabling", Boolean.valueOf(bSendNull));
//        if (interval != SharedStorage.getInteger(getContext(), PREF_INTERVAL, 0)) {
//            isUpdated = true;
//            SharedStorage.setInteger(getContext(), PREF_INTERVAL, interval);
//        }
//        if (time != SharedStorage.getInteger(getContext(), "gpsTrackingTime", 0)) {
//            isUpdated = true;
//            SharedStorage.setInteger(getContext(), "gpsTrackingTime", time);
//        }
//        if (days != SharedStorage.getInteger(getContext(), "gpsTrackingDays", 0)) {
//            isUpdated = true;
//            SharedStorage.setInteger(getContext(), "gpsTrackingDays", days);
//        }
//        if (serverType != SharedStorage.getInteger(getContext(), "gpsTrackingServerType", 0)) {
//            isUpdated = true;
//            SharedStorage.setInteger(getContext(), "gpsTrackingServerType", serverType);
//        }
//        if (period != SharedStorage.getInteger(getContext(), PREF_PERIOD, 0)) {
//            isUpdated = true;
//            SharedStorage.setInteger(getContext(), PREF_PERIOD, period);
//        }
//        if (port != SharedStorage.getInteger(getContext(), "gpsTrackingPort", 0)) {
//            isUpdated = true;
//            SharedStorage.setInteger(getContext(), "gpsTrackingPort", port);
//        }
//        if (locationSource != SharedStorage.getInteger(getContext(), "LocationSource", 1)) {
//            isUpdated = true;
//            SharedStorage.setInteger(getContext(), "LocationSource", locationSource);
//        }
//        if (bSpeed != SharedStorage.getBoolean(getContext(), "gpsTrackingSpeed", false)) {
//            isUpdated = true;
//            SharedStorage.setBoolean(getContext(), "gpsTrackingSpeed", Boolean.valueOf(bSpeed));
//        }
//        if (bGpsTime != SharedStorage.getBoolean(getContext(), "gpsTrackingGpsTime", false)) {
//            isUpdated = true;
//            SharedStorage.setBoolean(getContext(), "gpsTrackingGpsTime", Boolean.valueOf(bGpsTime));
//        }
//        if (bLocationSource != SharedStorage.getBoolean(getContext(), "IsWriteLocationSource", false)) {
//            isUpdated = true;
//            SharedStorage.setBoolean(getContext(), "IsWriteLocationSource", Boolean.valueOf(bLocationSource));
//        }
//        if (!serverAddress.equals(SharedStorage.getString(getContext(), PREF_SERVERADDRESS, MdmService.getDeviceId(this._context)))) {
//            isUpdated = true;
//            SharedStorage.setString(getContext(), PREF_SERVERADDRESS, serverAddress);
//        }
//        if (!TextUtils.isEmpty(ppcGuid)) {
//            if (!ppcGuid.equals(SharedStorage.getString(getContext(), "gpsTrackingPPCGuid", BuildConfig.VERSION_NAME))) {
//                isUpdated = true;
//                SharedStorage.setString(getContext(), "gpsTrackingPPCGuid", ppcGuid);
//            }
//        }
//        if (!TextUtils.isEmpty(erpId)) {
//            if (!erpId.equals(SharedStorage.getString(getContext(), "gpsTrackingErpId", BuildConfig.VERSION_NAME))) {
//                isUpdated = true;
//                SharedStorage.setString(getContext(), "gpsTrackingErpId", erpId);
//            }
//        }
//        boolean isFileRenamed = true;
//        String oldFileName = SharedStorage.getString(getContext(), "gpsTrackingFile", BuildConfig.VERSION_NAME);
//        if (!(oldFileName.equals(BuildConfig.VERSION_NAME) || oldFileName.equals(fileName) || fileName.equals(BuildConfig.VERSION_NAME))) {
//            File file = new File(oldFileName);
//            if (file.exists()) {
//                isFileRenamed = file.renameTo(new File(fileName));
//                if (!isFileRenamed) {
//                    Log.w("agentp2", "An error occurred while renaming the file in GpsTracking method");
//                }
//            }
//        }
//        if (!(oldFileName.equals(fileName) || fileName.equals(BuildConfig.VERSION_NAME) || !isFileRenamed)) {
//            isUpdated = true;
//            SharedStorage.setString(getContext(), "gpsTrackingFile", fileName);
//        }
//        if (!TextUtils.isEmpty(FTPfileName)) {
//            if (!FTPfileName.equals(SharedStorage.getString(getContext(), "gpsTrackingFileName", BuildConfig.VERSION_NAME))) {
//                isUpdated = true;
//                SharedStorage.setString(getContext(), "gpsTrackingFileName", FTPfileName);
//            }
//        }
//        if (!TextUtils.isEmpty(userName)) {
//            if (!userName.equals(SharedStorage.getString(getContext(), "gpsTrackingUsername", BuildConfig.VERSION_NAME))) {
//                isUpdated = true;
//                SharedStorage.setString(getContext(), "gpsTrackingUsername", userName);
//            }
//        }
//        if (!TextUtils.isEmpty(password)) {
//            if (!password.equals(SharedStorage.getString(getContext(), "gpsTrackingPassword", BuildConfig.VERSION_NAME))) {
//                isUpdated = true;
//                SharedStorage.setString(getContext(), "gpsTrackingPassword", password);
//            }
//        }
//        if (!TextUtils.isEmpty(FTPfolder)) {
//            if (!FTPfolder.equals(SharedStorage.getString(getContext(), "gpsTrackingFilePath", BuildConfig.VERSION_NAME))) {
//                isUpdated = true;
//                SharedStorage.setString(getContext(), "gpsTrackingFilePath", FTPfolder);
//            }
//        }
//        if (passiveConnection != SharedStorage.getBoolean(getContext(), "gpsTrackingIsPassiveConnection", false)) {
//            isUpdated = true;
//            SharedStorage.setBoolean(getContext(), "gpsTrackingIsPassiveConnection", Boolean.valueOf(passiveConnection));
//        }
//        if (this._isStarted && isUpdated) {
//            sendNewPreferences(RENEW_PREFERENCES);
//        }
//        if (this._isStarted && !isUpdated) {
//            sendNewPreferences(CHECK_PREFERENCES);
//        }
//    }
//
//    private void readGpsTrackingSettings(boolean isRenew) {
//        int interval = SharedStorage.getInteger(getContext(), PREF_INTERVAL, 0);
//        int time = SharedStorage.getInteger(getContext(), "gpsTrackingTime", 0);
//        int days = SharedStorage.getInteger(getContext(), "gpsTrackingDays", 0);
//        int serverType = SharedStorage.getInteger(getContext(), "gpsTrackingServerType", 0);
//        int period = SharedStorage.getInteger(getContext(), PREF_PERIOD, 0);
//        int port = SharedStorage.getInteger(getContext(), "gpsTrackingPort", 0);
//        int indexLocationSource = SharedStorage.getInteger(getContext(), "LocationSource", 1);
//        boolean bSpeed = SharedStorage.getBoolean(getContext(), "gpsTrackingSpeed", false);
//        boolean bGpsTime = SharedStorage.getBoolean(getContext(), "gpsTrackingGpsTime", false);
//        boolean bIsLocationSource = SharedStorage.getBoolean(getContext(), "IsWriteLocationSource", false);
//        boolean enable = SharedStorage.getBoolean(getContext(), "gpsTrackingEnable", false);
//        boolean bSendNull = SharedStorage.getBoolean(getContext(), "gpsTrackingFixGpsDisabling", false);
//        boolean passiveConnection = SharedStorage.getBoolean(getContext(), "gpsTrackingIsPassiveConnection", false);
//        String file = SharedStorage.getString(getContext(), "gpsTrackingFile", BuildConfig.VERSION_NAME);
//        String serverAddress = SharedStorage.getString(getContext(), PREF_SERVERADDRESS, MdmService.getDeviceId(this._context));
//        String PPCGuid = SharedStorage.getString(getContext(), "gpsTrackingPPCGuid", BuildConfig.VERSION_NAME);
//        String erpId = SharedStorage.getString(getContext(), "gpsTrackingErpId", BuildConfig.VERSION_NAME);
//        String username = SharedStorage.getString(getContext(), "gpsTrackingUsername", BuildConfig.VERSION_NAME);
//        String password = SharedStorage.getString(getContext(), "gpsTrackingPassword", BuildConfig.VERSION_NAME);
//        String filePath = SharedStorage.getString(getContext(), "gpsTrackingFilePath", BuildConfig.VERSION_NAME);
//        String fileName = SharedStorage.getString(getContext(), "gpsTrackingFileName", BuildConfig.VERSION_NAME);
//        if (isRenew) {
//            this._integerParams[integerPrefs.TIME.getID()] = time;
//            this._integerParams[integerPrefs.INTERVAL.getID()] = interval;
//            this._integerParams[integerPrefs.DAYS.getID()] = days;
//            this._integerParams[integerPrefs.PERIOD.getID()] = period;
//            this._integerParams[integerPrefs.PORT.getID()] = port;
//            this._integerParams[integerPrefs.SERVER_TYPE.getID()] = serverType;
//            this._integerParams[integerPrefs.INDEX_LOCATION_SOURCE.getID()] = indexLocationSource;
//            this._booleanParams[booleanPrefs.SPEED.getID()] = bSpeed;
//            this._booleanParams[booleanPrefs.GPS_TIME.getID()] = bGpsTime;
//            this._booleanParams[booleanPrefs.LOCATION_SOURCE.getID()] = bIsLocationSource;
//            this._booleanParams[booleanPrefs.SEND_NULL.getID()] = bSendNull;
//            this._booleanParams[booleanPrefs.PASSIVE_CONNECTION.getID()] = passiveConnection;
//            this._stringParams.add(stringPrefs.FILE.getID(), file);
//            this._stringParams.add(stringPrefs.SERVER_ADDRESS.getID(), serverAddress);
//            this._stringParams.add(stringPrefs.PPC_GUID.getID(), PPCGuid);
//            this._stringParams.add(stringPrefs.ERP_ID.getID(), erpId);
//            this._stringParams.add(stringPrefs.USERNAME.getID(), username);
//            this._stringParams.add(stringPrefs.PASSWORD.getID(), password);
//            this._stringParams.add(stringPrefs.FILEPATH.getID(), filePath);
//            this._stringParams.add(stringPrefs.FILENAME.getID(), fileName);
//            return;
//        }
//        takeGpsTrackingSettingsFromJava(enable, interval, time, days, bSpeed, bGpsTime, file, serverType, serverAddress, PPCGuid, erpId, period, port, indexLocationSource, bIsLocationSource, bSendNull, username, password, filePath, passiveConnection);
//    }
//}