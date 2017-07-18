package recycleview;

import android.view.View;

/**
 * Created by hardik on 13/12/16.
 */

public interface ClickListener {

    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
