package android.rahardyan.simplechatapp.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rahardyan on 04/06/17.
 */

public class IssueList extends RealmObject{

    @PrimaryKey
    @SerializedName("id")
    private String mId;
    @SerializedName("inprogress")
    private Long mInprogress;
    @SerializedName("priority")
    private String mPriority;
    @SerializedName("title")
    private String mTitle;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Long getInprogress() {
        return mInprogress;
    }

    public void setInprogress(Long inprogress) {
        mInprogress = inprogress;
    }

    public String getPriority() {
        return mPriority;
    }

    public void setPriority(String priority) {
        mPriority = priority;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}