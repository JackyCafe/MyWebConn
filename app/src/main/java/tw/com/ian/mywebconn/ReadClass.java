package tw.com.ian.mywebconn;

import java.io.Serializable;

public class ReadClass implements Serializable {
    String id;
    String title;
    String category;
    String content;
    String attachment;

    public ReadClass(String id, String title, String category, String content, String attachment) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.content = content;
        this.attachment = attachment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "ReadClass{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                ", attachment='" + attachment + '\'' +
                '}';
    }
}
