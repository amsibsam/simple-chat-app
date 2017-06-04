
package android.rahardyan.simplechatapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("current_page")
    private Long mCurrentPage;
    @SerializedName("data")
    private List<CommentDetail> mData;
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

    public List<CommentDetail> getData() {
        return mData;
    }

    public void setData(List<CommentDetail> data) {
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

    public static class CommentDetail {

        @SerializedName("action")
        private String mAction;
        @SerializedName("attachFileId")
        private String mAttachFileId;
        @SerializedName("attachStatus")
        private String mAttachStatus;
        @SerializedName("createdBy")
        private String mCreatedBy;
        @SerializedName("createdName")
        private String mCreatedName;
        @SerializedName("creatorAvatarImageUrl")
        private String mCreatorAvatarImageUrl;
        @SerializedName("field")
        private String mField;
        @SerializedName("fileName")
        private String mFileName;
        @SerializedName("fileSize")
        private Long mFileSize;
        @SerializedName("historyId")
        private String mHistoryId;
        @SerializedName("historyType")
        private String mHistoryType;
        @SerializedName("issueId")
        private String mIssueId;
        @SerializedName("message")
        private String mMessage;
        @SerializedName("thumbnailUri")
        private String mThumbnailUri;
        @SerializedName("unitGroupType")
        private String mUnitGroupType;
        @SerializedName("unitName")
        private String mUnitName;
        @SerializedName("writtenTime")
        private String mWrittenTime;

        public String getAction() {
            return mAction;
        }

        public void setAction(String action) {
            mAction = action;
        }

        public String getAttachFileId() {
            return mAttachFileId;
        }

        public void setAttachFileId(String attachFileId) {
            mAttachFileId = attachFileId;
        }

        public String getAttachStatus() {
            return mAttachStatus;
        }

        public void setAttachStatus(String attachStatus) {
            mAttachStatus = attachStatus;
        }

        public String getCreatedBy() {
            return mCreatedBy;
        }

        public void setCreatedBy(String createdBy) {
            mCreatedBy = createdBy;
        }

        public String getCreatedName() {
            return mCreatedName;
        }

        public void setCreatedName(String createdName) {
            mCreatedName = createdName;
        }

        public String getCreatorAvatarImageUrl() {
            return mCreatorAvatarImageUrl;
        }

        public void setCreatorAvatarImageUrl(String creatorAvatarImageUrl) {
            mCreatorAvatarImageUrl = creatorAvatarImageUrl;
        }

        public String getField() {
            return mField;
        }

        public void setField(String field) {
            mField = field;
        }

        public String getFileName() {
            return mFileName;
        }

        public void setFileName(String fileName) {
            mFileName = fileName;
        }

        public Long getFileSize() {
            return mFileSize;
        }

        public void setFileSize(Long fileSize) {
            mFileSize = fileSize;
        }

        public String getHistoryId() {
            return mHistoryId;
        }

        public void setHistoryId(String historyId) {
            mHistoryId = historyId;
        }

        public String getHistoryType() {
            return mHistoryType;
        }

        public void setHistoryType(String historyType) {
            mHistoryType = historyType;
        }

        public String getIssueId() {
            return mIssueId;
        }

        public void setIssueId(String issueId) {
            mIssueId = issueId;
        }

        public String getMessage() {
            return mMessage;
        }

        public void setMessage(String message) {
            mMessage = message;
        }

        public String getThumbnailUri() {
            return mThumbnailUri;
        }

        public void setThumbnailUri(String thumbnailUri) {
            mThumbnailUri = thumbnailUri;
        }

        public String getUnitGroupType() {
            return mUnitGroupType;
        }

        public void setUnitGroupType(String unitGroupType) {
            mUnitGroupType = unitGroupType;
        }

        public String getUnitName() {
            return mUnitName;
        }

        public void setUnitName(String unitName) {
            mUnitName = unitName;
        }

        public String getWrittenTime() {
            return mWrittenTime;
        }

        public void setWrittenTime(String writtenTime) {
            mWrittenTime = writtenTime;
        }

    }

}
