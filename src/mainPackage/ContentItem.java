/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainPackage;

import java.io.File;

/**
 *
 * @author danil
 */
public class ContentItem {
    public enum ContentType { TITLE, TEXT, IMAGE }
    
    private final ContentType type;
    private String text;
    private File imageFile;
    
    public ContentItem(String text, ContentType type) {
        this.text = text;
        this.type = type;
    }
    
    public ContentItem(File imageFile) {
        this.imageFile = imageFile;
        this.type = ContentType.IMAGE;
    }
    
    public ContentType getType() { return type; }
    public String getText() { return text; }
    public File getImageFile() { return imageFile; }
}
