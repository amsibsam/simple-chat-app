
package android.rahardyan.simplechatapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;

public class Comment {

    @SerializedName("current_page")
    private Long mCurrentPage;
    @SerializedName("data")
    private RealmList<CommentDetail> mData;
    @SerializedName("page_size")
    private Long mPageSize;
    @SerializedName("start_number")
    private Long mStartNumber;
    @SerializedName("total_records")
    private Long mTotalRecords;

    public Long getCurrentPage() {
        return mCurrentPage;
    }

    public void setCurrentPage(Long currentPage) {
        mCurrentPage = currentPage;
    }

    public RealmList<CommentDetail> getData() {
        return mData;
    }

    public void setData(RealmList<CommentDetail> data) {
        mData = data;
    }

    public Long getPageSize() {
        return mPageSize;
    }

    public void setPageSize(Long pageSize) {
        mPageSize = pageSize;
    }

    public Long getStartNumber() {
        return mStartNumber;
    }

    public void setStartNumber(Long startNumber) {
        mStartNumber = startNumber;
    }

    public Long getTotalRecords() {
        return mTotalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        mTotalRecords = totalRecords;
    }

}
