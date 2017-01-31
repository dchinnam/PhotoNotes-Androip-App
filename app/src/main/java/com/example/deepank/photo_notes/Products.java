package com.example.deepank.photo_notes;

/**
 * Created by Deepank on 2/12/2016.
 */
public class Products {
    private int _id;
    private String _productname;
    private String _title;
    public Products(){

    }
    public Products(String _productname,String _title){

        this._productname=_productname;
        this._title=_title;
    }
    public void set_id(int _id){
        this._id=_id;
    }
    public void set_productname(String _productname){
        this._productname=_productname;
    }
    public int get_id(){
        return _id;
    }
    public String get_productname(){
        return _productname;
    }

    public String get_title(){
        return _title;
    }


}
