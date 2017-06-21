package ru.noties.iconview.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Good ol' ListView... long time no see
        final ListView listView = (ListView) findViewById(R.id.list_view);
        final Adapter adapter = new Adapter(this);
        adapter.setItems(createItems(this));
        listView.setAdapter(adapter);
    }

    // context for colors
    private static List<ListItem> createItems(@NonNull Context context) {

        final int max = 100;

        final List<ListItem> items = new ArrayList<>(max);

        final int[] colors = context.getResources().getIntArray(R.array.colors);
        final int[] icons = {
                R.drawable.ic_3d_rotation,
                R.drawable.ic_accessibility,
                R.drawable.ic_accessible,
                R.drawable.ic_account_balance,
                R.drawable.ic_account_balance_wallet
        };

        final int colorsLength = colors.length;
        final int iconsLength = icons.length;

        for (int i = 0; i < max; i++) {
            items.add(new ListItem(
                    icons[i % (iconsLength - 1)],
                    colors[i % (colorsLength - 1)]
            ));
        }

        return items;
    }
}
