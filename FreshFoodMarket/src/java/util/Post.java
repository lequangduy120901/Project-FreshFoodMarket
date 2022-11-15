/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Dinh Nam
 */
public class Post {

    private int postID;
    private String thumbnail, title, description, author;
    private boolean flag, status;

    public Post() {
    }

    public Post(int postID, String thumbnail, String title, String description, String author, boolean flag, boolean status) {
        this.postID = postID;
        this.thumbnail = thumbnail;
        this.title = title;
        this.description = description;
        this.author = author;
        this.flag = flag;
        this.status = status;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Post{" + "postID=" + postID + ", thumbnail=" + thumbnail + ", title=" + title + ", description=" + description + ", author=" + author + ", flag=" + flag + ", status=" + status + '}';
    }
}
