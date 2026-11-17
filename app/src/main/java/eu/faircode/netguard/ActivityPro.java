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

// SadSha NetGuard free v1: there is no purchase flow. This screen only
// lists the features, which are all enabled for everyone (see IAB.java).
// The old buy buttons, donation challenge and netguard.me links were
// removed. The SKU names are kept because other classes reference them.

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

public class ActivityPro extends AppCompatActivity {
    private static final String TAG = "NetGuard.Pro";

    public static final String SKU_LOG = "log";
    public static final String SKU_FILTER = "filter";
    public static final String SKU_NOTIFY = "notify";
    public static final String SKU_SPEED = "speed";
    public static final String SKU_THEME = "theme";
    public static final String SKU_PRO1 = "pro1";
    public static final String SKU_SUPPORT1 = "support1";
    public static final String SKU_SUPPORT2 = "support2";
    public static final String SKU_DONATION = "donation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "Create");
        Util.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro);

        getSupportActionBar().setTitle(R.string.title_pro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initial state: with the free build every feature shows as enabled
        // and all buy buttons are hidden (see updateState).
        updateState();
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Log.i(TAG, "Up");
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateState() {
        Button btnLog = findViewById(R.id.btnLog);
        Button btnFilter = findViewById(R.id.btnFilter);
        Button btnNotify = findViewById(R.id.btnNotify);
        Button btnSpeed = findViewById(R.id.btnSpeed);
        Button btnTheme = findViewById(R.id.btnTheme);
        Button btnAll = findViewById(R.id.btnAll);
        Button btnDev1 = findViewById(R.id.btnDev1);
        Button btnDev2 = findViewById(R.id.btnDev2);
        TextView tvLog = findViewById(R.id.tvLog);
        TextView tvFilter = findViewById(R.id.tvFilter);
        TextView tvNotify = findViewById(R.id.tvNotify);
        TextView tvSpeed = findViewById(R.id.tvSpeed);
        TextView tvTheme = findViewById(R.id.tvTheme);
        TextView tvAll = findViewById(R.id.tvAll);
        TextView tvDev1 = findViewById(R.id.tvDev1);
        TextView tvDev2 = findViewById(R.id.tvDev2);

        TextView tvLogUnavailable = findViewById(R.id.tvLogUnavailable);
        TextView tvFilterUnavailable = findViewById(R.id.tvFilterUnavailable);

        boolean can = Util.canFilter(this);

        btnLog.setVisibility(IAB.isPurchased(SKU_LOG, this) || !can ? View.GONE : View.VISIBLE);
        btnFilter.setVisibility(IAB.isPurchased(SKU_FILTER, this) || !can ? View.GONE : View.VISIBLE);
        btnNotify.setVisibility(IAB.isPurchased(SKU_NOTIFY, this) ? View.GONE : View.VISIBLE);
        btnSpeed.setVisibility(IAB.isPurchased(SKU_SPEED, this) ? View.GONE : View.VISIBLE);
        btnTheme.setVisibility(IAB.isPurchased(SKU_THEME, this) ? View.GONE : View.VISIBLE);
        btnAll.setVisibility(IAB.isPurchased(SKU_PRO1, this) ? View.GONE : View.VISIBLE);
        btnDev1.setVisibility(IAB.isPurchased(SKU_SUPPORT1, this) ? View.GONE : View.VISIBLE);
        btnDev2.setVisibility(IAB.isPurchased(SKU_SUPPORT2, this) ? View.GONE : View.VISIBLE);

        tvLog.setVisibility(IAB.isPurchased(SKU_LOG, this) && can ? View.VISIBLE : View.GONE);
        tvFilter.setVisibility(IAB.isPurchased(SKU_FILTER, this) && can ? View.VISIBLE : View.GONE);
        tvNotify.setVisibility(IAB.isPurchased(SKU_NOTIFY, this) ? View.VISIBLE : View.GONE);
        tvSpeed.setVisibility(IAB.isPurchased(SKU_SPEED, this) ? View.VISIBLE : View.GONE);
        tvTheme.setVisibility(IAB.isPurchased(SKU_THEME, this) ? View.VISIBLE : View.GONE);
        tvAll.setVisibility(IAB.isPurchased(SKU_PRO1, this) ? View.VISIBLE : View.GONE);
        tvDev1.setVisibility(IAB.isPurchased(SKU_SUPPORT1, this) ? View.VISIBLE : View.GONE);
        tvDev2.setVisibility(IAB.isPurchased(SKU_SUPPORT2, this) ? View.VISIBLE : View.GONE);

        tvLogUnavailable.setVisibility(can ? View.GONE : View.VISIBLE);
        tvFilterUnavailable.setVisibility(can ? View.GONE : View.VISIBLE);
    }
}
