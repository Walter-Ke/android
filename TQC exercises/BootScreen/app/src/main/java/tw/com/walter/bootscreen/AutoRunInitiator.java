package tw.com.walter.bootscreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AutoRunInitiator extends BroadcastReceiver
{
    public AutoRunInitiator()
    {
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
       /*TODO: add Toast and start GAD01 Activity */

        Toast.makeText(context,"BOOTING",Toast.LENGTH_LONG).show();

        Intent intent1 = new Intent(context, MainActivity.class);
        intent1.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
