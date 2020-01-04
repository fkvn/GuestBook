package webtest.models;

import java.util.Date;

public class GuestBookEntry {
	Integer id;

    String name;

    String message;

    Date date;

    public GuestBookEntry( Integer id, String name, String message, Date date )
    {
        this.id = id;
        this.name = name;
        this.message = message;
        this.date = date;
    }

    public GuestBookEntry( Integer id, String name, String message )
    {
        this( id, name, message, new Date() );
    }

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }

}
