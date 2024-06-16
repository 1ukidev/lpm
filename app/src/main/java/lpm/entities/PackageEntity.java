package lpm.entities;

import com.alibaba.fastjson2.annotation.JSONField;

public class PackageEntity {
    @JSONField(name="name")
    private String name;

    @JSONField(name="url")
    private String url;

    @JSONField(name="sha256")
    private String SHA256;

    @JSONField(name="steps")
    private String steps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSHA256() {
        return SHA256;
    }

    public void setSHA256(String SHA256) {
        this.SHA256 = SHA256;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
