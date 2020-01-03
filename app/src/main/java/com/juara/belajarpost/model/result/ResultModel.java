
package com.juara.belajarpost.model.result;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultModel implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<ResultModel> CREATOR = new Creator<ResultModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ResultModel createFromParcel(Parcel in) {
            return new ResultModel(in);
        }

        public ResultModel[] newArray(int size) {
            return (new ResultModel[size]);
        }

    }
    ;
    private final static long serialVersionUID = 5868036690133552731L;

    protected ResultModel(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResultModel() {
    }

    /**
     * 
     * @param message
     * @param status
     */
    public ResultModel(Boolean status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
