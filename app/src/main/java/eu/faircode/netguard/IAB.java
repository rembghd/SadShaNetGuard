package eu.faircode.netguard;

/*
    This file is part of NetGuard.

    NetGuard is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    NetGuard is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with NetGuard.  If not, see <http://www.gnu.org/licenses/>.

    Copyright 2015-2026 by Marcel Bokhorst (M66B)
*/

// SadSha NetGuard free v1: there is no billing and no purchase flow.
// Every feature is unlocked for everyone, so this class is a stub that
// always reports "purchased". The original Play Billing implementation
// (IInAppBillingService AIDL) was removed with it. Call sites are
// unchanged, so no other file had to be touched for this.

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class IAB implements ServiceConnection {
    private static final String TAG = "NetGuard.IAB";

    private Context context;
    private Delegate delegate;

    public interface Delegate {
        void onReady(IAB iab);
    }

    public IAB(Delegate delegate, Context context) {
        this.context = context.getApplicationContext();
        this.delegate = delegate;
    }

    public void bind() {
        // No billing service in the free build.
        Log.i(TAG, "Bind (stub: no billing)");
    }

    public void unbind() {
        // No billing service in the free build.
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        // Never called: no billing service is bound.
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        // Never called: no billing service is bound.
    }

    public boolean isAvailable(String sku) {
        return false;
    }

    public void updatePurchases() {
        // Nothing to update: everything is unlocked.
    }

    public boolean isPurchased(String sku, String type) {
        return true;
    }

    public List<String> getPurchases(String type) {
        return new ArrayList<String>();
    }

    public android.app.PendingIntent getBuyIntent(String sku, boolean subscription) {
        return null;
    }

    public static void setBought(String sku, Context context) {
        Log.i(TAG, "Bought " + sku + " (stub: already unlocked)");
    }

    public static boolean isPurchased(String sku, Context context) {
        // Free v1: all features unlocked for everyone.
        return true;
    }

    public static boolean isPurchasedAny(Context context) {
        // Free v1: all features unlocked for everyone.
        return true;
    }

    public static String getResult(int responseCode) {
        switch (responseCode) {
            case 0:
                return "OK";
            case 1:
                return "USER_CANCELED";
            case 2:
                return "SERVICE_UNAVAILABLE";
            case 3:
                return "BILLING_UNAVAILABLE";
            case 4:
                return "ITEM_UNAVAILABLE";
            case 5:
                return "DEVELOPER_ERROR";
            case 6:
                return "ERROR";
            case 7:
                return "ITEM_ALREADY_OWNED";
            case 8:
                return "ITEM_NOT_OWNED";
            default:
                return Integer.toString(responseCode);
        }
    }
}
