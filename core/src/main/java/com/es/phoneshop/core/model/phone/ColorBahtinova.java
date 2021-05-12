package com.es.phoneshop.core.model.phone;

public class ColorBahtinova {
    private Long id;
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public ColorBahtinova(Long id, String code){
        this.id = id;
        this.code = code;
    }

    public ColorBahtinova(){

    }

    public boolean equals(Object object){
        if(this == object){
            return true;
        }
        if (object == null || object.getClass() != this.getClass()){
            return false;
        }
        ColorBahtinova comparedColor = (ColorBahtinova) object;
        if(id.equals(comparedColor.getId()) && code.equals(comparedColor.getCode())){
            return true;
        }
        else{
            return false;
        }
    }

    public int hashCode(){
        int hash = 33;
        hash = 77 * hash + id.hashCode();
        hash = 77 * hash + code.hashCode();
        return hash;
    }
}
