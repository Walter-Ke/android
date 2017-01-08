
package tw.com.walter.bootscreen;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MediaMountInitiator extends BroadcastReceiver
{

    public MediaMountInitiator()
    {
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        /* TODO: add Toast when got SD Card*/
        Toast.makeText(context,"SD card is mounted", Toast.LENGTH_LONG).show();
    }
}
