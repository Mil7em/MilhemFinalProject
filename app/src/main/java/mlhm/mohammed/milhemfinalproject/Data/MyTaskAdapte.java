package mlhm.mohammed.milhemfinalproject.Data;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;

import mlhm.mohammed.milhemfinalproject.R;

public class MyTaskAdapte {
    //Adapter

    /**
     *
     *  target: arrange data source using listview
     */
//1.
    public class MyTaskAdapte extends ArrayAdapter<MyTask>
    {
        /**
         *constractor
         * @param context the activity of (app) that this adapter belong to
         * @param resource XML  design of the item
         */
        //fix error
        public MyTaskAdapte(@NonNull ll   Context context, int resource) {
            super(context, resource);
        }

        /**
         * bulding single item view
         * @param position index item in list view
         * @param convertView item view
         * @param parent listview
         * @return
         */

        @NonNull
        @Override
        //3. overriding getview
        public View getView(int position, @Nullable  View convertView, @NonNull ViewGroup parent) {
            //3.1
            View v= LayoutInflater.from(getContext()).inflate(R.layout.item_task_view,parent,false);
            //3.2 find view by ID
            TextView tvTitle=v.findViewById(R.id.itmTvTittle);
            TextView tvImportant=v.findViewById(R.id.itmTvImportant);
            TextView tvNecessary=v.findViewById(R.id.itemTvNecessery);
            TextView tvSubject=v.findViewById(R.id.itmTvSubject);

            ImageButton btnDelete=v.findViewById(R.id.itmbtnDelete);
            ImageButton btnCall=v.findViewById(R.id.itmbtnEdit);
            ImageButton btnEdit=v.findViewById(R.id.itmbtnCall);

            //3.3 get the soutable task object
            final MyTask task=getItem(position);
            //3.4 connect the dot to the view (view the data using item views)
            tvTitle.setText(task.getTitle());
            tvSubject.setText(task.getSub());
            switch(task.getImportant()){
                case 5: tvNecessary.setBackgroundColor(Color.RED);break;
                case 4: tvNecessary.setBackgroundColor(Color.YELLOW);break;
                case 3: tvNecessary.setBackgroundColor(Color.CYAN);break;
                case 2: tvNecessary.setBackgroundColor(Color.MAGENTA);break;
                case 1: tvNecessary.setBackgroundColor(Color.RED);break;
            }
            switch(task.getNecessary()){
                case 5: tvImportant.setBackgroundColor(Color.RED);break;
                case 4: tvImportant.setBackgroundColor(Color.YELLOW);break;
                case 3: tvImportant.setBackgroundColor(Color.CYAN);break;
                case 2: tvImportant.setBackgroundColor(Color.MAGENTA);break;
                case 1: tvImportant.setBackgroundColor(Color.RED);break;
            }
            //3.5 events
            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),task.getTitle(),Toast.LENGTH_LONG).show();
                }
            });
            return v;
        }
    }
}
