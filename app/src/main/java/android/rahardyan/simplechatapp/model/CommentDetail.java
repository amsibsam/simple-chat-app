package android.rahardyan.simplechatapp.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rahardyan on 04/06/17.
 */

public class CommentDetail extends RealmObject {

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
    @PrimaryKey
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
