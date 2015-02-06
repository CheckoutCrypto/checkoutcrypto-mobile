// ccService.aidl
package com.checkoutcrypto.ccProvider;


// Declare any non-default types here with import statements

/** Example service interface */
interface ccService {
    /** Request the process ID of this service, to do evil things with it. */
    String getKey();
    String getAppName();
    void setKey(String uid, String appname, String apikey, String updated, boolean update);
}