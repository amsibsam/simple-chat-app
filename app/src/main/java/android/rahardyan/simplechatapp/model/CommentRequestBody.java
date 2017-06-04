package android.rahardyan.simplechatapp.model;

/**
 * Created by rahardyan on 03/06/17.
 */

public class CommentRequestBody {
    private String comment;
    private String uuid;

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getComment() {
        return comment;
    }

    public String getUuid() {
        return uuid;
    }
}
