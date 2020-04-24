package com.portal.homeFellowship.model;

import java.io.Serializable;

public class UserProfileAdmin implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1916032015578919764L;
	private Long roleID;
    private String type;// = UserProfileType.MAKER.getUserProfileType();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    } 

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((roleID == null) ? 0 : roleID.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof UserProfile)) {
            return false;
        }
        UserProfileAdmin other = (UserProfileAdmin) obj;
        if (roleID == null) {
            if (other.roleID != null) {
                return false;
            }
        } else if (!roleID.equals(other.roleID)) {
            return false;
        }
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "UserProfileAdmin [roleID=" + roleID + ", type=" + type + "]";
	}
    
    

}
