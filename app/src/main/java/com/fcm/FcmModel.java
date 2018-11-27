package com.fcm;

/**
 * Created by Android on 28/3/18.
 */

public class FcmModel
{
    private String appName;
    private String packageName;
    private String iconUrl;
    private String title;
    private String message;
    private String url;
    private Integer notificationId;

    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public String getIconUrl()
    {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl)
    {
        this.iconUrl = iconUrl;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public Integer getNotificationId()
    {
        return notificationId;
    }

    public void setNotificationId(String notificationId)
    {
        try
        {
            this.notificationId = Integer.parseInt(notificationId);
        } catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
    }

}
