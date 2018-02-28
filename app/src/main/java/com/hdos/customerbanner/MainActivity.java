package com.hdos.customerbanner;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] images = {
            /*"http://g.hiphotos.baidu.com/image/pic/item/6159252dd42a2834da6660c459b5c9ea14cebf39.jpg",

            "http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg",
            "http://d.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd976427f6c3901213fb80e911c.jpg",
            "http://d.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd976427f6c3901213fb80e911c.jpg",*/
            "http://d.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd976427f6c3901213fb80e911c.jpg"
    };
    //轮播下面的小点（小圆点是本地的，自己导入的图片）
    private int[] indicator = {R.drawable.banner_dot, R.drawable.banner_dot_active};
    private ConvenientBanner convenientBanner;
    //图片加载地址的集合
    private List<String> bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);

        bean = Arrays.asList(images);
        convenientBanner.setCanLoop(false);
        convenientBanner.setPointViewVisible(false)
                //设置小点
                .setPageIndicator(indicator);
        //允许手动轮播
        convenientBanner.setManualPageable(true);
        //设置自动轮播的时间
        convenientBanner.startTurning(2000);
        //设置点击事件
        //泛型为具体实现类ImageLoaderHolder
        convenientBanner.setPages(new CBViewHolderCreator<NetImageLoadHolder>() {
            @Override
            public NetImageLoadHolder createHolder() {
                return new NetImageLoadHolder();
            }
        }, bean);

        //设置每个pager的点击事件
        convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "点击了" + convenientBanner.getCurrentItem(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class NetImageLoadHolder implements Holder<String> {
        private ImageView image_lv;

        //可以是一个布局也可以是一个Imageview
        @Override
        public ImageView createView(Context context) {
            image_lv = new ImageView(context);
            image_lv.setScaleType(ImageView.ScaleType.FIT_XY);

            return image_lv;

        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            //Glide框架
            Glide.with(context).load(data).into(image_lv);

        }

    }
}
