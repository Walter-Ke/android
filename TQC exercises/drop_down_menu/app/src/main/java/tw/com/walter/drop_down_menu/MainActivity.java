package tw.com.walter.drop_down_menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String NAME = "NAME";
    private ExpandableListAdapter mAdapter;
    private String[] groups = { "Menu 1", "Menu 2", "Menu 3", "Menu 4"};
    private String[][] children_array = {
            { "Sub_menu 1-1", "Sub_menu 1-2", "Sub_menu 1-3"},
            { "Sub_menu 2-1", "Sub_menu 2-2"},
            { "Sub_menu 3-1", "Sub_menu 3-2", "Sub_menu 3-3", "Sub_menu 3-4"},
            { "Sub_menu 4-1", "Sub_menu 4-2", "Sub_menu 4-3"}
    };
    private ExpandableListView listView;
    private List<Map<String, String>> groupData;
    private List<List<Map<String, String>>> childData;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ExpandableListView) findViewById(R.id.expandlist);
        setData();
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()
        {
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition,  long l)
            {
                /***
                 *
                 * TODO- Set the selected menu item
                 *
                 */
                for(int i=0;i<groups.length;i++){

                    if(i==groupPosition){

                        if(listView.isGroupExpanded(i)){

                          listView.collapseGroup(i);

                        }else{

                          listView.expandGroup(i);
                        }
                    }
                    else{
                        listView.collapseGroup(i);
                    }
                }
                return true;
            }
        });
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l)
            {
                /***
                 *
                 * TODO- Set the selected sub-menu item
                 *
                 */
                HashMap m= (HashMap) mAdapter.getChild(groupPosition,childPosition);
                String albumid = (String)m.get(NAME);
                Toast.makeText(getApplicationContext(),albumid,Toast.LENGTH_LONG).show();

                return false;
            }
        });

        listView.setAdapter(mAdapter);
    }

    private void setData()
    {
        groupData = new ArrayList<Map<String, String>>();
        childData = new ArrayList<List<Map<String, String>>>();

        //main menu
        for(int i=0;i<groups.length;i++){

            //
            Map<String,String> curGroupMap = new HashMap<String,String>();
            groupData.add(curGroupMap);

            //
            curGroupMap.put(NAME,groups[i]);



            List<Map<String,String>>children = new ArrayList<Map<String,String>>();

            for(int j=0;j<children_array[i].length;j++){
                Map<String,String>curChildMap=new HashMap<String,String>();

                children.add(curChildMap);
                curChildMap.put(NAME, children_array[i][j]);
            }
            childData.add(children);
        }
        //


        /****
         *
         *  TODO- set the initial value
         *
         */



        mAdapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                R.layout.mylistitem,
                new String[]{NAME},
                new int[] {android.R.id.text1},
                childData,
                R.layout.submenu,
                new String[]{NAME,},
                new int[]{android.R.id.text1}
        );

    }
}
