package eu.alfo.rtu_pit;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class NewQuestionFragment extends Fragment{
    private Button buttonMake;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_question, container, false);

        buttonMake.findViewById(R.id.buttonCreateNewQuestion);

        buttonMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                showAccount();
//                Intent intent = new Intent(getActivity(), StartingScreenActivity.class);
//                getActivity().startActivity(intent);

            }
        });

//        return super.onCreateView(inflater, container, savedInstanceState);
        return v;
    }
//    public void showAccount(){
//        AlertDialog.Builder signeNewQuestion = new AlertDialog.Builder(getActivity());
//
//        signeNewQuestion.setTitle((CharSequence) signeNewQuestion.setTitle(R.string.app_name));
//
//        LayoutInflater inflater = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            inflater = this.getLayoutInflater();
//        }
//        View account_fill = inflater.inflate(R.layout.fragment_new_question, null);
//
//        signeNewQuestion.setView(account_fill);
//        signeNewQuestion.show();
//    }

}
