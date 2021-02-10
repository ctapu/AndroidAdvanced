package com.wikitech.organizer.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PermissionsItemDto {

    @SerializedName("permissions")
    public ArrayList<String> permissions;

    @SerializedName("version")
    public String version;
}
