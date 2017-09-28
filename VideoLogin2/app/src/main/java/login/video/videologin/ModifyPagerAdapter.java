package login.video.videologin;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Alan on 18/11/2016.
 */

public class ModifyPagerAdapter extends PagerAdapter {

    private Context context;
    private int count = 0;

    public ModifyPagerAdapter(Context mContext, int count) {
        this.context = mContext;
        this.count = count;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        //Carregamento dos PageViews a partir de um array presente no arquivo Strings
        View itemView = LayoutInflater.from(context).inflate(R.layout.pager_item, container, false);

        Resources res = context.getResources();
        String[] pagerContents = res.getStringArray(R.array.pagerContents);
        String[] pagerTitles = res.getStringArray(R.array.pagerTitles);

        int index = position - 1;
        if (position == 0) {
            index = count - 1;
        } else if (position == count + 1) {
            index = 0;
        }
        TextView title = (TextView) itemView.findViewById(R.id.viewPager_Title);
        TextView content = (TextView) itemView.findViewById(R.id.viewPager_Content);

        title.setText(pagerTitles[index]);
        content.setText(pagerContents[index]);


        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    @Override
    public int getCount() {
        return count + 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }
}
