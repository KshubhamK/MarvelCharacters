package com.shubham.marvel.models.apiResponse;

import com.shubham.marvel.models.character.Data;

import java.util.List;

public class ResponseModel {
    private String copyright;

    private String code;

    private Data data;

    private String attributionHTML;

    private String attributionText;

    private String etag;

    private String status;

    public String getCopyright ()
    {
        return copyright;
    }

    public void setCopyright (String copyright)
    {
        this.copyright = copyright;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public Data getData ()
    {
        return data;
    }

    public void setData (Data data)
    {
        this.data = data;
    }

    public String getAttributionHTML ()
    {
        return attributionHTML;
    }

    public void setAttributionHTML (String attributionHTML)
    {
        this.attributionHTML = attributionHTML;
    }

    public String getAttributionText ()
    {
        return attributionText;
    }

    public void setAttributionText (String attributionText)
    {
        this.attributionText = attributionText;
    }

    public String getEtag ()
    {
        return etag;
    }

    public void setEtag (String etag)
    {
        this.etag = etag;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }
}
