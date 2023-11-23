package kib.dev.testalertdialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class AvatarAdapter extends BaseAdapter {
    private Context mContext;
    private int[] avatarIds;

    public AvatarAdapter(Context context, int[] avatarIds) {
        mContext = context;
        this.avatarIds = avatarIds;
    }

    @Override
    public int getCount() {
        return avatarIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200)); // Adjust the size as needed
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(avatarIds[position]);
        return imageView;
    }
}

