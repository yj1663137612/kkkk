package enter;

/**
 * Created by ACER on 2016/10/24.
 */

public class Content {
    String summary;
    String icon;
    String stamp;
    String title;
    String nid;
    String link;
    String type;
    public Content() {

    }


    public Content(String summary, String icon, String stamp, String title, String nid, String link, String type) {
        this.summary = summary;
        this.icon = icon;
        this.stamp = stamp;
        this.title = title;
        this.nid = nid;
        this.link = link;
        this.type = type;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public String getStamp() {
        return stamp;
    }

    public String getTitle() {
        return title;
    }

    public String getNid() {
        return nid;
    }

    public String getLink() {
        return link;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Content{" +
                "summary='" + summary + '\'' +
                ", icon='" + icon + '\'' +
                ", stamp='" + stamp + '\'' +
                ", title='" + title + '\'' +
                ", nid='" + nid + '\'' +
                ", link='" + link + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
