package ismummy.me.onibara.models;

import java.io.Serializable;

import ismummy.me.onibara.core.Requests;

public class Store implements Serializable {
    private String title;
    private String subTitle;
    private String image;
    private String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getImage() {
        return Requests.MEDIA_URI + image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;

        if (object != null && object instanceof Store) {
            isEqual = (this.getId().equals(((Store) object).getId()));
        }

        return isEqual;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(this.getId());
    }
}
