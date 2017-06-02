package cl.mac.remembercall.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cl.mac.remembercall.R;
import cl.mac.remembercall.RemindersListFragment;
import cl.mac.remembercall.data.Queries;
import cl.mac.remembercall.models.Reminder;

import static android.R.attr.name;

/**
 * Created by Michael on 24-05-2017.
 */

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.ViewHolder>{

    private List<Reminder> reminders = new ArrayList<>();
    private RemindersClick listener;

    public RemindersAdapter(RemindersClick listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_call, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Reminder reminder = reminders.get(position);

        TextView textView = holder.textView;
        textView.setText(reminder.getName());


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reminder auxReminder = reminders.get(holder.getAdapterPosition());
                listener.clicked(auxReminder.getId());

            }
        });
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }


    public void addReminder(Reminder reminder){
        reminders.add(0, reminder);
        notifyItemInserted(0);
    }

    public void updateReminder(){
        reminders.clear();
        reminders.addAll(new Queries().reminders());
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.nameTv);
        }
    }

}
