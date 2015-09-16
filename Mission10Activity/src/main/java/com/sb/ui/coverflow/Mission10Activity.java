package com.sb.ui.coverflow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Book list application using coverflow widget.
 *
 * @Sep. 12, 2015
 *
 */
public class Mission10Activity extends ActionBarActivity {

    /**
     * Spacing
     */
    public static int spacing = -4;//-45

    // Book database
    private Map<String, List<String>> mBookData;
    private List<String> mBookItem;

    private ImageView mBookImageView;
    private TextView mBookNameTextView;
    private TextView mBookCoTextView;
    private TextView mBookPrintTextView;
    private TextView mBookAuthorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link member fields to layout id
        mBookImageView = (ImageView)findViewById(R.id.book_image_view);
        mBookNameTextView= (TextView)findViewById(R.id.bookName_text_view);
        mBookCoTextView= (TextView)findViewById(R.id.bookCo_text_view);
        mBookPrintTextView= (TextView)findViewById(R.id.bookPrint_text_book);
        mBookAuthorTextView= (TextView)findViewById(R.id.bookAuthor_text_view);

        // Set adapter to coverflow
        CoverFlow coverFlow = (CoverFlow) findViewById(R.id.coverflow);
        ImageAdapter coverImageAdapter = new ImageAdapter(this);
        coverFlow.setAdapter(coverImageAdapter);

        // Set attribute to coverflow
        coverFlow.setSpacing(spacing);
        coverFlow.setSelection(2, true);// set center among 5 images
        coverFlow.setAnimationDuration(3000);


        coverFlow.setCallbackDuringFling(false);

        coverFlow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SB>>>", "" + position+ "/// id"+ id);
                //if(coverFlow.getSelectedItemPosition()!= 2)
                //    return;
                int imageId= 0;
                String key= "";
                switch (position) {
                    case 0:
                        imageId= R.drawable.dropdeadgorgeous;
                        key= "1";
                        break;
                    case 1:
                        imageId= R.drawable.gonewiththewind;
                        key= "2";
                        break;
                    case 2:
                        imageId= R.drawable.hardtime;
                        key= "3";
                        break;
                    case 3:
                        imageId= R.drawable.therainmaker;
                        key= "4";
                        break;
                    case 4:
                        imageId= R.drawable.theranch;
                        key= "5";
                        break;
                }
                mBookImageView.setImageResource(imageId);

                mBookItem= mBookData.get(key);

                mBookNameTextView.setText("Name: "+ mBookItem.get(0));
                mBookCoTextView.setText("Publisher: "+ mBookItem.get(1));
                mBookPrintTextView.setText("Printed: "+ mBookItem.get(2));
                mBookAuthorTextView.setText("Author: "+ mBookItem.get(3));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Set book data
        mBookData= new HashMap<>();
        
        mBookItem= new ArrayList<>();
        mBookItem.add("Drop Dead Gorgeous");
        mBookItem.add("Onyx(Pengun Putnam Inc.");
        mBookItem.add("August, 1998");
        mBookItem.add("Heather Graham Pozzessere");
        mBookData.put("1", mBookItem);

        mBookItem= new ArrayList<>();
        mBookItem.add("Gone With The Wind");
        mBookItem.add("Warner mBooks");
        mBookItem.add("1991");
        mBookItem.add("Margaret Mitchell's");
        mBookData.put("2", mBookItem);

        mBookItem= new ArrayList<>();
        mBookItem.add("Hard Time");
        mBookItem.add("Dell Publishing");
        mBookItem.add("1999");
        mBookItem.add("Sara Paretsky");
        mBookData.put("3", mBookItem);

        mBookItem= new ArrayList<>();
        mBookItem.add("The Rain Maker");
        mBookItem.add("Dell Publishing");
        mBookItem.add("1995");
        mBookItem.add("John Grisham");
        mBookData.put("4", mBookItem);

        mBookItem= new ArrayList<>();
        mBookItem.add("The Ranch");
        mBookItem.add("Dell Publishing");
        mBookItem.add("1997");
        mBookItem.add("Danielle Steel");
        mBookData.put("5", mBookItem);
    }

    /**
     * Adapter class
     */
    public class ImageAdapter extends BaseAdapter {

        int itemBackground;
        private Context mContext;
        private FileInputStream outstream;

        private Integer[] mImageIds = { R.drawable.dropdeadgorgeous, R.drawable.gonewiththewind,
                R.drawable.hardtime, R.drawable.therainmaker, R.drawable.theranch};

        private ImageView[] mImages;

        public ImageAdapter(Context c) {
            mContext = c;
            mImages = new ImageView[mImageIds.length];
        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            ImageView img = new ImageView(mContext);
            img.setImageResource(mImageIds[position]);
            img.setLayoutParams(new CoverFlow.LayoutParams(200, 300));//300,140
            img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);//CENTER_INSIDE

            BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
            drawable.setAntiAlias(true);


            return img;
        }

        public float getScale(boolean focused, int offset) {
            return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
