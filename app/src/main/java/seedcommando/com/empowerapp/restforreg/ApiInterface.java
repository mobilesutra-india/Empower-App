package seedcommando.com.empowerapp.restforreg;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import seedcommando.com.empowerapp.pojos.CommanResponsePojo;
import seedcommando.com.empowerapp.pojos.LicensePOJO;
import seedcommando.com.empowerapp.pojos.LoginPOJO;
import seedcommando.com.empowerapp.pojos.ManagerPoJo.ClaenderPoJo;
import seedcommando.com.empowerapp.pojos.ManagerSummaryPoJo.SummaryPoJo;
import seedcommando.com.empowerapp.pojos.RegistrationDetails;
import seedcommando.com.empowerapp.pojos.WorkFromHome.FinalResponsePoJo;
import seedcommando.com.empowerapp.pojos.WorkFromHome.WorkFromHomePoJo;
import seedcommando.com.empowerapp.pojos.WorkFromHome.getreason.ReasonPoJo;
import seedcommando.com.empowerapp.pojos.WorkFromHome.status.WFHStatus;
import seedcommando.com.empowerapp.pojos.aprovels.compoff.CompoffAprovel;
import seedcommando.com.empowerapp.pojos.aprovels.leave.LeaveAprove;
import seedcommando.com.empowerapp.pojos.aprovels.od.ODAprovel;
import seedcommando.com.empowerapp.pojos.aprovels.regularization.RegAprove;
import seedcommando.com.empowerapp.pojos.aprovels.regularization.daystatus.DayStatusReg;
import seedcommando.com.empowerapp.pojos.aprovels.wfh.WFHAprovel;
import seedcommando.com.empowerapp.pojos.attendancedetails.AttendanceDetails;
import seedcommando.com.empowerapp.pojos.compoff.compoffagainstdetails.CompOffDetails;
import seedcommando.com.empowerapp.pojos.compoff.status.CompOffStatus;
import seedcommando.com.empowerapp.pojos.config.EmployeeConfig;
import seedcommando.com.empowerapp.pojos.discripanciespojo.Descripancy;
import seedcommando.com.empowerapp.pojos.leavepojo.Leave;
import seedcommando.com.empowerapp.pojos.leavepojo.leavetypecode.LeaveCode;
import seedcommando.com.empowerapp.pojos.leavepojo.statuspojo.Status;
import seedcommando.com.empowerapp.pojos.regularizationpoJo.Regularization;
import seedcommando.com.empowerapp.pojos.regularizationpoJo.getreasonpojo.Reason;
import seedcommando.com.empowerapp.pojos.regularizationpoJo.realtimedata.RealTime;
import seedcommando.com.empowerapp.pojos.regularizationpoJo.shift.ShiftType;
import seedcommando.com.empowerapp.pojos.regularizationpoJo.statuspojo.RegStatus;

/**
 * Created by commando4 on 1/2/2018.
 */

public interface ApiInterface {
    //Enterprise_MobileAppService //Empower_License//Enterprise_MobileAppService
    //cloude

    @FormUrlEncoded
    @POST("/Enterprise_MobileAppService/api/User/Registration")
    Call<RegistrationDetails> SendRegistrationData(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("/Enterprise_MobileAppService/api/User/VerifyLicenseNumber")
    Call<LicensePOJO> SendLicenseRegData(@FieldMap Map<String, String> fields);


}

