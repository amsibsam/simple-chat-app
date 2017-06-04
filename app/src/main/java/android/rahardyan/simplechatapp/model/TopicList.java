
package android.rahardyan.simplechatapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class TopicList {

    @SerializedName("issue_list")
    private List<IssueList> mIssueList;
    @SerializedName("page")
    private Long mPage;
    @SerializedName("pageSize")
    private Long mPageSize;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("userid")
    private String mUserid;

    public List<IssueList> getIssueList() {
        return mIssueList;
    }

    public void setIssueList(List<IssueList> issueList) {
        mIssueList = issueList;
    }

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public Long getPageSize() {
        return mPageSize;
    }

    public void setPageSize(Long pageSize) {
        mPageSize = pageSize;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getUserid() {
        return mUserid;
    }

    public void setUserid(String userid) {
        mUserid = userid;
    }

    public class IssueList {

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

    public class Topic {

        @SerializedName("attachments")
        private List<Object> mAttachments;
        @SerializedName("config")
        private Object mConfig;
        @SerializedName("context")
        private Context mContext;
        @SerializedName("createdTime")
        private String mCreatedTime;
        @SerializedName("creatorIcon")
        private String mCreatorIcon;
        @SerializedName("creatorId")
        private String mCreatorId;
        @SerializedName("creatorName")
        private String mCreatorName;
        @SerializedName("description")
        private String mDescription;
        @SerializedName("end")
        private String mEnd;
        @SerializedName("fileCount")
        private Long mFileCount;
        @SerializedName("histories")
        private List<Object> mHistories;
        @SerializedName("historyCount")
        private Long mHistoryCount;
        @SerializedName("id")
        private String mId;
        @SerializedName("imageFacePath")
        private String mImageFacePath;
        @SerializedName("imagePath")
        private String mImagePath;
        @SerializedName("inProgress")
        private Long mInProgress;
        @SerializedName("isCreatorBlocked")
        private String mIsCreatorBlocked;
        @SerializedName("issueId")
        private String mIssueId;
        @SerializedName("parentId")
        private String mParentId;
        @SerializedName("parentName")
        private String mParentName;
        @SerializedName("planetName")
        private String mPlanetName;
        @SerializedName("priorityId")
        private String mPriorityId;
        @SerializedName("priorityName")
        private String mPriorityName;
        @SerializedName("projectId")
        private String mProjectId;
        @SerializedName("start")
        private String mStart;
        @SerializedName("summary")
        private String mSummary;
        @SerializedName("type")
        private String mType;
        @SerializedName("updatedTime")
        private String mUpdatedTime;
        @SerializedName("watcherCount")
        private Long mWatcherCount;
        @SerializedName("watchers")
        private List<Object> mWatchers;

        public List<Object> getAttachments() {
            return mAttachments;
        }

        public void setAttachments(List<Object> attachments) {
            mAttachments = attachments;
        }

        public Object getConfig() {
            return mConfig;
        }

        public void setConfig(Object config) {
            mConfig = config;
        }

        public Context getContext() {
            return mContext;
        }

        public void setContext(Context context) {
            mContext = context;
        }

        public String getCreatedTime() {
            return mCreatedTime;
        }

        public void setCreatedTime(String createdTime) {
            mCreatedTime = createdTime;
        }

        public String getCreatorIcon() {
            return mCreatorIcon;
        }

        public void setCreatorIcon(String creatorIcon) {
            mCreatorIcon = creatorIcon;
        }

        public String getCreatorId() {
            return mCreatorId;
        }

        public void setCreatorId(String creatorId) {
            mCreatorId = creatorId;
        }

        public String getCreatorName() {
            return mCreatorName;
        }

        public void setCreatorName(String creatorName) {
            mCreatorName = creatorName;
        }

        public String getDescription() {
            return mDescription;
        }

        public void setDescription(String description) {
            mDescription = description;
        }

        public String getEnd() {
            return mEnd;
        }

        public void setEnd(String end) {
            mEnd = end;
        }

        public Long getFileCount() {
            return mFileCount;
        }

        public void setFileCount(Long fileCount) {
            mFileCount = fileCount;
        }

        public List<Object> getHistories() {
            return mHistories;
        }

        public void setHistories(List<Object> histories) {
            mHistories = histories;
        }

        public Long getHistoryCount() {
            return mHistoryCount;
        }

        public void setHistoryCount(Long historyCount) {
            mHistoryCount = historyCount;
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

        public Long getInProgress() {
            return mInProgress;
        }

        public void setInProgress(Long inProgress) {
            mInProgress = inProgress;
        }

        public String getIsCreatorBlocked() {
            return mIsCreatorBlocked;
        }

        public void setIsCreatorBlocked(String isCreatorBlocked) {
            mIsCreatorBlocked = isCreatorBlocked;
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

        public String getPlanetName() {
            return mPlanetName;
        }

        public void setPlanetName(String planetName) {
            mPlanetName = planetName;
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

        public String getProjectId() {
            return mProjectId;
        }

        public void setProjectId(String projectId) {
            mProjectId = projectId;
        }

        public String getStart() {
            return mStart;
        }

        public void setStart(String start) {
            mStart = start;
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

        public String getUpdatedTime() {
            return mUpdatedTime;
        }

        public void setUpdatedTime(String updatedTime) {
            mUpdatedTime = updatedTime;
        }

        public Long getWatcherCount() {
            return mWatcherCount;
        }

        public void setWatcherCount(Long watcherCount) {
            mWatcherCount = watcherCount;
        }

        public List<Object> getWatchers() {
            return mWatchers;
        }

        public void setWatchers(List<Object> watchers) {
            mWatchers = watchers;
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

    }
}
