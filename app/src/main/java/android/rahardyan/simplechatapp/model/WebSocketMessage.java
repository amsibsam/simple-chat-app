
package android.rahardyan.simplechatapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class WebSocketMessage {

    @SerializedName("data")
    private Data mData;
    @SerializedName("type")
    private String mType;

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
        mData = data;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public class Attach {

        @SerializedName("createdBy")
        private String mCreatedBy;
        @SerializedName("createdName")
        private String mCreatedName;
        @SerializedName("createdTime")
        private String mCreatedTime;
        @SerializedName("fileID")
        private String mFileID;
        @SerializedName("fileName")
        private String mFileName;
        @SerializedName("filePath")
        private String mFilePath;
        @SerializedName("fileSize")
        private Long mFileSize;
        @SerializedName("histories")
        private List<History> mHistories;
        @SerializedName("issueId")
        private String mIssueId;
        @SerializedName("previewUri")
        private String mPreviewUri;
        @SerializedName("thumbnailUri")
        private String mThumbnailUri;
        @SerializedName("timeZone")
        private String mTimeZone;

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

        public String getCreatedTime() {
            return mCreatedTime;
        }

        public void setCreatedTime(String createdTime) {
            mCreatedTime = createdTime;
        }

        public String getFileID() {
            return mFileID;
        }

        public void setFileID(String fileID) {
            mFileID = fileID;
        }

        public String getFileName() {
            return mFileName;
        }

        public void setFileName(String fileName) {
            mFileName = fileName;
        }

        public String getFilePath() {
            return mFilePath;
        }

        public void setFilePath(String filePath) {
            mFilePath = filePath;
        }

        public Long getFileSize() {
            return mFileSize;
        }

        public void setFileSize(Long fileSize) {
            mFileSize = fileSize;
        }

        public List<History> getHistories() {
            return mHistories;
        }

        public void setHistories(List<History> histories) {
            mHistories = histories;
        }

        public String getIssueId() {
            return mIssueId;
        }

        public void setIssueId(String issueId) {
            mIssueId = issueId;
        }

        public String getPreviewUri() {
            return mPreviewUri;
        }

        public void setPreviewUri(String previewUri) {
            mPreviewUri = previewUri;
        }

        public String getThumbnailUri() {
            return mThumbnailUri;
        }

        public void setThumbnailUri(String thumbnailUri) {
            mThumbnailUri = thumbnailUri;
        }

        public String getTimeZone() {
            return mTimeZone;
        }

        public void setTimeZone(String timeZone) {
            mTimeZone = timeZone;
        }

    }

    public class Changes {

        @SerializedName("histories")
        private Histories mHistories;

        public Histories getHistories() {
            return mHistories;
        }

        public void setHistories(Histories histories) {
            mHistories = histories;
        }

    }

    public class Context {

        @SerializedName("canAddWatcher")
        private Boolean mCanAddWatcher;
        @SerializedName("canDelete")
        private Boolean mCanDelete;
        @SerializedName("canDeleteWatcher")
        private Boolean mCanDeleteWatcher;
        @SerializedName("canEdit")
        private Boolean mCanEdit;
        @SerializedName("canEditColor")
        private Boolean mCanEditColor;
        @SerializedName("canEditDescription")
        private Boolean mCanEditDescription;
        @SerializedName("canEditDuration")
        private Boolean mCanEditDuration;
        @SerializedName("canEditProgress")
        private Boolean mCanEditProgress;
        @SerializedName("canEditSummary")
        private Boolean mCanEditSummary;
        @SerializedName("canView")
        private Boolean mCanView;
        @SerializedName("isCreator")
        private Boolean mIsCreator;
        @SerializedName("isRead")
        private Boolean mIsRead;
        @SerializedName("isWatcher")
        private Boolean mIsWatcher;

        public Boolean getCanAddWatcher() {
            return mCanAddWatcher;
        }

        public void setCanAddWatcher(Boolean canAddWatcher) {
            mCanAddWatcher = canAddWatcher;
        }

        public Boolean getCanDelete() {
            return mCanDelete;
        }

        public void setCanDelete(Boolean canDelete) {
            mCanDelete = canDelete;
        }

        public Boolean getCanDeleteWatcher() {
            return mCanDeleteWatcher;
        }

        public void setCanDeleteWatcher(Boolean canDeleteWatcher) {
            mCanDeleteWatcher = canDeleteWatcher;
        }

        public Boolean getCanEdit() {
            return mCanEdit;
        }

        public void setCanEdit(Boolean canEdit) {
            mCanEdit = canEdit;
        }

        public Boolean getCanEditColor() {
            return mCanEditColor;
        }

        public void setCanEditColor(Boolean canEditColor) {
            mCanEditColor = canEditColor;
        }

        public Boolean getCanEditDescription() {
            return mCanEditDescription;
        }

        public void setCanEditDescription(Boolean canEditDescription) {
            mCanEditDescription = canEditDescription;
        }

        public Boolean getCanEditDuration() {
            return mCanEditDuration;
        }

        public void setCanEditDuration(Boolean canEditDuration) {
            mCanEditDuration = canEditDuration;
        }

        public Boolean getCanEditProgress() {
            return mCanEditProgress;
        }

        public void setCanEditProgress(Boolean canEditProgress) {
            mCanEditProgress = canEditProgress;
        }

        public Boolean getCanEditSummary() {
            return mCanEditSummary;
        }

        public void setCanEditSummary(Boolean canEditSummary) {
            mCanEditSummary = canEditSummary;
        }

        public Boolean getCanView() {
            return mCanView;
        }

        public void setCanView(Boolean canView) {
            mCanView = canView;
        }

        public Boolean getIsCreator() {
            return mIsCreator;
        }

        public void setIsCreator(Boolean isCreator) {
            mIsCreator = isCreator;
        }

        public Boolean getIsRead() {
            return mIsRead;
        }

        public void setIsRead(Boolean isRead) {
            mIsRead = isRead;
        }

        public Boolean getIsWatcher() {
            return mIsWatcher;
        }

        public void setIsWatcher(Boolean isWatcher) {
            mIsWatcher = isWatcher;
        }

    }

    public class Data {

        @SerializedName("changes")
        private Changes mChanges;
        @SerializedName("source")
        private List<Source> mSource;
        @SerializedName("subType")
        private String mSubType;
        @SerializedName("type")
        private String mType;

        public Changes getChanges() {
            return mChanges;
        }

        public void setChanges(Changes changes) {
            mChanges = changes;
        }

        public List<Source> getSource() {
            return mSource;
        }

        public void setSource(List<Source> source) {
            mSource = source;
        }

        public String getSubType() {
            return mSubType;
        }

        public void setSubType(String subType) {
            mSubType = subType;
        }

        public String getType() {
            return mType;
        }

        public void setType(String type) {
            mType = type;
        }

    }

    public class Histories {

        @SerializedName("newValue")
        private List<NewValue> mNewValue;
        @SerializedName("oldValue")
        private Object mOldValue;

        public List<NewValue> getNewValue() {
            return mNewValue;
        }

        public void setNewValue(List<NewValue> newValue) {
            mNewValue = newValue;
        }

        public Object getOldValue() {
            return mOldValue;
        }

        public void setOldValue(Object oldValue) {
            mOldValue = oldValue;
        }

    }

    public class History {

        @SerializedName("action")
        private String mAction;
        @SerializedName("attachFileID")
        private String mAttachFileID;
        @SerializedName("attachFileSize")
        private Long mAttachFileSize;
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
        @SerializedName("historyId")
        private String mHistoryId;
        @SerializedName("historyType")
        private String mHistoryType;
        @SerializedName("issueId")
        private String mIssueId;
        @SerializedName("message")
        private String mMessage;
        @SerializedName("newValue")
        private String mNewValue;
        @SerializedName("oldValue")
        private String mOldValue;
        @SerializedName("rowId")
        private String mRowId;
        @SerializedName("thumbnailUri")
        private String mThumbnailUri;
        @SerializedName("timeZone")
        private String mTimeZone;
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

        public String getAttachFileID() {
            return mAttachFileID;
        }

        public void setAttachFileID(String attachFileID) {
            mAttachFileID = attachFileID;
        }

        public Long getAttachFileSize() {
            return mAttachFileSize;
        }

        public void setAttachFileSize(Long attachFileSize) {
            mAttachFileSize = attachFileSize;
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

        public String getNewValue() {
            return mNewValue;
        }

        public void setNewValue(String newValue) {
            mNewValue = newValue;
        }

        public String getOldValue() {
            return mOldValue;
        }

        public void setOldValue(String oldValue) {
            mOldValue = oldValue;
        }

        public String getRowId() {
            return mRowId;
        }

        public void setRowId(String rowId) {
            mRowId = rowId;
        }

        public String getThumbnailUri() {
            return mThumbnailUri;
        }

        public void setThumbnailUri(String thumbnailUri) {
            mThumbnailUri = thumbnailUri;
        }

        public String getTimeZone() {
            return mTimeZone;
        }

        public void setTimeZone(String timeZone) {
            mTimeZone = timeZone;
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

    public class NewValue {

        @SerializedName("action")
        private String mAction;
        @SerializedName("attachFileID")
        private String mAttachFileID;
        @SerializedName("attachFileSize")
        private Long mAttachFileSize;
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
        @SerializedName("historyId")
        private String mHistoryId;
        @SerializedName("historyType")
        private String mHistoryType;
        @SerializedName("issueId")
        private String mIssueId;
        @SerializedName("message")
        private String mMessage;
        @SerializedName("newValue")
        private String mNewValue;
        @SerializedName("oldValue")
        private String mOldValue;
        @SerializedName("rowId")
        private String mRowId;
        @SerializedName("thumbnailUri")
        private String mThumbnailUri;
        @SerializedName("timeZone")
        private String mTimeZone;
        @SerializedName("unitGroupType")
        private String mUnitGroupType;
        @SerializedName("unitName")
        private String mUnitName;
        @SerializedName("uuid")
        private String mUuid;
        @SerializedName("writtenTime")
        private String mWrittenTime;

        public String getAction() {
            return mAction;
        }

        public void setAction(String action) {
            mAction = action;
        }

        public String getAttachFileID() {
            return mAttachFileID;
        }

        public void setAttachFileID(String attachFileID) {
            mAttachFileID = attachFileID;
        }

        public Long getAttachFileSize() {
            return mAttachFileSize;
        }

        public void setAttachFileSize(Long attachFileSize) {
            mAttachFileSize = attachFileSize;
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

        public String getNewValue() {
            return mNewValue;
        }

        public void setNewValue(String newValue) {
            mNewValue = newValue;
        }

        public String getOldValue() {
            return mOldValue;
        }

        public void setOldValue(String oldValue) {
            mOldValue = oldValue;
        }

        public String getRowId() {
            return mRowId;
        }

        public void setRowId(String rowId) {
            mRowId = rowId;
        }

        public String getThumbnailUri() {
            return mThumbnailUri;
        }

        public void setThumbnailUri(String thumbnailUri) {
            mThumbnailUri = thumbnailUri;
        }

        public String getTimeZone() {
            return mTimeZone;
        }

        public void setTimeZone(String timeZone) {
            mTimeZone = timeZone;
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

        public String getUuid() {
            return mUuid;
        }

        public void setUuid(String uuid) {
            mUuid = uuid;
        }

        public String getWrittenTime() {
            return mWrittenTime;
        }

        public void setWrittenTime(String writtenTime) {
            mWrittenTime = writtenTime;
        }

    }

    public class Source {

        @SerializedName("attaches")
        private List<Attach> mAttaches;
        @SerializedName("attachsCount")
        private Long mAttachsCount;
        @SerializedName("categoryId")
        private String mCategoryId;
        @SerializedName("categoryName")
        private String mCategoryName;
        @SerializedName("commentUID")
        private String mCommentUID;
        @SerializedName("context")
        private Context mContext;
        @SerializedName("createdBy")
        private String mCreatedBy;
        @SerializedName("createdName")
        private String mCreatedName;
        @SerializedName("createdTime")
        private String mCreatedTime;
        @SerializedName("creatorAvatarImageUrl")
        private String mCreatorAvatarImageUrl;
        @SerializedName("description")
        private String mDescription;
        @SerializedName("endTime")
        private String mEndTime;
        @SerializedName("histories")
        private List<History> mHistories;
        @SerializedName("historiesCount")
        private Long mHistoriesCount;
        @SerializedName("id")
        private String mId;
        @SerializedName("imageFacePath")
        private String mImageFacePath;
        @SerializedName("imagePath")
        private String mImagePath;
        @SerializedName("issueId")
        private String mIssueId;
        @SerializedName("parentId")
        private String mParentId;
        @SerializedName("parentName")
        private String mParentName;
        @SerializedName("priorityId")
        private String mPriorityId;
        @SerializedName("priorityName")
        private String mPriorityName;
        @SerializedName("progress")
        private String mProgress;
        @SerializedName("projectId")
        private String mProjectId;
        @SerializedName("rowId")
        private String mRowId;
        @SerializedName("startTime")
        private String mStartTime;
        @SerializedName("status")
        private String mStatus;
        @SerializedName("summary")
        private String mSummary;
        @SerializedName("type")
        private String mType;
        @SerializedName("unitId")
        private String mUnitId;
        @SerializedName("updatedBy")
        private String mUpdatedBy;
        @SerializedName("updatedName")
        private String mUpdatedName;
        @SerializedName("updatedTime")
        private String mUpdatedTime;
        @SerializedName("watchers")
        private List<Watcher> mWatchers;

        public List<Attach> getAttaches() {
            return mAttaches;
        }

        public void setAttaches(List<Attach> attaches) {
            mAttaches = attaches;
        }

        public Long getAttachsCount() {
            return mAttachsCount;
        }

        public void setAttachsCount(Long attachsCount) {
            mAttachsCount = attachsCount;
        }

        public String getCategoryId() {
            return mCategoryId;
        }

        public void setCategoryId(String categoryId) {
            mCategoryId = categoryId;
        }

        public String getCategoryName() {
            return mCategoryName;
        }

        public void setCategoryName(String categoryName) {
            mCategoryName = categoryName;
        }

        public String getCommentUID() {
            return mCommentUID;
        }

        public void setCommentUID(String commentUID) {
            mCommentUID = commentUID;
        }

        public Context getContext() {
            return mContext;
        }

        public void setContext(Context context) {
            mContext = context;
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

        public String getCreatedTime() {
            return mCreatedTime;
        }

        public void setCreatedTime(String createdTime) {
            mCreatedTime = createdTime;
        }

        public String getCreatorAvatarImageUrl() {
            return mCreatorAvatarImageUrl;
        }

        public void setCreatorAvatarImageUrl(String creatorAvatarImageUrl) {
            mCreatorAvatarImageUrl = creatorAvatarImageUrl;
        }

        public String getDescription() {
            return mDescription;
        }

        public void setDescription(String description) {
            mDescription = description;
        }

        public String getEndTime() {
            return mEndTime;
        }

        public void setEndTime(String endTime) {
            mEndTime = endTime;
        }

        public List<History> getHistories() {
            return mHistories;
        }

        public void setHistories(List<History> histories) {
            mHistories = histories;
        }

        public Long getHistoriesCount() {
            return mHistoriesCount;
        }

        public void setHistoriesCount(Long historiesCount) {
            mHistoriesCount = historiesCount;
        }

        public String getId() {
            return mId;
        }

        public void setId(String id) {
            mId = id;
        }

        public String getImageFacePath() {
            return mImageFacePath;
        }

        public void setImageFacePath(String imageFacePath) {
            mImageFacePath = imageFacePath;
        }

        public String getImagePath() {
            return mImagePath;
        }

        public void setImagePath(String imagePath) {
            mImagePath = imagePath;
        }

        public String getIssueId() {
            return mIssueId;
        }

        public void setIssueId(String issueId) {
            mIssueId = issueId;
        }

        public String getParentId() {
            return mParentId;
        }

        public void setParentId(String parentId) {
            mParentId = parentId;
        }

        public String getParentName() {
            return mParentName;
        }

        public void setParentName(String parentName) {
            mParentName = parentName;
        }

        public String getPriorityId() {
            return mPriorityId;
        }

        public void setPriorityId(String priorityId) {
            mPriorityId = priorityId;
        }

        public String getPriorityName() {
            return mPriorityName;
        }

        public void setPriorityName(String priorityName) {
            mPriorityName = priorityName;
        }

        public String getProgress() {
            return mProgress;
        }

        public void setProgress(String progress) {
            mProgress = progress;
        }

        public String getProjectId() {
            return mProjectId;
        }

        public void setProjectId(String projectId) {
            mProjectId = projectId;
        }

        public String getRowId() {
            return mRowId;
        }

        public void setRowId(String rowId) {
            mRowId = rowId;
        }

        public String getStartTime() {
            return mStartTime;
        }

        public void setStartTime(String startTime) {
            mStartTime = startTime;
        }

        public String getStatus() {
            return mStatus;
        }

        public void setStatus(String status) {
            mStatus = status;
        }

        public String getSummary() {
            return mSummary;
        }

        public void setSummary(String summary) {
            mSummary = summary;
        }

        public String getType() {
            return mType;
        }

        public void setType(String type) {
            mType = type;
        }

        public String getUnitId() {
            return mUnitId;
        }

        public void setUnitId(String unitId) {
            mUnitId = unitId;
        }

        public String getUpdatedBy() {
            return mUpdatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            mUpdatedBy = updatedBy;
        }

        public String getUpdatedName() {
            return mUpdatedName;
        }

        public void setUpdatedName(String updatedName) {
            mUpdatedName = updatedName;
        }

        public String getUpdatedTime() {
            return mUpdatedTime;
        }

        public void setUpdatedTime(String updatedTime) {
            mUpdatedTime = updatedTime;
        }

        public List<Watcher> getWatchers() {
            return mWatchers;
        }

        public void setWatchers(List<Watcher> watchers) {
            mWatchers = watchers;
        }

    }
    public class Watcher {

        @SerializedName("avatarImageUrl")
        private String mAvatarImageUrl;
        @SerializedName("createdBy")
        private String mCreatedBy;
        @SerializedName("createdName")
        private String mCreatedName;
        @SerializedName("isBlock")
        private String mIsBlock;
        @SerializedName("isRead")
        private String mIsRead;
        @SerializedName("isWatcher")
        private String mIsWatcher;
        @SerializedName("updatedBy")
        private String mUpdatedBy;
        @SerializedName("updatedName")
        private String mUpdatedName;
        @SerializedName("watcherId")
        private String mWatcherId;
        @SerializedName("watcherName")
        private String mWatcherName;

        public String getAvatarImageUrl() {
            return mAvatarImageUrl;
        }

        public void setAvatarImageUrl(String avatarImageUrl) {
            mAvatarImageUrl = avatarImageUrl;
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

        public String getIsBlock() {
            return mIsBlock;
        }

        public void setIsBlock(String isBlock) {
            mIsBlock = isBlock;
        }

        public String getIsRead() {
            return mIsRead;
        }

        public void setIsRead(String isRead) {
            mIsRead = isRead;
        }

        public String getIsWatcher() {
            return mIsWatcher;
        }

        public void setIsWatcher(String isWatcher) {
            mIsWatcher = isWatcher;
        }

        public String getUpdatedBy() {
            return mUpdatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            mUpdatedBy = updatedBy;
        }

        public String getUpdatedName() {
            return mUpdatedName;
        }

        public void setUpdatedName(String updatedName) {
            mUpdatedName = updatedName;
        }

        public String getWatcherId() {
            return mWatcherId;
        }

        public void setWatcherId(String watcherId) {
            mWatcherId = watcherId;
        }

        public String getWatcherName() {
            return mWatcherName;
        }

        public void setWatcherName(String watcherName) {
            mWatcherName = watcherName;
        }

    }

}
