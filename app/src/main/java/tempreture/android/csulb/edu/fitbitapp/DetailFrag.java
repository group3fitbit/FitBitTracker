package tempreture.android.csulb.edu.fitbitapp;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import static tempreture.android.csulb.edu.fitbitapp.UserProgress.Users;


public class DetailFrag extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
   // public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        //final View view = inflater.inflate(R.layout.user_progress_list, container,false);

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_progress_list, container, false);

        ListView listView = (ListView) view.findViewById(R.id.progress_list);
        String[] names = new String[5];
        int[] images = new int[5];
       // String[] names = {"one","two","three","four","five"};
       // int[] images = {R.drawable.adrian_circle,R.drawable.ming_circle,R.drawable.noam_circle,R.drawable.adrian_circle,R.drawable.adrian_circle};
        for(int i=0;i<Users.size();i++){
            names[i] = Users.get(i).getName();
            images[i] = Users.get(i).getBoxImage();
        }
        //Instantiating the Customized Adapter
        ListViewAdapter LVAdapter = new ListViewAdapter(getActivity(), names, images);
        listView.setAdapter(LVAdapter);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}

//Class implementation for the Customized Adapter
class ListViewAdapter extends ArrayAdapter<String> {
    Context context;
    int images[];
    String tls[];

/*
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }*/

    ListViewAdapter(Context c, String[] titles, int img[]) {
        super(c, R.layout.list_view, R.id.textView2, titles);
        this.context = c;
        this.images = img;
        this.tls = titles;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.list_view, parent, false);

        //Getting the reference to the TextView and the ImageView
        ImageView iv = (ImageView) row.findViewById(R.id.imageView);
        TextView tv = (TextView) row.findViewById(R.id.textView2);

        //Populating the ImageView and the TextView with appropriate data
        ImageLoader imageloader = new ImageLoader();
        imageloader.loadImage(row.getResources(),iv,images[position]);
        tv.setText(tls[position]);

        return row;
    }
}