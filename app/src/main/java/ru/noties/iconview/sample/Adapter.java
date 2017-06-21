package ru.noties.iconview.sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import ru.noties.iconview.IconView;

public class Adapter extends BaseAdapter {

    private final LayoutInflater inflater;

    private List<ListItem> items;

    public Adapter(@NonNull Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setItems(@Nullable List<ListItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items != null
                ? items.size()
                : 0;
    }

    @Override
    public ListItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        final ListItem item = getItem(position);
        return item.color() + item.icon();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View view;
        final ListItem item = getItem(position);

        if (convertView == null) {
            view = inflater.inflate(R.layout.adapter_list_item, parent, false);
            view.setTag(R.id.adapter_tag, new Holder(view));
        } else {
            view = convertView;
        }

        final Holder holder = (Holder) view.getTag(R.id.adapter_tag);
        holder.iconView.setColor(item.color());
        holder.iconView.setImageResource(item.icon());

        return view;
    }

    private static class Holder {

        final IconView iconView;

        Holder(@NonNull View view) {
            this.iconView = (IconView) view.findViewById(R.id.icon_view);
        }
    }
}
