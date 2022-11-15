/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Dinh Nam
 */
public class PostList {

    private Post post;
    private Product product;

    public PostList() {}

    public PostList(Post post, Product product) {
        this.post = post;
        this.product = product;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    @Override
    public String toString() {
        return "PostList{" + "post=" + post + ", product=" + product + '}';
    }

}
