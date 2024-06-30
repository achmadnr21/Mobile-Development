package com.example.practicecameraserverstorage.data;

public class FileData {

    private int id = -1;
    private String fileName = null;
    private String path = null;

    public FileData(int id, String fileName, String path) {
        this.id = id;
        this.fileName = fileName;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPath() {
        return path;
    }

}
