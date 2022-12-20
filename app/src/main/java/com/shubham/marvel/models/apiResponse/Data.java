package com.shubham.marvel.models.apiResponse;

import java.util.List;

public class Data
{
    private String total;

    private String offset;

    private String limit;

    private String count;

    private List<ResponseModel> results;

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public String getOffset ()
    {
        return offset;
    }

    public void setOffset (String offset)
    {
        this.offset = offset;
    }

    public String getLimit ()
    {
        return limit;
    }

    public void setLimit (String limit)
    {
        this.limit = limit;
    }

    public String getCount ()
    {
        return count;
    }

    public void setCount (String count)
    {
        this.count = count;
    }

    public List<ResponseModel> getResults ()
    {
        return results;
    }

    public void setResults (List<ResponseModel> results)
    {
        this.results = results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [total = "+total+", offset = "+offset+", limit = "+limit+", count = "+count+", results = "+results+"]";
    }
}