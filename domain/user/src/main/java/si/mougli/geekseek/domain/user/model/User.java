package si.mougli.geekseek.domain.user.model;

import javax.persistence.Entity;
import si.mougli.geekseek.domain.persistence.model.BaseEntity;

@Entity
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String name;
    private String bio;
    private String email;
    private String avatarUrl;

    /**
     * Internal auth access token
     */
    private String apiToken;

    /**
     * External auth system access token
     */
    private String accessToken;

    // JPA Default constructor
    protected User()
    {
    }

    public User(String id)
    {
        super(id);
    }

    public String getName()
    {
        return name;
    }

    public User setName(String name)
    {
        this.name = name;
        return this;
    }

    public String getBio()
    {
        return bio;
    }

    public User setBio(String bio)
    {
        this.bio = bio;
        return this;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getApiToken()
    {
        return apiToken;
    }

    public void setApiToken(String apiToken)
    {
        this.apiToken = apiToken;
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }

    public String getAvatarUrl()
    {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl)
    {
        this.avatarUrl = avatarUrl;
    }
}
