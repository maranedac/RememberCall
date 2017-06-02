package cl.mac.remembercall;


import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cl.mac.remembercall.adapters.RemindersAdapter;
import cl.mac.remembercall.adapters.RemindersClick;
import cl.mac.remembercall.models.Reminder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RemindersListFragment extends Fragment implements RemindersClick{

    public static final String REMINDER_ID = "cl.mac.remembercall.KEY.REMINDER_ID";
    private RemindersAdapter adapter;

    public RemindersListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminderslist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);

        adapter = new RemindersAdapter(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.updateReminder();

    }

    public void addReminder(Reminder reminder){
        Toast.makeText(getContext(), "Recordatorio Añadido", Toast.LENGTH_SHORT).show();
        adapter.addReminder(reminder);
    }

    @Override
    public void clicked(long id) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(REMINDER_ID, id);
        startActivity(intent);
    }


}
