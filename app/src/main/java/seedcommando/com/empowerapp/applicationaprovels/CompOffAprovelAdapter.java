package seedcommando.com.empowerapp.applicationaprovels;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import seedcommando.com.empowerapp.R;
import seedcommando.com.empowerapp.Utilities;
import seedcommando.com.empowerapp.constantclass.EmpowerApplication;
import seedcommando.com.empowerapp.pojos.CommanResponsePojo;
import seedcommando.com.empowerapp.pojos.aprovels.compoff.CompoffAprovelSetData;
import seedcommando.com.empowerapp.rest.ApiClient;
import seedcommando.com.empowerapp.rest.ApiInterface;

/**
 * Created by commando4 on 4/13/2018.
 */

public class CompOffAprovelAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<CompoffAprovelSetData> mDataSource;
    ProgressDialog pd;

    private ApiInterface apiService;
    AprovelForCompoff aprovelForCompoff1;


    public CompOffAprovelAdapter(Context context, ArrayList<CompoffAprovelSetData> items,AprovelForCompoff aprovelForCompoff) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        aprovelForCompoff1=aprovelForCompoff;
    }

    //1
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //2
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    //4
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item

        final View rowView = mInflater.inflate(R.layout.compoffaprovel, parent, false);

        // Get title element
        apiService = ApiClient.getClient().create(ApiInterface.class);

       /* final TextView leavetype =
                (TextView) rowView.findViewById(R.id.textView_leave_type);
        final TextView balance =
                (TextView) rowView.findViewById(R.id.textView_balance_text);*/
        final TextView name =
                rowView.findViewById(R.id.textView_name);
        final TextView reason =
                rowView.findViewById(R.id.textView_reason);
        TextView date =
                rowView.findViewById(R.id.textView_taken_date);
        TextView days =
                rowView.findViewById(R.id.textView_days);
        TextView leavel =
                rowView.findViewById(R.id.textView_levelno);
        TextView status =
                rowView.findViewById(R.id.textView_app_status);
        Button Aprove =
                rowView.findViewById(R.id.aprove);
        Button reject =
                rowView.findViewById(R.id.reject);

        CompoffAprovelSetData leaveAprovelSetData = ( CompoffAprovelSetData) getItem(position);
        if (leaveAprovelSetData.getMaxLevelCount().equals(leaveAprovelSetData.getLevel())) {
            Aprove.setText("Approve");
            reject.setText("Reject");


            name.setText(leaveAprovelSetData.getFname() + " " + leaveAprovelSetData.getLname());
            reason.setText(leaveAprovelSetData.getReasonDescription());
            name.setTag(leaveAprovelSetData.getCompensatoryOffAppIDMaster());
            reason.setTag(leaveAprovelSetData.getCompensatoryOffAppLevelDetailID());
            date.setText(leaveAprovelSetData.getTakenCompOffDate());
            days.setText(leaveAprovelSetData.getNoOfCompOffDays());
            leavel.setText(leaveAprovelSetData.getLevel());
            status.setText(leaveAprovelSetData.getStatus());
        } else {
            Aprove.setText("Recommend");
            reject.setText("Not Recommend");
            name.setText(leaveAprovelSetData.getFname() + " " + leaveAprovelSetData.getLname());
            reason.setText(leaveAprovelSetData.getReasonDescription());
            name.setTag(leaveAprovelSetData.getCompensatoryOffAppIDMaster());
            reason.setTag(leaveAprovelSetData.getCompensatoryOffAppLevelDetailID());
            date.setText(leaveAprovelSetData.getTakenCompOffDate());
            days.setText(leaveAprovelSetData.getNoOfCompOffDays());
            leavel.setText(leaveAprovelSetData.getLevel());
            status.setText(leaveAprovelSetData.getStatus());

        }
        Aprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(EmpowerApplication.aesAlgorithm.Decrypt(EmpowerApplication.get_session("one")).equals("1")) {
                    final Dialog dialogRemark = new Dialog(mContext);
                dialogRemark.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogRemark.setContentView(R.layout.application_aprove_reason_dialog);
                Window window = dialogRemark.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialogRemark.show();
                dialogRemark.setCancelable(true);
                dialogRemark.setCanceledOnTouchOutside(false);

                final EditText remark = dialogRemark
                        .findViewById(R.id.edit_reason);

                Button btn_submit = dialogRemark
                        .findViewById(R.id.submit1);

                Button btn_cancel = dialogRemark
                        .findViewById(R.id.cancel1);
                btn_cancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        dialogRemark.dismiss();
                    }
                });

                //dialogue code...

                btn_submit.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {
                        if (!remark.getText().toString().isEmpty()) {
                            dialogRemark.dismiss();
                            if(Utilities.isNetworkAvailable(mContext)) {
                                Toast.makeText(mContext,name.getTag().toString()+""+reason.getTag().toString(),Toast.LENGTH_LONG).show();

                                sendAppAprovals(EmpowerApplication.aesAlgorithm.Decrypt(EmpowerApplication.get_session("employeeId")),remark.getText().toString(),name.getTag().toString(),reason.getTag().toString(),"0");

                                // DescripanciesData(EmpowerApplication.aesAlgorithm.Decrypt(EmpowerApplication.get_session("employeeId")));
                            }else {
                                Toast.makeText(mContext,"No Internet Connection...",Toast.LENGTH_LONG).show();
                            }
                            Toast.makeText(mContext,  remark.getText().toString(), Toast.LENGTH_LONG).show();

                        } else {
                            EmpowerApplication.dialog("Please Enter Remark", mContext);


                        }
                    }
                });

                Toast.makeText(mContext, "Application Aproved", Toast.LENGTH_LONG).show();


                }else{
                    sendAppAprovalsforApprove(EmpowerApplication.aesAlgorithm.Decrypt(EmpowerApplication.get_session("employeeId")),name.getTag().toString(),reason.getTag().toString(),"0");
                }




            }

        });


        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialogRemark = new Dialog(mContext);
                dialogRemark.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogRemark.setContentView(R.layout.application_aprove_reason_dialog);
                Window window = dialogRemark.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialogRemark.show();
                dialogRemark.setCancelable(true);
                dialogRemark.setCanceledOnTouchOutside(false);

                final EditText remark = dialogRemark
                        .findViewById(R.id.edit_reason);

                Button btn_submit = dialogRemark
                        .findViewById(R.id.submit1);

                Button btn_cancel = dialogRemark
                        .findViewById(R.id.cancel1);
                btn_cancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        dialogRemark.dismiss();
                    }
                });

                //dialogue code...

                btn_submit.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {
                        if (!remark.getText().toString().isEmpty()) {
                            dialogRemark.dismiss();
                            if(Utilities.isNetworkAvailable(mContext)) {
                                Toast.makeText(mContext,name.getTag().toString()+""+reason.getTag().toString(),Toast.LENGTH_LONG).show();

                                sendAppAprovals(EmpowerApplication.aesAlgorithm.Decrypt(EmpowerApplication.get_session("employeeId")),remark.getText().toString(),name.getTag().toString(),reason.getTag().toString(),"0");

                                // DescripanciesData(EmpowerApplication.aesAlgorithm.Decrypt(EmpowerApplication.get_session("employeeId")));
                            }else {
                                Toast.makeText(mContext,"No Internet Connection...",Toast.LENGTH_LONG).show();
                            }
                            Toast.makeText(mContext,  remark.getText().toString(), Toast.LENGTH_LONG).show();

                        } else {
                            EmpowerApplication.dialog("Please Enter Remark", mContext);


                        }
                    }
                });
                Toast.makeText(mContext, "Application Rejected", Toast.LENGTH_LONG).show();

            }
        });
        return rowView;

    }


    public void sendAppAprovals(String id,String reason,String appmasterid,String appleveldetailid,String status) {

        pd = new ProgressDialog(mContext);
        pd.setMessage("Loading....");
        pd.setCanceledOnTouchOutside(false);
        pd.show();

        Map<String,String> aprovelFlow=new HashMap<String,String>();


        aprovelFlow.put("ByEmployeeID",id);
        aprovelFlow.put("rejectRemark",reason);
        aprovelFlow.put("CompensatoryOffAppID",appmasterid);
        aprovelFlow.put("CompensatoryOffAppLevelDetailID",appleveldetailid);
        aprovelFlow.put("statusID",status);
        Call<CommanResponsePojo> call = apiService.SendCompOffAprovel(aprovelFlow);
        call.enqueue(new Callback<CommanResponsePojo>() {
            @Override
            public void onResponse(retrofit2.Call<CommanResponsePojo> call, Response<CommanResponsePojo> response) {


                if (response.isSuccessful()) {
                    pd.dismiss();


                    if (response.body().getStatus().equals("1")) {
                        String msg=response.body().getMessage();
                        // EmpowerApplication.alertdialog(response.body().getMessage(),mContext);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                        msg=msg.replace("\\r",".").replace("\\n","");

                        alertDialogBuilder.setMessage(msg);
                        alertDialogBuilder.setPositiveButton("ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        if(Utilities.isNetworkAvailable(mContext)) {
                                            aprovelForCompoff1.CompOffAppAprovals(EmpowerApplication.aesAlgorithm.Decrypt(EmpowerApplication.get_session("employeeId")),EmpowerApplication.aesAlgorithm.Decrypt(EmpowerApplication.get_session("data")));

                                            // DescripanciesData(EmpowerApplication.aesAlgorithm.Decrypt(EmpowerApplication.get_session("employeeId")));
                                        }else {
                                            Toast.makeText(mContext,"No Internet Connection...",Toast.LENGTH_LONG).show();
                                        }
                                        //Toast.makeText(context, "You clicked yes button", Toast.LENGTH_LONG).show();

                                    }
                                });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();


                    }else {
                        EmpowerApplication.alertdialog(response.body().getMessage(), mContext);
                    }
                }else {
                    switch (response.code()) {
                        case 404:
                            //Toast.makeText(ErrorHandlingActivity.this, "not found", Toast.LENGTH_SHORT).show();
                            EmpowerApplication.alertdialog("File or directory not found", mContext);
                            break;
                        case 500:
                            EmpowerApplication.alertdialog("server broken", mContext);

                            //Toast.makeText(ErrorHandlingActivity.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            EmpowerApplication.alertdialog("unknown error", mContext);

                            //Toast.makeText(ErrorHandlingActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }

                }


            }

            @Override
            public void onFailure(retrofit2.Call<CommanResponsePojo> call, Throwable t) {
                // Log error here since request failed
                pd.dismiss();
                Log.e("TAG", t.toString());
                EmpowerApplication.alertdialog(t.getMessage(), mContext);


            }
        });
    }

    public void sendAppAprovalsforApprove(String id,String appmasterid,String appleveldetailid,String status) {

        pd = new ProgressDialog(mContext);
        pd.setMessage("Loading....");
        pd.setCanceledOnTouchOutside(false);
        pd.show();

        Map<String,String> aprovelFlow=new HashMap<String,String>();


        aprovelFlow.put("ByEmployeeID",id);
        aprovelFlow.put("rejectRemark","");
        aprovelFlow.put("CompensatoryOffAppID",appmasterid);
        aprovelFlow.put("CompensatoryOffAppLevelDetailID",appleveldetailid);
        aprovelFlow.put("statusID",status);
        Call<CommanResponsePojo> call = apiService.SendCompOffAprovel(aprovelFlow);
        call.enqueue(new Callback<CommanResponsePojo>() {
            @Override
            public void onResponse(retrofit2.Call<CommanResponsePojo> call, Response<CommanResponsePojo> response) {


                if (response.isSuccessful()) {
                    pd.dismiss();


                    if (response.body().getStatus().equals("1")) {
                        String msg=response.body().getMessage();
                        // EmpowerApplication.alertdialog(response.body().getMessage(),mContext);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                        msg=msg.replace("\\r",".").replace("\\n","");

                        alertDialogBuilder.setMessage(msg);
                        alertDialogBuilder.setPositiveButton("ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        if(Utilities.isNetworkAvailable(mContext)) {
                                            aprovelForCompoff1.CompOffAppAprovals(EmpowerApplication.aesAlgorithm.Decrypt(EmpowerApplication.get_session("employeeId")),EmpowerApplication.aesAlgorithm.Decrypt(EmpowerApplication.get_session("data")));

                                            // DescripanciesData(EmpowerApplication.aesAlgorithm.Decrypt(EmpowerApplication.get_session("employeeId")));
                                        }else {
                                            Toast.makeText(mContext,"No Internet Connection...",Toast.LENGTH_LONG).show();
                                        }
                                        //Toast.makeText(context, "You clicked yes button", Toast.LENGTH_LONG).show();

                                    }
                                });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();


                    }else {
                        EmpowerApplication.alertdialog(response.body().getMessage(), mContext);
                    }
                }else {
                    switch (response.code()) {
                        case 404:
                            //Toast.makeText(ErrorHandlingActivity.this, "not found", Toast.LENGTH_SHORT).show();
                            EmpowerApplication.alertdialog("File or directory not found", mContext);
                            break;
                        case 500:
                            EmpowerApplication.alertdialog("server broken", mContext);

                            //Toast.makeText(ErrorHandlingActivity.this, "server broken", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            EmpowerApplication.alertdialog("unknown error", mContext);

                            //Toast.makeText(ErrorHandlingActivity.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }

                }


            }

            @Override
            public void onFailure(retrofit2.Call<CommanResponsePojo> call, Throwable t) {
                // Log error here since request failed
                pd.dismiss();
                Log.e("TAG", t.toString());
                EmpowerApplication.alertdialog(t.getMessage(), mContext);


            }
        });
    }

}
